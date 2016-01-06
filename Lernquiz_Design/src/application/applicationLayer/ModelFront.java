package application.applicationLayer;

import java.util.Observable;

public class ModelFront extends Observable {
	private UseCaseController1 useCaseController1;
	private UseCaseController2 useCaseController2;
	private Zustand zustand;
	
	public ModelFront() {
		useCaseController1 = new UseCaseController1();
		useCaseController2 = new UseCaseController2();
	}

	public Zustand getZustand() {
		return zustand;
	}

	public void setZustand(Zustand zustand) {
		this.zustand = zustand;
	}

	public UseCaseController1 getUseCaseController1() {
		return useCaseController1;
	}

	public UseCaseController2 getUseCaseController2() {
		return useCaseController2;
	}
	
	
}
