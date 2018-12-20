package model;

import java.io.Serializable;

/**
 *
 * @author ViNguyen
 */
public class BPC implements Serializable{
    private NhanVien nhanVien;
    private DuAn duAn;
    private int soNTG;
    private String viTri;

    public BPC(NhanVien nhanVien, DuAn duAn, int soNTG, String viTri) {
        this.nhanVien = nhanVien;
        this.duAn = duAn;
        this.soNTG = soNTG;
        this.viTri = viTri;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public DuAn getDuAn() {
        return duAn;
    }

    public void setDuAn(DuAn duAn) {
        this.duAn = duAn;
    }

    public int getSoNTG() {
        return soNTG;
    }

    public void setSoNTG(int soNTG) {
        this.soNTG = soNTG;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }
    
    public int getmaNV() {
        return nhanVien.getMaNV();
    }
    
    public String getHoTen() {
        return nhanVien.getHoTen();
    }
    
    public int getmaDA(){
        return duAn.getMaDA();
    }
    
    public String getTenDA(){
        return duAn.getTenDA();
    }
    
    public Object[] toObject() {
        return new Object[] {getmaNV(),getHoTen(),getmaDA(),getTenDA(),soNTG,viTri};
    }
}
