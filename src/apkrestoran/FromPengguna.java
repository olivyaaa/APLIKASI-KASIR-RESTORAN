package apkrestoran;

import aplikasi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FromPengguna extends javax.swing.JFrame {
    private DefaultTableModel model;
    private DefaultTableModel model2;
    String username, password, email, jenis, id_pegawai, created_at, updated_at, active, nama_pegawai;


    public FromPengguna() {
        initComponents();
        Nonaktif();
        
        model = new DefaultTableModel();
        tblpengguna.setModel(model);
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Email");
        model.addColumn("Jabatan");
        model.addColumn("ID Pegawai");
        model.addColumn("Active");
        
        
        
        model2 = new DefaultTableModel();
        tblpegawai.setModel(model2);
        model2.addColumn("ID Pegawai");
        model2.addColumn("Nama Pegawai");
        
        GetDataPengguna();
        GetDataPegawai();    
    }
    
    public void GetDataPengguna(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Statement stat = (Statement) Koneksi.KoneksiDb().createStatement();
            String sql = "Select * From pengguna";
            ResultSet rs = stat.executeQuery(sql);
            
            while (rs.next()){
                Object[] obj = new Object[6];
                obj [0] = rs.getString("username");
                obj [1] = rs.getString("password");
                obj [2] = rs.getString("email");
                obj [3] = rs.getString("jenis");
                obj [4] = rs.getString("id_pegawai");
                obj [5] = rs.getString("active");
                
                model.addRow(obj);
               }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }  
    }
    
    public void GetDataPegawai(){
        model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged();
        try{
            Statement stat = (Statement) Koneksi.KoneksiDb().createStatement();
            String sql = "select * From pegawai";
            ResultSet rs = stat.executeQuery(sql);
            
            while (rs.next()){
                Object[] obj = new Object[2];
                obj [0] = rs.getString("id_pegawai");
                obj [1] = rs.getString("nama_pegawai");
                
                model2.addRow(obj);
               }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }  
    }
    
    public void SelectDataPengguna(){
        int i = tblpengguna.getSelectedRow();
        if(i == -1)
        {
            return;
        }txtuser.setText(""+model.getValueAt(i, 0));
        txtpass.setText(""+model.getValueAt(i, 1));
        txtemail.setText(""+model.getValueAt(i, 2));
        cbjabatan.setSelectedItem(""+model.getValueAt(i, 3));
        txtidpegawai.setText(""+model.getValueAt(i, 4));
        cbactive.setSelectedItem(""+model.getValueAt(i, 5));
        btn_hapus.setEnabled(true);
        btn_edit.setEnabled(true);
    }
    
    public void SelectDataPegawai(){
        int i = tblpegawai.getSelectedRow();
        if(i == -1)
        {
            return;
        }txtidpegawai.setText(""+model2.getValueAt(i, 0));
    }
    
    
    public void LoadData(){
        username = txtuser.getText();
        password = txtpass.getText();
        email = txtemail.getText();
        jenis = (String)cbjabatan.getSelectedItem();
        id_pegawai = txtidpegawai.getText();
        created_at = null;
        updated_at =null;
        active = (String)cbactive.getSelectedItem();
    }
    
    public void SimpanData(){
        LoadData();
        
        try {
            com.mysql.jdbc.Statement stat = (com.mysql.jdbc.Statement) Koneksi.KoneksiDb().createStatement();
            String sql = "Insert Into pengguna (username, password, email, jenis, id_pegawai, created_at, updated_at)" + "values" + "('"+username+"','"+password+"','"+email+"','"+jenis+"','"+id_pegawai+"','"+created_at+"','"+updated_at+"')";
            PreparedStatement p = (PreparedStatement) Koneksi.KoneksiDb().prepareStatement(sql);
            p.executeUpdate();
            
            GetDataPengguna();
            Nonaktif();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        }catch(SQLException ex){
        }
    }
    
    public void HapusData(){
        LoadData();
        
        int pesan = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data pengguna " + username + "?","Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
        if(pesan == JOptionPane.OK_OPTION){
            try {
               com.mysql.jdbc.Statement stat = (com.mysql.jdbc.Statement) Koneksi.KoneksiDb().createStatement();
               String sql = "Delete From pengguna Where username='"+username+"'";
               PreparedStatement p = (PreparedStatement) Koneksi.KoneksiDb().prepareStatement(sql);
               p.executeUpdate();
               
               GetDataPengguna();
               
               JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            } catch(SQLException error){
                JOptionPane.showConfirmDialog(null, error.getMessage());
            }
        }   
    }
    
    public void Nonaktif(){
        txtuser.setEnabled(false);
        txtpass.setEnabled(false);
        txtemail.setEnabled(false);
        cbjabatan.setEnabled(false);
        txtidpegawai.setEnabled(false);
        cbactive.setEnabled(false);
        btn_simpan.setEnabled(false);  
        btn_edit.setEnabled(false); 
        btn_hapus.setEnabled(false);  
    }
    
    public void Aktif(){
        txtuser.setEnabled(true);
        txtpass.setEnabled(true);
        txtemail.setEnabled(true);
        cbjabatan.setEnabled(true);
        txtidpegawai.setEnabled(false);
        cbactive.setEnabled(true);
        btn_simpan.setEnabled(true);  
        btn_tambah.setEnabled(false); 
        txtuser.requestFocus();
    }
    
    public void Kosongkan(){
        txtuser.setText("");
        txtpass.setText("");
        txtemail.setText("");
        cbjabatan.setSelectedItem("Tidak dipilih");
        cbactive.setSelectedItem("Tidak dipilih");
    }
    
    public void CariPengguna(){
      model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection con = Koneksi.KoneksiDb();
            Statement st = con.createStatement();
            String sql = "Select * From pengguna Where username like '%" + txtcaripengguna.getText() + "%'";
            ResultSet rs =st.executeQuery(sql);
            
            while(rs.next()){
                Object[] ob = new Object[8];
                ob [0] = rs.getString(1);
                ob [1] = rs.getString(2);
                ob [2] = rs.getString(3);
                ob [3] = rs.getString(4);
                ob [4] = rs.getString(5);
                ob [5] = rs.getString(6);
                ob [6] = rs.getString(7);
                ob [7] = rs.getString(8);
                
                model.addRow(ob);
            }
        }catch(SQLException e){
            System.out.println(e);
            }   
    }
    
    public void CariPegawai(){
      model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged();
        try{
            Connection con = Koneksi.KoneksiDb();
            Statement st = con.createStatement();
            String sql = "Select * From pegawai Where nama_pegawai like '%" + txtcaripegawai.getText() + "%'";
            ResultSet rs =st.executeQuery(sql);
            
            while(rs.next()){
                Object[] ob = new Object[2];
                ob [0] = rs.getString(1);
                ob [1] = rs.getString(2);
                
                model2.addRow(ob);
            }
        }catch(SQLException e){
            System.out.println(e);
            }   
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lb_keluar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        txtpass = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbjabatan = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        txtcaripegawai = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpegawai = new javax.swing.JTable();
        txtidpegawai = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbactive = new javax.swing.JComboBox<>();
        txtcaripengguna = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblpengguna = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btn_tambah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lb_datapegawai = new javax.swing.JLabel();
        lb_datapengguna = new javax.swing.JLabel();
        lb_logaktifitas = new javax.swing.JLabel();
        lb_tentang = new javax.swing.JLabel();

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
                .addContainerGap(1095, Short.MAX_VALUE)
                .addComponent(lb_keluar)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lb_keluar)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(1138, 598));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1078, 541));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Username");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Password");

        txtuser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtuser.setPreferredSize(new java.awt.Dimension(250, 25));
        txtuser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtuserKeyTyped(evt);
            }
        });

        txtpass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtpass.setPreferredSize(new java.awt.Dimension(250, 25));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Email");

        txtemail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtemail.setPreferredSize(new java.awt.Dimension(250, 25));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Jabatan");

        cbjabatan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbjabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak dipilih", "admin", "kasir", "manager" }));
        cbjabatan.setPreferredSize(new java.awt.Dimension(250, 25));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("ID Pegawai");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField4.setPreferredSize(new java.awt.Dimension(250, 25));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Active");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak dipilih", "1", "0" }));
        jComboBox2.setPreferredSize(new java.awt.Dimension(250, 25));

        txtcaripegawai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtcaripegawai.setPreferredSize(new java.awt.Dimension(650, 30));
        txtcaripegawai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcaripegawaiKeyTyped(evt);
            }
        });

        tblpegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID Pegawai", "Nama Pegawai"
            }
        ));
        tblpegawai.setPreferredSize(new java.awt.Dimension(700, 150));
        tblpegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpegawaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblpegawai);

        txtidpegawai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtidpegawai.setPreferredSize(new java.awt.Dimension(250, 25));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Active");

        cbactive.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbactive.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak dipilih", "1", "0" }));
        cbactive.setPreferredSize(new java.awt.Dimension(250, 25));

        txtcaripengguna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtcaripengguna.setPreferredSize(new java.awt.Dimension(650, 30));
        txtcaripengguna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcaripenggunaKeyTyped(evt);
            }
        });

        jScrollPane2.setPreferredSize(new java.awt.Dimension(700, 150));

        tblpengguna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Username", "Password", "Email", "Jabatan", "ID Pegawai", "Active"
            }
        ));
        tblpengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpenggunaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblpengguna);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-20.png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-20.png"))); // NOI18N

        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-plus-20.png"))); // NOI18N
        btn_tambah.setText(" Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-save-20.png"))); // NOI18N
        btn_simpan.setText(" Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-edit-20.png"))); // NOI18N
        btn_edit.setText(" Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-delete-20.png"))); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbactive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_tambah)
                        .addGap(18, 18, 18)
                        .addComponent(btn_simpan)
                        .addGap(18, 18, 18)
                        .addComponent(btn_edit)
                        .addGap(18, 18, 18)
                        .addComponent(btn_hapus))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtcaripengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtcaripegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)))
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcaripegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcaripengguna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbactive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_tambah)
                            .addComponent(btn_simpan)
                            .addComponent(btn_edit)
                            .addComponent(btn_hapus))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("DATA PENGGUNA");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("ADMIN");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-dashboard-20.png"))); // NOI18N
        jLabel13.setText(" Dashboard");

        lb_datapegawai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_datapegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-people-20.png"))); // NOI18N
        lb_datapegawai.setText(" Data Pegawai");
        lb_datapegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_datapegawaiMouseClicked(evt);
            }
        });

        lb_datapengguna.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_datapengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-people-20.png"))); // NOI18N
        lb_datapengguna.setText(" Data Pengguna");
        lb_datapengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_datapenggunaMouseClicked(evt);
            }
        });

        lb_logaktifitas.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_logaktifitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-people-20.png"))); // NOI18N
        lb_logaktifitas.setText(" Log Aktivitas Pegawai");
        lb_logaktifitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_logaktifitasMouseClicked(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_datapegawai))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_datapengguna))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_logaktifitas))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_tentang)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3)))
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(lb_datapegawai)
                        .addGap(18, 18, 18)
                        .addComponent(lb_datapengguna)
                        .addGap(18, 18, 18)
                        .addComponent(lb_logaktifitas)
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

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        Kosongkan();
        Aktif();
        txtuser.requestFocus();   
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String username = txtuser.getText();
        String password = txtpass.getText();
        String email = txtemail.getText();
        String jenis = (String)cbjabatan.getSelectedItem();
        String id_pegawai = txtidpegawai.getText();
        String created_at = null;
        String updated_at = null;
        String active = (String)cbactive.getSelectedItem();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            String sql = "Insert Into pengguna Values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, username);
            p.setString(2, password);
            p.setString(3, email);
            p.setString(4, jenis);
            p.setString(5, id_pegawai);
            p.setString(6, created_at);
            p.setString(7, updated_at);
            p.setString(8, active);
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        }catch(Exception e){
            System.out.println("Terjadi kesalahan ");
        }finally{
            GetDataPengguna();
            Kosongkan();
        }
        Nonaktif();
        btn_tambah.setEnabled(true);
        btn_simpan.setEnabled(false);
        btn_edit.setEnabled(false);
        btn_hapus.setEnabled(false);
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        int i= tblpengguna.getSelectedRow();
        if(i == -1){
            return;
        }
        String username = (String)model.getValueAt(i, 0);
        String username2 = txtuser.getText();
        String password = txtpass.getText();
        String email = txtemail.getText();
        String jenis = (String)cbjabatan.getSelectedItem();
        String id_pegawai = txtidpegawai.getText();
        String created_at = null;
        String updated_at = null;
        String active = (String)cbactive.getSelectedItem();
        
        try{
          Connection c = Koneksi.KoneksiDb();
          String sql = "Update pengguna set username2=?, password=?, email=?, jenis=?, id_pegawai=?, created_at=?, updated_at=?, active=? where username=?"; 
          PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, username2);
            p.setString(2, password);
            p.setString(3, email);
            p.setString(4, jenis);
            p.setString(5, id_pegawai);
            p.setString(6, created_at);
            p.setString(7, updated_at);
            p.setString(8, active);
            p.setString(9, username);
            
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        }catch(Exception e){
            System.out.println("update error ");
        }finally{
            GetDataPengguna();
            Kosongkan();
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        HapusData();
        Kosongkan();
        Nonaktif();
        btn_tambah.setEnabled(true);
        btn_simpan.setEnabled(false);
        btn_edit.setEnabled(false);
        btn_hapus.setEnabled(false);
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void tblpegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpegawaiMouseClicked
        // TODO add your handling code here:
        SelectDataPegawai();
    }//GEN-LAST:event_tblpegawaiMouseClicked

    private void tblpenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpenggunaMouseClicked
        // TODO add your handling code here:
        SelectDataPengguna();
        Aktif();
        txtuser.setEnabled(false);
        btn_simpan.setEnabled(false);
    }//GEN-LAST:event_tblpenggunaMouseClicked

    private void txtuserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtuserKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuserKeyTyped

    private void txtcaripegawaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcaripegawaiKeyTyped
        // TODO add your handling code here:
        CariPegawai();
    }//GEN-LAST:event_txtcaripegawaiKeyTyped

    private void txtcaripenggunaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcaripenggunaKeyTyped
        // TODO add your handling code here:
        CariPengguna();
    }//GEN-LAST:event_txtcaripenggunaKeyTyped

    private void lb_datapegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_datapegawaiMouseClicked
        // TODO add your handling code here:
        new FromPegawai().show();
        this.dispose();
    }//GEN-LAST:event_lb_datapegawaiMouseClicked

    private void lb_datapenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_datapenggunaMouseClicked
        // TODO add your handling code here:
        new FromPengguna().show();
        this.dispose();
    }//GEN-LAST:event_lb_datapenggunaMouseClicked

    private void lb_logaktifitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_logaktifitasMouseClicked
        // TODO add your handling code here:
        new FromLogAktivitasPegawaiAdmin().show();
        this.dispose();
    }//GEN-LAST:event_lb_logaktifitasMouseClicked

    private void lb_tentangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_tentangMouseClicked
        // TODO add your handling code here:
        new FromTentangAdmin().show();
        this.dispose();
    }//GEN-LAST:event_lb_tentangMouseClicked

    private void lb_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_keluarMouseClicked
        // TODO add your handling code here:
        new FromDashboardAdmin().show();
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
            java.util.logging.Logger.getLogger(FromPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FromPengguna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JComboBox<String> cbactive;
    private javax.swing.JComboBox<String> cbjabatan;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lb_datapegawai;
    private javax.swing.JLabel lb_datapengguna;
    private javax.swing.JLabel lb_keluar;
    private javax.swing.JLabel lb_logaktifitas;
    private javax.swing.JLabel lb_tentang;
    private javax.swing.JTable tblpegawai;
    private javax.swing.JTable tblpengguna;
    private javax.swing.JTextField txtcaripegawai;
    private javax.swing.JTextField txtcaripengguna;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtidpegawai;
    private javax.swing.JTextField txtpass;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
