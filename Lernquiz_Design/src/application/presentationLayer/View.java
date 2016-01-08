package application.presentationLayer;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import application.applicationLayer.ModelFront;
import application.applicationLayer.Spieler;
import application.applicationLayer.Spielfeld;
import application.applicationLayer.Wissensstreiter;

public class View implements Observer {
	
	private ModelFront model;
	private Controller controller;
	
	public void update(Observable obs, Object arg) {		
		this.model = (ModelFront) obs;
		
		switch(model.getZustand()) {
			case NeuerSpielzug:
				System.out.println(String.format("\n\n%s ist jetzt am Zug. Viel Gl�ck!", model.getUseCaseController1().getAktuellerSpieler().getName()));
				break;
			case NeuerSpielzug_FAIL:
				for (int i : model.getUseCaseController1().getW�rfelErgebnisse()) {
					System.out.println(String.format("Du hast eine %d gew�rfelt.", i));
				}
				break;
			case NeuerSpielzug_SUCCESS:
				for (int i : model.getUseCaseController1().getW�rfelErgebnisse()) {
					System.out.println(String.format("Du hast eine %d gew�rfelt.", i));
				}
				printEigeneWissensstreiter();
				printFremdeWissensstreiter();
				break;
			case WarteAufWissensstreiterEingabe:
				System.out.println(String.format("Du hast eine %d gew�rfelt", model.getUseCaseController1().getW�rfelErgebnisse().get(model.getUseCaseController1().getW�rfelErgebnisse().size() - 1)));
				printEigeneWissensstreiter();
				printFremdeWissensstreiter();
				System.out.println("Welcher Wissensstreiter soll bewegt werden?");
				break;
			case WarteAufAntwortEingabe:
				// P0 kollidiert mit H2
				// Peter testet Hans
				// Frage
				// Antwort 1	Antwort 2
				// Antwort 3	Antwort 4
				System.out.println("W�hle die richtige Antwort aus.");
				break;
			case WarteAufKategorieEingabe:
				System.out.println("Warte auf Eingabe");
				break;
			case WarteAufSelbsttestEingabe:
				System.out.println("Warte auf Eingabe");
				break;
			
		}
	}
	
	
	
	private void printEigeneWissensstreiter() {
		Spieler eigenerSpieler = model.getUseCaseController1().getAktuellerSpieler();
		int aktuellerSpielerIndex = Spielfeld.getInstance().spieler.indexOf(eigenerSpieler);
		ArrayList<Wissensstreiter> wissensstreiterAufSpielfeld = Spielfeld.getInstance().spieler.get(aktuellerSpielerIndex).getAlleWissensstreiterAufSpielfeld();
		
		System.out.println(String.format("%d von 3 Wissensstreiter auf dem Spielfeld.", wissensstreiterAufSpielfeld.size()));
		System.out.println("Die Wissensteiter stehen auf folgenden Feldern:");
		printWissenstreiterPositionen(eigenerSpieler);
	}
	
	private void printFremdeWissensstreiter() {
		Spieler eigenerSpieler = model.getUseCaseController1().getAktuellerSpieler();
		int eigenerSpielerIndex = Spielfeld.getInstance().spieler.indexOf(eigenerSpieler);
		
		for (int i = 0; i < Spielfeld.getInstance().spieler.size(); i++) {
			if (i != eigenerSpielerIndex) {
				printWissenstreiterPositionen(Spielfeld.getInstance().spieler.get(i));
			}
		}
	}
	
	private void printWissenstreiterPositionen(Spieler spieler) {
		int aktuellerSpielerIndex = Spielfeld.getInstance().spieler.indexOf(spieler);
		ArrayList<Wissensstreiter> wissensstreiterAufSpielfeld = Spielfeld.getInstance().spieler.get(aktuellerSpielerIndex).getAlleWissensstreiterAufSpielfeld();

		if (Spielfeld.getInstance().spieler.get(aktuellerSpielerIndex).alleWissensstreiterImHeimatfeld()) {
			// ...
		} else {
			for (int i = 0; i < Spielfeld.getInstance().spieler.get(aktuellerSpielerIndex).getAlleWissensstreiterAufSpielfeld().size(); i++) {
				System.out.print(String.format("%s%d auf Feld %d\t", spieler.getName().charAt(0), i, wissensstreiterAufSpielfeld.get(i).getPosition()));			
			}
			System.out.println();
		}
	}
}
