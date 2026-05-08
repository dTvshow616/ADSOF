package ApartadoUno;

import java.util.Arrays;
import java.util.List;

public class PersonFeaturizer implements Featurizer<Person> {
 
        private static final List<String> FEATURES =
                Arrays.asList("age", "weight", "height", "gender");
 
        @Override
        public List<String> featureNames() { return FEATURES; }
 
        @Override
        public Object featureValue(Person person, String featureName) {
            switch (featureName) {
                case "age":    return person.getAge();
                case "weight": return person.getWeight();
                case "height": return person.getHeight();
                case "gender": return person.getGender();
                default:
                    throw new IllegalArgumentException("Unknown feature: " + featureName);
            }
        }
}
