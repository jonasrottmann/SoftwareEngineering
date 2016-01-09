package application.applicationLayer.spiel;

import java.util.Random;
import java.util.ArrayList;


public class Kategorie {

	private final ArrayList<Frage> fragen = new ArrayList<Frage>();
	private final String name;
	
	public Kategorie(String name) {
		this.name = name;
	}
	
	public Frage getRandomFrage() {
		return fragen.get(new Random().nextInt((fragen.size()-1) < 1 ? 1 : 1));
	}
	
	public void addFrage(Frage frage) {
		fragen.add(frage);
	}
	
	public String getName() {
		return name;
	}
}
