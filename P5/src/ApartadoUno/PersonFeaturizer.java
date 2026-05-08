package ApartadoUno;

import java.util.Arrays;
import java.util.List;

/**
 * Implements the class Person Featurizer
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 */
public class PersonFeaturizer implements Featurizer<Person> {

    private static final List<String> FEATURES = Arrays.asList("age", "weight", "height", "gender");

    /**
     * Returns the names of all features this Featurizer can extract
     * @return the names of all features this Featurizer can extract
     */
    @Override
    public List<String> featureNames() {
        return FEATURES;
    }

    /**
     * Extracts the value of a named feature from the given object.
     * @param person the object where we are searching the feature
     * @param featureName the name of the feature we are searching
     * @return the raw value (String, Integer, Boolean, etc.)
     */
    @Override
    public Object featureValue(Person person, String featureName) {
        switch (featureName) {
            case "age":
                return person.getAge();
            case "weight":
                return person.getWeight();
            case "height":
                return person.getHeight();
            case "gender":
                return person.getGender();
            default:
                throw new IllegalArgumentException("Unknown feature: " + featureName);
        }
    }
}