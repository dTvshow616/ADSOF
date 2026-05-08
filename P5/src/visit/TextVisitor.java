package visit;

import tree.Node;

/**
 * Implements the class Text Visitor
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 *
 * @param <G> the type of object stored in this visitor
 */
public class TextVisitor<G> implements Visitor<G> {
    private int depth = 0;

    /**
     * Prints all the labels from de decision tree
     * @param node node of the decision tree where the visit is going to start
     */
    @Override
    public void visit(Node<G> node) {
        System.out.println("  ".repeat(depth) + node.getLabel());
        depth++;
        for (Node<G> child : node.getNextNodes().values()) {
            child.accept(this);
        }

        if (node.getOtherwiseNode() != null) {
            node.getOtherwiseNode().accept(this);
        }
        depth--;
    }
}