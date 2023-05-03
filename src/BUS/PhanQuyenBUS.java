package BUS;

import DAO.PhanQuyenDAO;
import DTO.PhanQuyen;
import CustomFuncs.CustomDialog;

import java.util.ArrayList;

public class PhanQuyenBUS {

    public static PhanQuyen quyenTK = null;
    private PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAO();
    private ArrayList<PhanQuyen> listPhanQuyen = null;

    public void docDanhSachQuyen() {
        this.listPhanQuyen = phanQuyenDAO.getListQuyen();
    }

    public void kiemTraQuyen(String quyen) {
        quyenTK = phanQuyenDAO.getQuyen(quyen);
    }

    public ArrayList<PhanQuyen> getListQuyen() {
        if (listPhanQuyen == null)
            docDanhSachQuyen();
        return this.listPhanQuyen;
    }

    public boolean suaQuyen(int maQuyen, String tenQuyen, int nhapHang, int sanPham, int nhanVien, int khachHang, int thongKe) {
        PhanQuyen phanQuyen = new PhanQuyen(maQuyen, tenQuyen, nhapHang, sanPham, nhanVien, khachHang, thongKe);
        boolean flag = phanQuyenDAO.suaQuyen(phanQuyen);
        if (flag) {
            new CustomDialog("Sửa thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Sửa thất bại!", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean themQuyen(String tenQuyen) {
        if (tenQuyen == null || tenQuyen.trim().equals("")) {
            return false;
        }
        
        if (kiemTonTaiTraQuyen(tenQuyen)) {
            new CustomDialog("Thêm thất bại! Quyền đã tồn tại", CustomDialog.ERROR_DIALOG);
            return false;
        }

        PhanQuyen phanQuyen = new PhanQuyen(0, tenQuyen, 0, 0, 0, 0, 0);
        boolean flag = phanQuyenDAO.themQuyen(phanQuyen);
        if (flag) {
            new CustomDialog("Thêm thành công! Hãy hiệu chỉnh quyền", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Thêm thất bại! Quyền đã tồn tại", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }

    private boolean kiemTonTaiTraQuyen(String tenQuyen) {
        docDanhSachQuyen();
        for (PhanQuyen q : listPhanQuyen) {
            
            if (q.getQuyen().equalsIgnoreCase(tenQuyen))
                return true;
            
                
        }
        return false;
    }

    public boolean xoaQuyen(String tenQuyen) {
        boolean flag = phanQuyenDAO.xoaQuyen(tenQuyen);
        if (flag) {
            new CustomDialog("Xoá thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Xoá thất bại!", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
