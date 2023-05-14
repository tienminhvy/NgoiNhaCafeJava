package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCap;
import CustomFuncs.CustomDialog;
import CustomFuncs.Regex;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NhaCungCapBUS {

    private NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
    private ArrayList<NhaCungCap> listNhaCungCap = null;

    public NhaCungCapBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNhaCungCap = nhaCungCapDAO.getListNhaCungCap();
    }

    public ArrayList<NhaCungCap> getListNhaCungCap() {
        if (this.listNhaCungCap == null) {
            docDanhSach();
        }
        return this.listNhaCungCap;
    }

    public boolean themNhaCungCap(String tenNCC, String diaChi, String dienThoai, String fax) {
        if (tenNCC.trim().equals("")) {
            new CustomDialog("Hãy nhập tên nhà cung cấp!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (!Regex.ktraTen(tenNCC)) {
            new CustomDialog("Tên không được chứa ký tự đặc biệt!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (diaChi.trim().equals("")) {
            new CustomDialog("Hãy nhập địa chỉ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (dienThoai.trim().equals("")) {
            new CustomDialog("Hãy nhập số điện thoại!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (!Regex.ktraSoDienThoai(dienThoai)) {
            new CustomDialog("Số điện thoại không hợp lệ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (fax.trim().equals("")) {
            new CustomDialog("Hãy nhập fax!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (!Regex.ktraSo(fax)) {
            new CustomDialog("Fax phải chứa ký tự số!", CustomDialog.ERROR_DIALOG);
            return false;
        }

        NhaCungCap ncc = new NhaCungCap();
        ncc.setTenNCC(tenNCC);
        ncc.setDiaChi(diaChi);
        ncc.setDienThoai(dienThoai);
        ncc.setFax(fax);
        boolean flag = nhaCungCapDAO.addNCC(ncc);
        if (flag) {
            new CustomDialog("Thêm mới thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Thêm mới thất bại!", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean suaNhaCungCap(String maNCC, String tenNCC, String diaChi, String dienThoai, String fax) {
        if (tenNCC.trim().equals("")) {
            new CustomDialog("Hãy nhập tên Nhà cung cấp này!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (diaChi.trim().equals("")) {
            new CustomDialog("Hãy nhập địa chỉ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (dienThoai.trim().equals("")) {
            new CustomDialog("Hãy nhập số điện thoại!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (fax.trim().equals("")) {
            new CustomDialog("Hãy nhập fax!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (!Regex.ktraSoDienThoai(dienThoai)) {
            new CustomDialog("Số điện thoại không hợp lệ!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (!Regex.ktraSo(fax)) {
            new CustomDialog("Fax phải chứa ký tự số!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        
        int ma = Integer.parseInt(maNCC);

        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNCC(ma);
        ncc.setTenNCC(tenNCC);
        ncc.setDiaChi(diaChi);
        ncc.setDienThoai(dienThoai);
        ncc.setFax(fax);
        
        boolean flag = nhaCungCapDAO.updateNCC(ncc);

        if (flag) {
            new CustomDialog("Sửa thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Sửa thất bại!", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean xoaNhaCungCap(int maNCC) {
        boolean flag = nhaCungCapDAO.xoaNCC(maNCC);
        
        if (flag) {
            new CustomDialog("Xoá thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Xoá thất bại!", CustomDialog.ERROR_DIALOG);
        }
        
        return flag;
    }
}
