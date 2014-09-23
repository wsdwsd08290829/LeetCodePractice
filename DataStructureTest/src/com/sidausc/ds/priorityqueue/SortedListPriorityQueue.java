package com.sidausc.ds.priorityqueue;

import java.util.Comparator;
import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.EmptyListException;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.NodePositionList;
import com.sidausc.ds.listanditerators.Position;
import com.sidausc.ds.listanditerators.PositionList;
/** Implementation of a priority queue by means of a sorted node list. */
public class SortedListPriorityQueue<K,V> implements PriorityQueue<K,V> {
  protected PositionList<Entry<K,V>> entries;
  protected Comparator<K> c;
  protected Position<Entry<K,V>> actionPos; // variable used by subclasses
  /** Inner class for entries */
  protected static class MyEntry<K,V> implements Entry<K,V> {
    protected K k; // key
    protected V v; // value
    public MyEntry(K key, V value) {
      k = key;
      v = value;
    }
    // methods of the Entry interface
    public K getKey() { return k; }
    public V getValue() { return v; }
  }
  /** Creates the priority queue with the default comparator. */
  public SortedListPriorityQueue () {
    entries = new NodePositionList<Entry<K,V>>();
    c = new DefaultComparator<K>();
  }
  /** Creates the priority queue with the given comparator. */
  public SortedListPriorityQueue (Comparator<K> comp) {
    entries = new NodePositionList<Entry<K,V>>();
    c = comp;
  }



  /** Returns but does not remove an entry with minimum key. */
  public Entry<K,V> min () throws EmptyPriorityQueueException {
    if (entries.isEmpty())
      throw new EmptyPriorityQueueException("priority queue is empty");
	else
		try {
			return entries.first().element();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
  }
  /** Inserts a key-value pair and return the entry created. */
  public Entry<K,V> insert (K k, V v) throws InvalidKeyException {
    checkKey(k); // auxiliary key-checking method (could throw exception)
    Entry<K,V> entry = new MyEntry<K,V>(k, v);
    try {
		insertEntry(entry);
	} catch (EmptyListException | InvalidPositionException
			| BoundaryViolationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}               // auxiliary insertion method
    return entry;
  }
  protected K checkKey(K k) throws InvalidKeyException {
	// TODO Auto-generated method stub
	if(k instanceof Comparable)return k;
	else throw new InvalidKeyException();
}
/** Auxiliary method used for insertion. 
 * @throws EmptyListException 
 * @throws BoundaryViolationException 
 * @throws InvalidPositionException */
  protected void insertEntry(Entry<K,V> e) throws EmptyListException, InvalidPositionException, BoundaryViolationException {
    if (entries.isEmpty()) {
      entries.addFirst(e);            // insert into empty list
      actionPos = entries.first();    // insertion position 
    }
    else if (c.compare(e.getKey(), entries.last().element().getKey()) > 0) {
      entries.addLast(e);             // insert at the end of the list
      actionPos = entries.last();     // insertion position
    }
    else {
      Position<Entry<K,V>> curr = entries.first();
      while (c.compare(e.getKey(), curr.element().getKey())> 0) {
	curr = entries.next(curr);    // advance toward insertion position
      }
      entries.addBefore(curr, e); 
      actionPos = entries.prev(curr); // insertion position
    }
  }
  /** Removes and returns an entry with minimum key. */
  public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
    if (entries.isEmpty())
      throw new EmptyPriorityQueueException("priority queue is empty");
	else
		try {
			return entries.remove(entries.first());
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
  }
@Override
public int size() {
	// TODO Auto-generated method stub
	return entries.size();
}
@Override
public boolean isEmpty() {
	// TODO Auto-generated method stub
	return size()==0;
}
}