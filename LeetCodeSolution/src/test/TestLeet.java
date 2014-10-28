package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import tree.TreeNode;
import leet.*;

public class TestLeet {
	@Test
	public void testBTLevelOrderTraversalSeralizedOJ() {
		TreeNode root = new TreeNode(1);
		TreeNode l11 = new TreeNode(2);
		TreeNode l12 = new TreeNode(3);
		TreeNode l21 = new TreeNode(4);
		TreeNode l22 = new TreeNode(5);
		root.left = l11;
		root.right = l12;
		l11.left = l21;
		l11.right = l22;
		System.out.println(BTLevelOrderTraversalSerializedOJ
				.levelOrderBottom(root));
	}

	@Test
	public void testNumberOfDistinctSubsequence() {
		assertEquals(NumOfDistinctSubsequence.numDistincts("aa", "aaa"), 0);
		assertEquals(NumOfDistinctSubsequence.numDistincts("aaa", "aa"), 3);
	}

	@Test
	public void testPascalTrangle() {
		assertEquals(PascalTrangle.factorial(4), 24);
		assertEquals(PascalTrangle.combination(4, 3), 4);
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(1);
		al.add(1);
		assertEquals(PascalTrangle.pascalTriangleRow(1), al);
	}
}
