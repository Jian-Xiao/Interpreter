package bin;

import java.io.IOException;
import java.util.List;

import javax.script.ScriptException;

public class MainConsole {

	public static void main(String[] args) throws IOException, ScriptException {
		//读取文件
		String path = "C:\\Users\\Lenovo\\Desktop\\analyser\\ArithmeticExpression\\testFile\\6.txt";
		FileRead fileRead = FileRead.getInstance();
		fileRead.readFile(path);
		String testString = fileRead.getContent();
		System.out.println("表达式：\n"+testString);
		//词法分析
		TokenScanner scanner = new TokenScanner();
		List<String> result = scanner.getTokens(testString);
		if (result!=null) {
			System.out.println("词法分析：\n"+scanner.toStrings(result));
			//语法分析
			GrammarScanner scanner2 = new GrammarScanner(result);
			List<String> result2 = scanner2.analyse();
			if (result2!=null) {
				System.out.println("语法分析：");
				print(result2);
				//语义分析
				Calculator calculator = new Calculator();
				result.add(";");
				System.out.println("语义分析：\n"+calculator.calculateResult(result));
			}
		}
	}
	
	public static void print(List<String> result ) {
		for (String string : result) {
			System.out.println(string);
		}
	}
}
