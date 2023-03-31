package com.example.myapplication.Model;

public class BinhLuan {
    private String idUser;
    private String idTruyen;
    private String cmt;
    private String ngay;

    public BinhLuan() {
    }

    public BinhLuan(String idUser, String idTruyen, String cmt, String ngay) {
        this.idUser = idUser;
        this.idTruyen = idTruyen;
        this.cmt = cmt;
        this.ngay = ngay;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(String idTruyen) {
        this.idTruyen = idTruyen;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
