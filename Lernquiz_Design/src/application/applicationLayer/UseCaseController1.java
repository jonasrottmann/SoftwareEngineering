package application.applicationLayer;

import java.util.ArrayList;
import java.util.Random;

public class UseCaseController1 {
	
	private int aktuellerSpieler;
	private Spielfeld spielfeld;
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
			if (letztesW�rfelErgebnis == 6) {
				wissenstreiterAufsSpielfeldBringen();
				modelFront.setZustand(Zustand.NeuerSpielzug_SUCCESS);
			} else {
				// N�chster Spieler am Zug
				modelFront.setZustand(Zustand.NeuerSpielzug_FAIL);
			}
			modelFront.notifyObservers();
		} else {
			// wissensstreiterBewegen
			if (letztesW�rfelErgebnis == 6 && !Spielfeld.getInstance().spieler.get(aktuellerSpieler).alleWissensstreiterAufSpielfeld()) {
				wissenstreiterAufsSpielfeldBringen();
				// TODO notify
			} else {
				// Bewegen
				modelFront.setZustand(Zustand.WarteAufWissensstreiterEingabe);
				modelFront.notifyObservers();
			}
		}
		aktuellerSpieler = ++aktuellerSpieler % Spielfeld.getInstance().spieler.size();
	}


	/**
	 * 
	 * @param wissensstreiter
	 */
	public void wissensstreiterBewegen(Wissensstreiter wissensstreiter) {
		int position = (wissensstreiter.getPosition() + w�rfelErgebnisse.get(w�rfelErgebnisse.size() - 1)) % 48;
		Spieler spielerAufZielfeld = spielfeld.isFeldBesetzt(position);
		wissensstreiter.setPosition(position);
		
		if(spielerAufZielfeld != null){
			//wissenstestStarten
			// TODO
		}
	}
	
	
	/**
	 * 
	 */
	private void wissenstreiterAufsSpielfeldBringen() {
		if (Spielfeld.getInstance().isFeldBesetzt(Spielfeld.STARTFELDER[aktuellerSpieler]) != null) {
			// Startfeld des aktuellen Spielers ist schon besetzt.
			// TODO wissenstest
			System.out.println("Startfeld besetzt...");
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
	
	public int getAktuellerSpieler() {
		return aktuellerSpieler;
	}
}
