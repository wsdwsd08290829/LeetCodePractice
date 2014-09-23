package com.sidausc.ds.priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.Position;
import com.sidausc.ds.priorityqueue.ArrayListCompleteBinaryTree.BTPos;
import com.sidausc.ds.tree.BinaryTree;
import com.sidausc.ds.tree.EmptyTreeException;
import com.sidausc.ds.tree.LinkedBinaryTree;
import com.sidausc.ds.tree.NonEmptyTreeException;

/** 
  * Realization of a priority queue by means of a heap.  A complete
  * binary tree realized by means of an array list is used to
  * represent the heap.
  */
public class HeapPriorityQueue<K,V> implements PriorityQueue<K,V> {
  protected CompleteBinaryTree<Entry<K,V>> heap;	// underlying heap
  protected CompleteBinaryTree<Entry<K,V>> falseheap;	// underlying heap
  protected Comparator<K> comp;	// comparator for the keys
  /** Inner class for heap entries. */
  protected static class  MyEntry<K,V> implements Entry<K,V> {
    protected K key;
    protected V value;
    public MyEntry(K k, V v) { key = k; value = v; }
    public K getKey() { return key; }
    public V getValue() { return value; }
    public String toString() { return "(" + key  + "," + value + ")"; }
  }
  /** Creates an empty heap with the default comparator */ 
  public HeapPriorityQueue() {  
    heap = new ArrayListCompleteBinaryTree<Entry<K,V>>(); // use an array list
    comp = new DefaultComparator<K>();     // use the default comparator
  }
  /** Creates an empty heap with the given comparator */
  public HeapPriorityQueue(Comparator<K> c) {
    heap = new ArrayListCompleteBinaryTree<Entry<K,V>>();
    comp = c;
  }
  /** use this constructure to build bottom up heap, eg in BottomUpHeap */
  public HeapPriorityQueue(CompleteBinaryTree<Entry<K,V>> cbt) {
	  	heap = new ArrayListCompleteBinaryTree<Entry<K,V>>();
	    falseheap = cbt;
	    comp = new DefaultComparator<K>();     // use the default comparator
}
  public void buildHeap(ArrayListCompleteBinaryTree<Entry<K,V>> cbt) throws InvalidPositionException, BoundaryViolationException{
	  ArrayList<BTPos<Entry<K, V>>> al = cbt.getArrayList();
	  for(int i=al.size()-1; i>0; i--){
		   downHeap(cbt, (Position<Entry<K, V>>) al.get(i));
	  }
	 heap = cbt;
	  
  }
  /** Returns the size of the heap */
  public int size() { return heap.size(); } 
  /** Returns whether the heap is empty */
  public boolean isEmpty() { return heap.size() == 0; }
  /** Returns but does not remove an entry with minimum key */
  public Entry<K,V> min() throws EmptyPriorityQueueException {
    if (isEmpty()) 
      throw new EmptyPriorityQueueException("Priority queue is empty");
    try {
		return heap.root().element();
	} catch (InvalidPositionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (EmptyTreeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
  }
  /** Inserts a key-value pair and returns the entry created */
  public Entry<K,V> insert(K k, V x) throws InvalidKeyException {
    checkKey(k);  // may throw an InvalidKeyException
    Entry<K,V> entry = new MyEntry<K,V>(k,x);
    try {
		upHeap(heap.add(entry));
	} catch (InvalidPositionException | EmptyTreeException
			| BoundaryViolationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return entry;
  }
  /** Removes and returns an entry with minimum key */
  public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
    if (isEmpty()) 
      throw new EmptyPriorityQueueException("Priority queue is empty");
    Entry<K, V> min = null;
	try {
		min = heap.root().element();
	} catch (InvalidPositionException | EmptyTreeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    if (size() == 1)
      heap.remove();
    else {
    	try {
    		heap.replace(heap.root(), heap.remove());
		} catch (InvalidPositionException | EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
		try {
			downHeap(heap.root());
		} catch (InvalidPositionException | BoundaryViolationException
				| EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
    return min;
  }
  /** Determines whether a given key is valid */
  protected void checkKey(K key) throws InvalidKeyException {
    try {
      comp.compare(key,key);
    }
    catch(Exception e) {
      throw new InvalidKeyException("Invalid key");
    }
  }



  /** Performs up-heap bubbling 
 * @throws BoundaryViolationException 
 * @throws EmptyTreeException 
 * @throws InvalidPositionException */
 protected void upHeap(Position<Entry<K,V>> v) throws InvalidPositionException, EmptyTreeException, BoundaryViolationException {
   Position<Entry<K,V>> u;
   while (!heap.isRoot(v)) {
     u = heap.parent(v);
     if (comp.compare(u.element().getKey(), v.element().getKey()) <= 0) break;
     swap(u, v);
     v = u;
   }
 }
 /** Performs down-heap bubbling 
 * @throws BoundaryViolationException 
 * @throws InvalidPositionException */
 protected void downHeap(Position<Entry<K,V>> r) throws InvalidPositionException, BoundaryViolationException {
   while (heap.isInternal(r)) {
     Position<Entry<K,V>> s;		// the position of the smaller child
     if (!heap.hasRight(r))
    	 s = heap.left(r);
     else if (comp.compare(heap.left(r).element().getKey(), 
                           heap.right(r).element().getKey()) <=0)
    	 s = heap.left(r);
     else
    	 s = heap.right(r);
     if (comp.compare(s.element().getKey(), r.element().getKey()) < 0) {
		swap(r, s);
		r = s;
     }
     else 
	break;
   }
 }
 //overload of downHeap
 protected void downHeap(CompleteBinaryTree<Entry<K,V>> heap,Position<Entry<K,V>> r) throws InvalidPositionException, BoundaryViolationException {
	   while (heap.isInternal(r)) {
	     Position<Entry<K,V>> s;		// the position of the smaller child
	     if (!heap.hasRight(r))
	    	 s = heap.left(r);
	     else if (comp.compare(heap.left(r).element().getKey(), 
	                           heap.right(r).element().getKey()) <=0)
	    	 s = heap.left(r);
	     else
	    	 s = heap.right(r);
	     if (comp.compare(s.element().getKey(), r.element().getKey()) < 0) {
			swap(r, s);
			r = s;
	     }
	     else 
		break;
	   }
	 }
 /** Swaps the entries of the two given positions 
 * @throws InvalidPositionException */
 protected void swap(Position<Entry<K,V>> x, Position<Entry<K,V>> y) throws InvalidPositionException {
   Entry<K,V> temp = x.element();
   heap.replace(x, y.element());
   heap.replace(y, temp);
 }
 /** Text visualization for debugging purposes */
 public String toString() {
   return heap.toString();
 }

}