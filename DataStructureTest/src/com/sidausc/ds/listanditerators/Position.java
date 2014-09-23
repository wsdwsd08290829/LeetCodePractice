package com.sidausc.ds.listanditerators;

public interface Position<E> {
	  /** Return the element stored at this position. 
	 * @throws InvalidPositionException */
	  E element() throws InvalidPositionException;
	  String toString();
}
