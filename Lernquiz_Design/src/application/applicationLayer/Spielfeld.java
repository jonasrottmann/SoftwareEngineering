package application.applicationLayer;

import java.util.ArrayList;
import java.util.List;

public class Spielfeld {
	

	private static Spielfeld mSpielfeld;
	public static final int ANZAHL_FELDER = 48;
	public static final int[] STARTFELDER = {0, 12, 24, 36}; // Spieler 0, 1, 2, 3
	public final List<Spieler> spieler = new ArrayList<Spieler>();
	public final List<Kategorie> kategorien = new ArrayList<Kategorie>();
	
	
	private Spielfeld() {
	}
	
	public static Spielfeld getInstance() {
		if (mSpielfeld == null)
			mSpielfeld = new Spielfeld();
		return mSpielfeld;
	}
	

	public Spieler isFeldBesetzt(int feld) {
		for (Spieler spieler : this.spieler) {
			for (Wissensstreiter wissensstreiter : spieler.getWissensstreiter()) {
				if(wissensstreiter.getPosition() == feld){
					return spieler;
				}
			}
		}
		return null;
	}

	public void wissensstreiterZurücksetzen(Wissensstreiter wissensstreiter, boolean antwortRichtig) {
		return;
	}
}
