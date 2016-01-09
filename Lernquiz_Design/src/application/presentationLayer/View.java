package application.presentationLayer;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import application.applicationLayer.Fassade;
import application.applicationLayer.spiel.Spieler;
import application.applicationLayer.spiel.Spielfeld;
import application.applicationLayer.spiel.Wissensstandszeiger;
import application.applicationLayer.spiel.Wissensstreiter;

public class View implements Observer {
	
	private Fassade model;
	
	public void update(Observable obs, Object arg) {		
		this.model = (Fassade) obs;
		
		switch(model.getZustand()) {
			case NeuerSpielzug:
				System.out.println(String.format("\n\n%s ist jetzt am Zug. Viel Glück!", model.getUseCaseController1().getAktuellerSpieler().getName()));
				break;
			case NeuerSpielzug_ErstesWuerfelnFail:
				for (int i : model.getUseCaseController1().getWürfelErgebnisse()) {
					System.out.println(String.format("Du hast eine %d gewürfelt.", i));
				}
				break;
			case NeuerSpielzug_ErstesWuerfelnSuccess:
				for (int i : model.getUseCaseController1().getWürfelErgebnisse()) {
					System.out.println(String.format("Du hast eine %d gewürfelt.", i));
				}
				printEigeneWissensstreiter();
				printFremdeWissensstreiter();
				break;
			case Warte_WissensstreiterEingabe:
				System.out.println(String.format("Du hast eine %d gewürfelt.", model.getUseCaseController1().getWürfelErgebnisse().get(model.getUseCaseController1().getWürfelErgebnisse().size() - 1)));
				printEigeneWissensstreiter();
				printFremdeWissensstreiter();
				System.out.println("Welcher Wissensstreiter soll bewegt werden?");
				break;
			case NeuerSpielzug_WissensstreiterAufSpielfeldgebracht:
				System.out.println(String.format("Du hast eine %d gewürfelt.", model.getUseCaseController1().getWürfelErgebnisse().get(model.getUseCaseController1().getWürfelErgebnisse().size() - 1)));
				System.out.println(String.format("Einer deiner Wissensstreiter wurde aufs Spielfeld gebracht."));
				printEigeneWissensstreiter();
				printFremdeWissensstreiter();
				break;
			case Warte_KategorieEingabe:
				Spieler testenderSpieler =  model.getUseCaseController2().getTestenderWissensstreiter().getSpieler();
				Spieler getesteterSpieler = model.getUseCaseController2().getGetesteterWissensstreiter().getSpieler();
				System.out.println(String.format("%s hat %s geschlagen.",testenderSpieler.getName(), getesteterSpieler.getName()));
				System.out.println(String.format("%s bitte wähle eine der Folgenden Kategorien:", testenderSpieler.getName()));
				for (int i = 0; i < Spielfeld.getInstance().getKategorien().size(); i++) {
					System.out.print(String.format("%d: %s", i, Spielfeld.getInstance().getKategorien().get(i).getName()));
					if (i % 2 == 0)
						System.out.print("\t\t");
					else
						System.out.print("\n");
				}
				System.out.println();
				break;
			case WarteAufAntwortEingabe:
				System.out.println(String.format("Es wurde \"%s\" gewählt.", model.getUseCaseController2().getAktuelleKategorie().getName()));
				System.out.println(model.getUseCaseController2().getAktuelleFrage().getFrage());
				for (int i = 0; i < model.getUseCaseController2().getAktuelleFrage().getAntworten().length; i++) {
					System.out.print(String.format("%d: %s", i, model.getUseCaseController2().getAktuelleFrage().getAntworten()[i]));
					if (i % 2 == 0)
						System.out.print("\t\t");
					else
						System.out.print("\n");
				}
				System.out.println(String.format("%s, wähle die richtige Antwort aus.", model.getUseCaseController2().getGetesteterWissensstreiter().getSpieler().getName()));
				break;
			case NeuerSpielzug_FrageFail:
				System.out.println(String.format("Die gewählt Antwort war leider falsch."));
				printWissensstandsanzeiger(model.getUseCaseController2().getGetesteterWissensstreiter().getSpieler());
				break;
			case NeuerSpielzug_FrageSuccess:
				System.out.println(String.format("Glückwunsch! Die gewählt Antwort war richtig."));
				printWissensstandsanzeiger(model.getUseCaseController2().getGetesteterWissensstreiter().getSpieler());
				break;
			case Warte_SelbsttestEingabe:
				System.out.println(String.format("%s, möchtest du selbst eine Frage der Kategorie %s beantworten?", model.getUseCaseController2().getTestenderWissensstreiter().getSpieler().getName(), model.getUseCaseController2().getAktuelleKategorie().getName()));
				break;
			case Warte_AlternativeKategorieEingabe:
				System.out.println(String.format("Der Wissensstand der Kategorie %s ist schon auf dem maximalen Niveau.\nWelche Kategorie soll statt dessen erhöht werden?", model.getUseCaseController2().getAktuelleKategorie().getName()));
				for (int i = 0; i < model.getUseCaseController2().getGetesteterWissensstreiter().getSpieler().getNichtVolleKategorien().size(); i++) {
					System.out.print(String.format("%d: %s", i, model.getUseCaseController2().getGetesteterWissensstreiter().getSpieler().getNichtVolleKategorien().get(i)));
					if (i % 2 == 0)
						System.out.print("\t\t");
					else
						System.out.print("\n");
				}
				break;
			case GewinnerErmittelt:
				System.out.print("\n\n");
				System.out.println(String.format("%s hat gewonnen!", model.getUseCaseController2().getGetesteterWissensstreiter().getSpieler().getName()));
				System.exit(0);
				break;
		}
	}
	
	
	
	private void printEigeneWissensstreiter() {
		Spieler eigenerSpieler = model.getUseCaseController1().getAktuellerSpieler();
		int aktuellerSpielerIndex = Spielfeld.getInstance().getSpieler().indexOf(eigenerSpieler);
		ArrayList<Wissensstreiter> wissensstreiterAufSpielfeld = Spielfeld.getInstance().getSpieler().get(aktuellerSpielerIndex).getAlleWissensstreiterAufSpielfeld();
		
		System.out.println(String.format("%d von 3 Wissensstreiter auf dem Spielfeld.", wissensstreiterAufSpielfeld.size()));
		System.out.println("Die Wissensteiter stehen auf folgenden Feldern:");
		printWissenstreiterPositionen(eigenerSpieler);
	}
	
	private void printFremdeWissensstreiter() {
		Spieler eigenerSpieler = model.getUseCaseController1().getAktuellerSpieler();
		int eigenerSpielerIndex = Spielfeld.getInstance().getSpieler().indexOf(eigenerSpieler);
		
		for (int i = 0; i < Spielfeld.getInstance().getSpieler().size(); i++) {
			if (i != eigenerSpielerIndex) {
				printWissenstreiterPositionen(Spielfeld.getInstance().getSpieler().get(i));
			}
		}
	}
	
	private void printWissenstreiterPositionen(Spieler spieler) {
		int aktuellerSpielerIndex = Spielfeld.getInstance().getSpieler().indexOf(spieler);
		ArrayList<Wissensstreiter> wissensstreiterAufSpielfeld = Spielfeld.getInstance().getSpieler().get(aktuellerSpielerIndex).getAlleWissensstreiterAufSpielfeld();

		if (Spielfeld.getInstance().getSpieler().get(aktuellerSpielerIndex).alleWissensstreiterImHeimatfeld()) {
			// ...
		} else {
			for (int i = 0; i < Spielfeld.getInstance().getSpieler().get(aktuellerSpielerIndex).getAlleWissensstreiterAufSpielfeld().size(); i++) {
				System.out.print(String.format("%s%d auf Feld %d\t", spieler.getName().charAt(0), spieler.getWissensstreiter().indexOf(Spielfeld.getInstance().getSpieler().get(aktuellerSpielerIndex).getAlleWissensstreiterAufSpielfeld().get(i)), wissensstreiterAufSpielfeld.get(i).getPosition()));			
			}
			System.out.println();
		}
	}
	
	
	private void printWissensstandsanzeiger(Spieler spieler) {
		System.out.println(String.format("%s Wissensstände:", spieler.getName()));
		for (int i = 0; i < Spielfeld.getInstance().getKategorien().size(); i++) {
			System.out.print(String.format("%s %d/%d", Spielfeld.getInstance().getKategorien().get(i).getName(), spieler.getWissensstandsZeiger().getWissensstandVonKategorie(i), Wissensstandszeiger.MAX_WISSENSSTAND));				
			if (i % 2 == 0)
				System.out.print("\t\t");
			else
				System.out.print("\n");
		}
	}
}
