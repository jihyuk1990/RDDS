package service;

public class BasketVO {

	String id;
	String p_number;
	String p_name;
	int p_price;
	int p_amount;
	String p_photourl;
	int basketnum;
	String p_category;

	public String getP_category() {
		return p_category;
	}

	public void setP_category(String p_category) {
		this.p_category = p_category;
	}

	public int getBasketnum() {
		return basketnum;
	}

	public void setBasketnum(int basketnum) {
		this.basketnum = basketnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getP_number() {
		return p_number;
	}

	public void setP_number(String p_number) {
		this.p_number = p_number;
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

	public int getP_amount() {
		return p_amount;
	}

	public void setP_amount(int p_amount) {
		this.p_amount = p_amount;
	}

	public String getP_photourl() {
		return p_photourl;
	}

	public void setP_photourl(String p_photourl) {
		this.p_photourl = p_photourl;
	}

}
