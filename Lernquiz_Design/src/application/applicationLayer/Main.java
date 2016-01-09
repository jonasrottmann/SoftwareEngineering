package application.applicationLayer;

import application.applicationLayer.spiel.Frage;
import application.applicationLayer.spiel.Kategorie;
import application.applicationLayer.spiel.Spieler;
import application.applicationLayer.spiel.Spielfeld;
import application.presentationLayer.Controller;
import application.presentationLayer.View;

public class Main {

	public static void main(String[] args) {
		// Init
		Spielfeld.getInstance().getSpieler().add(new Spieler("Hans"));
		Spielfeld.getInstance().getSpieler().add(new Spieler("Peter"));
		Spielfeld.getInstance().getSpieler().add(new Spieler("Simone"));
		Spielfeld.getInstance().getSpieler().add(new Spieler("Anna"));
		for (int i = 0; i < 3; i++) {
			Kategorie kat = new Kategorie("Kategorie " + i);
			for (int j = 0; j < 1; j++) {
				Frage frage = new Frage("Frage " + j, "Antwort 1", "Antwort 2", "Antwort 3", "Antwort 4", j % 4);
				kat.addFrage(frage);
			}
			Spielfeld.getInstance().getKategorien().add(kat);
		}
	
		View v = new View();
		Controller c = new Controller();
		Fassade modelFront = new Fassade(v, c);		
				
		while (true) {
			modelFront.spielzugStarten();
		}
	}
}
