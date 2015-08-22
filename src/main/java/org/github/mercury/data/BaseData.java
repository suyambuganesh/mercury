package org.github.mercury.data;

import java.time.Instant;

public class BaseData {

    private String id;
    private final long updatedAt;
    private String title;

    public BaseData() {
        this.updatedAt = Instant.now().getEpochSecond();
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
