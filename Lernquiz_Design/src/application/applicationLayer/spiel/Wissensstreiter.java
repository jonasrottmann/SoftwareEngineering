package application.applicationLayer.spiel;
public class Wissensstreiter {
	public static final int POSITION_IM_HEIMATFELD = -1;
	private int position;
	private final Spieler spieler;

	public Spieler getSpieler() {
		return spieler;
	}

	public Wissensstreiter(Spieler spieler) {
		position = POSITION_IM_HEIMATFELD;
		this.spieler = spieler;
	}
	
	public int getPosition() {
		return position;
	}

	/**
	 * 
	 * @param position Muss kleiner {@link Spielfeld#ANZAHL_FELDER} sein.
	 */
	public void setPosition(int position) {
		this.position = position;
	}
}
