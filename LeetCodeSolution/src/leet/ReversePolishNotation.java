package leet;

import java.util.Stack;
/**
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * DS: stack
 * O(n)
 * @sidawang
 */
public class ReversePolishNotation {
	public static boolean isOperator(String s){
		String operators = "+-*/";
		return operators.indexOf(s) >= 0;
	}
	public static int runReversePolishNotation(String[] tokens){
		Stack<Integer> stack = new Stack<Integer>();
		//if is operator, pop two and calculate, push back
		//else push to stack
		for(String s: tokens){
			if(isOperator(s)){
				int b = stack.pop();
				int a = stack.pop();
				int result = 0;
				//java7 syntax, //use "if" or String.indexOf() for compatibility
				switch(s){ 
					case "+": result = a+b;break;
					case "-": result = a-b;break;
					case "*": result = a*b;break;
					case "/": result = a/b; break;
				}
				stack.push(result);
				//System.out.println("not operators");
			}else{
				stack.push(Integer.valueOf(s));
			}
		}
		return stack.pop();
	}
	
	public static void main(String[] args) {
		String[] tokens = {"2", "1", "+", "3", "*"};
		double finalresult = runReversePolishNotation(tokens);
		System.out.println(finalresult);
		
		String[] tokens1 = {"0","3","/"};
		double finalresult1 = runReversePolishNotation(tokens1);
		System.out.println(finalresult1);
	}	
}
