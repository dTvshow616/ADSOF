package ApartadoDos;

public class GraphvizVisitor<G> implements Visitor<G> {
    private StringBuilder sb = new StringBuilder();

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

    public String getGraph() {
        return "digraph {\n" + sb + "}";
    }
}
