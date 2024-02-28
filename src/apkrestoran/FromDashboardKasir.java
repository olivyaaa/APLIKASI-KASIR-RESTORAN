package apkrestoran;

import aplikasi.Koneksi;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.Timer;

public class FromDashboardKasir extends javax.swing.JFrame {
    java.util.Date tglsekarang = new java.util.Date();
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy", Locale.getDefault());
    private String tanggal = format.format(tglsekarang);

    public FromDashboardKasir() {
        initComponents();
        lbtanggal.setText(tanggal);
        
        setJam();
    }
    
    public final void setJam(){
      ActionListener taskPerform = new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              String nol_jam = "", nol_menit = "", nol_detik = "";
                
                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();
                
                if(nilai_jam <= 9) nol_jam = "0";
                if(nilai_menit <= 9) nol_menit = "0";
                if(nilai_detik <= 9) nol_detik = "0";
                
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                
                lbjam.setText(jam + ":" + menit + ":" + detik + " "); 
          }
      };
      new Timer (1000, taskPerform).start();
    }
    
    public void updateactive(){
        String user = lbuser.getText();
        String active = "0";
        try{
            Connection con = Koneksi.KoneksiDb();
            String sql = "Update pengguna set active = ? where username = ?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1, active);
            p.setString(2, user);
            
            p.executeUpdate();
            p.close();
        }catch(Exception e){
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbkeluar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        selamat = new javax.swing.JLabel();
        lbuser = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbidpegawai = new javax.swing.JLabel();
        lbtanggal = new javax.swing.JLabel();
        lbjam = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbtransaksi = new javax.swing.JLabel();
        lb_catatantransaksi = new javax.swing.JLabel();
        lb_pelanggan = new javax.swing.JLabel();
        lb_updatestatus = new javax.swing.JLabel();
        lb_jenis = new javax.swing.JLabel();
        lb_tentang = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1366, 768));

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1138, 85));

        lbkeluar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-exit-20.png"))); // NOI18N
        lbkeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbkeluarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(1108, Short.MAX_VALUE)
                .addComponent(lbkeluar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lbkeluar)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(1138, 598));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(1078, 541));

        selamat.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        selamat.setForeground(new java.awt.Color(255, 255, 255));
        selamat.setText("SELAMAT DATANG");

        lbuser.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbuser.setForeground(new java.awt.Color(255, 255, 255));
        lbuser.setText("USERNAME");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID ANDA");

        lbidpegawai.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbidpegawai.setForeground(new java.awt.Color(255, 255, 255));
        lbidpegawai.setText("ID");

        lbtanggal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbtanggal.setForeground(new java.awt.Color(255, 255, 255));
        lbtanggal.setText("TANGGAL");

        lbjam.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbjam.setForeground(new java.awt.Color(255, 255, 255));
        lbjam.setText("JAM");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(selamat)
                .addContainerGap(338, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbuser)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(lbidpegawai)))
                        .addGap(455, 455, 455))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbjam)
                            .addComponent(lbtanggal))
                        .addGap(144, 144, 144))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(selamat)
                .addGap(18, 18, 18)
                .addComponent(lbuser)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lbidpegawai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(lbjam)
                .addGap(18, 18, 18)
                .addComponent(lbtanggal)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("DASHBOARD");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("KASIR");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-dashboard-20.png"))); // NOI18N
        jLabel10.setText(" Dashboard");

        lbtransaksi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-receipt-and-change-20.png"))); // NOI18N
        lbtransaksi.setText(" Transaski");
        lbtransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbtransaksiMouseClicked(evt);
            }
        });

        lb_catatantransaksi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_catatantransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-report-20.png"))); // NOI18N
        lb_catatantransaksi.setText(" Catatan Transaksi");
        lb_catatantransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_catatantransaksiMouseClicked(evt);
            }
        });

        lb_pelanggan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_pelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-people-20.png"))); // NOI18N
        lb_pelanggan.setText(" Data Pelanggan");
        lb_pelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_pelangganMouseClicked(evt);
            }
        });

        lb_updatestatus.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_updatestatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-table-20.png"))); // NOI18N
        lb_updatestatus.setText(" Update Status Meja");
        lb_updatestatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_updatestatusMouseClicked(evt);
            }
        });

        lb_jenis.setForeground(new java.awt.Color(255, 255, 255));
        lb_jenis.setText("JABATAN");

        lb_tentang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_tentang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-about-20.png"))); // NOI18N
        lb_tentang.setText(" Tentang");
        lb_tentang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_tentangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_tentang))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_pelanggan))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_updatestatus))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbtransaksi))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_catatantransaksi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jLabel7)
                .addGap(61, 61, 61)
                .addComponent(lb_jenis)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8)))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(lb_jenis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(lb_pelanggan)
                        .addGap(18, 18, 18)
                        .addComponent(lb_updatestatus)
                        .addGap(18, 18, 18)
                        .addComponent(lbtransaksi)
                        .addGap(18, 18, 18)
                        .addComponent(lb_catatantransaksi)
                        .addGap(18, 18, 18)
                        .addComponent(lb_tentang))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbtransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbtransaksiMouseClicked
        // TODO add your handling code here:
        new  FromTransaksi().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where username = '"+lbuser.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbuser.getText().equals(r.getString("username")) &&
                        r.getString("jenis").equals("kasir")){
                    FromTransaksi.lbjabatan.setText(r.getString(4));
                    FromTransaksi.txtidpegawai.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lbtransaksiMouseClicked

    private void lbkeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbkeluarMouseClicked
        // TODO add your handling code here:
        new FromLogin().show();
        this.dispose();
        
        updateactive();
    }//GEN-LAST:event_lbkeluarMouseClicked

    private void lb_catatantransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_catatantransaksiMouseClicked
        // TODO add your handling code here:
        new  FromCatatanTransaksi().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where username = '"+lbuser.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbuser.getText().equals(r.getString("username")) &&
                        r.getString("jenis").equals("kasir")){
                    FromCatatanTransaksi.txtjabatan.setText(r.getString(4));
                    FromCatatanTransaksi.txtidpegawai.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_catatantransaksiMouseClicked

    private void lb_pelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_pelangganMouseClicked
        // TODO add your handling code here:
        new  FromPelanggan().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where username = '"+lbuser.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbuser.getText().equals(r.getString("username")) &&
                        r.getString("jenis").equals("kasir")){
                    FromPelanggan.lb_jenis.setText(r.getString(4));
                    FromPelanggan.lbid.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_pelangganMouseClicked

    private void lb_updatestatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_updatestatusMouseClicked
        // TODO add your handling code here:
        new  FromUpdateStatusMeja().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where username = '"+lbuser.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbuser.getText().equals(r.getString("username")) &&
                        r.getString("jenis").equals("kasir")){
                    FromUpdateStatusMeja.lbjenis.setText(r.getString(4));
                    FromUpdateStatusMeja.lbid.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_updatestatusMouseClicked

    private void lb_tentangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_tentangMouseClicked
        // TODO add your handling code here:
        new  FromTentangKasir().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where username = '"+lbuser.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbuser.getText().equals(r.getString("username")) &&
                        r.getString("jenis").equals("kasir")){
                    FromTentangKasir.lbjenis.setText(r.getString(4));
                    FromTentangKasir.lbid.setText(r.getString(5));
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
            java.util.logging.Logger.getLogger(FromDashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromDashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromDashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromDashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FromDashboardKasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lb_catatantransaksi;
    public static javax.swing.JLabel lb_jenis;
    private javax.swing.JLabel lb_pelanggan;
    private javax.swing.JLabel lb_tentang;
    private javax.swing.JLabel lb_updatestatus;
    public static javax.swing.JLabel lbidpegawai;
    private javax.swing.JLabel lbjam;
    private javax.swing.JLabel lbkeluar;
    private javax.swing.JLabel lbtanggal;
    private javax.swing.JLabel lbtransaksi;
    public static javax.swing.JLabel lbuser;
    private javax.swing.JLabel selamat;
    // End of variables declaration//GEN-END:variables
}
