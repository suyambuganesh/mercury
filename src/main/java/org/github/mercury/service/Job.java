package org.github.mercury.service;

import org.reactivestreams.Publisher;

public interface Job extends Publisher<String> {

    public void start();

    public void stop();

}
