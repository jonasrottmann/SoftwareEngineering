package application.applicationLayer;

import java.util.Random;
import java.util.ArrayList;


public class Kategorie {

	private ArrayList<Frage> fragen = new ArrayList<Frage>();
	private String name;
	
	public Kategorie(String name) {
		this.name = name;
	}
	
	public Frage getRandomFrage() {
		return fragen.get(new Random().nextInt(fragen.size()-1));
	}
	
	public void addFrage(Frage frage) {
		fragen.add(frage);
	}
}
