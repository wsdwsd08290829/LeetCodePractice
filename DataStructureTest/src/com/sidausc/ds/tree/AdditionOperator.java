package com.sidausc.ds.tree;

/** Class for the addition operator in an arithmetic expression. */
public class AdditionOperator extends ExpressionOperator {
  public Integer getValue() {
    return (firstOperand + secondOperand); 	// unboxing and then autoboxing
  }
  public String toString() { return new String("+"); }
}