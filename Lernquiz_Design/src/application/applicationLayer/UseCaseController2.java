package application.applicationLayer;

public class UseCaseController2 {
	
	private int aktuelleKategorieIndex;
	private Kategorie aktuelleKategorie;
	private Frage aktuelleFrage;
	private final ModelFront model;
	private Wissensstreiter testenderWissensstreiter;
	private Wissensstreiter getesteterWissensstreiter;
	

	
	public UseCaseController2(ModelFront model) {
		this.model = model;
	}
	
	
	/**
	 * 
	 * @param ws1 testenderSpieler
	 * @param ws2 gestesteterSpieler
	 */
	public void wissenstestStarten(Wissensstreiter ws1, Wissensstreiter ws2) {
		testenderWissensstreiter = ws1;
		getesteterWissensstreiter = ws2;
		model.setZustand(Zustand.WarteAufKategorieEingabe);
		model.notifyObservers();
	}
	
	
	public void kategorieSetzen(Kategorie kategorie) {
		this.aktuelleKategorie = kategorie;
		this.aktuelleKategorieIndex = Spielfeld.getInstance().kategorien.indexOf(aktuelleKategorie);
		this.aktuelleFrage = aktuelleKategorie.getRandomFrage();
		model.setZustand(Zustand.WarteAufAntwortEingabe);
		model.notifyObservers();
	}
	
	public void antwortPruefen(int antwortNummer) {
		
		
		if(antwortNummer == aktuelleFrage.getRichtigeAntwortNummer()){
			int wissensstandszeiger = getesteterWissensstreiter.getSpieler().getWissensstandsZeiger().getWissensstandVonKategorie(aktuelleKategorieIndex);
			if (wissensstandszeiger == Wissensstandszeiger.MAX_WISSENSSTAND) {
				if (getesteterWissensstreiter.getSpieler().getWissensstandsZeiger().isGewinner()) {
					// TODO gewinner 
				} else {
					// TODO prüfe auf spielende ggf. nach nächste kat fragen 
					// modelFront.setZustand();
					// modelFront.notifyObservers();
				}
			} else {
				// Wissensstand erhöhen
				wissensstandszeigerErhoehen();
				int startfeldGetesteterSpieler = Spielfeld.STARTFELDER[Spielfeld.getInstance().spieler.indexOf(getesteterWissensstreiter.getSpieler())];
				
				if(Spielfeld.getInstance().isFeldBesetzt(startfeldGetesteterSpieler) != null){
					getesteterWissensstreiter.setPosition(Wissensstreiter.POSITION_IM_HEIMATFELD);
				}else{
					getesteterWissensstreiter.setPosition(startfeldGetesteterSpieler);
				}
				
				model.getUseCaseController1().setNaechsterSpieler();
	
				model.setZustand(Zustand.NeuerSpielzug_FrageSuccess);
			}
		} else {
			// Antwort ist falsch
			if(getesteterWissensstreiter.getSpieler().equals(testenderWissensstreiter.getSpieler())){
				getesteterWissensstreiter.setPosition(Wissensstreiter.POSITION_IM_HEIMATFELD);
				model.getUseCaseController1().setNaechsterSpieler();
				model.setZustand(Zustand.NeuerSpielzug_FrageSuccess);
			}else{
				this.model.setZustand(Zustand.WarteAufSelbsttestEingabe);
			}
		}
	}
	
	public void selbstTestStarten(){
		wissenstestStarten(testenderWissensstreiter, testenderWissensstreiter);
	}
	


	public void wissensstandszeigerErhoehen() {
		getesteterWissensstreiter.getSpieler().getWissensstandsZeiger().wissensstandInkrementieren(aktuelleKategorieIndex);
	}

	
}
