package Locke;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Logger {
	public static void log(String message) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("output.txt", true), true);
	      out.write(message);
	      out.close();    
	}

}
