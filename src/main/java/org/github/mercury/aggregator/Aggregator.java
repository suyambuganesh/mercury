package org.github.mercury.aggregator;

import org.github.mercury.data.BaseData;

public interface Aggregator {

    public String id();

    public BaseData aggregateData();

}
