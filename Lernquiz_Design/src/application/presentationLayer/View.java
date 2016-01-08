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
				System.out.println(String.format("\n\nSpieler %d ist jetzt am Zug. Viel Glück!", model.getUseCaseController1().getAktuellerSpieler()));
				break;
			case NeuerSpielzug_FAIL:
				for (int i : model.getUseCaseController1().getWürfelErgebnisse()) {
					System.out.println(String.format("Du hast eine %d gewürfelt.", i));
				}
				break;
			case NeuerSpielzug_SUCCESS:
				for (int i : model.getUseCaseController1().getWürfelErgebnisse()) {
					System.out.println(String.format("Du hast eine %d gewürfelt.", i));
				}
				printWissensstreiterPositionen();
				break;
			case WarteAufAntwortEingabe:
				System.out.println("Warte auf Eingabe");
				break;
			case WarteAufKategorieEingabe:
				System.out.println("Warte auf Eingabe");
				break;
			case WarteAufSelbsttestEingabe:
				System.out.println("Warte auf Eingabe");
				break;
			case WarteAufWissensstreiterEingabe:
				System.out.println("Warte auf Eingabe");
				break;
		}
	}
	
	
	
	
	private void printWissensstreiterPositionen() {
		int aktuellerSpielerIndex =  model.getUseCaseController1().getAktuellerSpieler();
		Spieler aktuellerSpieler = Spielfeld.getInstance().spieler.get(aktuellerSpielerIndex);
		ArrayList<Wissensstreiter> wissensstreiterAufSpielfeld = Spielfeld.getInstance().spieler.get(aktuellerSpielerIndex).getAlleWissensstreiterAufSpielfeld();
		
		if (Spielfeld.getInstance().spieler.get(aktuellerSpielerIndex).alleWissensstreiterImHeimatfeld()) {
			// ...
		} else {
			System.out.println(String.format("%d von 3 Wissensstreiter auf dem Spielfeld.", wissensstreiterAufSpielfeld.size()));
			System.out.println("Deine Wissensteiter stehen auf folgenden Feldern:");
			for (int i = 0; i < Spielfeld.getInstance().spieler.get(aktuellerSpielerIndex).getAlleWissensstreiterAufSpielfeld().size(); i++) {
				System.out.print(String.format("%s%d auf Feld %d\t", aktuellerSpieler.getName().charAt(0), i, wissensstreiterAufSpielfeld.get(i).getPosition()));			
			}
		}
	}
}
