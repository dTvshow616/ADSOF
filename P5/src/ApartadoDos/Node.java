package ApartadoDos;

import java.util.*;
import java.util.function.Predicate;

/**
 * Implements the nodes
 * @param <G> the type parameter
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.3
 */
public class Node<G> {
    /** The tree this node belongs to */
    DecisionTree<G> tree;
    /** This node's label */
    String label;
    /** This node's data */
    List<G> data;
    /** This node's children (except for the otherwise node) */
    HashMap<Predicate<G>, Node<G>> nextNodes = new HashMap<>();
    /** This node's otherwise node */
    Node<G> otherwiseNode = null;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A Node's constructor
     * @param tree  the tree this node is part of
     * @param label the node's name
     */
    public Node(DecisionTree<G> tree, String label) {
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
     * It adds a children node from this one following a certain condition and returns this same node
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
     * It sets this node's otherwise node and returns this same node
     * @param nodeName the otherwise node's name
     * @return this node
     */
    public Node<G> otherwise(String nodeName) {
        Node<G> node = new Node<>(tree, nodeName);
        this.otherwiseNode = node;
        this.tree.addNode(node);
        return this;
    }

    /**
     * It adds an object to this node's dataset
     * @param object the desired object
     */
    public void addData(G object) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.add(object);
    }

    /**
     * It filters this node's nada to its children, if it's a leaf it will register itself as such
     */
    public void filterData() {
        //System.out.println("Filtering data for node: " + this.label);
        /* If this is a leaf node add it to the tree's list of leaf nodes and skip the filtering */
        if (this.nextNodes.isEmpty() && this.otherwiseNode == null && this.data != null) {
            this.tree.addLeafNode(this);

        } else if (this.data != null) {
            /* Filter this node's data onto its kids */
            for (G object : this.data) {
                boolean alreadyAssigned = false;
                for (Predicate<G> predicate : this.nextNodes.keySet()) {
                    if (evaluateCondition(predicate, object)) {
                        this.nextNodes.get(predicate).addData(object);
                        alreadyAssigned = true;
                    }
                }
                if (!alreadyAssigned && this.otherwiseNode != null) {
                    this.otherwiseNode.addData(object);
                }
            }

            /* Filter kid's data */
            for (Node<G> node : this.nextNodes.values()) {
                node.filterData();
            }
            if (this.otherwiseNode != null) {
                this.otherwiseNode.filterData();
            }
        }
    }

    /**
     * It clears this node's data
     */
    public void clearData() {
        this.data = null;
    }

    public void accept(Visitor<G> visitor) {
        visitor.visit(this);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets this node's label
     * @return the label
     */
    public String getLabel() {
        return this.label;
    }

    public HashMap<Predicate<G>, Node<G>> getNextNodes() {
        return this.nextNodes;
    }

    /**
     * It gets this node's otherwise node
     * @return the otherwise node
     */
    public Node<G> getOtherwiseNode() {
        return this.otherwiseNode;
    }

    /**
     * It sets this node's data
     * @param newData the new data
     */
    public void setData(List<G> newData) {
        this.data = newData;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    @Override
    public String toString() {
        StringBuilder prediction = new StringBuilder();
        prediction.append(label).append("=");

        prediction.append("[");

        for (G object : this.data) {
            prediction.append(object);
            if (object != this.data.get(this.data.size() - 1)) {
                prediction.append(", ");
            }
        }

        prediction.append("]");

        return prediction.toString();
    }
}