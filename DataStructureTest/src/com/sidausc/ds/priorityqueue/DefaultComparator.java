package com.sidausc.ds.priorityqueue;

import java.util.Comparator;

/** Comparator based on the natural ordering
 */
public class DefaultComparator<E> implements Comparator<E> {
 /** Compares two given elements
   */
 public int compare(E a, E b) throws ClassCastException { 
   return ((Comparable<E>) a).compareTo(b);
 }
}