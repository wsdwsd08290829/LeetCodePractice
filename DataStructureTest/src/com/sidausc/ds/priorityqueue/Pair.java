package com.sidausc.ds.priorityqueue;




public class Pair<K, V> implements Entry<K,V>, Cloneable{
	  K key;
	  V value;
	  public Pair(K k, V v) {
		    key = k;
		    value = v;
	 }
	  public void set(K k, V v) {
	    key = k;
	    value = v;
	  }

	  public K getKey() { return key; }
	  public V getValue() { return value; }
	  public String toString() {
	    return "[" + getKey() + ", " + getValue() + "]";
	  }
	  public Object clone() throws CloneNotSupportedException{
		  return super.clone();
	  }

	}
