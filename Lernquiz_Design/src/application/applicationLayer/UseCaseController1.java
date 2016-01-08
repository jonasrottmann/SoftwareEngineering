package application.applicationLayer;

import java.util.ArrayList;
import java.util.Random;

public class UseCaseController1 {
	
	private int aktuellerSpieler;
	private Spielfeld spielfeld = Spielfeld.getInstance();
	private ArrayList<Integer> w�rfelErgebnisse = new ArrayList<Integer>();
	private ModelFront modelFront;

	/**
	 * 
	 * @param modelFront
	 */
	public UseCaseController1(ModelFront modelFront) {
		this.modelFront = modelFront;
		aktuellerSpieler = 0;
	}

	/**
	 * 
	 */
	public void wuerfeln(){
		modelFront.setZustand(Zustand.NeuerSpielzug);
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
		if(Spielfeld.getInstance().spieler.get(aktuellerSpieler).alleWissensstreiterImHeimatfeld()){
			// Wissensstreiter aus Heimatfeld bringen
			if (letztesW�rfelErgebnis == 6) {
				wissenstreiterAufsSpielfeldBringen();
				modelFront.setZustand(Zustand.NeuerSpielzug_SUCCESS);
			} else {
				// N�chster Spieler am Zug
				modelFront.setZustand(Zustand.NeuerSpielzug_FAIL);
			}
			modelFront.notifyObservers();
		} else {
			// Wissensstreiter ziehen
			if (letztesW�rfelErgebnis == 6 && !Spielfeld.getInstance().spieler.get(aktuellerSpieler).alleWissensstreiterAufSpielfeld()) {
				wissenstreiterAufsSpielfeldBringen();
				// Problem: Wenn eigener Wissensstreiter auf dem Startfeld steht
			} else {
				// Bewegen
				modelFront.setZustand(Zustand.WarteAufWissensstreiterEingabe);
				modelFront.notifyObservers();
			}
		}
		aktuellerSpieler = ++aktuellerSpieler % Spielfeld.getInstance().spieler.size();
	}


	public void wissensstreiterBewegen(Wissensstreiter wissensstreiter) {
		int position = (wissensstreiter.getPosition() + w�rfelErgebnisse.get(w�rfelErgebnisse.size() - 1)) % 48;
		Spieler spielerAufZielfeld = spielfeld.isFeldBesetzt(position);
		wissensstreiter.setPosition(position);
		
		if(spielerAufZielfeld != null){
			modelFront.getUseCaseController2().testen(ws1, ws2);
			//wissenstestStarten
			// TODO
			System.out.println("WISSENSSTEST!");
		}
	}
	
	private void wissenstreiterAufsSpielfeldBringen() {
		Spieler kollidierenderSpieler = Spielfeld.getInstance().isFeldBesetzt(Spielfeld.STARTFELDER[aktuellerSpieler]);
		
		if (kollidierenderSpieler != null) {
			// TODO Checken au�erhalb dieser Methode
			// Startfeld des aktuellen Spielers ist schon besetzt.
			if (Spielfeld.getInstance().spieler.get(aktuellerSpieler).equals(kollidierenderSpieler)) {
				// Startfeld ist von uns selbst besetzt
				modelFront.setZustand(Zustand.WarteAufWissensstreiterEingabe);
				modelFront.notifyObservers();
			} else {
				//wissenstestStarten
				// TODO
				System.out.println("WISSENSSTEST!");
			}
		} else {
			Spielfeld.getInstance()
			.spieler.get(aktuellerSpieler)
			.getWissensstreiterAusHeimatfeld()
			.setPosition(Spielfeld.STARTFELDER[aktuellerSpieler]);
		}
	}
	
	public ArrayList<Integer> getW�rfelErgebnisse() {
		return w�rfelErgebnisse;
	}
	
	public Spieler getAktuellerSpieler() {
		return Spielfeld.getInstance().spieler.get(aktuellerSpieler);
	}
}
