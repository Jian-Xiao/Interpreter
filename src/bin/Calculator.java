package bin;


import java.io.IOException;
import java.util.List;
import javax.script.*;


public class Calculator {

	public  boolean signal = true;
	public  String str = "";
	public  int lineNum = 0;
	
	public  LinkedListStack testOne = new LinkedListStack();
	public  LinkedListStack testTwo = new LinkedListStack();

	
	public Calculator() throws IOException {
		
	}

	
	public  String calculateResult(List<String> tokens) throws ScriptException {
		testOne.push("\0");
		testTwo.push("\0");	
		for (String string : tokens) {
			adjustStack(string);
 		}
		return (String)testOne.pop();
	}

	
	public  void adjustStack(String kk) throws ScriptException {
		while (true) {
			// 1.push数字
			if (judgeIfNumber(kk)) {
				testOne.push(kk);
				break;
			}
			// 2.判断结束
			else if (kk.equals(";") && ((String) testTwo.top()).equals("\0")) {
				break;
			}
			// 3.push 括号
			else if (kk.equals("(")) {
				testTwo.push(kk);
				break;
			}
			// 4.push 符号
			else if (priority(kk) > priority((String) testTwo.top())) {
				testTwo.push(kk);
				break;
			}
			// 5.调整括号
			else if (kk.equals(")") && ((String) testTwo.top()).equals("(")) {
				testTwo.pop();
				break;
			}
			// 6.运算
			else if (priority(kk) <= priority((String) testTwo.top())) {
				double a = Double.parseDouble((String) testOne.pop());
				double b=0;
				if(!((String) testOne.top()).equals("\0"))
					b = Double.parseDouble((String) testOne.pop());
				
				String c = (String) testTwo.pop();
				switch (c) {
				case "+":
					b = b + a;
					break;
				case "-":
					b = b - a;
					break;
				case "*":
					b = b * a;
					break;
				case "/":
					// CaseHandle 非法计算(除法)
					if (a == 0) {
						System.out.println("语义错误: line " + lineNum
								+ " Invalid calculation happend for the denominator is 0 ");
					}
					b = b / a;
					break;
				}
				testOne.push("" + b);
			}
		}
	}

	public  boolean judgeIfNumber(String kk) {
		if (!kk.equals(";")&&getType(kk).equals("number")) {
			return true;
		}
		return false;
	}

	

	public  boolean judgeIfOperation(String kk) {
		if(getType(kk).equals("op"))
			return true;
		return false;
	}

	public  int priority(String object) {
		int k;

		if (object.equals("*") || object.equals("/")) {
			k = 2;
		} else if (object.equals("+") || object.equals("-")) {
			k = 1;
		} else if (object.equals("(") || object.equals(")")) {
			k = 0;
		} else {
			k = -1;
		}
		return k;
	}

	public  String getType(String s) {

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
