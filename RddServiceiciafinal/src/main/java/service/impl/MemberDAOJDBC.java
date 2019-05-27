package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.prefs.BackingStoreException;

import javax.servlet.http.HttpSession;

import PageDTO.PageMaker;
import service.BasketVO;
import service.MemberVO;
import service.OrderVO;
import service.organicVO;
import service.common.JDBCUtil;
import web.MemberController;

public class MemberDAOJDBC {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	private final String RDDMEMBER_INSERT = "insert into rddusermember(id,"
			+ "password, name, birthYY, birthMM, birthDD, gender, tel1, tel2, tel3, email, "
			+ "address1, address2, address3) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String BASKET_INPUT = "insert into rddbasket(ID,P_NUMBER,P_NAME,P_PRICE,P_AMOUNT,P_PHOTOURL,P_CATEGORY) values(?,?,?,?,?,?,?)";

	private final String ORDERCOMPLETE_INSERT = "insert into rddorder values(?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private final String MODIFY_UPDATE = "update rddusermember set password=?, tel1=?, tel2=?, tel3=?, email=?, address1=?, address2=?, address3=? where id=?";

	private final String DELETE_BASKET = "delete from rddbasket where basketnum = ?";

	private final String ORDER_LIST = "select * from rddorder where id =?";

	private final String BASKET_LIST = "select * from rddbasket where basketnum = ?";

	public MemberDAOJDBC() {
		System.out.println("샘플다오 객체생성");
	}

	public int Totalsearch(PageMaker page, organicVO vo) throws Exception {

		System.out.println("DAO 검색값 : " + vo.getSearch());
		String CLOTHES_COUNT = "select max(count) as maxcount from (select count(*) as count from rdds.rddfood where  p_name like '%"
				+ vo.getSearch() + "%' or p_explain like '%" + vo.getSearch()
				+ "%' union select count(*) from rdds.rddbeauty where p_name like '%" + vo.getSearch()
				+ "%' or p_explain like '%" + vo.getSearch()
				+ "%' union select count(*) from rdds.rddclothes where p_name like '%" + vo.getSearch()
				+ "%' or p_explain like '%" + vo.getSearch()
				+ "%' union select count(*) from rdds.rdddaily where p_name like '%" + vo.getSearch()
				+ "%' or p_explain like '%" + vo.getSearch() + "%') as masval";

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(CLOTHES_COUNT);

		rs = stmt.executeQuery();

		while (rs.next()) {

			page.setTotalcount(rs.getInt("maxcount"));

			System.out.println("카운트 : " + rs.getInt("maxcount"));
		}

		JDBCUtil.close(rs, stmt, conn);

		int total = page.getTotalcount();

		System.out.println("pagemaker total : " + total);
		return total;
	}

	public int pageTotal(PageMaker page, organicVO vo) throws Exception {

		String CLOTHES_COUNT = "select max(count) as maxcount from (select count(*) as count from rdds.rddfood where p_category = ? union select count(*) from rdds.rddbeauty where p_category = ? union select count(*) from rdds.rddclothes where p_category = ? union select count(*) from rdds.rdddaily where p_category = ?) as masval";

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(CLOTHES_COUNT);
		stmt.setString(1, vo.getP_category());
		stmt.setString(2, vo.getP_category());
		stmt.setString(3, vo.getP_category());
		stmt.setString(4, vo.getP_category());

		System.out.println("pagemakerVOO : " + vo.getP_category());
		rs = stmt.executeQuery();

		while (rs.next()) {

			page.setTotalcount(rs.getInt("maxcount"));

			System.out.println("카운트 : " + rs.getInt("maxcount"));
		}

		JDBCUtil.close(rs, stmt, conn);

		int total = page.getTotalcount();

		System.out.println("pagemaker total : " + total);
		return total;
	}

	public List<organicVO> organicList(organicVO vo, PageMaker page) throws Exception {

		String ORGANIC_LIST = "select * from rdds.rddfood where p_category = ? order by p_number desc limit ?, ?";
		String CLOTHES_LIST = "select * from rdds.rddclothes where p_category = ? order by p_number desc limit ?, ?";
		String DAILY_LIST = "select * from rdds.rdddaily where p_category = ? order by p_number desc limit ?, ?";
		String BEAUTY_LIST = "select * from rdds.rddbeauty where p_category = ? order by p_number desc limit ?, ?";

		List<organicVO> organicList = new ArrayList<organicVO>();

		organicVO organic = new organicVO();

		int pagenumbers = page.getPagenum() * 20;
		System.out.println("페이지 넘버스 : " + pagenumbers);

		conn = JDBCUtil.getConnection();

		System.out.println("쿼리문 시작");
		stmt = conn.prepareStatement(ORGANIC_LIST);
		stmt.setString(1, vo.getP_category());
		stmt.setInt(2, pagenumbers);
		stmt.setInt(3, page.getContentnum());

		rs = stmt.executeQuery();

		while (rs.next()) {
			organic = new organicVO();
			System.out.println("쿼리문 성공");
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_stock(rs.getInt("p_stock"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organicList.add(organic);

			System.out.println(rs.getString("p_number"));
			System.out.println("dao url : " + rs.getString("p_photourl"));
			System.out.println("dao vo url : " + organic.getP_photourl());

		}

		System.out.println("여기까지??");
		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();

		System.out.println("쿼리문 시작");
		stmt = conn.prepareStatement(CLOTHES_LIST);
		stmt.setString(1, vo.getP_category());
		stmt.setInt(2, pagenumbers);
		stmt.setInt(3, page.getContentnum());

		rs = stmt.executeQuery();

		while (rs.next()) {
			organic = new organicVO();
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_stock(rs.getInt("p_stock"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organicList.add(organic);

			System.out.println(rs.getString("p_number"));

		}

		System.out.println("여기까지??");
		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();

		System.out.println("쿼리문 시작");
		stmt = conn.prepareStatement(DAILY_LIST);
		stmt.setString(1, vo.getP_category());
		stmt.setInt(2, pagenumbers);
		stmt.setInt(3, page.getContentnum());

		rs = stmt.executeQuery();

		while (rs.next()) {
			organic = new organicVO();
			System.out.println("쿼리문 성공");
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_stock(rs.getInt("p_stock"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organicList.add(organic);

			System.out.println(rs.getString("p_number"));

		}

		System.out.println("여기까지??");
		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();

		System.out.println("쿼리문 시작");
		stmt = conn.prepareStatement(BEAUTY_LIST);
		stmt.setString(1, vo.getP_category());
		stmt.setInt(2, pagenumbers);
		stmt.setInt(3, page.getContentnum());

		rs = stmt.executeQuery();

		while (rs.next()) {
			organic = new organicVO();
			System.out.println("쿼리문 성공");
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_stock(rs.getInt("p_stock"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organicList.add(organic);

			System.out.println(rs.getString("p_number"));

		}

		System.out.println("여기까지??");
		JDBCUtil.close(rs, stmt, conn);

		return organicList;

	}

	public void insertMember(MemberVO vo) throws Exception {
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(RDDMEMBER_INSERT);

		stmt.setString(1, vo.getId());
		stmt.setString(2, vo.getPw());
		stmt.setString(3, vo.getName());
		stmt.setString(4, vo.getBirthYY());
		stmt.setString(5, vo.getBirthMM());
		stmt.setString(6, vo.getBirthDD());

		stmt.setString(7, vo.getGender());
		stmt.setString(8, vo.getTel1());
		stmt.setString(9, vo.getTel2());
		stmt.setString(10, vo.getTel3());
		stmt.setString(11, vo.getEmail());
		stmt.setString(12, vo.getAddress1());
		stmt.setString(13, vo.getAddress2());
		stmt.setString(14, vo.getAddress3());

		stmt.executeUpdate();

		JDBCUtil.close(stmt, conn);

	}

	public String loginOk(MemberVO vo) throws Exception {

		conn = JDBCUtil.getConnection();

		String loginID = null;
		String LOGIN_OK = "select * from rddusermember where id=? and password=?";

		try {
			stmt = conn.prepareStatement(LOGIN_OK);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPw());

			rs = stmt.executeQuery();

			if (rs.next()) {
				loginID = rs.getString("id");

				if (vo.getId().equals(loginID)) {
					System.out.println("id");
				} else {
					loginID = null;
				}
			}

		} catch (Exception e) {
			System.out.println("에러");
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}

		return loginID;

	}

	public MemberVO serchEmailOk(MemberVO vo) throws Exception {
		conn = JDBCUtil.getConnection();

		String searchEmail = null;
		String searchName = null;
		String searchPassword = null;
		String searchId = null;
		String searchTel1 = null;
		String searchTel2 = null;
		String searchTel3 = null;

		MemberVO receiveVo = new MemberVO();

		String SEARCHEAMIL_OK = "select * from rddusermember where name=? and email=? and tel1=? and tel2=? and tel3=?";

		try {
			stmt = conn.prepareStatement(SEARCHEAMIL_OK);
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getEmail());
			stmt.setString(3, vo.getTel1());
			stmt.setString(4, vo.getTel2());
			stmt.setString(5, vo.getTel3());

			System.out.println(vo.getName());
			System.out.println(vo.getEmail());

			rs = stmt.executeQuery();

			if (rs.next()) {
				searchEmail = rs.getString("email");
				searchName = rs.getString("name");
				searchPassword = rs.getString("password");
				searchId = rs.getString("id");
				searchTel1 = rs.getString("tel1");
				searchTel2 = rs.getString("tel2");
				searchTel3 = rs.getString("tel3");

				System.out.println(searchEmail);
				System.out.println(searchName);
				System.out.println(searchPassword);
				System.out.println(searchId);

				receiveVo.setEmail(searchEmail);
				receiveVo.setName(searchName);
				receiveVo.setPw(searchPassword);
				receiveVo.setId(searchId);
				receiveVo.setTel1(searchTel1);
				receiveVo.setTel2(searchTel2);
				receiveVo.setTel3(searchTel3);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}

		return receiveVo;

	}

	public String checkID(MemberVO vo) throws Exception {

		conn = JDBCUtil.getConnection();

		String checkID = null;
		String CHECKID_OK = "select * from rddusermember where id=?";

		try {
			stmt = conn.prepareStatement(CHECKID_OK);
			stmt.setString(1, vo.getId());

			rs = stmt.executeQuery();

			if (rs.next()) {
				checkID = rs.getString("id");

				if ((vo.getId()).equals(checkID)) {
					System.out.println("id");
				}
			}

		} catch (Exception e) {
			System.out.println("에러");
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}

		return checkID;

	}

	public organicVO selectorganic(organicVO vo) throws Exception {

		String select = "select * from rddfood where p_number=? and p_category=?";
		String select2 = "select * from rddclothes where p_number=? and p_category=?";
		String select3 = "select * from rddbeauty where p_number=? and p_category=?";
		String select4 = "select * from rdddaily where p_number=? and p_category=?";

		organicVO organic = null;
		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(select);
		stmt.setString(1, vo.getP_number());
		stmt.setString(2, vo.getP_category());
		rs = stmt.executeQuery();

		System.out.println(vo.getP_number());

		if (rs.next()) {
			organic = new organicVO();
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organic.setP_stock(rs.getInt("p_stock"));
			System.out.println(rs.getString("p_category") + "왜안나와?");
		}

		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(select2);
		stmt.setString(1, vo.getP_number());
		stmt.setString(2, vo.getP_category());
		rs = stmt.executeQuery();

		System.out.println(vo.getP_number());

		if (rs.next()) {
			organic = new organicVO();
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organic.setP_stock(rs.getInt("p_stock"));
			System.out.println(rs.getString("p_category") + "왜안나와?");
		}

		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(select3);
		stmt.setString(1, vo.getP_number());
		stmt.setString(2, vo.getP_category());
		rs = stmt.executeQuery();

		System.out.println(vo.getP_number());

		if (rs.next()) {
			organic = new organicVO();
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organic.setP_stock(rs.getInt("p_stock"));
			System.out.println(rs.getString("p_category") + "왜안나와?");
		}

		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(select4);
		stmt.setString(1, vo.getP_number());
		stmt.setString(2, vo.getP_category());
		rs = stmt.executeQuery();

		System.out.println(vo.getP_number());

		if (rs.next()) {
			organic = new organicVO();
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organic.setP_stock(rs.getInt("p_stock"));
			System.out.println(rs.getString("p_category") + "왜안나와?");
		}

		JDBCUtil.close(rs, stmt, conn);

		return organic;
	}

	public MemberVO memberModify(MemberVO vo) throws Exception {
		System.out.println("회원정보 수정을 위해 디비에서 불러오는 작업");

		String modify = "select * from rddusermember where id=?";
		MemberVO voForModify = new MemberVO();

		conn = JDBCUtil.getConnection();

		try {
			stmt = conn.prepareStatement(modify);
			stmt.setString(1, vo.getId());
			rs = stmt.executeQuery();

			while (rs.next()) {
				voForModify.setId(rs.getString("id"));
				voForModify.setPw(rs.getString("password"));
				voForModify.setName(rs.getString("name"));
				voForModify.setBirthYY(rs.getString("birthYY"));
				voForModify.setBirthMM(rs.getString("birthMM"));
				voForModify.setBirthDD(rs.getString("birthDD"));
				voForModify.setGender(rs.getString("gender"));
				voForModify.setTel1(rs.getString("tel1"));
				voForModify.setTel2(rs.getString("tel2"));
				voForModify.setTel3(rs.getString("tel3"));
				voForModify.setEmail(rs.getString("email"));
				voForModify.setAddress1(rs.getString("address1"));
				voForModify.setAddress2(rs.getString("address2"));
				voForModify.setAddress3(rs.getString("address3"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JDBCUtil.close(rs, stmt, conn);

		return voForModify;

	}

	public String inputToBasket(BasketVO vo, String loginId) throws Exception {

		String selectFood = "select * from rddfood where p_number=? and p_category=?";
		String selectBeaty = "select * from rddbeauty where p_number=? and p_category=?";
		String selectClothes = "select * from rddclothes where p_number=? and p_category=?";
		String selectDaily = "select * from rdddaily where p_number=? and p_category=?";

		String result = "";// 처리결과를 담는 곳

		String p_number = vo.getP_number();// rddfood테이블에서 수량을 뽑아오기 위한 품번
		String p_category = vo.getP_category();

		organicVO organic = null;

		// 1. rddbeaty 탐색 시작
		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(selectFood);
		stmt.setString(1, p_number);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		// 쿼리문 결과값이 있는지 없는지 판별하기 위해 행하는 rs.next()
		if (rs.next()) {// rddfood테이블에서 p_number와 p_category에 해당하는 데이터를 찾았다면 아래 코드를 실행
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(selectFood);
			stmt.setString(1, p_number);
			stmt.setString(2, p_category);
			rs = stmt.executeQuery();

			if (rs.next()) {
				organic = new organicVO();
				organic.setP_stock(rs.getInt("p_stock"));

			}

			JDBCUtil.close(rs, stmt, conn);

			if ((organic.getP_stock()) < vo.getP_amount()) {

				System.out.println("재고 : " + organic.getP_stock());
				System.out.println("사는 수량 : " + vo.getP_amount());

				result = "NO";

			} else {

				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BASKET_INPUT);

				stmt.setString(1, loginId);
				stmt.setString(2, vo.getP_number());
				stmt.setString(3, vo.getP_name());
				stmt.setInt(4, vo.getP_price());
				stmt.setInt(5, vo.getP_amount());
				stmt.setString(6, vo.getP_photourl());
				stmt.setString(7, vo.getP_category());

				stmt.executeUpdate();

				JDBCUtil.close(stmt, conn);

				result = "YES";

			}

		}

		// 2. rddbeaty 탐색 시작
		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(selectBeaty);
		stmt.setString(1, p_number);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		// 쿼리문 결과값이 있는지 없는지 판별하기 위해 행하는 rs.next()
		if (rs.next()) {// rddbeaty테이블에서 p_number와 p_category에 해당하는 데이터를 찾았다면 아래 코드를 실행
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(selectBeaty);
			stmt.setString(1, p_number);
			stmt.setString(2, p_category);
			rs = stmt.executeQuery();

			if (rs.next()) {
				organic = new organicVO();
				organic.setP_stock(rs.getInt("p_stock"));

			}

			JDBCUtil.close(rs, stmt, conn);

			if ((organic.getP_stock()) < vo.getP_amount()) {

				System.out.println("재고 : " + organic.getP_stock());
				System.out.println("사는 수량 : " + vo.getP_amount());

				result = "NO";

			} else {

				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BASKET_INPUT);

				stmt.setString(1, loginId);
				stmt.setString(2, vo.getP_number());
				stmt.setString(3, vo.getP_name());
				stmt.setInt(4, vo.getP_price());
				stmt.setInt(5, vo.getP_amount());
				stmt.setString(6, vo.getP_photourl());
				stmt.setString(7, vo.getP_category());

				stmt.executeUpdate();

				JDBCUtil.close(stmt, conn);

				result = "YES";

			}

		}

		// 3.rddclothes 탐색 시작
		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(selectClothes);
		stmt.setString(1, p_number);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		// 쿼리문 결과값이 있는지 없는지 판별하기 위해 행하는 rs.next()
		if (rs.next()) {// rddclothes테이블에서 p_number와 p_category에 해당하는 데이터를 찾았다면 아래 코드를 실행
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(selectClothes);
			stmt.setString(1, p_number);
			stmt.setString(2, p_category);
			rs = stmt.executeQuery();

			if (rs.next()) {
				organic = new organicVO();
				organic.setP_stock(rs.getInt("p_stock"));

			}

			JDBCUtil.close(rs, stmt, conn);

			if ((organic.getP_stock()) < vo.getP_amount()) {

				System.out.println("재고 : " + organic.getP_stock());
				System.out.println("사는 수량 : " + vo.getP_amount());

				result = "NO";

			} else {

				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BASKET_INPUT);

				stmt.setString(1, loginId);
				stmt.setString(2, vo.getP_number());
				stmt.setString(3, vo.getP_name());
				stmt.setInt(4, vo.getP_price());
				stmt.setInt(5, vo.getP_amount());
				stmt.setString(6, vo.getP_photourl());
				stmt.setString(7, vo.getP_category());

				stmt.executeUpdate();

				JDBCUtil.close(stmt, conn);

				result = "YES";

			}

		}

		// 4.rdddaily 탐색 시작
		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(selectDaily);
		stmt.setString(1, p_number);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		// 쿼리문 결과값이 있는지 없는지 판별하기 위해 행하는 rs.next()
		if (rs.next()) {// rdddaily테이블에서 p_number와 p_category에 해당하는 데이터를 찾았다면 아래 코드를 실행
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(selectDaily);
			stmt.setString(1, p_number);
			stmt.setString(2, p_category);
			rs = stmt.executeQuery();

			if (rs.next()) {
				organic = new organicVO();
				organic.setP_stock(rs.getInt("p_stock"));

			}

			JDBCUtil.close(rs, stmt, conn);

			if ((organic.getP_stock()) < vo.getP_amount()) {

				System.out.println("재고 : " + organic.getP_stock());
				System.out.println("사는 수량 : " + vo.getP_amount());

				result = "NO";

			} else {

				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BASKET_INPUT);

				stmt.setString(1, loginId);
				stmt.setString(2, vo.getP_number());
				stmt.setString(3, vo.getP_name());
				stmt.setInt(4, vo.getP_price());
				stmt.setInt(5, vo.getP_amount());
				stmt.setString(6, vo.getP_photourl());
				stmt.setString(7, vo.getP_category());

				stmt.executeUpdate();

				JDBCUtil.close(stmt, conn);

				result = "YES";

			}

		}

		return result;
	}

	public boolean updateMember(MemberVO vo) throws Exception {
		System.out.println("회원정보 수정완료");

		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(MODIFY_UPDATE);
		stmt.setString(1, vo.getPw());
		stmt.setString(2, vo.getTel1());
		stmt.setString(3, vo.getTel2());
		stmt.setString(4, vo.getTel3());
		stmt.setString(5, vo.getEmail());
		stmt.setString(6, vo.getAddress1());
		stmt.setString(7, vo.getAddress2());
		stmt.setString(8, vo.getAddress3());
		stmt.setString(9, vo.getId());
		// "update rddsusermember set password=?, tel1=?, tel2=?, tel3=?, email=?,
		// address1=?, address2=?, address3=? where id=?"
		stmt.executeUpdate();

		System.out.println("완료각?");

		JDBCUtil.close(stmt, conn);

		return true;

	}

	public MemberVO clickorder(MemberVO membervo) throws SQLException {

		String clickorder = "select * from rddusermember where id=?";

		MemberVO covo = new MemberVO();

		conn = JDBCUtil.getConnection();

		try {
			stmt = conn.prepareStatement(clickorder);
			stmt.setString(1, membervo.getId());
			rs = stmt.executeQuery();

			while (rs.next()) {

				covo.setName(rs.getString("name"));
				covo.setTel1(rs.getString("tel1"));

				covo.setTel2(rs.getString("tel2"));
				covo.setTel3(rs.getString("tel3"));
				covo.setEmail(rs.getString("email"));
				covo.setAddress1(rs.getString("address1"));
				covo.setAddress2(rs.getString("address2"));
				covo.setAddress3(rs.getString("address3"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		JDBCUtil.close(rs, stmt, conn);

		return covo;
	}

	public List<BasketVO> shoppingbasket(String loginId) throws Exception {

		String BASKET_LIST = "select * from rddbasket where id=?";

		List<BasketVO> basketList = new ArrayList<BasketVO>();

		BasketVO vo = new BasketVO();

		conn = JDBCUtil.getConnection();

		try {
			stmt = conn.prepareStatement(BASKET_LIST);
			stmt.setString(1, loginId);

			rs = stmt.executeQuery();

			while (rs.next()) {
				vo = new BasketVO();
				vo.setBasketnum(rs.getInt("basketnum"));
				vo.setId(rs.getString("id"));
				vo.setP_number(rs.getString("p_number"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_price(rs.getInt("p_price"));
				vo.setP_amount(rs.getInt("p_amount"));
				vo.setP_photourl(rs.getString("p_photourl"));
				vo.setP_category(rs.getString("p_category"));

				basketList.add(vo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JDBCUtil.close(rs, stmt, conn);

		return basketList;

	}

	public List<organicVO> searchList(organicVO vo, PageMaker page) throws Exception {

		String food_search = "select * from rddfood where p_name like '%" + vo.getSearch() + "%' or p_explain like '%"
				+ vo.getSearch() + "%' order by p_number desc limit ?,?";
		String beauty_search = "select * from rddbeauty where p_name like '%" + vo.getSearch()
				+ "%' or p_explain like '%" + vo.getSearch() + "%' order by p_number desc limit ?,?";
		String clothes_search = "select * from rddclothes where p_name like '%" + vo.getSearch()
				+ "%' or p_explain like '%" + vo.getSearch() + "%' order by p_number desc limit ?,?";
		String daily_search = "select * from rdddaily where p_name like '%" + vo.getSearch() + "%' or p_explain like '%"
				+ vo.getSearch() + "%' order by p_number desc limit ?,?";

		List<organicVO> searchList = new ArrayList<organicVO>();
		organicVO organic = new organicVO();

		int pagenumbers = page.getPagenum() * 20;
		System.out.println("페이지 넘버스 : " + pagenumbers);

		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(food_search);
		stmt.setInt(1, pagenumbers);
		stmt.setInt(2, page.getContentnum());

		rs = stmt.executeQuery();

		while (rs.next()) {
			organic = new organicVO();
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_stock(rs.getInt("p_stock"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organic.setSearch(vo.getSearch());
			searchList.add(organic);

			System.out.println(rs.getString("p_category"));
			System.out.println("vo카테고리 : " + organic.getP_category());
			System.out.println("푸드 검색값");
		}

		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(beauty_search);
		stmt.setInt(1, pagenumbers);
		stmt.setInt(2, page.getContentnum());

		rs = stmt.executeQuery();

		while (rs.next()) {
			organic = new organicVO();
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_stock(rs.getInt("p_stock"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organic.setSearch(vo.getSearch());

			searchList.add(organic);

			System.out.println(rs.getString("p_category"));
			System.out.println("vo카테고리 : " + organic.getP_category());
			System.out.println("뷰티 검색값");
		}

		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(clothes_search);
		stmt.setInt(1, pagenumbers);
		stmt.setInt(2, page.getContentnum());

		rs = stmt.executeQuery();

		while (rs.next()) {
			organic = new organicVO();
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_stock(rs.getInt("p_stock"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organic.setSearch(vo.getSearch());

			searchList.add(organic);

			System.out.println(rs.getString("p_category"));
			System.out.println("vo카테고리 : " + organic.getP_category());
			System.out.println("가공식품 검색값");
		}

		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(daily_search);
		stmt.setInt(1, pagenumbers);
		stmt.setInt(2, page.getContentnum());

		rs = stmt.executeQuery();

		while (rs.next()) {
			organic = new organicVO();
			organic.setP_number(rs.getString("p_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_stock(rs.getInt("p_stock"));
			organic.setP_explain(rs.getString("p_explain"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));
			organic.setSearch(vo.getSearch());
			searchList.add(organic);

			System.out.println(rs.getString("p_category"));
			System.out.println("vo카테고리 : " + organic.getP_category());
			System.out.println("패션 검색값");
		}

		JDBCUtil.close(rs, stmt, conn);

		return searchList;
	}

	public String insertordercomplete(OrderVO ordervo, String loginID) throws Exception {

		// 테스트로 주문번호 생성부분 시작
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		Date time = new Date();

		String orderNumber = format.format(time);

		Random random = new Random();

		orderNumber += random.nextInt(100);

		System.out.println("랜덤주문번호 테스트 : " + orderNumber);

		// 테스트로 주문번호 생성부분 끝

		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(ORDERCOMPLETE_INSERT);

		// 주문번호 디비에 넣는 부분 시작
		stmt.setString(1, orderNumber);
		// 주문번호 디비에 넣는 부분 끝
		stmt.setString(2, loginID);
		stmt.setString(3, ordervo.getP_number());
		stmt.setString(4, ordervo.getP_name());
		stmt.setInt(5, ordervo.getP_price());
		stmt.setInt(6, ordervo.getP_amount());
		stmt.setString(7, ordervo.getP_photourl());
		stmt.setString(8, ordervo.getName());
		stmt.setString(9, ordervo.getAddress1());
		stmt.setString(10, ordervo.getAddress2());
		stmt.setString(11, ordervo.getAddress3());
		stmt.setString(12, ordervo.getTel1());
		stmt.setString(13, ordervo.getTel2());
		stmt.setString(14, ordervo.getTel3());
		stmt.setString(15, ordervo.getRequest());
		stmt.setInt(16, ordervo.getAllpay_price());
		stmt.setString(17, ordervo.getPaymethod());
		stmt.setString(18, ordervo.getP_category());
		stmt.setString(19, ordervo.getP_delivery());
		stmt.setString(20, ordervo.getDroneaddress());

		stmt.executeUpdate();

		JDBCUtil.close(stmt, conn);

		return orderNumber;
	}

	public BasketVO deleteBasket(BasketVO basketvo, String[] basketNum) throws Exception {

		for (int a = 0; a < basketNum.length; a++) {
			System.out.println("DAO넘어온 값 : " + basketNum[a]);
			conn = JDBCUtil.getConnection();

			stmt = conn.prepareStatement(DELETE_BASKET);
			stmt.setString(1, basketNum[a]);

			stmt.executeUpdate();

			JDBCUtil.close(stmt, conn);

		}
		return null;
	}

	public List<OrderVO> allorderlist(String id) throws Exception {

		// 주문내역DB에서 데이터를 꺼내와서 OrderVO객체에 담고 그 객체를 담을 ArrayList객체 생성
		List<OrderVO> orderListVO = new ArrayList<OrderVO>();
		OrderVO orderVO;
		// 주문내역DB에서 로그인 아이디에 해당하는 주문내역을 뽑아오는 과정
		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(ORDER_LIST);
		stmt.setString(1, id);
		rs = stmt.executeQuery();

		while (rs.next()) {
			orderVO = new OrderVO();

			orderVO.setP_number(rs.getString("p_number"));
			orderVO.setP_name(rs.getString("p_name"));
			orderVO.setP_price(rs.getInt("p_price"));
			orderVO.setP_amount(rs.getInt("p_amount"));
			orderVO.setP_photourl(rs.getString("p_photourl"));
			orderVO.setToday(rs.getDate("today"));
			orderVO.setName(rs.getString("name"));
			orderVO.setAddress1(rs.getString("address1"));
			orderVO.setAddress2(rs.getString("address2"));
			orderVO.setAddress3(rs.getString("address3"));
			orderVO.setTel1(rs.getString("tel1"));
			orderVO.setTel2(rs.getString("tel2"));
			orderVO.setTel3(rs.getString("tel3"));
			orderVO.setRequest(rs.getString("request"));
			orderVO.setAllpay_price(rs.getInt("allpay_price"));
			orderVO.setPaymethod(rs.getString("pay_method"));

			String p_delivery = rs.getString("p_delivery");
			if (p_delivery.equals("delivering")) {
				orderVO.setP_delivery("배송중");
			} else {
				orderVO.setP_delivery(rs.getString("p_delivery"));
			}

			orderListVO.add(orderVO);

		}

		JDBCUtil.close(rs, stmt, conn);

		return orderListVO;

	}

	public BasketVO basketorderList(String basketnum, String id) throws Exception {

		BasketVO basketvo = new BasketVO();

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(BASKET_LIST);
		stmt.setString(1, basketnum);
		rs = stmt.executeQuery();

		while (rs.next()) {

			basketvo.setId(id);
			basketvo.setBasketnum(rs.getInt("basketnum"));
			basketvo.setP_number(rs.getString("p_number"));
			basketvo.setP_name(rs.getString("p_name"));
			basketvo.setP_price(rs.getInt("p_price"));
			basketvo.setP_amount(rs.getInt("p_amount"));
			basketvo.setP_photourl(rs.getString("p_photourl"));
			basketvo.setP_category(rs.getString("p_category"));

		}

		JDBCUtil.close(rs, stmt, conn);

		return basketvo;

	}

	public void orderDeliveryRecord(String basketnum, OrderVO ordervo) throws Exception { // 장바구니 ->배송정보입력 -> 결재하기 클릭시
																							// ordervo에 주문내역 삽입 및 장바구니에
																							// 주문내용 삭제

		String BASKETORDER_LIST = "select * from rddbasket where basketnum = ?";
		BasketVO basketvo = new BasketVO();

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(BASKETORDER_LIST);
		stmt.setString(1, basketnum);
		rs = stmt.executeQuery();

		while (rs.next()) {

			basketvo.setP_number(rs.getString("p_number"));
			basketvo.setP_name(rs.getString("p_name"));
			basketvo.setP_price(rs.getInt("p_price"));
			basketvo.setP_amount(rs.getInt("p_amount"));
			basketvo.setP_photourl(rs.getString("p_photourl"));
			basketvo.setP_category(rs.getString("p_category"));
		}

		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(ORDERCOMPLETE_INSERT);

		stmt.setString(1, ordervo.getId());
		stmt.setString(2, basketvo.getP_number());
		stmt.setString(3, basketvo.getP_name());
		stmt.setInt(4, basketvo.getP_price());
		stmt.setInt(5, basketvo.getP_amount());
		stmt.setString(6, basketvo.getP_photourl());
		stmt.setString(7, ordervo.getName());
		stmt.setString(8, ordervo.getAddress1());
		stmt.setString(9, ordervo.getAddress2());
		stmt.setString(10, ordervo.getAddress3());
		stmt.setString(11, ordervo.getTel1());
		stmt.setString(12, ordervo.getTel2());
		stmt.setString(13, ordervo.getTel3());
		stmt.setString(14, ordervo.getRequest());
		stmt.setInt(15, ordervo.getAllpay_price());
		stmt.setString(16, ordervo.getPaymethod());
		stmt.setString(17, basketvo.getP_category());
		stmt.setString(18, ordervo.getP_delivery());
		stmt.setString(19, ordervo.getDroneaddress());

		System.out.println("number : " + basketvo.getP_number());
		System.out.println("getP_name : " + basketvo.getP_name());
		System.out.println("getP_price : " + basketvo.getP_price());
		System.out.println("getP_amount" + basketvo.getP_amount());
		System.out.println("getP_photourl : " + basketvo.getP_photourl());
		System.out.println("getName : " + ordervo.getName());
		System.out.println("getAddress1 : " + ordervo.getAddress1());
		System.out.println("tAddress2 : " + ordervo.getAddress2());
		System.out.println("Address3 : " + ordervo.getAddress3());
		System.out.println("Tel1 : " + ordervo.getTel1());
		System.out.println("Tel2 : " + ordervo.getTel2());
		System.out.println("Tel3 : " + ordervo.getTel3());
		System.out.println("Request : " + ordervo.getRequest());
		System.out.println("Allpay_price : " + ordervo.getAllpay_price());
		System.out.println("Paymethod : " + ordervo.getPaymethod());
		System.out.println("basketvo : " + ordervo.getP_category());

		stmt.executeUpdate();

		JDBCUtil.close(stmt, conn);

		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(DELETE_BASKET);
		stmt.setString(1, basketnum);

		stmt.executeUpdate();

		JDBCUtil.close(stmt, conn);

	}

	public void admindelete(organicVO organicvo) throws SQLException {

		String p_number = organicvo.getP_number();
		String p_category = organicvo.getP_category();

		String ADMIN_SELECT1 = "select * from rddfood where p_number = ? and p_category = ?";
		String ADMIN_SELECT2 = "select * from rddbeauty where p_number = ? and p_category = ?";
		String ADMIN_SELECT3 = "select * from rdddaily where p_number = ? and p_category = ?";
		String ADMIN_SELECT4 = "select * from rddclothes where p_number = ? and p_category = ?";

		String ADMIN_DELETE1 = "delete from rddfood where p_number = ? and p_category = ?";
		String ADMIN_DELETE2 = "delete from rddbeauty where p_number = ? and p_category = ?";
		String ADMIN_DELETE3 = "delete from rdddaily where p_number = ? and p_category = ?";
		String ADMIN_DELETE4 = "delete from rddclothes where p_number = ? and p_category = ?";

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(ADMIN_SELECT1);
		stmt.setString(1, p_number);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ADMIN_DELETE1);
			stmt.setString(1, p_number);
			stmt.setString(2, p_category);

			stmt.executeUpdate();

		}

		JDBCUtil.close(stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(ADMIN_SELECT2);
		stmt.setString(1, p_number);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ADMIN_DELETE2);
			stmt.setString(1, p_number);
			stmt.setString(2, p_category);

			stmt.executeUpdate();

		}

		JDBCUtil.close(stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(ADMIN_SELECT3);
		stmt.setString(1, p_number);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ADMIN_DELETE3);
			stmt.setString(1, p_number);
			stmt.setString(2, p_category);

			stmt.executeUpdate();

		}

		JDBCUtil.close(stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(ADMIN_SELECT4);
		stmt.setString(1, p_number);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ADMIN_DELETE4);
			stmt.setString(1, p_number);
			stmt.setString(2, p_category);

			stmt.executeUpdate();

		}

		JDBCUtil.close(stmt, conn);

	}

	public boolean checkNameAndPhone(MemberVO vo) throws Exception {

		String CHECK_NAMEPHONE = "select * from rddusermember where name = ? and tel1 = ? and tel2 = ? and tel3 = ?";

		String name = vo.getName();
		String tel1 = vo.getTel1();
		String tel2 = vo.getTel2();
		String tel3 = vo.getTel3();

		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(CHECK_NAMEPHONE);
		stmt.setString(1, name);
		stmt.setString(2, tel1);
		stmt.setString(3, tel2);
		stmt.setString(4, tel3);
		rs = stmt.executeQuery();

		if (rs.next()) {// 회원디비에 이름과 전화번호가 있으면

			System.out.println(rs.getString("name"));
			System.out.println(rs.getString("tel1"));
			System.out.println(rs.getString("tel2"));
			System.out.println(rs.getString("tel3"));
			JDBCUtil.close(rs, stmt, conn);

			return true;

		} else {// 회원디비에 이름과 전화번호가 없으면
			JDBCUtil.close(rs, stmt, conn);

			System.out.println("회원디비에 회원이름 전화번호 없다!!!!");

			return false;
		}

	}

	public void iteminsert(organicVO vo) throws Exception {
		if (vo.getP_tabler().equals("rddfood")) {
			String iteminsert = "insert into rddfood (p_name, p_price, p_stock, p_explain, p_photourl, p_category)values(?, ?, ?, ?, ?, ?)";

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(iteminsert);
			stmt.setString(1, vo.getP_name());
			stmt.setInt(2, vo.getP_price());
			stmt.setInt(3, vo.getP_stock());
			stmt.setString(4, vo.getP_explain());
			stmt.setString(5, vo.getP_photourl());
			stmt.setString(6, vo.getP_category());
			stmt.executeUpdate();

		} else if (vo.getP_tabler().equals("rddbeauty")) {
			String iteminsert = "insert into rddbeauty (p_name, p_price, p_stock, p_explain, p_photourl, p_category)values(?, ?, ?, ?, ?, ?)";

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(iteminsert);
			stmt.setString(1, vo.getP_name());
			stmt.setInt(2, vo.getP_price());
			stmt.setInt(3, vo.getP_stock());
			stmt.setString(4, vo.getP_explain());
			stmt.setString(5, vo.getP_photourl());
			stmt.setString(6, vo.getP_category());
			stmt.executeUpdate();

		} else if (vo.getP_tabler().equals("rdddaily")) {
			String iteminsert = "insert into rdddaily (p_name, p_price, p_stock, p_explain, p_photourl, p_category)values(?, ?, ?, ?, ?, ?)";

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(iteminsert);
			stmt.setString(1, vo.getP_name());
			stmt.setInt(2, vo.getP_price());
			stmt.setInt(3, vo.getP_stock());
			stmt.setString(4, vo.getP_explain());
			stmt.setString(5, vo.getP_photourl());
			stmt.setString(6, vo.getP_category());
			stmt.executeUpdate();

		} else if (vo.getP_tabler().equals("rddclothes")) {
			String iteminsert = "insert into rddclothes (p_name, p_price, p_stock, p_explain, p_photourl, p_category)values(?, ?, ?, ?, ?, ?)";

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(iteminsert);
			stmt.setString(1, vo.getP_name());
			stmt.setInt(2, vo.getP_price());
			stmt.setInt(3, vo.getP_stock());
			stmt.setString(4, vo.getP_explain());
			stmt.setString(5, vo.getP_photourl());
			stmt.setString(6, vo.getP_category());
			stmt.executeUpdate();

		}

		System.out.println("DAO 시작");
		System.out.println(vo.getP_tabler());
		System.out.println(vo.getP_category());
		System.out.println(vo.getP_name());
		System.out.println(vo.getP_stock());
		System.out.println(vo.getP_price());
		System.out.println(vo.getP_explain());
		System.out.println(vo.getP_photourl());
		System.out.println("DAO 끝");

		JDBCUtil.close(stmt, conn);
	}

	public organicVO itemmodify(organicVO vo) throws Exception {

		String rddfood = "select * from rddfood where p_number = ? and p_category = ?";
		String rddbeauty = "select * from rddbeauty where p_number = ? and p_category = ?";
		String rddclothes = "select * from rddclothes where p_number = ? and p_category = ?";
		String rdddaily = "select * from rdddaily where p_number = ? and p_category = ?";

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(rddfood);
		stmt.setString(1, vo.getP_number());
		stmt.setString(2, vo.getP_category());
		rs = stmt.executeQuery();

		while (rs.next()) {
			String tabler = "rddfood";

			vo.setP_name(rs.getString("p_name"));
			vo.setP_number(rs.getString("p_number"));
			vo.setP_price(rs.getInt("p_price"));
			vo.setP_stock(rs.getInt("P_STOCK"));
			vo.setP_tabler(tabler);
			vo.setP_category(rs.getString("p_category"));
			vo.setP_photourl(rs.getString("p_photourl"));
			vo.setP_explain(rs.getString("p_explain"));

			System.out.println("DAO URL" + vo.getP_photourl());
			System.out.println("DOA stock : " + vo.getP_stock());
			System.out.println("DAO 설명 : " + vo.getP_explain());

		}

		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(rddbeauty);
		stmt.setString(1, vo.getP_number());
		stmt.setString(2, vo.getP_category());
		rs = stmt.executeQuery();

		while (rs.next()) {
			String tabler = "rddbeauty";

			vo.setP_name(rs.getString("p_name"));
			vo.setP_number(rs.getString("p_number"));
			vo.setP_price(rs.getInt("p_price"));
			vo.setP_stock(rs.getInt("p_stock"));
			vo.setP_tabler(tabler);
			vo.setP_category(rs.getString("p_category"));
			vo.setP_photourl(rs.getString("p_photourl"));
			vo.setP_explain(rs.getString("p_explain"));

		}

		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(rdddaily);
		stmt.setString(1, vo.getP_number());
		stmt.setString(2, vo.getP_category());
		rs = stmt.executeQuery();

		while (rs.next()) {
			String tabler = "rdddaily";

			vo.setP_name(rs.getString("p_name"));
			vo.setP_number(rs.getString("p_number"));
			vo.setP_price(rs.getInt("p_price"));
			vo.setP_stock(rs.getInt("p_stock"));
			vo.setP_tabler(tabler);
			vo.setP_category(rs.getString("p_category"));
			vo.setP_photourl(rs.getString("p_photourl"));
			vo.setP_explain(rs.getString("p_explain"));

		}

		JDBCUtil.close(rs, stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(rddclothes);
		stmt.setString(1, vo.getP_number());
		stmt.setString(2, vo.getP_category());
		rs = stmt.executeQuery();

		while (rs.next()) {
			String tabler = "rddclothes";

			vo.setP_name(rs.getString("p_name"));
			vo.setP_number(rs.getString("p_number"));
			vo.setP_price(rs.getInt("p_price"));
			vo.setP_stock(rs.getInt("p_stock"));
			vo.setP_tabler(tabler);
			vo.setP_category(rs.getString("p_category"));
			vo.setP_photourl(rs.getString("p_photourl"));
			vo.setP_explain(rs.getString("p_explain"));

		}

		JDBCUtil.close(rs, stmt, conn);

		return vo;
	}

	public void itemupdata(organicVO vo) throws Exception {

		if (vo.getP_tabler().equals("rddfood")) {
			String rddfood = "UPDATE rddfood SET p_name = ?, p_price = ?, p_stock = ?, p_explain = ? , p_photourl =?, p_category = ? WHERE p_number = ?";

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(rddfood);
			stmt.setString(1, vo.getP_name());
			stmt.setInt(2, vo.getP_price());
			stmt.setInt(3, vo.getP_stock());
			stmt.setString(4, vo.getP_explain());
			stmt.setString(5, vo.getP_photourl());
			stmt.setString(6, vo.getP_category());
			stmt.setString(7, vo.getP_number());
			stmt.executeUpdate();

		} else if (vo.getP_tabler().equals("rddbeauty")) {
			String rddbeauty = "UPDATE rddbeauty SET p_name = ?, p_price = ?, p_stock = ?, p_explain = ? , p_photourl =?, p_category = ? WHERE p_number = ?";

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(rddbeauty);
			stmt.setString(1, vo.getP_name());
			stmt.setInt(2, vo.getP_price());
			stmt.setInt(3, vo.getP_stock());
			stmt.setString(4, vo.getP_explain());
			stmt.setString(5, vo.getP_photourl());
			stmt.setString(6, vo.getP_category());
			stmt.setString(7, vo.getP_number());
			stmt.executeUpdate();

		} else if (vo.getP_tabler().equals("rddclothes")) {
			String rddclothes = "UPDATE rddclothes SET p_name = ?, p_price = ?, p_stock = ?, p_explain = ? , p_photourl =?, p_category = ? WHERE p_number = ?";

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(rddclothes);
			stmt.setString(1, vo.getP_name());
			stmt.setInt(2, vo.getP_price());
			stmt.setInt(3, vo.getP_stock());
			stmt.setString(4, vo.getP_explain());
			stmt.setString(5, vo.getP_photourl());
			stmt.setString(6, vo.getP_category());
			stmt.setString(7, vo.getP_number());
			stmt.executeUpdate();

		} else if (vo.getP_tabler().equals("rdddaily")) {
			String rdddaily = "UPDATE rdddaily SET p_name = ?, p_price = ?, p_stock = ?, p_explain = ? , p_photourl =?, p_category = ? WHERE p_number = ?";

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(rdddaily);
			stmt.setString(1, vo.getP_name());
			stmt.setInt(2, vo.getP_price());
			stmt.setInt(3, vo.getP_stock());
			stmt.setString(4, vo.getP_explain());
			stmt.setString(5, vo.getP_photourl());
			stmt.setString(6, vo.getP_category());
			stmt.setString(7, vo.getP_number());
			stmt.executeUpdate();

		}

		JDBCUtil.close(rs, stmt, conn);
	}

	public void contorolStock(OrderVO ordervo, organicVO organicvo) throws SQLException { // 판매량에 따라 재고변경
		String SEARCH_NAME_CATEGORY1 = "select * from rddfood where p_name = ? and p_category = ?";
		String SEARCH_NAME_CATEGORY2 = "select * from rddbeauty where p_name = ? and p_category = ?";
		String SEARCH_NAME_CATEGORY3 = "select * from rdddaily where p_name = ? and p_category = ?";
		String SEARCH_NAME_CATEGORY4 = "select * from rddclothes where p_name = ? and p_category = ?";

		String UPDATE_STOCK = "update rddfood set p_stock = ? where p_name = ? and p_category = ?";
		String UPDATE_STOCK2 = "update rddbeauty set p_stock = ? where p_name = ? and p_category = ?";
		String UPDATE_STOCK3 = "update rdddaily set p_stock = ? where p_name = ? and p_category = ?";
		String UPDATE_STOCK4 = "update rddclothes set p_stock = ? where p_name = ? and p_category = ?";

		String p_name = ordervo.getP_name();
		String p_category = ordervo.getP_category();
		int p_stock = organicvo.getP_stock();
		int p_amount = ordervo.getP_amount();
		int Newp_stock = p_stock - p_amount;

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(SEARCH_NAME_CATEGORY1);
		stmt.setString(1, p_name);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_STOCK);
			stmt.setInt(1, Newp_stock);
			stmt.setString(2, p_name);
			stmt.setString(3, p_category);

			stmt.executeUpdate();

		}

		JDBCUtil.close(stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(SEARCH_NAME_CATEGORY2);
		stmt.setString(1, p_name);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_STOCK2);
			stmt.setInt(1, Newp_stock);
			stmt.setString(2, p_name);
			stmt.setString(3, p_category);

			stmt.executeUpdate();

		}

		JDBCUtil.close(stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(SEARCH_NAME_CATEGORY3);
		stmt.setString(1, p_name);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_STOCK3);
			stmt.setInt(1, Newp_stock);
			stmt.setString(2, p_name);
			stmt.setString(3, p_category);

			stmt.executeUpdate();

		}
		JDBCUtil.close(stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(SEARCH_NAME_CATEGORY4);
		stmt.setString(1, p_name);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {
			JDBCUtil.close(rs, stmt, conn);

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_STOCK4);
			stmt.setInt(1, Newp_stock);
			stmt.setString(2, p_name);
			stmt.setString(3, p_category);

			stmt.executeUpdate();

		}
		JDBCUtil.close(stmt, conn);

	}

	public void controlbasketstock(int p_amount, String p_name, String p_category) throws SQLException {

		organicVO organicvo = new organicVO();

		int p_stock = 0;
		int Newp_stock = 0;

		String SEARCH_NAME_CATEGORY1 = "select * from rddfood where p_name = ? and p_category = ?";
		String SEARCH_NAME_CATEGORY2 = "select * from rddbeauty where p_name = ? and p_category = ?";
		String SEARCH_NAME_CATEGORY3 = "select * from rdddaily where p_name = ? and p_category = ?";
		String SEARCH_NAME_CATEGORY4 = "select * from rddclothes where p_name = ? and p_category = ?";

		String UPDATE_STOCK = "update rddfood set p_stock = ? where p_name = ? and p_category = ?";
		String UPDATE_STOCK2 = "update rddbeauty set p_stock = ? where p_name = ? and p_category = ?";
		String UPDATE_STOCK3 = "update rdddaily set p_stock = ? where p_name = ? and p_category = ?";
		String UPDATE_STOCK4 = "update rddclothes set p_stock = ? where p_name = ? and p_category = ?";

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(SEARCH_NAME_CATEGORY1);
		stmt.setString(1, p_name);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {

			p_stock = (rs.getInt("p_stock"));
			Newp_stock = p_stock - p_amount;

			JDBCUtil.close(rs, stmt, conn);
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_STOCK);
			stmt.setInt(1, Newp_stock);
			stmt.setString(2, p_name);
			stmt.setString(3, p_category);

			stmt.executeUpdate();

		}

		JDBCUtil.close(stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(SEARCH_NAME_CATEGORY2);
		stmt.setString(1, p_name);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {

			p_stock = (rs.getInt("p_stock"));
			Newp_stock = p_stock - p_amount;

			JDBCUtil.close(rs, stmt, conn);
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_STOCK2);
			stmt.setInt(1, Newp_stock);
			stmt.setString(2, p_name);
			stmt.setString(3, p_category);

			stmt.executeUpdate();

		}

		JDBCUtil.close(stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(SEARCH_NAME_CATEGORY3);
		stmt.setString(1, p_name);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {

			p_stock = (rs.getInt("p_stock"));
			Newp_stock = p_stock - p_amount;

			JDBCUtil.close(rs, stmt, conn);
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_STOCK3);
			stmt.setInt(1, Newp_stock);
			stmt.setString(2, p_name);
			stmt.setString(3, p_category);

			stmt.executeUpdate();

		}

		JDBCUtil.close(stmt, conn);

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(SEARCH_NAME_CATEGORY4);
		stmt.setString(1, p_name);
		stmt.setString(2, p_category);
		rs = stmt.executeQuery();

		if (rs.next()) {

			p_stock = (rs.getInt("p_stock"));
			Newp_stock = p_stock - p_amount;

			JDBCUtil.close(rs, stmt, conn);
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_STOCK4);
			stmt.setInt(1, Newp_stock);
			stmt.setString(2, p_name);
			stmt.setString(3, p_category);

			stmt.executeUpdate();

		}

		JDBCUtil.close(stmt, conn);

	}

	// ------------------------------------------식품 인기상품
	// 시작----------------------------------------------
	public organicVO organic_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'organic' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

			System.out.println(vo.getP_category());

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	public organicVO processedfood_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'processed' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	public organicVO junkfood_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'instance' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	public organicVO dringkcafe_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'dringkcafe' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	// -----------------------------------------------식품 인기상품
	// 끝-----------------------------------

	// -----------------------------------------------의류 인기상품
	// 시작-----------------------------------------

	public organicVO girlclothes_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'girlclothes' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	public organicVO boyclothes_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'boyclothes' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	public organicVO babyclothes_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'babyclothes' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	// -----------------------------------의류 끝-------------------------------

	// ------------------------------------생활용품 인기상품 시작---------------------------
	public organicVO kitch_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'kitchen' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	public organicVO bathroom_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'bathroom' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	public organicVO storage_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'storage' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	public organicVO cleaning_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'cleaning' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	// ----------------------------------------------------생활용품 인기상품
	// 끝---------------------------------------------

	// ----------------------------------------------------미용 인기상품
	// 시작-------------------------------------------------

	public organicVO cosmetic_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'realbeauty' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	public organicVO perfume_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'perfume' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	public organicVO beauty_item(organicVO vo) throws Exception {

		String category = "select populationproduct.* from (SELECT p_number, p_name, p_price, sum(P_amount) as amount, p_photourl, p_category FROM rdds.rddorder group by p_number, p_name, p_price, p_photourl, p_category having P_CATEGORY = 'tool' ORDER BY sum(P_AMOUNT) desc, P_number desc) as populationproduct limit 1";

		organicVO organic = new organicVO();
		conn = JDBCUtil.getConnection();

		stmt = conn.prepareStatement(category);
		rs = stmt.executeQuery();

		while (rs.next()) {
			organic.setP_number(rs.getString("P_number"));
			organic.setP_name(rs.getString("p_name"));
			organic.setP_price(rs.getInt("p_price"));
			organic.setP_photourl(rs.getString("p_photourl"));
			organic.setP_category(rs.getString("p_category"));

			System.out.println(rs.getString("P_number"));
			System.out.println(rs.getString("p_name"));

			System.out.println(rs.getInt("p_price"));

			System.out.println(rs.getString("p_photourl"));
			System.out.println(vo.getP_category());

		}

		JDBCUtil.close(rs, stmt, conn);
		return organic;

	}

	// ----------------------------------------미용 인기상품
	// 끝---------------------------------------------------------------
}
