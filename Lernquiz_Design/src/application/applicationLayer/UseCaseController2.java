package application.applicationLayer;

import application.applicationLayer.spiel.Frage;
import application.applicationLayer.spiel.Kategorie;
import application.applicationLayer.spiel.Spielfeld;
import application.applicationLayer.spiel.Wissensstandszeiger;
import application.applicationLayer.spiel.Wissensstreiter;

public class UseCaseController2 {
	
	private int aktuelleKategorieIndex;
	private Kategorie aktuelleKategorie;
	private Frage aktuelleFrage;
	private final Fassade model;
	private Wissensstreiter testenderWissensstreiter;
	private Wissensstreiter getesteterWissensstreiter;
	




	public UseCaseController2(Fassade model) {
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
		if (!isSelbstTest()) {
			model.setZustand(Zustaende.Warte_KategorieEingabe);
			model.notifyObservers();
		} else {
			kategorieSetzen(aktuelleKategorie);
		}
	}
	
	
	public void kategorieSetzen(Kategorie kategorie) {
		this.aktuelleKategorie = kategorie;
		this.aktuelleKategorieIndex = Spielfeld.getInstance().getKategorien().indexOf(aktuelleKategorie);
		this.aktuelleFrage = aktuelleKategorie.getRandomFrage();
		model.setZustand(Zustaende.Warte_AntwortEingabe);
		model.notifyObservers();
	}
	
	public void antwortPruefen(int antwortNummer) {
		if(antwortNummer == aktuelleFrage.getRichtigeAntwortNummer()){
			// Antwort ist richtig
			int wissensstandszeiger = getesteterWissensstreiter.getSpieler().getWissensstandsZeiger().getWissensstandVonKategorie(aktuelleKategorieIndex);
			if (wissensstandszeiger == Wissensstandszeiger.MAX_WISSENSSTAND) {
				if (getesteterWissensstreiter.getSpieler().getWissensstandsZeiger().isGewinner()) {
					model.setZustand(Zustaende.GewinnerErmittelt);
					model.notifyObservers();
				} else {
					model.setZustand(Zustaende.Warte_AlternativeKategorieEingabe);
					model.notifyObservers();
				}
			} else {
				// Wissensstand erhöhen
				wissensstandszeigerInkrementieren();
				int startfeldGetesteterSpieler = Spielfeld.STARTFELDER[Spielfeld.getInstance().getSpieler().indexOf(getesteterWissensstreiter.getSpieler())];
				
				if(Spielfeld.getInstance().isFeldBesetzt(startfeldGetesteterSpieler) != null){
					getesteterWissensstreiter.setPosition(Wissensstreiter.POSITION_IM_HEIMATFELD);
				}else{
					getesteterWissensstreiter.setPosition(startfeldGetesteterSpieler);
				}
				model.getUseCaseController1().setNaechsterSpieler();
				model.setZustand(Zustaende.NeuerSpielzug_FrageSuccess);
				model.notifyObservers();
			}
		} else {
			// Antwort ist falsch
			wissensstandszeigerDekrementieren();
			getesteterWissensstreiter.setPosition(Wissensstreiter.POSITION_IM_HEIMATFELD);
			if(getesteterWissensstreiter.getSpieler().equals(testenderWissensstreiter.getSpieler())){
				getesteterWissensstreiter.setPosition(Wissensstreiter.POSITION_IM_HEIMATFELD);
				model.getUseCaseController1().setNaechsterSpieler();
				model.setZustand(Zustaende.NeuerSpielzug_FrageFail);
				model.notifyObservers();
			}else{
				model.setZustand(Zustaende.Warte_SelbsttestEingabe);
				model.notifyObservers();
			}
		}
	}
	
	public void selbstTestStarten(){
		wissenstestStarten(testenderWissensstreiter, testenderWissensstreiter);
	}
	
	private void wissensstandszeigerInkrementieren() {
		getesteterWissensstreiter.getSpieler().getWissensstandsZeiger().wissensstandInkrementieren(aktuelleKategorieIndex);
	}
	
	private void wissensstandszeigerDekrementieren() {
		getesteterWissensstreiter.getSpieler().getWissensstandsZeiger().wissensstandDekrementieren(aktuelleKategorieIndex);
	}

	public void alternativeKategorieSetzen(int kategorieIndex) {
		getesteterWissensstreiter.getSpieler().getWissensstandsZeiger().wissensstandInkrementieren(kategorieIndex);
		model.getUseCaseController1().setNaechsterSpieler();
		model.setZustand(Zustaende.NeuerSpielzug_FrageSuccess);
		model.notifyObservers();
	}
	
	private boolean isSelbstTest() {
		return testenderWissensstreiter.equals(getesteterWissensstreiter);
	}
	
	public Wissensstreiter getGetesteterWissensstreiter() {
		return getesteterWissensstreiter;
	}
	
	public Wissensstreiter getTestenderWissensstreiter() {
		return testenderWissensstreiter;
	}
	
	public Kategorie getAktuelleKategorie() {
		return aktuelleKategorie;
	}
	
	public Frage getAktuelleFrage() {
		return aktuelleFrage;
	}
}
