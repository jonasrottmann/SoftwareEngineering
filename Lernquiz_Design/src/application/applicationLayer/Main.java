package application.applicationLayer;

public class Main {

	public static void main(String[] args) {
		// Init
		for (int i = 0; i < 3; i++) {
			Spielfeld.getInstance().spieler.add(new Spieler());
		}
		for (int i = 0; i < 3; i++) {
			Kategorie kat = new Kategorie("Kategorie " + i);
			for (int j = 0; j < 1; j++) {
				Frage frage = new Frage("Frage " + j, "Antwort 1", "Antwort 2", "Antwort 3", "Antwort 4", j % 4);
				kat.addFrage(frage);
			}
			Spielfeld.getInstance().kategorien.add(kat);
		}
	
		
		ModelFront modelFront = new ModelFront();
		
		
	
		
		
		
		
	}
}
