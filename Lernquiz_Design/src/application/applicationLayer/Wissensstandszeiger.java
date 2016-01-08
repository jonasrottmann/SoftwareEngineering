package application.applicationLayer;

public class Wissensstandszeiger {
	
	public static final int MAX_WISSENSSTAND = 4;
	
	private int[] zeiger = new int[4];
	
	public int getWissensstandVonKategorie(int index) {
		return zeiger[index];
	}
	
	public boolean isGewinner() {
		for (int i : zeiger) {
			if ( i != MAX_WISSENSSTAND) {
				return false;
			}
		}
		return true;
	}
	
	public void wissensstandInkrementieren(int kategorieIndex) {
		zeiger[kategorieIndex]++;
	}
	
	public void wissensstandDekrementieren(int kategorieIndex) {
		zeiger[kategorieIndex]--;
		if (zeiger[kategorieIndex] < 0) {
			zeiger[kategorieIndex] = 0;
		}
	}
}