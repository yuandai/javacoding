package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Roche {

/*
a cat ran across the road
the dog barked at the cat

cat -> 2
dog -> 1

[cat, dog]
*/
	public static void main(String[] args) {
		
		String page = new String("a cat ran across the road\r\n" + 
				"the dog barked at the cat");
		List<String> result = wordCount(page);
		System.out.println(result);
		
	}

public static List<String> wordCount(String page) {

    Map<String, Integer> lstMap = new HashMap<String, Integer>();
    
    //1. load
    StringTokenizer st = new StringTokenizer(page);
    
    while (st.hasMoreElements()) {
        String word = (String)st.nextElement();
        
        Integer count = (Integer)lstMap.get(word);
        if (count != null) {
            count ++;
            lstMap.put(word, count);
        } else {
            lstMap.put(word, 1);
        }
    }
    
    //2. sort
    //List<String> list = lstMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).lstMap(e -> new String(e.getKey(), e.getValue())).collect(Collectors.toList()); 
    
    //Collections.sort(lstMap, new Comparator<Map.Entry<String, Integer>>() {
    List<Map.Entry<String, Integer>> lstAry = new ArrayList<Map.Entry<String, Integer>>();
    Iterator<Map.Entry<String, Integer>> itAry = lstMap.entrySet().iterator();
    while (itAry.hasNext()) {
    	Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)itAry.next();
    	lstAry.add(entry);
    }
    Collections.sort(lstAry, new Comparator<Map.Entry<String, Integer>>() {

    
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        
            int count1 = ((Map.Entry<String, Integer>)o1).getValue();
            int count2 = ((Map.Entry<String, Integer>)o2).getValue();
            
            if (count1 < count2)
                return 1;
            else if (count1 == count2)
                return 0;
            else
                return -1;
        
        }
    
    });
    
    //3. output
    List<String> lst = new ArrayList<String>();
    
    //Iterator<Map.Entry<String, Integer>> it = lstMap.entrySet().iterator();
    Iterator<Map.Entry<String, Integer>> it = lstAry.iterator();
    while (it.hasNext()) {
    
        Map.Entry<String, Integer> entry = it.next();
        
        String word = entry.getKey();
        
        lst.add(word);
        
    }
    
    return lst;

}
}