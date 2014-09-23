package com.sidausc.ds.listanditerators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ElementIterator<E> implements Iterator<E> {
	  protected PositionList<E> list; // the underlying list
	  protected Position<E> cursor; // the next position
	  /** Creates an element iterator over the given list. 
	 * @throws EmptyListException */
	  public ElementIterator(PositionList<E> L)  {
	    list = L;
	    try {
			cursor = (list.isEmpty())? null : list.first();
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  public boolean hasNext() { return (cursor != null);  }
	  public E next() throws NoSuchElementException {
		  E toReturn = null;
		try {
		    if (cursor == null)
		      throw new NoSuchElementException("No next element");
		
			toReturn = cursor.element();
			cursor = (cursor == list.last())? null : list.next(cursor);
		} catch (EmptyListException | InvalidPositionException
				| BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		return toReturn;
	  }
	@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}
