package src;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	
	private HashMap<String,String> map;
	
	public MapTest() {
		map = new HashMap<String, String>();
		
		map.put("a", "A");
		map.put("b", "B");
		map.put("c", "C");
	}
	
	public HashMap<String, String> getMap() {
		return map;
	}
	
	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}
	
	public void printMap() {
		Set entrySet = map.entrySet();
		Iterator it = entrySet.iterator();
		
		while (it.hasNext()) {
			Map.Entry key = (Map.Entry)it.next();
			String value = map.get(key.getKey());
			System.out.println("value " + value);
		}
		
	}

}
