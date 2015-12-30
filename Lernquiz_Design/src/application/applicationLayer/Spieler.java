package application.applicationLayer;

import java.util.ArrayList;
import java.util.List;

public class Spieler {
	
	private Wissensstandszeiger wissensstandszeiger;
	private List<Wissensstreiter> wissensstreiter = new ArrayList<Wissensstreiter>();
	
	
	
	public Spieler() {
		for (int i = 0; i < 2; i++) {
			wissensstreiter.add(new Wissensstreiter());
		}
		
		wissensstandszeiger = new Wissensstandszeiger();
	}

	public boolean alleWissensstreiterImHeimatfeld(){
		boolean imHeimatfeld = true;
		for (Wissensstreiter wissensstreiter : this.wissensstreiter) {
			if(wissensstreiter.getPosition() != -1){
				return false;
			}
		}
		return imHeimatfeld;
	}
	
	public boolean alleWissensstreiterAufSpielfeld(){
		boolean aufSpielfeld = true;
		for (Wissensstreiter wissensstreiter : this.wissensstreiter) {
			if(wissensstreiter.getPosition() == -1){
				return false;
			}
		}
		return aufSpielfeld;
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

	public List<Wissensstreiter> getWissensstreiter() {
		return wissensstreiter;
	}

	public void setWissensstreiter(List<Wissensstreiter> wissensstreiter) {
		this.wissensstreiter = wissensstreiter;
	}
	
	
}
