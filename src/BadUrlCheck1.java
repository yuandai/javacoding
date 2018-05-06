package src;

import java.util.ArrayList;
import java.util.BitSet;
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
 * 
 * The false positive rate will be approximately (1-e-kn/m)k, 
 * where: k hashes, m bits in the filter, number n of elements you expect to insert
 * 
 */

public class BadUrlCheck1 {
	private int totalCount = 0;
	private int count = 0;
	private int failure = 0;
	
	private DB1 badUrlDB = null;
	private Cache1 cache = null;
	
	public static void main(String[] args) {
		
		List<String> badUrlList = new ArrayList<String>();
		
		//prepare bad url list
		badUrlList.add("hacker.com");
		badUrlList.add("malware.com");
		badUrlList.add("identitytheft.com");		
		
		BadUrlCheck1 check = new BadUrlCheck1();
		
		//init bad url db
		check.badUrlDB = new DB1(badUrlList);
		
		System.out.println("hacker.com in bad url db: " + check.badUrlDB.contains("hacker.com"));
		
		//init cache
		check.cache = new Cache1();
		
		Set<String> badUrlSet = check.badUrlDB.getBadUrlSet();
		for (String str : badUrlSet) {
			check.cache.add(str);
		}
		
		//test hash function
		System.out.println("test hash function");
		for (String str : badUrlList) {
			System.out.println(check.cache.hashKey(str, 1));
			System.out.println(check.cache.hashKey(str, 2));
		}
		System.out.println("");
		
		//check bad url
		System.out.println("abc.com: " + check.checkBadUrl("abc.com"));
		System.out.println("google.com: " + check.checkBadUrl("google.com"));
		System.out.println("facebook.com: " + check.checkBadUrl("facebook.com"));
		System.out.println("youtube.com: " + check.checkBadUrl("youtube.com"));
		System.out.println("twitter.com: " + check.checkBadUrl("twitter.com"));
		System.out.println("amazon.com: " + check.checkBadUrl("amazon.com"));
		System.out.println("netflix.com: " + check.checkBadUrl("netflix.com"));
		System.out.println("doctor.com: " + check.checkBadUrl("doctor.com"));
		System.out.println("dental.com: " + check.checkBadUrl("dental.com"));
		
				
		System.out.println("hacker.com: " + check.checkBadUrl("hacker.com"));
		System.out.println("malware.com: " + check.checkBadUrl("malware.com"));
		System.out.println("identitytheft.com: " + check.checkBadUrl("identitytheft.com"));
		
		System.out.println("ab.com: " + check.checkBadUrl("ab.com"));
		System.out.println("gogle.com: " + check.checkBadUrl("gogle.com"));
		System.out.println("facebk.com: " + check.checkBadUrl("facebk.com"));
		System.out.println("hack.com: " + check.checkBadUrl("hack.com"));
		System.out.println("software.com: " + check.checkBadUrl("software.com"));
		System.out.println("identityprotect.com: " + check.checkBadUrl("identityprotect.com"));
		
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

class Cache1 {
	
	private static final int MAX_HASHES = 8; //Number of Hash Functions
	private static final int LOG2_NOBITS = 16;
	private static final long HSTART = 0xBB40E64DA205B064L;
	private static final long HMULT = 7664345821815920749L;
	private static final long[] byteTable;
	
	private final BitSet data; //Bloom filter of size 2^LOG2_NOBITS
	private final int hashMask;
	
	static {
		byteTable = new long[256 * MAX_HASHES];
		long h = 0x544B2FBACAAF1684L;
		for (int i = 0; i < byteTable.length; i++) {
			for (int j = 0; j < 31; j++)
				h = (h >>> 7) ^ h; 
			h = (h << 11) ^ h; h = (h >>> 10) ^ h;
			byteTable[i] = h;
		}
	}
	
	public Cache1() {
	    this.data = new BitSet(1 << LOG2_NOBITS);
	    this.hashMask = (1 << LOG2_NOBITS) - 1;
	}
	
	public boolean possibleContains(String badUrl) {
		boolean result = true;
		
		for (int i = 0; i < MAX_HASHES; i ++) {
			long key = hashKey(badUrl, i);
			
			int bitNo = (int)(key) & this.hashMask;
			if (!data.get(bitNo)) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	public void add(String badUrl) {
		
		for (int i = 0; i < MAX_HASHES; i ++) {
			long key = hashKey(badUrl, i);
			int bitNo = (int) (key) & this.hashMask;
		    data.set(bitNo);
		}
				
	}
	
	  protected long hashKey(String s, int hcNo) {
		    long h = HSTART;
		    final long hmult = HMULT;
		    final long[] ht = byteTable;
		    int startIx = 256 * hcNo;
		    for (int len = s.length(), i = 0; i < len; i++) {
		      char ch = s.charAt(i);
		      h = (h * hmult) ^ ht[startIx + (ch & 0xff)];
		      h = (h * hmult) ^ ht[startIx + ((ch >>> 8) & 0xff)];
		    }
		    return h;
		  }

}

class DB1 {
	
	private Set<String> badUrlSet = new HashSet<String>();
	
	public DB1(List<String> badUrlList) {
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

