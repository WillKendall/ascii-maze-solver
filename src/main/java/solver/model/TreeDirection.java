package solver.model;

/**
 * Created by will on 5/24/17.
 */
public class TreeDirection {

    private final Character direction;
    private TreeNode node;

    public TreeDirection(Character direction) {
        this.direction = direction;
        this.node = null;
    }

    public TreeDirection(Character direction, TreeNode node) {
        this.direction = direction;
        this.node = node;
    }

    public char getDirection() {
        return direction;
    }

    public TreeNode getNode() {
        return node;
    }

    public void setNode(TreeNode node)  {
        this.node = node;
    }

}
