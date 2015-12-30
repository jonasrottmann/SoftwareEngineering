package application.applicationLayer;

public class Wissensstandszeiger {
	
	int[] zeiger = new int[4];

	public int getWissensstandVonKategorie(int index) {
		return zeiger[index];
	}
}