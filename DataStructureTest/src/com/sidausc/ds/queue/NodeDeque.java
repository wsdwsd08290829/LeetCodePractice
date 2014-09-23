package com.sidausc.ds.queue;

import com.sidausc.ds.linkedlist.DLNode;


public class NodeDeque<E> implements Deque<E> {
  protected DLNode<E> header, trailer;  // sentinels
  protected int size;    // number of elements
  public NodeDeque() {  // initialize an empty deque
    header = new DLNode<E>();
    trailer = new DLNode<E>();
    header.setNext(trailer);  // make header point to trailer
    trailer.setPrev(header);  // make trailer point to header
    size = 0;
  }
  public int size() {   
	    return size;
	  }
  public boolean isEmpty() {    
	    if (size == 0)
	      return true;
	    return false;
	  }

  public E getFirst() throws EmptyDequeException {  
    if (isEmpty())
      throw new EmptyDequeException("Deque is empty.");
    return header.getNext().getElement();
  }
@Override
public E getLast() throws EmptyDequeException {
	 if (isEmpty())
	      throw new EmptyDequeException("Deque is empty.");
	    return trailer.getNext().getElement();
}
@Override
public void addFirst(E o) {  
    DLNode<E> second = header.getNext();
    DLNode<E> first = new DLNode<E>(o, header, second);
    second.setPrev(first);
    header.setNext(first);
    size++;
  }
@Override
public void addLast(E o) {
	 DLNode<E> secondToLast = header.getNext();
	    DLNode<E> last = new DLNode<E>(o, secondToLast, trailer);
	    secondToLast.setNext(last);
	    trailer.setPrev(last);
	    size++;
	
}
@Override
public E removeFirst() throws EmptyDequeException {
	if (isEmpty())
	    throw new EmptyDequeException("Deque is empty.");
	  DLNode<E> first = header.getNext();
	  E o = first.getElement();
	  DLNode<E> second = first.getNext();
	  header.setNext(second);
	  second.setPrev(header);
	  size--; 
	  return o;
}
@Override
	public E removeLast() throws EmptyDequeException {
	  if (isEmpty())
	    throw new EmptyDequeException("Deque is empty.");
	  DLNode<E> last = trailer.getPrev();
	  E o = last.getElement();
	  DLNode<E> secondtolast = last.getPrev();
	  trailer.setPrev(secondtolast);
	  secondtolast.setNext(trailer);
	  size--; 
	  return o;
	}
}