package com.sidausc.ds.tree;

import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.Position;


/** Print out the expression stored in an arithmetic expression tree.  */
public class PrintExpressionTour extends EulerTour<ExpressionTerm, String> {
  public String execute(BinaryTree<ExpressionTerm> T) {
    init(T);
    System.out.print("Expression: ");
    try {
		eulerTour(T.root());
	} catch (InvalidPositionException | BoundaryViolationException
			| EmptyTreeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    System.out.println();
    return null;	// nothing to return
  }
  protected void visitLeft(Position<ExpressionTerm> v, TourResult<String> r) { 
    try {
		if (tree.isInternal(v)) System.out.print("(");
	} catch (InvalidPositionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} }
  protected void visitBelow(Position<ExpressionTerm> v, TourResult<String> r) { 
    try {
		System.out.print(v.element());
	} catch (InvalidPositionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} }
  protected void visitRight(Position<ExpressionTerm> v, TourResult<String> r) { 
    try {
		if (tree.isInternal(v)) System.out.print(")");
	} catch (InvalidPositionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} }
}