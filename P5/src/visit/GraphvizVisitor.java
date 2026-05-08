package visit;

import tree.Node;

/**
 * Implements the class GrapvizVisitor
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 *
 * @param <G> the type of object stored in this visitor
 */
public class GraphvizVisitor<G> implements Visitor<G> {
    private StringBuilder sb = new StringBuilder();

    /**
     * Prints all the labels from de decision tree
     * @param node node of the decision tree where the visit is going to start
     */
    @Override
    public void visit(Node<G> node) {
        for (Node<G> child : node.getNextNodes().values()) {
            sb.append("  \"").append(node.getLabel())
              .append("\" -> \"").append(child.getLabel()).append("\";\n");
            child.accept(this);
        }
        if (node.getOtherwiseNode() != null) {
            sb.append("  \"").append(node.getLabel())
              .append("\" -> \"").append(node.getOtherwiseNode().getLabel())
              .append("\" [style=dashed];\n");
            node.getOtherwiseNode().accept(this);
        }
    }

    /**
     * Gets the graph of the decision tree
     * @return the graph of the decision tree
     */
    public String getGraph() {
        return "digraph {\n" + sb + "}";
    }
}