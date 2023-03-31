package com.example.myapplication.Model;


import java.util.Arrays;

public class Truyen {
    private int id;
    private String ten;
    private String moTa;
    private String tenTG;
    private  int namSX;
    private String linkAnhBia;
    private String[] linkAnhTruyen;

    public Truyen() {
    }

    public Truyen(int id, String ten, String moTa, String tenTG, int namSX, String linkAnhBia, String[] linkAnhTruyen) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
        this.tenTG = tenTG;
        this.namSX = namSX;
        this.linkAnhBia = linkAnhBia;
        this.linkAnhTruyen = linkAnhTruyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public int getNamSX() {
        return namSX;
    }

    public void setNamSX(int namSX) {
        this.namSX = namSX;
    }

    public String getLinkAnhBia() {
        return linkAnhBia;
    }

    public void setLinkAnhBia(String linkAnhBia) {
        this.linkAnhBia = linkAnhBia;
    }

    public String[] getLinkAnhTruyen() {
        return linkAnhTruyen;
    }

    public void setLinkAnhTruyen(String[] linkAnhTruyen) {
        this.linkAnhTruyen = linkAnhTruyen;
    }

    @Override
    public String toString() {
        return "Truyen{" +
                "id=" + id +
                ", ten='" + ten + '\'' +
                ", moTa='" + moTa + '\'' +
                ", tenTG='" + tenTG + '\'' +
                ", namSX=" + namSX +
                ", linkAnhBia='" + linkAnhBia + '\'' +
                ", linkAnhTruyen=" + Arrays.toString(linkAnhTruyen) +
                '}';
    }
}
