/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngoinhacafe.GUI.FormQuanLy.ThongKe;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import ngoinhacafe.GUI.FormChung.MyTable;
import ngoinhacafe.GUI.MyButton.DateButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author DELL
 */
public class ThongKeHuuForm {

}

class ThongKeSanPham extends JPanel {
    

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    JButton btnRefresh = new JButton("Làm mới");
    MyTable tb;

    public ThongKeSanPham() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel tieu chi
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Số lượng nhập", "Số lượng bán"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(getClass().getResource("/assets/images/icons8_data_backup_30px.png")));
        btnRefresh.addActionListener((ae) -> {
            
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);

        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thong ke
        tb = new MyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }

//    private void soLuongSanPhamNhap() {
//        tb.clear();
//        tb.setHeaders(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Mã phiếu nhập", "Tên nhà cung cấp", "Ngày nhập", "Số lượng"});
//
//        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);
//
//        int tongTatCa = 0;
//        for (SanPham sp : qlspBUS.getDssp()) {
//            int tongSoLuong = 0;
//            tb.addRow(new String[]{sp.getMaSP(), sp.getTenSP(), "", "", "", ""});
//
//            for (PhieuNhap pn : qlpnBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
//                ChiTietPhieuNhap ctpn = qlctpnBUS.getChiTiet(pn.getMaPN(), sp.getMaSP());
//                if (ctpn != null) {
//                    tb.addRow(new String[]{"", "",
//                        pn.getMaPN(),
//                        qlnccBUS.getNhaCungCap(pn.getMaNCC()).getTenNCC(),
//                        String.valueOf(pn.getNgayNhap()),
//                        String.valueOf(ctpn.getSoLuong())
//                    });
//                    tongSoLuong += ctpn.getSoLuong();
//                }
//            }
//            
//            tb.addRow(new String[]{"", "", "", "", mcd.getKhoangTG(), String.valueOf(tongSoLuong)});
//            tb.addRow(new String[]{"", "", "", "", "", ""});
//
//            tongTatCa += tongSoLuong;
//        }
//        tb.addRow(new String[]{"", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa)});
//    }
//
//    private void soLuongSanPhamBan() {
//        tb.clear();
//        tb.setHeaders(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Mã hóa đơn", "Tên nhân viên", "Ngày lập", "Số lượng"});
//
//        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);
//
//        int tongTatCa = 0;
//        for (SanPham sp : qlspBUS.getDssp()) {
//            int tongSoLuong = 0;
//            tb.addRow(new String[]{sp.getMaSP(), sp.getTenSP(), "", "", "", ""});
//
//            for (HoaDon hd : qlhdBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayTu(), -1, -1)) {
//                ChiTietHoaDon cthd = qlcthdBUS.getChiTiet(hd.getMaHoaDon(), sp.getMaSP());
//                if (cthd != null) {
//                    tb.addRow(new String[]{"", "",
//                        hd.getMaHoaDon(),
//                        qlnvBUS.getNhanVien(hd.getMaNhanVien()).getTenNV(),
//                        String.valueOf(hd.getNgayLap()),
//                        String.valueOf(cthd.getSoLuong())
//                    });
//                    tongSoLuong += cthd.getSoLuong();
//                }
//            }
//            tb.addRow(new String[]{"", "", "", "", mcd.getKhoangTG(), String.valueOf(tongSoLuong)});
//            tb.addRow(new String[]{"", "", "", "", "", ""});
//            tongTatCa += tongSoLuong;
//        }
//
//        tb.addRow(new String[]{"", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa)});
//    }
    private void soLuongSanPhamNhap() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Mã phiếu nhập", "Tên nhà cung cấp", "Ngày nhập", "Số lượng", "Ðơn giá", "Tổng tiền"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        int tongTatCa = 0;
        float tongTien = 0;
        
        tb.addRow(new String[]{"", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa), "", String.valueOf(tongTien)});
    }

    private void soLuongSanPhamBan() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã sản phẩm", "Tên sản phẩm", "Mã hóa don", "Tên nhân viên", "Ngày lập", "Số lượng", "Ðơn giá", "Tổng tiền"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        int tongTatCa = 0;
        float tongTien = 0;
        

        tb.addRow(new String[]{"", "", "", "", "T?ng t?t c?", String.valueOf(tongTatCa), "", String.valueOf(tongTien)});
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Số lượng nhập")) {
            soLuongSanPhamNhap();
        }
        if (cbTieuChi.getSelectedItem().equals("Số lượng bán")) {
            soLuongSanPhamBan();
        }
    }
}

class ThongKeNhanVien extends JPanel {
    

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    JButton btnRefresh = new JButton("Làm mới");
    MyTable tb;

    public ThongKeNhanVien() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel tieu chi
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Tổng tiền", "Số lượng sản phẩm"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(getClass().getResource("/assets/images/icons8_data_backup_30px.png")));
        btnRefresh.addActionListener((ae) -> {
            
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);

        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thong ke
        tb = new MyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }

    public void tongTienTungNhanVien_searchOnChange() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã nhân viên", "Tên nhân viên", "Mã hóa đơn", "Ngày lập", "Tổng tiền hóa đơn"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        float tongTatCa = 0;
        
        
    }

    public void sanPhamCuaTungNhanVien_searchOnChange() {
        tb.setHeaders(new String[]{"Mã nhân viên", "Tên nhân viên", "Mã hóa đơn", "Ngày lập", "Mã sản phẩm", "Tên sản phẩm", "Số lượng sản phẩm"});
        tb.clear();

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        int tongTatCa = 0;
        
        tb.addRow(new String[]{"", "", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa)});
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Tổng tiền")) {
            tongTienTungNhanVien_searchOnChange();
        }
        if (cbTieuChi.getSelectedItem().equals("Số lượng sản phẩm")) {
            sanPhamCuaTungNhanVien_searchOnChange();
        }
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }
}

class ThongKeKhachHang extends JPanel {

    

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    JButton btnRefresh = new JButton("Làm mới");
    MyTable tb;

    public ThongKeKhachHang() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel tieu chi
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Tổng tiền", "Số lượng sản phẩm"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(getClass().getResource("/assets/images/icons8_data_backup_30px.png")));
        btnRefresh.addActionListener((ae) -> {
            
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);

        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thong ke
        tb = new MyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }

    //Thong ke tong tien hoa don cua tung khach hang
    public void tongTienTungKhachHang_searchOnChange() {
        tb.setHeaders(new String[]{"Mã khách hàng", "Tên khách hàng", "Mã hóa đơn", "Ngày lập", "Tổng tiền hóa đơn"});
        tb.clear();

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        float tongTatCa = 0;
        
        
    }

    //Thong ke san pham va so luong mua cua tung khach hang
    public void sanPhamCuaTungKhachHang_searchOnChange() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã khách hàng", "Tên khách hàng", "Mã hóa đơn", "Ngày lập", "Mã sản phẩm", "Tên sản phẩm", "Số lượng sản phẩm"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        int tongTatCa = 0;
        
        tb.addRow(new String[]{"", "", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa)});
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Tổng tiền")) {
            tongTienTungKhachHang_searchOnChange();
        }
        if (cbTieuChi.getSelectedItem().equals("Số lượng sản phẩm")) {
            sanPhamCuaTungKhachHang_searchOnChange();
        }
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }
}

class ThongKeNhaCungCap extends JPanel {
    

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    MyTable tb;
    JButton btnRefresh = new JButton("Làm mới");

    public ThongKeNhaCungCap() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel tieu chi
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Số lượng sản phẩm", "Tổng thành tiền"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(getClass().getResource("/assets/images/icons8_data_backup_30px.png")));
        btnRefresh.addActionListener((ae) -> {
            
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);
        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thong ke
        tb = new MyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }

    private void soLuongSanPhamCungCap() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã nhà cung cấp", "Tên nhà cung cấp", "Mã phiếu nhập", "Ngày lập", "Mã sản phẩm", "Tên sản phẩm", "Số lượng"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        int tongTatCa = 0;
        
        tb.addRow(new String[]{"", "", "", "", "", "Tổng tất cả:", String.valueOf(tongTatCa)});
    }

    private void tongTienThanhToan() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã nhà cung cấp", "Tên nhà cung cấp", "Mã phiếu nhập", "Ngày lập", "Mã sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        float tongTatCa = 0;
        
        
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Số lượng sản phẩm")) {
            soLuongSanPhamCungCap();
        }
        if (cbTieuChi.getSelectedItem().equals("Tổng thành tiền")) {
            tongTienThanhToan();
        }
    }
}
