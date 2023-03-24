package com.example.myapplication.Model;

public class BinhLuan {
    private int idUser;
    private int idTruyen;
    private String cmt;
    private String ngay;

    public BinhLuan() {
    }

    public BinhLuan(int idUser, int idTruyen, String cmt, String ngay) {
        this.idUser = idUser;
        this.idTruyen = idTruyen;
        this.cmt = cmt;
        this.ngay = ngay;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(int idTruyen) {
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
