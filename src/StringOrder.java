package src;

public class StringOrder {

    public static void main(String args[]) {
    
        String str = "abcdefg";
        
        str = strOrd(str);       
        
        System.out.println("str:" + str);
        
    }
        

    private static String strOrd(String s) {
        char[] chArray = s.toCharArray();    	
    
        int len = chArray.length;
        
        for (int i = 0; i < len/2; i ++) {
        
            char tmp = ' ';
            tmp = chArray[i];
            chArray[i] = chArray[len - 1 - i];
            chArray[len - 1 - i] = tmp;
            
        }
        
        s = new String(chArray);
       
        return s;
    }
}