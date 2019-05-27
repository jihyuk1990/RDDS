package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import service.MemberVO;

public class SearchPwService {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public MemberVO searchPw(MemberVO vo) throws Exception {
		MemberDAOJDBC memberDAO = new MemberDAOJDBC();
		
		MemberVO searchVo=new MemberVO();

		searchVo = memberDAO.serchEmailOk(vo);
	

		
		return searchVo;

	}

}
