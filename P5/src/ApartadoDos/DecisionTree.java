package ApartadoDos;

import ApartadoUno.Dataset;
import ApartadoUno.Featurizer;

import java.util.*;
import java.util.function.Predicate;

/**
 * Implements the Decision Tree
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 */
public class DecisionTree<G> {
    private Node<G> rootNode = null;
    private HashMap<String, Node<G>> nodes = new HashMap<>();
    private List<Node<G>> leafNodes = new ArrayList<>();
    private HashMap<String, G> labeledData = new HashMap<>();
    private Featurizer<G> featurizer;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * DecisionTree's constructor
     */
    public DecisionTree() {
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
        this.featurizer = dataset.getFeaturizer();
        //System.out.println("Setting data for node: " + this.rootNode.getLabel());
        this.rootNode.setData(dataset);
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
        Dataset<G> dataset = new Dataset<>(this.featurizer); // DUE: Ver de dónde sacar el featurizer
        dataset.addAll(objects);

        return predict(dataset);
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

        /* If the tree is empty the root is initialized */
        if (nodes.isEmpty()) {
            Node<G> node = new Node<>(this, label);
            this.rootNode = node;
            this.nodes.put(label, node);
            return node;
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

    /**
     * It gets this tree's featurizer
     * @return this tree's featurizer
     */
    public Featurizer<G> getFeaturizer() {
        return featurizer;
    }

    public HashMap<String, G> getLabeledData() {
        return labeledData;
    }

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