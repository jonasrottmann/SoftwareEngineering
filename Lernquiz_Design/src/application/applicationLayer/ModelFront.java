package application.applicationLayer;

import java.util.Observable;

import application.presentationLayer.Controller;
import application.presentationLayer.View;

public class ModelFront extends Observable {
	private UseCaseController1 uc1;
	private UseCaseController2 uc2;
	private Zustand zustand;
	
	public ModelFront(View v, Controller c) {
		// Zuerst Controller registrieren, da Reihenfolge der Benachrichtigung entgegengesetzt
		addObserver(c);
		addObserver(v);
		uc1 = new UseCaseController1(ModelFront.this);
		uc2 = new UseCaseController2(ModelFront.this);		
	}

	public Zustand getZustand() {
		return zustand;
	}

	public void setZustand(Zustand zustand) {
		this.zustand = zustand;
	}

	public UseCaseController1 getUseCaseController1() {
		return uc1;
	}

	public UseCaseController2 getUseCaseController2() {
		return uc2;
	}
	
	public void wuerfeln() {
		uc1.wuerfeln();
	}
	
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}
}
