package com.subakcine.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionProvider {
	//db 연결 메서드
	public static Connection getConnection() {
		Connection conn = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "c##subak";
		String password = "subak";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			
		}catch(Exception e) {
			System.out.println("예외발생"+e);
		}
		return conn;
	}
	//close메서드(매개변수3개)
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}catch(Exception e) {
			System.out.println("connection 예외발생:"+e);
		}
	}
	//close메서드(매개변수2개)
	public static void close(Statement stmt, Connection conn) {
		try {
			if(stmt!=null) {
				stmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}catch(Exception e) {
			System.out.println("connection 예외발생:"+e);
		}
	}
}
