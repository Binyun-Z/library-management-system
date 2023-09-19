package Dao;

import Entity.Book;
import Util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class BookDao extends DBConnect {
	
    public ArrayList<Book> getAllBook(){
        ArrayList<Book> booklist = new ArrayList<>();
        IODao ioDao = new IODao();
        try {
            Connection conn = super.getConnection();
            String sql = "SELECT * FROM book limit 0,1000";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("id"));
                book.setName(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setStore(rs.getInt("store"));
                book.setLend(ioDao.QueryBookNumById(book.getId()));
                booklist.add(book);
            }
            return booklist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booklist;
    }

    public void addtemp(Book book){
        try {
            int i = 0;
            Connection conn = super.getConnection();
            PreparedStatement pst = null;
            String sql = "insert into tempadd (id, bookname, author, publisher, store, bookdesc)values(?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, book.getId());
            pst.setString(2, book.getName());
            pst.setString(3, book.getAuthor());
            pst.setString(4, book.getPublisher());
            pst.setInt(5, book.getStore());
            pst.setString(6, book.getDesc());
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Book> getadd(){
        ArrayList<Book> booklist = new ArrayList<>();
        try {
            Connection conn = super.getConnection();
            String sql = "SELECT * FROM tempadd";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            Book addbook = null;
            while (rs.next()) {
                addbook = new Book();
                addbook.setStore(rs.getInt("store"));
                addbook.setId(rs.getString("id"));
                addbook.setName(rs.getString("bookname"));
                addbook.setAuthor(rs.getString("author"));
                addbook.setPublisher(rs.getString("publisher"));
                booklist.add(addbook);
            }
            return booklist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booklist;
    }

    public void confirm(){
        try {
            int i = 0;
            Connection conn = super.getConnection();
            PreparedStatement pst = null;
            String sql = "INSERT INTO Book SELECT * FROM tempadd;";
            pst = conn.prepareStatement(sql);
            i = pst.executeUpdate();
            sql = "truncate table tempadd;";
            pst = conn.prepareStatement(sql);
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void truncatetable(){
        int i = 0;
        Connection conn = null;
        try {
            conn = super.getConnection();        
            PreparedStatement pst = null;
            String sql = "truncate table tempadd;";
            pst = conn.prepareStatement(sql);
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public  ArrayList<Book> QueryBook(String s){
        try {
        	int flag = 1;
        	IODao ioDao = new IODao();
        	ArrayList<Book> booklist = new ArrayList<>();
            Connection conn = super.getConnection();
            
            String sql = "SELECT * FROM Book WHERE id=" + "'" + s + "'";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	Book book = new Book();
                book.setId(rs.getString("id"));
                book.setName(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setStore(rs.getInt("store"));
                book.setLend(ioDao.QueryBookNumById(book.getId()));
                book.setDesc(rs.getString("bookdesc"));
                booklist.add(book);
            }
            sql = "SELECT * FROM Book WHERE bookname=" + "'" + s + "'";
            pst = null;
            rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
             	Book book = new Book();
                book.setId(rs.getString("id"));
                book.setName(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setStore(rs.getInt("store"));
                book.setLend(ioDao.QueryBookNumById(book.getId()));
                book.setDesc(rs.getString("bookdesc"));
                booklist.add(book);
            }
            sql = "SELECT * FROM Book WHERE author=" + "'" + s + "'";
            pst = null;
            rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
             	Book book = new Book();
                book.setId(rs.getString("id"));
                book.setName(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setStore(rs.getInt("store"));
                book.setLend(ioDao.QueryBookNumById(book.getId()));
                book.setDesc(rs.getString("bookdesc"));
                booklist.add(book);
            }
            sql = "SELECT * FROM Book WHERE id like '%" + s + "%'";
            pst = null;
            rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	flag = 1;
                for(int i = 0; i < booklist.size(); i++){                   
                    Book b = booklist.get(i);
                    if(rs.getString("id").equals( b.getId())){
                    	flag = 0;
                    	break;}
                }
                if(flag == 1)
                {
	             	Book book = new Book();
	                book.setId(rs.getString("id"));
	                book.setName(rs.getString("bookname"));
	                book.setAuthor(rs.getString("author"));
	                book.setPublisher(rs.getString("publisher"));
	                book.setStore(rs.getInt("store"));
	                book.setLend(ioDao.QueryBookNumById(book.getId()));
	                book.setDesc(rs.getString("bookdesc"));
	                booklist.add(book);
                }


            }
            sql = "SELECT * FROM Book WHERE bookname like '%" + s + "%'";
            pst = null;
            rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	flag = 1;
                for(int i = 0; i < booklist.size(); i++){                   
                    Book b = booklist.get(i);
                    if(rs.getString("id").equals( b.getId())){
                    	flag = 0;
                    	break;}
                }
                if(flag == 1)
                {
	             	Book book = new Book();
	                book.setId(rs.getString("id"));
	                book.setName(rs.getString("bookname"));
	                book.setAuthor(rs.getString("author"));
	                book.setPublisher(rs.getString("publisher"));
	                book.setStore(rs.getInt("store"));
	                book.setLend(ioDao.QueryBookNumById(book.getId()));
	                book.setDesc(rs.getString("bookdesc"));
	                booklist.add(book);
                }
            }
            sql = "SELECT * FROM Book WHERE author like '%" + s + "%'";
            pst = null;
            rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
            	flag = 1;
                for(int i = 0; i < booklist.size(); i++){                   
                    Book b = booklist.get(i);
                    if(rs.getString("id").equals( b.getId())){
                    	flag = 0;
                    	break;}
                }
                if(flag == 1)
                {
	             	Book book = new Book();
	                book.setId(rs.getString("id"));
	                book.setName(rs.getString("bookname"));
	                book.setAuthor(rs.getString("author"));
	                book.setPublisher(rs.getString("publisher"));
	                book.setStore(rs.getInt("store"));
	                book.setLend(ioDao.QueryBookNumById(book.getId()));
	                book.setDesc(rs.getString("bookdesc"));
	                booklist.add(book);
                }
            }
            return booklist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  Book QueryBookById(String s){
        try {
            IODao ioDao = new IODao();
            Connection conn = super.getConnection();
            String sql = "SELECT * FROM Book WHERE id=" + "'" + s + "'";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            Book book = new Book();
            while (rs.next()) {
                book.setId(rs.getString("id"));
                book.setName(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setStore(rs.getInt("store"));
                book.setLend(ioDao.QueryBookNumById(book.getId()));
                book.setDesc(rs.getString("bookdesc"));
            }
            return book;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void DeleteById(String s){
        try {
            int i = 0;
            Connection conn = super.getConnection();
            PreparedStatement pst = null;
            String sql = "DELETE FROM Book WHERE  id=" + "'" + s + "'";
            pst = conn.prepareStatement(sql);
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void EditDone(Book book){
        try {
            int i = 0;
            Connection conn = super.getConnection();
            PreparedStatement pst = null;
            String sql = "UPDATE Book SET bookname=?,author=?,publisher=?,store=?,bookdesc=? WHERE id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, book.getName());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getPublisher());
            pst.setInt(4, book.getStore());
            pst.setString(5, book.getDesc());
            pst.setString(6, book.getId());
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
