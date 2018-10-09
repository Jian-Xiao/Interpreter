package bin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {

	private static FileRead instance = new FileRead();

	private static String fileContent = "";

	private FileRead() {
	}

	public static FileRead getInstance() {
		return instance;
	}

	// readFile
	public  void readFile(String path) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(path));
		String line = read.readLine();
		while (line != null) {
			fileContent = fileContent + line;
			line = read.readLine();
		}
		read.close();
	}

	// getContent
	public String getContent() {
		return fileContent;
	}

	// clearContent
	public void clear() {
		fileContent = "";
	}

}
