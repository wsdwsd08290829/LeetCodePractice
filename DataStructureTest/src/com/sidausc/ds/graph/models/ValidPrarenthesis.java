package com.sidausc.ds.graph.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ValidPrarenthesis {
	public static boolean validPrarenthesis(String s){
		Map<Character,Character> map = new HashMap<Character,Character>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
		
		Set<Character> left = map.keySet();
		Set<Character> right = new HashSet<Character>();
		right.addAll(map.values());
		
		Stack<Character> stack = new Stack<Character>();
		for(int i=0;i<s.length();i++){
			if(left.contains(s.charAt(i))){
				stack.add(s.charAt(i));
			}else if(right.contains(s.charAt(i))){
				
				/**
				 * 
				if (!stack.isEmpty() && map.get(stack.peek()) == c) {
					stack.pop();
				} else {
					return false;
				}
				 */
				if(stack.isEmpty())return false;
				Character c = stack.pop();
				if(map.get(c)!=s.charAt(i))return false;
			}
		}
		
		//merge to return stack.isEmpty()
		if(!stack.isEmpty())return false;
		return true;
	}
	public static void main(String[] args) {
		System.out.println(validPrarenthesis("(([{}])"));
		System.out.println(validPrarenthesis("(([{}])))"));
		System.out.println(validPrarenthesis("(([{}})"));
		System.out.println(validPrarenthesis("(([{}]{)"));
		
		System.out.println(validPrarenthesis("(([{}]))"));
		
		System.out.println(isValid("(([{}])"));
		System.out.println(isValid("(([{}])))"));
		System.out.println(isValid("(([{}})"));
		System.out.println(isValid("(([{}]{)"));
		
		System.out.println(isValid("(([{}]))"));
	}
	
	public static boolean isValid(String s) {
		char[] charArray = s.toCharArray();
	 
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
	 
		Stack<Character> stack = new Stack<Character>();
	 
		for (Character c : charArray) {
			if (map.keySet().contains(c)) {
				stack.push(c);
			} else if (map.values().contains(c)) {
				if (!stack.isEmpty() && map.get(stack.peek()) == c) {
					stack.pop();
				} else {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
}
