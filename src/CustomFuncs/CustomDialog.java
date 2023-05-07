package CustomFuncs;

import GUI.Color.ColorDesign;
import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomDialog extends JDialog {

    private String content;
    private int type;
    public final static int ERROR_DIALOG = 1;
    public final static int SUCCESS_DIALOG = 2;
    public final static int INFO_DIALOG = 3;
    public final static int WARNING_DIALOG = 4;

    public CustomDialog(String content, int type) {
        Main.changLNF("Windows");
        this.content = content;
        this.type = type;
        addControls();
        addEvents();
        showWindow();
    }

    JPanel pnMain, pnTop, pnBottom, pnButton;
    JLabel lblIcon, lblContent, lblClose;
    JButton btnOK, btnCancel;
    final ImageIcon iconError = new ImageIcon("image/icons8_cancel_70px.png");
    final ImageIcon iconSuccess = new ImageIcon("image/icons8_checkmark_70px.png");
    final ImageIcon iconInfo = new ImageIcon("image/icons8_info_70px.png");
    final ImageIcon iconWarning = new ImageIcon("image/icons8_warning_shield_70px.png");

    private void addControls() {
        Container con = getContentPane();
        this.setForeground(Color.black);
        pnMain = new JPanel();
        pnTop = new JPanel();
        pnBottom = new JPanel();
        pnButton = new JPanel();
        lblIcon = new JLabel();
        lblContent = new JLabel(content);
        btnOK = new JButton("Đã rõ");
        btnCancel = new JButton("Hủy");

        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnTop.setLayout(new FlowLayout());
        pnBottom.setLayout(new FlowLayout());
        pnButton.setLayout(new FlowLayout());

//        pnMain.setBackground(Color.WHITE);
//        pnTop.setBackground(Color.WHITE);
//        pnBottom.setBackground(Color.WHITE);
//        pnButton.setBackground(Color.WHITE);

        lblContent.setFont(new Font("", Font.PLAIN, 18));
        lblContent.setHorizontalAlignment(JTextField.CENTER);
        lblContent.setForeground(Color.BLACK);
        lblContent.setText("<html>" +
                "<div style='text-align: center; width:300px'>" +
                    content +
                "</div></html>");

        btnOK.setPreferredSize(new Dimension(60, 30));
        btnCancel.setPreferredSize(btnOK.getPreferredSize());

        pnTop.add(lblIcon, BorderLayout.CENTER);
        pnBottom.add(lblContent);
        pnButton.add(btnOK);

        JPanel pnHeader = new JPanel();
        pnHeader.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnHeader.setPreferredSize(new Dimension(400, 25));
        lblClose = new JLabel(new ImageIcon("image/icons8-close-26.png"));
        lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnHeader.add(lblClose);

        pnMain.add(pnHeader);
        pnMain.add(pnTop);
        pnMain.add(pnBottom);
        pnMain.add(pnButton);

        JPanel pnFooter = new JPanel();
        pnFooter.setPreferredSize(new Dimension(400, 20));
        pnMain.add(pnFooter);

        con.add(pnMain);

  
        switch (type) {
            case ERROR_DIALOG:
                lblIcon.setIcon(iconError);
                break;
            case SUCCESS_DIALOG:
                lblIcon.setIcon(iconSuccess);
                break;
            case INFO_DIALOG:
                lblIcon.setIcon(iconInfo);
                break;
            case WARNING_DIALOG:
                lblIcon.setIcon(iconWarning);
                pnButton.add(btnCancel);
                break;
        }

        pnMain.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));

        btnOK.setPreferredSize(new Dimension(80, 30));
        btnCancel.setPreferredSize(btnOK.getPreferredSize());
    }

    private void addEvents() {
        lblClose.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeDialog();
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
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
                action = OK_OPTION;
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
                action = CANCEL_OPTION;
            }
        });
        pnMain.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                Move(x, y);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
    }

    int xMouse, yMouse;

    private void Move(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private int action;
    public final static int OK_OPTION = 1;
    public final static int CANCEL_OPTION = 2;

    private void closeDialog() {
        this.setVisible(false);
    }

    public int getAction() {
        return action;
    }

    private void showWindow() {
        this.setUndecorated(true);
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setModal(true);
//        this.setBackground(Color.WHITE);
        this.setVisible(true);
        getRootPane().setDefaultButton(btnOK);
    }
}
