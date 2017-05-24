package solver.model;

/**
 * Created by will on 5/24/17.
 */
public abstract class TreeDirection {

    private final char direction;
    private final TreeNode node;

    public TreeDirection(char direction, TreeNode node) {
        this.direction = direction;
        this.node = node;
    }

    public char getDirection() {
        return direction;
    }

    public TreeNode getNode() {
        return node;
    }

}
