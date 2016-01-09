package application.presentationLayer;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import application.applicationLayer.Fassade;
import application.applicationLayer.Terminal;
import application.applicationLayer.spiel.Kategorie;
import application.applicationLayer.spiel.Spielfeld;
import application.applicationLayer.spiel.Wissensstreiter;

public class Controller implements Observer {
	
	private final Terminal terminal;
	private Fassade model;
	
	public Controller() {
		terminal = new Terminal();
	}
	
	public void update(Observable obs, Object arg) {
		model = (Fassade) obs;
		String s;
		Integer i;
		switch(model.getZustand()) {
			case Warte_WissensstreiterEingabe:
				i = null;
				while (i == null || i >= 3 || i < 0 ||  model.getUseCaseController1().getAktuellerSpieler().getWissensstreiter().get(i).getPosition() == Wissensstreiter.POSITION_IM_HEIMATFELD) {
					s = terminal.readLine();
					i = stringToInt(s);
				}				
				model.getUseCaseController1().wissensstreiterBewegen(model.getUseCaseController1().getAktuellerSpieler().getWissensstreiter().get(i));
				break;
			case Warte_KategorieEingabe:
				i = null;
				while (i == null || i >= 4 || i < 0) {
					s = terminal.readLine();
					i = stringToInt(s);
				}
				model.getUseCaseController2().kategorieSetzen(Spielfeld.getInstance().getKategorien().get(i));
				break;
			case WarteAufAntwortEingabe:
				i = null;
				while (i == null || i >= 4 || i < 0) {
					s = terminal.readLine();
					i = stringToInt(s);
				}
				model.getUseCaseController2().antwortPruefen(i);
				break;
			case Warte_SelbsttestEingabe:
				i = null;
				while (i == null || i >= 2 || i < 0) {
					s = terminal.readLine();
					i = stringToInt(s);
				}
				if (i == 1)
					model.getUseCaseController2().selbstTestStarten();
				break;
			case Warte_AlternativeKategorieEingabe:
				ArrayList<Kategorie> kategorien = model.getUseCaseController2().getGetesteterWissensstreiter().getSpieler().getNichtVolleKategorien();
				i = null;
				while (i == null || i >= kategorien.size() || i < 0) {
					s = terminal.readLine();
					i = stringToInt(s);
				}
				model.getUseCaseController2().alternativeKategorieSetzen(i);
				break;
			default:
				break;
		}
	}
	
	private Integer stringToInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return null;
		}
	}
}
