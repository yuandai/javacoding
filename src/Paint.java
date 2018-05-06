package src;

public class Paint {
	
	private static int[][] screen1 = {
			{1,1,1,1,0,0,0,0,0,0},
			{1,1,1,1,0,0,0,0,0,0},
			{1,1,1,1,0,0,0,0,0,0},
			{1,1,1,1,0,0,0,0,0,0},
			{1,1,1,1,0,0,0,0,0,0},
			{1,1,1,1,0,0,0,0,0,0},
			{1,1,1,1,0,0,0,0,0,0},
			{1,1,1,1,0,0,0,0,0,0},
			{1,1,1,1,0,0,0,0,0,0},
	};
	
	private static int[][] screen2 = {
			{0,0,1,1,0,0,0,2,0,0},
			{0,1,1,1,0,0,0,0,2,0},
			{1,1,1,1,0,0,3,3,0,0},
			{1,1,1,1,0,3,0,0,3,0},
			{1,0,0,1,0,3,0,0,3,0},
			{1,0,0,0,0,0,3,3,0,0},
			{1,1,1,1,0,0,0,0,0,0},
			{1,1,1,1,0,0,0,0,0,3},
			{0,1,1,1,0,0,0,0,3,0},
	};
	
	public static void main(String args[]) {
		
		//screen1
		System.out.println("screen 1");
		for (int i = 0; i < screen1.length; i ++) {
			for (int j = 0; j < screen1[0].length; j ++) {
				System.out.print(screen1[i][j]);
			}
			System.out.println("");
		}
		
		System.out.println("x " + 8 + " y " + 9 + " color " + 3);
		paint(9, 8, 3, screen1);
	
		System.out.println("screen 1 after");
		for (int i = 0; i < screen1.length; i ++) {
			for (int j = 0; j < screen1[0].length; j ++) {
				System.out.print(screen1[i][j]);
			}
			System.out.println("");
		}
		
		//screen 2
		for (int k = 0; k < 5; k ++) {
			System.out.println("screen 2 round " + k);
			for (int i = 0; i < screen2.length; i ++) {
				for (int j = 0; j < screen2[0].length; j ++) {
					System.out.print(screen2[i][j]);
				}
				System.out.println("");
			}
		
			System.out.println("x " + (5+k) + " y " + (5+k) + " color " + k);
			paint(5 + k, 5 + k, k, screen2);
		
			System.out.println("screen 2 after round " + k);
			for (int i = 0; i < screen2.length; i ++) {
				for (int j = 0; j < screen2[0].length; j ++) {
					System.out.print(screen2[i][j]);
				}
				System.out.println("");
			}
		}

	}
	
	private static void paint(int x, int y, int color, int[][] screen) {
		
		if (screen == null || screen.length == 0 || screen[0].length == 0 || 
				y > screen.length - 1 || y < 0 || x > screen[0].length - 1 || x < 0)
			return;
		
		int orig = screen[y][x];
		if (orig == color)
			return;
		
		int ylen = screen.length;
		int xlen = screen[0].length;
		
				
		visit(x, y, xlen, ylen, color, orig, screen);
	}
	
	private static void visit(int x, int y, int xlen, int ylen, int color, int orig, int[][]screen) {
		
		//System.out.println("x " + x + " y " + " color " + color + " orig " + orig);
		if (screen[y][x] == orig) {
			screen[y][x] = color;
			
			if (x < xlen - 1) {
				visit(x + 1, y, xlen, ylen, color, orig, screen);
			}
			
			if (y < ylen - 1) {
				visit(x, y + 1, xlen, ylen, color, orig, screen);
			}
			
			if (x > 0) {
				visit(x - 1, y, xlen, ylen, color, orig, screen);
			}
			
			if (y > 0) {
				visit(x, y - 1, xlen, ylen, color, orig, screen);
			}
		}
		
	}
		
}
	

	


