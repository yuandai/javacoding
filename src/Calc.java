package src;

/*
 * Amazon phone interview.
 */
import java.util.Stack;

public class Calc {
	
	public static void main(String[] args) {
		
		String exp = "1 + (7 + 4)*2*3 + (6/3 - 1)";
		int ret = calc(exp);
		System.out.println("The result of "+ exp + " is: " + ret);
						
	}
	
	private static int calc(String exp) {
		int ret = 0;
		Stack<String> st = new Stack<String>();
		StringBuffer subExp = new StringBuffer();
		
		boolean isFirstNum = true;
		String currOp = "";
		boolean isSubExp = false;
		for (int i = 0; i < exp.length(); i ++) {
			
			String ch = exp.substring(i, i + 1);
			
			if (ch.equals(" "))
				continue;
			
			if (isSubExp) {
				if (!isCloseBracket(ch)) {
					subExp.append(ch);
				} else {
					isSubExp = false;
					String subExpStr = subExp.toString();
					int result = calc(subExpStr);
					st.push(Integer.toString(result));
					subExp = new StringBuffer();
				}
				continue;
			}
			
			if (isNum(ch)) {
				
				if (isFirstNum) {
					
					st.push(ch);
					isFirstNum = false;
					
				} else {
					
					String nextOp = null;
					if (i + 2 < exp.length()) 
						nextOp = exp.substring(i + 1, i + 2);
					if (nextOp != null && isLowOp(currOp) && isHighOp(nextOp)) {
						st.push(ch);
					} else {
						String op = st.pop();
						String firstNum = st.pop();
							
						int result = calculate(op, firstNum, ch);
						st.push(Integer.toString(result));
					}					
					
				}
				
			} else if (isLowOp(ch) || isHighOp(ch)) {
				
				currOp = ch;
				st.push(ch);
				
			} else if (isOpenBracket(ch)) {
				isSubExp = true;
			}
			
		}
		
		if (st.size() > 0)
			ret = Integer.parseInt(st.pop());
		
		String secondNum = Integer.toString(ret);
		while (st.size() > 1) {
			String op = st.pop();
			String firstNum = st.pop();
			ret = calculate(op, firstNum, secondNum);
			secondNum = Integer.toString(ret);
		}
							
		return ret;
	}
	
	private static int calculate(String op, String fstNum, String secNum) {
		int ret = 0;
		
		switch (op) {
		case "+":
			ret = Integer.parseInt(fstNum) + Integer.parseInt(secNum);
			break;
		case "-":
			ret = Integer.parseInt(fstNum) - Integer.parseInt(secNum);
			break;
		case "*":
			ret = Integer.parseInt(fstNum) * Integer.parseInt(secNum);
			break;
		case "/":
			ret = Integer.parseInt(fstNum) / Integer.parseInt(secNum);
			break;
		}
		
		return ret;
	}
	
	private static boolean isNum(String ch) {
		
		boolean ret = true;
		
		try {
			Integer.parseInt(ch);
		} catch (NumberFormatException e) {
			ret = false;
		}
		
		return ret;
	}
	
	private static boolean isHighOp(String op) {
		
		boolean ret = true;
		
		if (op.equals("*") || op.equals("/")) 
			ret = true;
		else
			ret = false;
		
		return ret;
		
	}
	
	private static boolean isLowOp(String op) {
		
		boolean ret = true;
		
		if (op.equals("+") || op.equals("-")) 
			ret = true;
		else
			ret = false;
		
		return ret;
		
	}		

	private static boolean isOpenBracket(String op) {
		
		boolean ret = true;
		
		if (op.equals("(")) 
			ret = true;
		else
			ret = false;
		
		return ret;
		
	}
	
	private static boolean isCloseBracket(String op) {
		
		boolean ret = true;
		
		if (op.equals(")")) 
			ret = true;
		else
			ret = false;
		
		return ret;
		
	}


}
