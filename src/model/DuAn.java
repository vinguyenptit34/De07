package model;

import java.io.Serializable;

/**
 *
 * @author ViNguyen
 */
public class DuAn implements Serializable{
    private int maDA;
    private String tenDA,kieuDA;
    private double tongKP;

    public DuAn(int maDA, String tenDA, String kieuDA, double tongKP) {
        this.maDA = maDA;
        this.tenDA = tenDA;
        this.kieuDA = kieuDA;
        this.tongKP = tongKP;
    }

    public int getMaDA() {
        return maDA;
    }

    public void setMaDA(int maDA) {
        this.maDA = maDA;
    }

    public String getTenDA() {
        return tenDA;
    }

    public void setTenDA(String tenDA) {
        this.tenDA = tenDA;
    }

    public String getKieuDA() {
        return kieuDA;
    }

    public void setKieuDA(String kieuDA) {
        this.kieuDA = kieuDA;
    }

    public double getTongKP() {
        return tongKP;
    }

    public void setTongKP(double tongKP) {
        this.tongKP = tongKP;
    }
    
    public Object[] toObject() {
        return new Object[] {maDA,tenDA,kieuDA,tongKP};
    }
}