package application.applicationLayer.spiel;
public class Frage {
	
	private final String frage;
	private final String[] antworten;
	private final int richtigeAntwort;

	/**
	 * @param richtigeAntwort 0 bis 3
	 */
	public Frage(String frageText, String antwort1, String antwort2, String antwort3, String antwort4, int richtigeAntwort) {
		this.frage = frageText;
		this.antworten = new String[]{antwort1, antwort2, antwort3, antwort4};
		this.richtigeAntwort = richtigeAntwort;
	}
	
	public int getRichtigeAntwortNummer() {
		return this.richtigeAntwort;
	}

	public String getFrage() {
		return frage;
	}

	public String[] getAntworten() {
		return antworten;
	}
	
}
