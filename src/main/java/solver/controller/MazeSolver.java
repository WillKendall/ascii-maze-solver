package solver.controller;

import solver.Constants;
import solver.model.CorrectPath;
import solver.model.TreeDirection;
import solver.model.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by will on 5/30/17.
 */
public class MazeSolver {
	private char[] solution;
	private TreeNode mazeTreeHead;
	private TreeNode mazeTreeNode;

	public MazeSolver(TreeNode mazeTree) {
		this.mazeTreeHead = mazeTree;
	}

	public CorrectPath findpath() throws Exception {
		//Breadth first search: Mark the closest direction to the beginning in each node.
		//Start at head.up and rotate clockwise through all directions.
		//Each adjoining node's reverse direction's toStart is marked, assuming it does not already have a direction
		//with a marked toStart.  Whenever a node is marked with a toStart = true, put it onto a queue
		//so that its children can be processed. Repeat this process for each of nodes.
		//If a toStart direction has already been marked in this node, do not mark this node again and
		// do not added it to the processing queue.  It has already gone through the queue or is already on it.
		//Each time a node is marked with a toStart direction, check the value of that node and
		//see if it is the finish node.  If so, get the toStart node's reverse direction and write it down.
		//Travel to that toStart node and repeat until back at start node.
		//You now have the correct path from the finish to the start, reverse the order.
		//Reverse it and return it. QED
		boolean foundEnd = false;
		Queue<TreeNode> nextNodeQue = new LinkedList<TreeNode>();
		nextNodeQue.add(this.mazeTreeHead);

		while (!nextNodeQue.isEmpty()) {
			this.mazeTreeNode = nextNodeQue.poll();
			Iterator it = this.mazeTreeNode.directions.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				TreeDirection treeDir = (TreeDirection) pair.getValue();
				TreeNode child = treeDir.getNode();
				if (child != null) {
					if (!ToStartAlreadyMarked(child)) {
						//first time seeing this child node, mark its direction to start
 						switch (pair.getKey().toString()) {
							case Constants.UP:
								child.directions.get(Constants.DOWN).setToStart(true);
								break;
							case Constants.RIGHT:
								child.directions.get(Constants.LEFT).setToStart(true);
								break;
							case Constants.DOWN:
								child.directions.get(Constants.UP).setToStart(true);
								break;
							case Constants.LEFT:
								child.directions.get(Constants.RIGHT).setToStart(true);
								break;
						} //switch (pair.getKey().toString())
						if (child.getValue() == Constants.END_CHAR) {
							//Found the end node.  Break out of the loops.
							foundEnd = true;
							this.mazeTreeNode = child;
							break; //break out of the while loop
						} //if (child.getValue() == Constants.END_CHAR)
						//first time seeing this node, place it on the queue for processing
						nextNodeQue.add(child);
					} //if (!ToStartAlreadyMarked(child))
				} //if (child != null)
			} //while (it.hasNext())

			if (foundEnd)	{
				nextNodeQue.clear();  //this queue is no longer needed, expedite its cleanup
				break;
			}
		} //while (!nextNodeQue.isEmpty())

		if (foundEnd) {
			//Found the end node.  The path to start exists.  Follow it back and write down path.
			LinkedList<String> reversePath = new LinkedList<String>();
			while (this.mazeTreeNode.getValue() != Constants.START_CHAR) {
				Iterator it = this.mazeTreeNode.directions.entrySet().iterator();
				while (it.hasNext()) {
					//Find direction to beginning
					Map.Entry pair = (Map.Entry) it.next();
					TreeDirection treeDir = (TreeDirection) pair.getValue();
					if (treeDir.isToStart()) {
						switch (pair.getKey().toString()) {
							case Constants.UP:
								reversePath.add(Constants.DOWN);
								break;
							case Constants.RIGHT:
								reversePath.add(Constants.LEFT);
								break;
							case Constants.DOWN:
								reversePath.add(Constants.UP);
								break;
							case Constants.LEFT:
								reversePath.add(Constants.RIGHT);
								break;
						} //switch (pair.getKey().toString())
						this.mazeTreeNode = treeDir.getNode();
						break;    //no need to search more directions for this node
					} //if (treeDir.isToStart())
				} //while (it.hasNext())
			} //while (this.mazeTreeNode.getValue() != Constants.START_CHAR)
			return fixPath(reversePath);  //make reversePath go from start to finish
		} else {
			throw new Exception("Invalid maze, no end found.");
		}
	} //public CorrectPath findpath()

	private boolean ToStartAlreadyMarked(TreeNode node) {
		Iterator it = node.directions.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			TreeDirection treeDir = (TreeDirection)pair.getValue();
			if (treeDir.isToStart())	{
				return true;
			}
		}	//while (it.hasNext())
		return false;
	}

	private CorrectPath fixPath(LinkedList<String> reversedPath)	{
		//This routine reverses the order of the path
		CorrectPath path = new CorrectPath();
		while (!reversedPath.isEmpty())	{
			path.add(reversedPath.removeLast());
		}
		return path;
	}
}
