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
				Frage frage = new Frage("Frage " + j, "Antowort 1", "Antowort 2", "Antowort 3", "Antowort 4", j % 4);
				kat.addFrage(frage);
			}
			Spielfeld.getInstance().kategorien.add(kat);
		}
	
		
		ModelFront modelFront = new ModelFront();
		
		
	
		
		
		
		
	}
}
