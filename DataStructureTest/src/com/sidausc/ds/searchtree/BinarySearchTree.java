package com.sidausc.ds.searchtree;

import java.util.Comparator;
import java.util.LinkedList;

import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.NodePositionList;
import com.sidausc.ds.listanditerators.Position;
import com.sidausc.ds.listanditerators.PositionList;
import com.sidausc.ds.priorityqueue.DefaultComparator;
import com.sidausc.ds.priorityqueue.Entry;
import com.sidausc.ds.priorityqueue.InvalidEntryException;
import com.sidausc.ds.priorityqueue.InvalidKeyException;
import com.sidausc.ds.tree.BTPosition;
import com.sidausc.ds.tree.BinaryTree;
import com.sidausc.ds.tree.EmptyTreeException;
import com.sidausc.ds.tree.LinkedBinaryTree;
import com.sidausc.ds.tree.NonEmptyTreeException;

//Realization of a dictionary by means of a binary search tree
public class BinarySearchTree<K,V> 
extends LinkedBinaryTree<Entry<K,V>>{ // implements Dictionary<K,V> 
	protected Comparator<K> C;	// comparator
	protected Position<Entry<K,V>> 
	           actionPos; // insert node or removed node's parent
	protected int numEntries = 0;	// number of entries
	/** Creates a BinarySearchTree with a default comparator. */
	public BinarySearchTree()  { 
		 C = new DefaultComparator<K>(); 
		 try {
			addRoot(null);
		} catch (NonEmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public BinarySearchTree(Comparator<K> c)  { 
		C = c; 
		 try {
			addRoot(null);
		} catch (NonEmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** Nested class for location-aware binary search tree entries */
	protected static class BSTEntry<K,V> implements Entry<K,V> {
	 protected K key;
	 protected V value;
	 protected Position<Entry<K,V>> pos;
	 BSTEntry() { /* default constructor */ }
	 BSTEntry(K k, V v, Position<Entry<K,V>> p) { 
	   key = k; value = v; pos = p;
	 }
	 public K getKey() { return key; }
	 public V getValue() { return value; }
	 public Position<Entry<K,V>> position() { return pos; }
	 @Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[" + key + ", " + value + "]" ;
		}
	}
	/** Extracts the key of the entry at a given node of the tree. 
	 * @throws InvalidPositionException */
	protected K key(Position<Entry<K,V>> position) throws InvalidPositionException  {
	 return position.element().getKey();
	}
	/** Extracts the value of the entry at a given node of the tree. 
	 * @throws InvalidPositionException */  
	protected V value(Position<Entry<K,V>> position) throws InvalidPositionException  { 
	 return position.element().getValue(); 
	}
	/** Extracts the entry at a given node of the tree. 
	 * @throws InvalidPositionException */  
	protected Entry<K,V> entry(Position<Entry<K,V>> position) throws InvalidPositionException  { 
	 return position.element();
	}
	/** Replaces an entry with a new entry (and reset the entry's location) 
	 * @throws InvalidPositionException */
	protected void replaceEntry(Position <Entry<K,V>> pos, Entry<K,V> ent) throws InvalidPositionException {
	 ((BSTEntry<K,V>) ent).pos = pos;
	 replace(pos, ent);
	}
	/** Checks whether a given key is valid. */  
	protected void checkKey(K key) throws InvalidKeyException {
	  if(key == null)  // just a simple test for now
	    throw new InvalidKeyException("null key");
	}
	/** Checks whether a given entry is valid. */
	protected void checkEntry(Entry<K,V> ent) throws InvalidEntryException {
	  if(ent == null || !(ent instanceof BSTEntry))
	    throw new InvalidEntryException("invalid entry");
	}
	/** Auxiliary method for inserting an entry at an external node 
	 * @throws InvalidPositionException 
	 * @throws EmptyTreeException 
	 * @throws NonEmptyTreeException */
	protected Entry<K,V> insertAtExternal(Position<Entry<K,V>> v, Entry<K,V> e) throws InvalidPositionException, NonEmptyTreeException, EmptyTreeException {
	  expandExternal(v,null,null);
	  replace(v, e);
	  numEntries++;
	  return e;
	}
	private void expandExternal(Position<Entry<K, V>> v, Entry<K, V> e1,
			Entry<K, V> e2) throws NonEmptyTreeException, InvalidPositionException, EmptyTreeException {
		LinkedBinaryTree<Entry<K, V>> bt1 = new LinkedBinaryTree<Entry<K,V>>();
		bt1.addRoot(e1);
		LinkedBinaryTree<Entry<K, V>> bt2 = new LinkedBinaryTree<Entry<K,V>>();
		bt2.addRoot(e2);
		this.attach(v, bt1, bt2);
		// TODO Auto-generated method stub
		
	}
	/** Auxiliary method for removing an external node and its parent */
	protected void removeExternal(Position<Entry<K,V>> v) {
	  removeAboveExternal(v);
	  numEntries--;
	}
	private void removeAboveExternal(Position<Entry<K, V>> v) { // w(v,sibv)
		// TODO Auto-generated method stub
		try {
			if(v !=null){
				Position<Entry<K, V>> w = parent(v);
				remove(v);
				remove(w);
				/*if(sibling(v)!=null && parent(v)!=null && sibling(v).element() !=null){
					replaceEntry(parent(v),sibling(v).element());
				}*/
			}
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/** Auxiliary method used by find, insert, and remove. 
	 * @throws BoundaryViolationException 
	 * @throws InvalidPositionException */
	protected Position<Entry<K,V>> treeSearch(K key, Position<Entry<K,V>> pos) throws InvalidPositionException, BoundaryViolationException {
	  if (isExternal(pos)) return pos; // key not found; return external node
	  else {
	    K curKey = key(pos);
	    int comp = C.compare(key, curKey);
	    if (comp < 0) 
	      return treeSearch(key, left(pos));	// search left subtree
	    else if (comp > 0)
	      return treeSearch(key, right(pos));	// search right subtree
	    return pos;		// return internal node where key is found
	  }
	}
	// Adds to L all entries in the subtree rooted at v having keys equal to k
	protected void addAll(PositionList<Entry<K,V>> L, 
			      Position<Entry<K,V>> v, K k) throws InvalidPositionException, BoundaryViolationException {
	  if (isExternal(v)) return;
	  Position<Entry<K,V>> pos = treeSearch(k, v);
	  if (!isExternal(pos))  {  // we found an entry with key equal to k 
	    addAll(L, left(pos), k);
	    L.addLast(pos.element()); 	// add entries in inorder
	    addAll(L, right(pos), k);
	  } // this recursive algorithm is simple, but it's not the fastest
	}
	
	// methods of the dictionary ADT
	public int size() { return numEntries; }
	public boolean isEmpty() { return size() == 0; }
	public Entry<K,V> find(K key) throws InvalidKeyException, InvalidPositionException, BoundaryViolationException, EmptyTreeException {
	  checkKey(key);		// may throw an InvalidKeyException
	  Position<Entry<K,V>> curPos = treeSearch(key, root());
	  actionPos = curPos;		// node where the search ended
	  if (isInternal(curPos)) return entry(curPos);
	  return null;
	}
	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString();
		}
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException, InvalidPositionException, BoundaryViolationException, EmptyTreeException {
	  checkKey(key);		// may throw an InvalidKeyException
	  PositionList<Entry<K,V>> L = new NodePositionList<Entry<K,V>>();
	  addAll(L, root(), key);
	  return L;
	}
	public Entry<K,V> insert(K k, V x) throws InvalidKeyException, InvalidPositionException, BoundaryViolationException, EmptyTreeException, NonEmptyTreeException {
	  checkKey(k);	// may throw an InvalidKeyException
	  Position<Entry<K,V>> insPos = treeSearch(k, root());
	  while (!isExternal(insPos))  // iterative search for insertion position
	    insPos = treeSearch(k, left(insPos));
	  actionPos = insPos;	// node where the new entry is being inserted
	  return insertAtExternal(insPos, new BSTEntry<K,V>(k, x, insPos));
	}
	public Entry<K,V> remove(Entry<K,V> ent) throws InvalidEntryException, InvalidPositionException, BoundaryViolationException  {
	  checkEntry(ent);            // may throw an InvalidEntryException
	  Position<Entry<K,V>> remPos = ((BSTEntry<K,V>) ent).position(); 
	  Entry<K,V> toReturn = entry(remPos);	// entry to be returned
	  if (isExternal(left(remPos))) remPos = left(remPos);  // left easy case
	  else if (isExternal(right(remPos))) remPos = right(remPos); // right easy case
	  else {			//  entry is at a node with internal children
	    Position<Entry<K,V>> swapPos = remPos;	// find node for moving entry
	    remPos = right(swapPos);
	    do
		remPos = left(remPos);
	    while (isInternal(remPos));
	    replaceEntry(swapPos, (Entry<K,V>) parent(remPos).element());
	  }
	  actionPos = sibling(remPos);	// sibling of the leaf to be removed
	  removeExternal(remPos);
	  return toReturn;
	}
	public static void main(String[] args) {
		BinarySearchTree<Integer,String> bst = new BinarySearchTree<Integer,String>();
		try {
			
			bst.insert(5,"e");
			bst.insert(1,"a");
			bst.insert(2,"b");
			bst.insert(3,"c");
			bst.insert(4,"d");
			StringBuilder s = new StringBuilder();
			System.out.println(bst.treeStructure((BTPosition<Entry<Integer, String>>)bst.root(), s));
			
			try {
				bst.remove(bst.root().element());
				s.delete(0,s.length());
				System.out.println(bst.treeStructure((BTPosition<Entry<Integer, String>>) bst.root(), s));
				
			} catch (InvalidEntryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NonEmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} 	// entries() method is omitted here