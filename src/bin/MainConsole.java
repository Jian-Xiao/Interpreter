package bin;

import java.io.IOException;
import java.util.List;

import javax.script.ScriptException;

public class MainConsole {

	public static void main(String[] args) throws IOException, ScriptException {
		//��ȡ�ļ�
		String path = "C:\\Users\\Lenovo\\Desktop\\analyser\\ArithmeticExpression\\testFile\\6.txt";
		FileRead fileRead = FileRead.getInstance();
		fileRead.readFile(path);
		String testString = fileRead.getContent();
		System.out.println("���ʽ��\n"+testString);
		//�ʷ�����
		TokenScanner scanner = new TokenScanner();
		List<String> result = scanner.getTokens(testString);
		if (result!=null) {
			System.out.println("�ʷ�������\n"+scanner.toStrings(result));
			//�﷨����
			GrammarScanner scanner2 = new GrammarScanner(result);
			List<String> result2 = scanner2.analyse();
			if (result2!=null) {
				System.out.println("�﷨������");
				print(result2);
				//�������
				Calculator calculator = new Calculator();
				result.add(";");
				System.out.println("���������\n"+calculator.calculateResult(result));
			}
		}
	}
	
	public static void print(List<String> result ) {
		for (String string : result) {
			System.out.println(string);
		}
	}
}
