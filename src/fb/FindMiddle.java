package src.fb;

import java.util.HashMap;
import java.util.Map;

public class FindMiddle {
	
	public static void main(String args[]) {
		
		int[] nums = {1,2,8,9,1,0,1};
		int[][] result = findMiddle(nums);
		print(result);
		
		int[] nums1 = {1,2,8,9,1,1,1};
		int[][] result1 = findMiddle(nums1);
		print(result1);
		
		int[] nums2 = null;
		int[][] result2 = findMiddle(nums2);
		print(result2);

		int[] nums3 = {};
		int[][] result3 = findMiddle(nums3);
		print(result3);

		int[] nums4 = {1};
		int[][] result4 = findMiddle(nums4);
		print(result4);

	}
	
	private static void print(int[][] result) {
		
		if (result != null && result[0] != null && result[1] != null) {
			for (int n : result[0])
				System.out.print(n + ",");
			System.out.println("");
			for (int n : result[1])
				System.out.print(n + ",");
			System.out.println("");
		} else
			System.out.println("no match foound!");
						
	}
	
	private static int[][] findMiddle(int[] nums) {
		int[][] result = new int[2][];
		
		if (nums == null || nums.length == 0)
			return result;
		
		Map<Integer, Integer> index = new HashMap<Integer, Integer>();
		
		int sum = 0;
		for (int n = 0; n < nums.length; n ++) {
			sum = sum + nums[n];
			index.put(sum, n);
		}
		
		if (sum % 2 == 1)
			return result;
		
		int halfSum = sum/2;
		
		Integer halfIndex = index.get(halfSum);
		
		if (halfIndex == null)
			return result;
		
		result[0] = new int[halfIndex + 1];
		for (int i = 0; i <= halfIndex; i ++)
			result[0][i] = nums[i];
		
		result[1] = new int[nums.length - halfIndex - 1];
		for (int j = halfIndex + 1; j < nums.length; j ++) 
			result[1][j - halfIndex - 1] = nums[j];				
		
		return result;
	}

}
