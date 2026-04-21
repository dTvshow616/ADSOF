package ApartadoUno;

import java.util.Collections;

public class DemoEjUno {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main(String[] args) {
        Dataset<Person> dataSet = buildDataSet();
        System.out.println("dataset: " + dataSet);

        //dataSet.removeDuplicates();
        System.out.println("dataset w/o duplicates: " + dataSet);

        Feature<Integer> ages = dataSet.feature("age");
        System.out.println("Ages: " + ages);
        Collections.sort(ages);
        System.out.println("Ages sorted: " + ages);
        System.out.println("Min age: " + ages.min());
        System.out.println("Gender distribution: " + dataSet.feature("gender").distribution()); // freq. of each value
    }

    public static Dataset<Person> buildDataSet() {
        Person[] people = {new Person("Pedro", 66, 75, 100, true), // name, age, weight, height, male?
                           new Person("ana", 47, 54, 158, false), new Person("Luis", 34, 75, 176, true),
                           new Person("Rosa", 47, 54, 158, false)};

        Dataset<Person> dataSet = new Dataset<>(new PersonFeaturizer());
        dataSet.addAll(people);
        return dataSet;
    }
}