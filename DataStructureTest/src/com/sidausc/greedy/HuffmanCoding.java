package com.sidausc.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCoding {
	/**
	 * steps:
	 * 1.  create inner class representing HuffmanNode(HN) char, freq,  left, right for each char and freq
	 * 2.  add HN to priorityQueue(heap, tree)
	 * 3.  create New HN(tree) by removing two min(as left&right) from qp. insert back until only one node left.
	 * 4.  create arr, traverse tree from top, left child set arr 0, right set 1. 
	 * 5.  when reach leaf, print arr as code for the char at that leaf
	 * C++
	 * http://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding-set-2/
	 */
	static class HuffmanNode{
		char ch;
		int freq; 
		HuffmanNode left;
		HuffmanNode right;
		public HuffmanNode(char ch, int freq) {
			super();
			this.ch = ch;
			this.freq = freq;
		}
	} 
  	static class HuffmanNodeComparator implements Comparator<HuffmanNode>{
		@Override
		public int compare(HuffmanNode o1, HuffmanNode o2) {
			return o1.freq-o2.freq;
		}
	}
	public static void runHuffmanEngine(char[] chArr, int[] freqArr){
		PriorityQueue<HuffmanNode> pq = createPQ(chArr, freqArr); //step2
		HuffmanNode huffmanTree = createTree(pq); //step3
		int[] arr = new int[chArr.length];
		parseTree(huffmanTree, arr, 0);
	}
	private static void parseTree(HuffmanNode huffmanTree, int[] arr, int index) {
		if(huffmanTree.left != null){
			arr[index]  = 0;
			parseTree(huffmanTree.left, arr, index+1);
		}
		if(huffmanTree.right != null){
			arr[index] = 1;
			parseTree(huffmanTree.right, arr, index+1);
		}
		if(huffmanTree.left == null && huffmanTree.right == null){
			System.out.print(huffmanTree.ch);
			for(int i=0;i<index;i++){
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
	private static PriorityQueue<HuffmanNode> createPQ(char[] chArr,
			int[] freqArr) {
		PriorityQueue<HuffmanNode> pq = new  PriorityQueue<HuffmanNode>(chArr.length, new HuffmanNodeComparator());
		for(int i=0;i<chArr.length;i++){
			pq.add(new HuffmanNode(chArr[i], freqArr[i]));
		}
		return pq;
	}
	private static HuffmanNode createTree(PriorityQueue<HuffmanNode> pq) {
		while(pq.size()>1){
			HuffmanNode left = pq.remove();
			HuffmanNode right = pq.remove();
			HuffmanNode newNode = new HuffmanNode('$', left.freq+right.freq);
			newNode.left = left;
			newNode.right = right;
			pq.add(newNode);
		}
		return pq.remove();
	}
	public static void main(String[] args) {
		char[] chArr = {'a','b','c','d','e','f'};
		int[] freqArr = {5,9, 12, 13, 16, 45};
		runHuffmanEngine(chArr, freqArr);
		
	}
}
