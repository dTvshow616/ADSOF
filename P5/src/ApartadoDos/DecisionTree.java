package ApartadoDos;

import ApartadoUno.Dataset;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

/**
 * Implementa el árbol de decisiones
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public class DecisionTree<G> {
    private List<Node> nodes;
    private HashMap<String, G> labeledData = new HashMap<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor del árbol de decisiones
     */
    public DecisionTree() { // DUE: Ver qué meter aquí

    }

    /**
     * Ejecuta el árbol de decisión a partir de un Dataset, proporcionando la etiqueta resultado de la entrada
     * @return la etiqueta resultado de la entrada
     */
    public String predict(Dataset<G> dataset) {
        return "DUE"; // DUE
    }

    /**
     * Ejecuta el árbol de decisión a partir de una colección de objetos del tipo paramétrico del árbol, proporcionando
     * la etiqueta resultado de la entrada
     * @return la etiqueta resultado de la entrada
     */
    public String predict(G... objects) { // DUE: Revisar argumentos
        return "DUE"; // DUE
    }

    /**
     * Gets a subtree of this one whose root is the desired node
     * @param nodeName the name of the desired node
     * @return a subtree of this one whose root is the desired node
     */
    public DecisionTree<G> node(String nodeName) {
        return null; //DUE
    }

    public DecisionTree<G> withCondition(String nodeName, Predicate<G> condition) { //DUE: Parámetros de entrada
        return null; // DUE
    }

    public DecisionTree<G> otherwise(String nodeName) {
        return null; // DUE
    }
}