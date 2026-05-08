package ApartadoDos;

public class TextVisitor<G> implements Visitor<G> {
    private int depth = 0;

    @Override
    public void visit(Node<G> node) {
        System.out.println("  ".repeat(depth) + node.label);
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
