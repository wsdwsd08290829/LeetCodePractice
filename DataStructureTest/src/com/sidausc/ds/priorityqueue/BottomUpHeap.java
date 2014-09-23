package com.sidausc.ds.priorityqueue;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.Position;
import com.sidausc.ds.tree.BTPosition;
import com.sidausc.ds.tree.BinaryTree;
import com.sidausc.ds.tree.EmptyTreeException;
import com.sidausc.ds.tree.LinkedBinaryTree;
import com.sidausc.ds.tree.NonEmptyTreeException;

public class BottomUpHeap<V> extends LinkedBinaryTree<V>{
	//BinaryTree<V> bt = new LinkedBinaryTree<V>();
	Comparator<V> comp = new DefaultComparator<V>();
	public BottomUpHeap(){
	}
	public BottomUpHeap(Comparator c){
		comp=c;
	}
	
	//assuming from last one is correct heap, complete heap by fix previous node one by one
	public static HeapPriorityQueue<String,String> bottomUpHeapNew(String[] arr) throws InvalidPositionException, BoundaryViolationException{
		CompleteBinaryTree<Entry<String,String>> cbt = new ArrayListCompleteBinaryTree<Entry<String,String>>();
		for(String s: arr){
			cbt.add(new Pair<String,String>(s,s));
			
		}
		System.out.println(cbt);
		HeapPriorityQueue<String,String> hpq = new HeapPriorityQueue<String, String>(cbt);
		hpq.buildHeap((ArrayListCompleteBinaryTree<Entry<String, String>>) cbt);
		return hpq;
		
	}
	 public BinaryTree<V> bottomUpHeap(List<V> l) throws NonEmptyTreeException, InvalidPositionException, EmptyTreeException, BoundaryViolationException{
		 int n = l.size();
		 
		 V root = l.remove(0);
		 BinaryTree<V> bt = new LinkedBinaryTree<V>();
		 ((LinkedBinaryTree<V>) bt).addRoot(root);
		 if(l.size()<=0){return bt;}
		 
		 List<V> l1 = new LinkedList<V>();
		 List<V> l2 = new LinkedList<V>();
		 for(int i = 0; i<= (l.size()-1)/2;i++){
			 l1.add(l.get(i));
			 System.out.println("l1"+l.get(i));
		 }
		 for(int i = (l.size()-1)/2+1; i<l.size();i++){
			 l2.add(l.get(i));
			 System.out.println("l2"+l.get(i));
		 }
		 
		 BinaryTree<V> left = bottomUpHeap(l1);
		 BinaryTree<V> right = bottomUpHeap(l2);
		 ((LinkedBinaryTree<V>)bt).attach(bt.root(), left, right);
		 myDownHeap(bt, (BTPosition<V>) bt.root());
		return bt;
	 }
	private void myDownHeap(BinaryTree<V> bt, BTPosition<V> r) throws InvalidPositionException, BoundaryViolationException {
		
		 while (bt.isInternal(r)) {
		     BTPosition<V> s;		// the position of the smaller child
		     if (!bt.hasRight(r))
		    	 s = (BTPosition<V>) bt.left(r);
		     else if (comp.compare(bt.left(r).element(), 
		    		 bt.right(r).element()) <=0)
		    	 s = (BTPosition<V>) bt.left(r);
		     else
		    	 s = (BTPosition<V>) bt.right(r);
		     if (comp.compare(s.element(), r.element()) < 0) {
				swap(r, s);
		    	 
				r = s;
		     }
		     else 
			break;
		   }
	}
	private void swap(BTPosition<V> r, BTPosition<V> s) throws InvalidPositionException {
		// TODO Auto-generated method stub
		V temp = r.element();
		r.setElement(s.element());
		s.setElement(temp);
	}
	public static void main(String[] args) {
		List<Integer> l = new LinkedList<Integer>();  //has to have 2^n-1 members
		l.add(7);
		l.add(6);
		l.add(5);
		l.add(4);
		l.add(3);
		l.add(1);
		l.add(2);
		/*l.add("D");
		l.add("E");
		l.add("F");
		l.add("G");*/
		BottomUpHeap<Integer> bt = new BottomUpHeap<Integer>();
		try {
			StringBuilder sb = new StringBuilder();
			LinkedBinaryTree<Integer> lbt = (LinkedBinaryTree<Integer>)bt.bottomUpHeap(l);
			System.out.println(lbt.root().element());
			System.out.println(lbt.treeStructure((BTPosition<Integer>) lbt.root(), sb));
		} catch (NonEmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(BottomUpHeap.bottomUpHeapNew(new String[]{"c","b","a"}));
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
