package ApartadoDos;

import ApartadoUno.Dataset;

import java.util.HashMap;
import java.util.function.Predicate;

/**
 * Implements the nodes
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class Node<G> {
    DecisionTree<G> tree;
    String label;
    Dataset<G> data;
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

    public void addData(G object) {
        if (this.data == null) {
            this.data = new Dataset<>(this.tree.getFeaturizer());
        }
        this.data.add(object);
    }

    /**
     * It filters this node's nada to its children, if it's a leaf it will register itself as such
     */
    public void filterData() {
        //System.out.println("Filtering data for node: " + this.label);
        if (this.nextNodes.isEmpty() && this.otherwiseNode == null && this.data != null) {
            this.tree.addLeafNode(this);
        } else if (this.data != null) {
            for (G object : this.data.getObjects()) {
                boolean alreadyAssigned = false;
                for (Predicate<G> predicate : this.nextNodes.keySet()) {
                    if (predicate.test(object)) {
                        this.nextNodes.get(predicate).addData(object);
                        alreadyAssigned = true;
                    }
                }
                if (!alreadyAssigned && this.otherwiseNode != null) {
                    this.otherwiseNode.addData(object);
                }
            }
            // Una vez que se filtre la info llamda a los hijos
            for (Node<G> node : this.nextNodes.values()) {
                node.filterData();
            }
            if (this.otherwiseNode != null) {
                this.otherwiseNode.filterData();
            }
        }
    }

    public void clearData() {
        this.data = null;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public String getLabel() {
        return this.label;
    }

    public Node<G> getOtherwiseNode() {
        return this.otherwiseNode;
    }

    public void setData(Dataset<G> newData) {
        this.data = newData;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    public String toString() {
        StringBuilder prediction = new StringBuilder();
        prediction.append(label).append("=");

        prediction.append("[");

        for (G object : this.data.getObjects()) {
            prediction.append(this.tree.getFeaturizer().importantFeatureValues(object));
            if (object != this.data.getObjects().get(this.data.getObjects().size() - 1)) {
                prediction.append(", ");
            }
        }

        prediction.append("]");

        return prediction.toString();
    }
}