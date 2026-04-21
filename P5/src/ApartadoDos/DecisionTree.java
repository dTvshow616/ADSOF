package ApartadoDos;

import ApartadoUno.Dataset;
import ApartadoUno.Featurizer;

import java.util.*;

/**
 * Implements the decision tree
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.2
 */
public class DecisionTree<G> {
    private Node<G> rootNode = null;
    private HashMap<String, Node<G>> nodes = new HashMap<>(); // No es final
    private List<Node<G>> leafNodes = new ArrayList<>();
    private HashMap<String, G> labeledData = new HashMap<>(); // No es final
    private Featurizer<G> featurizer = new Featurizer<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor del árbol de decisiones
     */
    public DecisionTree() { // DUE: Ver qué meter aquí

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Ejecuta el árbol de decisión a partir de un Dataset, proporcionando la etiqueta resultado de la entrada
     * @return la etiqueta resultado de la entrada
     */
    public String predict(Dataset<G> dataset) {
        // DUE: meter el dataset en root
        this.rootNode.filterData();

        StringBuilder prediction = new StringBuilder();
        for (Node<G> node : this.leafNodes) {
            prediction.append(node.data.toString());
        }
        return prediction.toString();
    }

    /**
     * Ejecuta el árbol de decisión a partir de una colección de objetos del tipo paramétrico del árbol, proporcionando
     * la etiqueta resultado de la entrada
     * @return la etiqueta resultado de la entrada
     */
    public String predict(G... objects) { // DUE: Revisar argumentos
        Dataset<G> dataset = new Dataset<>();
        //DUE:dataset.addAll(objects);
        return predict(dataset);
    }

    /**
     * It gets the tree's node with a certain label
     * @param label the desired node's label
     * @return the tree's node with a certain label
     */
    public Node<G> node(String label) {
        if (nodes.isEmpty()) { // Si el árbol no tiene nodos se inicializa la raíz
            Node<G> node = new Node<>(this, label);
            this.rootNode = node;
            this.nodes.put(label, node);
        }
        return this.nodes.get(label);
    }

    /**
     * It adds a node to the tree
     * @param node the desired node
     */
    public void addNode(Node<G> node) {
        this.nodes.put(node.label, node);
    }
}