package controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connector.DBConnector;
import model.MajorDTO;
import model.UserDTO;

public class MajorController {
    private Connection conn;
    private UserDTO logIn;
    
    public MajorController(DBConnector connector) {
        conn = connector.makeConnection();
    }
    
    
    // 1 강의 등록
    public boolean insert(MajorDTO m) {
        String query = "INSERT INTO `major` (`majorname`, `professor`) VALUES (?, ?)";
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, m.getMajorName());
            pstmt.setString(2, m.getProfessor());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //2. 강의 수정
    public void update(MajorDTO m) {
        String query = "UPDATE `major` SET `majorname` WHERE  `id` =?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, m.getMajorName());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //3.강의 삭제
    public void delete(int id) {
        String query ="DELETE FROM `major` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        
    }
    
    // 4. 강의 목록 조회
    public ArrayList<MajorDTO> selectAll(){
        ArrayList<MajorDTO> list = new ArrayList<>();
        String query = "SELECT *FROM `major` ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                MajorDTO m = new MajorDTO();
                m.setId(rs.getInt("id"));
                m.setMajorName(rs.getString("majorName"));
                m.setProfessor(rs.getString("professor"));
                
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //5. 강의 개별 조회 안되면 id ` ` < 
    public MajorDTO selectOne(int id) {
        MajorDTO m = null;
        String query = "SELCET *FROM `board` WHERE `id` =?";
        try {
            PreparedStatement pstmt = conn.prepareCall(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                m = new MajorDTO();
                m.setId(rs.getInt("id"));
                m.setMajorName(rs.getString("majorName"));
                m.setProfessor(rs.getString("professor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
        
    }
    
    
    
}
