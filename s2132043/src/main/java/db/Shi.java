package db;

public class Shi {
	/** 市コード **/
	private int id;
	
	/** 市名 **/
	private String name;
	
	public Shi(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
