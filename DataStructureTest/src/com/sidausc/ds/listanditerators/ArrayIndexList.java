package com.sidausc.ds.listanditerators;

public class ArrayIndexList<E> implements IndexList<E> {
		  private E[] A;	      // array storing the elements of the indexed list
		  private int capacity = 16;  // initial length of array A
		  private int size = 0;	      // number of elements stored in the indexed list
		  /** Creates the indexed list with initial capacity 16. */
		  public ArrayIndexList() { 
		    A = (E[]) new Object[capacity]; // the compiler may warn, but this is ok
		  }
		  /** Inserts an element at the given index. */
		  public void add(int r, E e) 
		    throws IndexOutOfBoundsException {
		    checkIndex(r, size() + 1);
		    if (size == capacity) {		// an overflow
		      capacity *= 2;
		      E[] B =(E[]) new Object[capacity];
		      for (int i=0; i<size; i++) 
			B[i] = A[i];
		      A = B;
		    }
		    for (int i=size-1; i>=r; i--)	// shift elements up
		      A[i+1] = A[i];
		    A[r] = e;
		    size++;
		  }
		  /** Removes the element stored at the given index. */
		  public E remove(int r) 
		    throws IndexOutOfBoundsException {
		    checkIndex(r, size());
		    E temp = A[r];
		    for (int i=r; i<size-1; i++)	// shift elements down
		      A[i] = A[i+1];
		    size--;
		    return temp;
		  }
		private void checkIndex(int r, int size) throws IndexOutOfBoundsException{
			// TODO Auto-generated method stub
			if(r>size-1){throw new IndexOutOfBoundsException();}
		}
		@Override
		public int size() {
			// TODO Auto-generated method stub
			return size;
		}
		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			if(size==0){
				return true;
			}else
				return false;
		}
		@Override
		public E get(int i) throws IndexOutOfBoundsException {
			// TODO Auto-generated method stub
			if(i>capacity-1)throw new IndexOutOfBoundsException();
			return A[i];
		}
		@Override
		public E set(int i, E e) throws IndexOutOfBoundsException {
			if(i>capacity-1)throw new IndexOutOfBoundsException();
			E old = A[i];
			 A[i] = e;
			 return old;
			// TODO Auto-generated method stub
			
		}

}
