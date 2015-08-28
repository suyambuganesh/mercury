package org.github.mercury.rs;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.fn.Consumer;

public class SseEmitterSubscription<T> implements Subscription {

	private final Subscriber<T> subscriber;
	private final Consumer<SseEmitterSubscription<T>> cancel;

	public SseEmitterSubscription(Subscriber<T> subscriber, Consumer<SseEmitterSubscription<T>> cancel) {
		this.subscriber = subscriber;
		this.cancel = cancel;
	}

	@Override
	public void request(final long n) {
		if (n < 1) {
			throw new RuntimeException("Can't request a non-positive number");
		}
	}

	@Override
	public void cancel() {
		this.cancel.accept(this);
	}

}
