package application.applicationLayer.spiel;

import java.util.ArrayList;

public class Spielfeld {
	private static Spielfeld mSpielfeld;
	public static final int ANZAHL_FELDER = 48;
	public static final int[] STARTFELDER = {0, 12, 24, 36}; // Spieler 0, 1, 2, 3
	private final ArrayList<Spieler> spieler = new ArrayList<Spieler>();
	private final ArrayList<Kategorie> kategorien = new ArrayList<Kategorie>();
	
	
	private Spielfeld() {
	}
	
	public static Spielfeld getInstance() {
		if (mSpielfeld == null)
			mSpielfeld = new Spielfeld();
		return mSpielfeld;
	}
	
	public Wissensstreiter isFeldBesetzt(int feld) {
		for (Spieler spieler : this.spieler) {
			for (Wissensstreiter wissensstreiter : spieler.getWissensstreiter()) {
				if(wissensstreiter.getPosition() == feld){
					return wissensstreiter;
				}
			}
		}
		return null;
	}
	
	public ArrayList<Spieler> getSpieler() {
		return spieler;
	}

	public ArrayList<Kategorie> getKategorien() {
		return kategorien;
	}
}
