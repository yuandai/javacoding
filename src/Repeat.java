package src;

import java.util.HashSet;
import java.util.Set;

public class Repeat {
	
	// find repeating substrings in a string
	// "banana" -> "an" (2), 

	// find longest repeating substrings in a string
	// "banana" -> "ana" (2)
	
	public static void main(String args[]) {
		
		String str = "banana";
		
		String result = find(str);
		
		System.out.println("result: " + result);
		
	}
	
	public static String find(String str) {

	    String result = null;
	    
	    Set<String> set = new HashSet<String>();
	    
	    for (int i = str.length() - 1; i > 1; i --) {
	   
	    	if (result != null)
	    		break;
	    	
	        for (int j = 0; j <= str.length() - i; j ++) {
	        
	            String sub = str.substring(j, j + i);
	            
	            int size = set.size();
	            set.add(sub);
	            int size1 = set.size();
	            
	            
	            if (size == size1) {
	                result = sub;
	                break;
	            }
	            
	        }
	    }
	    return result;
	}	
}
