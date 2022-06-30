package controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connector.DBConnector;
import model.UserDTO;

public class UserController {
    private Connection conn;

    public UserController(DBConnector connector) {
        conn = connector.makeConnection();
    }
    
    // 1. 로그인
    
    public UserDTO logIn(String userName, String passWord) {
       String query = "SELECT *FROM `user` WHERE `username` = ? AND `password` = ? AND `class` = ?";
       
       try {
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, userName);
        pstmt.setString(2, passWord);
        
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            UserDTO u = new UserDTO();
            u.setId(rs.getInt("id"));
            u.setUserName(rs.getString("userName"));
            u.setPassWord(rs.getString("passWord"));
            
            return u;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
       return null;
    }
    
    //2. 회원가입
    public boolean register(UserDTO u) {
        String query = "INSERT INTO `user` (`userName`, `passWord`, `userclass`) VALUES (?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, u.getUserName());
            pstmt.setString(2, u.getPassWord());
            pstmt.setInt(3, u.getUserClass());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //3.회원 정보 수정
    public void update(UserDTO u) {
        String query = "UPDATE `user` SET `passWord` =? WHERE `id` = ?";
        try {
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, u.getPassWord());
            pstmt.setInt(2, u.getId());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
