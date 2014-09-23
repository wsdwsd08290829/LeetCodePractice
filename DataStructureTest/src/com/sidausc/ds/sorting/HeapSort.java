package com.sidausc.ds.sorting;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.sidausc.ds.priorityqueue.EmptyPriorityQueueException;
import com.sidausc.ds.priorityqueue.HeapPriorityQueue;
import com.sidausc.ds.priorityqueue.InvalidKeyException;
import com.sidausc.ds.priorityqueue.PriorityQueue;

public class HeapSort {
	public static List<String> heapSort(List<String> l){
		Iterator it = l.iterator();
		PriorityQueue<String, String>  pq = new HeapPriorityQueue<String,String>(
					new Comparator<String>(){
						public int compare(String a, String b){
							return a.compareTo(b);
						}
					}
				);
		for(int i=0;i<l.size();i++){
			try {
				pq.insert(l.get(i), l.get(i));
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0; i<l.size();i++){
			try {
				l.set(i, pq.removeMin().getValue());
			} catch (EmptyPriorityQueueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return l;
	}

	public static void main(String[] args) {
		List<String> l = new LinkedList<String>();
		l.add("c");
		l.add("bb");
		l.add("ba");
		l.add("a");
		System.out.println(HeapSort.heapSort(l)); 
		
		
	}
}
