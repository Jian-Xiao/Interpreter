package bin;

import java.util.List;
import java.util.ArrayList;

public class GrammarScanner {
	List<String> wenfa = new ArrayList<>();
	List<String>  tokens;
	String curString="";
	int current = 0;
    String errorMessage="”Ô∑®¥ÌŒÛ£∫something wrong";
	
	public GrammarScanner(List<String>  tokens) {
		this.tokens = tokens;
	}

	public List<String> analyse() {
		analyseM();
		if(current!=tokens.size()||errorMessage.length()!=20) {
			wenfa.clear();
			wenfa.add(errorMessage);
			System.out.println("”Ô∑®¥ÌŒÛ£∫something wrong");
			return wenfa;
		}
		return wenfa;
	}

	public void analyseM() {
		if (current<tokens.size()&&tokens.get(current).equals("+")) {
			current++;
			curString+="+";
			wenfa.add("M ->+E  ,"+curString);
			analyseE();
		} else if (current<tokens.size()&&tokens.get(current).equals("-")) {
			current++;
			curString+="-";
			wenfa.add("M ->-E  ,"+curString);
			analyseE();
		} else {
			wenfa.add("M ->E   ,"+curString);
			analyseE();
		}
	}

	private void analyseE() {
		wenfa.add("E ->TE` ,"+curString);
		analyseT();
		analyseE1();
	}

	private void analyseT() {
		wenfa.add("T ->FT` ,"+curString);
		analyseF();
		analyseT1();
	}

	private void analyseE1() {
		if (current<tokens.size()&&tokens.get(current).equals("+")) {
			current++;
			curString+="+";
			wenfa.add("E`->+TE`,"+curString);
			analyseT();
			analyseE1();
		} else if (current<tokens.size()&&(tokens.get(current)).equals("-")) {
			current++;
			curString+="-";
			wenfa.add("E`->-TE`,"+curString);
			analyseT();
			analyseE1();
		} else {
			wenfa.add("E`->¶≈   ,"+curString);
		}
	}

	private void analyseT1() {
		if (current<tokens.size()&&tokens.get(current).equals("*")) {
			current++;
			curString+="*";
			wenfa.add("T`->*FT`,"+curString);
			analyseF();
			analyseT1();
		} else if (current<tokens.size()&&tokens.get(current).equals("/")) {
			current++;
			curString+="/";
			wenfa.add("T`->/FT`,"+curString);
			analyseF();
			analyseT1();
		} else {
			wenfa.add("T`->¶≈   ,"+curString);
		}
	}

	private void analyseF() {
		if (current<tokens.size()&&tokens.get(current).equals("(")) {
			current++;
			curString+="(";
			wenfa.add("F ->(E) ,"+curString);
			analyseE();
			if (current<tokens.size()&&!tokens.get(current).equals(")")) {
				errorMessage="”Ô∑®¥ÌŒÛ£∫something wrong in )";
				System.out.println("”Ô∑®¥ÌŒÛ£∫something wrong in )");
			}
			curString+=")";			
			current++;
		} else {
			curString+=tokens.get(current);
			wenfa.add("F ->num ,"+curString);
			if(current<tokens.size()&&!(getType(tokens.get(current)).equals("number"))) {
				errorMessage="”Ô∑®¥ÌŒÛ£∫something wrong near "+tokens.get(current);
				System.out.println("”Ô∑®¥ÌŒÛ£∫something wrong near "+tokens.get(current));
			}
			current++;
		}
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
}
