package application.applicationLayer;

import java.util.ArrayList;
import java.util.Random;

import application.applicationLayer.spiel.Spieler;
import application.applicationLayer.spiel.Spielfeld;
import application.applicationLayer.spiel.Wissensstreiter;

public class UseCaseController1 {
	
	private int aktuellerSpieler;
	private Spielfeld spielfeld = Spielfeld.getInstance();
	private ArrayList<Integer> w�rfelErgebnisse = new ArrayList<Integer>();
	private Fassade modelFront;

	/**
	 * 
	 * @param modelFront
	 */
	public UseCaseController1(Fassade modelFront) {
		this.modelFront = modelFront;
		aktuellerSpieler = 0;
	}

	/**
	 * 
	 */
	public void wuerfeln(){
		modelFront.setZustand(Zustaende.NeuerSpielzug);
		modelFront.notifyObservers();
		
		w�rfelErgebnisse.clear();
		
		// 3 mal oder bis 6 w�rfeln
		for (int i = 0; i < 3; i++) {
			int w�rfel = 1 + new Random().nextInt(6);
			w�rfelErgebnisse.add(w�rfel);
			if (w�rfel == 6) break;
		}
		
		// letzes W�rfelergebnis zwischenspeichern (ist das dritte oder eine 6)
		int letztesW�rfelErgebnis = w�rfelErgebnisse.get(w�rfelErgebnisse.size() - 1);
		
		// Spielfeld ver�ndern
		if(Spielfeld.getInstance().getSpieler().get(aktuellerSpieler).alleWissensstreiterImHeimatfeld()){
			// Wissensstreiter aus Heimatfeld bringen
			if (letztesW�rfelErgebnis == 6) {
				wissenstreiterAufsSpielfeldBringen();
				modelFront.setZustand(Zustaende.NeuerSpielzug_ErstesWuerfelnSuccess);
				modelFront.notifyObservers();
			} else {
				// N�chster Spieler am Zug
				modelFront.setZustand(Zustaende.NeuerSpielzug_ErstesWuerfelnFail);
				modelFront.notifyObservers();
			}
		} else {
			// Wissensstreiter ziehen
			if (letztesW�rfelErgebnis == 6 && !Spielfeld.getInstance().getSpieler().get(aktuellerSpieler).alleWissensstreiterAufSpielfeld()) {
				Wissensstreiter wissensstreiterAufStartfeld = Spielfeld.getInstance().isFeldBesetzt(Spielfeld.STARTFELDER[aktuellerSpieler]);
				if (wissensstreiterAufStartfeld != null) {
					// Startfeld des aktuellen Spielers ist schon besetzt.
					if (Spielfeld.getInstance().getSpieler().get(aktuellerSpieler).getWissensstreiter().contains(wissensstreiterAufStartfeld)) {
						// Startfeld ist von uns selbst besetzt
						// Diesen r�cken
						modelFront.setZustand(Zustaende.Warte_WissensstreiterEingabe);
						modelFront.notifyObservers();
					} else {
						// Starte Wissensstest mit einem Wissenssteiter aus dem Heimatfeld
						modelFront.getUseCaseController2().wissenstestStarten(Spielfeld.getInstance().getSpieler().get(aktuellerSpieler).getWissensstreiterAusHeimatfeld(), wissensstreiterAufStartfeld);
					}
				} else {
					wissenstreiterAufsSpielfeldBringen();
					modelFront.setZustand(Zustaende.NeuerSpielzug_WissensstreiterAufSpielfeldgebracht);
					modelFront.notifyObservers();
				}
			} else {
				// Bewegen
				modelFront.setZustand(Zustaende.Warte_WissensstreiterEingabe);
				modelFront.notifyObservers();
			}
		}
		setNaechsterSpieler();
	}


	public void wissensstreiterBewegen(Wissensstreiter wissensstreiter) {
		int position = (wissensstreiter.getPosition() + w�rfelErgebnisse.get(w�rfelErgebnisse.size() - 1)) % 48;
		Wissensstreiter kollidierenderWissensstreiter = spielfeld.isFeldBesetzt(position);
		wissensstreiter.setPosition(position);
		
		if(kollidierenderWissensstreiter != null){
			modelFront.getUseCaseController2().wissenstestStarten(wissensstreiter, kollidierenderWissensstreiter);
		}
	}
	
	private void wissenstreiterAufsSpielfeldBringen() {
		Spielfeld.getInstance()
			.getSpieler().get(aktuellerSpieler)
			.getWissensstreiterAusHeimatfeld()
			.setPosition(Spielfeld.STARTFELDER[aktuellerSpieler]);
	}
	
	public ArrayList<Integer> getW�rfelErgebnisse() {
		return w�rfelErgebnisse;
	}
	
	public Spieler getAktuellerSpieler() {
		return Spielfeld.getInstance().getSpieler().get(aktuellerSpieler);
	}
	
	public void setNaechsterSpieler(){
		this.aktuellerSpieler = ++aktuellerSpieler % Spielfeld.getInstance().getSpieler().size();
	}
}
