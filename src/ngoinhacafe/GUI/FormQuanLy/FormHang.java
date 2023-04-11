/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngoinhacafe.GUI.FormQuanLy;


import ngoinhacafe.GUI.FormChon.ChonKhachHangForm;
import ngoinhacafe.GUI.FormChon.ChonKhuyenMaiForm;
import ngoinhacafe.GUI.FormChon.ChonNhaCungCapForm;
import ngoinhacafe.GUI.FormChon.ChonNhanVienForm;
import ngoinhacafe.GUI.FormChung.LoginForm;
import ngoinhacafe.GUI.FormChung.MyTable;
import ngoinhacafe.GUI.MyButton.MoreButton;
import ngoinhacafe.GUI.MyButton.RefreshButton;
import ngoinhacafe.GUI.MyButton.SuaButton;
import ngoinhacafe.GUI.MyButton.ThemButton;
import ngoinhacafe.GUI.MyButton.XoaButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author DELL
 */
public class FormHang extends JPanel {
    
    ChonSanPhamPanel target;

    public FormHang() {

    }

    public void addChiTiet(String masp, int soluong) {

    }
    
    public void setTarget(ChonSanPhamPanel target) {
        this.target = target;
    }
}

class PhieuNhapHang extends FormHang {

    JTextField txMaPhieuNhap = new JTextField(20);
    JTextField txNhanVien = new JTextField(17);
    JTextField txNhaCC = new JTextField(17);
    JTextField txNgayLap = new JTextField(20);
    JTextField txGioLap = new JTextField(20);
    JTextField txTongTien = new JTextField(20);

    MoreButton btnChonNhanVien = new MoreButton();
    MoreButton btnChonNhaCC = new MoreButton();

    MyTable tbChiTietPhieuNhap = new MyTable();
    XoaButton btnXoa = new XoaButton();
    SuaButton btnSua = new SuaButton();
    RefreshButton btnRefresh = new RefreshButton();

    JButton btnNhapHang = new JButton("Nhập hàng");
    JButton btnHuy = new JButton("Hủy");


    public PhieuNhapHang(int _x, int _y, int _width, int _height) {
        this.setBounds(_x, _y, _width, _height);
        this.setBackground(new Color(0, 0, 0));
        this.setLayout(new FlowLayout());

        // =============== panel input =================
        int plIP_height = 180;
        JPanel plInput = new JPanel();
        plInput.setPreferredSize(new Dimension(_width - 10, plIP_height));
        plInput.setBackground(new Color(240, 240, 240));
        plInput.setLayout(new FlowLayout());

        // btn
        btnChonNhaCC.setPreferredSize(new Dimension(30, 30));
        btnChonNhaCC.addActionListener((ae) -> {
            ChonNhaCungCapForm cncc = new ChonNhaCungCapForm(txNhaCC);
            cncc.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    String mancc = txNhaCC.getText();
                }
            });
        });

        btnChonNhanVien.setPreferredSize(new Dimension(30, 30));
        btnChonNhanVien.addActionListener((ae) -> {
            ChonNhanVienForm cnv = new ChonNhanVienForm(txNhanVien);
            cnv.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    String mavn = txNhanVien.getText();
                }
            });
        });
        btnChonNhanVien.setEnabled(false);

        // set border
        txMaPhieuNhap.setBorder(BorderFactory.createTitledBorder("Mã phiếu nhập:"));
        txNhanVien.setBorder(BorderFactory.createTitledBorder("Nhân viên:"));
        txNgayLap.setBorder(BorderFactory.createTitledBorder("Ngày lập:"));
        txGioLap.setBorder(BorderFactory.createTitledBorder("Giờ lập:"));
        txNhaCC.setBorder(BorderFactory.createTitledBorder("Nhà cung cấp:"));
        txTongTien.setBorder(BorderFactory.createTitledBorder("Tổng tiền (triệu vnd):"));

        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaPhieuNhap.setFont(f);
        txNhanVien.setFont(f);
        txNgayLap.setFont(f);
        txGioLap.setFont(f);
        txNhaCC.setFont(f);
        txMaPhieuNhap.setFont(f);
        txTongTien.setFont(f);

        // set Text
        

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                txNgayLap.setText(LocalDate.now().toString());
                txGioLap.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                if (txNhanVien.getText().equals("")
                        || txNhaCC.getText().equals("")
                        || txTongTien.getText().equals("")
                        || txTongTien.getText().equals("0")) {
                    btnNhapHang.setEnabled(false);
                } else {
                    btnNhapHang.setEnabled(true);
                }
            }
        }, 0, 1000);

        // set editable
        txMaPhieuNhap.setEditable(false);
        txNhanVien.setEditable(false);
        txNhaCC.setEditable(false);
        txNgayLap.setEditable(false);
        txGioLap.setEditable(false);
        txTongTien.setEditable(false);

        // add to panel
        plInput.add(txMaPhieuNhap);
        plInput.add(txTongTien);
        plInput.add(txNhaCC);
        plInput.add(btnChonNhaCC);
        plInput.add(txNhanVien);
        plInput.add(btnChonNhanVien);
        plInput.add(txNgayLap);
        plInput.add(txGioLap);

        this.add(plInput);

        // =============== panel các sản phẩm đã chọn ==================
        int plSP_height = 495;
        JPanel plSanPham = new JPanel();
        plSanPham.setPreferredSize(new Dimension(_width - 10, plSP_height));
        plSanPham.setBackground(new Color(250, 250, 29));
        plSanPham.setLayout(new BorderLayout());

        int plBtn_height = 50;
        JPanel plButtonChiTiet = new JPanel();
        plButtonChiTiet.setLayout(new FlowLayout(FlowLayout.CENTER));
        plButtonChiTiet.setBackground(new Color(220, 220, 220));
        plButtonChiTiet.setPreferredSize(new Dimension(_width - 10, plBtn_height));

        btnXoa.addActionListener((ae) -> {
            btnXoaOnClick();
        });
        btnSua.addActionListener((ae) -> {
            btnSuaOnClick();
        });
        btnRefresh.addActionListener((ae) -> {
            
        });

        plButtonChiTiet.add(btnXoa);
        plButtonChiTiet.add(btnSua);
        plButtonChiTiet.add(btnRefresh);

        tbChiTietPhieuNhap.setPreferredSize(new Dimension(_width - 10, plSP_height - plBtn_height));
        tbChiTietPhieuNhap.setHeaders(new String[]{"STT", "Mã", "Tên", "Số lượng", "Đơn giá", "Thành tiền"});
        tbChiTietPhieuNhap.setColumnsWidth(new double[]{1, 2, 3, 2.2, 2.5, 3});
        tbChiTietPhieuNhap.setAlignment(0, JLabel.CENTER);
        tbChiTietPhieuNhap.setAlignment(1, JLabel.CENTER);
        tbChiTietPhieuNhap.setAlignment(3, JLabel.CENTER);
        tbChiTietPhieuNhap.setAlignment(4, JLabel.RIGHT);
        tbChiTietPhieuNhap.setAlignment(5, JLabel.RIGHT);

        plSanPham.add(tbChiTietPhieuNhap, BorderLayout.CENTER);
        plSanPham.add(plButtonChiTiet, BorderLayout.SOUTH);

        this.add(plSanPham);

        // ============= panel Thanh toán ==============
        int plTT_height = _height - plIP_height - plSP_height - 20;
        JPanel plThanhToan = new JPanel();
        plThanhToan.setLayout(new FlowLayout(FlowLayout.RIGHT));
        plThanhToan.setPreferredSize(new Dimension(_width - 10, plTT_height));
        plThanhToan.setBackground(new Color(0, 0, 0));

        btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/assets/images/icons8_cancel_30px_1.png")));
        btnNhapHang.setIcon(new ImageIcon(this.getClass().getResource("/assets/images/icons8_us_dollar_30px.png")));

        btnHuy.addActionListener((ae) -> {
            btnHuyOnClick();
        });
        btnNhapHang.addActionListener((ae) -> {
            btnNhapHangOnClick();
        });

        plThanhToan.add(btnHuy);
        plThanhToan.add(btnNhapHang);

        this.add(plThanhToan);
    }

    private void btnHuyOnClick() {
        clear();
    }

    private void btnNhapHangOnClick() {
    
    }

    private void btnXoaOnClick() {
        int i = tbChiTietPhieuNhap.getTable().getSelectedRow();
        
    }

    private void btnSuaOnClick() {
        int i = tbChiTietPhieuNhap.getTable().getSelectedRow();
        
    }

    public void clear() {
        txNhaCC.setText("");
        txTongTien.setText("");
        
    }

    @Override
    public void addChiTiet(String masp, int soluong) {
        
    }
}

class HoaDonBanHang extends FormHang {

    JTextField txMaHoaDon = new JTextField(20);
    JTextField txNhanVien = new JTextField(17);
    JTextField txNgayLap = new JTextField(9);
    JTextField txGioLap = new JTextField(9);
    JTextField txKhachHang = new JTextField(17);
    JTextField txTongTien = new JTextField(20);
    JTextField txKhuyenMai = new JTextField(17);

    MoreButton btnChonNhanVien = new MoreButton();
    MoreButton btnChonKhachHang = new MoreButton();
    MoreButton btnChonKhuyenMai = new MoreButton();

    MyTable tbChiTietHoaDon = new MyTable();
    XoaButton btnXoa = new XoaButton();
    SuaButton btnSua = new SuaButton();
    RefreshButton btnRefresh = new RefreshButton();

    JButton btnThanhToan = new JButton("Thanh toán");
    JButton btnHuy = new JButton("Hủy");


    public HoaDonBanHang(int _x, int _y, int _width, int _height) {
        this.setBounds(_x, _y, _width, _height);
        this.setBackground(new Color(0, 0, 0));
        this.setLayout(new FlowLayout());

        // =============== panel input =================
        int plIP_height = 180;
        JPanel plInput = new JPanel();
        plInput.setPreferredSize(new Dimension(_width - 10, plIP_height));
        plInput.setBackground(new Color(240, 240, 240));
        plInput.setLayout(new FlowLayout());

        // btn
        btnChonKhachHang.setPreferredSize(new Dimension(30, 30));
        btnChonKhachHang.addActionListener((ae) -> {
            ChonKhachHangForm ckh = new ChonKhachHangForm(txKhachHang);
            ckh.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    String makh = txKhachHang.getText();
                    
                }
            });
        });

        btnChonNhanVien.setPreferredSize(new Dimension(30, 30));
        btnChonNhanVien.addActionListener((ae) -> {
            ChonNhanVienForm cnv = new ChonNhanVienForm(txNhanVien);
            cnv.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    String mavn = txNhanVien.getText();
                    
                }
            });
        });
        btnChonNhanVien.setEnabled(false);

        btnChonKhuyenMai.setPreferredSize(new Dimension(30, 30));
        btnChonKhuyenMai.addActionListener((ae) -> {
            ChonKhuyenMaiForm ckm = new ChonKhuyenMaiForm(txKhuyenMai);
            ckm.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    String makm = txKhuyenMai.getText();
                    
                }
            });
        });

        // set border
        txMaHoaDon.setBorder(BorderFactory.createTitledBorder("Mã hóa đơn:"));
        txNhanVien.setBorder(BorderFactory.createTitledBorder("Nhân viên:"));
        txNgayLap.setBorder(BorderFactory.createTitledBorder("Ngày lập:"));
        txGioLap.setBorder(BorderFactory.createTitledBorder("Giờ lập:"));
        txKhachHang.setBorder(BorderFactory.createTitledBorder("Khách hàng:"));
        txTongTien.setBorder(BorderFactory.createTitledBorder("Tổng tiền (triệu vnd):"));
        txKhuyenMai.setBorder(BorderFactory.createTitledBorder("Khuyến mãi:"));

        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaHoaDon.setFont(f);
        txNhanVien.setFont(f);
        txNgayLap.setFont(f);
        txGioLap.setFont(f);
        txKhachHang.setFont(f);
        txMaHoaDon.setFont(f);
        txTongTien.setFont(f);
        txKhuyenMai.setFont(f);

        // set Text\

        
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                txNgayLap.setText(LocalDate.now().toString());
                txGioLap.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                if (txNhanVien.getText().equals("")
                        || txKhachHang.getText().equals("")
                        || txKhuyenMai.getText().equals("")
//                        || dscthd.isEmpty()
                        ) {
                    btnThanhToan.setEnabled(false);
                } else {
                    btnThanhToan.setEnabled(true);
                }
            }
        }, 0, 1000);

        // set editable
        txMaHoaDon.setEditable(false);
        txNhanVien.setEditable(false);
        txKhachHang.setEditable(false);
        txNgayLap.setEditable(false);
        txGioLap.setEditable(false);
        txTongTien.setEditable(false);
        txKhuyenMai.setEditable(false);

        // add to panel
        plInput.add(txMaHoaDon);
        plInput.add(txTongTien);
        plInput.add(txKhachHang);
        plInput.add(btnChonKhachHang);
        plInput.add(txNhanVien);
        plInput.add(btnChonNhanVien);
        plInput.add(txNgayLap);
        plInput.add(txGioLap);
        plInput.add(txKhuyenMai);
        plInput.add(btnChonKhuyenMai);

        this.add(plInput);

        // =============== panel các sản phẩm đã chọn ==================
        int plSP_height = 495;
        JPanel plSanPham = new JPanel();
        plSanPham.setPreferredSize(new Dimension(_width - 10, plSP_height));
        plSanPham.setBackground(new Color(250, 250, 29));
        plSanPham.setLayout(new BorderLayout());

        int plBtn_height = 50;
        JPanel plButtonChiTiet = new JPanel();
        plButtonChiTiet.setLayout(new FlowLayout(FlowLayout.CENTER));
        plButtonChiTiet.setBackground(new Color(220, 220, 220));
        plButtonChiTiet.setPreferredSize(new Dimension(_width - 10, plBtn_height));

        btnXoa.addActionListener((ae) -> {
            btnXoaOnClick();
        });
        btnSua.addActionListener((ae) -> {
            btnSuaOnClick();
        });
        btnRefresh.addActionListener((ae) -> {
            
        });

        plButtonChiTiet.add(btnXoa);
        plButtonChiTiet.add(btnSua);
        plButtonChiTiet.add(btnRefresh);

        tbChiTietHoaDon.setPreferredSize(new Dimension(_width - 10, plSP_height - plBtn_height));
        tbChiTietHoaDon.setHeaders(new String[]{"STT", "Mã", "Tên", "Số lượng", "Đơn giá", "Thành tiền"});
        tbChiTietHoaDon.setColumnsWidth(new double[]{1, 2, 3, 2.2, 2.5, 3});
        tbChiTietHoaDon.setAlignment(0, JLabel.CENTER);
        tbChiTietHoaDon.setAlignment(1, JLabel.CENTER);
        tbChiTietHoaDon.setAlignment(3, JLabel.CENTER);
        tbChiTietHoaDon.setAlignment(4, JLabel.RIGHT);
        tbChiTietHoaDon.setAlignment(5, JLabel.RIGHT);

        plSanPham.add(tbChiTietHoaDon, BorderLayout.CENTER);
        plSanPham.add(plButtonChiTiet, BorderLayout.SOUTH);

        this.add(plSanPham);

        // ============= panel Thanh toán ==============
        int plTT_height = _height - plIP_height - plSP_height - 20;
        JPanel plThanhToan = new JPanel();
        plThanhToan.setLayout(new FlowLayout(FlowLayout.RIGHT));
        plThanhToan.setPreferredSize(new Dimension(_width - 10, plTT_height));
        plThanhToan.setBackground(new Color(0, 0, 0));

        btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/assets/images/icons8_cancel_30px_1.png")));
        btnThanhToan.setIcon(new ImageIcon(this.getClass().getResource("/assets/images/icons8_us_dollar_30px.png")));

        btnHuy.addActionListener((ae) -> {
            btnHuyOnClick();
        });
        btnThanhToan.addActionListener((ae) -> {
            btnThanhToanOnClick();
        });

        plThanhToan.add(btnHuy);
        plThanhToan.add(btnThanhToan);

        this.add(plThanhToan);
    }

    private void btnHuyOnClick() {
        clear();
    }

    private void btnThanhToanOnClick() {
    }

    private void btnXoaOnClick() {
        int i = tbChiTietHoaDon.getTable().getSelectedRow();
        
    }

    private void btnSuaOnClick() {
        int i = tbChiTietHoaDon.getTable().getSelectedRow();
        
    }

    public void clear() {
        txKhachHang.setText("");
        txTongTien.setText("");
        txKhuyenMai.setText("");
        
    }

    @Override
    public void addChiTiet(String masp, int soluong) {
    
    }
    
}

class ChonSanPhamPanel extends JPanel {

    MyTable tbSanPham = new MyTable();
    JTextField txTimKiem = new JTextField(30);

    JLabel lbImage = new JLabel();
    JTextField txMaSP = new JTextField(12);
    JTextField txLoaiSP = new JTextField(12);
    JTextField txTenSP = new JTextField(12);
    JTextField txDonGia = new JTextField(12);
    JTextField txSoLuong = new JTextField(7);

    RefreshButton btnRefresh = new RefreshButton();
    ThemButton btnThem = new ThemButton();

    FormHang target = new FormHang();

    public ChonSanPhamPanel(int _x, int _y, int _width, int _height) {
        this.setBounds(_x, _y, _width, _height);
        this.setBackground(new Color(59, 68, 75));
        this.setLayout(new BorderLayout());

        // panel hiển thị sản phẩm
        int plSP_height = _height - 300;
        JPanel plSanPham = new JPanel();
        plSanPham.setPreferredSize(new Dimension(_width - 10, plSP_height));
        plSanPham.setBackground(new Color(49, 49, 49));
        plSanPham.setLayout(new BorderLayout());

        JPanel plTimKiem = new JPanel();
        txTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTimKiem.setHorizontalAlignment(JLabel.CENTER);
        addDocumentListener(txTimKiem);
        plTimKiem.add(txTimKiem);
        btnRefresh.addActionListener((ae) -> {
            refreshTable();
        });
        plTimKiem.add(btnRefresh);
        plSanPham.add(plTimKiem, BorderLayout.NORTH);

        tbSanPham.setHeaders(new String[]{"Mã", "Loại", "Tên", "Đơn giá", "Số lượng"});
        tbSanPham.setColumnsWidth(new double[]{.5, .5, 3, 1, .5});
        tbSanPham.setAlignment(3, JLabel.RIGHT);
        tbSanPham.setAlignment(4, JLabel.RIGHT);
        tbSanPham.setupSort();
        plSanPham.add(tbSanPham, BorderLayout.CENTER);

        this.add(plSanPham, BorderLayout.CENTER);

        // =========== panel chi tiết sản phẩm chọn ================
        JPanel plChiTiet = new JPanel();
        plChiTiet.setPreferredSize(new Dimension(_width - 10, 258));
        plChiTiet.setBackground(new Color(240, 240, 240));
        plChiTiet.setLayout(new BorderLayout());

        lbImage.setBackground(Color.yellow);
        lbImage.setPreferredSize(new Dimension(200, 200));
        plChiTiet.add(lbImage, BorderLayout.WEST);

        JPanel plTextField = new JPanel();
        plTextField.setLayout(new FlowLayout());
        plTextField.setBackground(new Color(240, 240, 240));
        // border
        txMaSP.setBorder(BorderFactory.createTitledBorder("Mã sản phẩm"));
        txLoaiSP.setBorder(BorderFactory.createTitledBorder("Loại sản phẩm"));
        txTenSP.setBorder(BorderFactory.createTitledBorder("Tên sản phẩm"));
        txDonGia.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        txSoLuong.setBorder(BorderFactory.createTitledBorder("Số lượng"));
        // disable
        txMaSP.setEditable(false);
        txLoaiSP.setEditable(false);
        txTenSP.setEditable(false);
        txDonGia.setEditable(false);
        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaSP.setFont(f);
        txLoaiSP.setFont(f);
        txTenSP.setFont(f);
        txDonGia.setFont(f);
        txSoLuong.setFont(f);
        // add to panel
        plTextField.add(txMaSP);
        plTextField.add(txLoaiSP);
        plTextField.add(txTenSP);
        plTextField.add(txDonGia);
        plTextField.add(txSoLuong);

        btnThem.addActionListener((ae) -> {
            try {
                String masp = txMaSP.getText();
                int soluong = Integer.parseInt(txSoLuong.getText());
                if (soluong > 0) {
                    this.target.addChiTiet(masp, soluong);
                } else {
                    JOptionPane.showMessageDialog(txSoLuong, "Số lượng phải là số dương!");
                    txSoLuong.requestFocus();
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(txSoLuong, "Số lượng phải là số nguyên!");
                txSoLuong.requestFocus();
            }
        });

        plChiTiet.add(plTextField, BorderLayout.CENTER);
        plChiTiet.add(btnThem, BorderLayout.SOUTH);

        this.add(plChiTiet, BorderLayout.SOUTH);

        // event
        tbSanPham.getTable().addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
            @Override
            public void mouseReleased(MouseEvent me) {
                String masp = getSelectedSanPham(0);
                if (masp != null) {
                    showInfo(masp, 1);
                }
            }
        });

        refreshAll();
    }

    public void setTarget(FormHang target) {
        this.target = target;
    }

    public void refreshTable() {
        
    }

    public void refreshAll() {
        refreshTable();
        txMaSP.setText("");
        txLoaiSP.setText("");
        txTenSP.setText("");
        txDonGia.setText("");
        txSoLuong.setText("");
        lbImage.setIcon(null);
    }

    public void showInfo(String masp, int soluong) {
        
    }

    private void addDocumentListener(JTextField tx) {
        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        tx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txSearchOnChange();
            }
        });
    }

    public void txSearchOnChange() {
        
    }

    public String getSelectedSanPham(int col) {
        int i = tbSanPham.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = tbSanPham.getTable().convertRowIndexToModel(i);
            return tbSanPham.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }
    
}
