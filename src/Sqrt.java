package src;

/*
 * Use pre-calculated table and binary search to implement sqrt() without calling Math.sqrt():
 * 
 * 1. Since only positive sqrt are pre-calculated, negative input is considered as invalid
 * 2. The larger MAX, the more sqrt it can calculate; input beyond the pre-calculated table is considered as invalid
 * 3. The larger DEGREE, the more accurate the result will be, but more memory will be used to store pre-calculated table
 */

public class Sqrt {
	private static final int MAX = 100;
	private static final int DEGREE = 40;
	
	static NumSqrt[] table = new NumSqrt[MAX * DEGREE];
	
	static {
		
		for (int i = 0; i < MAX; i ++) {
			for (int j = 0; j < DEGREE; j ++) {
				float sqrt = (i + j * 1/new Float(DEGREE));
				float num = sqrt * sqrt;
				table[i * DEGREE + j] = new NumSqrt(num, sqrt);
			}
		}
		
	}
	
	public static void main(String args[]) {
		
		System.out.println("sqrt of 6.7: " + sqrt(6.7f));
		System.out.println("sqrt of 20.7: " + sqrt(20.7f));
		System.out.println("sqrt of 9000.7: " + sqrt(9000.7f));
		System.out.println("sqrt of 10000.7: " + sqrt(10000.7f));
		System.out.println("sqrt of 0: " + sqrt(0));
		System.out.println("sqrt of -0.25: " + sqrt(-0.25f));
		
	}
	
	private static float sqrt(float num) {
		
		float result = -1f;
		
		float max = (MAX - 1 + (DEGREE - 1) * 1/new Float(DEGREE)) * (MAX - 1 + (DEGREE - 1) * 1/new Float(DEGREE));
		
		if (num <= max && num >= 0)		
			result = binSearch(num, 0, MAX * DEGREE - 1);
				
		return result;
	}
	
	private static float binSearch(float num, int start, int end) {
		float result = 0.0f;
		
		if (start == end || start + 1 == end)
			result = table[start].sqrt;
		else {
		
			int middleIndex = start + (end - start)/2;
			float middleNum = table[middleIndex].num;
			
			if (middleNum == num)
				result = table[middleIndex].sqrt;
			else if (middleNum < num)
				result = binSearch(num, middleIndex + 1, end);
			else
				result = binSearch(num, start, middleIndex - 1);
		
		}
		
		return result;
	}

}

class NumSqrt {
	float num;
	float sqrt;
	
	public NumSqrt(float num, float sqrt) {
		this.num = num;
		this.sqrt = sqrt;
	}
}
