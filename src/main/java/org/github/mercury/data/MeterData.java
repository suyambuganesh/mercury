package org.github.mercury.data;

public class MeterData extends BaseData {

    private final byte value;

    public MeterData(byte value) {
        this.value = value;
    }

    public MeterData(String title, byte value) {
        this.value = value;
        setTitle(title);
    }

    public byte getValue() {
        return value;
    }
}
