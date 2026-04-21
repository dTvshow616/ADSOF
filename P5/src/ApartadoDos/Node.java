package ApartadoDos;

import java.util.*;
import java.util.function.Predicate;

/**
 * Implements the nodes
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class Node<G> {
    DecisionTree<G> tree;
    String label;
    List<G> data = new ArrayList<>();
    HashMap<Predicate<G>, Node<G>> nextNodes = new HashMap<>();
    Node<G> otherwiseNode = null; // Si es null, este nodo es nodo hoja

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A Node's constructor
     * @param tree  the tree this node is part of
     * @param label the node's name
     */
    Node(DecisionTree<G> tree, String label) {
        this.tree = tree;
        this.label = label;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It allows for an object's condition to be evaluated
     * @param condition the desired condition
     * @param object    the desired object
     * @return the predicate's output
     */
    public boolean evaluateCondition(Predicate<G> condition, G object) {
        return condition.test(object);
    }

    /**
     * Adds a children node from this one following a certain condition
     * @param label     the children node's label
     * @param condition the children node's condition
     * @return this node
     */
    public Node<G> withCondition(String label, Predicate<G> condition) {
        if (!this.nextNodes.containsKey(condition)) {
            Node<G> node = new Node<>(tree, label);
            this.nextNodes.put(condition, node);
            this.tree.addNode(node);
        }
        return this;
    }

    /**
     * Adds a children node from this one when none of the other conditions were met
     * @return this node
     */
    public Node<G> otherwise(String nodeName) {
        Node<G> node = new Node<>(tree, nodeName);
        this.otherwiseNode = node;
        this.tree.addNode(node);
        return this;
    }

    public void filterData() {
        //DUE
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
}