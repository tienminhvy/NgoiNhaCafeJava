package ngoinhacafe.Logic.QuanLyTaiKhoan;

public class TaiKhoan {
    String TenTK, MatKhau, maNV, maQuyen, MaTK;
    
    public TaiKhoan(String TenTK, String MatKhau, String maNV, String maQuyen, String MaTK) {
        this.TenTK = TenTK;
        this.MatKhau = MatKhau;
        this.maNV = maNV;
        this.maQuyen = maQuyen;
        this.MaTK = MaTK;
    }
    
    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }


    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }
    
}
