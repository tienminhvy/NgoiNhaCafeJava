package BUS;

import DAO.SanPhamDAO;
import DTO.SanPham;
import CustomFuncs.CustomDialog;
import CustomFuncs.Regex;

import java.util.ArrayList;

public class SanPhamBUS {

    private ArrayList<SanPham> listSanPham = null;
    private SanPhamDAO spDAO = new SanPhamDAO();

    public SanPhamBUS() {
        docListSanPham();
    }

    public void docListSanPham() {
        listSanPham = spDAO.getListSanPham();
    }

    public ArrayList<SanPham> getListSanPham() {
        if (listSanPham == null) {
            docListSanPham();
        }
        return listSanPham;
    }

    public SanPham getSanPham(String ma) {
        if (!ma.trim().equals("")) {
            try {
                int maSP = Integer.parseInt(ma);
                for (SanPham sp : listSanPham) {
                    if (sp.getMaSP() == maSP) {
                        return sp;
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public ArrayList<SanPham> getSanPhamTheoTen(String ten) {
        System.out.println(ten);
        ArrayList<SanPham> dssp = new ArrayList<>();
        for (SanPham sp : listSanPham) {
            String tenSP = sp.getTenSP().toLowerCase();
            if (tenSP.toLowerCase().contains(ten.toLowerCase())) {
                dssp.add(sp);
            }
        }
        return dssp;
    }

    public ArrayList<SanPham> getSanPhamTheoLoai(String ma) {
        if (!ma.trim().equals("")) {
            ArrayList<SanPham> dssp = new ArrayList<>();
            try {
                int maLoai = Integer.parseInt(ma);
                for (SanPham sp : listSanPham) {
                    if (sp.getMaLoai() == maLoai) {
                        dssp.add(sp);
                    }
                }
                return dssp;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public String getAnh(String ma) {
        int maSP = Integer.parseInt(ma);
        return spDAO.getAnh(maSP);
    }

    public void capNhatSoLuongSP(int ma, int soLuongMat) {
        spDAO.capNhatSoLuongSP(ma, soLuongMat);
    }

    public boolean themSanPham(String ten,
            String loai,
            String soLuong,
            String anh,
            String donGia) {
        
        if (ten.trim().equals("")) {
            new CustomDialog("Tên SP không được để rỗng!", CustomDialog.ERROR_DIALOG);
            return false;
        }
        
        if (!Regex.ktraTen(ten)) {
            new CustomDialog("Tên SP không được chứa ký tự đặc biệt!", CustomDialog.ERROR_DIALOG);
            return false;
        }

        try {
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            int soLuongSP = Integer.parseInt(soLuong);
            donGia = donGia.replace(",", "");
            int donGiaSP = Integer.parseInt(donGia);
            if (maLoai == 0) {
                new CustomDialog("Vui lòng chọn Loại sản phẩm!", CustomDialog.ERROR_DIALOG);
                return false;
            }
            SanPham sp = new SanPham();
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            if (spDAO.themSanPham(sp)) {
                new CustomDialog("Thêm thành công!", CustomDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new CustomDialog("Thêm thất bại!", CustomDialog.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            new CustomDialog("Nhập số hợp lệ cho Đơn giá và Số lượng!", CustomDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean xoaSanPham(String ma) {
        if (ma.trim().equals("")) {
            new CustomDialog("Chưa chọn sản phẩm để xoá!", CustomDialog.ERROR_DIALOG);
            return false;
        }

        int maSP = Integer.parseInt(ma);
        if (spDAO.xoaSanPham(maSP)) {
            new CustomDialog("Xoá thành công!", CustomDialog.SUCCESS_DIALOG);
            return true;
        }

        new CustomDialog("Xoá thất bại!", CustomDialog.ERROR_DIALOG);
        return false;
    }

    public boolean suaSanPham(String ma,
            String ten,
            String loai,
            String soLuong,
            String anh,
            String donGia) {

        try {
            if (ma.trim().equals("")) {
                new CustomDialog("Chưa chọn sản phẩm để sửa!", CustomDialog.ERROR_DIALOG);
                return false;
            }
            donGia = donGia.replace(",", "");
            int maSP = Integer.parseInt(ma);
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            int soLuongSP = Integer.parseInt(soLuong);
            int donGiaSP = Integer.parseInt(donGia);

            if (maLoai == 0) {
                new CustomDialog("Vui lòng chọn Loại sản phẩm!", CustomDialog.ERROR_DIALOG);
                return false;
            }

            if (ten.trim().equals("")) {
                new CustomDialog("Tên SP không được để rỗng!", CustomDialog.ERROR_DIALOG);
                return false;
            }
            
            if (!Regex.ktraTen(ten)) {
                new CustomDialog("Tên SP không được chứa ký tự đặc biệt!", CustomDialog.ERROR_DIALOG);
                return false;
            }

            SanPham sp = new SanPham();
            sp.setMaSP(maSP);
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);
            sp.setTrangThai(1);

            if (spDAO.suaSanPham(sp)) {
                new CustomDialog("Sửa thành công!", CustomDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new CustomDialog("Sửa thất bại!", CustomDialog.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            new CustomDialog("Nhập số hợp lệ cho Đơn giá và Số lượng!", CustomDialog.ERROR_DIALOG);
        }
        return false;
    }

    public String getTenSP(int maSP) {
        for (SanPham sp : listSanPham) {
            if (sp.getMaSP() == maSP) {
                return sp.getTenSP();
            }
        }
        return "";
    }
}
