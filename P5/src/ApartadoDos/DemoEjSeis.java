package ApartadoDos;

import ApartadoUno.Dataset;
import ApartadoUno.Person;
import ApartadoUno.PersonFeaturizer;

public class DemoEjSeis {
            /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main(String[] args) {
        Dataset<Person> dataSet = buildDataSet(); // like in previous listing
        DecisionTree<Person> dt = buildPersonDecisionTree();

        System.out.println(dt.predict(dataSet));
        

        dt.accept(new TextVisitor<>());

        GraphvizVisitor<Person> gv = new GraphvizVisitor<>();
        dt.accept(gv);
        System.out.println(gv.getGraph());          
    }

    public static DecisionTree<Person> buildPersonDecisionTree() {
        DecisionTree<Person> dt = new DecisionTree<>();

        dt.node("root") // nodo raíz, al ser el primero que se añade
          .withCondition("male", p -> p.isMale()).otherwise("female");
        dt.node("male") // como el nodo ya existe, se añaden condiciones sobre él
          .withCondition("old male", p -> p.getAge() > 65)
          .withCondition("middle male", p -> p.getAge() <= 65 && p.getAge() > 34).otherwise("young male");

        return dt;
    }

    public static Dataset<Person> buildDataSet() {
        Person[] people = {new Person("Pedro", 66, 75, 100, true), // name, age, weight, height, male?
                           new Person("Ana", 47, 54, 158, false), new Person("Luis", 34, 75, 176, true),
                           new Person("Rosa", 47, 54, 158, false)};

        Dataset<Person> dataSet = new Dataset<>(new PersonFeaturizer());
        dataSet.addAll(people);
        return dataSet;
    }
}
