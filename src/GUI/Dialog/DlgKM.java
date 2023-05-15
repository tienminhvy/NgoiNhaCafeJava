package GUI.Dialog;

import BUS.KhuyenMaiBUS;
import DTO.KhuyenMai;
import CustomFuncs.CustomDialog;
import CustomFuncs.Table;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class DlgKM extends JDialog {

    public static KhuyenMai kmTimDuoc = null;
    private KhuyenMaiBUS KhuyenMaiBUS = new KhuyenMaiBUS();
    private int tongTien = 0;

    public DlgKM(int tongTien) {
        this.tongTien = tongTien;
        addControls();
        addEvents();

        this.setSize(750, 500);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblMaKM;
    private DefaultTableModel dtmKM;
    private JButton btnChon, btnThoat;

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Từ khoá tìm");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
//        pnTop.add(lblTuKhoa);
//        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmKM = new DefaultTableModel();
        dtmKM.addColumn("Mã");
        dtmKM.addColumn("Chương trình");
        dtmKM.addColumn("% KM");
        dtmKM.addColumn("Điều kiện");
        dtmKM.addColumn("Bắt đầu");
        dtmKM.addColumn("Kết thúc");
        dtmKM.addColumn("Trạng thái");
        tblMaKM = new Table(dtmKM);
        JScrollPane scrMaGiam = new JScrollPane(tblMaKM);
        pnTable.add(scrMaGiam, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnThoat = new JButton("Thoát");
        btnChon.setFont(font);
        btnThoat.setFont(font);
        pnButton.add(btnChon);
        pnButton.add(btnThoat);
        con.add(pnButton, BorderLayout.SOUTH);

        TableColumnModel columnModelBanHang = tblMaKM.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(56);
        columnModelBanHang.getColumn(1).setPreferredWidth(213);
        columnModelBanHang.getColumn(2).setPreferredWidth(30);
        columnModelBanHang.getColumn(3).setPreferredWidth(62);
        columnModelBanHang.getColumn(4).setPreferredWidth(58);
        columnModelBanHang.getColumn(5).setPreferredWidth(61);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tblMaKM.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        tblMaKM.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        btnChon.setPreferredSize(new Dimension(120, 40));
        btnThoat.setPreferredSize(btnChon.getPreferredSize());

        loadDataLenTable();
    }

    private void addEvents() {
        txtTuKhoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenTable(txtTuKhoa.getText());
            }
        });

        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonMaGiam();
            }
        });

        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThoat();
            }
        });
    }

    private void xuLyChonMaGiam() {
        int row = tblMaKM.getSelectedRow();
        if (row > -1) {
            if (tblMaKM.getValueAt(row, 6).equals("Không hiệu lực")) {
                new CustomDialog("Khuyến mãi này đã hết hiệu lực!", CustomDialog.ERROR_DIALOG);
                loadDataLenTable();
                return;
            }
            int ma = Integer.parseInt(tblMaKM.getValueAt(row, 0) + "");
            String ten = tblMaKM.getValueAt(row, 1) + "";
            int phanTram = Integer.parseInt(tblMaKM.getValueAt(row, 2) + "");
            String dieuKienst = tblMaKM.getValueAt(row, 3) + "";
            dieuKienst = dieuKienst.replace(">", "");
            dieuKienst = dieuKienst.replace(",", "");
            int dieuKien = Integer.parseInt(dieuKienst);

            if(dieuKien > tongTien) {
                new CustomDialog("Không đủ điều kiện áp dụng khuyến mãi này!", CustomDialog.ERROR_DIALOG);
                return;
            }

            String ngayBD = tblMaKM.getValueAt(row, 4) + "";
            String ngayKT = tblMaKM.getValueAt(row, 5) + "";

            kmTimDuoc = new KhuyenMai();
            kmTimDuoc.setMaKM(ma);
            kmTimDuoc.setTenKM(ten);
            kmTimDuoc.setPhanTramKM(phanTram);
            kmTimDuoc.setDieuKien(dieuKien);
            kmTimDuoc.setNgayBD(ngayBD);
            kmTimDuoc.setNgayKT(ngayKT);
        }
        xuLyThoat();
    }

    private void xuLyThoat() {
        dispose();
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DecimalFormat dcf = new DecimalFormat(">###,###");
    
    private void loadDataLenTable() {
        dtmKM.setRowCount(0);
        KhuyenMaiBUS.docDanhSach();
        ArrayList<KhuyenMai> dskm = KhuyenMaiBUS.getDanhSachKhuyenMai();
        Date ngayBDdt, ngayKTdt;
        for (KhuyenMai km : dskm) {
            Vector vec = new Vector();
            vec.add(km.getMaKM());
            vec.add(km.getTenKM());
            vec.add(km.getPhanTramKM());
            vec.add(dcf.format(km.getDieuKien()));
            vec.add(km.getNgayBD());
            vec.add(km.getNgayKT());

            Date now = new Date();
            
            try {
                ngayBDdt = sdf.parse(km.getNgayBD());
                ngayKTdt = sdf.parse(km.getNgayKT());
                if (ngayBDdt.before(now) && ngayKTdt.after(now)) {
                    vec.add("Còn hiệu lực");
                } else {
                    vec.add("Hết hiệu lực");
                }
            } catch (Exception ex) {
                vec.add("Không xác định");
            }
            dtmKM.addRow(vec);
        }
    }

    private void loadDataLenTable(String tuKhoa) {
        TableColumnModel columnModelBanHang = tblMaKM.getColumnModel();

    }

}
