package com.sidausc.ds.hashtable;

import java.util.*;
public class HashTableMultiMap<K,V> implements MultiMap<K,V> {
  Map<K,LinkedList<Map.Entry<K,V>>> m;  // a map of keys to lists of entries
  int nSize; // the size of this MapDictionary
  /** Default constructor, which uses a HashMap. */
  public HashTableMultiMap() {
    m = new HashMap<K,LinkedList<Map.Entry<K,V>>>(); // default map 
    nSize = 0;
  }
  /** Returns the number of entries in the dictionary. */
  public int size() { return nSize; }
  /** Returns whether the dictionary is empty. */
  public boolean isEmpty() { return nSize == 0; }
  /** Inserts an item into the dictionary.  Returns the newly created
   * entry. */
  public Map.Entry<K,V> put(K key, V value) 
			throws IllegalArgumentException {
    LinkedList<Map.Entry<K,V>> ll;
    if (key == null) throw new IllegalArgumentException();
    if ((ll = m.get(key)) == null) {  // nothing there yet
      ll = new LinkedList<Map.Entry<K,V>>();
      m.put(key,ll);
    }
    Map.Entry<K,V> e = new AbstractMap.SimpleEntry<K,V>(key,value); 
    ll.add(e); // add the new entry to the list for this key
    nSize++;
    return e;
  }



  /** Returns an entry containing the given key, or <tt>null</tt> if
   * no such entry exists. */
  public Map.Entry<K,V> get(K key) 
			throws IllegalArgumentException {
    LinkedList<Map.Entry<K,V>> ll;
    if (key == null) throw new IllegalArgumentException();
    if ((ll = m.get(key)) == null) return null;  // nothing there yet
    return ll.peekFirst();  // the first element is as good as any
  }

  /** Returns an iterator containing all the entries containing the
   * given key, or an empty iterator if no such entries exist. */
  public Iterable<Map.Entry<K,V>> getAll(K key) 
				  throws IllegalArgumentException {
    LinkedList<Map.Entry<K,V>> ll;
    if (key == null) throw new IllegalArgumentException();
    if ((ll = m.get(key)) == null) return null;  // nothing there yet
    return ll;  
  }

  /** Removes and returns the given entry from the dictionary. */
  public Map.Entry<K,V> remove(Map.Entry<K,V> e) 
			throws IllegalArgumentException {
    LinkedList<Map.Entry<K,V>> ll;
    if (e == null) throw new IllegalArgumentException();
    K key = e.getKey();
    ll = m.get(key);
    if (ll == null) throw new IllegalArgumentException(); // no such key in m
    if (ll.remove(e)) {
      nSize--;
      if (ll.isEmpty()) m.remove(key); // remove the empty list in this case
      return e; // e was in ll, so return the removed entry
    }
    else
      throw new IllegalArgumentException(); // e was not in ll
  }

  /** Returns an iterator containing all the entries in the dictionary. */
  public Iterable<Map.Entry<K,V>> entrySet() {
    LinkedList<Map.Entry<K,V>> ll = new LinkedList<Map.Entry<K,V>>(); 
    for (LinkedList<Map.Entry<K,V>> sub : m.values())
      ll.addAll(sub);  // add all the entries in this list to ll
    return ll;
  }
}