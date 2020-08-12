package com.annguyen.conekfirebase.Model;

public class ThongTinKhachHang {
    String maKhachHang;
    String nameKhachHang;
    String birth;

    public ThongTinKhachHang(String maKhachHang, String nameKhachHang, String birth) {
        this.maKhachHang = maKhachHang;
        this.nameKhachHang = nameKhachHang;
        this.birth = birth;
    }

    public String getMaKhachHang() {
        return maKhachHang;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
