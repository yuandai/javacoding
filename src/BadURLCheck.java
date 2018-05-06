package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * A Bloom filter is a probabilistic data structure: 
 * it tells us that the element either definitely is not in the set or may be in the set.
 * 
 * To test for membership, simply hash the string with the same hash functions, 
 * then see if those values are set in the bit vector. If they aren't, 
 * you know that the element isn't in the set. If they are, you only know that it might be, 
 * because another element or some combination of other elements could have set the same bits.
 * 
 * You can modify the false positive rate of your filter. 
 * A larger filter will have less false positives, and a smaller one more.
 */

public class BadURLCheck {
	private int totalCount = 0;
	private int count = 0;
	private int failure = 0;
	
	private DB badUrlDB = null;
	private Cache cache = null;
	
	public static void main(String[] args) {
		
		List<String> badUrlList = new ArrayList<String>();
		
		badUrlList.add("hacker");
		badUrlList.add("malware");
		badUrlList.add("identitytheft");
		
		//test hash function
		System.out.println("test hash function");
		for (String str : badUrlList) {
			System.out.println(Cache.hashKey(str, true));
			System.out.println(Cache.hashKey(str, false));
		}
		
		BadURLCheck check = new BadURLCheck();
		
		//init bad url db
		check.badUrlDB = new DB(badUrlList);
		
		System.out.println("hacker in bad url db: " + check.badUrlDB.contains("hacker"));
		System.out.println("");
		
		//init cache
		check.cache = new Cache();
		
		Set<String> badUrlSet = check.badUrlDB.getBadUrlSet();
		for (String str : badUrlSet) {
			check.cache.add(str);
		}
		
		//check bad url
		System.out.println("abc: " + check.checkBadUrl("abc"));
		System.out.println("google: " + check.checkBadUrl("google"));
		System.out.println("facebook: " + check.checkBadUrl("facebook"));
		System.out.println("youtube: " + check.checkBadUrl("youtube"));
		System.out.println("twitter: " + check.checkBadUrl("twitter"));
		System.out.println("amazon: " + check.checkBadUrl("amazon"));
		System.out.println("netflix: " + check.checkBadUrl("netflix"));
		System.out.println("doctor: " + check.checkBadUrl("doctor"));
		System.out.println("dental: " + check.checkBadUrl("dental"));
		
				
		System.out.println("hacker: " + check.checkBadUrl("hacker"));
		System.out.println("malware: " + check.checkBadUrl("malware"));
		System.out.println("identitytheft: " + check.checkBadUrl("identitytheft"));
		
		System.out.println("ab: " + check.checkBadUrl("ab"));
		System.out.println("gogle: " + check.checkBadUrl("gogle"));
		System.out.println("facebk: " + check.checkBadUrl("facebk"));
		System.out.println("hack: " + check.checkBadUrl("hack"));
		System.out.println("software: " + check.checkBadUrl("software"));
		System.out.println("identityprotect: " + check.checkBadUrl("identityprotect"));
		
		System.out.println("check count: " + check.totalCount +
				" db check count: " + check.count + " db check failure: " + check.failure + " db check failure rate (false positive rate): " + (float)check.failure*100/check.count);
						
	}
	
	public boolean checkBadUrl(String url) {
		boolean result = false;
		
		totalCount ++;
		
		boolean possible = cache.possibleContains(url);
		if (possible == false) {
			result = false;
		} else {
			System.out.println("check bad url db for " + url);
			result = badUrlDB.contains(url);
			
			count ++;
			if (result == false)
				failure ++;
		}
		
		return result;
	}
	
	
}

class Cache {
	
	int[] map = new int[27];
	
	public Cache() {
		for (int i = 0; i < map.length; i ++) {
			map[i] = 0;
		}
	}
	
	public boolean possibleContains(String badUrl) {
		boolean result = false;
		
		int key1 = hashKey(badUrl, true);
		int key2 = hashKey(badUrl, false);

		if (map[key1] == 1 || map[key2] == 1)
			result = true;
		
		return result;
	}
	
	public void add(String badUrl) {
		
		int key1 = hashKey(badUrl, true);
		int key2 = hashKey(badUrl, false);
				
		map[key1] = 1;
		map[key2] = 1;
	}
	
	protected static int hashKey(String name, boolean first)
	{
		if (name == null || name.length() == 0)
			return 26;
		char ch = name.charAt(0);
		if (!first)
			ch = name.charAt(name.length() - 1);
		
	    int x = Character.toLowerCase(ch)- 97;
	    if (x < 0 || x > 25)
	           x = 26;
	    
	    return x;
	}
}

class DB {
	
	private Set<String> badUrlSet = new HashSet<String>();
	
	public DB(List<String> badUrlList) {
		for (String str : badUrlList) {
			badUrlSet.add(str);
		}
	}
	
	public Set<String> getBadUrlSet() {
		return this.badUrlSet;
	}
	
	public void addBadUrl(String badUrl) {
		badUrlSet.add(badUrl);
	}
	
	public boolean contains(String badUrl) {
		return badUrlSet.contains(badUrl);
	}
	
}

