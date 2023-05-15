package GUI;

import GUI.Dialog.*;
import CustomFuncs.CustomDialog;
import CustomFuncs.TransparentPanel;
import CustomFuncs.Table;
import CustomFuncs.ImagePanel;
import static Main.Main.changLNF;

import BUS.NhanVienBUS;
import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.NhanVien;
import DTO.PhanQuyen;
import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PnQuanLyNhanVienGUI extends JPanel {

    public PnQuanLyNhanVienGUI() {
        changLNF("Windows");
        addControlsNhanVien();
        addEventsNhanVien();
        addEventsPhanQuyen();
    }

    private PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    JDateChooser dateNgaySinh;
    JTextField txtMaNV, txtTen, txtSDT, txtTimNV, txtDiaChi;
    Table tblNhanVien;
    DefaultTableModel dtmNhanVien;
    JButton btnReset, btnThemNV, btnSuaNV, btnXoaNV, btnTimNV, btnCapTaiKhoan, btnResetMatKhau, btnKhoaTaiKhoan;

    private void addControlsNhanVien() {
        this.setLayout(new BorderLayout());
        int w = 1030;
        int h = 844;
        Font font = new Font("", Font.PLAIN, 20);

        /*
        =========================================================================
                                    PANEL NHÂN VIÊN
        =========================================================================
         */
        JPanel pnNhanVien = new TransparentPanel();
        pnNhanVien.setLayout(new BoxLayout(pnNhanVien, BoxLayout.Y_AXIS));

        JPanel pnTopNV = new TransparentPanel();
        pnTopNV.setLayout(new BoxLayout(pnTopNV, BoxLayout.Y_AXIS));

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ NHÂN VIÊN</h1></html>");
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        pnTopNV.add(pnTitle);
        //==========
        JPanel pnText = new TransparentPanel();
        pnText.setLayout(new BoxLayout(pnText, BoxLayout.Y_AXIS));

        txtMaNV = new JTextField(25);
        txtMaNV.setEditable(false);
        txtTen = new JTextField(25);
        dateNgaySinh = new JDateChooser();
        dateNgaySinh.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        dateNgaySinh.getCalendarButton().setPreferredSize(new Dimension(32, 32));

        txtDiaChi = new JTextField(25);
        txtSDT = new JTextField(25);

        txtMaNV.setFont(font);
        txtTen.setFont(font);
        dateNgaySinh.setFont(font);
        txtDiaChi.setFont(font);
        txtSDT.setFont(font);

        JLabel lblMa, lblTen, lblNgaySinh, lblDiaChi, lblChucVu;

        lblMa = new JLabel("Mã Nhân viên");
        lblTen = new JLabel("Tên");
        lblNgaySinh = new JLabel("Ngày sinh");
        lblDiaChi = new JLabel("Địa chỉ");
        lblChucVu = new JLabel("SĐT");

        lblMa.setFont(font);
        lblTen.setFont(font);
        lblNgaySinh.setFont(font);
        lblDiaChi.setFont(font);
        lblChucVu.setFont(font);

        JPanel pnMa = new TransparentPanel();
        pnMa.add(lblMa);
        pnMa.add(txtMaNV);
        pnText.add(pnMa);

        JPanel pnHo = new TransparentPanel();
        pnHo.add(lblTen);
        pnHo.add(txtTen);
        pnText.add(pnHo);

        JPanel pnTen = new TransparentPanel();
        pnTen.add(lblNgaySinh);
        pnTen.add(dateNgaySinh);
        pnText.add(pnTen);

        JPanel pnGioiTinh = new TransparentPanel();
        pnGioiTinh.add(lblDiaChi);
        pnGioiTinh.add(txtDiaChi);
        pnText.add(pnGioiTinh);

        JPanel pnChucVu = new TransparentPanel();
        pnChucVu.add(lblChucVu);
        pnChucVu.add(txtSDT);
        pnText.add(pnChucVu);

        Dimension lblSize = new Dimension(150, 26);
        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblNgaySinh.setPreferredSize(lblSize);
        lblDiaChi.setPreferredSize(lblSize);
        lblChucVu.setPreferredSize(lblSize);
        Dimension txtSize = new Dimension(400, 32);
        txtMaNV.setPreferredSize(txtSize);
        txtTen.setPreferredSize(txtSize);
        txtDiaChi.setPreferredSize(txtSize);
        dateNgaySinh.setPreferredSize(new Dimension(440, 32));
        txtSDT.setPreferredSize(txtSize);
        
        pnTopNV.add(pnText);

        //==========
        JPanel pnTimNV = new TransparentPanel();
        JLabel lblTim = new JLabel("Từ khoá tìm");
        lblTim.setFont(font);
        txtTimNV = new JTextField(25);
        txtTimNV.setFont(font);
        pnTimNV.add(lblTim);
        pnTimNV.add(txtTimNV);
        pnTopNV.add(pnTimNV);
        lblTim.setPreferredSize(lblSize);
        //==========
        JPanel pnButton = new TransparentPanel();

        btnThemNV = new JButton("Thêm");
        btnSuaNV = new JButton("Lưu");
        btnXoaNV = new JButton("Xoá");
        btnTimNV = new JButton("Tìm kiếm");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThemNV.setFont(fontButton);
        btnSuaNV.setFont(fontButton);
        btnXoaNV.setFont(fontButton);
        btnTimNV.setFont(fontButton);

        btnThemNV.setIcon(new ImageIcon("image/add-icon.png"));
        btnSuaNV.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoaNV.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTimNV.setIcon(new ImageIcon("image/Search-icon.png"));

        pnButton.add(btnThemNV);
        pnButton.add(btnSuaNV);
        pnButton.add(btnXoaNV);
        pnButton.add(btnTimNV);

        Dimension btnSize = btnTimNV.getPreferredSize();
        btnThemNV.setPreferredSize(btnSize);
        btnSuaNV.setPreferredSize(btnSize);
        btnXoaNV.setPreferredSize(btnSize);
        btnTimNV.setPreferredSize(btnSize);

        JPanel pnButton2 = new TransparentPanel();
        btnCapTaiKhoan = new JButton("Cấp tài khoản");
        btnResetMatKhau = new JButton("Mật khẩu/Quyền");
        btnKhoaTaiKhoan = new JButton("Khoá tài khoản");
        btnCapTaiKhoan.setIcon(new ImageIcon("image/icons8_man_with_key_32px.png"));
        btnResetMatKhau.setIcon(new ImageIcon("image/icons8_password_reset_32px.png"));
        btnKhoaTaiKhoan.setIcon(new ImageIcon("image/icons8_denied_32px.png"));
        btnCapTaiKhoan.setFont(fontButton);
        btnResetMatKhau.setFont(fontButton);
        btnKhoaTaiKhoan.setFont(fontButton);
        pnButton2.add(btnCapTaiKhoan);
        pnButton2.add(btnResetMatKhau);
        pnButton2.add(btnKhoaTaiKhoan);

        pnNhanVien.add(pnTopNV);
        pnNhanVien.add(pnButton);
        pnNhanVien.add(pnButton2);
        //===================TABLE NHÂN VIÊN=====================
        JPanel pnTableNhanVien = new TransparentPanel();
        pnTableNhanVien.setLayout(new BorderLayout());
        dtmNhanVien = new DefaultTableModel();
        dtmNhanVien.addColumn("Mã NV");
        dtmNhanVien.addColumn("Tên");
        dtmNhanVien.addColumn("Ngày tạo");
        dtmNhanVien.addColumn("Địa chỉ");
        dtmNhanVien.addColumn("SĐT");
        dtmNhanVien.addColumn("Tài khoản");
        tblNhanVien = new Table(dtmNhanVien);

        tblNhanVien.setDefaultEditor(Object.class, null);
        tblNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrTblNhanVien = new JScrollPane(tblNhanVien);
        pnTableNhanVien.add(scrTblNhanVien, BorderLayout.CENTER);
        pnNhanVien.add(scrTblNhanVien);
        /*
        =========================================================================
                                    PANEL QUYỀN
        =========================================================================
         */
        JPanel pnPhanQuyen = new TransparentPanel();
        pnPhanQuyen.setLayout(new BoxLayout(pnPhanQuyen, BoxLayout.Y_AXIS));

        JPanel pnTitlePhanQuyen = new TransparentPanel();
        JLabel lblTitlePhanQuyen = new JLabel("<html><h1>Quản lý phân quyền</h1></html>");
        pnTitlePhanQuyen.add(lblTitlePhanQuyen);
        pnPhanQuyen.add(pnTitlePhanQuyen);

        JPanel pnCmbQuyen = new TransparentPanel();
        JLabel lblCmbQuyen = new JLabel("<html><b>Nhóm quyền:</b></html>");
        lblCmbQuyen.setFont(font);
        cmbQuyen = new JComboBox<String>();
        cmbQuyen.setFont(font);
        pnCmbQuyen.add(lblCmbQuyen);
        pnCmbQuyen.add(cmbQuyen);
        pnPhanQuyen.add(pnCmbQuyen);

        JPanel pnCheckNhapHang = new TransparentPanel();
        ckbNhapHang = new JCheckBox("Quản lý nhập hàng.");
        ckbNhapHang.setFont(font);
        pnCheckNhapHang.add(ckbNhapHang);
        pnPhanQuyen.add(pnCheckNhapHang);

        JPanel pnCheckQLSanPham = new TransparentPanel();
        ckbQLSanPham = new JCheckBox("Quản lý sản phẩm.");
        ckbQLSanPham.setFont(font);
        pnCheckQLSanPham.add(ckbQLSanPham);
        pnPhanQuyen.add(pnCheckQLSanPham);

        JPanel pnCheckQLNhanVien = new TransparentPanel();
        ckbQLNhanVien = new JCheckBox("Quản lý nhân viên.");
        ckbQLNhanVien.setFont(font);
        pnCheckQLNhanVien.add(ckbQLNhanVien);
        pnPhanQuyen.add(pnCheckQLNhanVien);

        JPanel pnCheckQLKhachHang = new TransparentPanel();
        ckbQLKhachHang = new JCheckBox("Quản lý khách hàng.");
        ckbQLKhachHang.setFont(font);
        pnCheckQLKhachHang.add(ckbQLKhachHang);
        pnPhanQuyen.add(pnCheckQLKhachHang);

        JPanel pnCheckQLThongKe = new TransparentPanel();
        ckbThongKe = new JCheckBox("Quản lý thống kê.");
        ckbThongKe.setFont(font);
        pnCheckQLThongKe.add(ckbThongKe);
        pnPhanQuyen.add(pnCheckQLThongKe);

        Dimension ckbSize = ckbQLKhachHang.getPreferredSize();
        cmbQuyen.setPreferredSize(ckbSize);
        ckbNhapHang.setPreferredSize(ckbSize);
        ckbQLSanPham.setPreferredSize(ckbSize);
        ckbQLNhanVien.setPreferredSize(ckbSize);
        ckbQLKhachHang.setPreferredSize(ckbSize);
        ckbThongKe.setPreferredSize(ckbSize);

        JPanel pnButtonQuyen = new TransparentPanel();
        btnThemQuyen = new JButton("Thêm quyền");
        btnSuaQuyen = new JButton("Sửa quyền");
        btnXoaQuyen = new JButton("Xoá quyền");
        btnThemQuyen.setFont(font);
        btnSuaQuyen.setFont(font);
        btnXoaQuyen.setFont(font);
        btnThemQuyen.setIcon(new ImageIcon("image/add-icon.png"));
        btnSuaQuyen.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoaQuyen.setIcon(new ImageIcon("image/delete-icon.png"));
        pnButtonQuyen.add(btnThemQuyen);
        pnButtonQuyen.add(btnSuaQuyen);
        pnButtonQuyen.add(btnXoaQuyen);
        btnSuaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());
        btnXoaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());
        pnPhanQuyen.add(pnButtonQuyen);

        /*
        =========================================================================
                                    PANEL TABBED
        =========================================================================
         */
        JTabbedPane tp = new JTabbedPane();
        tp.add("Nhân viên", pnNhanVien);
        tp.add("Quyền", pnPhanQuyen);
        tp.setFont(font);

        loadDataTblNhanVien(null);

        loadDataCmbQuyen();
        this.add(tp);
        this.repaint();

    }

    JComboBox<String> cmbQuyen;
    JCheckBox ckbNhapHang, ckbQLSanPham, ckbQLNhanVien, ckbQLKhachHang, ckbThongKe;
    JButton btnSuaQuyen, btnThemQuyen, btnXoaQuyen;

    private void addEventsNhanVien() {
        tblNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblNhanVien();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        txtTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemNhanVien();
            }
        });

        btnTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemNhanVien();
            }
        });

        btnThemNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemNhanVien();
            }
        });

        btnSuaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaNhanVien();
            }
        });

        btnXoaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaNhanVien();
            }
        });

        btnCapTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyCapTaiKhoan();
            }
        });

        btnResetMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyResetMatKhau();
            }
        });

        btnKhoaTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyKhoaTaiKhoan();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTblNhanVien(null);
                txtMaNV.setText("");
                txtTen.setText("");
                dateNgaySinh.setCalendar(null);

                txtSDT.setText("");
                txtTimNV.setText("");
                txtDiaChi.setText("");
            }
        });
    }

    private void addEventsPhanQuyen() {
        cmbQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyHienThiChiTietQuyen();
            }
        });
        btnThemQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemQuyen();
            }
        });
        btnSuaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaQuyen();
            }
        });
        btnXoaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaQuyen();
            }
        });
    }

    private void xuLyXoaQuyen() {
        if (cmbQuyen.getSelectedIndex() < 1) {
            new CustomDialog("Chưa chọn nhóm quyền để xoá!", CustomDialog.ERROR_DIALOG);
            return;
        }
        CustomDialog dlg = new CustomDialog("Bạn có chắc chắn muốn xoá?", CustomDialog.WARNING_DIALOG);
        if (dlg.getAction() == CustomDialog.CANCEL_OPTION) {
            return;
        }
        String tenQuyen = cmbQuyen.getSelectedItem() + "";
        boolean flag = phanQuyenBUS.xoaQuyen(tenQuyen);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLyThemQuyen() {
        String tenQuyen = JOptionPane.showInputDialog("Nhập tên quyền");

        boolean flag = phanQuyenBUS.themQuyen(tenQuyen);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLySuaQuyen() {
        if (cmbQuyen.getSelectedIndex() < 1) {
            new CustomDialog("Chưa chọn nhóm quyền để sửa!", CustomDialog.ERROR_DIALOG);
            return;
        }
        String tenQuyen = cmbQuyen.getSelectedItem() + "";
        int nhapHang = ckbNhapHang.isSelected() ? 1 : 0;
        int sanPham = ckbQLSanPham.isSelected() ? 1 : 0;
        int nhanVien = ckbQLNhanVien.isSelected() ? 1 : 0;
        int khachHang = ckbQLKhachHang.isSelected() ? 1 : 0;
        int thongKe = ckbThongKe.isSelected() ? 1 : 0;

        boolean flag = phanQuyenBUS.suaQuyen(0, tenQuyen, nhapHang, sanPham, nhanVien, khachHang, thongKe);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLyHienThiChiTietQuyen() {
        ArrayList<PhanQuyen> dsq = phanQuyenBUS.getListQuyen();
        PhanQuyen phanQuyen = new PhanQuyen();
        for (PhanQuyen pq : dsq) {
            if (pq.getQuyen().equals(cmbQuyen.getSelectedItem())) {
                phanQuyen.setQuyen(pq.getQuyen());
                phanQuyen.setNhapHang(pq.getNhapHang());
                phanQuyen.setQlSanPham(pq.getQlSanPham());
                phanQuyen.setQlNhanVien(pq.getQlNhanVien());
                phanQuyen.setQlKhachHang(pq.getQlKhachHang());
                phanQuyen.setThongKe(pq.getThongKe());
                break;
            }
        }
        ckbNhapHang.setSelected(false);
        ckbQLSanPham.setSelected(false);
        ckbQLNhanVien.setSelected(false);
        ckbQLKhachHang.setSelected(false);
        ckbThongKe.setSelected(false);
        if (phanQuyen.getNhapHang() == 1) {
            ckbNhapHang.setSelected(true);
        }
        if (phanQuyen.getQlSanPham() == 1) {
            ckbQLSanPham.setSelected(true);
        }
        if (phanQuyen.getQlNhanVien() == 1) {
            ckbQLNhanVien.setSelected(true);
        }
        if (phanQuyen.getQlKhachHang() == 1) {
            ckbQLKhachHang.setSelected(true);
        }
        if (phanQuyen.getThongKe() == 1) {
            ckbThongKe.setSelected(true);
        }
    }

    private void loadDataCmbQuyen() {
        phanQuyenBUS.docDanhSachQuyen();
        ArrayList<PhanQuyen> dsq = phanQuyenBUS.getListQuyen();
        cmbQuyen.removeAllItems();
        cmbQuyen.addItem("Chọn quyền");
        for (PhanQuyen pq : dsq) {
            cmbQuyen.addItem(pq.getQuyen());
        }
    }

    private void xuLyResetMatKhau() {
        String maNV = txtMaNV.getText();
        if (maNV.trim().equals("")) {
            new CustomDialog("Hãy chọn nhân viên!", CustomDialog.ERROR_DIALOG);
            return;
        }
        try {
            NhanVien nv = nhanVienBUS.getNhanVien(Integer.parseInt(maNV));
            if (nv.getMaTK() == 0) {
                new CustomDialog("Nhân viên này chưa có tài khoản!", CustomDialog.ERROR_DIALOG);
                return;
            }
            DlgQuyen_MatKhau dialog = new DlgQuyen_MatKhau(nv.getMaTK() + "");
            dialog.setVisible(true);
        } catch (Exception e) {
            new CustomDialog("Lỗi khi tìm nhân viên!", CustomDialog.ERROR_DIALOG);
            System.out.println(e);
        }
    }

    private void xuLyCapTaiKhoan() {
        if (txtMaNV.getText().trim().equals("")) {
            new CustomDialog("Hãy chọn nhân viên!", CustomDialog.ERROR_DIALOG);
            return;
        }
        DlgCapTaiKhoan dialog = new DlgCapTaiKhoan(txtMaNV.getText());
        dialog.setVisible(true);
        loadDataTblNhanVien(null);
    }

    private void xuLyKhoaTaiKhoan() {
        TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        NhanVienBUS nhanVienBUS = new NhanVienBUS();
        int maTK = nhanVienBUS.getNhanVien(Integer.parseInt(txtMaNV.getText())).getMaTK();
        taiKhoanBUS.khoaTaiKhoan(maTK);
        loadDataTblNhanVien(null);
    }

    private void xuLyXoaNhanVien() {
        String ma = txtMaNV.getText();
        boolean flag = nhanVienBUS.xoaNhanVien(ma);
        if (flag) {
            nhanVienBUS.docDanhSach();
            loadDataTblNhanVien(null);
        }
    }

    private void xuLySuaNhanVien() {
        if (txtDiaChi.getText().length() == 0) {
            new CustomDialog("Hãy nhập địa chỉ!", CustomDialog.ERROR_DIALOG);
            return;
        }
        String ma = txtMaNV.getText();
        String ten = txtTen.getText();
        Date ngaySinh = dateNgaySinh.getDate();

        String diaChi = txtDiaChi.getText();
        String sdt = txtSDT.getText();
        if (nhanVienBUS.updateNhanVien(ma, ten, ngaySinh, diaChi, sdt)) {
            nhanVienBUS.docDanhSach();
            loadDataTblNhanVien(null);
        }
    }

    private void xuLyThemNhanVien() {
        if (txtDiaChi.getText().length() == 0) {
            new CustomDialog("Hãy nhập địa chỉ!", CustomDialog.ERROR_DIALOG);
            return;
        }
        String ten = txtTen.getText();
        Date ngaySinh = dateNgaySinh.getDate();
        String diaChi = txtDiaChi.getText();
        String sdt = txtSDT.getText();
        if (nhanVienBUS.themNhanVien(ten, ngaySinh, diaChi, sdt)) {
            nhanVienBUS.docDanhSach();
            loadDataTblNhanVien(null);
        }
    }

    private void xuLyTimKiemNhanVien() {
        ArrayList<NhanVien> dsnv = nhanVienBUS.timNhanVien(txtTimNV.getText());
        dtmNhanVien.setRowCount(0);
        loadDataTblNhanVien(dsnv);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private void xuLyClickTblNhanVien() {
        int row = tblNhanVien.getSelectedRow();
        if (row > -1) {
            txtMaNV.setText(tblNhanVien.getValueAt(row, 0) + "");
            txtTen.setText(tblNhanVien.getValueAt(row, 1) + "");
            Date d;
            try {
                d = sdf.parse(tblNhanVien.getValueAt(row, 2) + "");
                dateNgaySinh.setDate(d);
            } catch (Exception ex) {
                dateNgaySinh.setDate(null);
            }
            txtSDT.setText(tblNhanVien.getValueAt(row, 4) + "");

            txtDiaChi.setText(tblNhanVien.getValueAt(row, 3) + "");
        }
    }

    private void loadDataTblNhanVien(ArrayList<NhanVien> dsnv) {
        dtmNhanVien.setRowCount(0);
        if (dsnv == null) {
            dsnv = nhanVienBUS.getDanhSachNhanVien();
        }

        for (NhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getTen());
            try {
                Date d = sdf.parse(nv.getNgaySinh());
                vec.add(sdf.format(d));
            } catch (Exception ex) {

                vec.add(null);
            }
            vec.add(nv.getDiaChi());
            vec.add(nv.getSdt());
            int trangThai = taiKhoanBUS.getTrangThai(nv.getMaTK() + "");

            if (nv.getMaTK() == 0) {
                vec.add("Không khả dụng");
            } else if (trangThai == 0) {
                vec.add("Bị khoá");
            } else if (trangThai == 1) {
                vec.add("Khả dụng");
            }

            dtmNhanVien.addRow(vec);
        }
    }

    TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();

}
