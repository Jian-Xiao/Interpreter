package bin;

import java.util.List;

public class MainConsole {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TokenScanner scanner=new TokenScanner();
        String testString="-2.1-(-0.31)";
        List<String> test=scanner.getTokens(testString);
        if(test==null) {
            System.out.println("error");
            return;
        }
        for(String s:test) {
            System.out.println(s+"\n");
            
        }
        
        
    }

}
