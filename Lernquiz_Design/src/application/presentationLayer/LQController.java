package application.presentationLayer;

import java.util.Observable;
import java.util.Observer;

import application.applicationLayer.LQModelFront;

public class LQController implements Observer {
	
	LQModelFront modelFront;
	LQView view;
	
	public void handleEvent(){
		
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
