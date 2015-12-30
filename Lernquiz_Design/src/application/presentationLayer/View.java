package application.presentationLayer;

import java.util.Observable;
import java.util.Observer;

import application.applicationLayer.ModelFront;

public class View implements Observer {
	
	ModelFront model;
	Controller controller;

	
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}
	
	public void display() {
		
	}
	
	public void manipulate() {
		
	}
}
