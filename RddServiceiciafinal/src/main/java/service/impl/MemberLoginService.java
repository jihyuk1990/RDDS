package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import service.MemberVO;
import service.common.JDBCUtil;

public class MemberLoginService {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public boolean login(MemberVO vo) throws Exception {
		MemberDAOJDBC memberDAO = new MemberDAOJDBC();
		boolean loginResult = false;

		String loginID = memberDAO.loginOk(vo);

		if (loginID != null) {
			loginResult = true;
			System.out.println(loginResult);

		}

		return loginResult;

	}

	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	
	public boolean idCheck(MemberVO vo) throws Exception {
		 MemberDAOJDBC memberDAO = new MemberDAOJDBC();
		 boolean idcheckResult = false ;
		String checkID = memberDAO.checkID(vo);
		
		if (checkID != null) {
			idcheckResult = true;
			System.out.println(idcheckResult);

		}
		 
		 System.out.println(idcheckResult);
		 return idcheckResult; 
	}

}
