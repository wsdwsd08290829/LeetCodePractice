package com.sidausc.simple;

public class PalindromInt {
	public boolean isPalindrome(int val){
	    int temp = val;
	    int length = 0;
	    
	    while(temp>0){
	        temp=temp/10;
	        length++;
	    }
	    int numOfLoop = length/2; 
	    
	    for(int i=1; i<=numOfLoop; i++){ 
	        int first = 0;
	        int last=0;
	        int lastDivider = 1;
	        int firstDivider = 10;
	        
	       //to get the first digit
	        for(int k=1;k<=length-1;k++){
	            lastDivider*=10;   
	        } 
	        last = val/lastDivider;
	        first = val%firstDivider;
	        //peel the first and last digit
	        val = val%lastDivider/firstDivider; 
	        //renew length to new length of val
	        temp = val;
	        length = 0;
	        while(temp>0){
	            temp=temp/10;
	            length++;
	        } 
	        //check result
	        if(last != first)return false;
	    }
	    return true;
	}
	//a better way is to cast int to Integer, then use toString then check palindrome on string or charArray;
	public static void main(String[] args) {
		PalindromInt pi = new PalindromInt();
		System.out.println(pi.isPalindrome(2123212));
	}
}
