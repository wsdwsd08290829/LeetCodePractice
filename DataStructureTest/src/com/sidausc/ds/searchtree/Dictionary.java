package com.sidausc.ds.searchtree;

import java.util.Iterator;
import java.util.List;

import com.sidausc.ds.priorityqueue.Entry;

public interface Dictionary<K,V> extends Entry<K,V>{
	//public Entry<K,V> find(K key);
	//public Iterable<List<Entry<K,V>>> findAll(K key);
	//public  insert(K key,V value);
	public Entry<K,V> remove(V value);
	//public Iterator<List<Entry<K,V>>> removeAll(K key);
} 