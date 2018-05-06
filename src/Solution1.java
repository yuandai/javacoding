package src;

import java.util.HashMap;
import java.util.Map;

class Solution1 {

    public static void main(String[] args) {
      String theDict[] = {
              "is", "a", "view", "this", "inter", "sy", "ape", "intermediate",
              "interview", "fors", "for", "syapse", "an", "ich", "which", "amazing",
              "any", "com", "comp", "company", "tow", "kfor", "work", "for", "or",
      };

      String s = "thisisaninterviewforsyapsewhichisanamazingcompanytoworkfor";
      

      Map<String, String> mapDic = new HashMap<String, String>();
      
      for (int i = 0; i < theDict.length; i ++) {
        
        String str = theDict[i];
        
        mapDic.put(str, str);
        
      }
      
      boolean result = parse(mapDic, s);
      
      System.out.println("Result: " + result);
      
    }
    
    private static boolean parse(Map<String, String> mapDic, String s) {
    	
      if (s.length() == 0)
    	  return true;
      
      
        boolean found = false;
        int j = 1;
        while (j <= s.length()) {
          
          String str = s.substring(0, j);
          if (mapDic.get(str) == null) {
            j ++;
            continue;
          }
          else {
            found = parse(mapDic, s.substring(j, s.length()));
            if (found) {
                System.out.println("found: " + j + " " + str);
            	break;
            }
            else
            	j ++;
          }
        }
      
      
      return found;
    }
      
      
  }
