package apkrestoran;

import aplikasi.Koneksi;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class FromLogin extends javax.swing.JFrame {
    String username, password;
    
    
    public FromLogin() {
        initComponents();
        }
        
        public void updateactive(){
        String user = txtuser.getText();
        String active = "1";
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtlogin = new javax.swing.JButton();
        txtcancel = new javax.swing.JButton();
        txtpass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 450));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/l2.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 450, 180));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("LOGIN");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 2, 70, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Username");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));

        txtuser.setPreferredSize(new java.awt.Dimension(270, 30));
        txtuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuserActionPerformed(evt);
            }
        });
        jPanel2.add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Password");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, -1, -1));

        txtlogin.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtlogin.setText("LOGIN");
        txtlogin.setPreferredSize(new java.awt.Dimension(97, 29));
        txtlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtloginActionPerformed(evt);
            }
        });
        jPanel2.add(txtlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, -1, -1));

        txtcancel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtcancel.setText("CANCEL");
        txtcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcancelActionPerformed(evt);
            }
        });
        jPanel2.add(txtcancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, -1, -1));

        txtpass.setPreferredSize(new java.awt.Dimension(270, 30));
        txtpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassActionPerformed(evt);
            }
        });
        jPanel2.add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuserActionPerformed

    private void txtloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtloginActionPerformed
        // TODO add your handling code here:
        try{
         Connection c = Koneksi.KoneksiDb();
         Statement  s = c.createStatement();
         
         String sql = "Select* From pengguna Where username ='"+txtuser.getText() 
                 +"'And password = '"+txtpass.getText()+"'";
         ResultSet r = s.executeQuery(sql);
         
        if(r.next()){
            if(txtuser.getText().equals(r.getString("username")) &&
            txtpass.getText().equals(r.getString("password")) &&
            r.getString("jenis").equals("admin")){
            FromDashboardAdmin a = new FromDashboardAdmin();
            FromDashboardAdmin.lbuser.setText(r.getString(1));
            FromDashboardAdmin.lb_jenis.setText(r.getString(4));
            FromDashboardAdmin.lbidpegawai.setText(r.getString(5));
             a.setVisible(true);
        }else if(txtuser.getText().equals(r.getString("username")) &&
            txtpass.getText().equals(r.getString("password")) &&
            r.getString("jenis").equals("kasir")){
            FromDashboardKasir a = new FromDashboardKasir();
            FromDashboardKasir.lbuser.setText(r.getString(1));
            FromDashboardKasir.lb_jenis.setText(r.getString(4));
            FromDashboardKasir.lbidpegawai.setText(r.getString(5));
            a.setVisible(true);
        }else if(txtuser.getText().equals(r.getString("username")) &&
            txtpass.getText().equals(r.getString("password")) &&
            r.getString("jenis").equals("manager")){
            FromDashboardManager a = new FromDashboardManager();
            FromDashboardManager.lbuser.setText(r.getString(1));
            FromDashboardManager.lb_jenis.setText(r.getString(4));
            FromDashboardManager.lbidpegawai.setText(r.getString(5));
            a.setVisible(true);
        }
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Periksa kembali username dan password anda !");
            txtpass.requestFocus();
        }
            }catch(Exception e){
                    System.out.println("gagal");  
        }
        
            updateactive();
    }//GEN-LAST:event_txtloginActionPerformed

    private void txtcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcancelActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_txtcancelActionPerformed

    private void txtpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassActionPerformed

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
            java.util.logging.Logger.getLogger(FromLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FromLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton txtcancel;
    private javax.swing.JButton txtlogin;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
