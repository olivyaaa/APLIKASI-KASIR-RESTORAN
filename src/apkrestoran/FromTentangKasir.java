/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apkrestoran;
import aplikasi.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author user
 */
public class FromTentangKasir extends javax.swing.JFrame {

    /**
     * Creates new form FromTentangAdmin
     */
    public FromTentangKasir() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lb_keluar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbtransaksi = new javax.swing.JLabel();
        lb_catatantransaksi = new javax.swing.JLabel();
        lb_datapelanggan = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lb_update = new javax.swing.JLabel();
        lbid = new javax.swing.JLabel();
        lbjenis = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));

        jPanel2.setBackground(new java.awt.Color(255, 153, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(1138, 85));

        lb_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-exit-20.png"))); // NOI18N
        lb_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_keluarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(1108, Short.MAX_VALUE)
                .addComponent(lb_keluar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lb_keluar)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(1138, 598));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1078, 541));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("Aplikasi Kasir Restoran merupakan sebuah Sistem yang digunakan untuk melakukan transaksi ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("Pembayaran kasir pada Restoran.");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("Copyright@ditaolivia");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setText("BANTUAN");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-whatsapp-20.png"))); // NOI18N
        jLabel6.setText("  0896 0768 6571");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-instagram-20.png"))); // NOI18N
        jLabel8.setText(" dyolivyaa");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-email-20.png"))); // NOI18N
        jLabel9.setText(" ditaolivia@gmail.com");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel2)
                .addGap(0, 120, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(377, 377, 377))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(442, 442, 442))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(104, 104, 104)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addGap(429, 429, 429))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(476, 476, 476))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(54, 54, 54)
                .addComponent(jLabel4)
                .addGap(55, 55, 55)
                .addComponent(jLabel5)
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("TENTANG");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel11.setText("KASIR");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-dashboard-20.png"))); // NOI18N
        jLabel12.setText(" Dashboard");

        lbtransaksi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-receipt-and-change-20.png"))); // NOI18N
        lbtransaksi.setText(" Transaksi");
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

        lb_datapelanggan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_datapelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-people-20.png"))); // NOI18N
        lb_datapelanggan.setText(" Data Pelanggan");
        lb_datapelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_datapelangganMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-about-20.png"))); // NOI18N
        jLabel16.setText(" Tentang");

        lb_update.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-table-20.png"))); // NOI18N
        lb_update.setText(" Update Status Meja");
        lb_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_updateMouseClicked(evt);
            }
        });

        lbid.setForeground(new java.awt.Color(255, 255, 255));
        lbid.setText("id");

        lbjenis.setForeground(new java.awt.Color(255, 255, 255));
        lbjenis.setText("jenis");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(85, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(77, 77, 77))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(lb_catatantransaksi)
                            .addComponent(lb_datapelanggan)
                            .addComponent(jLabel16)
                            .addComponent(lb_update)
                            .addComponent(lbtransaksi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lbjenis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbid)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lbjenis))
                    .addComponent(lbid))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(lb_datapelanggan)
                        .addGap(18, 18, 18)
                        .addComponent(lb_update)
                        .addGap(18, 18, 18)
                        .addComponent(lbtransaksi)
                        .addGap(18, 18, 18)
                        .addComponent(lb_catatantransaksi)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16))))
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

    private void lbtransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbtransaksiMouseClicked
        // TODO add your handling code here:
        new  FromTransaksi().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+lbid.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbid.getText().equals(r.getString("id_pegawai")) &&
                        r.getString("jenis").equals("kasir")){
                    FromTransaksi.lbjabatan.setText(r.getString(4));
                    FromTransaksi.txtidpegawai.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lbtransaksiMouseClicked

    private void lb_catatantransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_catatantransaksiMouseClicked
        // TODO add your handling code here:
        new  FromCatatanTransaksi().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+lbid.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbid.getText().equals(r.getString("id_pegawai")) &&
                        r.getString("jenis").equals("kasir")){
                    FromCatatanTransaksi.txtjabatan.setText(r.getString(4));
                    FromCatatanTransaksi.txtidpegawai.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_catatantransaksiMouseClicked

    private void lb_datapelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_datapelangganMouseClicked
        // TODO add your handling code here:
        new  FromPelanggan().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+lbid.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbid.getText().equals(r.getString("id_pegawai")) &&
                        r.getString("jenis").equals("kasir")){
                    FromPelanggan.lb_jenis.setText(r.getString(4));
                    FromPelanggan.lbid.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_datapelangganMouseClicked

    private void lb_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_updateMouseClicked
        // TODO add your handling code here:
        new  FromUpdateStatusMeja().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+lbid.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(lbid.getText().equals(r.getString("id_pegawai")) &&
                        r.getString("jenis").equals("kasir")){
                    FromUpdateStatusMeja.lbjenis.setText(r.getString(4));
                    FromUpdateStatusMeja.lbid.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_updateMouseClicked

    private void lb_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_keluarMouseClicked
        // TODO add your handling code here:
        new FromDashboardKasir().show();
        this.dispose();
    }//GEN-LAST:event_lb_keluarMouseClicked

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
            java.util.logging.Logger.getLogger(FromTentangKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromTentangKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromTentangKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromTentangKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FromTentangKasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel lb_catatantransaksi;
    private javax.swing.JLabel lb_datapelanggan;
    private javax.swing.JLabel lb_keluar;
    private javax.swing.JLabel lb_update;
    public static javax.swing.JLabel lbid;
    public static javax.swing.JLabel lbjenis;
    private javax.swing.JLabel lbtransaksi;
    // End of variables declaration//GEN-END:variables
}
