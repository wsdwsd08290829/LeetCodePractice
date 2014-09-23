package com.sidausc.ds.priorityqueue;



public class MyTestPriorityQueue {
	public static void main(String[] args) throws InvalidKeyException, EmptyPriorityQueueException {
		Pair<Integer,String> entry1 = new Pair<Integer,String>(1, "1");
		Pair<Integer,String> entry2 = new Pair<Integer,String>(2, "2");
		CompleteBinaryTree<Entry<Integer,String>> cbt = new ArrayListCompleteBinaryTree<Entry<Integer,String>>();
		cbt.add(entry1);
		//entry1.set(2,"2");
		try {
			entry2 = (Pair<Integer, String>) entry1.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entry2.set(3, "3");
		cbt.add(entry2);
		System.out.println("cbt" + cbt);
		
		PriorityQueue<Integer, String> hpq = new HeapPriorityQueue<Integer,String>();
		hpq.insert(7, "7");
		hpq.insert(6, "6");
		hpq.insert(5, "5");
		hpq.insert(2, "2");
		hpq.insert(1, "1");
		hpq.insert(3, "3");
		System.out.println(hpq.min() instanceof Entry);
		System.out.println(hpq.removeMin());
		System.out.println(hpq.min());
		System.out.println(hpq.removeMin());
		System.out.println(hpq.min());
		System.out.println(hpq.removeMin());
		
		
		System.out.println("hpq" + hpq);
	}
}
