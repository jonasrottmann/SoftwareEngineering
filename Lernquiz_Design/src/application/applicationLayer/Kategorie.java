package application.applicationLayer;

import java.util.Random;
import java.util.ArrayList;


public class Kategorie {

	private ArrayList<Frage> fragen;

	public Frage getRandomFrage() {
		return fragen.get(new Random().nextInt(fragen.size()));
	}
}
