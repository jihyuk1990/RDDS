package service;

public class MemberVO {

	private String id; // 아이디
	private String pw; // 비번
	private String name;// 이름
	private String birthYY; // 년
	private String birthMM; // 월
	private String birthDD; // 일
	private String gender; // 성별

	private String email;

	private String address1; // 지번주소
	private String address2; // 도로명주소
	private String address3; // 상세주소
	private String tel1; // 휴대폰
	private String tel2;
	private String tel3;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthYY() {
		return birthYY;
	}

	public void setBirthYY(String birthYY) {
		this.birthYY = birthYY;
	}

	public String getBirthMM() {
		return birthMM;
	}

	public void setBirthMM(String birthMM) {
		this.birthMM = birthMM;
	}

	public String getBirthDD() {
		return birthDD;
	}

	public void setBirthDD(String birthDD) {
		this.birthDD = birthDD;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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



}
