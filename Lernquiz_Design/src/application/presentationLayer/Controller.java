package application.presentationLayer;

import java.util.Observable;
import java.util.Observer;

import application.applicationLayer.ModelFront;
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
//					System.out.println("Scheiﬂ eingabe... nochmal:");
//					s = terminal.readLine();
//				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case WarteAufAntwortEingabe:
				break;
			case WarteAufKategorieEingabe:
				break;
			case WarteAufSelbsttestEingabe:
				break;
			case WarteAufWissensstreiterEingabe:
				s = terminal.readLine();
				while (Integer.valueOf(s) == null ) {
					s = terminal.readLine();
				}
				// ...
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
