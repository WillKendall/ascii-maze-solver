package solver.model;

import solver.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by will on 5/24/17.
 */
public class TreeNode {
//    public final TreeDirection left = new TreeDirection('L');
//    public final TreeDirection right = new TreeDirection('R');
//    public final TreeDirection up = new TreeDirection('U');
//    public final TreeDirection down = new TreeDirection('D');
    public final Map<String, TreeDirection> directions = new HashMap<>();
    private Character value = null;

    public TreeNode() {
        directions.put(Constants.UP, new TreeDirection('U'));
        directions.put(Constants.RIGHT, new TreeDirection('R'));
        directions.put(Constants.DOWN, new TreeDirection('D'));
        directions.put(Constants.LEFT, new TreeDirection('L'));
    }

    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

}
