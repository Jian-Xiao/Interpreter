package bin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenScanner {
	List<Character> legalCharacter = new ArrayList<>();

	public TokenScanner() {
		char[] legalChar = "0123456789+-*/^().".toCharArray();
		for (char a : legalChar)
			legalCharacter.add(a);
	}

	public List<String> getTokens(String text) {
		List<String> tokens = new ArrayList<>();
		String token = "";
		int status = 0;
		char[] charArray = text.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			// ¼ÇµÃ¼ÓÅÐ¶Ï
			if (!legalCharacter.contains(charArray[i])) {
				System.out.println("´Ê·¨´íÎó£º·Ç·¨×Ö·û");
				return null;
			}
			switch (status) {
			case 0:
				if (charArray[i] == '0') {
					status = 6;
					token += Character.toString(charArray[i]);
				} else if ('1' <= charArray[i] && charArray[i] <= '9') {
					status = 1;
					token += Character.toString(charArray[i]);
				} else if (charArray[i] == '+' || charArray[i] == '*' || charArray[i] == '/' || charArray[i] == '^'
						|| charArray[i] == '(' || charArray[i] == ')') {
					status = 4;
					token += Character.toString(charArray[i]);

				} else if (charArray[i] == '-') {
					status = 5;
					token += Character.toString(charArray[i]);
				} else {
					System.out.println("´Ê·¨´íÎó£ºÊäÈë´íÎó");
					return null;
				}
				break;

			case 1:
				if ('0' <= charArray[i] && charArray[i] <= '9') {
					status = 1;
					token += Character.toString(charArray[i]);
				} else if (charArray[i] == '.') {
					status = 2;
					token += Character.toString(charArray[i]);
				} else {
					tokens.add(token);
					token = "";
					status = 0;
					i -= 1;
				}
				break;
			case 2:
				if ('0' <= charArray[i] && charArray[i] <= '9') {
					token += Character.toString(charArray[i]);
					status = 3;

				}
				// ·ñÔò±¨´í
				else {
					System.out.println("´Ê·¨´íÎó£ºÊý×ÖÊäÈë´íÎó");
					return null;
				}
				break;
			case 3:
				if ('0' <= charArray[i] && charArray[i] <= '9') {
					token += Character.toString(charArray[i]);
					status = 3;
				} else {
					tokens.add(token);
					status = 0;
					i -= 1;
					token = "";
				}
				break;
			case 4:
				status = 0;
				tokens.add(token);
				token = "";
				i -= 1;
				break;
			case 5:
				if (charArray[i] == '0' && (i == 1 || charArray[i - 2] == '(')) {
					status = 6;
					token += Character.toString(charArray[i]);
				} else if ('1' <= charArray[i] && charArray[i] <= '9' && (i == 1 || charArray[i - 2] == '(')) {
					status = 1;
					token += Character.toString(charArray[i]);
				} else {
					tokens.add(token);
					status = 0;
					token = "";
					i -= 1;
				}
				break;
			case 6:
				if (charArray[i] == '.') {
					token += Character.toString(charArray[i]);
					status = 2;
				} else {
					tokens.add(token);
					status = 0;
					i -= 1;
					token = "";
				}
			}
		}
		tokens.add(token);
		return tokens;
	}

	public String getType(String s) {

		String result = "";
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
			result = "op";
		} else if (s.equals("(") || s.equals(")")) {
			result = "bracket";
		} else {
			result = "number";
		}
		return result;
	}

	public String toStrings(List<String> tokenList) {
		String tmp = "";
		for (String token : tokenList) {
			tmp += "<" + getType(token) + "," + token + "> \n";
		}
		return tmp;
	}

}
