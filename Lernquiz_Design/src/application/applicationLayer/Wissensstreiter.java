package application.applicationLayer;
public class Wissensstreiter {
	public static final int POSITION_IM_HEIMATFELD = -1;
	private int position;

	public Wissensstreiter() {
		position = POSITION_IM_HEIMATFELD;
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
