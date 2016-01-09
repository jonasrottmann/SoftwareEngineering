package application.applicationLayer;

import java.util.Observable;

import application.presentationLayer.Controller;
import application.presentationLayer.View;

public class Fassade extends Observable {
	private UseCaseController1 uc1;
	private UseCaseController2 uc2;
	private Zustaende zustand;
	
	public Fassade(View v, Controller c) {
		// Zuerst Controller registrieren, da Reihenfolge der Benachrichtigung entgegengesetzt
		addObserver(c);
		addObserver(v);
		uc1 = new UseCaseController1(Fassade.this);
		uc2 = new UseCaseController2(Fassade.this);		
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
	
	public void spielzugStarten() {
		uc1.wuerfeln();
	}
	
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}
}
