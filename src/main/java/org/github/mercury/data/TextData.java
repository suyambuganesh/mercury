package org.github.mercury.data;

public class TextData extends BaseData {

    private final String text;

    public TextData(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
