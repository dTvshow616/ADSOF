package demos;

import dataset.Dataset;
import feature.PersonFeaturizer;
import person.Person;
import tree.DecisionTree;

import java.util.function.Predicate;

/**
 * Demo for Ejercicio 3
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class DemoEjTres {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * The entry point of application.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Dataset<Person> dataSet = buildDataSet(); // like in previous listing
        DecisionTree<Person> dt = buildPersonDecisionTree();

        System.out.println(dt.predict(dataSet));

        Predicate<Person> isOldMale = dt.getPredicate("old male");

        System.out.println(isOldMale.test(new Person("Pedro", 66, 75, 180, true)));
        System.out.println(isOldMale.test(new Person("Ana", 47, 54, 158, false)));
        System.out.println(isOldMale.test(new Person("Luis", 34, 75, 176, true)));
    }

    /**
     * It builds a Person decision tree
     * @return a Person decision tree
     */
    public static DecisionTree<Person> buildPersonDecisionTree() {
        DecisionTree<Person> dt = new DecisionTree<>();

        dt.node("root") // nodo raíz, al ser el primero que se añade
          .withCondition("male", p -> p.isMale()).otherwise("female");
        dt.node("male") // como el nodo ya existe, se añaden condiciones sobre él
          .withCondition("old male", p -> p.getAge() > 65)
          .withCondition("middle male", p -> p.getAge() <= 65 && p.getAge() > 34).otherwise("young male");

        return dt;
    }

    /**
     * It builds a Person dataset
     * @return a Person dataset
     */
    public static Dataset<Person> buildDataSet() {
        Person[] people = {new Person("Pedro", 66, 75, 100, true), // name, age, weight, height, male?
                           new Person("Ana", 47, 54, 158, false), new Person("Luis", 34, 75, 176, true),
                           new Person("Rosa", 47, 54, 158, false)};

        Dataset<Person> dataSet = new Dataset<>(new PersonFeaturizer());
        dataSet.addAll(people);
        return dataSet;
    }
}