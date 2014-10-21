package com.sidausc.ds.priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;


public class IndexedPriorityQueue<K extends Comparable<K>, V> {
	int capacity;
	int size;
	ArrayList<Entry<K, V>> pq;
	Comparator<K> comparator;
	public static class Entry<K, V> {
		K key;
		V value;
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		Entry(K k, V v){
			key = k;
			value = v;
		}
		protected Entry<K,V> clone() {
			return new Entry<K, V>(key, value);
		}
		@Override
		public String toString() {
			return "[" + key + ", " + value + "]" ;
		}
	}
	public int size(){return size;}
	@Override
	public String toString(){
		String s = "";
		for(Entry<K, V> e: pq){
			if(e==null)continue;
			s+= e.toString()+ ", ";
		}
		return s;
	}
	public IndexedPriorityQueue(int c) {
		capacity = c;
		pq = new ArrayList<Entry<K,V>>();
		pq.add(null);  //index 0 is empty;
	}
	public IndexedPriorityQueue(int c, Comparator<K> comp) {
		this(c);
		comparator = comp;
		
	}
	public IndexedPriorityQueue(){
		this(1);
		comparator = new Comparator<K>(){
			@Override
			public int compare(K o1, K o2) {
				// TODO Auto-generated method stub
				return ((Comparable<K>)o1).compareTo(o2);
			}  ///????????????????
		};
	}
	public void insert(K k, V v){
		//if(contains(k))throw new RuntimeException("Key already exists");
		pq.add(size+1, new Entry<K, V>(k, v));
		size++;
		swim(size);
	}
	public Entry<K,V> remove(){
		Entry<K, V> temp = pq.get(1).clone();

		pq.set(1, pq.get(size).clone());
		pq.set(size, null);

		size--;
		sink(1);
		
		return temp;
	}
	public void changeKey(K k, V v){
		
	}
	public void swim(int i){
		
		Entry<K,V> e = pq.get(i);
		int end = size;
		while(end/2>0){//till reach root
			Entry<K, V> parent = pq.get(end/2);
			if(e.key.compareTo(parent.key) < 0){
				//change with parent
				Entry<K,V> temp = pq.get(end/2).clone();
				pq.set(end/2, e);
				pq.set(end, temp);
				end = end/2;
			}else break;
		}
	}
	public void sink(int i){
		int left = 2*i;
		int right = 2*i+1;
		Entry<K, V> min;
		int minside ;
		while(right<=size){
			System.out.println(left + " " + right);
			min = pq.get(left).key.compareTo(pq.get(right).key)>0?pq.get(right):pq.get(left);
			minside  =  pq.get(left).key.compareTo(pq.get(right).key)>0?right:left;
			Entry<K,V> e = pq.get(i);
			if(e.key.compareTo(min.key)>0 ){
				Entry<K,V> temp = pq.get(minside).clone();
				pq.set(minside, pq.get(i));
				pq.set(i, temp);
				i=minside;
				left = 2*i;
				right = 2*i+1;
			}else break;
			
		}
		// possible miss one left not comparing eg 4, 5; left = 2, right = 3
		if(right==size+1){
			//System.out.println("save");
			if( pq.get(left).key.compareTo(pq.get(i).key)<0){
				min = pq.get(left);
				minside= left;
				Entry<K,V> temp1 = pq.get(minside).clone();
				pq.set(minside, pq.get(i));
				pq.set(i, temp1);
				
			}	//if no here the test result not correct
		}
		

	}

	public boolean contains(K k){
		for(Entry<K, V> entry: pq){
			if(entry == null)continue;
			if(entry.key.equals(k))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		IndexedPriorityQueue<Integer, String> ipq = new IndexedPriorityQueue<Integer, String>(10);
		ipq.insert(3,"c");
		ipq.insert(2,"b");

		ipq.insert(4, "d");
		System.out.println(ipq);
		System.out.println(ipq.remove().value);
		System.out.println(ipq);
		System.out.println(ipq.remove().value);
		System.out.println(ipq);
	//	System.out.println(ipq.remove().value);
		
	}
	
	//assuming no duplicate Keys
	public void changeKey(K originalKey, K newKey,
		V newValue) {
		for(int i=1;i<=size;i++){
			if(pq.get(i).key.equals(originalKey)){
				pq.set(i, new Entry(newKey, newValue));
				adjust(i, originalKey, newKey);
				
			}
		}
	}

	private void adjust(int i, K oriKey, K newKey) {
		if(oriKey.compareTo(newKey) < 0 ){ sink(i); }
		else swim(i);
	}
}
