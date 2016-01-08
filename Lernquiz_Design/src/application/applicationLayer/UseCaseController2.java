package application.applicationLayer;

public class UseCaseController2 {
	
	private int aktuelleKategorieIndex;
	private Kategorie aktuelleKategorie;
	private Frage aktuelleFrage;
	private Spieler testenderSpieler;
	private Spieler getesteterSpieler;
	private final ModelFront modelFront;

	
	public UseCaseController2(ModelFront modelFront) {
		this.modelFront = modelFront;
	}
	
	
	/**
	 * 
	 * @param ws1 testenderSpieler
	 * @param ws2 gestesteterSpieler
	 */
	public void wissenstestStarten(Wissensstreiter ws1, Wissensstreiter ws2) {
		testenderSpieler = ws1.getSpieler();
		getesteterSpieler = ws2.getSpieler();
		modelFront.setZustand(Zustand.WarteAufKategorieEingabe);
		modelFront.notifyObservers();
	}
	
	
	public void kategorieSetzen(Kategorie kategorie) {
		this.aktuelleKategorie = kategorie;
		this.aktuelleKategorieIndex = Spielfeld.getInstance().kategorien.indexOf(aktuelleKategorie);
		this.aktuelleFrage = aktuelleKategorie.getRandomFrage();
		modelFront.setZustand(Zustand.WarteAufAntwortEingabe);
		modelFront.notifyObservers();
	}
	
	public void antwortPruefen(int antwortNummer) {
		
		
		if(antwortNummer == aktuelleFrage.getRichtigeAntwortNummer()){
			int wissensstandszeiger = getesteterSpieler.getWissensstandszeiger().getWissensstandVonKategorie(aktuelleKategorieIndex);
			if (wissensstandszeiger == Wissensstandszeiger.MAX_WISSENSSTAND) {
				if (getesteterSpieler.getWissensstandszeiger().isGewinner()) {
					// TODO gewinner 
				} else {
					// TODO prüfe auf spielende ggf. nach nächste kat fragen 
					// modelFront.setZustand();
					// modelFront.notifyObservers();
				}
			} else {
				// Wissensstand erhöhen
				wissensstandszeigerErhoehen();
			}
		} else {
			// Antwort ist falsch
		}
		return;
	}
	


	public void wissensstandszeigerErhoehen() {
		getesteterSpieler.getWissensstandszeiger().wissensstandInkrementieren(aktuelleKategorieIndex);
	}

	
	
	
	
	
	
	public void selbstTesten(boolean selbstTesten) {
		
	}
}
