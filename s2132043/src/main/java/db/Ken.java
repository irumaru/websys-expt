package db;

public class Ken {
	/** 県コード **/
	private int id;
	
	/** 県名 **/
	private String name;
	
	public Ken(int id, String name) {
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
