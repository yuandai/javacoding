package src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Balance {
  
  public static void main(String[] args) {
    
    int[] nums1 = {1,1,1,2,1};
    int[] nums2 = {2,1,1,2,1};
    
    boolean result = find(nums1);    
    System.out.println("nums1 result " + result);
    result = find(nums2);    
    System.out.println("nums2 result " + result);

    
  }
  
  private static boolean find(int[] nums) {
    
    boolean result = false;
    
    int sum = 0;
    
    List<Integer> sums = new ArrayList<Integer>();
    
    for (int i = 0; i < nums.length; i ++) {
      
      sum = sum + nums[i];
      
      sums.add(i, sum);
      
    }
    
    Iterator<Integer> it = sums.iterator();
    while(it.hasNext()) {
      
      Integer sumi = (Integer)it.next();
      
      if (sumi == sum - sumi) {
        result = true;
        break;
      }
      
      if (sumi > sum/2)
    	  break;
      
    }
    
    return result;
    
  }
  
}