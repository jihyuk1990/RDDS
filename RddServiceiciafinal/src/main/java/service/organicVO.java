package service;

public class organicVO {
	private String p_number; // 바코드
	private String p_name; // 이름
	private int p_price; //가격
	private int p_stock; //재고
	private String p_explain; // 설명
	private String p_photourl; //사진
	private String p_category; //카테고리
	private String Search;
	private String P_tabler;

	public String getP_tabler() {
		return P_tabler;
	}

	public void setP_tabler(String p_tabler) {
		P_tabler = p_tabler;
	}

	public String getSearch() {
		return Search;
	}

	public void setSearch(String search) {
		Search = search;
	}

	public String getP_number() {
		return p_number;
	}

	public void setP_number(String string) {
		this.p_number = string;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public int getP_stock() {
		return p_stock;
	}

	public void setP_stock(int p_stock) {
		this.p_stock = p_stock;
	}

	public String getP_explain() {
		return p_explain;
	}

	public void setP_explain(String p_explain) {
		this.p_explain = p_explain;
	}

	public String getP_photourl() {
		return p_photourl;
	}

	public void setP_photourl(String p_photourl) {
		this.p_photourl = p_photourl;
	}

	public String getP_category() {
		return p_category;
	}

	public void setP_category(String p_category) {
		this.p_category = p_category;
	}


}
