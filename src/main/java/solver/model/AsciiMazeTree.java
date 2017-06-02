package solver.model;

import solver.Constants;

/**
 * Created by will on 5/24/17.
 */
public class AsciiMazeTree {

	private TreeNode head;
    private TreeNode[][] treeArray;

    public AsciiMazeTree(String[] mazeText) {
        //The constructor takes in an ASCII maze in the form of a String[]
        //and converts it to a tree.
        this.head = null;
		this.treeArray = null;
        char[] current;

        if (mazeText != null) {
			//The treeArray allows us to easily determine which nodes are next to each other
			// by positioning nodes in their correct position relative to each other.
			this.treeArray = new TreeNode[mazeText.length][];
			for (int row = 0; row < mazeText.length; row++) {
				current = mazeText[row].toCharArray();
				this.treeArray[row] = new TreeNode[current.length];
				for (int col = 0; col < current.length; col++)	{
					if (Constants.NODE_CHARS.contains(current[col]))	{
						//This position is not a wall or out of bounds, create a node at this position
						this.treeArray[row][col] = new TreeNode();
						this.treeArray[row][col].setValue(current[col]);
						connectNodeUpAndLeft(row, col);
						if (current[col] == Constants.START_CHAR)	{
							//This is the starting node of the maze/tree
							this.head = treeArray[row][col];
						}
					}
				}
			}	//for (int row = 0; row < mazeText.length; row++)
		} //if (mazeText != null)

    }	//public AsciiMazeTree(String[] mazeText)


	public TreeNode getHead() {
		return head;
	}

	private void connectNodeUpAndLeft(int row, int col) {
    	//Link the node above
    	if (nodeExists((row-1), col))	{
    		//This node's up points to the above node
			this.treeArray[row][col].directions.get("up").setNode(this.treeArray[row-1][col]);
			//The above node's down points to this node
			this.treeArray[row-1][col].directions.get("down").setNode(this.treeArray[row][col]);
		}

		//Link the node to the left
		if (nodeExists(row, (col-1)))	{
    		//This node's left points to the previous node
			this.treeArray[row][col].directions.get("left").setNode(this.treeArray[row][col-1]);
			//The previous node's right points to this node
			this.treeArray[row][col-1].directions.get("right").setNode(this.treeArray[row][col]);
		}
	}

	private boolean nodeExists(int i, int k)	{
    	try	{
    		//If this position is null or is out of bounds, return false.  Return true otherwise.
			TreeNode checkNode = this.treeArray[i][k];
			if(checkNode == null)	{
				return false;
			}
			return true;
		} catch (ArrayIndexOutOfBoundsException e)	{
    		return false;
		}
	}

}	//public class AsciiMazeTree
