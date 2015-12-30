package application.applicationLayer;

import java.util.Observable;

public class ModelFront {
	private UseCaseController1 useCaseController1;
	private UseCaseController2 useCaseController2;
	
	
	public ModelFront() {
		useCaseController1 = new UseCaseController1();
		useCaseController2 = new UseCaseController2();
	}
}
