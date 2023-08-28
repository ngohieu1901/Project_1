package com.example.project_1.DTO;

public class FileDTO {
    int hinh;
    String ten;
    String ngay;

    public FileDTO(int hinh, String ten, String ngay) {
        this.hinh = hinh;
        this.ten = ten;
        this.ngay = ngay;
    }

    public FileDTO() {
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
