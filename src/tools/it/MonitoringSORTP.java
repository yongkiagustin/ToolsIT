/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.it;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jonas
 */
public class MonitoringSORTP extends javax.swing.JFrame {

    public boolean ref = false;

    /**
     * Creates new form MonitoringSORTP
     */
    public MonitoringSORTP() {

        initComponents();
        get_StatusUpload();
        get_StatusUpload2();

    }

    //Order Studio
    ArrayList<ModelStatusUpload> statusUpload() {
        String url = "jdbc:mysql://34.101.91.186/jonas_db?zeroDateTimeBehavior=convertToNull";
        String username = "jonas";
        String password = "t8c4cX7aKJe97F6h";
        
        Instant now = Instant.now();
        Instant yesterday = now.minus(1, ChronoUnit.DAYS);

        ArrayList<ModelStatusUpload> statusUploadList = new ArrayList<>();
        try (java.sql.Connection connection = DriverManager.getConnection(url, username, password)) {
            String queryRead = 
                    "SELECT\n" +
                    "	s.`name` as store,\n" +
                    "	os.production_finish_at,\n" +
                    "	os.so_number,\n" +
                    "	os.`name`,\n" +
                    "	os.status_order,\n" +
                    "	status_upload,\n" +
                    "	ops.created_at \n" +
                    "FROM\n" +
                    "	`order_studios` os\n" +
                    "	INNER JOIN order_payment_studios ops ON os.id = ops.order_studio_id\n" +
                    "	INNER JOIN orders o on os.order_id = o.id\n" +
                    "	INNER JOIN stores s on o.store_id = s.id\n" +
                    "WHERE\n" +
                    "	status_order = 6 \n" +
                    "	AND os.created_at >= '2022-01-01' \n" +
                    "	AND ops.created_at <= '"+yesterday+"' \n" +
                    "	AND os.name NOT LIKE '%Penambahan%'"+
                    "	AND os.name NOT LIKE '%Laminasi%';";
            Statement st = connection.prepareStatement(queryRead,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(queryRead);
            ModelStatusUpload modelStatusUpload;
            rs.first();
            do {
                modelStatusUpload = new ModelStatusUpload(
                        rs.getString("store"),
                        rs.getTimestamp("production_finish_at"),
                        rs.getString("so_number"),
                        rs.getString("name"),
                        rs.getInt("status_order"),
                        rs.getInt("status_upload"),
                        rs.getTimestamp("created_at"));
                statusUploadList.add(modelStatusUpload);
            } while (rs.next() == true);

        } catch (SQLException e) {

            System.out.println(e);
        }
        return statusUploadList;
    }

    public void get_StatusUpload() {

        ArrayList<ModelStatusUpload> list = statusUpload();
        DefaultTableModel model = (DefaultTableModel) jTableOS.getModel();

        Object[] row = new Object[7];

        for (int i = 0; i < list.size(); i++) {

            int jmlRow = model.getRowCount();
            row[0] = jmlRow + 1;
            row[1] = list.get(i).getStore_name();
            row[2] = list.get(i).getSo_number();
            row[3] = list.get(i).getName();
            row[4] = list.get(i).getUpdated_at();
            row[5] = list.get(i).getProduction_finish_at();
            row[6] = list.get(i).getStatus_upload();

            model.addRow(row);
        }

    }

    //Order Printings
    ArrayList<ModelStatusUpload> statusUpload2() {
        String url = "jdbc:mysql://34.101.91.186/jonas_db?zeroDateTimeBehavior=convertToNull";
        String username = "jonas";
        String password = "t8c4cX7aKJe97F6h";
        
        Instant now = Instant.now();
        Instant yesterday = now.minus(1, ChronoUnit.DAYS);

        ArrayList<ModelStatusUpload> statusUploadList2 = new ArrayList<>();
        try (java.sql.Connection connection = DriverManager.getConnection(url, username, password)) {
            String queryRead = "SELECT\n" +
                    "	s.`name` as store,\n" +
                    "	op.production_finish_at,\n" +
                    "	op.so_number,\n" +
                    "	op.`name`,\n" +
                    "	op.so_status,\n" +
                    "	status_upload,\n" +
                    "	ops.created_at \n" +
                    "FROM\n" +
                    "	`order_printings` op\n" +
                    "	INNER JOIN order_payment_printings ops ON op.id = ops.order_printing_id\n" +
                    "	INNER JOIN orders o on op.order_id = o.id\n" +
                    "	INNER JOIN stores s on o.store_id = s.id\n" +
                    "WHERE\n" +
                    "	so_status = 7 \n" +
                    "	AND op.created_at >= '2022-01-01' \n" +
                    "	AND ops.created_at <= '"+yesterday+"' \n" +
                    "	AND op.name NOT LIKE '%Penambahan%'"+
                    "	AND op.name NOT LIKE '%Laminasi%';";
            Statement st = connection.prepareStatement(queryRead,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(queryRead);
            ModelStatusUpload modelStatusUpload;
            rs.first();
            do {
                modelStatusUpload = new ModelStatusUpload(
                        rs.getString("store"),
                        rs.getTimestamp("production_finish_at"),
                        rs.getString("so_number"),
                        rs.getString("name"),
                        rs.getInt("so_status"),
                        rs.getInt("status_upload"),
                        rs.getTimestamp("created_at"));
                statusUploadList2.add(modelStatusUpload);
            } while (rs.next() == true);

        } catch (SQLException e) {

            System.out.println(e);
        }
        return statusUploadList2;
    }

    public void get_StatusUpload2() {

        ArrayList<ModelStatusUpload> list = statusUpload2();
        DefaultTableModel model = (DefaultTableModel) jTableOP.getModel();

        Object[] row = new Object[7];

        for (int i = 0; i < list.size(); i++) {

            int jmlRow = model.getRowCount();
           row[0] = jmlRow + 1;
            row[1] = list.get(i).getStore_name();
            row[2] = list.get(i).getSo_number();
            row[3] = list.get(i).getName();
            row[4] = list.get(i).getUpdated_at();
            row[5] = list.get(i).getProduction_finish_at();
            row[6] = list.get(i).getStatus_upload();

            model.addRow(row);
        }

    }

    public void refresh() {
        DefaultTableModel model = (DefaultTableModel) jTableOS.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        DefaultTableModel model2 = (DefaultTableModel) jTableOP.getModel();
        while (model2.getRowCount() > 0) {
            model2.removeRow(0);
        }
        get_StatusUpload();
        get_StatusUpload2();
    }

    //timer
    Timer t = new Timer(1800000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            refresh();
        }
    });

    public void QueryGantiStatusStudio() {
        String so1 = jTextField1.getText();
        String url = "jdbc:mysql://34.101.91.186/jonas_db?zeroDateTimeBehavior=convertToNull";
        String username = "jonas";
//        String password = "";
        String password = "t8c4cX7aKJe97F6h";

        try (java.sql.Connection connection = DriverManager.getConnection(url, username, password)) {
            //update status
            String queryUpdate = "UPDATE jonas_db.order_studios SET status_upload = 2 where so_number='" + so1 + "'";
            Statement st = connection.prepareStatement(queryUpdate);
            st.execute(queryUpdate);
            JOptionPane.showMessageDialog(null, "Status Berhasil Diganti");
            jTextField1.setText("");
            refresh();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            jTextField1.setText("");

        }
    }

    public void QueryGantiStatusPrinting() {
        String so2 = jTextField2.getText();
        String url = "jdbc:mysql://34.101.91.186/jonas_db?zeroDateTimeBehavior=convertToNull";
        String username = "jonas";
//        String password = "";
        String password = "t8c4cX7aKJe97F6h";

        try (java.sql.Connection connection = DriverManager.getConnection(url, username, password)) {
            //update status
            String queryUpdate = "UPDATE jonas_db.order_printings SET status_upload = 2 where so_number='" + so2 + "'";
            Statement st = connection.prepareStatement(queryUpdate);
            st.execute(queryUpdate);
            JOptionPane.showMessageDialog(null, "Status Berhasil Diganti");
            jTextField2.setText("");
            refresh();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            jTextField2.setText("");

        }
    }
     public void QueryGantiStatusOrderStudio() {
        String so1 = jTextField1.getText();
        String url = "jdbc:mysql://34.101.91.186/jonas_db?zeroDateTimeBehavior=convertToNull";
        String username = "jonas";
//        String password = "";
        String password = "t8c4cX7aKJe97F6h";

        try (java.sql.Connection connection = DriverManager.getConnection(url, username, password)) {
            //update status
            String queryUpdate = "UPDATE jonas_db.order_studios SET status_order = 6 where so_number='" + so1 + "'";
            Statement st = connection.prepareStatement(queryUpdate);
            st.execute(queryUpdate);
            JOptionPane.showMessageDialog(null, "Status Berhasil Diganti");
            jTextField1.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            jTextField1.setText("");

        }
    }
     public void QueryGantiStatusOrderPrinting() {
        String so2 = jTextField2.getText();
        String url = "jdbc:mysql://34.101.91.186/jonas_db?zeroDateTimeBehavior=convertToNull";
        String username = "jonas";
//        String password = "";
        String password = "t8c4cX7aKJe97F6h";

        try (java.sql.Connection connection = DriverManager.getConnection(url, username, password)) {
            //update status
            String queryUpdate = "UPDATE jonas_db.order_printings SET so_status = 7 where so_number='" + so2 + "'";
            Statement st = connection.prepareStatement(queryUpdate);
            st.execute(queryUpdate);
            JOptionPane.showMessageDialog(null, "Status Berhasil Diganti");
            jTextField2.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            jTextField2.setText("");

        }
    }
//    ==================

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOS = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableOP = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabelonoff = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        btnPaidOP = new javax.swing.JButton();
        btn_paidOS = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MONITORING ORDER PAID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ORDER STUDIO");

        jTableOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "Store", "NO SO", "Nama Paket", "Tanggal PAID", "CTP", "Status Upload"
            }
        ));
        jTableOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableOS);
        if (jTableOS.getColumnModel().getColumnCount() > 0) {
            jTableOS.getColumnModel().getColumn(0).setMinWidth(50);
            jTableOS.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableOS.getColumnModel().getColumn(2).setMinWidth(130);
            jTableOS.getColumnModel().getColumn(2).setPreferredWidth(130);
            jTableOS.getColumnModel().getColumn(2).setMaxWidth(130);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ORDER PRINTING");

        jTableOP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "Store", "NO SO", "Nama Paket", "Tanggal PAID", "CTP", "Status Upload"
            }
        ));
        jTableOP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableOPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableOP);
        if (jTableOP.getColumnModel().getColumnCount() > 0) {
            jTableOP.getColumnModel().getColumn(0).setMinWidth(50);
            jTableOP.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableOP.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableOP.getColumnModel().getColumn(2).setMinWidth(130);
            jTableOP.getColumnModel().getColumn(2).setPreferredWidth(130);
            jTableOP.getColumnModel().getColumn(2).setMaxWidth(130);
        }

        jButton1.setText("Auto Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("(yang statusnya lebih dari 1 hari)");

        jLabelonoff.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelonoff.setForeground(new java.awt.Color(0, 255, 0));
        jLabelonoff.setText("OFF");

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton3.setText("Ganti Status Upload");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Ganti Status Upload");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnPaidOP.setText("Ganti Status ke PAID");
        btnPaidOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaidOPActionPerformed(evt);
            }
        });

        btn_paidOS.setText("Ganti Status ke PAID");
        btn_paidOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_paidOSActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("(refresh manual minimal 1 hari sekali)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPaidOP))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_paidOS)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelonoff)))
                .addContainerGap())
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabelonoff)
                    .addComponent(jButton2)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(btn_paidOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPaidOP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        QueryGantiStatusPrinting();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        QueryGantiStatusStudio();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        refresh();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (ref == false) {
            ref = true;
            jLabelonoff.setText("ON");
            t.start();
        } else {
            ref = false;
            jLabelonoff.setText("OFF");
            t.stop();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableOPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOPMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableOP.getModel();
        int selectedRowIndex = jTableOP.getSelectedRow();
        jTextField2.setText(model.getValueAt(selectedRowIndex, 1).toString());
    }//GEN-LAST:event_jTableOPMouseClicked

    private void jTableOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOSMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableOS.getModel();
        int selectedRowIndex = jTableOS.getSelectedRow();
        jTextField1.setText(model.getValueAt(selectedRowIndex, 1).toString());
    }//GEN-LAST:event_jTableOSMouseClicked

    private void btnPaidOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaidOPActionPerformed
        QueryGantiStatusOrderPrinting();
    }//GEN-LAST:event_btnPaidOPActionPerformed

    private void btn_paidOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_paidOSActionPerformed
        QueryGantiStatusOrderStudio();
    }//GEN-LAST:event_btn_paidOSActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MonitoringSORTP().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPaidOP;
    private javax.swing.JButton btn_paidOS;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelonoff;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableOP;
    private javax.swing.JTable jTableOS;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
