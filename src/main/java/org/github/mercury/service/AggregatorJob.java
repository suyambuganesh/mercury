package org.github.mercury.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.github.mercury.aggregator.Aggregator;
import org.github.mercury.rs.SseEmitterSubscription;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.fn.Consumer;
import reactor.fn.timer.HashWheelTimer;

public class AggregatorJob implements Job, Consumer<Long> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AggregatorJob.class);

	private final HashWheelTimer hashWheelTimer;
	private final Map<Subscription, Subscriber<? super String>> subscribers;
	private List<Aggregator> aggregators;
	private final ExecutorService executor;
	private final ObjectMapper objectMapper;

	public AggregatorJob(List<Aggregator> aggregators) {
		this.aggregators = aggregators;
		this.hashWheelTimer = new HashWheelTimer();
		this.subscribers = new ConcurrentHashMap<>();
		this.executor = Executors.newFixedThreadPool(this.aggregators.size());
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public void accept(final Long t) {
		if (this.subscribers.keySet().size() >= 1) {
			// TODO RxJava-fy this code fragment
			for (Aggregator aggregator : this.aggregators) {
				this.executor.execute(() -> {
					for (Map.Entry<Subscription, Subscriber<? super String>> entry : subscribers.entrySet()) {
						try {
							entry.getValue().onNext(objectMapper.writeValueAsString(aggregator.aggregateData()));
						} catch (Exception e) {
							LOGGER.error("Error while aggregating results", e);
						}
					}
				});
			}
		} else {
			// no subscribers so stop the timer
			stop();
		}
	}

	@Override
	public void start() {
		hashWheelTimer.schedule(this, 15, TimeUnit.SECONDS, 2000);
	}

	@Override
	public void stop() {
		hashWheelTimer.cancel();
	}

	@Override
	public void subscribe(final Subscriber<? super String> subscriber) {
		this.subscribers.put(new SseEmitterSubscription<>(subscriber, subscribers::remove), subscriber);
	}

}
