package application.applicationLayer;

public enum Zustand {
	NeuerSpielzug, 
	NeuerSpielzug_WuerfelnFail,
	NeuerSpielzug_WuerfelnSuccess, // W�rfeln war erfolgreich, es wurde eine 6 gew�felt
	WarteAufWissensstreiterEingabe, 
	WarteAufKategorieEingabe,
	WarteAufAntwortEingabe,
	NeuerSpielzug_FrageFail,
	NeuerSpielzug_FrageSuccess,
	WarteAufSelbsttestEingabe,
}
