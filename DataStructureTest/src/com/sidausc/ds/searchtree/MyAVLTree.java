package com.sidausc.ds.searchtree;

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
import com.sidausc.ds.tree.LinkedBinaryTree;
import com.sidausc.ds.tree.NonEmptyTreeException;

/**  Implementation of an AVL tree. */
public class MyAVLTree<K,V> extends BinarySearchTree<K,V> { //implements Dictionary<K,V> 
  public MyAVLTree(Comparator<K> c)  { super(c); }
  public MyAVLTree() { super(); }
  /** Nested class for the nodes of an AVL tree. */ 
 /* protected static class AVLNode<K,V> extends BTNode<Entry<K,V>> {
    protected int height;  // we add a height field to a BTNode
    AVLNode() { default constructor }
    *//** Preferred constructor *//*
    AVLNode(Entry<K,V> element, BTPosition<Entry<K,V>> parent,
	    BTPosition<Entry<K,V>> left, BTPosition<Entry<K,V>> right) {
      super(element, parent, left, right);
      height = 0;
      if (left != null) 
        height = Math.max(height, 1 + ((AVLNode<K,V>) left).getHeight());
      if (right != null) 
        height = Math.max(height, 1 + ((AVLNode<K,V>) right).getHeight());
    } // we assume that the parent will revise its height if needed
    public void setHeight(int h) { height = h; }
    public int getHeight() { return height; }
  }*/
  /** Creates a new binary search tree node (overrides super's version). */
  protected BTPosition<Entry<K,V>> createNode(Entry<K,V> element, 
      BTPosition<Entry<K,V>> parent, BTPosition<Entry<K,V>> left, 
      BTPosition<Entry<K,V>> right) {
    return new BTNode<Entry<K,V>>(element,parent,left,right);  // now use AVL nodes
  }
  /** Returns the height of a node (call back to an AVLNode). */
/*  public int height(Position<Entry<K,V>> p)  {
	
    return ((BTNode<Entry<K,V>>) p).getHeight();
  }*/
  /** Sets the height of an internal node (call back to an AVLNode). 
 * @throws BoundaryViolationException 
 * @throws InvalidPositionException */
  /*protected void setHeight(Position<Entry<K,V>> p) throws InvalidPositionException, BoundaryViolationException { 
    ((BTNode<Entry<K,V>>)p).setHeight(1+Math.max(height(left(p)), height(right(p))));
  }*/
  /** Returns whether a node has balance factor between -1 and 1. 
 * @throws BoundaryViolationException 
 * @throws InvalidPositionException */
  protected boolean isBalanced(Position<Entry<K,V>> p) throws InvalidPositionException, BoundaryViolationException  {
	  System.out.println("p  "+ p.element());
	  try {
		System.out.println("p  "+ isRoot(p));
		System.out.println("p  "+ root());
	} catch (EmptyTreeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 System.out.println("left p  "+ left(p).element());
	System.out.println("height left " + height(left(p)) + "height right " + height(right(p)));
	  int bf = 0;
    if(!hasLeft(p) && hasRight(p)) bf = height(right(p)); 
    if(hasLeft(p) && !hasRight(p)) bf = height(left(p)); 
    if(hasLeft(p) && hasRight(p)) bf = height(left(p)) - height(right(p));
    return ((-1 <= bf) &&  (bf <= 1));
  }



  /** Returns a child of p with height no smaller than that of the other child 
 * @throws BoundaryViolationException 
 * @throws InvalidPositionException 
 * @throws EmptyTreeException */
  protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) throws InvalidPositionException, BoundaryViolationException, EmptyTreeException  {
    if (height(left(p)) > height(right(p))) return left(p);
    else if (height(left(p)) < height(right(p))) return right(p);
    // equal height children - break tie using parent's type
    if (isRoot(p)) return left(p);
    if (p == left(parent(p))) return left(p);
    else return right(p);
  }
  /**  
    * Rebalance method called by insert and remove.  Traverses the path from 
    * zPos to the root. For each node encountered, we recompute its height 
    * and perform a trinode restructuring if it's unbalanced.
 * @throws BoundaryViolationException 
 * @throws EmptyTreeException 
 * @throws InvalidPositionException 
    */
  protected void rebalance(Position<Entry<K,V>> zPos) throws InvalidPositionException, EmptyTreeException, BoundaryViolationException {
   /* if(isInternal(zPos)){
       setHeight(zPos);
      // System.out.println(((BTNode<Entry<K, V>>) zPos).getHeight());
    }*/
	 
   if(zPos != null)
    while (!isRoot(zPos)) {  // traverse up the tree towards the root
      zPos = parent(zPos);
      System.out.println("????????"+zPos);
    /*  setHeight(zPos);*/
      if (!isBalanced(zPos)) { 
	// perform a trinode restructuring at zPos's tallest grandchild
    	
        Position<Entry<K,V>> xPos =  tallerChild(tallerChild(zPos));
        zPos = restructure(xPos); // tri-node restructure (from parent class)
        /*setHeight(left(zPos));  // recompute heights
        setHeight(right(zPos));
        setHeight(zPos);*/
        System.out.println("my zpos "+ zPos + " zPos parent " + ((BTPosition<Entry<K, V>>) zPos).getParent() + " curremnt root "+ root);
        if(((BTPosition<Entry<K, V>>) zPos).getParent() == null) root = (BTPosition<Entry<K, V>>) zPos;
      }
    }
   else System.out.println("FFFFFFFFFFFFFFFF");
   
  } 
  private Position<Entry<K, V>> restructure(Position<Entry<K, V>> xPos) {
	// TODO Auto-generated method stub
	  try {

		BTPosition<Entry<K,V>> yPos = (BTPosition<Entry<K, V>>) parent(xPos);
		BTPosition<Entry<K,V>> zPos = (BTPosition<Entry<K, V>>) parent(yPos);
		  System.out.println("x: "  + xPos.element());
		  System.out.println("y: "  + yPos.element());
		  System.out.println("z: "  + zPos.element());
		  StringBuilder s = new StringBuilder();
		  System.out.println(treeStructure((BTPosition<Entry<K, V>>) root(), s));
		Position<Entry<K, V>> T0=null,T1=null,T2=null,T3=null;
		Position<Entry<K,V>> a=null, b=null, c = null;
		//case 1: \ \
		if(right(zPos)==yPos && right(yPos) ==xPos){
			System.out.println("!!!!!! here1");
			a = zPos; b = yPos; c=xPos;
			T0 = left(zPos); T1 = left(yPos); T2=left(xPos); T3=right(xPos);
		} 
		 //case 2: / /
		else if(left(zPos)==yPos && left(yPos) ==xPos){
			System.out.println("!!!!!! here2");
			a = xPos; b = yPos; c=zPos;
			T0 = left(xPos); T1 = right(xPos); T2=right(yPos); T3=right(zPos);
		} 
		//case 3: \ \
		else if(right(zPos)==yPos && left(yPos) ==xPos){
			System.out.println("!!!!!! here3");
			a = zPos; b = xPos; c=yPos;
			T0 = left(zPos); T1 = left(xPos); T2=right(xPos); T3=right(yPos);
		} 
		 //case 4: / \
		else if(left(zPos)==yPos && right(yPos) ==xPos){
			System.out.println("!!!!!! here4");
			a = yPos; b = xPos; c=zPos;
			T0 = left(yPos); T1 = left(xPos); T2=right(xPos); T3=right(zPos);
		} else {
			System.out.println("!!!!!! here5");
			throw new Exception();
			
		}
		
		//LinkedBinaryTree<Entry<K,V>> treeleft = new LinkedBinaryTree<Entry<K,V>>();
		//LinkedBinaryTree<Entry<K,V>> treeright = new LinkedBinaryTree<Entry<K,V>>();
		BTNode<Entry<K,V>> btleft = new BTNode<Entry<K,V>>(a.element(), (BTPosition<Entry<K,V>>)b, (BTPosition<Entry<K,V>>)T0,(BTPosition<Entry<K,V>>)T1);
		BTNode<Entry<K,V>> btright = new BTNode<Entry<K,V>>(c.element(), (BTPosition<Entry<K,V>>)b, (BTPosition<Entry<K,V>>)T2,(BTPosition<Entry<K,V>>)T3);
		
		
		
		((BTPosition<Entry<K, V>>) zPos).setLeft(btleft);
		((BTPosition<Entry<K, V>>) zPos).setRight(btright);
		((BTPosition<Entry<K, V>>) zPos).setElement(b.element());
		((BTPosition<Entry<K, V>>) zPos).setParent(zPos.getParent());
		System.out.println("zPos" + zPos.element());
		s = new StringBuilder();
		System.out.println(treeStructure((BTPosition<Entry<K, V>>) super.root(), s));
		return zPos;
	} catch (InvalidPositionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (BoundaryViolationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NonEmptyTreeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	return null;
}
// overridden methods of the dictionary ADT
  public Entry<K,V> insert(K k, V v) throws InvalidKeyException, InvalidPositionException, BoundaryViolationException, EmptyTreeException, NonEmptyTreeException  {
    Entry<K,V> toReturn = super.insert(k, v); // calls our createNode method
    System.out.println("action" + actionPos);
    StringBuilder s = new StringBuilder();
	System.out.println(treeStructure((BTPosition<Entry<K, V>>) root(), s));
    rebalance(actionPos); // rebalance up from the insertion position
    return toReturn;
  }
  public Entry<K,V> remove(Entry<K,V> ent) throws InvalidEntryException, InvalidPositionException, BoundaryViolationException {
    Entry<K,V> toReturn = super.remove(ent);
    try {
		System.out.println("Key "+ super.root());
	} catch (EmptyTreeException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    System.out.println("Key "+ toReturn.getKey());
    if (toReturn != null)
		try {
			rebalance(actionPos);
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // rebalance up the tree
    return toReturn;
  }
  public static void main(String[] args) {
		MyAVLTree<Integer,String> bst = new MyAVLTree<Integer,String>();
		try {
		//????????com.sidausc.ds.tree.BTNode cannot be cast to com.sidausc.ds.searchtree.AVLTree$AVLNode	
			StringBuilder s = new StringBuilder();
			bst.insert(1,"a");
			bst.insert(2,"b");
			bst.insert(3,"c");
			 
			
			bst.insert(4,"d");
			// System.out.println("4 just inserted");
			 s = new StringBuilder();
			System.out.println(bst.treeStructure((BTPosition<Entry<Integer, String>>)bst.root(), s));
			//bst.insert(5,"e");
		/*	try {
				
				bst.remove(bst.root().element());
			} catch (InvalidEntryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			 s = new StringBuilder();
				System.out.println(bst.treeStructure((BTPosition<Entry<Integer, String>>)bst.root(), s));
			
		/*	try {
				bst.remove(bst.root().element());
				s.delete(0,s.length());
				System.out.println(bst.treeStructure((BTPosition<Entry<Integer, String>>) bst.root(), s));
				
			} catch (InvalidEntryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NonEmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} // end of AVLTree class