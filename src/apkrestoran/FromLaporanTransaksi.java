package apkrestoran;

import aplikasi.Koneksi;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class FromLaporanTransaksi extends javax.swing.JFrame {
    private DefaultTableModel model;
    
   
    public FromLaporanTransaksi() {
        initComponents();
        Nonaktif();
        
        model = new DefaultTableModel();
        tbl_transaksi.setModel(model);
        
        model.addColumn("ID Transaksi");
        model.addColumn("Tanggal");
        model.addColumn("ID Pegawai");
        model.addColumn("ID Pelanggan");
        model.addColumn("ID Meja");
        model.addColumn("Total Harga");
        model.addColumn("Total Bayar");
        model.addColumn("Bayar");
        model.addColumn("Kembali");
        
        GetData();
    }
    
    public void Nonaktif(){
        jctanggal.setEnabled(false);
        jcdaritanggal.setEnabled(false);
        jcdaritanggal2.setEnabled(false);
        btn_pilih.setEnabled(false);
        btn_lihat.setEnabled(false);
        btn_caritanggal.setEnabled(false);
    }
    
    public void Pegawai(){
        lbidpegawai.setText("ID Pegawai");
        lbnamapegawai.setText("Nama Pegawai");
        lbjabatan.setText("Jabatan");
    }
    
    public void GetData(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            String sql = "Select * From transaksi";
            ResultSet r = s.executeQuery(sql);
            
            while(r.next()){
                Object[] ob = new Object[9];
                ob [0] = r.getString("id_transaksi");
                ob [1] = r.getString("tanggal");
                ob [2] = r.getString("id_pegawai");
                ob [3] = r.getString("id_pelanggan");
                ob [4] = r.getString("id_meja");
                ob [5] = r.getString("subtotal");
                ob [6] = r.getString("total");
                ob [7] = r.getString("bayar");
                ob [8] = r.getString("kembali");
                
                model.addRow(ob);
            }
            r.close();
            s.close();
        }catch(Exception e){
            System.out.println("Terjadi Kesalahan");
        }
    }
    
    public void LihatTransaksi(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            String sql = "Select * From transaksi Where id_pegawai like '%"+lbidpegawai.getText()+"%'";
            ResultSet r = s.executeQuery(sql);
            
            while(r.next()){
                Object[] ob = new Object[9];
                ob [0] = r.getString("id_transaksi");
                ob [1] = r.getString("tanggal");
                ob [2] = r.getString("id_pegawai");
                ob [3] = r.getString("id_pelanggan");
                ob [4] = r.getString("id_meja");
                ob [5] = r.getString("subtotal");
                ob [6] = r.getString("total");
                ob [7] = r.getString("bayar");
                ob [8] = r.getString("kembali");
                
                model.addRow(ob);
            }
            r.close();
            s.close();
        }catch(Exception e){
            System.out.println("Terjadi Kesalahan");
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_lihat = new javax.swing.JPanel();
        rb_tanggal = new javax.swing.JRadioButton();
        jctanggal = new com.toedter.calendar.JDateChooser();
        btn_liaht = new javax.swing.JButton();
        jcdaritanggal = new com.toedter.calendar.JDateChooser();
        rb_daritanggal = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jcdaritanggal2 = new com.toedter.calendar.JDateChooser();
        btn_caritanggal = new javax.swing.JButton();
        rb_semua = new javax.swing.JRadioButton();
        rb_pegawai = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        lbidpegawai = new javax.swing.JLabel();
        lbnamapegawai = new javax.swing.JLabel();
        lbjabatan = new javax.swing.JLabel();
        btn_pilih = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lb_datamenu = new javax.swing.JLabel();
        lb_datameja = new javax.swing.JLabel();
        lb_laptrans = new javax.swing.JLabel();
        lb_lappendapatan = new javax.swing.JLabel();
        lb_logaktivitas = new javax.swing.JLabel();
        lb_tentang = new javax.swing.JLabel();
        lbid = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 153, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(1138, 85));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-exit-20.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(1108, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel10)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(1138, 598));

        btn_lihat.setBackground(new java.awt.Color(255, 255, 255));
        btn_lihat.setPreferredSize(new java.awt.Dimension(1078, 541));

        rb_tanggal.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rb_tanggal);
        rb_tanggal.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rb_tanggal.setText("Tanggal");
        rb_tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_tanggalActionPerformed(evt);
            }
        });

        jctanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jctanggal.setPreferredSize(new java.awt.Dimension(200, 25));

        btn_liaht.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_liaht.setText("Lihat");
        btn_liaht.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_liahtActionPerformed(evt);
            }
        });

        jcdaritanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jcdaritanggal.setPreferredSize(new java.awt.Dimension(200, 25));

        rb_daritanggal.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rb_daritanggal);
        rb_daritanggal.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rb_daritanggal.setText("Dari Tanggal");
        rb_daritanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_daritanggalActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("s/d");

        jcdaritanggal2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jcdaritanggal2.setPreferredSize(new java.awt.Dimension(200, 25));

        btn_caritanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_caritanggal.setText("Lihat");
        btn_caritanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_caritanggalActionPerformed(evt);
            }
        });

        rb_semua.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rb_semua);
        rb_semua.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rb_semua.setText("Semua");
        rb_semua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_semuaActionPerformed(evt);
            }
        });

        rb_pegawai.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rb_pegawai);
        rb_pegawai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rb_pegawai.setText("Pegawai");
        rb_pegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_pegawaiActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 153, 0));
        jPanel5.setPreferredSize(new java.awt.Dimension(699, 60));

        lbidpegawai.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbidpegawai.setForeground(new java.awt.Color(255, 255, 255));
        lbidpegawai.setText("ID Pegawai");

        lbnamapegawai.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbnamapegawai.setForeground(new java.awt.Color(255, 255, 255));
        lbnamapegawai.setText("Nama Pegawai");

        lbjabatan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbjabatan.setForeground(new java.awt.Color(255, 255, 255));
        lbjabatan.setText("Jabatan");

        btn_pilih.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_pilih.setText("Pilih Pegawai");
        btn_pilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilihActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(lbidpegawai)
                .addGap(50, 50, 50)
                .addComponent(lbnamapegawai)
                .addGap(48, 48, 48)
                .addComponent(lbjabatan)
                .addGap(53, 53, 53)
                .addComponent(btn_pilih)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbidpegawai)
                    .addComponent(lbnamapegawai)
                    .addComponent(lbjabatan)
                    .addComponent(btn_pilih))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setPreferredSize(new java.awt.Dimension(977, 270));

        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_transaksi);

        javax.swing.GroupLayout btn_lihatLayout = new javax.swing.GroupLayout(btn_lihat);
        btn_lihat.setLayout(btn_lihatLayout);
        btn_lihatLayout.setHorizontalGroup(
            btn_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_lihatLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addGroup(btn_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(btn_lihatLayout.createSequentialGroup()
                        .addGroup(btn_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb_tanggal)
                            .addGroup(btn_lihatLayout.createSequentialGroup()
                                .addGroup(btn_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(btn_lihatLayout.createSequentialGroup()
                                        .addComponent(rb_semua)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rb_pegawai))
                                    .addComponent(jctanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_liaht)))
                        .addGap(18, 18, 18)
                        .addGroup(btn_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btn_lihatLayout.createSequentialGroup()
                                .addComponent(jcdaritanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jcdaritanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_caritanggal))
                            .addComponent(rb_daritanggal))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_lihatLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        btn_lihatLayout.setVerticalGroup(
            btn_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_lihatLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(btn_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btn_lihatLayout.createSequentialGroup()
                        .addComponent(rb_tanggal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jctanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btn_lihatLayout.createSequentialGroup()
                        .addComponent(rb_daritanggal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(btn_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcdaritanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_liaht)
                            .addComponent(jLabel11)))
                    .addGroup(btn_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_caritanggal)
                        .addComponent(jcdaritanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(btn_lihatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_semua)
                    .addComponent(rb_pegawai))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btn_lihat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn_lihat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("LAPORAN TRANSAKSI PENJUALAN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("MANAGER");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-dashboard-20.png"))); // NOI18N
        jLabel3.setText(" Dashboard");

        lb_datamenu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_datamenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-waiter-20.png"))); // NOI18N
        lb_datamenu.setText(" Data Menu");
        lb_datamenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_datamenuMouseClicked(evt);
            }
        });

        lb_datameja.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_datameja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-table-20.png"))); // NOI18N
        lb_datameja.setText(" Data Meja");
        lb_datameja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_datamejaMouseClicked(evt);
            }
        });

        lb_laptrans.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_laptrans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-report-20.png"))); // NOI18N
        lb_laptrans.setText(" Laporan Transaksi");
        lb_laptrans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_laptransMouseClicked(evt);
            }
        });

        lb_lappendapatan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_lappendapatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-report-20.png"))); // NOI18N
        lb_lappendapatan.setText(" Laporan Pendapatan");

        lb_logaktivitas.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_logaktivitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-people-20.png"))); // NOI18N
        lb_logaktivitas.setText(" Log Aktivitas Pegawai");
        lb_logaktivitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_logaktivitasMouseClicked(evt);
            }
        });

        lb_tentang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_tentang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-about-20.png"))); // NOI18N
        lb_tentang.setText(" Tentang");
        lb_tentang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_tentangMouseClicked(evt);
            }
        });

        lbid.setForeground(new java.awt.Color(255, 255, 255));
        lbid.setText("id");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lb_datamenu)
                            .addComponent(lb_datameja)
                            .addComponent(lb_laptrans)
                            .addComponent(lb_lappendapatan)
                            .addComponent(lb_logaktivitas)
                            .addComponent(lb_tentang)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(282, 282, 282)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbid)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel2)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lbid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lb_datamenu)
                        .addGap(18, 18, 18)
                        .addComponent(lb_datameja)
                        .addGap(18, 18, 18)
                        .addComponent(lb_laptrans)
                        .addGap(18, 18, 18)
                        .addComponent(lb_lappendapatan)
                        .addGap(18, 18, 18)
                        .addComponent(lb_logaktivitas)
                        .addGap(18, 18, 18)
                        .addComponent(lb_tentang))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rb_tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_tanggalActionPerformed
        // TODO add your handling code here:
        jctanggal.setEnabled(true);
        jcdaritanggal.setEnabled(false);
        jcdaritanggal2.setEnabled(false);
        btn_pilih.setEnabled(false);
        btn_lihat.setEnabled(true);
        btn_caritanggal.setEnabled(false);
        Pegawai();
    }//GEN-LAST:event_rb_tanggalActionPerformed

    private void rb_daritanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_daritanggalActionPerformed
        // TODO add your handling code here:
        jctanggal.setEnabled(false);
        jcdaritanggal.setEnabled(true);
        jcdaritanggal2.setEnabled(true);
        btn_pilih.setEnabled(false);
        btn_lihat.setEnabled(false);
        btn_caritanggal.setEnabled(true);
        Pegawai();
    }//GEN-LAST:event_rb_daritanggalActionPerformed

    private void rb_semuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_semuaActionPerformed
        // TODO add your handling code here:
        Nonaktif();
        GetData();
        Pegawai();
    }//GEN-LAST:event_rb_semuaActionPerformed

    private void rb_pegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_pegawaiActionPerformed
        // TODO add your handling code here:
        jctanggal.setEnabled(false);
        jcdaritanggal.setEnabled(false);
        jcdaritanggal2.setEnabled(false);
        btn_pilih.setEnabled(true);
        btn_lihat.setEnabled(false);
        btn_caritanggal.setEnabled(false);
        DaftarPegawai a = new DaftarPegawai();
        a.setVisible(true);
    }//GEN-LAST:event_rb_pegawaiActionPerformed

    private void btn_liahtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_liahtActionPerformed
        // TODO add your handling code here:
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDate.format(jctanggal.getDate());
        try{
            Connection c = Koneksi.KoneksiDb();
            String sql = "Select * From transaksi Where tanggal = '"+date+"'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                model.addRow(new Object[]{
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9),
            });   
            }
            tbl_transaksi.setModel(model);
        }catch(Exception e){
            System.out.println("Cari Data Error");
        }finally{
            jctanggal.setDate(null);
        }
        
    }//GEN-LAST:event_btn_liahtActionPerformed

    private void btn_caritanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_caritanggalActionPerformed
        // TODO add your handling code here:
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = sDate.format(jcdaritanggal.getDate());
        String date2 = sDate.format(jcdaritanggal2.getDate());
        try{
            Connection c = Koneksi.KoneksiDb();
            String sql = "Select * From transaksi Where tanggal Between '"+date1+"' And '"+date2+"'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                model.addRow(new Object[]{
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9),
            });   
            }
            tbl_transaksi.setModel(model);
        }catch(Exception e){
            System.out.println("Cari Data Error");
        }finally{
            jcdaritanggal.setDate(null);
            jcdaritanggal2.setDate(null);
        }
    }//GEN-LAST:event_btn_caritanggalActionPerformed

    private void btn_pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilihActionPerformed
        // TODO add your handling code here:
        LihatTransaksi();
    }//GEN-LAST:event_btn_pilihActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        new FromDashboardManager().show();
        this.dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void lb_datamenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_datamenuMouseClicked
        // TODO add your handling code here:
        new  FromMenu().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+lbidpegawai.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbidpegawai.getText().equals(r.getString("id_pegawai"))){ 
                    FromMenu.lb_id.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_datamenuMouseClicked

    private void lb_datamejaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_datamejaMouseClicked
        // TODO add your handling code here:
        new  FromMeja().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+lbidpegawai.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbidpegawai.getText().equals(r.getString("id_pegawai"))){ 
                    FromMeja.lb_id.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }

    }//GEN-LAST:event_lb_datamejaMouseClicked

    private void lb_laptransMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_laptransMouseClicked
        // TODO add your handling code here:
        new  FromLaporanTransaksi().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+lbidpegawai.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbidpegawai.getText().equals(r.getString("id_pegawai"))){ 
                    FromLaporanTransaksi.lbid.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_laptransMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        new  FromLaporanPendapatan().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+lbidpegawai.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbidpegawai.getText().equals(r.getString("id_pegawai"))){ 
                    FromLaporanPendapatan.lbid.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void lb_logaktivitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_logaktivitasMouseClicked
        // TODO add your handling code here:
        new  FromLogAktivitasPegawaiManager().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+lbidpegawai.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbidpegawai.getText().equals(r.getString("id_pegawai"))){ 
                    FromLogAktivitasPegawaiManager.lbid.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_logaktivitasMouseClicked

    private void lb_tentangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_tentangMouseClicked
        // TODO add your handling code here:
        new  FromTentangManager().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+lbidpegawai.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbidpegawai.getText().equals(r.getString("id_pegawai"))){ 
                    FromTentangManager.lbid.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_tentangMouseClicked

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
            java.util.logging.Logger.getLogger(FromLaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromLaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromLaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromLaporanTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FromLaporanTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_caritanggal;
    private javax.swing.JButton btn_liaht;
    private javax.swing.JPanel btn_lihat;
    private javax.swing.JButton btn_pilih;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jcdaritanggal;
    private com.toedter.calendar.JDateChooser jcdaritanggal2;
    private com.toedter.calendar.JDateChooser jctanggal;
    private javax.swing.JLabel lb_datameja;
    private javax.swing.JLabel lb_datamenu;
    private javax.swing.JLabel lb_lappendapatan;
    private javax.swing.JLabel lb_laptrans;
    private javax.swing.JLabel lb_logaktivitas;
    private javax.swing.JLabel lb_tentang;
    public static javax.swing.JLabel lbid;
    public static javax.swing.JLabel lbidpegawai;
    public static javax.swing.JLabel lbjabatan;
    public static javax.swing.JLabel lbnamapegawai;
    private javax.swing.JRadioButton rb_daritanggal;
    private javax.swing.JRadioButton rb_pegawai;
    private javax.swing.JRadioButton rb_semua;
    private javax.swing.JRadioButton rb_tanggal;
    private javax.swing.JTable tbl_transaksi;
    // End of variables declaration//GEN-END:variables
}
