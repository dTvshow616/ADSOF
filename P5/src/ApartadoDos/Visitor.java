package ApartadoDos;

/**
 * Implements the interface Visitor
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 * 
 * @param <G> the type of object send to the in the Visitor
 */
public interface Visitor<G> {
    /**
     * Prints all the labels from de decision tree
     * @param node node of the decision tree where the visit is going to start
     */
    void visit(Node<G> node);
}
