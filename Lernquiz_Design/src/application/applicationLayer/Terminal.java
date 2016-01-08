package application.applicationLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {

	private BufferedReader console;
	
	public Terminal() {
		console = new BufferedReader(new InputStreamReader(System.in));
	}

	public String readLine() {
		try {
			return console.readLine();
		} catch (IOException e) {
			return "\n";
		}
	}
}
