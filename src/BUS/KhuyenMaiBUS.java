package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMai;
import CustomFuncs.CustomDialog;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

public class KhuyenMaiBUS {

    private ArrayList<KhuyenMai> listKhuyenMai = null;
    private KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();

    public KhuyenMaiBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listKhuyenMai = khuyenMaiDAO.getDanhSachKhuyenMai();
    }

    public ArrayList<KhuyenMai> getDanhSachKhuyenMai() {
        if (this.listKhuyenMai == null)
            docDanhSach();
        return this.listKhuyenMai;
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public boolean themKhuyenMai(String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        if (ten.equals("")) {
            new CustomDialog("Hãy nhập tên chương trình khuyến mãi!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        
        boolean flag = false;
        try {
            
            Date ngayBDdt = ngayBD;
            Date ngayKTdt = ngayKT;
            
            if (ngayBDdt.compareTo(ngayKTdt) > 0 || ngayBDdt.compareTo(ngayKTdt) == 0) {
                new CustomDialog("Ngày kết thúc không hợp lệ!", CustomDialog.ERROR_DIALOG);
                return false;
            }
            int phanTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            KhuyenMai gg = new KhuyenMai();
            gg.setTenKM(ten);
            gg.setPhanTramKM(phanTramGiam);
            gg.setDieuKien(dieuKienGiam);
            
            gg.setNgayBD(sdf.format(ngayBD));
            gg.setNgayKT(sdf.format(ngayKT));

            flag = khuyenMaiDAO.themKM(gg);
        } catch (Exception e) {
            System.out.println(e);
            if (e instanceof NullPointerException) {
                new CustomDialog("Ngày đã nhập không hợp lệ!", CustomDialog.ERROR_DIALOG);
            } else new CustomDialog("Hãy nhập số nguyên hợp lệ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new CustomDialog("Thêm mới thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Thêm mới thất bại!", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean suaKhuyenMai(String ma, String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        
        if (ma.equals("")) {
            new CustomDialog("Chưa chọn mã để sửa!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new CustomDialog("Hãy nhập tên chương trình khuyến mãi!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        
        boolean flag = false;
        try {
            
            Date ngayBDdt = ngayBD;
            Date ngayKTdt = ngayKT;
            
            if (ngayBDdt.compareTo(ngayKTdt) > 0 || ngayBDdt.compareTo(ngayKTdt) == 0) {
                new CustomDialog("Ngày kết thúc không hợp lệ!", CustomDialog.ERROR_DIALOG);
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
            gg.setNgayBD(sdf.format(ngayBD));
            gg.setNgayKT(sdf.format(ngayKT));

            flag = khuyenMaiDAO.suaKM(gg);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                new CustomDialog("Ngày đã nhập không hợp lệ!", CustomDialog.ERROR_DIALOG);
            } else new CustomDialog("Hãy nhập số nguyên hợp lệ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new CustomDialog("Sửa thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Sửa thất bại!", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
