package service.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {   // 오라클연결 부분 

	public static Connection getConnection() {
		//어떤 디비에 어떤 계정으로 연결할지 프로그램에 알려줌.
		//이 메소드를 통해서 프로그램상에서 디비 연결.
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			return DriverManager.getConnection("jdbc:mysql://192.168.1.137:3306/rdds?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERTTONULL&serverTimezone=GMT","mysql_admin","mysql_admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(Statement stmt, Connection conn) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}

		try {
			if (!conn.isClosed() || conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		//쿼리문 조회결과를 저장하는 resultSet 객체 자원 반납,
		//나머지는 위와 같이 stmt와 conn 반남
		
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
		}

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}

		try {
			if (!conn.isClosed() || conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}

	}

}




