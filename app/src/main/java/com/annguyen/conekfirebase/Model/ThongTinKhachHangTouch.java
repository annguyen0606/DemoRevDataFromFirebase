package com.annguyen.conekfirebase.Model;

public class ThongTinKhachHangTouch {
    String maKhachHang;
    String nameKhachHang;
    String thoiGianTouch;

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public ThongTinKhachHangTouch(String maKhachHang, String nameKhachHang, String thoiGianTouch) {
        this.maKhachHang = maKhachHang;
        this.nameKhachHang = nameKhachHang;
        this.thoiGianTouch = thoiGianTouch;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getNameKhachHang() {
        return nameKhachHang;
    }

    public void setNameKhachHang(String nameKhachHang) {
        this.nameKhachHang = nameKhachHang;
    }

    public String getThoiGianTouch() {
        return thoiGianTouch;
    }

    public void setThoiGianTouch(String thoiGianTouch) {
        this.thoiGianTouch = thoiGianTouch;
    }
}
