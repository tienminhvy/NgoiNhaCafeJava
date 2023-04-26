package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMai;
import MyCustom.MyDialog;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

public class KhuyenMaiBUS {

    private ArrayList<KhuyenMai> listGiamGia = null;
    private KhuyenMaiDAO giamGiaDAO = new KhuyenMaiDAO();

    public KhuyenMaiBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listGiamGia = giamGiaDAO.getDanhSachKhuyenMai();
    }

    public ArrayList<KhuyenMai> getDanhSachKhuyenMai() {
        if (this.listGiamGia == null)
            docDanhSach();
        return this.listGiamGia;
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public boolean themKhuyenMai(String ten, String phanTram, String dieuKien, String ngayBD, String ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        if (ten.equals("")) {
            new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        boolean flag = false;
        try {
            
            Date ngayBDdt = sdf.parse(ngayBD);
            Date ngayKTdt = sdf.parse(ngayKT);
            
            if (ngayBDdt.compareTo(ngayKTdt) > 0 || ngayBDdt.compareTo(ngayKTdt) == 0) {
                new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
                return false;
            }
            int phanTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            KhuyenMai gg = new KhuyenMai();
            gg.setTenKM(ten);
            gg.setPhanTramKM(phanTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);

            flag = giamGiaDAO.themMaGiam(gg);
        } catch (Exception e) {
            new MyDialog("Hãy nhập số nguyên hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Thêm mới thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm mới thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean suaKhuyenMai(String ma, String ten, String phanTram, String dieuKien, String ngayBD, String ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        
        if (ma.equals("")) {
            new MyDialog("Chưa chọn mã để sửa!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        boolean flag = false;
        try {
            
            Date ngayBDdt = sdf.parse(ngayBD);
            Date ngayKTdt = sdf.parse(ngayKT);
            
            if (ngayBDdt.compareTo(ngayKTdt) > 0 || ngayBDdt.compareTo(ngayKTdt) == 0) {
                new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
                return false;
            }
            
            int maGiam = Integer.parseInt(ma);
            int phanTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            KhuyenMai gg = new KhuyenMai();
            gg.setMaKM(maGiam);
            gg.setTenKM(ten);
            gg.setPhanTramKM(phanTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);

            flag = giamGiaDAO.suaMaGiam(gg);
        } catch (Exception e) {
            new MyDialog("Hãy nhập số nguyên hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
