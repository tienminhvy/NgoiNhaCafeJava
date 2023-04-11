package ngoinhacafe.GUI.FormQuanLy.ThongKe;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import ngoinhacafe.GUI.FormChon.ChonKhachHangForm;
import ngoinhacafe.GUI.FormChon.ChonNhaCungCapForm;
import ngoinhacafe.GUI.FormChon.ChonNhanVienForm;
import ngoinhacafe.GUI.FormChon.ChonSanPhamForm;
import ngoinhacafe.GUI.FormChung.MyTable;
import ngoinhacafe.GUI.MyButton.DateButton;
import ngoinhacafe.GUI.MyButton.MoreButton;
import ngoinhacafe.GUI.MyButton.RefreshButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author nguye
 */
public class ThongKeForm extends JPanel {

    public static final int width = 1120, height = 740;

    public ThongKeForm() {
        this.setBackground(Color.darkGray);

        ThongKe_Hoang tkH = new ThongKe_Hoang();
        ThongKeForm_NewVersion tk2 = new ThongKeForm_NewVersion();

        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        tabs.setPreferredSize(new Dimension(width, height));

        //add tab thong ke san pham
//        tabs.addTab("Thống kê ver2", getIcon("icons8_company_30px.png"), tk2);
        tabs.addTab("Thống kê tổng quát", getIcon("icons8_pie_chart_30px.png"), tkH);
        tabs.addTab("Sản phẩm", getIcon("icons8_multiple_smartphones_30px.png"), null);
        tabs.addTab("Nhân viên", getIcon("icons8_assistant_30px.png"), null);
        tabs.addTab("Khách hàng", getIcon("icons8_user_30px.png"), null);
        tabs.addTab("Nhà cung cấp", getIcon("icons8_company_30px.png"), null);

        tabs.addChangeListener((ce) -> {
            int i = tabs.getSelectedIndex();
            if (tabs.getComponentAt(i) == null) {
                switch (tabs.getTitleAt(i)) {
                    case "Sản phẩm":
                        tabs.setComponentAt(i, new ThongKeSanPham());
                        break;
                    case "Nhân viên":
                        tabs.setComponentAt(i, new ThongKeNhanVien());
                        break;
                    case "Khách hàng":
                        tabs.setComponentAt(i, new ThongKeKhachHang());
                        break;
                    case "Nhà cung cấp":
                        tabs.setComponentAt(i, new ThongKeNhaCungCap());
                        break;
                }
            }
        });

        this.add(tabs);
    }

    private ImageIcon getIcon(String filename) {
        return new ImageIcon(getClass().getResource("/assets/images/" + filename));
    }
}

class ThongKe_Hoang extends JPanel {
    

    JTextField txNgay1 = new JTextField(7);
    JTextField txNgay2 = new JTextField(7);
    JTextField txNhanVien = new JTextField(10);
    JTextField txKhachHang = new JTextField(10);
    JTextField txNhaCC = new JTextField(10);
    JTextField txSanPham = new JTextField(10);

    DatePicker dPicker1;
    DatePicker dPicker2;
    MoreButton btnChonNhanVien = new MoreButton();
    MoreButton btnChonKhachHang = new MoreButton();
    MoreButton btnChonNhaCC = new MoreButton();
    MoreButton btnChonSanPham = new MoreButton();

    JTabbedPane tabDoiTuongThongKe = new JTabbedPane();
    JPanel plThongKeHoaDon = new JPanel();
    JPanel plThongKePhieuNhap = new JPanel();
    JPanel plThongKeSoLuong = new JPanel();

    MyTable tbThongKeHoaDon = new MyTable();
    MyTable tbThongKePhieuNhap = new MyTable();

    MyTable tbKetQuaHoaDon = new MyTable();
    MyTable tbKetQuaPhieuNhap = new MyTable();

    JPanel plSanPham, plNhanVien, plKhachHang, plNhaCC;
    RefreshButton btnRefresh = new RefreshButton();

    public ThongKe_Hoang() {
        setLayout(new BorderLayout());

        // panel chon ngay
        DatePickerSettings ds = new DatePickerSettings();
        ds.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(ds);
        dPicker2 = new DatePicker(ds.copySettings());
        dPicker1.addDateChangeListener((dce) -> {
            txNgay1.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2.addDateChangeListener((dce) -> {
            txNgay2.setText(dPicker2.getDateStringOrEmptyString());
        });
        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);
        
        txNgay1.setBorder(BorderFactory.createTitledBorder("Từ"));
        txNgay2.setBorder(BorderFactory.createTitledBorder("Đến"));

        JPanel plChonNgay = new JPanel();
        plChonNgay.setBorder(BorderFactory.createTitledBorder("Khoảng ngày"));

        addDocumentListener(txNgay1);
        addDocumentListener(txNgay2);
        plChonNgay.add(txNgay1);
        plChonNgay.add(dPicker1);
        plChonNgay.add(txNgay2);
        plChonNgay.add(dPicker2);
        
        // event
        btnChonSanPham.addActionListener((ae) -> {
            ChonSanPhamForm cnv = new ChonSanPhamForm(txSanPham, null, null, null, null);
        });
        btnChonNhanVien.addActionListener((ae) -> {
            ChonNhanVienForm cnv = new ChonNhanVienForm(txNhanVien);
        });
        btnChonKhachHang.addActionListener((ae) -> {
            ChonKhachHangForm ckh = new ChonKhachHangForm(txKhachHang);
        });
        btnChonNhaCC.addActionListener((ae) -> {
            ChonNhaCungCapForm cnv = new ChonNhaCungCapForm(txNhaCC);
        });
        btnRefresh.addActionListener((ae) -> {
            refresh();
        });

        plSanPham = getPanelTieuChi("Sản phẩm", txSanPham, btnChonSanPham);
        plNhanVien = getPanelTieuChi("Nhân viên", txNhanVien, btnChonNhanVien);
        plKhachHang = getPanelTieuChi("Khách hàng", txKhachHang, btnChonKhachHang);
        plNhaCC = getPanelTieuChi("Nhà cung cấp", txNhaCC, btnChonNhaCC);

        // panel chon tieu chi
        JPanel plChonTieuChi = new JPanel();
        plChonTieuChi.add(plChonNgay);
        plChonTieuChi.add(plSanPham);
        plChonTieuChi.add(plNhanVien);
        plChonTieuChi.add(plKhachHang);
        plChonTieuChi.add(plNhaCC);
        plChonTieuChi.add(btnRefresh);
        this.add(plChonTieuChi, BorderLayout.NORTH);

        // panel thong ke hoa don (ban duoc)
        plThongKeHoaDon.setLayout(new BorderLayout());
        tbThongKeHoaDon.setHeaders(new String[]{"Hóa đơn", "Tên nhân viên", "Tên khách hàng", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"});
        tbThongKeHoaDon.setAlignment(0, JLabel.CENTER);
        tbThongKeHoaDon.setAlignment(4, JLabel.CENTER);
        tbThongKeHoaDon.setAlignment(5, JLabel.RIGHT);
        tbThongKeHoaDon.setAlignment(6, JLabel.RIGHT);
        plThongKeHoaDon.add(tbThongKeHoaDon, BorderLayout.CENTER);

        tbKetQuaHoaDon.setHeaders(new String[]{"TỔNG TẤT CẢ", "", "", "", "", "", "TỔNG BÁN RA"});
        tbKetQuaHoaDon.setPreferredSize(new Dimension(ThongKeForm.width, 75));
        tbKetQuaHoaDon.setAlignment(0, JLabel.CENTER);
        tbKetQuaHoaDon.setAlignment(4, JLabel.CENTER);
        tbKetQuaHoaDon.setAlignment(5, JLabel.RIGHT);
        tbKetQuaHoaDon.setAlignment(6, JLabel.RIGHT);
        plThongKeHoaDon.add(tbKetQuaHoaDon, BorderLayout.SOUTH);

        // panal thong ke phieu nhap (nhap kho)
        plThongKePhieuNhap.setLayout(new BorderLayout());
        tbThongKePhieuNhap.setHeaders(new String[]{"Phiếu nhập", "Tên nhân viên", "Tên nhà cung cấp", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"});
        tbThongKePhieuNhap.setAlignment(0, JLabel.CENTER);
        tbThongKePhieuNhap.setAlignment(4, JLabel.CENTER);
        tbThongKePhieuNhap.setAlignment(5, JLabel.RIGHT);
        tbThongKePhieuNhap.setAlignment(6, JLabel.RIGHT);
        plThongKePhieuNhap.add(tbThongKePhieuNhap, BorderLayout.CENTER);

        tbKetQuaPhieuNhap.setHeaders(new String[]{"TỔNG TẤT CẢ", "", "", "", "", "", "TỔNG NHẬP VÀO"});
        tbKetQuaPhieuNhap.setPreferredSize(new Dimension(ThongKeForm.width, 75));
        tbKetQuaPhieuNhap.setAlignment(0, JLabel.CENTER);
        tbKetQuaPhieuNhap.setAlignment(4, JLabel.CENTER);
        tbKetQuaPhieuNhap.setAlignment(5, JLabel.RIGHT);
        tbKetQuaPhieuNhap.setAlignment(6, JLabel.RIGHT);
        plThongKePhieuNhap.add(tbKetQuaPhieuNhap, BorderLayout.SOUTH);
        
        // panel thong ke tong so
        plThongKeSoLuong = new JPanel();
        setDataToPanelTong();

        // tabpane doi tuong thong ke
        tabDoiTuongThongKe.setBackground(Color.yellow);
        tabDoiTuongThongKe.addTab("Tổng", getIcon("icons8_futures_30px.png"), plThongKeSoLuong);
        tabDoiTuongThongKe.addTab("Bán ra", getIcon("icons8_small_business_30px_3.png"), plThongKeHoaDon);
        tabDoiTuongThongKe.addTab("Nhập vào", getIcon("icons8_downloads_30px.png"), plThongKePhieuNhap);

        // event chuyen tab
        // tab ban dau la hoa don, nen cần ẩn nha cung cap 
        plNhaCC.setVisible(false);
        // event
        tabDoiTuongThongKe.addChangeListener((ce) -> {
            Boolean HoaDon_isSelected = (tabDoiTuongThongKe.getSelectedComponent() == plThongKeHoaDon);
            plNhaCC.setVisible(!HoaDon_isSelected);
            plKhachHang.setVisible(HoaDon_isSelected);
        });

        this.add(tabDoiTuongThongKe, BorderLayout.CENTER);

        // show giá trị đầu
        onChangeThongKeBanHang();
        onChangeThongKeNhapHang();
    }
    
    private void setDataToPanelTong() {
        plThongKeSoLuong.removeAll();
    }

    public void refresh() {
        
        dPicker1.setDate(null);
        dPicker2.setDate(null);
        txSanPham.setText("");
        txNhanVien.setText("");
        txKhachHang.setText("");
        txNhaCC.setText("");

        Boolean HoaDon_isSelected = (tabDoiTuongThongKe.getSelectedComponent() == plThongKeHoaDon);
        if (HoaDon_isSelected) {
            onChangeThongKeBanHang();
        } else {
            onChangeThongKeNhapHang();
        }
        setDataToPanelTong();
    }

    private JPanel getPanelTieuChi(String name, JTextField tx, MoreButton b) {
        JPanel result = new JPanel();
        result.setBorder(BorderFactory.createTitledBorder(name));
        tx.setBorder(BorderFactory.createTitledBorder(" "));

        addDocumentListener(tx);

        result.add(tx);
        result.add(b);

        return result;
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                Boolean HoaDon_isSelected = (tabDoiTuongThongKe.getSelectedComponent() == plThongKeHoaDon);
                if (HoaDon_isSelected) {
                    onChangeThongKeBanHang();
                } else {
                    onChangeThongKeNhapHang();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }
        });
    }

    private ImageIcon getIcon(String filename) {
        return new ImageIcon(getClass().getResource("/assets/images/" + filename));
    }

    private void onChangeThongKeBanHang() {
        tbThongKeHoaDon.clear();

        int tongSLHoaDon = 0;
        int tongSLSanPham = 0;
        float tongTatCaTien = 0;

        String maspLoc = txSanPham.getText();
        String manvLoc = txNhanVien.getText();
        String makhLoc = txKhachHang.getText();
        

        MyCheckDate mcd = new MyCheckDate(txNgay1, txNgay2);

        

        tbKetQuaHoaDon.clear();
        
    }

    private void onChangeThongKeNhapHang() {
        tbThongKePhieuNhap.clear();

        int tongSLPhieuNhap = 0;
        int tongSLSanPham = 0;
        float tongTatCaTien = 0;

        String maspLoc = txSanPham.getText();
        String manvLoc = txNhanVien.getText();
        String manccLoc = txNhaCC.getText();

        
        MyCheckDate mcd = new MyCheckDate(txNgay1, txNgay2);


        tbKetQuaPhieuNhap.clear();
        
    }
    
    private JPanel getJPanelTong(String name, String iconName, int soluong, Color c) {
        JPanel result = new JPanel();
        result.setLayout(new BorderLayout());
        result.setPreferredSize(new Dimension(ThongKeForm.width / 4 - 15, 200));
        result.setBorder(BorderFactory.createLineBorder(c));
        
        // hinh anh
        JLabel lbIcon = new JLabel();
        lbIcon.setIcon(getIcon(iconName));
        result.add(lbIcon, BorderLayout.WEST);
        
        // tieu de
        JPanel plLeft = new JPanel();
        
        JLabel lbTieuDe = new JLabel(name, JLabel.CENTER);
        lbTieuDe.setPreferredSize(new Dimension(ThongKeForm.width / 4 - 100, 70));
        lbTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        plLeft.add(lbTieuDe);
        
        JLabel lbSoLuong = new JLabel(String.valueOf(soluong), JLabel.CENTER);
        lbSoLuong.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        lbSoLuong.setForeground(c);
        plLeft.add(lbSoLuong);
        
        result.add(plLeft, BorderLayout.CENTER);
        
        return result;
    }
}

class MyCheckDate {

    LocalDate fromDate = null;
    LocalDate toDate = null;
    String khoangTG = "";

    public MyCheckDate(JTextField txFrom, JTextField txTo) {
        try {
            fromDate = LocalDate.parse(txFrom.getText());
            txFrom.setForeground(Color.black);
            khoangTG += String.valueOf(fromDate);

        } catch (DateTimeParseException e) {
            txFrom.setForeground(Color.red);
            khoangTG += "Từ ngày đầu";
        }
        try {
            toDate = LocalDate.parse(txTo.getText());
            txTo.setForeground(Color.black);
            khoangTG += " đến " + String.valueOf(toDate);

        } catch (DateTimeParseException e) {
            txTo.setForeground(Color.red);
            khoangTG += " đến nay";
        }
    }

    public LocalDate getNgayTu() {
        return fromDate;
    }

    public LocalDate getNgayDen() {
        return toDate;
    }

    public String getKhoangTG() {
        return khoangTG;
    }
}
