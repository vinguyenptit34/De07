/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.IOFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BPC;
import model.DuAn;
import model.NhanVien;

/**
 *
 * @author ViNguyen
 */
public class Main extends javax.swing.JFrame {

    enum State{
        normal,
        themDuAn,
        suaDuAn,
        themNhanVien,
        suaNhanVien,
        themBPC,
        suaBPC
    }
    private State state;
    private ArrayList<DuAn> listDuAn = new ArrayList<>();
    private ArrayList<NhanVien> listNhanVien = new ArrayList<>();
    private ArrayList<BPC> listBPC = new ArrayList<>();
    DefaultTableModel tm1,tm2,tm3;
    public Main() {
        initComponents();
        tm1 = (DefaultTableModel)jTable1.getModel();
        tm2 = (DefaultTableModel)jTable2.getModel();
        tm3 = (DefaultTableModel)jTable3.getModel();
        changeState(state.normal);
        IOFile.readFile(listDuAn, "DuAn.dat");
        IOFile.readFile(listNhanVien, "NhanVien.dat");
        IOFile.readFile(listBPC, "BPC.dat");
    }
    
    private void changeState(State state) {
        this.state = state;
        if(state == State.normal) {
            btcn1.setEnabled(false);
            btbq1.setEnabled(false);
            btcn2.setEnabled(false);
            btbq2.setEnabled(false);
            btcn3.setEnabled(false);
            btbq3.setEnabled(false);
            bttm1.setEnabled(true);
            btsua1.setEnabled(true);
            btxoa1.setEnabled(true);
            btluu1.setEnabled(true);
            bttm2.setEnabled(true);
            btsua2.setEnabled(true);
            btxoa2.setEnabled(true);
            btluu2.setEnabled(true);
            bttm3.setEnabled(true);
            btsua3.setEnabled(true);
            btxoa3.setEnabled(true);
            btluu3.setEnabled(true);
        }
        else if((state == State.themDuAn) || (state == State.suaDuAn)) {
            bttm1.setEnabled(false);
            btsua1.setEnabled(false);
            btxoa1.setEnabled(false);
            btluu1.setEnabled(false);
            btcn1.setEnabled(true);
            btbq1.setEnabled(true);
        }
        else if((state == State.themNhanVien) || (state == State.suaNhanVien)) {
            bttm2.setEnabled(false);
            btsua2.setEnabled(false);
            btxoa2.setEnabled(false);
            btluu2.setEnabled(false);
            btcn2.setEnabled(true);
            btbq2.setEnabled(true);
        }
        else if((state == State.themBPC) || (state == State.suaBPC)) {
            bttm3.setEnabled(false);
            btsua3.setEnabled(false);
            btxoa3.setEnabled(false);
            btluu3.setEnabled(false);
            btcn3.setEnabled(true);
            btbq3.setEnabled(true);
        }
    }
    
    private DuAn newDuAn() {
        DuAn da = null;
        try{
            String tenDA = jTextField2.getText(),
                    kieuDA = jComboBox1.getSelectedItem().toString();
            Double tongKP = Double.parseDouble(jTextField3.getText());
            if(tenDA.equals("")) {
                JOptionPane.showMessageDialog(this, "k dc bo trong");
            }
            else if(tongKP<0) {
                JOptionPane.showMessageDialog(this, "Kinh phi phai la so");
            }
            else {
                int maDA = Integer.parseInt(jTextField1.getText());
                da= new DuAn(maDA,tenDA,kieuDA,tongKP);
            }
        }catch(Exception e){
                JOptionPane.showMessageDialog(this,"Nhap lai");
            }
        return da;
    }
    
    private void themDuAn() {
        DuAn da = newDuAn();
        if(da!=null) {
            listDuAn.add(da);
            hienthiDuAn();
        }
    }
    
    private void hienthiDuAn() {
        tm1.setRowCount(0);
        for(DuAn da : listDuAn){
            tm1.addRow(da.toObject());
        }
    }
    
    private void suaDuAn() {
        int check = jTable1.getSelectedRow();
        DuAn da = newDuAn();
        listDuAn.set(check, da);
        hienthiDuAn();
    }
    
    private void xoaDuAn() {
        int check = jTable1.getSelectedRow();
        if(check<0 || check>jTable1.getRowCount()||jTable1.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "Chon du an de xoa");
        }
        else{
            listDuAn.remove(check);
            hienthiDuAn();
        }
    }
    
    private DuAn timDuAn(int maDA) {
        for(int i=0;i<listDuAn.size();i++) {
            if(listDuAn.get(i).getMaDA()==maDA){
                return listDuAn.get(i);
            }
        }
        return null;
    } 
    
    private NhanVien newNhanVien() {
        NhanVien nv = null;
        try{
            String hoTen = jTextField5.getText(),
                    diaChi = jTextField6.getText(),
                    chuyenMon = jTextField7.getText();
            if(hoTen.equals("")||diaChi.equals("")||chuyenMon.equals("")){
                JOptionPane.showConfirmDialog(this, "Khong duoc bo trong");
            }
            else {
                int maNV = Integer.parseInt(jTextField4.getText());
                nv = new NhanVien(maNV,hoTen,diaChi,chuyenMon);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Nhan lai");
        }
        return nv;
    }
    
    private void themNhanVien() {
        NhanVien nv = newNhanVien();
        if(nv!=null) {
            listNhanVien.add(nv);
            hienthiNhanVien();
        }
    }
    
    private void hienthiNhanVien() {
        tm2.setRowCount(0);
        for(NhanVien nv:listNhanVien){
            tm2.addRow(nv.toObject());
        }
    }
    
    private void suaNhanVien() {
        int check = jTable2.getSelectedRow();
        NhanVien nv = newNhanVien();
        listNhanVien.set(check, nv);
        hienthiNhanVien();
    }
    
    private void xoaNhanVien() {
        int check = jTable2.getSelectedRow();
        if(check<0 || check>jTable2.getRowCount() || jTable2.getRowCount()==0) {
            JOptionPane.showMessageDialog(this, "Chon nhan vien de xoa");
        }
        else {
            listNhanVien.remove(check);
            hienthiNhanVien();
        }
    }
    
    private NhanVien timNhanVien(int maNV) {
        for(int i=0;i<listNhanVien.size();i++) {
            if(listNhanVien.get(i).getMaNV()==maNV)
                return listNhanVien.get(i);
        }
        return null;
    }
    
    private boolean isBPC(int manv,int mada){
        for(BPC bpc : listBPC) {
            if(bpc.getmaNV()==manv && bpc.getmaDA()==mada){
                return true;
            }
        }
        return false;
    }
    
    private BPC newBPC() {
        BPC bpc = null;
        int manv=0,mada=0;
        try{
            manv = Integer.parseInt(jComboBox2.getSelectedItem().toString());
            mada = Integer.parseInt(jComboBox3.getSelectedItem().toString());
            if(isBPC(manv,mada)){
                JOptionPane.showMessageDialog(this, "Da ton tai");
                return null;
            }
            int soNTG = Integer.parseInt(jTextField8.getText());
            String viTri = jTextField9.getText();
            if(soNTG<0) {
                JOptionPane.showMessageDialog(this, "So NTG phai la so");
            }
            else if(viTri.equals("")){
                JOptionPane.showMessageDialog(this, "Vi tri phai la chu");
            }
            else {
                bpc = new BPC(timNhanVien(manv),timDuAn(mada),soNTG,viTri);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Nhap lai");
        }
        return bpc;
    }
    
    private void hienthiBPC() {
        tm3.setRowCount(0);
        for(BPC bpc : listBPC) {
            tm3.addRow(bpc.toObject());
        }
    }
    
    private void themBPC() {
        BPC b = newBPC();
        if(b!=null) {
            listBPC.add(b);
            hienthiBPC();
        }
    }
    
    private void suaBPC() {
        int check = jTable3.getSelectedRow();
        BPC b = newBPC();
        if(b!=null) {
            return;
        }
        else {
            listBPC.set(check, b);
            hienthiBPC();
        }
    }
    
    private void xoaBPC() {
        int check = jTable3.getSelectedRow();
        if(check<0 || check>jTable3.getRowCount()|| jTable3.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "Chon BPC de xoa");
        }
        else {
            listBPC.remove(check);
            hienthiBPC();
        }
    }
    
    private void refresh2Ma() {
        jComboBox2.removeAllItems();
        jComboBox3.removeAllItems();
        for(NhanVien nv : listNhanVien) {
            jComboBox2.addItem(Integer.toString(nv.getMaNV()));
        }
        for(DuAn da :listDuAn) {
            jComboBox3.addItem(Integer.toString(da.getMaDA()));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bttm1 = new javax.swing.JButton();
        btsua1 = new javax.swing.JButton();
        btxoa1 = new javax.swing.JButton();
        btluu1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        btcn1 = new javax.swing.JButton();
        btbq1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        bttm2 = new javax.swing.JButton();
        btsua2 = new javax.swing.JButton();
        btxoa2 = new javax.swing.JButton();
        btluu2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        btcn2 = new javax.swing.JButton();
        btbq2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        bttm3 = new javax.swing.JButton();
        btsua3 = new javax.swing.JButton();
        btxoa3 = new javax.swing.JButton();
        btluu3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        btcn3 = new javax.swing.JButton();
        btbq3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btsx = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        bttk = new javax.swing.JButton();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã dự án", "Tên dự án", "Kiểu dự án", "Tổng kinh phí"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        bttm1.setText("Thêm mới");
        bttm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttm1ActionPerformed(evt);
            }
        });

        btsua1.setText("Sửa");
        btsua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsua1ActionPerformed(evt);
            }
        });

        btxoa1.setText("Xoá");
        btxoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoa1ActionPerformed(evt);
            }
        });

        btluu1.setText("Lưu");
        btluu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btluu1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã dự án");

        jLabel2.setText("Tên dự án");

        jLabel3.setText("Kiểu dự án");

        jLabel4.setText("Tổng kinh phí");

        btcn1.setText("Cập nhật");
        btcn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcn1ActionPerformed(evt);
            }
        });

        btbq1.setText("Bỏ qua");
        btbq1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbq1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhỏ", "Trung bình", "Lớn" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(bttm1)
                .addGap(146, 146, 146)
                .addComponent(btsua1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btxoa1)
                .addGap(150, 150, 150)
                .addComponent(btluu1)
                .addGap(93, 93, 93))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, 0, 206, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3)
                    .addComponent(jTextField1))
                .addGap(213, 213, 213)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btbq1)
                    .addComponent(btcn1))
                .addGap(187, 187, 187))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttm1)
                    .addComponent(btsua1)
                    .addComponent(btxoa1)
                    .addComponent(btluu1))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btcn1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btbq1)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dự án", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Địa chỉ", "Chuyên môn"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        bttm2.setText("Thêm mới");
        bttm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttm2ActionPerformed(evt);
            }
        });

        btsua2.setText("Sửa");
        btsua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsua2ActionPerformed(evt);
            }
        });

        btxoa2.setText("Xoá");
        btxoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoa2ActionPerformed(evt);
            }
        });

        btluu2.setText("Lưu");
        btluu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btluu2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Mã nhân viên");

        jLabel7.setText("Họ tên");

        jLabel8.setText("Địa chỉ");

        jLabel9.setText("Chuyên môn");

        btcn2.setText("Cập nhật");
        btcn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcn2ActionPerformed(evt);
            }
        });

        btbq2.setText("Bỏ qua");
        btbq2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbq2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(bttm2)
                .addGap(142, 142, 142)
                .addComponent(btsua2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(btxoa2)
                .addGap(142, 142, 142)
                .addComponent(btluu2)
                .addGap(91, 91, 91))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(76, 76, 76)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField6)
                    .addComponent(jTextField5)
                    .addComponent(jTextField7)
                    .addComponent(jTextField4))
                .addGap(138, 138, 138)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btcn2)
                    .addComponent(btbq2))
                .addGap(208, 208, 208))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttm2)
                    .addComponent(btsua2)
                    .addComponent(btxoa2)
                    .addComponent(btluu2))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btcn2)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btbq2)))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhân viên", jPanel2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Mã dự án", "Tên dự án", "Số ngày tham gia", "Vị trí công việc"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        bttm3.setText("Thêm mới");
        bttm3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttm3ActionPerformed(evt);
            }
        });

        btsua3.setText("Sửa");
        btsua3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsua3ActionPerformed(evt);
            }
        });

        btxoa3.setText("Xoá");
        btxoa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoa3ActionPerformed(evt);
            }
        });

        btluu3.setText("Lưu");
        btluu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btluu3ActionPerformed(evt);
            }
        });

        jLabel10.setText("Mã nhân viên");

        jLabel11.setText("Mã dự án");

        jLabel12.setText("Số ngày tham gia");

        jLabel13.setText("Vị trí công việc");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btcn3.setText("Cập nhật");
        btcn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcn3ActionPerformed(evt);
            }
        });

        btbq3.setText("Bỏ qua");
        btbq3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbq3ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Sắp xếp"));

        btsx.setText("Sắp xếp");
        btsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsxActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Theo tên nhân viên");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Theo số ngày tham gia");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btsx)
                .addGap(31, 31, 31))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btsx)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jLabel14.setText("Tên nhân viên");

        bttk.setText("Tìm kiếm");
        bttk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(bttk)
                        .addGap(0, 140, Short.MAX_VALUE))
                    .addComponent(jTextField10))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bttk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(bttm3)
                .addGap(138, 138, 138)
                .addComponent(btsua3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btxoa3)
                .addGap(150, 150, 150)
                .addComponent(btluu3)
                .addGap(104, 104, 104))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, 225, Short.MAX_VALUE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btbq3)
                                    .addComponent(btcn3))
                                .addGap(206, 206, 206))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttm3)
                    .addComponent(btsua3)
                    .addComponent(btxoa3)
                    .addComponent(btluu3))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btcn3)
                        .addGap(18, 18, 18)))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(btbq3)
                            .addGap(33, 33, 33))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(49, 49, 49)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bảng phân công", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int check = jTable1.getSelectedRow();
        jTextField1.setText(tm1.getValueAt(check, 0).toString());
        jTextField2.setText(tm1.getValueAt(check, 1).toString());
        jComboBox1.setSelectedItem(tm1.getValueAt(check, 2).toString());
        jTextField3.setText(tm1.getValueAt(check, 3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void bttm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttm1ActionPerformed
        changeState(State.themDuAn);
        int maDA = listDuAn.size()+10000;
        while(true){
            if(timDuAn(maDA)==null){
                jTextField1.setText(maDA+"");
                break;
            }
            maDA++;
        }
    }//GEN-LAST:event_bttm1ActionPerformed

    private void btsua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsua1ActionPerformed
        if(jTable1.getSelectedRow()>=0) {
            changeState(State.suaDuAn);
        }else{
            JOptionPane.showMessageDialog(this, "Chon du an de sua");
        }
    }//GEN-LAST:event_btsua1ActionPerformed

    private void btxoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoa1ActionPerformed
        xoaDuAn();
        refresh2Ma();
    }//GEN-LAST:event_btxoa1ActionPerformed

    private void btluu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btluu1ActionPerformed
        IOFile.writeFile(listDuAn, "DuAn.dat");
    }//GEN-LAST:event_btluu1ActionPerformed

    private void btcn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcn1ActionPerformed
        if(state==State.themDuAn){
            themDuAn();
        }
        else if(state == State.suaDuAn){
            suaDuAn();
        }
        refresh2Ma();
        changeState(State.normal);
    }//GEN-LAST:event_btcn1ActionPerformed

    private void btbq1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbq1ActionPerformed
        changeState(State.normal);
    }//GEN-LAST:event_btbq1ActionPerformed

    private void bttm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttm2ActionPerformed
        changeState(State.themNhanVien);
        int manv = listNhanVien.size()+10000;
        while(true) {
            if(timNhanVien(manv)==null) {
                jTextField4.setText(manv+"");
                break;
            }
            manv++;
        }
    }//GEN-LAST:event_bttm2ActionPerformed

    private void btsua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsua2ActionPerformed
        if(jTable2.getSelectedRow()>=0) {
            changeState(State.suaNhanVien);
        }else {
            JOptionPane.showMessageDialog(this, "Chon nhan vien de sua");
        }
    }//GEN-LAST:event_btsua2ActionPerformed

    private void btxoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoa2ActionPerformed
        xoaNhanVien();
        refresh2Ma();
    }//GEN-LAST:event_btxoa2ActionPerformed

    private void btluu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btluu2ActionPerformed
        IOFile.writeFile(listNhanVien, "NhanVien.dat");
    }//GEN-LAST:event_btluu2ActionPerformed

    private void btcn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcn2ActionPerformed
        if(state == State.themNhanVien) {
            themNhanVien();
        }
        else if(state == State.suaNhanVien) {
            suaNhanVien();
        }
        changeState(State.normal);
        refresh2Ma();
    }//GEN-LAST:event_btcn2ActionPerformed

    private void btbq2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbq2ActionPerformed
        changeState(State.normal);
    }//GEN-LAST:event_btbq2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int check = jTable2.getSelectedRow();
        jTextField4.setText(tm2.getValueAt(check, 0).toString());
        jTextField5.setText(tm2.getValueAt(check, 1).toString());
        jTextField6.setText(tm2.getValueAt(check, 2).toString());
        jTextField7.setText(tm2.getValueAt(check, 3).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void bttm3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttm3ActionPerformed
        changeState(State.themBPC);
    }//GEN-LAST:event_bttm3ActionPerformed

    private void btsua3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsua3ActionPerformed
        if(jTable3.getSelectedRow()>=0){
            changeState(State.suaBPC);
        }
        else {
            JOptionPane.showMessageDialog(this, "Chon BPC de sua");
        }
    }//GEN-LAST:event_btsua3ActionPerformed

    private void btxoa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoa3ActionPerformed
        xoaBPC();
    }//GEN-LAST:event_btxoa3ActionPerformed

    private void btluu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btluu3ActionPerformed
        IOFile.writeFile(listBPC, "BPC.dat");
    }//GEN-LAST:event_btluu3ActionPerformed

    private void btcn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcn3ActionPerformed
        if(state == State.themBPC){
            themBPC();
        }
        else if(state == State.suaBPC){
            suaBPC();
        }
        changeState(State.normal);
    }//GEN-LAST:event_btcn3ActionPerformed

    private void btbq3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbq3ActionPerformed
        changeState(State.normal);
    }//GEN-LAST:event_btbq3ActionPerformed

    private void btsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsxActionPerformed
        if(jRadioButton1.isSelected()) {
            Collections.sort(listBPC,new Comparator<BPC>() {
                @Override
                public int compare(BPC o1, BPC o2) {
                    return o1.getHoTen().compareToIgnoreCase(o2.getHoTen());
                }
                
            });
        }
        else {
            Collections.sort(listBPC,new Comparator<BPC>(){
                @Override
                public int compare(BPC o1, BPC o2) {
                    return o2.getSoNTG()-o1.getSoNTG();
                }
            });
        }
        tm3.setRowCount(0);
        for(BPC b:listBPC) {
            tm3.addRow(b.toObject());
        }
    }//GEN-LAST:event_btsxActionPerformed

    private void bttkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttkActionPerformed
        List<BPC> bpc = new ArrayList<>();
        String ten = jTextField10.getText();
        for(int i=0;i<listBPC.size();i++) {
            if(listBPC.get(i).getHoTen().equals(ten))
                bpc.add(listBPC.get(i));
        }
        tm3.setRowCount(0);
        for(BPC i:bpc) {
            tm3.addRow(i.toObject());
        }
    }//GEN-LAST:event_bttkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbq1;
    private javax.swing.JButton btbq2;
    private javax.swing.JButton btbq3;
    private javax.swing.JButton btcn1;
    private javax.swing.JButton btcn2;
    private javax.swing.JButton btcn3;
    private javax.swing.JButton btluu1;
    private javax.swing.JButton btluu2;
    private javax.swing.JButton btluu3;
    private javax.swing.JButton btsua1;
    private javax.swing.JButton btsua2;
    private javax.swing.JButton btsua3;
    private javax.swing.JButton btsx;
    private javax.swing.JButton bttk;
    private javax.swing.JButton bttm1;
    private javax.swing.JButton bttm2;
    private javax.swing.JButton bttm3;
    private javax.swing.JButton btxoa1;
    private javax.swing.JButton btxoa2;
    private javax.swing.JButton btxoa3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
