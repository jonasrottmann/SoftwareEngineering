package application.presentationLayer;

import java.util.Observable;
import java.util.Observer;

import application.applicationLayer.ModelFront;
import application.applicationLayer.Spielfeld;
import application.applicationLayer.Terminal;

public class Controller implements Observer {
	
	private final Terminal terminal;
	private ModelFront modelFront;
	
	public Controller() {
		terminal = new Terminal();
	}
	

	public void update(Observable obs, Object arg) {
		modelFront = (ModelFront) obs;
		String s;
		Integer i;
		switch(modelFront.getZustand()) {
			case NeuerSpielzug_FAIL:
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case NeuerSpielzug_SUCCESS:
//				System.out.println("Eingabe:");
//				s = terminal.readLine();
//				while (stringToInt(s) == null) {
//					System.out.println("Scheiß eingabe... nochmal:");
//					s = terminal.readLine();
//				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case WarteAufWissensstreiterEingabe:
				i = null;
				while (i == null || i >= 4 || i < 0) {
					s = terminal.readLine();
					i = stringToInt(s);
				}
				modelFront.getUseCaseController1().wissensstreiterBewegen(modelFront.getUseCaseController1().getAktuellerSpieler().getWissensstreiter().get(i));
				break;
				
				
				
				
			case WarteAufKategorieEingabe:
				i = null;
				while (i == null || i >= 4 || i < 0) {
					s = terminal.readLine();
					i = stringToInt(s);
				}
				modelFront.getUseCaseController2().kategorieSetzen(Spielfeld.getInstance().kategorien.get(i));
				break;
			case WarteAufAntwortEingabe:
				i = null;
				while (i == null || i >= 4 || i < 0) {
					s = terminal.readLine();
					i = stringToInt(s);
				}
				modelFront.getUseCaseController2().antwortPruefen(i);
				break;
			
			case WarteAufSelbsttestEingabe:
				break;
			
			default:
				break;
		}
	}
	
	
	private Integer stringToInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return null;
		}
	}
}
