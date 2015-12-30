package application.applicationLayer;

import java.util.ArrayList;
import java.util.List;

public class Spielfeld {
	

	private static Spielfeld mSpielfeld;
	
	
	private Spielfeld() {
	}
	
	public static Spielfeld getInstance() {
		if (mSpielfeld == null)
			mSpielfeld = new Spielfeld();
		return mSpielfeld;
	}
	
	
	
	List<Spieler> spieler = new ArrayList<Spieler>();
	List<Kategorie> kategorien = new ArrayList<Kategorie>();
	
	

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
