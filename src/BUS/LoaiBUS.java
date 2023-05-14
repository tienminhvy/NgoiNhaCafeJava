package BUS;

import DAO.LoaiDAO;
import DTO.LoaiSP;
import CustomFuncs.CustomDialog;
import CustomFuncs.Regex;
import java.util.ArrayList;

public class LoaiBUS {

    private LoaiDAO loaiDAO = new LoaiDAO();
    private ArrayList<LoaiSP> listLoai = null;
    
    public LoaiBUS() {
        docDanhSachLoai();
    }

    public void docDanhSachLoai() {
        this.listLoai = loaiDAO.getDanhSachLoai();
    }

    public ArrayList<LoaiSP> getDanhSachLoai() {
        if (listLoai == null) {
            docDanhSachLoai();
        }
        return this.listLoai;
    }

    public String getTenLoai(int ma) {
        LoaiSP loai = loaiDAO.getLoai(ma);
        if (loai == null)
            return "";
        return loai.getMaLoai() + " - " + loai.getTenLoai();
    }

    public boolean themLoai(int maLoai, String tenLoai, String MoTa) {
        if (tenLoai.trim().equals("")) {
            new CustomDialog("Không được để trống tên loại!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (!Regex.ktraTen(tenLoai)) {
            new CustomDialog("Tên không được chứa ký tự đặc biệt!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        maLoai += 1;
        LoaiSP loai = new LoaiSP(maLoai, tenLoai, MoTa);
        if (loaiDAO.themLoai(loai)) {
            new CustomDialog("Thêm thành công!", CustomDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new CustomDialog("Thêm thất bại!", CustomDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean xoaLoai(String ma) {
        if (ma.trim().equals("")) {
            new CustomDialog("Chưa chọn loại để xoá!", CustomDialog.SUCCESS_DIALOG);
            return false;
        }
        int maLoai = Integer.parseInt(ma);
        if (loaiDAO.xoaLoai(maLoai)) {
            new CustomDialog("Xoá thành công!", CustomDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new CustomDialog("Xoá thất bại! Loại có sản phẩm con", CustomDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suaLoai(String ma, String ten, String moTa) {
        if (ten.trim().equals("")) {
            new CustomDialog("Không được để trống tên loại!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (!Regex.ktraTen(ten)) {
            new CustomDialog("Tên không được chứa ký tự đặc biệt!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        int maLoai = Integer.parseInt(ma);
        if (loaiDAO.suaLoai(maLoai, ten, moTa)) {
            new CustomDialog("Sửa thành công!", CustomDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new CustomDialog("Sửa thất bại!", CustomDialog.ERROR_DIALOG);
            return false;
        }
    }

}
