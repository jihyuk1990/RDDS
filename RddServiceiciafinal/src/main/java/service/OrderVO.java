package service;

import java.sql.Date;

public class OrderVO {

	String id;
	String p_number;
	String p_name;
	int p_price;
	int p_amount;
	String p_photourl;
	Date today;
	String name;
	String address1;
	String address2;
	String address3;
	String tel1;
	String tel2;
	String tel3;
	String request;
	int allpay_price;
	String paymethod;
	String p_category;
	String p_delivery; 
	String droneaddress; 
	
	
	
	
	
	public String getDroneaddress() {
		return droneaddress;
	}
	public void setDroneaddress(String droneaddress) {
		this.droneaddress = droneaddress;
	}
	public String getP_delivery() {
		return p_delivery;
	}
	public void setP_delivery(String p_delivery) {
		this.p_delivery = p_delivery;
	}
	public String getP_category() {
		return p_category;
	}
	public void setP_category(String p_category) {
		this.p_category = p_category;
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
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public int getAllpay_price() {
		return allpay_price;
	}
	public void setAllpay_price(int allpay_price) {
		this.allpay_price = allpay_price;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

     
	
	
	
}
