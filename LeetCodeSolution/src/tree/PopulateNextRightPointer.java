package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL; !!!You may only use
 * constant extra space.
 * 
 * @author sidawang
 * 
 */
public class PopulateNextRightPointer {
	/********* problem1: perfect binary tree **********/
	// bfs, queue to save level
	// NOT CONSTANT EXTRA SPACE(assume that it is a perfect binary tree)
	// works for both problem1 and problem1
	public void connect(TreeLinkNode root) {
		if (root == null)
			return;
		Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
		q.add(root);
		while (!q.isEmpty()) {
			Queue<TreeLinkNode> tempQ = new LinkedList<TreeLinkNode>();
			while (!q.isEmpty()) {
				TreeLinkNode linkNode = q.remove();
				linkNode.next = q.peek();
				if (linkNode.left != null)
					tempQ.add(linkNode.left);
				if (linkNode.right != null)
					tempQ.add(linkNode.right);
			}
			q = tempQ;
		}
	}

	// only works for problem1
	// see each level as list( similar to queue)
	public void connect11(TreeLinkNode root) {
		if (root == null)
			return;
		root.next = null;
		// root is visiting level(already fixed), newLevelRoot = next level to
		// be fixed(next link)
		while (root != null) {
			TreeLinkNode newLevelRoot = root.left;
			// visit previous list, fix current level list
			while (root != null) {
				TreeLinkNode nextNode = root.next;
				// each node in previous list, left->right, right-> next left;
				root.left.next = root.right;
				if (nextNode != null) {
					root.right.next = nextNode.left;
				} else { // root is last of this level
					root.right.next = null;
				}
				root = nextNode;
			}
			root = newLevelRoot;
		}
	}

	/********* problem2: any binary tree ***********/
	// works for both problem1 and 2
	public void connect21(TreeLinkNode root) {
		if (root == null)
			return;
		root.next = null;
		// root is visiting level(already fixed), newLevelRoot = next level to
		// be fixed(next link)
		while (root != null) {
			// add prev and next Node, check left first then right
			TreeLinkNode newLevelRoot = null;
			TreeLinkNode prev = null;
			// visit previous list, fix current level list
			while (root != null) {
				TreeLinkNode nextNode = root.next;
				// each node in previous list, visit left for prev and next
				// visit right for prev and next
				// set newLevleRoot
				if (root.left != null) {
					if (prev == null) {
						prev = root.left;
					} else {
						prev.next = root.left;
						prev = prev.next;
					}
					if (newLevelRoot == null) {
						newLevelRoot = root.left;
					}
				}
				if (root.right != null) {
					if (prev == null) {
						prev = root.right;
					} else {
						prev.next = root.right;
						prev = prev.next;
					}
					if (newLevelRoot == null) {
						newLevelRoot = root.right;
					}
				}
				root = nextNode;
				// fix last node
				if (root == null && prev != null) {
					prev.next = null;
				}
			} // finish visiting prev list
			root = newLevelRoot;
		}// finish all level;
	} // end method

	public static void main(String[] args) {

	}
}
