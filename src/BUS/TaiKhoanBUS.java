package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoan;
import CustomFuncs.CustomDialog;
import DAO.NhanVienDAO;
import DAO.PhanQuyenDAO;

public class TaiKhoanBUS {

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public String getTenDangNhapTheoMa(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.getTenDangNhapTheoMa(maNV);
    }

    public String getQuyenTheoMa(String ma) {
        int maTK = Integer.parseInt(ma);
        return taiKhoanDAO.getQuyenTheoMa(maTK);
    }

    public void datLaiMatKhau(String ma, String tenDangNhap) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.datLaiMatKhau(maNV, tenDangNhap);
        if (flag) {
            new CustomDialog("Đặt lại thành công! Mật khẩu mới là: " + tenDangNhap, CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Đặt lại thất bại!", CustomDialog.ERROR_DIALOG);
        }
    }

    public void datLaiQuyen(String ma, String quyen) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.datLaiQuyen(maNV, new PhanQuyenDAO().getMaQuyen(quyen));
        if (flag) {
            new CustomDialog("Đặt lại thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Đặt lại thất bại!", CustomDialog.ERROR_DIALOG);
        }
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        return taiKhoanDAO.kiemTraTrungTenDangNhap(tenDangNhap);
    }

    public boolean themTaiKhoan(String ma, String tenDangNhap, String quyen) {
        int maNV = Integer.parseInt(ma);
        if (tenDangNhap.trim().equals("")) {
            new CustomDialog("Không được để trống Tên đăng nhập!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        if (kiemTraTrungTenDangNhap(tenDangNhap)) {
            CustomDialog dlg = new CustomDialog("Tên đăng nhập bị trùng! Có thể tài khoản bị khoá, thực hiện mở khoá?", CustomDialog.WARNING_DIALOG);
            if (dlg.getAction() == CustomDialog.OK_OPTION) {
                moKhoaTaiKhoan(ma);
                return true;
            }
            return false;
        }
        boolean flag = taiKhoanDAO.themTaiKhoan(tenDangNhap, tenDangNhap);
        if (flag) {
            
            new NhanVienDAO().updateTaiKhoanNV(maNV, tenDangNhap);
            taiKhoanDAO.themQuyen(taiKhoanDAO.getMaTK(tenDangNhap), new PhanQuyenDAO().getMaQuyen(quyen));
            
            new CustomDialog("Cấp tài khoản thành công! Mật khẩu là " + tenDangNhap, CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Cấp tài khoản thất bại! Tài khoản đã tồn tại", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public void khoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.khoaTaiKhoan(maNV);
        if (flag) {
            new CustomDialog("Khoá tài khoản thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Khoá tài khoản thất bại!", CustomDialog.ERROR_DIALOG);
        }
    }

    public void moKhoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        
        boolean flag = taiKhoanDAO.moKhoaTaiKhoan(maNV);
        if (flag) {
            new CustomDialog("Mở khoá tài khoản thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Mở khoá tài khoản thất bại!", CustomDialog.ERROR_DIALOG);
        }
    }

    public boolean doiMatKhau(String matKhauCu, String matKhauMoi, String nhapLaiMatKhau) {
        if(!matKhauMoi.equals(nhapLaiMatKhau)) {
            new CustomDialog("Mật khẩu mới không khớp!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = taiKhoanDAO.doiMatKhau(matKhauCu, matKhauMoi);
        if (flag) {
            new CustomDialog("Đổi thành công!", CustomDialog.SUCCESS_DIALOG);
        } else {
            new CustomDialog("Mật khẩu cũ nhập sai!", CustomDialog.ERROR_DIALOG);
        }
        return flag;
    }
    
    public int getTrangThai(String maNV) {
        int ma = Integer.parseInt(maNV);
        return taiKhoanDAO.getTrangThai(ma);
    }

}
