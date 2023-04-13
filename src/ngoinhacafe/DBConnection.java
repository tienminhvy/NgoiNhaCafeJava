/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngoinhacafe;
import java.sql.*;
import javax.sql.*;
import java.util.*;

/**
 *
 * @author Admin
 */
public class DBConnection {
    
    private static String DB_NAME = "db_cafe";
    private static String DB_URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    private static Connection conn;
    
    public static Connection getConnection() {
        conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception ex) {
            printErrOnException("Xảy ra lỗi khi kết nối đến DB!", ex);
        }
        return conn;
    }
    
    public static void closeConnection() {
        try {
            conn.close();
        } catch (Exception ex) {
            printErrOnException("Lỗi khi đóng kết nối DB!", ex);
        }
    }
    
    public static void printErrOnException(String err_name, Exception ex) {
        System.out.println("================");
        System.err.println(err_name);
        System.err.println("Nội dung lỗi:");
        System.err.println(ex.getMessage());
        System.out.println("");
        ex.printStackTrace();
        System.out.println("================");
    }
    
    public static ResultSet sendQuery(String query) {
        ResultSet rs = null;
        try {
            // connnect to database
            conn = getConnection();
            // crate statement - prevent SQL Injection
            PreparedStatement stmt = conn.prepareStatement(query);
            // get data from table 'student'
            rs = stmt.executeQuery();
            return rs;
        } catch (Exception ex) {
            printErrOnException("Xảy ra lỗi khi thực thi chuỗi truy vấn!", ex);
        }
        
        return rs;
    }
    
    public static int sendUpdateQuery(String query) {
        int affected_rows = 0;
        
        try {
            // connnect to database
            conn = getConnection();
            // crate statement - prevent SQL Injection
            PreparedStatement stmt = conn.prepareStatement(query);
            // get data from table 'student'
            affected_rows = stmt.executeUpdate();
            return affected_rows;
        } catch (Exception ex) {
            printErrOnException("Xảy ra lỗi khi thực thi chuỗi truy vấn cập nhật!", ex);
            affected_rows = 0;
        }
        return affected_rows;
    }
    
    public static ArrayList<String> getColumnNames(String table) {
        ArrayList<String> str = new ArrayList<>();
        ResultSet Result = sendQuery("SELECT * FROM "+table);
        try {
            ResultSetMetaData rsMetaData = Result.getMetaData();
            for(int i = 1; i < rsMetaData.getColumnCount() + 1; i++) {
                str.add(rsMetaData.getColumnName(i));
            }
        } catch (Exception ex) {
            printErrOnException("Đã xảy ra lỗi khi lấy danh sách tên cột!", ex);
            str = null;
        }
        return str;
    }
    public static ResultSet getAllColumsFrom(String table) {
        ResultSet Result = sendQuery("SELECT * FROM "+table);
        return Result;
    }
    public static ResultSet getColumsFrom(String columns, String table) {
        ResultSet Result = sendQuery("SELECT "+columns+" FROM "+table);
        return Result;
    }
    
    public static int deleteAllFromTable(String table) {
        int deleted_rows = sendUpdateQuery("DELETE FROM "+table);
        return deleted_rows;
    }
    
    public static int deleteAllFromTableWhere(String table, String condition) {
        int deleted_rows = sendUpdateQuery("DELETE FROM "+table+" WHERE "+condition);
        return deleted_rows;
    }
    
    public static int updateWhere(String table, String columnToUpdate, String valueToUpdate, String condition) {
        String query = "UPDATE "+ table +" "
                + "SET " + columnToUpdate + "=" + valueToUpdate + " "
                + "WHERE "+condition;
        int updated_rows = sendUpdateQuery(query);
        return updated_rows;
    }
    
    public static int insertInto(String table, String columns, String values) {
        String query = "INSERT INTO "+ table +" (" + columns + ")"
                + "VALUES " + "(" +values+ ")";
        int inserted_rows = sendUpdateQuery(query);
        return inserted_rows;
    }
}
