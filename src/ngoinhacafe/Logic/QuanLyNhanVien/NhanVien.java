
package ngoinhacafe.Logic.QuanLyNhanVien;


import java.time.LocalDate;

public class NhanVien {

    String MaNV, TenNV, DiaChi, SDT, MaTK;
    LocalDate NgaySinh;
    int TrangThai;

    public NhanVien(String MaNV, String TenNV, String DiaChi, String SDT, String MaTK, LocalDate NgaySinh, int TrangThai) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.MaTK = MaTK;
        this.NgaySinh = NgaySinh;
        this.TrangThai = TrangThai;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    //test nhan vien
//    public static void main(String[] args) {
//        NhanVien nv = new NhanVien("", "", "", "", "", LocalDate.MIN, 0);
//    }
}
