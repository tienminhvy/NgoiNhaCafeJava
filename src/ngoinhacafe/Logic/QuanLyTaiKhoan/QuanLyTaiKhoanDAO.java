package ngoinhacafe.Logic.QuanLyTaiKhoan;

import ngoinhacafe.Logic.ConnectionDB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class QuanLyTaiKhoanDAO {

    ConnectionDB qltkConnectiion;

    public QuanLyTaiKhoanDAO() {

    }

    public ArrayList<TaiKhoan> readDB() {
        qltkConnectiion = new ConnectionDB();
        ArrayList<TaiKhoan> dstk = new ArrayList<>();
        try {
            String qry = "SELECT * FROM taikhoan";
            ResultSet r = qltkConnectiion.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String ten = r.getString("TenTaiKhoan");
                    String pass = r.getString("MatKhau");
                    String manv = r.getString("MaNV");
                    String maquyen = r.getString("MaQuyen");
                    
                    dstk.add(new TaiKhoan(ten, maquyen, manv, maquyen, manv));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng tài khoản");
        } finally {
            qltkConnectiion.closeConnect();
        }
        return dstk;
    }

    public Boolean add(TaiKhoan tk) {
        qltkConnectiion = new ConnectionDB();
        Boolean ok = qltkConnectiion.sqlUpdate("INSERT INTO `taikhoan` (`TenTaiKhoan`, `MatKhau`, `MaNV`, `MAQuyen`,`MATK`) VALUES ('"
                + tk.getTenTK()+ "', '" + tk.getMatKhau()+ "', '" + tk.getMaNV() + "', '" + tk.getMaQuyen() + "');" + tk.getMaTK() + "');");
        qltkConnectiion.closeConnect();
        return ok;
    }

    public Boolean delete(String username) {
        qltkConnectiion = new ConnectionDB();
        Boolean ok = qltkConnectiion.sqlUpdate("DELETE FROM `taikhoan` WHERE `taikhoan`.`TenTaiKhoan` = '" + username + "'");
        qltkConnectiion.closeConnect();
        return ok;
    }

    public Boolean update(String username, String pass, String maNV, String maQuyen) {
        qltkConnectiion = new ConnectionDB();
        Boolean ok = qltkConnectiion.sqlUpdate("Update taikhoan Set MatKhau='" + pass + "',MaNV='" + maNV
                + "',MaQuyen='" + maQuyen + "' where TenTaiKhoan='" + username + "'");
        qltkConnectiion.closeConnect();
        return ok;
    }

    public void close() {
        qltkConnectiion.closeConnect();
    }
}
