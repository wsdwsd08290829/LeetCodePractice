package com.sidausc.ds.tree;

/** Class for an operator of an arithmetic expression */
public class ExpressionOperator extends ExpressionTerm {
  protected Integer firstOperand, secondOperand;
  public void setOperands(Integer x, Integer y) {
    firstOperand = x;
    secondOperand = y;
  }
}
