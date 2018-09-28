package bin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenScanner {
    List<Character> legalCharacter=new ArrayList<>();
    public TokenScanner() {
        char[] legalChar="0123456789+-*/^().".toCharArray();
        for(char a:legalChar)
            legalCharacter.add(a);
    }
    public List<String> getTokens(String text){
        //Map<String,String> tokens=new HashMap<>();
        List<String> tokens=new ArrayList<>();
        String token="";
        int status=0;
        char[] charArray=text.toCharArray();
        //boolean isNumber=false;
        for(int i=0;i<charArray.length;i++) {
            //记得加判断
            if(!legalCharacter.contains(charArray[i])) {
                System.out.println("非法字符");
                return null;
            }
            switch(status) {
            case 0:
                if(charArray[i]=='0') {
                    status=6;
                    token+=Character.toString(charArray[i]);
                }else if('1'<=charArray[i]&&charArray[i]<='9') {
                    status=1;
                    token+=Character.toString(charArray[i]); 
                }else if(charArray[i]=='+'||charArray[i]=='*'||
                        charArray[i]=='/'||charArray[i]=='^'||charArray[i]=='('||charArray[i]==')') {
                    status=4;
                    token+=Character.toString(charArray[i]);
                    
                }else if(charArray[i]=='-') {
                    status=5;
                    token+=Character.toString(charArray[i]);
                }
                else {
                    System.out.println("输入错误");
                    return null;
                }
                break;
                    
            case 1:
                if('0'<=charArray[i]&&charArray[i]<='9') {
                    status=1;
                    token+=Character.toString(charArray[i]);      
                }
                else if(charArray[i]=='.') {
                    status=2;
                    token+=Character.toString(charArray[i]);
                }
                else {
                    tokens.add(token);
                    token="";
                    status=0;
                    i-=1;
                }
                break;
            case 2:
                if('0'<=charArray[i]&&charArray[i]<='9') {
                    token+=Character.toString(charArray[i]);
                    status=3;
                    
                }
                //否则报错
                else {
                    System.out.println("数字输入错误");
                    return null;
                }
                break;
            case 3:
                if('0'<=charArray[i]&&charArray[i]<='9') {
                    token+=Character.toString(charArray[i]);
                    status=3;
                }
                else {
                    tokens.add(token);
                    status=0;
                    i-=1;
                    token="";
                }
                break;
            case 4:
                status=0;
                tokens.add(token);
                token="";
                i-=1;
                break;
            case 5:
                if(charArray[i]=='0'&&(i==1||charArray[i-2]=='(')) {
                    status=6;
                    token+=Character.toString(charArray[i]);
                }
                else if('1'<=charArray[i]&&charArray[i]<='9'&&(i==1||charArray[i-2]=='(')) {
                    status=1;
                    token+=Character.toString(charArray[i]);
                }
                else {
                    tokens.add(token);
                    status=0;
                    token="";
                    i-=1;
                }
                break;
            case 6:
                if(charArray[i]=='.') {
                    token+=Character.toString(charArray[i]);
                    status=2;
                }
                else {
                    tokens.add(token);
                    status=0;
                    i-=1;
                    token="";
                }
            }
        }
        tokens.add(token);
        return tokens;
    }
    
    public Map<String, String> getType(List<String> tokenList){
        Map<String, String> result=new HashMap<String, String>();
        for(String s:tokenList) {
            if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")||s.equals("^")) {
                result.put(s,"op");
            }
            else if(s.equals("(")||s.equals(")")){
                result.put(s, "bracket");
            }
            else{
                
                result.put(s,"number");
            }
            
        }
        return result;
        
    }
    
}
