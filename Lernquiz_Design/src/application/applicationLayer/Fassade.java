package application.applicationLayer;

import java.util.Observable;

import application.applicationLayer.spiel.Wissensstreiter;

public class Fassade extends Observable {
	private UseCaseController1 uc1;
	private UseCaseController2 uc2;
	private Zustaende zustand;
	
	public Fassade() {
		uc1 = new UseCaseController1(Fassade.this);
		uc2 = new UseCaseController2(Fassade.this);		
	}

	public void spielzugStarten() {
		uc1.wuerfeln();
	}
	
	public Zustaende getZustand() {
		return zustand;
	}

	public void setZustand(Zustaende zustand) {
		this.zustand = zustand;
	}

	public UseCaseController1 getUseCaseController1() {
		return uc1;
	}

	public UseCaseController2 getUseCaseController2() {
		return uc2;
	}
	
	public void wissenstestStarten(Wissensstreiter wissensstreiter, Wissensstreiter kollidierenderWissensstreiter) {
		uc2.wissenstestStarten(wissensstreiter, kollidierenderWissensstreiter);
	}
	
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}
}
