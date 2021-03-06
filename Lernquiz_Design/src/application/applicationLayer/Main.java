package application.applicationLayer;

import application.applicationLayer.spiel.Frage;
import application.applicationLayer.spiel.Kategorie;
import application.applicationLayer.spiel.Spieler;
import application.applicationLayer.spiel.Spielfeld;
import application.presentationLayer.Controller;
import application.presentationLayer.View;

public class Main {

	public static void main(String[] args) {
		init();
		
		Fassade modelFront = new Fassade();	
		new Controller(modelFront);
		new View(modelFront);
				
		while (true) {
			modelFront.spielzugStarten();
		}
	}
	
	private static void init() {
		Spielfeld.getInstance().getSpieler().add(new Spieler("Hans"));
		Spielfeld.getInstance().getSpieler().add(new Spieler("Peter"));
		Spielfeld.getInstance().getSpieler().add(new Spieler("Simone"));
		Spielfeld.getInstance().getSpieler().add(new Spieler("Anna"));
		for (int i = 0; i < 3; i++) {
			Kategorie kat = new Kategorie("Kategorie " + i);
			for (int j = 0; j < 1; j++) {
				Frage frage = new Frage("Frage " + j, "Antwort 0", "Antwort 1", "Antwort 2", "Antwort 3", j % 4);
				kat.addFrage(frage);
			}
			Spielfeld.getInstance().getKategorien().add(kat);
		}
	}
}
