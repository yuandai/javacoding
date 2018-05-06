package src.fb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * read 2 csv files about dinosaur leg length and weight and 
 * calculate dinosaur speed using:
 * 
 * speed = 9.8 X leg length / weight
 * 
 * print the dinosaur list in descending order of speed.
 * 
 * Notes: both file has same dinosaur name list (count>1), but order are different.
 */

public class Dinosaur {
	
	public static void main(String args[]) {
		
		String legFileName = "C:\\Users\\iandai\\ian\\javacoding\\src\\fb\\dinosaurleg.csv";
		String weightFileName = "C:\\Users\\iandai\\ian\\javacoding\\src\\fb\\dinosaurweight.csv";
		
		print(legFileName, weightFileName);
				
	}
	
	private static void print(String legName, String weightName) {
		
		BufferedReader legF = null;
		BufferedReader weightF = null;
		
		Map<String, Din> dinMap= new HashMap<String, Din>();
		
		try {
			legF = new BufferedReader(new FileReader(legName));
			weightF = new BufferedReader(new FileReader(weightName));
		} catch (FileNotFoundException e) {
			System.out.println("file not found: " + e.getMessage());
			return;
		}
		
		boolean done = false;
		while (!done) {
			String lnLeg = null;
			try {
				lnLeg = legF.readLine();
			} catch (IOException e) {
				System.out.println("leg read line error: " + e.getMessage());
				break;
			}
			if (lnLeg == null)
				done = true;
			else {
				StringTokenizer st = new StringTokenizer(lnLeg, ",");
				while (st.hasMoreElements()) {
					String name = (String)st.nextElement();
					String legLength = (String)st.nextElement();
					Din din = new Din();
					din.name = name;
					din.legLength = Integer.parseInt(legLength);
					dinMap.put(name, din);
				}
			}
		}
		
		done = false;
		while (!done) {
			String lnWeight = null;
			try {
				lnWeight = weightF.readLine();
			} catch (IOException e) {
				System.out.println("weight read line error: " + e.getMessage());
				break;
			}
			if (lnWeight == null)
				done = true;
			else {
				StringTokenizer st = new StringTokenizer(lnWeight, ",");
				while (st.hasMoreElements()) {
					String name = (String)st.nextElement();
					String weight = (String)st.nextElement();
					Din din = dinMap.get(name);
					din.weight = Integer.parseInt(weight);
					din.speed = 9.8f * (din.legLength / din.weight);
				}
			}
		}
		
		List<Din> dinList = new ArrayList<Din>();
		
		Iterator<Map.Entry<String,Din>> it = dinMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Din> dinEntry = it.next();
			Din din = dinEntry.getValue();
			dinList.add(din);
		}
		
		Collections.sort(dinList, new Comparator() {
			public int compare(Object obj1, Object obj2) {
				Din din1 = (Din) obj1;
				Din din2 = (Din) obj2;
				
				if (din1.speed < din2.speed)
					return 1;
				else if (din1.speed > din2.speed)
					return -1;
				else
					return 0;
			}
		});
		
		System.out.println("name, legLength, weight, speed");

		for (Din din : dinList) {
			System.out.print(din.name + ", ");
			System.out.print(din.legLength + ", ");
			System.out.print(din.weight + ", ");
			System.out.println(din.speed);
		}
		
		
		try {
		legF.close();
		weightF.close();
		} catch (IOException e) {
			System.out.println("file cannot be closed: " + e.getMessage());
			return;
		}
		
		return;
		
	}

}

class Din {
	String name;
	float legLength;
	float weight;
	float speed;
};

