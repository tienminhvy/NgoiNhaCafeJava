package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVien;
import MyCustom.MyDialog;
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

    public ArrayList<NhanVien> getDanhSachNhanVien() {
        if (this.listNhanVien == null)
            docDanhSach();
        return this.listNhanVien;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public boolean themNhanVien(String ten, String ngaySinh, String diaChi, String sdt) {
        ten = ten.trim();
        ngaySinh = ngaySinh.trim();
        sdt = sdt.trim();
        if (ten.equals("")) {
            new MyDialog("Tên không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (sdt.equals("")) {
            new MyDialog("Số điện thoại không được để trống!", MyDialog.ERROR_DIALOG);
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
            new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public boolean updateNhanVien(String ma, String ten, String ngaySinh, String diaChi, String sdt) {
        int maNV = Integer.parseInt(ma);
        ten = ten.trim();
        ngaySinh = ngaySinh.trim();
        diaChi = diaChi.trim();
        if (ten.equals("")) {
            new MyDialog("Tên không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (diaChi.equals("")) {
            new MyDialog("Địa chỉ không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (sdt.equals("")) {
            new MyDialog("Số điện thoại không được để trống!", MyDialog.ERROR_DIALOG);
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
            new MyDialog("Cập nhập thất bại!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Cập nhập thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public ArrayList<NhanVien> timNhanVien(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        for (NhanVien nv : listNhanVien) {
            if (nv.getTen().toLowerCase().contains(tuKhoa) || nv.getNgaySinh().toLowerCase().contains(tuKhoa) ||
                    nv.getDiaChi().toLowerCase().contains(tuKhoa) || nv.getSdt().toLowerCase().contains(tuKhoa)) {
                dsnv.add(nv);
            }
        }
        return dsnv;
    }

    public boolean xoaNhanVien(String ma) {
        try {
            int maNV = Integer.parseInt(ma);
            MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
            boolean flag = false;
            if (dlg.getAction() == MyDialog.OK_OPTION) {
                flag = nvDAO.deleteNhanVien(maNV);
                if (flag) {
                    new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
                } else {
                    new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
                }
            }
            return flag;
        } catch (Exception e) {
            new MyDialog("Chưa chọn nhân viên!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean nhapExcel(String ho, String ten, String gioiTinh, String chucVu) {
        NhanVien nv = new NhanVien();
        nv.setTen(ho);
        nv.setNgaySinh(ten);
        nv.setDiaChi(gioiTinh);
        nv.setSdt(chucVu);
        boolean flag = nvDAO.nhapExcel(nv);
        return flag;
    }
}
