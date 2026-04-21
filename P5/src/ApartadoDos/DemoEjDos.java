package ApartadoDos;

import ApartadoUno.Dataset;
import ApartadoUno.Person;

public class DemoEjDos {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main(String[] args) {
        Dataset<Person> dataSet = buildDataSet(); // like in previous listing
        DecisionTree<Person> dt = buildPersonDecisionTree();

        System.out.println(dt.predict(dataSet));
        System.out.println(
                dt.predict(new Person("Miguel", 86, 72, 165, true), new Person("Clara", 42, 59, 162, false)));
    }

    public static DecisionTree<Person> buildPersonDecisionTree() {
        DecisionTree<Person> dt = new DecisionTree<>();

        dt.node("root") // nodo raíz, al ser el primero que se añade
          // Creo que el lambda entrega true/false como segundo parámetro
          .withCondition("male", p -> p.isMale())
          .otherwise("female");
        dt.node("male") // como el nodo ya existe, se añaden condiciones sobre el
          .withCondition("old male", p -> p.getAge() > 65)
          .withCondition("middle male", p -> p.getAge() <= 65 && p.getAge() > 34)
          .otherwise("young male");

        return dt;
    }

    public static Dataset<Person> buildDataSet() {
        return null;//DUE
    }
}