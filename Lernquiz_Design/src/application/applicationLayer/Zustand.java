package application.applicationLayer;

public enum Zustand {
	NeuerSpielzug, 
	NeuerSpielzug_FAIL,
	NeuerSpielzug_SUCCESS, // W�rfeln war erfolgreich, es wurde eine 6 gew�felt
	WarteAufWissensstreiterEingabe, 
	WarteAufKategorieEingabe,
	WarteAufAntwortEingabe,
	WarteAufSelbsttestEingabe,
}
