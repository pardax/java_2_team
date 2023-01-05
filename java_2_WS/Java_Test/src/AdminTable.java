

public class AdminTable {
	private String num;
	private String name;
	private String id;
	private String en;
	
	public AdminTable(String num, String name, String id, String en) {
		this.num = num;
		this.name = name;
		this.id = id;
		this.en = en;
	}
	
	public String getNum() {
		return num;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getEn() {
		return en;
	}
}
