package demos;

import dataset.Dataset;
import feature.Feature;
import feature.PersonFeaturizer;
import person.Person;

/**
 * Demo for Ejercicio 1
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class DemoEjUno {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * The entry point of application.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Dataset<Person> dataSet = buildDataSet();
        System.out.println("dataset: " + dataSet);

        //dataSet.removeDuplicates();
        System.out.println("dataset w/o duplicates: " + dataSet);

        Feature<Integer> ages = dataSet.feature("age");
        System.out.println("Ages: " + ages);
        ages.sort();
        System.out.println("Ages sorted: " + ages);
        System.out.println("Min age: " + ages.min());
        System.out.println("Gender distribution: " + dataSet.feature("gender").distribution()); // freq. of each value
    }

    /**
     * It builds a Person dataset
     * @return a Person dataset
     */
    public static Dataset<Person> buildDataSet() {
        Person[] people = {new Person("Pedro", 66, 75, 100, true), // name, age, weight, height, male?
                           new Person("ana", 47, 54, 158, false), new Person("Luis", 34, 75, 176, true),
                           new Person("Rosa", 47, 54, 158, false)};

        Dataset<Person> dataSet = new Dataset<>(new PersonFeaturizer());
        dataSet.addAll(people);
        return dataSet;
    }
}