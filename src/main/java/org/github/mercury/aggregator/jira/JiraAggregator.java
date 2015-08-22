package org.github.mercury.aggregator.jira;

import org.github.mercury.aggregator.Aggregator;
import org.github.mercury.data.BaseData;
import org.springframework.stereotype.Component;

@Component
public class JiraAggregator implements Aggregator {

    @Override
    public String id() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public BaseData aggregateData() {
        throw new UnsupportedOperationException("Method not implemented");
    }

}
