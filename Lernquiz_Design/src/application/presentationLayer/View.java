package application.presentationLayer;

import java.util.Observable;
import java.util.Observer;

import application.applicationLayer.ModelFront;

public class View implements Observer {
	
	private ModelFront model;
	private Controller controller;

	
	public void update(Observable obs, Object arg) {
		this.model = (ModelFront) obs;
		
		
	}
	
	public void display() {
		
	}
	
	public void manipulate() {
		
	}
}
