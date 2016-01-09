package application.applicationLayer.spiel;

import java.util.ArrayList;

public class Spieler {
	
	private final Wissensstandszeiger wissensstandszeiger;
	private final ArrayList<Wissensstreiter> wissensstreiter = new ArrayList<Wissensstreiter>();
	private final String name;
	
	public Spieler(String name) {
		for (int i = 0; i < 3; i++) {
			wissensstreiter.add(new Wissensstreiter(Spieler.this));
		}
		this.wissensstandszeiger = new Wissensstandszeiger();
		this.name = name;		
	}

	public boolean alleWissensstreiterImHeimatfeld(){
		boolean imHeimatfeld = true;
		for (Wissensstreiter wissensstreiter : this.wissensstreiter) {
			if(wissensstreiter.getPosition() != Wissensstreiter.POSITION_IM_HEIMATFELD){
				return false;
			}
		}
		return imHeimatfeld;
	}
	
	public boolean alleWissensstreiterAufSpielfeld() {
		return getAlleWissensstreiterAufSpielfeld().size() == 3;
	}
	
	public ArrayList<Wissensstreiter> getAlleWissensstreiterAufSpielfeld() {
		ArrayList<Wissensstreiter> tmpWissensstreiter = new ArrayList<Wissensstreiter>();
		for (Wissensstreiter wissensstreiter : this.wissensstreiter) {
			if(wissensstreiter.getPosition() != Wissensstreiter.POSITION_IM_HEIMATFELD){
				tmpWissensstreiter.add(wissensstreiter);
			}
		}
		return tmpWissensstreiter;
	}
	
	
	public void wissensstandDekrementieren(Kategorie kategorie) {
		return;
	}

	public void wissensstandInkrementieren(Kategorie karegorie) {
		return;
	}

	public Wissensstandszeiger getWissensstandsZeiger() {
		return wissensstandszeiger;
	}

	public ArrayList<Wissensstreiter> getWissensstreiter() {
		return wissensstreiter;
	}
	
	public Wissensstreiter getWissensstreiterAusHeimatfeld() {
		for (Wissensstreiter w : wissensstreiter) {
			if (w.getPosition() == Wissensstreiter.POSITION_IM_HEIMATFELD) {
				return w;
			}
		}
		return null;
	}
	
	public ArrayList<Kategorie> getNichtVolleKategorien() {
		ArrayList<Kategorie> tmpKategorien = new ArrayList<Kategorie>();
		for (int i = 0 ; i < Spielfeld.getInstance().getKategorien().size(); i++ ) {
			int wissensstand = wissensstandszeiger.getWissensstandVonKategorie(i);
			if (wissensstand != Wissensstandszeiger.MAX_WISSENSSTAND) {
				tmpKategorien.add(Spielfeld.getInstance().getKategorien().get(i));
			}
		}
		return tmpKategorien;
	}
	
	public String getName() {
		return name;
	}

}
