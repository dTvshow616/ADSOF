package ApartadoUno;

import java.util.List;

/**
 * Implements the interface featurizer
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 *
 * @param <T> the type of object stored in this featurizer
 */
public interface Featurizer<T> {

    /**
     * Returns the names of all features this Featurizer can extract
     * @return the names of all features this Featurizer can extract
     */
    List<String> featureNames();

    /**
     * Extracts the value of a named feature from the given object.
     * @param object the object where we are searching the feature
     * @param featureName the name of the feature we are searching
     * @return the raw value (String, Integer, Boolean, etc.)
     */
    Object featureValue(T object, String featureName);
}