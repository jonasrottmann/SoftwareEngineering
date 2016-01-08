package application.applicationLayer;

import java.util.ArrayList;
import java.util.List;

public class Spieler {
	
	private Wissensstandszeiger wissensstandszeiger;
	private ArrayList<Wissensstreiter> wissensstreiter = new ArrayList<Wissensstreiter>();
	private final String name;
	
	public Spieler(String name) {
		for (int i = 0; i < 2; i++) {
			wissensstreiter.add(new Wissensstreiter());
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

	public Wissensstandszeiger getWissensstandszeiger() {
		//Commented automatically
		//return;
		return null;
	}

	public Wissensstandszeiger getWissensstandsZeiger() {
		return wissensstandszeiger;
	}

	public void setWissensstandsZeiger(Wissensstandszeiger wissensstandsZeiger) {
		this.wissensstandszeiger = wissensstandsZeiger;
	}

	public ArrayList<Wissensstreiter> getWissensstreiter() {
		return wissensstreiter;
	}

	public void setWissensstreiter(ArrayList<Wissensstreiter> wissensstreiter) {
		this.wissensstreiter = wissensstreiter;
	}
	
	public Wissensstreiter getWissensstreiterAusHeimatfeld() {
		for (Wissensstreiter w : wissensstreiter) {
			if (w.getPosition() == Wissensstreiter.POSITION_IM_HEIMATFELD) {
				return w;
			}
		}
		return null;
	}
	
	
	public String getName() {
		return name;
	}

}
