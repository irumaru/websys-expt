package db;

public class Region {
	private int kencode;
	private int shicode;
	private String kenName;
	private String shiName;
	
	public Region(int kencode, String kenName, int shicode, String shiName) {
		this.kencode = kencode;
		this.kenName = kenName;
		this.shicode = shicode;
		this.shiName = shiName;
	}

	public int getKencode() {
		return kencode;
	}

	public int getShicode() {
		return shicode;
	}

	public String getKenName() {
		return kenName;
	}

	public String getShiName() {
		return shiName;
	}
}
