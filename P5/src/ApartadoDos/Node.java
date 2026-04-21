package ApartadoDos;

import java.util.List;
import java.util.function.Predicate;

public class Node<G> {
    String feature;
    List<G> data;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A Node's constructor
     * @param feature the node's feature
     */
    Node(String feature) {
        this.feature = feature;
    }

    public boolean evalCondition(Predicate<G> condition, G object) {
        return condition.test(object);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public String getFeature() {
        return feature;
    }

    public void setFeature(String newFeature) {
        this.feature = newFeature;
    }

}