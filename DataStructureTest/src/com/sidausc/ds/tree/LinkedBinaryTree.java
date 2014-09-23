package com.sidausc.ds.tree;

import java.util.Iterator;
import java.util.LinkedList;

import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.DNode;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.NodePositionList;
import com.sidausc.ds.listanditerators.Position;
import com.sidausc.ds.listanditerators.PositionList;

/**
  * An implementation of the BinaryTree interface by means of a linked structure.
  */
public class LinkedBinaryTree<E> implements BinaryTree<E> {
	  protected BTPosition<E> root;	// reference to the root
	  protected int size;		// number of nodes
	  /**  Creates an empty binary tree. */
	  public LinkedBinaryTree() { 		    
	    root = null;  // start with an empty tree
	    size = 0;
	  }
	  /** Returns the number of nodes in the tree. */
	  public int size() {
	    return size; 
	  } 
	
	  /** Returns whether a node is internal. */
	  public boolean isInternal(Position<E> v) throws InvalidPositionException {
	    checkPosition(v);		// auxiliary method
	    return (hasLeft(v) || hasRight(v));
	  }
	  public boolean isExternal(Position<E> v) throws InvalidPositionException {
		  checkPosition(v);
		return !isInternal(v);
		  
	  }
	  /** Returns whether a node is the root. 
	 * @throws EmptyTreeException */
	  public boolean isRoot(Position<E> v) throws InvalidPositionException, EmptyTreeException { 
	    checkPosition(v);
	    return (v == root()); 
	  }
	  /** Returns whether a node has a left child. */
	  public boolean hasLeft(Position<E> v) throws InvalidPositionException { 
	    BTPosition<E> vv = checkPosition(v);
	    return (vv.getLeft() != null);
	  }
	  @Override
	  public boolean hasRight(Position<E> v) throws InvalidPositionException {
	  	BTPosition<E> vv = checkPosition(v);
	  	return (vv.getRight()!=null);
	  }
	  /** Returns the root of the tree. */
	  public Position<E> root() throws EmptyTreeException {
	    if (root == null)
	      throw new EmptyTreeException("The tree is empty");
	    return root;
	  } 
	  public boolean isLeftChild(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		  if(this.left(this.parent(v)) == v)return true;
		  else return false;
	  }
	  public boolean isRightChild(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		  return !isLeftChild(v);
	  }
	  /** Returns the left child of a node. */
	  public Position<E> left(Position<E> v) 
	    throws InvalidPositionException, BoundaryViolationException { 
	    BTPosition<E> vv = checkPosition(v);
	    Position<E> leftPos = vv.getLeft();
	    if (leftPos == null)
	      throw new BoundaryViolationException("No left child");
	    return leftPos;
	  }
	  @Override
	  public Position<E> right(Position<E> v) throws InvalidPositionException,
	  		BoundaryViolationException {
		  BTPosition<E> vv = checkPosition(v);
		  Position<E> rightPos = vv.getRight();
	  	return rightPos;
	  }
	
	  public int height(Position<E> v){
		  try {
			
			if(isExternal(v))return 0;
			else
				try {
					  if(!hasLeft(v) && hasRight(v)) return(1+ height(right(v)));
					  if(hasLeft(v) && !hasRight(v)) return(1+ height(left(v))); 
					return(1+ Math.max(height(left(v)),height(right(v))));
				} catch (InvalidPositionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BoundaryViolationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	  }
	  /** Returns the parent of a node. */
	  public Position<E> parent(Position<E> v) 
	    throws InvalidPositionException, BoundaryViolationException { 
	    BTPosition<E> vv = checkPosition(v);
	    Position<E> parentPos = vv.getParent();
	    if (parentPos == null)
	      throw new BoundaryViolationException("No parent");
	    return parentPos; 
	  }
	  /** Returns an iterable collection of the children of a node. */
	  public Iterable<Position<E>> children(Position<E> v) 
	    throws InvalidPositionException { 
	   PositionList<Position<E>> children = new NodePositionList<Position<E>>();
	    if (hasLeft(v))
			try {
				children.addLast(left(v));
			} catch (BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    if (hasRight(v))
			try {
				children.addLast(right(v));
			} catch (BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    return children;
	  }
	  /** Returns an iterable collection of the tree nodes. */
	  public Iterable<Position<E>> positions() {
	   PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
	    if(size != 0)
			try {
				preorderPositions(root(), positions);
			} catch (InvalidPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EmptyTreeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  // assign positions in preorder
	    return positions;
	  } 
	 /** Returns an iterator of the elements stored at the nodes */
	  public Iterator<E> iterator() {
	    Iterable<Position<E>> positions = positions();
	   PositionList<E> elements = new NodePositionList<E>();
	   for (Position<E> pos: positions)
		try {
			elements.addLast(pos.element());
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return elements.iterator();  // An iterator of elements
	  }
	  /** Replaces the element at a node. */
	  public E replace(Position<E> v, E o) 
	    throws InvalidPositionException {
	    BTPosition<E> vv = checkPosition(v);
	    E temp = v.element();
	    vv.setElement(o);
	    return temp;
	  }
	  // Additional accessor method
	  /** Return the sibling of a node */
	  public Position<E> sibling(Position<E> v) 
	    throws InvalidPositionException, BoundaryViolationException {
	      BTPosition<E> vv = checkPosition(v);
	      BTPosition<E> parentPos = vv.getParent();
	      if (parentPos != null) {
		BTPosition<E> sibPos;
		BTPosition<E> leftPos = parentPos.getLeft();
		if (leftPos == vv)
		  sibPos = parentPos.getRight();
		else
		  sibPos = parentPos.getLeft();
		if (sibPos != null)
		  return sibPos;
	      }
	      throw new BoundaryViolationException("No sibling");
	  }
	  // Additional update methods
	  /** Adds a root node to an empty tree */
	  public Position<E> addRoot(E e) throws NonEmptyTreeException {
	    if(!isEmpty())
	      throw new NonEmptyTreeException("Tree already has a root");
	    size = 1;
	    root = createNode(e,null,null,null);
	    return root;
	  }
	  /** Inserts a left child at a given node. */
	  public Position<E>  insertLeft(Position<E> v, E e)
	    throws InvalidPositionException {
	    BTPosition<E> vv = checkPosition(v);
	    Position<E> leftPos = vv.getLeft();
	    if (leftPos != null)
	      throw new InvalidPositionException("Node already has a left child");
	    BTPosition<E> ww = createNode(e, vv, null, null);
	    vv.setLeft(ww);
	    size++;
	    return ww;
	  }
	  public Position<E>  insertRight(Position<E> v, E e)
			    throws InvalidPositionException {
			    BTPosition<E> vv = checkPosition(v);
			    Position<E> rightPos = vv.getRight();
			    if (rightPos != null)
			      throw new InvalidPositionException("Node already has a left child");
			    BTPosition<E> ww = createNode(e, vv, null, null);
			    vv.setRight(ww);
			    size++;
			    return ww;
			  }
	  /** Removes a node with zero or one child. */
	  public E remove(Position<E> v)
	    throws InvalidPositionException {
	    BTPosition<E> vv = checkPosition(v);
	    BTPosition<E> leftPos = vv.getLeft();
	    BTPosition<E> rightPos = vv.getRight();
	    if (leftPos != null && rightPos != null){
	    	 throw new InvalidPositionException("Cannot remove node with two children");
	    }
	     
	    BTPosition<E> ww; 	// the only child of v, if any
	    if (leftPos != null)
	      ww = leftPos;
	    else if (rightPos != null)
	      ww = rightPos;
	    else 		// v is a leaf
	      ww = null;
	    if (vv == root) { 	// v is the root
	      if (ww != null)
		ww.setParent(null);
	      root = ww;
	    }
	    else { 		// v is not the root
	      BTPosition<E> uu = vv.getParent();
	      if (vv == uu.getLeft())
		uu.setLeft(ww);
	      else
		uu.setRight(ww);
	      if(ww != null)
		ww.setParent(uu);
	    }
	    size--;
	    return v.element();
	  }
	  /** Attaches two trees to be subtrees of an external node. 
	 * @throws EmptyTreeException */
	  public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2) 
	    throws InvalidPositionException, EmptyTreeException {
	    BTPosition<E> vv = checkPosition(v);
	    if (isInternal(v))
	      throw new InvalidPositionException("Cannot attach from internal node");
	    if (!T1.isEmpty())
			try {
				{
				  BTPosition<E> r1 = checkPosition(T1.root());
				  vv.setLeft(r1);
				  r1.setParent(vv);		// T1 should be invalidated
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    if (!T2.isEmpty())
			try {
				{
				  BTPosition<E> r2 = checkPosition(T2.root());
				  vv.setRight(r2);
				  r2.setParent(vv);		// T2 should be invalidated
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  /** If v is a good binary tree node, cast to BTPosition, else throw exception */
	  protected BTPosition<E> checkPosition(Position<E> v) 
	    throws InvalidPositionException {
	    if (v == null ) //|| !(v instanceof BTPosition)
	      throw new InvalidPositionException("The position is invalid");
	    return (BTPosition<E>) v;
	  }
	  /** Creates a new binary tree node */
	  protected BTPosition<E> createNode(E element, BTPosition<E> parent, 
					  BTPosition<E> left, BTPosition<E> right) {
	    return new BTNode<E>(element,parent,left,right); }
	  /** Creates a list storing the the nodes in the subtree of a node,
	    * ordered according to the preorder traversal of the subtree. */
	  protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos) 
	    throws InvalidPositionException {
	    pos.addLast(v);
	    if (hasLeft(v))
			try {
				preorderPositions(left(v), pos);
			} catch (BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	// recurse on left child
	    if (hasRight(v))
			try {
				preorderPositions(right(v), pos);
			} catch (BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	// recurse on right child
	  }
	 public String toString(){
		 NodePositionList<Position<E>> pos = new NodePositionList<Position<E>>();
		 try {
			preorderPositions(root(), pos);
		} catch (InvalidPositionException | EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 NodePositionList<String> postr = new NodePositionList<String>();
		 for(Position<E> p: pos){
			
			 try {
				 if(p.element() != null){
					 postr.addLast(p.element().toString());
					 System.out.println(p.element());
				 }
			} catch (InvalidPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 }
		 
		 return postr.toString();
	 }
	 public LinkedList<BTPosition<E>> binaryPreorder(BinaryTree<E> T, BTPosition<E> v, LinkedList<BTPosition<E>> ls) throws InvalidPositionException{
		 ls.add(v);
		 if(T.hasLeft(v))binaryPreorder(T, v.getLeft(),ls);
		 if(T.hasRight(v))binaryPreorder(T, v.getRight(), ls);
		return ls;
		 
	 }
	 public LinkedList<BTPosition<E>> binaryPostorder(BinaryTree<E> T, BTPosition<E> v, LinkedList<BTPosition<E>> ls) throws InvalidPositionException{
		
		 if(T.hasLeft(v))binaryPostorder(T, v.getLeft(),ls);
		 if(T.hasRight(v))binaryPostorder(T, v.getRight(), ls);
		 ls.add(v);
		return ls;
	 }
	 
	 //visit value in the binary search tree in non decreasing order
	 public LinkedList<BTPosition<E>> binaryInorder(BinaryTree<E> T, BTPosition<E> v, LinkedList<BTPosition<E>> ls) throws InvalidPositionException{	
		 if(T.hasLeft(v))binaryInorder(T, v.getLeft(),ls);
		 ls.add(v);
		 if(T.hasRight(v))binaryInorder(T, v.getRight(), ls);
		return ls;
	 }
	 //in order
	 public StringBuilder toExpString(BTPosition<E> v, StringBuilder s) throws InvalidPositionException{
		 if(this.hasLeft(v)){s.append("(");toExpString(v.getLeft(),s);};
		 s.append(v.toString());
		 if(this.hasRight(v)){toExpString(v.getRight(), s);s.append(")");};
		return s;
	 }
	 public void toExpString2(BTPosition<E> v) throws InvalidPositionException{
		 if(this.isInternal(v))System.out.print("("); else System.out.print(v.toString());
		 if(this.hasLeft(v)){toExpString2(v.getLeft());}
		 if(this.isInternal(v))System.out.print(v.toString());
		 if(this.hasRight(v)){toExpString2(v.getRight());}
		 if(this.isInternal(v))System.out.print(")");
	 }
	 public StringBuilder treeStructure(BTPosition<E> v, StringBuilder s) throws InvalidPositionException{
		 	s.append(v.toString());
	
			 if(this.hasLeft(v)){s.append("(");treeStructure(v.getLeft(), s);s.append(",");}
			 if(this.hasRight(v)){treeStructure(v.getRight(), s); s.append(")");}
	
		 return s;
	 }
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}
	public LinkedList<BTPosition<E>> eulerTour(BinaryTree<E> T,BTPosition<E> v, LinkedList<BTPosition<E>> ls) throws InvalidPositionException{
		ls.add(v);
		if(T.hasLeft(v)){
			eulerTour(T,v.getLeft(),ls);
		}
		ls.add(v);
		if(T.hasRight(v)){
			eulerTour(T,v.getRight(),ls);
		}
		ls.add(v);
		return ls;
	}
	public int countDescendents(BTPosition<E> v) throws InvalidPositionException{
		int sumleft = 0;
		int sumright = 0;
		if(this.hasLeft(v))sumleft =  1 + countDescendents(v.getLeft());
		if(this.hasRight(v))sumright =  1+ countDescendents(v.getRight());
		
		return sumleft+sumright;
	}

}
