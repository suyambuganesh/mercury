package org.github.mercury.aggregator.jira;

import java.util.List;

import org.github.mercury.data.BaseData;

public class JiraData extends BaseData {

    private Integer todo;
    private Integer inProgress;
    private Integer done;
    private List<String> inProgressFeatures;

    public Integer getTodo() {
        return todo;
    }

    public void setTodo(final Integer todo) {
        this.todo = todo;
    }

    public Integer getInProgress() {
        return inProgress;
    }

    public void setInProgress(final Integer inProgress) {
        this.inProgress = inProgress;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(final Integer done) {
        this.done = done;
    }

    public List<String> getInProgressFeatures() {
        return inProgressFeatures;
    }

    public void setInProgressFeatures(final List<String> inProgressFeatures) {
        this.inProgressFeatures = inProgressFeatures;
    }

}
