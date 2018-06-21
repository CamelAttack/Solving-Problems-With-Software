package basic;

import edu.duke.*;

public class HelloWorld {
	public void runHello() {
		FileResource res = new FileResource("src/basic//hello_unicode.txt");
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}
}
