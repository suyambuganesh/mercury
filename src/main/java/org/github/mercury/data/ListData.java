package org.github.mercury.data;

import java.util.List;

public class ListData<T> extends BaseData {

    private final List<Item<T>> items;

    public ListData(List<Item<T>> items) {
        this.items = items;
    }

    public List<Item<T>> getItems() {
        return items;
    }

    public static class Item<T> {
        private String label;
        private T value;

        public Item() {
        }

        public Item(String label, T value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}
