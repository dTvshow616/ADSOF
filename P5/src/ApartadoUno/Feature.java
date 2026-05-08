package ApartadoUno;

import java.util.*;

public class Feature<T extends Comparable<T>> {

    private String name;
    private List<T> values;

    public Feature(String name) {
        this.name = name;
        this.values = new ArrayList<>();
    }

    public Feature(String name, List<T> values) {
        this.name = name;
        this.values = new ArrayList<>(values);
    }

    public void add(T value) {
        values.add(value);
    }

    public List<T> getValues() {
        return values;
    }

    public void sort() {
        Collections.sort(values);
    }

    public T min() {
        return Collections.min(values);
    }

    public T max() {
        return Collections.max(values);
    }

    public Map<T, Integer> distribution() {
        Map<T, Integer> freq = new HashMap<>();

        for (T value : values) {
            freq.put(value, freq.getOrDefault(value, 0) + 1);
        }

        return freq;
    }

    @Override
    public String toString() {
        return name + "=" + values;
    }
}