package com.sidausc.ds.priorityqueue;

import java.util.Comparator;

import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.EmptyListException;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.Position;

/** Implementation of an adaptable priority queue by means of a sorted list. */
public class SortedListAdaptablePriorityQueue<K,V>
    extends SortedListPriorityQueue<K,V> {
  /** Creates the priority queue with the default comparator */
  public SortedListAdaptablePriorityQueue() { 
    super();
  }
  /** Creates the priority queue with the given comparator */
  public SortedListAdaptablePriorityQueue(Comparator<K> comp) { 
    super(comp);
  }
  /** Inserts a key-value pair and returns the entry created */
  public Entry<K,V> insert (K k, V v) throws InvalidKeyException {
    checkKey(k);
    LocationAwareEntry<K,V> entry = new LocationAwareEntry<K,V>(k,v);
    try {
		insertEntry(entry);
	} catch (EmptyListException | InvalidPositionException
			| BoundaryViolationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    entry.setLocation(actionPos);	// position of the new entry
    return entry;
  }
  /** Removes and returns the given entry 
 * @throws InvalidPositionException 
 * @throws InvalidEntryException */
  public Entry<K,V> remove(Entry<K,V> entry) throws InvalidPositionException, InvalidEntryException {
    checkEntry(entry);
    LocationAwareEntry<K,V> e = (LocationAwareEntry<K,V>) entry;
    Position<Entry<K,V>> p = e.location();
    entries.remove(p);
    e.setLocation(null);
    return e;
  }
  /** Replaces the key of the given entry 
 * @throws BoundaryViolationException 
 * @throws InvalidPositionException 
 * @throws EmptyListException 
 * @throws InvalidEntryException 
 * @throws InvalidKeyException */
  public K replaceKey(Entry<K,V> entry, K k) throws EmptyListException, InvalidPositionException, BoundaryViolationException, InvalidEntryException, InvalidKeyException {
    checkKey(k);
    checkEntry(entry);
    LocationAwareEntry<K,V> e = (LocationAwareEntry<K,V>) remove(entry);
    K oldKey = e.setKey(k);
    insertEntry(e);
    e.setLocation(actionPos); // position of new entry
    return oldKey;
  }
  /** Replaces the value of the given entry 
 * @throws InvalidEntryException */
  public V replaceValue(Entry<K,V> e, V value) throws InvalidEntryException {
    checkEntry(e);
    V oldValue = ((LocationAwareEntry<K,V>) e).setValue(value);
    return oldValue;
  }
  /** Determines whether a given entry is valid */
  protected void checkEntry(Entry ent) throws InvalidEntryException {
    if(ent == null || !(ent instanceof LocationAwareEntry))
      throw new InvalidEntryException("invalid entry");
  }
  /** Inner class for a location-aware entry */
  protected static class LocationAwareEntry<K,V>
    extends MyEntry<K,V> implements Entry<K,V> {
    /** Position where the entry is stored. */
    private Position<Entry<K,V>> loc;
    public LocationAwareEntry(K key, V value) {
      super(key, value);
    }
    public LocationAwareEntry(K key, V value, Position<Entry<K,V>> pos) {
      super(key, value);
      loc = pos;
    }
    protected Position<Entry<K,V>> location() {
      return loc;
    }
    protected Position<Entry<K,V>> setLocation(Position<Entry<K,V>> pos) {
      Position<Entry<K,V>> oldPosition = location();
      loc = pos;
      return oldPosition;
    }
    protected K setKey(K key) {
      K oldKey = getKey();
      k = key;
      return oldKey;
    }
    protected V setValue(V value) {
      V oldValue = getValue();
      v = value;
      return oldValue;
    }
  }
}
