package ApartadoUno;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implements the class Dataset
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 * 
 * @param <T> the type of object stored in this dataset
 */
public class Dataset<T> {

    private final List<T> objects;
    private final Featurizer<T> featurizer;

    /**
     * A Dataset's constructor
     * @param featurizer the featurizer the dataset is going to use
     */
    public Dataset(Featurizer<T> featurizer) {
        this.objects = new ArrayList<>();
        this.featurizer = featurizer;
    }

    /** 
     * Adds an object to the dataset.
     * @param object the object is going to add
     */
    public void add(T object) {
        objects.add(object);
    }

    /** 
     * Adds an array of objcets to the dataset.
     * @param array the array that is going to be added in the dataset
     */
    public void addAll(T[] array) {
        objects.addAll(java.util.Arrays.asList(array));
    }

    /** 
     * Removes the duplicate objects in the dataset.
     */
    public void removeDuplicates() {
        Set<T> seen = new LinkedHashSet<>();

        seen.addAll(objects);
        objects.clear();
        objects.addAll(seen);
    }

    /** 
     * Return a list with all the values(of the featureName) from the objects
     * @param featureName the name of the feature we are searching
     * @return the feature with the collections of values
     */
    public <F extends Comparable<F>> Feature<F> feature(String featureName) {
        Feature<F> f = new Feature<>(featureName);
        for (T obj : objects) {
            f.add((F) featurizer.featureValue(obj, featureName));
        }
        return f;
    }

    /**
     * It gets the feature names from the featurizer
     * @return the feature names from the featurizer
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


    /**
     * It gets the dataset's objects
     * @return the dataset's objects
     */
    public List<T> getObjects() {
        return Collections.unmodifiableList(objects);
    }


    /**
     * It gets the size of the objects list
     * @return the size of the objects list
     */
    public int size() {
        return objects.size();
    }

    @Override
    public String toString() {
        return featurizer.featureNames().stream()
                .map(name -> name + "=" + feature(name).getValues())
                .collect(Collectors.joining(", ", "dataset{", "}"));
    }
}