package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVien;
import CustomFuncs.CustomDialog;
import CustomFuncs.Regex;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

public class NhanVienBUS {

    private NhanVienDAO nvDAO = new NhanVienDAO();
    private ArrayList<NhanVien> listNhanVien = null;

    public NhanVienBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNhanVien = nvDAO.getDanhSachNhanVien();
    }
    
    public NhanVien getNhanVien(int maNV) {
        return nvDAO.getNhanVien(maNV);
    }

    public ArrayList<NhanVien> getDanhSachNhanVien() {
        docDanhSach();
        return this.listNhanVien;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public boolean themNhanVien(String ten, String ngaySinh, String diaChi, String sdt) {
        ten = ten.trim();
        ngaySinh = ngaySinh.trim();
        sdt = sdt.trim();
        if (ten.equals("")) {
            new CustomDialog("Tên không được để trống!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (diaChi.equals("")) {
            new CustomDialog("Địa chỉ không được để trống!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (sdt.equals("")) {
            new CustomDialog("Số điện thoại không được để trống!", CustomDialog.ERROR_DIALOG);
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
        
        NhanVien nv = new NhanVien();
        nv.setTen(ten);
        Date d;
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d = sdf.parse(ngaySinh);
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            nv.setNgaySinh(sdf.format(d));
        } catch (Exception ex) {
            d = new Date();
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            nv.setNgaySinh(sdf.format(d));
        }
        nv.setDiaChi(diaChi);
        nv.setSdt(sdt);
        boolean flag = nvDAO.themNhanVien(nv);
        if (!flag) {
            new CustomDialog("Thêm thất bại!", CustomDialog.ERROR_DIALOG);
        } else {
            new CustomDialog("Thêm thành công!", CustomDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public boolean updateNhanVien(String ma, String ten, String ngaySinh, String diaChi, String sdt) {
        int maNV = Integer.parseInt(ma);
        ten = ten.trim();
        ngaySinh = ngaySinh.trim();
        diaChi = diaChi.trim();
        if (ten.equals("")) {
            new CustomDialog("Tên không được để trống!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (diaChi.equals("")) {
            new CustomDialog("Địa chỉ không được để trống!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (sdt.equals("")) {
            new CustomDialog("Số điện thoại không được để trống!", CustomDialog.ERROR_DIALOG);
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
        
        NhanVien nv = new NhanVien();
        nv.setMaNV(maNV);
        nv.setTen(ten);
        
        Date d;
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d = sdf.parse(ngaySinh);
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            nv.setNgaySinh(sdf.format(d));
        } catch (Exception ex) {
            d = new Date();
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            nv.setNgaySinh(sdf.format(d));
        }
        
        nv.setDiaChi(diaChi);
        nv.setSdt(sdt);
        boolean flag = nvDAO.updateNhanVien(nv);
        if (!flag) {
            new CustomDialog("Cập nhập thất bại!", CustomDialog.ERROR_DIALOG);
        } else {
            new CustomDialog("Cập nhập thành công!", CustomDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public ArrayList<NhanVien> timNhanVien(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        for (NhanVien nv : listNhanVien) {
            if (nv.getTen().toLowerCase().contains(tuKhoa)) {
                dsnv.add(nv);
            }
        }
        return dsnv;
    }

    public boolean xoaNhanVien(String ma) {
        try {
            int maNV = Integer.parseInt(ma);
            CustomDialog dlg = new CustomDialog("Bạn có chắc chắn muốn xoá?", CustomDialog.WARNING_DIALOG);
            boolean flag = false;
            if (dlg.getAction() == CustomDialog.OK_OPTION) {
                flag = nvDAO.deleteNhanVien(maNV);
                if (flag) {
                    new CustomDialog("Xoá thành công!", CustomDialog.SUCCESS_DIALOG);
                } else {
                    new CustomDialog("Xoá thất bại!", CustomDialog.ERROR_DIALOG);
                }
            }
            return flag;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            new CustomDialog("Chưa chọn nhân viên!", CustomDialog.ERROR_DIALOG);
        }
        return false;
    }
}
