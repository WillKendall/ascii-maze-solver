package solver.model;

/**
 * Created by will on 5/24/17.
 */
public class TreeNode {
    public final TreeDirection left = new TreeDirection('L');
    public final TreeDirection right = new TreeDirection('R');
    public final TreeDirection up = new TreeDirection('U');
    public final TreeDirection down = new TreeDirection('D');

    private Character value = null;

    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

}
