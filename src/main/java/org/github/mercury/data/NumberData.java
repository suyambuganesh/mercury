package org.github.mercury.data;

public class NumberData extends BaseData {

    private final int current;
    private final int last;

    public NumberData(final int current, final int last) {
        this.current = current;
        this.last = last;
    }

    public int getCurrent() {
        return current;
    }

    public int getLast() {
        return last;
    }
}
