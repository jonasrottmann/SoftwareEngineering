package application.applicationLayer;

public enum Zustand {
	NeuerSpielzug, 
	NeuerSpielzug_FAIL,
	NeuerSpielzug_SUCCESS, // Würfeln war erfolgreich, es wurde eine 6 gewüfelt
	WarteAufWissensstreiterEingabe, 
	WarteAufKategorieEingabe,
	WarteAufAntwortEingabe,
	WarteAufSelbsttestEingabe,
}
