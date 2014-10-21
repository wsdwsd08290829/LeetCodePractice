package basic;

import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RegularExpDotStar {
	
	    public static boolean isMatch(String s, String p) {
	 
	        if(p.length() == 0)
	            return s.length() == 0;
	 
	        //p's length 1 is special case    
	        if(p.length() == 1 || p.charAt(1) != '*'){
	            /*if(s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)))
	                return false;
	            return isMatch(s.substring(1), p.substring(1));*/
	        	
	        	//my way
	        	if(p.charAt(0) == '.' || (s.length()>0 && p.charAt(0)==s.charAt(0)))return isMatch(s.substring(1), p.substring(1));
	        	else
	        		return false;
	        }else{
	            int len = s.length();
	 
	          /*  int i = -1; 
	            while(i<len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))){
	                if(isMatch(s.substring(i+1), p.substring(2)))
	                    return true;
	                i++;
	            }
	            return false;*/
	             
	            //my way
	            int firstMismatch = 0;
	            if(isMatch(s, p.substring(2)))return true;  //if * represent 0 recur
	            for(int i=0;i<s.length();i++){	   //if match 1 to if match all;
	            	if(p.charAt(0)=='.'|| (p.charAt(0) == s.charAt(0))){
	            		System.out.println();
	            		if(isMatch(s.substring(i+1), p))return true;
	            	}else{
	            		firstMismatch = i;
	            		break;
	            	}
	            }
	            return isMatch(s.substring(firstMismatch), p.substring(2));        
	        } 
	    }
	public static void main(String[] args) {
		System.out.println(isMatch("aa","a*"));
		String s = "test";
		System.out.println(s.substring(4)); //""
		
		//System.out.println(s.substring(5)); //StringIndexOutOfBoundException
		/*Result result = JUnitCore.runClasses(TestBasic.class);
		List<Failure> failures = result.getFailures();
		for(Failure f : failures){
			System.out.println(f.getTrace());
		}
		System.out.println(result.wasSuccessful());*/
		
	}
}
