package solver.model;

/**
 * Created by will on 5/24/17.
 */
public class TreeDirection {

    private final Character dirChar;
    private boolean toStart;
    private TreeNode node;

    public TreeDirection(Character dirChar) {
        this.dirChar = dirChar;
        this.toStart = false;
        this.node = null;
    }

    public TreeDirection(Character dirChar, TreeNode node) {
        this.dirChar = dirChar;
        this.toStart = false;
        this.node = node;
    }

    public char getDirChar() {
        return dirChar;
    }

    public boolean isToStart() {
        return toStart;
    }

    public void setToStart(boolean toStart) { this.toStart = toStart; }

    public TreeNode getNode() {
        return node;
    }

    public void setNode(TreeNode node)  {
        this.node = node;
    }

}
