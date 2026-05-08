package ApartadoUno;

import java.util.List;
 
/**
 * A Featurizer extracts named features from objects of type T.
 * It acts as a bridge between domain objects and the Dataset machinery.
 */
public interface Featurizer<T> {
 
    /**
     * Returns the names of all features this Featurizer can extract.
     */
    List<String> featureNames();
 
    /**
     * Extracts the value of a named feature from the given object.
     * Returns the raw value (String, Integer, Boolean, etc.)
     */
    Object featureValue(T object, String featureName);
}