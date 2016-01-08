package application.applicationLayer;

import java.util.ArrayList;
import java.util.Random;

public class UseCaseController1 {
	
	private int aktuellerSpieler;
	private Spielfeld spielfeld;
	private ArrayList<Integer> würfelErgebnisse = new ArrayList<Integer>();
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
		
		würfelErgebnisse.clear();
		
		// 3 mal oder bis 6 würfeln
		for (int i = 0; i < 3; i++) {
			int würfel = 1 + new Random().nextInt(6);
			würfelErgebnisse.add(würfel);
			if (würfel == 6) break;
		}
		
		// letzes Würfelergebnis zwischenspeichern (ist das dritte oder eine 6)
		int letztesWürfelErgebnis = würfelErgebnisse.get(würfelErgebnisse.size() - 1);
		
		// Spielfeld verändern
		if(Spielfeld.getInstance().spieler.get(aktuellerSpieler).alleWissensstreiterImHeimatfeld()){
			if (letztesWürfelErgebnis == 6) {
				wissenstreiterAufsSpielfeldBringen();
				modelFront.setZustand(Zustand.NeuerSpielzug_SUCCESS);
			} else {
				// Nächster Spieler am Zug
				modelFront.setZustand(Zustand.NeuerSpielzug_FAIL);
			}
			modelFront.notifyObservers();
		} else {
			// wissensstreiterBewegen
			if (letztesWürfelErgebnis == 6 && !Spielfeld.getInstance().spieler.get(aktuellerSpieler).alleWissensstreiterAufSpielfeld()) {
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
		int position = (wissensstreiter.getPosition() + würfelErgebnisse.get(würfelErgebnisse.size() - 1)) % 48;
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
	
	public ArrayList<Integer> getWürfelErgebnisse() {
		return würfelErgebnisse;
	}
	
	public int getAktuellerSpieler() {
		return aktuellerSpieler;
	}
}
