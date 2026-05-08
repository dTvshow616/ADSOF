package ApartadoUno;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A generic Dataset stores a collection of objects of type T and uses
 * a Featurizer<T> to expose named feature columns for analysis.
 *
 * @param <T> the type of object stored in this dataset
 */
public class Dataset<T> {

    private final List<T> objects;
    private final Featurizer<T> featurizer;

    public Dataset(Featurizer<T> featurizer) {
        this.objects = new ArrayList<>();
        this.featurizer = featurizer;
    }

    // -----------------------------------------------------------------------
    // Mutation
    // -----------------------------------------------------------------------

    /** Adds an object to the dataset. */
    public void add(T object) {
        objects.add(object);
    }

    public void addAll(T[] array) {
        objects.addAll(java.util.Arrays.asList(array));
    }

    /**
     * Removes duplicate objects (objects that compare equal via equals()).
     * The first occurrence is kept; subsequent ones are discarded.
     */
    public void removeDuplicates() {
        Set<T> seen = new LinkedHashSet<>();

        seen.addAll(objects);
        objects.clear();
        objects.addAll(seen);
    }

    // -----------------------------------------------------------------------
    // Feature access
    // -----------------------------------------------------------------------


    public <F extends Comparable<F>> Feature<F> feature(String featureName) {
        Feature<F> f = new Feature<>(featureName);
        for (T obj : objects) {
            f.add((F) featurizer.featureValue(obj, featureName));
        }
        return f;
    }

    /**
     * Returns all feature names provided by the underlying Featurizer.
     */
    public List<String> featureNames() {
        return featurizer.featureNames();
    }

    /**
     * It gets the dataset's featurizer
     * @return the dataset's featurizer
     */
    public Featurizer<T> getFeaturizer() {
        return this.featurizer;
    }

    // -----------------------------------------------------------------------
    // Introspection
    // -----------------------------------------------------------------------

    /** Returns an unmodifiable view of the objects in this dataset. */
    public List<T> getObjects() {
        return Collections.unmodifiableList(objects);
    }

    /** Returns the number of objects in this dataset. */
    public int size() {
        return objects.size();
    }

    @Override
    public String toString() {
        // Print each feature as name=[values]
        return featurizer.featureNames().stream()
                .map(name -> name + "=" + feature(name).getValues())
                .collect(Collectors.joining(", ", "dataset{", "}"));
    }
}