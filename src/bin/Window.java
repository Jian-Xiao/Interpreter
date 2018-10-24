package bin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.script.ScriptException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;

public class Window {

	private JFrame frame;
	private JTextField expression;
	private JTextField resultTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 504, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					calculate();
				} catch (IOException | ScriptException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		expression = new JTextField();
		expression.setColumns(10);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearBtn();
			}
		});

		resultTxt = new JTextField();
		resultTxt.setColumns(10);

		JLabel label = new JLabel("\u8868\u8FBE\u5F0F");

		JLabel label_1 = new JLabel("\u7ED3\u679C");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup().addGap(44)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(label_1).addContainerGap())
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addComponent(label)
												.addContainerGap())
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(resultTxt, Alignment.LEADING)
														.addComponent(expression, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
												.addGap(32)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(btnClear, GroupLayout.DEFAULT_SIZE, 105,
																Short.MAX_VALUE)
														.addComponent(btnCalculate, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGap(70))))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(90).addComponent(label)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(expression, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCalculate))
						.addGap(44).addComponent(label_1).addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(resultTxt,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnClear))
						.addGap(63)));
		frame.getContentPane().setLayout(groupLayout);
	}

	public void calculate() throws IOException, ScriptException {
		// 读取文件
		// String path =
		// "C:\\Users\\Lenovo\\Desktop\\analyser\\ArithmeticExpression\\testFile\\6.txt";
		// FileRead fileRead = FileRead.getInstance();
		// fileRead.readFile(path);
		String testString = expression.getText();
		System.out.println("表达式：\n" + testString);
		// 词法分析
		TokenScanner scanner = new TokenScanner();
		List<String> result = scanner.getTokens(testString);
		if (result.get(0).charAt(0)!= '词') {
			System.out.println("词法分析：\n" + scanner.toStrings(result));
			// 语法分析
			GrammarScanner scanner2 = new GrammarScanner(result);
			List<String> result2 = scanner2.analyse();
			if (result2.get(0).charAt(0) == 'M') {
				System.out.println("语法分析：");
				print(result2);
				// 语义分析
				Calculator calculator = new Calculator();
				result.add(";");
				String reString = calculator.calculateResult(result);
				System.out.println("语义分析：\n" + reString);
				resultTxt.setText(reString);
			} else
				resultTxt.setText(result2.get(0));
		}
		else
			resultTxt.setText(result.get(0));
	}

	public void print(List<String> result) {
		for (String string : result) {
			System.out.println(string);
		}
	}
	
	public void clearBtn() {
		resultTxt.setText("");
		expression.setText("");
	}
}
