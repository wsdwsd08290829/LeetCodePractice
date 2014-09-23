package com.sidausc.ds.listanditerators;

public class FavoriteListMTF<E> extends FavoriteList<E> {
	  /** Default constructor */
	  public FavoriteListMTF() { }
	  /** Moves up an entry to the first position;  O(1) time  
	 * @throws InvalidPositionException */
	  protected void moveUp(Position<Entry<E>> pos) throws InvalidPositionException { 
	    fList.addFirst(fList.remove(pos)); 
	  }
	  /** Returns the k most accessed elements, for a given k; O(kn) time 
	 * @throws InvalidPositionException 
	 * @throws EmptyListException */
	  public Iterable<E> top(int k) throws InvalidPositionException, EmptyListException {
	    if (k < 0 || k > size()) 
	      throw new IllegalArgumentException("Invalid argument");
	    PositionList<E> T = new NodePositionList<E>(); // top-k list
	    if (!isEmpty()) {
	      // copy entries into temporary list C
	      PositionList<Entry<E>> C = new NodePositionList<Entry<E>>();
	      for (Entry<E> e: fList)
	    	  	C.addLast(e);
	      // find the top k elements, one at a time
	      for (int i = 0; i < k; i++) {
				Position<Entry<E>> maxPos = null;	// position of top entry
				int maxCount = -1;			// access count of top entry
				E maxVal = null;
				
				/*for (Position<Entry<E>> p: C.positions()) {	
				  // examine all entries of C
				  int c = count(p);
				  if (c > maxCount) {	// found an entry with higher access count
				    maxCount = c;
				    maxPos = p;
				  }
				  T.addLast(value(maxPos));	// add top entry to list T
					C.remove(maxPos);		// remove top entry from list C
				}*/
			
					for(DNode<Entry<E>> temp = (DNode<Entry<E>>) C.first(); temp!=null; temp=temp.getNext()){
						if(temp.element() != null){
							if(temp.element().count()>maxCount)
							{
								maxCount = temp.element().count();
								maxVal = temp.element().value();
								maxPos = temp;
							}
						}
					}
					
					T.addLast(maxVal);
					C.remove(maxPos);		
			
				
	      }
	    }
	    return T;
	  }
	}