package com.sidausc.ds.tree;

import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.Position;

/** Compute the value of an arithmetic expression tree. */
public class EvaluateExpressionTour extends EulerTour<ExpressionTerm, Integer> {
  public Integer execute(BinaryTree<ExpressionTerm> T) throws InvalidPositionException, BoundaryViolationException, EmptyTreeException {
    init(T); // calls method of superclass
    return eulerTour(tree.root());  // returns the value of the expression
  }
  protected void visitRight(Position<ExpressionTerm> v, TourResult<Integer> r) {
    ExpressionTerm term = null;
	try {
		term = v.element();
	} catch (InvalidPositionException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    try {
		if (tree.isInternal(v)) {
		  ExpressionOperator op = (ExpressionOperator) term;
		  op.setOperands(r.left, r.right);
		}
	} catch (InvalidPositionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    r.out = term.getValue(); 
  }
}