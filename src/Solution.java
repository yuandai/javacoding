package src;

import java.util.HashMap;
import java.util.Map;

class Solution {

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
      
      
      boolean result = true;
      int i = 0;
      while (i < s.length()) {
        boolean found = false;
        int j = i;
        while (j < s.length()) {
          
          String str = s.substring(i, j);
          System.out.println("1:" + i + " " + j + " " + str);
          if (mapDic.get(str) == null) {
            j ++;
            System.out.println("2:" + i + " " + j + " " + str);
            continue;
          }
          else {
            found = true;
            break;
          }
        }
        if (found) {
          //System.out.println("b assign" + i + " " + j);
          i = j;
          //System.out.println("a assign" + i + " " + j);

        }
        else {
          result = false;
          break;
        }
        
        if (!found)
         i ++;
      }
      
      System.out.println("result " + result);
    }
      
      
  }
