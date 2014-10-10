package basic;

import java.util.Stack;

public class ReversePolishNotation {
	public static void main(String[] args) {
		String[] tokens = {"2", "1", "+", "3", "*"};
		double finalresult = runReversePolishNotation(tokens);
		System.out.println(finalresult);
	}
	public static double runReversePolishNotation(String[] tokens){
		String operators = "+-*/";
		Stack<String> stack = new Stack<String>();
		for(String s: tokens){
			if(operators.indexOf(s) >= 0){
				int i = operators.indexOf(s);
				double b = Double.valueOf(stack.pop());
				double a = Double.valueOf(stack.pop());
				double result = 0;
				
				switch(i){
					case 0: result = a+b;break;
					case 1: result = a-b;break;
					case 2: result = a*b;break;
					case 3: result = (double)a/b; break;
				}
				stack.push(String.valueOf(result));
				//System.out.println("not operators");
			}else{
				stack.push(s);
			}
		}
		return Double.valueOf(stack.pop());
	}
	
	
}
