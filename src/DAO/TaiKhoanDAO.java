package DAO;

import BUS.DangNhapBUS;
import DTO.TaiKhoan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TaiKhoanDAO {

    public boolean themTaiKhoan(String TenTaiKhoan, String MatKhau) {
        try {
            String sql = "INSERT INTO taikhoan (TenTaiKhoan, MatKhau, TrangThai) "
                    + "VALUES (?, ?, 1)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, TenTaiKhoan);
            pre.setString(2, MatKhau);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
    public int getMaTK(String TenTaiKhoan) {
        try {
            String sql = "SELECT MaTK FROM taikhoan WHERE TenTaiKhoan = ?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, TenTaiKhoan);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("MaTK");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean kiemTraTrungTenDangNhap(String TenTaiKhoan) {
        try {
            String sql = "SELECT * FROM taikhoan WHERE TenTaiKhoan = ?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, TenTaiKhoan);
            ResultSet rs = pre.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getTenDangNhapTheoMa(int maTK) {
        try {
            String sql = "SELECT TenTaiKhoan FROM taikhoan WHERE MaTK=" + maTK;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean datLaiMatKhau(int maTK, String matKhau) {
        try {
            String sql = "UPDATE taikhoan SET MatKhau=? WHERE MaTK=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, matKhau);
            pre.setInt(2, maTK);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean themQuyen(int maTK, int maQuyen) {
        try {
            String sql = "INSERT INTO quyentaikhoan(MaQuyen, MaTK) "
                    + "VALUES (?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maQuyen);
            pre.setInt(2, maTK);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean datLaiQuyen(int maTK, int maQuyen) {
        try {
            String sql = "UPDATE quyentaikhoan SET MaQuyen=? WHERE MaTK=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maQuyen);
            pre.setInt(2, maTK);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public String getQuyenTheoMa(int maTK) {
        try {
            String sql = "SELECT TenQuyen,ChiTietQuyen FROM quyentaikhoan, phanquyen WHERE MaTK=? AND quyentaikhoan.MaQuyen = phanquyen.MaQuyen";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maTK);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                return rs.getString("TenQuyen");
            }
            
        } catch (Exception e) {
        }
        return "";
    }
    
    public String getChiTietQuyen(int maQuyen) {
        try {
            String sql = "SELECT ChiTietQuyen FROM phanquyen WHERE MaQuyen=" + maQuyen;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean khoaTaiKhoan(int maTK) {
        try {
            String sql = "UPDATE taikhoan SET TrangThai=0 WHERE MaTK=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maTK);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean moKhoaTaiKhoan(int maTK) {
        try {
            String sql = "UPDATE taikhoan SET TrangThai=1 WHERE MaTK=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, maTK);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean doiMatKhau(String matKhauCu, String matKhauMoi) {
        try {
            String sql = "UPDATE taikhoan SET MatKhau=? WHERE MaTK=? AND MatKhau=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, matKhauMoi);
            pre.setInt(2, DangNhapBUS.taiKhoanLogin.getMaTK());
            pre.setString(3, matKhauCu);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public int getTrangThai(int ma) {
        try {
            String sql = "SELECT TrangThai FROM TaiKhoan WHERE MaTK=" + ma;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }
}
