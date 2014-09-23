package com.sidausc.ds.tree;

import java.util.LinkedList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.Position;
import com.sidausc.ds.stack.NodeStack;

public class MyExpressionTree<E> extends LinkedBinaryTree<E>{
	public MyExpressionTree<Character> createTreeFromString(String exp) throws InvalidPositionException, EmptyTreeException, NonEmptyTreeException{
		char[] arr = exp.toCharArray();
		Character[] arrnew = new Character[arr.length];
		for(int i = 0; i<arr.length; i++){
			arrnew[i] = arr[i];
		}
		NodeStack<MyExpressionTree<Character>> ns = new NodeStack<MyExpressionTree<Character>>();
		for(Character c : arrnew){
			if(c=='(')continue;
			else if(c==')'){
				MyExpressionTree child1 = ns.pop();  //may be a tree, put right
				MyExpressionTree parent = ns.pop();
				MyExpressionTree child2 = ns.pop();  //must be a value, put left, use for toString
				
				parent.attach(parent.root(), child2,child1);
				ns.push(parent);
			}else{
				MyExpressionTree<Character> T = new MyExpressionTree<Character>();
				T.addRoot(c);
				ns.push(T);
			}
		}
		return  ns.pop();
	}
	
	public String toString(){
		//return parentheticRepresentation(this, root());
		return super.toString();
		
	}
	
	//using postorder
	private Double evaluate(LinkedBinaryTree<E> T, BTPosition<E> n) throws NumberFormatException, InvalidPositionException
	{
	    if (n != null)
	    {
	        if (T.isExternal(n))  // n is a node with a number
	            return (double) Integer.parseInt(n.element().toString());
	        else
	        {
	            Double left = evaluate(T, ((BTPosition)n).getLeft());
	            Double right = evaluate(T, ((BTPosition)n).getRight());
	            return calculate(left, n.element().toString(), right);
	        } //end else
	    } //end if
	    return 0d;
	} //end evaluate
	private Double calculate(Double left, String string, Double right) {
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    String foo = left.toString()+string+right.toString();
	    Double evalexp = 0d;
	    try {
	    	evalexp =  (Double) engine.eval(foo);
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return evalexp;
	}
	
	public static void main(String[] args) throws NonEmptyTreeException, InvalidPositionException, EmptyTreeException {
		String exp = "(4+((1+2)*3))";
		//test exp string to tree
		MyExpressionTree<Character> met = new MyExpressionTree<Character>();
		met = met.createTreeFromString(exp);
		System.out.println(met);
		//test evaluate tree to result
		System.out.println(met.evaluate(met, (BTPosition<Character>) met.root()));
		//test Inorder, or Preorder or Postorder traverse the tree
		LinkedList<BTPosition<Character>> ls = new LinkedList<BTPosition<Character>>();
		System.out.println(met.binaryInorder(met, (BTPosition<Character>) met.root(), ls));
		
		//test tree back to exp string  //in order visiting the ree
		StringBuilder s= new StringBuilder();
		System.out.println(met.toExpString((BTPosition<Character>)met.root(),s));
		met.toExpString2((BTPosition<Character>)met.root());
		System.out.println();
		//test eulerTour of Tree, each node visited three times;
		LinkedList<BTPosition<Character>> ls2 = new LinkedList<BTPosition<Character>>();
		System.out.println(met.eulerTour(met, (BTPosition<Character>) met.root(), ls2));
		
		//test parenthesis version of tree
		s= new StringBuilder();
		System.out.println(met.treeStructure((BTPosition<Character>)met.root(),s));
		//test get descendents
		System.out.println(met.countDescendents(((BTPosition<Character>) met.root()).getRight()));
		//not get it from book
	//	System.out.println(met.countDescendents2(((BTPosition<Character>) met.root()).getRight()));
		
 
			//??????
		/**
		 * how to use PrintExpressionTour, EvaluateExpressionTour, EulerTour, Expression*;
		 */
		}
	//+(4,*(+(1,2),3))   
}
