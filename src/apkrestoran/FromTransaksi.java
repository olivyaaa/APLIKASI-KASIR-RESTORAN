package apkrestoran;

import aplikasi.Koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FromTransaksi extends javax.swing.JFrame {
    java.util.Date tglsekarang = new java.util.Date();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private String tanggal = format.format(tglsekarang);
    private DefaultTableModel model;
    private DefaultTableModel model2;
    
    
    public FromTransaksi() {
        initComponents();
        Nonaktif();
        
        model = new DefaultTableModel();
        tblmenu.setModel(model);
        model.addColumn("ID Menu");
        model.addColumn("Nama Menu");
        model.addColumn("Kategori");
        model.addColumn("Harga");
        model.addColumn("Stok");
        
        model2 = new DefaultTableModel();
        tbl_dettransaksi.setModel(model2);
        model2.addColumn("ID Transaksi");
        model2.addColumn("ID Menu");
        model2.addColumn("Harga");
        model2.addColumn("Jumlah");
        model2.addColumn("Harga Total");
        
        setJam();
        Utama();
        GetDataMenu();
        txttanggal.setText(tanggal);
        txttotalbayar.setText("0");
        txtbayar.setText("0");
        txtkembali.setText("0");
        
        txtjumlah.setEnabled(false);
        txtbayar.setEnabled(false);
        btn_tambah2.setEnabled(false);
        btn_kurang.setEnabled(false);
        btn_proses.setEnabled(false);
        btn_cetak.setEnabled(false);
        btn_carimeja.setEnabled(false);
        btn_caripelanggan.setEnabled(false);
        txtcarimenu.setEnabled(false);
      }
    
    public void GetDataMenu(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Statement stat = (Statement)Koneksi.KoneksiDb().createStatement();
            String sql = "Select * From menu";
            ResultSet rs = stat.executeQuery(sql);
            
            while(rs.next()){
            Object[] obj = new Object[5];
            obj [0] = rs.getString("id_menu");
            obj [1] = rs.getString("nama_menu");
            obj [2] = rs.getString("jenis");
            obj [3] = rs.getString("harga");
            obj [4] = rs.getString("stok");
            
            model.addRow(obj);
        }
            rs.close();
            stat.close();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    
    public void SelectDataMenu(){
        int i = tblmenu.getSelectedRow();
        if(i == -1)
        {
            return;
        }txtidmenu.setText(""+model.getValueAt(i, 0));
        txtharga.setText(""+model.getValueAt(i, 3));
    }
    
    public void GetDataDetTransaksi(){
        DefaultTableModel model = (DefaultTableModel)tbl_dettransaksi.getModel();
        model.addRow(new Object[]{
            txtidtransaksi.getText(),
            txtidmenu.getText(),
            txtharga.getText(),
            txtjumlah.getText(),
            txthargatotal.getText()
        }   ); 
    }
    
    public void IdOtomatis(){
        try{
            com.mysql.jdbc.Statement st = (com.mysql.jdbc.Statement) Koneksi.KoneksiDb().createStatement();
            String sql = "Select * From transaksi order by id_transaksi desc";
            
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                String id = rs.getString("id_transaksi").substring(3);
                String AN = ""+(Integer.parseInt(id)+1);
                String nol = "";
                
                if(AN.length()==1)
                {nol="00";}
                else if(AN.length()==2)
                {nol="0";}
                else if(AN.length()==3)
                {nol="";}       
                    txtidtransaksi.setText("TR"+nol+AN);
            }else{
                txtidtransaksi.setText("TR001");
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    
    public void Nonaktif(){
        txtidtransaksi.setEnabled(false);
        txttanggal.setEnabled(false);
        txtjam.setEnabled(false);
        txtidpegawai.setEnabled(false);
        txtidmenu.setEnabled(false);
        txtharga.setEnabled(false);
        txthargatotal.setEnabled(false);
        txttotalbayar.setEnabled(false); 
        txtkembali.setEnabled(false);
        txtidpelanggan.setEnabled(false);
        txtidmeja.setEnabled(false);
    }
    
    public void Cari(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection con = Koneksi.KoneksiDb();
            Statement st = con.createStatement();
            String sql = "Select * From menu Where id_menu like '%" + txtcarimenu.getText() + "%'" + 
                    "or nama_menu like '%" + txtcarimenu.getText() + "%'";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Object[] ob = new Object[5];
                ob [0] = rs.getString(1);
                ob [1] = rs.getString(2);
                ob [2] = rs.getString(3);
                ob [3] = rs.getString(4);
                ob [4] = rs.getString(5);
                
                model.addRow(ob);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void setJam(){
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
                
                txtjam.setText(jam + ":" + menit + ":" + detik + ""); 
          }
      };
      new Timer (1000, taskPerform).start();
    }
    
    public void TotalBiaya(){
        int jumlahBaris = tbl_dettransaksi.getRowCount();
        int totalBiaya = 0;
        int jumlah, harga;
        for(int i = 0; i < jumlahBaris; i++){
            jumlah = Integer.parseInt(tbl_dettransaksi.getValueAt(i, 2).toString());
            harga = Integer.parseInt(tbl_dettransaksi.getValueAt(i, 3).toString());
            totalBiaya = totalBiaya + (jumlah * harga);
        }
        txttotalbayar.setText(String.valueOf(totalBiaya));   
    }
    
    public void TambahTransaksi(){
        int jumlah, harga, total;
        jumlah = Integer.valueOf(txtjumlah.getText());
        harga = Integer.valueOf(txtharga.getText());
        total = jumlah * harga;
        
        txthargatotal.setText(String.valueOf(total));
        GetDataDetTransaksi();
        TotalBiaya();
        Kosongkan2();
    }
    
    public void Kosong(){
        DefaultTableModel model = (DefaultTableModel) tbl_dettransaksi.getModel();
        
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
    }
    
    public void Utama(){
        txtidpelanggan.setText("");
        txtcarimenu.setText("");
        txtidmenu.setText("");
        txtidmeja.setText("");
        txtharga.setText("");
        txtjumlah.setText("");
        IdOtomatis();
    }
    
    public void Kosongkan(){
        txtcarimenu.setText("");
        txttotalbayar.setText("0");
        txtbayar.setText("0");
        txtkembali.setText("0");
    }
    
    public void Kosongkan2(){
        txtidmenu.setText("");
        txtharga.setText("");
        txtjumlah.setText("");
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lb_keluar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtidpelanggan = new javax.swing.JTextField();
        txtcarimenu = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtidmeja = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmenu = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        txtidtransaksi = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txttanggal = new javax.swing.JTextField();
        txtjam = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtidpegawai = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_cetak = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtidmenu = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        txtjumlah = new javax.swing.JTextField();
        txthargatotal = new javax.swing.JTextField();
        btn_tambah2 = new javax.swing.JButton();
        btn_kurang = new javax.swing.JButton();
        btn_proses = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtbayar = new javax.swing.JTextField();
        txtkembali = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_dettransaksi = new javax.swing.JTable();
        txttotalbayar = new javax.swing.JTextField();
        btn_caripelanggan = new javax.swing.JButton();
        btn_carimeja = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lb_catatantransaksi = new javax.swing.JLabel();
        lb_datapelanggan = new javax.swing.JLabel();
        lb_statusmeja = new javax.swing.JLabel();
        lb_tentang = new javax.swing.JLabel();
        lbjabatan = new javax.swing.JLabel();

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
                .addGap(32, 32, 32)
                .addComponent(lb_keluar)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(1138, 598));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1078, 541));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("ID Pelanggan :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("Pencarian Menu :");

        txtidpelanggan.setPreferredSize(new java.awt.Dimension(200, 28));
        txtidpelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidpelangganActionPerformed(evt);
            }
        });

        txtcarimenu.setPreferredSize(new java.awt.Dimension(200, 28));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("ID Meja :");

        txtidmeja.setPreferredSize(new java.awt.Dimension(200, 28));
        txtidmeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidmejaActionPerformed(evt);
            }
        });

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 200));

        tblmenu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmenuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblmenu);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel18.setText("ID Transaksi");

        txtidtransaksi.setPreferredSize(new java.awt.Dimension(105, 28));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel19.setText("Tanggal / Jam");

        txttanggal.setPreferredSize(new java.awt.Dimension(105, 28));

        txtjam.setPreferredSize(new java.awt.Dimension(105, 28));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel20.setText("ID Pegawai");

        txtidpegawai.setPreferredSize(new java.awt.Dimension(105, 28));

        btn_tambah.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.setPreferredSize(new java.awt.Dimension(105, 28));
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_cetak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_cetak.setText("Cetak");
        btn_cetak.setPreferredSize(new java.awt.Dimension(105, 28));
        btn_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel15.setText("ID Menu :");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setText("Harga :");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel17.setText("Jumlah :");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel21.setText("Harga Total :");

        txtidmenu.setPreferredSize(new java.awt.Dimension(150, 28));

        txtharga.setPreferredSize(new java.awt.Dimension(150, 28));
        txtharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargaActionPerformed(evt);
            }
        });

        txtjumlah.setPreferredSize(new java.awt.Dimension(150, 28));
        txtjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahActionPerformed(evt);
            }
        });

        txthargatotal.setPreferredSize(new java.awt.Dimension(150, 28));
        txthargatotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargatotalActionPerformed(evt);
            }
        });

        btn_tambah2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-plus-20.png"))); // NOI18N
        btn_tambah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah2ActionPerformed(evt);
            }
        });

        btn_kurang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-minus-20.png"))); // NOI18N
        btn_kurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kurangActionPerformed(evt);
            }
        });

        btn_proses.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_proses.setText("Proses");
        btn_proses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prosesActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel22.setText("Total Bayar :");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel23.setText("Bayar :");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel24.setText("Kembali :");

        txtbayar.setPreferredSize(new java.awt.Dimension(6, 28));
        txtbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbayarActionPerformed(evt);
            }
        });

        txtkembali.setPreferredSize(new java.awt.Dimension(6, 28));
        txtkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkembaliActionPerformed(evt);
            }
        });

        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 200));

        tbl_dettransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_dettransaksi);

        txttotalbayar.setPreferredSize(new java.awt.Dimension(150, 28));

        btn_caripelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-20.png"))); // NOI18N
        btn_caripelanggan.setPreferredSize(new java.awt.Dimension(55, 35));
        btn_caripelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_caripelangganActionPerformed(evt);
            }
        });

        btn_carimeja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-20.png"))); // NOI18N
        btn_carimeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_carimejaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(txtidpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidtransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btn_cetak, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_tambah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(316, 316, 316))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(102, 102, 102))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtidpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_caripelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidmeja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_carimeja, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)
                                        .addComponent(btn_proses))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtidmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(btn_tambah2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_kurang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtbayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtkembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txttotalbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthargatotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcarimenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_caripelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_carimeja, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtidmeja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtcarimenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidtransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtidmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttotalbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))
                                .addGap(44, 44, 44))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel16)
                                            .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel17)
                                            .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btn_tambah2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_proses, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_kurang))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txthargatotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addGap(15, 15, 15)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("TRANSAKSI");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("KASIR");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-dashboard-20.png"))); // NOI18N
        jLabel3.setText(" Dashboard");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-receipt-and-change-20.png"))); // NOI18N
        jLabel4.setText(" Transaksi");

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

        lb_statusmeja.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lb_statusmeja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-table-20.png"))); // NOI18N
        lb_statusmeja.setText(" Update Status Meja");
        lb_statusmeja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_statusmejaMouseClicked(evt);
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

        lbjabatan.setForeground(new java.awt.Color(255, 255, 255));
        lbjabatan.setText("jabatan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(77, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lb_catatantransaksi)
                            .addComponent(lb_datapelanggan)
                            .addComponent(lb_statusmeja)
                            .addComponent(lb_tentang)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(258, 258, 258)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbjabatan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lbjabatan))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lb_datapelanggan)
                        .addGap(18, 18, 18)
                        .addComponent(lb_statusmeja)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
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

    private void txtidpelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidpelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidpelangganActionPerformed

    private void txtjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlahActionPerformed
        // TODO add your handling code here:
        int totalharga = 0;
        int jumlah, harga;
        jumlah = Integer.parseInt(txtjumlah.getText());
        harga = Integer.parseInt(txtharga.getText());
        totalharga = (jumlah * harga);
        
        txthargatotal.setText(String.valueOf(totalharga));
    }//GEN-LAST:event_txtjumlahActionPerformed

    private void txthargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargaActionPerformed

    private void btn_prosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prosesActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)tbl_dettransaksi.getModel();
        
        String notrans = txtidtransaksi.getText();
        String tanggal = txttanggal.getText();
        String idpegawai = txtidpegawai.getText();
        String idpel = txtidpelanggan.getText();
        String meja = txtidmeja.getText();
        String subtotal = txthargatotal.getText();
        String totalbayar = txttotalbayar.getText();
        String bayar = txtbayar.getText();
        String kembali = txtkembali.getText();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            String sql = "Insert Into transaksi Values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, notrans);
            p.setString(2, tanggal);
            p.setString(3, idpegawai);
            p.setString(4, idpel);
            p.setString(5, meja);
            p.setString(6, subtotal);
            p.setString(7, totalbayar);
            p.setString(8, bayar);
            p.setString(9, kembali);
            p.executeUpdate();
            p.close();
        }catch(Exception ex){
            Logger.getLogger(FromTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try{
            Connection c = Koneksi.KoneksiDb();
            int baris = tbl_dettransaksi.getRowCount();
            for (int i = 0; i < baris; i++){
                String sql = "Insert Into detail_transaksi (id_transaksi, id_menu, harga, jumlah, total_harga) values ('"
                        +tbl_dettransaksi.getValueAt(i, 0)+"','"+tbl_dettransaksi.getValueAt(i, 1)+"','"+tbl_dettransaksi.getValueAt(i, 2)
                        +"','"+tbl_dettransaksi.getValueAt(i, 3)+"','"+tbl_dettransaksi.getValueAt(i, 4)+"')";
                PreparedStatement p = c.prepareStatement(sql);
                p.executeUpdate();
                p.close();          
            }
        }catch(Exception ex){
            Logger.getLogger(FromTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        Kosongkan();
        Utama();
        Kosong();
        IdOtomatis();
        GetDataMenu();
        txtjumlah.setEnabled(false);
        txtbayar.setEnabled(false);
        btn_tambah2.setEnabled(false);
        btn_kurang.setEnabled(false);
        btn_proses.setEnabled(false);
        btn_carimeja.setEnabled(false);
        btn_caripelanggan.setEnabled(false);
        txtcarimenu.setEnabled(false);
       
    }//GEN-LAST:event_btn_prosesActionPerformed

    private void txtkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkembaliActionPerformed

    private void txthargatotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargatotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargatotalActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        txtjumlah.setEnabled(true);
        txtbayar.setEnabled(true);
        btn_tambah2.setEnabled(true);
        btn_kurang.setEnabled(true);
        btn_proses.setEnabled(true);
        btn_cetak.setEnabled(true);
        btn_carimeja.setEnabled(true);
        btn_caripelanggan.setEnabled(true);
        txtcarimenu.setEnabled(true);
        Kosong();
        GetDataMenu();
        Kosongkan();
        Kosongkan2();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void txtidmejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidmejaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidmejaActionPerformed

    private void btn_caripelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_caripelangganActionPerformed
        // TODO add your handling code here:
        DaftarPelanggan a = new DaftarPelanggan();
        a.setVisible(true);
    }//GEN-LAST:event_btn_caripelangganActionPerformed

    private void btn_carimejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_carimejaActionPerformed
        // TODO add your handling code here:
        DaftarMeja a = new DaftarMeja();
        a.setVisible(true);
    }//GEN-LAST:event_btn_carimejaActionPerformed

    private void tblmenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmenuMouseClicked
        // TODO add your handling code here:
        SelectDataMenu();
        
        txtjumlah.requestFocus();
    }//GEN-LAST:event_tblmenuMouseClicked

    private void txtbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbayarActionPerformed
        // TODO add your handling code here:
        int bayar, total, kembalian;
        
        total = Integer.valueOf(txttotalbayar.getText());
        bayar = Integer.valueOf(txtbayar.getText());
        
        if(total > bayar){
            JOptionPane.showMessageDialog(null, "Uang tidak cukup melakukan pembayaran");
        }else{
            kembalian = bayar - total;
            txtkembali.setText(String.valueOf(kembalian));
            
        }
    }//GEN-LAST:event_txtbayarActionPerformed

    private void btn_tambah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah2ActionPerformed
        // TODO add your handling code here:
        TambahTransaksi();
    }//GEN-LAST:event_btn_tambah2ActionPerformed

    private void btn_kurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kurangActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)tbl_dettransaksi.getModel();
        int row = tbl_dettransaksi.getSelectedRow();
        model.removeRow(row);
        TotalBiaya();
        txtbayar.setText("0");
        txtkembali.setText("0");
    }//GEN-LAST:event_btn_kurangActionPerformed

    private void lb_catatantransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_catatantransaksiMouseClicked
        // TODO add your handling code here:
        new FromCatatanTransaksi().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+txtidpegawai.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(txtidpegawai.getText().equals(r.getString("id_pegawai")) &&
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
            
            String sql = "Select * From pengguna Where id_pegawai = '"+txtidpegawai.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(txtidpegawai.getText().equals(r.getString("id_pegawai")) &&
                        r.getString("jenis").equals("kasir")){
                    FromPelanggan.lb_jenis.setText(r.getString(4));
                    FromPelanggan.lbid.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_datapelangganMouseClicked

    private void lb_statusmejaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_statusmejaMouseClicked
        // TODO add your handling code here:
        new  FromUpdateStatusMeja().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+txtidpegawai.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(txtidpegawai.getText().equals(r.getString("id_pegawai")) &&
                        r.getString("jenis").equals("kasir")){
                    FromUpdateStatusMeja.lbjenis.setText(r.getString(4));
                    FromUpdateStatusMeja.lbid.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_statusmejaMouseClicked

    private void lb_tentangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_tentangMouseClicked
        // TODO add your handling code here:
        new  FromTentangKasir().show();
        this.dispose();
        
        try{
            Connection c = Koneksi.KoneksiDb();
            Statement s = c.createStatement();
            
            String sql = "Select * From pengguna Where id_pegawai = '"+txtidpegawai.getText()+"'";
            ResultSet r = s.executeQuery(sql);
            
            if(r.next()){
                if(txtidpegawai.getText().equals(r.getString("id_pegawai")) &&
                        r.getString("jenis").equals("kasir")){
                    FromTentangKasir.lbjenis.setText(r.getString(4));
                    FromTentangKasir.lbid.setText(r.getString(5));
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_lb_tentangMouseClicked

    private void lb_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_keluarMouseClicked
        // TODO add your handling code here:
        new FromDashboardKasir().show();
        this.dispose();
    }//GEN-LAST:event_lb_keluarMouseClicked

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakActionPerformed
        // TODO add your handling code here:
        String nota = JOptionPane.showInputDialog("Masukan ID Transaksi");
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String DB = "jdbc:mysql://localhost/db_restoran1";
            String user = "root";
            String pass = "";
            
            con = DriverManager.getConnection(DB, user, pass);
            Statement  stat = con.createStatement();
            
        try{
            String report = ("src\\apkrestoran\\notarestoran.jrxml");
            HashMap hash = new HashMap();
            hash.put("kode", nota);
            JasperReport jasperReport = JasperCompileManager.compileReport(report);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, con);
            JasperViewer.viewReport(jasperPrint, false); 
        }catch(Exception ex){
            Logger.getLogger(FromTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }catch(Exception e){ 
        }
    }//GEN-LAST:event_btn_cetakActionPerformed

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
            java.util.logging.Logger.getLogger(FromTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FromTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_carimeja;
    private javax.swing.JButton btn_caripelanggan;
    private javax.swing.JButton btn_cetak;
    private javax.swing.JButton btn_kurang;
    private javax.swing.JButton btn_proses;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tambah2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_catatantransaksi;
    private javax.swing.JLabel lb_datapelanggan;
    private javax.swing.JLabel lb_keluar;
    private javax.swing.JLabel lb_statusmeja;
    private javax.swing.JLabel lb_tentang;
    public static javax.swing.JLabel lbjabatan;
    private javax.swing.JTable tbl_dettransaksi;
    private javax.swing.JTable tblmenu;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtcarimenu;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txthargatotal;
    public static javax.swing.JTextField txtidmeja;
    private javax.swing.JTextField txtidmenu;
    public static javax.swing.JTextField txtidpegawai;
    public static javax.swing.JTextField txtidpelanggan;
    private javax.swing.JTextField txtidtransaksi;
    private javax.swing.JTextField txtjam;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtkembali;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttotalbayar;
    // End of variables declaration//GEN-END:variables
}
