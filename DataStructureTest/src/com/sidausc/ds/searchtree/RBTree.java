package com.sidausc.ds.searchtree;
///?????????To be implemented
import java.util.Comparator;

import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.Position;
import com.sidausc.ds.priorityqueue.Entry;
import com.sidausc.ds.priorityqueue.InvalidEntryException;
import com.sidausc.ds.priorityqueue.InvalidKeyException;
import com.sidausc.ds.tree.BTNode;
import com.sidausc.ds.tree.BTPosition;
import com.sidausc.ds.tree.EmptyTreeException;
import com.sidausc.ds.tree.NonEmptyTreeException;

/** Realization of a dictionary by means of a red-black tree. */
public class RBTree<K,V>
  extends BinarySearchTree<K,V> implements Dictionary<K,V> {
  public RBTree() { super(); }
  public RBTree(Comparator<K> C) { super(C); }
  /** Nested class for the nodes of a red-black tree */
  protected static class RBNode<K,V> extends BTNode<Entry<K,V>> {
    protected boolean isRed;  // we add a color field to a BTNode
    RBNode() {/* default constructor */}
    /** Preferred constructor */
    RBNode(Entry<K,V> element, BTPosition<Entry<K,V>> parent,
	   BTPosition<Entry<K,V>> left, BTPosition<Entry<K,V>> right) {
      super(element, parent, left, right);
      isRed = false;
    } 
    public boolean isRed()  {return isRed;}
    public void makeRed()  {isRed = true;}
    public void makeBlack()  {isRed = false;}
    public void setColor(boolean color)  {isRed = color;}
  }
  /** Creates a new tree node. */
  protected BTPosition<Entry<K,V>> createNode(Entry<K,V> element, 
      BTPosition<Entry<K,V>> parent, BTPosition<Entry<K,V>> left, 
      BTPosition<Entry<K,V>> right) {
    return new RBNode<K,V>(element,parent,left,right); // a red-black node
  }
  public Entry<K,V> insert(K k, V x) throws InvalidKeyException  {
    Entry<K, V> toReturn = null;
	try {
		toReturn = super.insert(k, x);
	} catch (InvalidPositionException | BoundaryViolationException
			| EmptyTreeException | NonEmptyTreeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    Position<Entry<K,V>> posZ = actionPos; // start at the insertion position
    setRed(posZ);
    try {
		if (isRoot(posZ))
		  setBlack(posZ);
		else
		  remedyDoubleRed(posZ);
	} catch (InvalidPositionException | EmptyTreeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} // fix a double-red color violation 
 catch (BoundaryViolationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return toReturn;
  }
  private void setRed(Position<Entry<K, V>> posZ) {
	// TODO Auto-generated method stub
	  ((RBNode<K, V>) posZ).makeRed();
	
}
private void setBlack(Position<Entry<K, V>> posZ) {
	// TODO Auto-generated method stub
	 ((RBNode<K, V>) posZ).makeBlack();
}
protected void remedyDoubleRed(Position<Entry<K,V>> posZ) throws InvalidPositionException, BoundaryViolationException, EmptyTreeException  {
    Position<Entry<K,V>> posV = parent(posZ);
    if (isRoot(posV))
      return;
    if (!isPosRed(posV))
      return;
    // we have a double red: posZ and posV
    if (!isPosRed(sibling(posV)))  { // Case 1: trinode restructuring
      posV = restructure(posZ);
      setBlack(posV);
      setRed(left(posV));
      setRed(right(posV));
    }  
    else  { // Case 2: recoloring
      setBlack(posV);
      setBlack(sibling(posV));
      Position<Entry<K,V>> posU = parent(posV);
      if (isRoot(posU))
        return;
      setRed(posU);
      remedyDoubleRed(posU);
    }
  }



  private Position<Entry<K, V>> restructure(Position<Entry<K, V>> posZ) {
	// TODO Auto-generated method stub
	return null;
}
public Entry<K,V> remove(Entry<K,V> ent) throws InvalidEntryException {
    Entry<K, V> toReturn = null;
	try {
		toReturn = super.remove(ent);
	} catch (InvalidPositionException | BoundaryViolationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    Position<Entry<K,V>> posR = actionPos;
    if (toReturn != null) {	
      try {
		if (wasParentRed(posR) || isRoot(posR) || isPosRed(posR))
		setBlack(posR);
		else
			try {
				remedyDoubleBlack(posR);
			} catch (InvalidPositionException | BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EmptyTreeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	} catch (InvalidPositionException | EmptyTreeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    return toReturn;
  }
  private boolean wasParentRed(Position<Entry<K, V>> posR) {
	// TODO Auto-generated method stub
	return false;
}
protected void remedyDoubleBlack(Position<Entry<K,V>> posR) throws InvalidPositionException, BoundaryViolationException, EmptyTreeException {
    Position<Entry<K,V>> posX, posY, posZ;
    boolean oldColor;
    posX = parent(posR);
    posY = sibling(posR);
    if (!isPosRed(posY))  {
      posZ = redChild(posY);
      if (hasRedChild(posY))  { // Case 1: trinode restructuring
      	oldColor = isPosRed(posX);
	posZ = restructure(posZ);
      	setColor(posZ, oldColor);
	setBlack(posR);
      	setBlack(left(posZ));
      	setBlack(right(posZ));
	return;
      }
      setBlack(posR);
      setRed(posY);
      if (!isPosRed(posX))  { // Case 2: recoloring
        if (!isRoot(posX))
	  remedyDoubleBlack(posX);
	return;
      }
      setBlack(posX);
      return;
    } // Case 3: adjustment
    if (posY == right(posX)) posZ = right(posY);
    else posZ = left(posY);
    restructure(posZ);
    setBlack(posY);
    setRed(posX);
    remedyDoubleBlack(posR);
  }
private void setColor(Position<Entry<K, V>> posZ, boolean oldColor) {
	// TODO Auto-generated method stub
	
}
private boolean hasRedChild(Position<Entry<K, V>> posY) {
	// TODO Auto-generated method stub
	return false;
}
private Position<Entry<K, V>> redChild(Position<Entry<K, V>> posY) {
	// TODO Auto-generated method stub
	return null;
}
private boolean isPosRed(Position<Entry<K, V>> posY) {
	// TODO Auto-generated method stub
	return false;
	
}
@Override
public K getKey() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public V getValue() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Entry<K, V> remove(V value) {
	// TODO Auto-generated method stub
	return null;
}
}