package ngoinhacafe.Logic.QuanLyTaiKhoan;

import java.util.ArrayList;

public class QuanLyTaiKhoanBUS {

    private ArrayList<TaiKhoan> dstk = new ArrayList<>();
    QuanLyTaiKhoanDAO qltkDAO = new QuanLyTaiKhoanDAO();

    public QuanLyTaiKhoanBUS() {
        dstk = qltkDAO.readDB();
    }

    public void showConsole() {
        dstk.forEach((tk) -> {
            System.out.print(tk.getTenTK() + " ");
            System.out.print(tk.getMatKhau() + " ");
            System.out.println(tk.getMaNV() + " ");
            System.out.println(tk.getMaQuyen());
        });
    }

    public String[] getHeaders() {
        return new String[]{"Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền"};
    }

    public void readDB() {
        dstk = qltkDAO.readDB();
    }

    public TaiKhoan getTaiKhoan(String tentk) {
        for (TaiKhoan tk : dstk) {
            if (tk.getTenTK().equals(tentk)) {
                return tk;
            }
        }
        return null;
    }

    public ArrayList<TaiKhoan> search(String value, String type) {
        ArrayList<TaiKhoan> result = new ArrayList<>();

        dstk.forEach((tk) -> {
            if (type.equals("Tất cả")) {
                if (tk.getTenTK().toLowerCase().contains(value.toLowerCase())
                        || tk.getMatKhau().toLowerCase().contains(value.toLowerCase())
                        || tk.getMaNV().toLowerCase().contains(value.toLowerCase())
                        || tk.getMaQuyen().toLowerCase().contains(value.toLowerCase())) {
                    result.add(tk);
                }
            } else {
                switch (type) {
                    case "Tên tài khoản":
                        if (tk.getTenTK().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                    case "Mật khẩu":
                        if (tk.getMatKhau().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                    case "Mã nhân viên":
                        if (tk.getMaNV().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                    case "Mã quyền":
                        if (tk.getMaQuyen().toLowerCase().contains(value.toLowerCase())) {
                            result.add(tk);
                        }
                        break;
                }
            }

        });

        return result;
    }

    public Boolean add(TaiKhoan tk) {
        Boolean ok = qltkDAO.add(tk);

        if (ok) {
            dstk.add(tk);
        }
        return ok;
    }

    public Boolean add(String username, String pass, String maNV, String maQuyen) {
        TaiKhoan tk = new TaiKhoan(maNV, maQuyen, maNV, maQuyen, maNV);
        return add(tk);
    }

    public Boolean delete(String username) {
        Boolean ok = qltkDAO.delete(username);

        if (ok) {
            for (int i = (dstk.size() - 1); i >= 0; i--) {
                if (dstk.get(i).getTenTK().equals(username)) {
                    dstk.remove(i);
                }
            }
        }
        return ok;
    }

    public Boolean update(String username, String pass, String maNV, String maQuyen) {
        Boolean ok = qltkDAO.update(username, pass, maNV, maQuyen);

        if (ok) {
            dstk.forEach((tk) -> {
                if (tk.getTenTK().equals(username)) {
                    tk.setMatKhau(pass);
                    tk.setMaNV(maNV);
                    tk.setMaQuyen(maQuyen);
                }
            });
        }

        return ok;
    }

    public ArrayList<TaiKhoan> getDstk() {
        return dstk;
    }
}
