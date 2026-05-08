package feature;

import java.util.*;

/**
 * Implements the class Feature
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 *
 * @param <T> the type of object stored in this dataset that needs to be comparable
 */
public class Feature<T extends Comparable<T>> {

    private String name;
    private List<T> values;

    /**
     * A Feature's constructor
     * @param name the name of the feature
     */
    public Feature(String name) {
        this.name = name;
        this.values = new ArrayList<>();
    }

    /**
     * A Feature's constructor
     * @param name the name of the feature
     * @param values a list oof values to be added in the feature
     */
    public Feature(String name, List<T> values) {
        this.name = name;
        this.values = new ArrayList<>(values);
    }

    /**
     * Adds a value to the feautre
     * @param value the value to be added
     */
    public void add(T value) {
        values.add(value);
    }

    /**
     * Gets the list of values
     * @return the list of values
     */
    public List<T> getValues() {
        return values;
    }

    /**
     *  Sorts the list of values
     */
    public void sort() {
        Collections.sort(values);
    }

    /**
     * Gets the min of the list of values
     * @return the min of the list of values
     */
    public T min() {
        return Collections.min(values);
    }

    /**
     * Gets the max of the list of values
     * @return the max of the list of values
     */
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