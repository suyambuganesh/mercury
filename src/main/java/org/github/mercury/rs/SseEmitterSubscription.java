package org.github.mercury.rs;

import org.reactivestreams.Subscription;

public class SseEmitterSubscription<T> implements Subscription {

    public SseEmitterSubscription() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void request(final long n) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("Method not implemented");
    }

}
