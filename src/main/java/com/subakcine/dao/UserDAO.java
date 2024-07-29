package com.subakcine.dao;

import com.subakcine.db.ConnectionProvider;
import com.subakcine.vo.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    //회원가입
    //성공하면 1, 실패는 0,-1
    public int insert(UserVO vo) {
        int re = -1;
        String sql = "INSERT INTO USERS (USERS_EMAIL, USERS_PASSWORD) VALUES (?,?)";
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getEmail());
            pstmt.setString(2, vo.getPassword());
            re = pstmt.executeUpdate();
            ConnectionProvider.close(pstmt, conn);
        } catch (Exception e) {
            System.out.println("dao insert exception ==> " + e.getMessage());
        }
        return re;
    }

    // 사용자 USERS_ID를 이메일로 가져오는 메소드
    public String getUsersID(String email) {
        String usersID = null;
        String sql = "SELECT USERS_ID FROM USERS WHERE USERS_EMAIL=?";
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                usersID = rs.getString("USERS_ID");
            }
            ConnectionProvider.close(rs, pstmt, conn);
        } catch (Exception e) {
            System.out.println("dao getUserID exception ==> " + e.getMessage());
        }
        return usersID;
    }

    //이메일로 user정보 가져오기
    public UserVO getUserByEmail(String email) {
        UserVO vo = new UserVO();
        String sql = "SELECT * FROM USERS WHERE USERS_EMAIL=?";
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                vo.setUserId(rs.getString("USERS_ID"));
                vo.setEmail(rs.getString("USERS_EMAIL"));
                vo.setPassword(rs.getString("USERS_PASSWORD"));
                vo.setCreateDate(rs.getDate("CREATE_DATE"));
            }
        } catch (Exception e) {
            System.out.println("UserDAO getUserByEmail exception ==> " + e.getMessage());
        }
        return vo;
    }

    //로그인시 아이디와 암호를 매개변수로 전달받아 회원의 정보가 올바른지 판별하는 메소드
    //아이디도 맞고 암호도 맞으면 1, 아이디는 있는데 암호가 틀리면 0, 아이디도 없으면 -1
    public int isUserExist(String email, String password) {
        int re = -1;
        String sql = "SELECT * FROM USERS WHERE USERS_EMAIL=?";
        Connection conn = ConnectionProvider.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("USERS_PASSWORD").equals(password)) {
                    re = 1;
                } else {
                    re = 0;
                }
            }
            ConnectionProvider.close(pstmt, conn);
        } catch (Exception e) {
            System.out.println("dao isUserExist exception ==> " + e.getMessage());
        }
        return re;
    }

    //email을 사용할 수 있는지 판별하는 메소드
    //사용가능:1, 이미 존재하는 이메일:0
    public int emailExist(String email){
        int re=1;
        try{
            String sql = "SELECT * FROM USERS WHERE USERS_EMAIL=?";
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                re = 0;
            }
            ConnectionProvider.close(pstmt, conn);
        } catch (Exception e) {
            System.out.println("dao emailExist exception ==> " + e.getMessage());
        }
        return re;
    }

    public int update(UserVO vo) {
        int re = -1;
        String sql = "UPDATE USERS SET USERS_PASSWORD=? WHERE USERS_EMAIL=?";
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getPassword());
            pstmt.setString(2, vo.getEmail());
            re = pstmt.executeUpdate();
            ConnectionProvider.close(pstmt, conn);
        } catch (Exception e) {
            System.out.println("dao insert exception ==> " + e.getMessage());
        }
        return re;
    }
    public int delete(String email) {
        int re = -1;
        String sql = "DELETE USERS WHERE USERS_EMAIL=?";
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            re = pstmt.executeUpdate();
            ConnectionProvider.close(pstmt, conn);
        } catch (Exception e) {
            System.out.println("dao insert exception ==> " + e.getMessage());
        }
        return re;
    }
}
