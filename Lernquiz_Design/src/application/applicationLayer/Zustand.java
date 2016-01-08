package application.applicationLayer;

public enum Zustand {
	NeuerSpielzug, 
	NeuerSpielzug_WuerfelnFail,
	NeuerSpielzug_WuerfelnSuccess, // Würfeln war erfolgreich, es wurde eine 6 gewüfelt
	WarteAufWissensstreiterEingabe, 
	WarteAufKategorieEingabe,
	WarteAufAntwortEingabe,
	NeuerSpielzug_FrageFail,
	NeuerSpielzug_FrageSuccess,
	WarteAufSelbsttestEingabe,
}
