package application.applicationLayer;

public enum Zustaende {
	NeuerSpielzug, 
	NeuerSpielzug_ErstesWuerfelnFail,
	NeuerSpielzug_ErstesWuerfelnSuccess,
	NeuerSpielzug_WissensstreiterAufSpielfeldgebracht,
	Warte_WissensstreiterEingabe, 
	Warte_KategorieEingabe,
	WarteAufAntwortEingabe,
	NeuerSpielzug_FrageFail,
	NeuerSpielzug_FrageSuccess,
	Warte_SelbsttestEingabe,
	Warte_AlternativeKategorieEingabe,
	GewinnerErmittelt
}
