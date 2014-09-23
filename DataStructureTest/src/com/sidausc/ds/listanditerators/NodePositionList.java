package com.sidausc.ds.listanditerators;

import java.util.Iterator;
import java.util.LinkedList;



public class NodePositionList<E> implements PositionList<E> {
	protected int numElts;            	// Number of elements in the list
	protected DNode<E> header, trailer;	// Special sentinels

	  /** Constructor that creates an empty list; O(1) time */
	  public NodePositionList() {
	    numElts = 0;
	    header = new DNode<E>(null, null, null);	// create header
	    trailer = new DNode<E>(header, null, null);	// create trailer
	    header.setNext(trailer);	// make header and trailer point to each other
	  }
	  /** Checks if position is valid for this list and converts it to
	    *  DNode if it is valid; O(1) time */
	  protected DNode<E> checkPosition(Position<E> p)
	    throws InvalidPositionException {
	    if (p == null)
	      throw new InvalidPositionException
		("Null position passed to NodeList");
	    if (p == header)
		throw new InvalidPositionException
		  ("The header node is not a valid position");
	    if (p == trailer)
		throw new InvalidPositionException
		  ("The trailer node is not a valid position");
	    try {
	      DNode<E> temp = (DNode<E>) p;
	      if ((temp.getPrev() == null) || (temp.getNext() == null))
		throw new InvalidPositionException("Position does not belong to a valid NodeList");
	      return temp;
	    } catch (ClassCastException e) {
	      throw new InvalidPositionException
		("Position is of wrong type for this list");
	    }
	  }

	  /** Returns the number of elements in the list;  O(1) time */
	  public int size() { return numElts; }
	  /** Returns whether the list is empty;  O(1) time  */
	  public boolean isEmpty() { return (numElts == 0); }
	  /** Returns the first position in the list; O(1) time */
	  public Position<E> first() throws EmptyListException {
	    if (isEmpty())
	      throw new EmptyListException("List is empty");
	    return header.getNext();
	  }
	  /** Returns the position before the given one; O(1) time */
	  public Position<E> prev(Position<E> p)
	      throws InvalidPositionException, BoundaryViolationException {
	    DNode<E> v = checkPosition(p);
	    DNode<E> prev = v.getPrev();
	    if (prev == header)
	      throw new BoundaryViolationException
		("Cannot advance past the beginning of the list");
	    return prev;
	  }
	  /** Insert the given element before the given position, returning
	    * the new position; O(1) time  */
	  public void addBefore(Position<E> p, E element) 
	      throws InvalidPositionException {			// 
	    DNode<E> v = checkPosition(p);
	    numElts++;
	    DNode<E> newNode = new DNode<E>(v.getPrev(), v, element);
	    v.getPrev().setNext(newNode);
	    v.setPrev(newNode);
	  }



	  /** Insert the given element at the beginning of the list, returning
	    * the new position; O(1) time  */
	  public void addFirst(E element) {
	    numElts++;
	    DNode<E> newNode = new DNode<E>(header, header.getNext(), element);
	    header.getNext().setPrev(newNode);
	    header.setNext(newNode);
	  }
	  /**Remove the given position from the list; O(1) time */
	  public E remove(Position<E> p)   //remove node by element
	      throws InvalidPositionException {
	    DNode<E> v = checkPosition(p);
	    numElts--;
	    DNode<E> vPrev = v.getPrev();
	    DNode<E> vNext = v.getNext();
	    vPrev.setNext(vNext);
	    vNext.setPrev(vPrev);
	    E vElem = v.element();
	    // unlink the position from the list and make it invalid
	    v.setNext(null);
	    v.setPrev(null);
	    return vElem;
	  }
	  
	  public E remove(E e) throws InvalidPositionException{  //remove node by element
		  if(header.getNext() == null)return null;
		  DNode<E> temp = header;
		  for(temp=header.getNext();temp.getNext() != null;temp=temp.getNext()){
			  if(temp.element() == e)remove(temp);	  
		  }
		  return e;
	  }
	  /** Replace the element at the given position with the new element
	    * and return the old element; O(1) time  */
	  public E set(Position<E> p, E element)
	      throws InvalidPositionException {
	    DNode<E> v = checkPosition(p);
	    E oldElt = v.element();
	    v.setElement(element);
	    return oldElt;
	  }
	@Override
	public Position<E> last() throws EmptyListException{
		// TODO Auto-generated method stub
		 if (isEmpty())
		      throw new EmptyListException("List is empty");
		return trailer.getPrev();
	}
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException,
			BoundaryViolationException {
		checkPosition(p);
		if(p==trailer.getPrev())return null;
		else return ((DNode<E>) p).getNext();
	}
	@Override
	public void addLast(E e){
		 numElts++;
		 DNode<E> newNode = new DNode<E>(trailer.getPrev(), trailer, e);
		trailer.getPrev().setNext(newNode);
		 trailer.setPrev(newNode);
		
	}
	@Override
	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
	    DNode<E> v = checkPosition(p);
	    numElts++;
	    DNode<E> newNode = new DNode<E>(v, v.getNext(), e);
	    v.getNext().setPrev(newNode);
	    v.setNext(newNode);	
	}
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		
			return new ElementIterator<E>(this);
		
	}
	 /** Returns an iterable collection of all the nodes in the list. 
	 * @throws EmptyListException 
	 * @throws BoundaryViolationException 
	 * @throws InvalidPositionException */
	  public NodePositionList<Position<E>> positions() throws EmptyListException, InvalidPositionException, BoundaryViolationException {     // create a list of posiitons
	    PositionList<Position<E>> P = new NodePositionList<Position<E>>();
	    if (!isEmpty()) {
	      Position<E> p = first();
	      while (true) {
		P.addLast(p); // add position p as the last element of list P
		if (p == last())
		  break;
		p = next(p);
	      }
	    }
	    
	    return (NodePositionList<Position<E>>) P; // return P as our Iterable object
	  }
	 /** Returns a textual representation of a given node list */
	  public static <E> String toString(PositionList<E> l) {
	    Iterator<E> it = l.iterator();
	    String s = "[";
	    while (it.hasNext()) {
	      s += it.next();	// implicit cast of the next element to String
	      if (it.hasNext())
		s += ", ";
	      }
	    s += "]";
	    return s;
	  }
	  public String toString(){
		  Iterator<E> it = this.iterator();
		    String s = "[";
		    while (it.hasNext()) {
		      s += it.next();	// implicit cast of the next element to String
		      if (it.hasNext())
			s += ", ";
		      }
		    s += "]";
		    return s;
	  }
	  public static void main(String[] args) throws InvalidPositionException, EmptyListException {
		NodePositionList<String> npl = new NodePositionList<String>();
		npl.addFirst("aaa");
		npl.addLast("ccc");
		npl.addAfter(npl.first(), "bbb");
		System.out.println(NodePositionList.toString(npl));
		System.out.println(NodePositionList.toString(npl));
		LinkedList ls = new LinkedList();
		ls.add(3);
		ls.add(4);
		Iterator it = ls.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
	
		}
	}
}
