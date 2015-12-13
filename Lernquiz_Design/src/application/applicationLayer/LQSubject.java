package application.applicationLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public abstract class LQSubject {
	
	List<Observer> observers;
	
	public LQSubject() {
		this.observers = new ArrayList<Observer>();
	}
	
	public void attach(Observer obs){
		this.observers.add(obs);
	};
	
	public void detach(Observer obs){
		this.observers.remove(obs);
	};
	
	public void notifyObservers(){
		for (Observer observer : this.observers) {
			observer.update(null, null);
		}
	}
	
}
