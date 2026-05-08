package ApartadoDos;

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
        for (Node<G> child : node.nextNodes.values()) {
            sb.append("  \"").append(node.label)
              .append("\" -> \"").append(child.label).append("\";\n");
            child.accept(this);
        }
        if (node.otherwiseNode != null) {
            sb.append("  \"").append(node.label)
              .append("\" -> \"").append(node.otherwiseNode.label)
              .append("\" [style=dashed];\n");
            node.otherwiseNode.accept(this);
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
