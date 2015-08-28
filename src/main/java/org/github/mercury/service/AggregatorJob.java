package org.github.mercury.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.fn.Consumer;
import reactor.fn.timer.HashWheelTimer;

public class AggregatorJob implements Job, Consumer<Long> {

    private final HashWheelTimer hashWheelTimer;
    private final Map<Subscription, Subscriber<? super String>> subscribers;

    public AggregatorJob() {
        this.hashWheelTimer = new HashWheelTimer();
        this.subscribers = new ConcurrentHashMap<>();
    }

    @Override
    public void accept(final Long t) {
        throw new UnsupportedOperationException("Method not implemented");
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
    public void subscribe(final Subscriber<? super String> s) {
        throw new UnsupportedOperationException("Method not implemented");
    }

}
