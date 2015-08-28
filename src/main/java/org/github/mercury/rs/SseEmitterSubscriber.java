package org.github.mercury.rs;

import java.io.IOException;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import reactor.fn.Consumer;

public class SseEmitterSubscriber implements Subscriber<String> {

    private final SseEmitter sseEmitter;
    private final Consumer<Throwable> errorConsumer;
    private Subscription subscription;

    public SseEmitterSubscriber(final SseEmitter sseEmitter, final Consumer<Throwable> errorConsumer) {
        this.sseEmitter = sseEmitter;
        this.errorConsumer = errorConsumer;
    }

    @Override
    public void onSubscribe(final Subscription s) {
        this.subscription = subscription;
        this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(final String data) {
        try {
            this.sseEmitter.send(data);
            this.subscription.request(Long.MAX_VALUE);
        }
        catch (IOException e) {
            errorConsumer.accept(e);
        }
    }

    @Override
    public void onError(final Throwable t) {
        errorConsumer.accept(t);
    }

    @Override
    public void onComplete() {
        this.sseEmitter.complete();
    }

}
