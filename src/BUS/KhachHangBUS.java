package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHang;
import CustomFuncs.CustomDialog;
import CustomFuncs.Regex;

import java.util.ArrayList;
import java.util.Locale;

public class KhachHangBUS {

    private ArrayList<KhachHang> listKhachHang = null;
    private KhachHangDAO khachHangDAO = new KhachHangDAO();

    public void docDanhSach() {
        this.listKhachHang = khachHangDAO.getListKhachHang();
    }

    public ArrayList<KhachHang> getListKhachHang() {
        if (listKhachHang == null)
            docDanhSach();
        return listKhachHang;
    }

    public ArrayList<KhachHang> timKiemKhachHang(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<KhachHang> dskh = new ArrayList<>();
        for (KhachHang kh : listKhachHang) {
            String ten = kh.getTen().toLowerCase();
            String diaChi = kh.getDiaChi().toLowerCase();
            String sdt = kh.getSdt().toLowerCase();
            if (ten.contains(tuKhoa) || diaChi.contains(tuKhoa) || sdt.contains(sdt)) {
                dskh.add(kh);
            }
        }
        return dskh;
    }

    public boolean themKhachHang(String ten, String diaChi, String sdt) {
        if (ten.trim().equals("")) {
            new CustomDialog("Không được để trống tên!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (diaChi.trim().equals("")) {
            new CustomDialog("Không được để trống địa chỉ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (sdt.trim().equals("")) {
            new CustomDialog("Không được để trống số điện thoại!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (!Regex.ktraTen(ten)) {
            new CustomDialog("Tên không hợp lệ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (!Regex.ktraSoDienThoai(sdt)) {
            new CustomDialog("Số điện thoại không hợp lệ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        
        KhachHang kh = new KhachHang();
        kh.setTen(ten);
        kh.setDiaChi(diaChi);
        kh.setSdt(sdt);
        boolean flag = khachHangDAO.addKhachHang(kh);
        if (flag) {
            new CustomDialog("Thêm thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Thêm thất bại!", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean suaKhachHang(String ma, String ten, String diaChi, String sdt) {
        if (ten.trim().equals("")) {
            new CustomDialog("Không được để trống tên!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (diaChi.trim().equals("")) {
            new CustomDialog("Không được để trống địa chỉ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (sdt.trim().equals("")) {
            new CustomDialog("Không được để trống số điện thoại!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (!Regex.ktraTen(ten)) {
            new CustomDialog("Tên không hợp lệ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (!Regex.ktraSoDienThoai(sdt)) {
            new CustomDialog("Số điện thoại không hợp lệ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        
        KhachHang kh = new KhachHang();
        kh.setTen(ten);
        kh.setDiaChi(diaChi);
        kh.setSdt(sdt);
        boolean flag = khachHangDAO.updateKhachHang(Integer.parseInt(ma), kh);
        if (flag) {
            new CustomDialog("Sửa thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Sửa thất bại!", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean xoaKhachHang(String ma) {
        boolean flag = false;
        try {
            int maKH = Integer.parseInt(ma);
            CustomDialog dlg = new CustomDialog("Bạn có chắc chắn muốn xoá?", CustomDialog.WARNING_DIALOG);
            if(dlg.getAction() == CustomDialog.CANCEL_OPTION)
                return false;
            flag = khachHangDAO.deleteKhachHang(maKH);
        } catch (Exception e) {
            new CustomDialog("Chưa chọn khách hàng!", CustomDialog.ERROR_DIALOG);
        }
        if (flag) {
            new CustomDialog("Xoá thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Xoá thất bại!", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
