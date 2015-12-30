package application.applicationLayer;

import java.util.Random;

public class UseCaseController1 {
	
	private Spieler aktuellerSpieler;
	private int augenzahl;
	private Spielfeld spielfeld;
	private int wuerfelzaehler;
	
	public void wuerfeln(){
		this.augenzahl = 1 + new Random().nextInt(6);
		System.out.println(this.augenzahl);
		
		if (this.augenzahl == 6){
			if(!(aktuellerSpieler.alleWissensstreiterAufSpielfeld())){
				//wissensstreiter rausholen
			}else{
				//wissensstreiterBewegen
			}
		}else{
			if(aktuellerSpieler.alleWissensstreiterImHeimatfeld()){
				
				if(wuerfelzaehler!=3){
					wuerfelzaehler++;
					wuerfeln();
				}else{
					//nächsterSpieler
				}
				
			}else{
				//wissensstreiterBewegen
			}
		}
	}

	public void wissensstreiterBewegen(Wissensstreiter wissensstreiter) {
		int position = (wissensstreiter.getPosition() + augenzahl) % 48;
		Spieler feldBesetzt = spielfeld.isFeldBesetzt(position);
		wissensstreiter.setPosition(position);
		
		if(feldBesetzt != null){
			//wissenstestStarten
		}
	}
}
