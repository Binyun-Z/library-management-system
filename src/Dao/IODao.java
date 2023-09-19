package Dao;

import Entity.Log;
import Util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class IODao extends DBConnect{
    public void borrow(String bookid, String readerid, String time, int borrowday){
        try {
            Connection conn = super.getConnection();
            PreparedStatement pst = null;
            String sql = "INSERT INTO iolog (bookid, readerid, borrowtime, borrowday, complete)VALUES(?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, bookid);
            pst.setString(2, readerid);
            pst.setString(3, time);
            pst.setInt(4, borrowday);
            pst.setInt(5, 0);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Log> getLogList(){
        try {
            ArrayList<Log> Loglist = new ArrayList<>();
            BookDao bdao = new BookDao();
            Connection conn = super.getConnection();
            String sql = "SELECT * FROM iolog";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            String service = null;
            String bookname = null;
            String complete = null;
            while (rs.next()) {
                Log log = new Log();
                bookname = bdao.QueryBookById(rs.getString("bookid")).getName();
                log.setBookid(rs.getString("bookid") + "  (" + bookname + ")");
                log.setReaderid(rs.getString("readerid"));
                log.setBorrowtime(rs.getString("borrowtime"));
                log.setBorrowday(rs.getInt("borrowday"));
                complete = (rs.getInt("complete") == 0) ? "未归还" : "已归还";
                log.setComplete(complete);
                Loglist.add(log);
            }
            return Loglist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int QueryBookNumById(String id){
        int num = 0;
        Connection conn = null;
        try {
            conn = super.getConnection();
            String sql = "SELECT * FROM iolog WHERE bookid=? AND complete=? ORDER BY borrowtime";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setInt(2, 0);
            rs = pst.executeQuery();
            rs.last();
            num += rs.getRow();
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  num;
    }
    
    public ArrayList<Log> QueryBorrowNumByReaderid(String id){
        int num = 0;
        Connection conn = null;
        ArrayList<Log> loglist = new ArrayList<>();
        String service = null;
        String complete = null;
        try {
            conn = super.getConnection();
            String sql = "SELECT * FROM iolog WHERE readerid=? AND complete=?";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setInt(2, 0);
            rs = pst.executeQuery();
            Log log = null;
            while(rs.next()){
                log = new Log();
                log.setReaderid(rs.getString("readerid"));
                log.setBookid(rs.getString("bookid"));
                log.setBorrowtime(rs.getString("borrowtime"));
                complete = (rs.getInt("complete") == 0) ? "未归还" : "已归还";
                log.setComplete(complete);
                log.setBorrowday(rs.getInt("borrowday"));
                loglist.add(log);
            }
            return loglist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
    public void ReturnBook(String bookid, String readerid, String borrowtime, String returntime){
        int i = 0;
        try {
            Connection conn = super.getConnection();
            PreparedStatement pst = null;
            String sql = "UPDATE iolog SET complete=1 WHERE bookid=? AND readerid=? AND borrowtime=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, bookid);
            pst.setString(2, readerid);
            pst.setString(3, borrowtime);
            i = pst.executeUpdate();
            sql = "INSERT INTO iolog (bookid, readerid, borrowtime, borrowday, complete)VALUES(?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, bookid);
            pst.setString(2, readerid);
            pst.setString(3, returntime);
            pst.setInt(4, 0);
            pst.setInt(5, 1);
            pst.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
