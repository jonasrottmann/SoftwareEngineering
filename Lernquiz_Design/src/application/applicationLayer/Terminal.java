package application.applicationLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {

	private BufferedReader konsole;
	
	public Terminal() {
		konsole = new BufferedReader(new InputStreamReader(System.in));
	}

	public String readLine() {
		try {
			return konsole.readLine();
		} catch (IOException e) {
			return "\n";
		}
	}
}
