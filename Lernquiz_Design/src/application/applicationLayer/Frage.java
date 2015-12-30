package application.applicationLayer;
public class Frage {
	
	String frage;
	String[] antworten;
	
	int richtigeAntwort;

	/**
	 * @param richtigeAntwort 0 bis 3
	 */
	public Frage(String frageText, String antwort1, String antwort2, String antwort3, String antwort4, int richtigeAntwort) {
		
	}
	
	public int getRichtigeAntwortNummer() {
		return this.richtigeAntwort;
	}
}
