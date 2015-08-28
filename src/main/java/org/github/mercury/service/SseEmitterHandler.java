package org.github.mercury.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.github.mercury.aggregator.Aggregator;
import org.github.mercury.rs.SseEmitterSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import reactor.fn.Consumer;

@Service
public class SseEmitterHandler implements EventSourceHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(SseEmitterHandler.class);

	private final Map<String, Job> jobRepository = new ConcurrentHashMap<>();
	private List<Aggregator> aggregators;

	@Autowired
	public SseEmitterHandler(List<Aggregator> aggregators) {
		this.aggregators = aggregators;
	}

	@Override
	public void handleSseEmitter(final String application, final SseEmitter sseEmitter) {
		if (jobRepository.containsKey(application)) {
			jobRepository.get(application).subscribe(new SseEmitterSubscriber(sseEmitter, errorConsumer));
		} else {
			Job job = new AggregatorJob(aggregators);
			job.subscribe(new SseEmitterSubscriber(sseEmitter, errorConsumer));
			job.start();
			jobRepository.put(application, job);
		}
	}

	private final Consumer<Throwable> errorConsumer = new Consumer<Throwable>() {
		@Override
		public void accept(final Throwable t) {
			LOGGER.error("Error from subscriber", t);
		}
	};
}
