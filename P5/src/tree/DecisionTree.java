package tree;

import dataset.Dataset;
import visit.Visitor;

import java.util.*;
import java.util.function.Predicate;

/**
 * Implements the Decision Tree
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 */
public class DecisionTree<G> {
    private final String rootName = "root";
    private Node<G> rootNode = null;
    private HashMap<String, Node<G>> nodes = new HashMap<>();
    private List<Node<G>> leafNodes = new ArrayList<>();
    private HashMap<String, G> labeledData = new HashMap<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * DecisionTree's constructor
     */
    public DecisionTree() {
        rootNode = new Node<>(this, rootName);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Ejecuta el árbol de decisión a partir de un Dataset, proporcionando la etiqueta resultado de la entrada
     * @return la etiqueta resultado de la entrada
     */
    public String predict(Dataset<G> dataset) {
        if (dataset == null) {
            throw new IllegalArgumentException("Dataset cannot be null");
        }

        /* Clear tree's data */
        for (Node<G> node : nodes.values()) {
            node.clearData();
        }
        this.leafNodes.clear();

        /* Filter the dataset */
        //System.out.println("Setting data for node: " + this.rootNode.getLabel());
        this.rootNode.setData(dataset.getObjects());
        this.rootNode.filterData();

        /* Create the label */
        StringBuilder prediction = new StringBuilder();
        prediction.append("{");

        boolean first = true;
        for (Node<G> node : this.leafNodes) {
            if (!first) {
                prediction.append(", ");
            } else {
                first = false;
            }
            prediction.append(node.toString());
        }

        prediction.append("}");

        return prediction.toString();
    }

    /**
     * Ejecuta el árbol de decisión a partir de una colección de objetos del tipo paramétrico del árbol, proporcionando
     * la etiqueta resultado de la entrada
     * @return la etiqueta resultado de la entrada
     */
    public String predict(G... objects) {
        /* Clear tree's data */
        for (Node<G> node : nodes.values()) {
            node.clearData();
        }
        this.leafNodes.clear();

        /* Filter the dataset */
        //System.out.println("Setting data for node: " + this.rootNode.getLabel());
        this.rootNode.setData(List.of(objects));
        this.rootNode.filterData();

        /* Create the label */
        StringBuilder prediction = new StringBuilder();
        prediction.append("{");

        boolean first = true;
        for (Node<G> node : this.leafNodes) {
            if (!first) {
                prediction.append(", ");
            } else {
                first = false;
            }
            prediction.append(node.toString());
        }

        prediction.append("}");

        return prediction.toString();
    }

    /**
     * It gets the tree's node with a certain label
     * @param label the desired node's label
     * @return the tree's node with a certain label
     */
    public Node<G> node(String label) {
        if (label == null) {
            throw new IllegalArgumentException("Label cannot be null");
        }

        if (label.equals(rootName)) {
            return rootNode;
        }

        return this.nodes.get(label);
    }

    /**
     * It adds a node to the tree
     * @param node the desired node
     */
    public void addNode(Node<G> node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        this.nodes.put(node.label, node);
    }

    /**
     * It does a Depth First Search on the tree and returns the accumulated predicate for a label from a certain node
     * @param current     the current node
     * @param targetLabel the targeted label
     * @param accumulated the accumulated predicates so far
     * @return the accumulated predicate for a label from the current node
     */
    private Predicate<G> dfs(Node<G> current, String targetLabel, Predicate<G> accumulated) {
        if (current == null || targetLabel == null || accumulated == null) {
            throw new IllegalArgumentException("No parameters should be null");
        }

        /* If the label is found, return the accumulated predicate */
        if (current.getLabel().equals(targetLabel)) {
            return accumulated;
        }

        /* Try all paths save for the otherwise node's */
        for (Map.Entry<Predicate<G>, Node<G>> entry : current.nextNodes.entrySet()) {
            Predicate<G> newPath = accumulated.and(entry.getKey());
            Predicate<G> result = dfs(entry.getValue(), targetLabel, newPath);
            if (result != null) {
                return result;
            }
        }

        /* Try otherwise node path */
        if (current.getOtherwiseNode() != null) {
            return dfs(current.otherwiseNode, targetLabel, accumulated);
        }

        return null;
    }

    /**
     * It adds a node to this tree's list of lead nodes
     * @param node the desired node
     */
    public void addLeafNode(Node<G> node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        this.leafNodes.add(node);
    }

    public void accept(Visitor<G> visitor) {
        rootNode.accept(visitor);
    }

    /**
     * It gets the labeled data
     * @return the labeled data
     */
    public HashMap<String, G> getLabeledData() {
        return labeledData;
    }

    /**
     * It gets the leaf nodes
     * @return the leaf nodes
     */
    public List<Node<G>> getLeafNodes() {
        return leafNodes;
    }

    public HashMap<String, Node<G>> getNodes() {
        return nodes;
    }

    public Predicate<G> getPredicate(String label) {
        return dfs(rootNode, label, p -> true);
    }

    public Node<G> getRootNode() {
        return rootNode;
    }
}