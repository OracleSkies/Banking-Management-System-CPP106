package com.mycompany.bankingmanagementsystem;


import cellAction.PanelAction;
import cellAction.TableActionCellEditor;
import cellAction.TableActionCellEditorView;
import cellAction.TableActionCellRenderer;
import cellAction.TableActionCellRendererView;
import cellAction.TableActionEvent;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author User
 */
public class AdminMain extends javax.swing.JFrame {

    /**
     * Creates new form Admin_Main
     */
    private String name;
    private String birthdate;
    private String phoneNumber;
    private String address;
    
    public AdminMain() {
        initComponents();
        
        Dashboard.setVisible(true);
        AccountManagement.setVisible(false);
        Transactions.setVisible(false);
        AuditAndReport.setVisible(false);
        loadTransactionDataForDashboard("Transactions.csv");
        loadTransactionDataForTransaction("Transactions.csv");
        loadAccountsForAccManagement("Accounts.csv");
        loadAccountsForAccApplication("AccountApplications.csv");
        loadAccountsApplicationForDashboard("AccountApplications.csv");
        
        
        TableActionEvent event = new TableActionEvent(){
            @Override 
            public void onEdit(int row){
                System.out.println("Edit Row:" + row);
            }
            @Override 
            public void onView(int row){
                showUserInfo(row);
            }
            @Override 
            public void onDelete(int row){
                int response = JOptionPane.showConfirmDialog(
                    null, // Parent component (current frame)
                    "Are you sure you want to deactivate account?", // Message
                    "Account Deactivate", // Title of the dialog
                    JOptionPane.YES_NO_OPTION, // Option type
                    JOptionPane.QUESTION_MESSAGE // Icon type
                );

                if (response == JOptionPane.YES_OPTION) {
                    if (ActiveAccountsTable.isEditing()){
                        ActiveAccountsTable.getCellEditor().stopCellEditing();
                    }
                    DefaultTableModel model = (DefaultTableModel) ActiveAccountsTable.getModel();
                    model.removeRow(row);

                }
            }
        };
        
        accApplicationTableDash.getColumnModel().getColumn(1).setCellRenderer(new TableActionCellRendererView());
        accApplicationTableDash.getColumnModel().getColumn(1).setCellEditor(new TableActionCellEditorView(event));
        
        AccountApplicationTable.getColumnModel().getColumn(1).setCellRenderer(new TableActionCellRendererView());
        AccountApplicationTable.getColumnModel().getColumn(1).setCellEditor(new TableActionCellEditorView(event));
        
        ActiveAccountsTable.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRenderer());
        ActiveAccountsTable.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor(event));
        
        
        transactionTableDash.setOpaque(false);
        transactionTableDash.setBackground(new java.awt.Color(204, 204, 204, 80));
        ((DefaultTableCellRenderer)transactionTableDash.getDefaultRenderer(Object.class)).setBackground(new java.awt.Color(204, 204, 204, 80));
        transactionTable.setOpaque(false);
        transactionTable.getViewport().setOpaque(false);
        transactionTableDash.setShowGrid(false);
        
        accApplicationTableDash.setOpaque(false);
        accApplicationTableDash.setBackground(new java.awt.Color(204, 204, 204, 80));
        ((DefaultTableCellRenderer)accApplicationTableDash.getDefaultRenderer(Object.class)).setBackground(new java.awt.Color(204, 204, 204, 80));
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        accApplicationTableDash.setShowGrid(false);
        
        AccountApplicationTable.setOpaque(false);
        AccountApplicationTable.setBackground(new java.awt.Color(204, 204, 204, 80));
        ((DefaultTableCellRenderer)AccountApplicationTable.getDefaultRenderer(Object.class)).setBackground(new java.awt.Color(204, 204, 204, 80));
        AccountApplication.setOpaque(false);
        AccountApplication.getViewport().setOpaque(false);
        AccountApplicationTable.setShowGrid(false);
        
        ActiveAccountsTable.setOpaque(false);
        ActiveAccountsTable.setBackground(new java.awt.Color(204, 204, 204, 80));
        ((DefaultTableCellRenderer)ActiveAccountsTable.getDefaultRenderer(Object.class)).setBackground(new java.awt.Color(204, 204, 204, 80));
        ActiveAccounts.setOpaque(false);
        ActiveAccounts.getViewport().setOpaque(false);
        ActiveAccountsTable.setShowGrid(false);
        
        TransactionTable.setOpaque(false);
        TransactionTable.setBackground(new java.awt.Color(204, 204, 204, 80));
        ((DefaultTableCellRenderer)TransactionTable.getDefaultRenderer(Object.class)).setBackground(new java.awt.Color(204, 204, 204, 80));
        jScrollPane5.setOpaque(false);
        jScrollPane5.getViewport().setOpaque(false);
        TransactionTable.setShowGrid(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        AccManagebutton = new javax.swing.JButton();
        Transactionsbutton = new javax.swing.JButton();
        Dashboardbutton = new javax.swing.JButton();
        AudRepbutton = new javax.swing.JButton();
        createNewAdminPanel = new javax.swing.JPanel();
        Create = new javax.swing.JButton();
        MasterPanel = new javax.swing.JPanel();
        AccountManagement = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        AccountApplication = new javax.swing.JScrollPane();
        AccountApplicationTable = new javax.swing.JTable();
        ActiveAccounts = new javax.swing.JScrollPane();
        ActiveAccountsTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        newUserAccountButton = new javax.swing.JButton();
        Dashboard = new javax.swing.JPanel();
        transactionTable = new javax.swing.JScrollPane();
        transactionTableDash = new javax.swing.JTable();
        accApplication = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        accApplicationTableDash = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        Transactions = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TransactionTable = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        AuditAndReport = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        SubTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        notificationButton = new javax.swing.JButton();
        accountButton = new javax.swing.JButton();
        BackgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Title.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("QuPAL BANKING ADMINISTRATOR");
        getContentPane().add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 810, 60));

        AccManagebutton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AccManagebutton.setForeground(new java.awt.Color(255, 255, 255));
        AccManagebutton.setText("ACCOUNT MANAGEMENT");
        AccManagebutton.setBorderPainted(false);
        AccManagebutton.setContentAreaFilled(false);
        AccManagebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AccManagebutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AccManagebuttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AccManagebuttonMouseExited(evt);
            }
        });
        AccManagebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccManagebuttonActionPerformed(evt);
            }
        });
        getContentPane().add(AccManagebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 290, 80));

        Transactionsbutton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Transactionsbutton.setForeground(new java.awt.Color(255, 255, 255));
        Transactionsbutton.setText("TRANSACTIONS");
        Transactionsbutton.setBorderPainted(false);
        Transactionsbutton.setContentAreaFilled(false);
        Transactionsbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Transactionsbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TransactionsbuttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TransactionsbuttonMouseExited(evt);
            }
        });
        Transactionsbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransactionsbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(Transactionsbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 290, 80));

        Dashboardbutton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Dashboardbutton.setForeground(new java.awt.Color(255, 255, 255));
        Dashboardbutton.setText("DASHBOARD");
        Dashboardbutton.setBorderPainted(false);
        Dashboardbutton.setContentAreaFilled(false);
        Dashboardbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Dashboardbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboardbuttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DashboardbuttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DashboardbuttonMouseExited(evt);
            }
        });
        Dashboardbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DashboardbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(Dashboardbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 290, 80));

        AudRepbutton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AudRepbutton.setForeground(new java.awt.Color(255, 255, 255));
        AudRepbutton.setText("AUDIT AND REPORT");
        AudRepbutton.setBorderPainted(false);
        AudRepbutton.setContentAreaFilled(false);
        AudRepbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AudRepbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AudRepbuttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AudRepbuttonMouseExited(evt);
            }
        });
        AudRepbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AudRepbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(AudRepbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 290, 80));

        createNewAdminPanel.setOpaque(false);

        Create.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Create.setForeground(new java.awt.Color(255, 255, 255));
        Create.setText("CREATE NEW ADMIN ACCOUNT");
        Create.setBorderPainted(false);
        Create.setContentAreaFilled(false);
        Create.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CreateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CreateMouseExited(evt);
            }
        });
        Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createNewAdminPanelLayout = new javax.swing.GroupLayout(createNewAdminPanel);
        createNewAdminPanel.setLayout(createNewAdminPanelLayout);
        createNewAdminPanelLayout.setHorizontalGroup(
            createNewAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createNewAdminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Create, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addContainerGap())
        );
        createNewAdminPanelLayout.setVerticalGroup(
            createNewAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createNewAdminPanelLayout.createSequentialGroup()
                .addGap(0, 568, Short.MAX_VALUE)
                .addComponent(Create, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(createNewAdminPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 310, 630));

        MasterPanel.setBackground(new java.awt.Color(51, 51, 255));
        MasterPanel.setOpaque(false);

        AccountManagement.setBackground(new java.awt.Color(255, 51, 255));
        AccountManagement.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ACCOUNT MANAGEMENT");

        AccountApplication.setBorder(null);

        AccountApplicationTable.setForeground(new java.awt.Color(255, 255, 255));
        AccountApplicationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AccountApplicationTable.setRowHeight(40);
        AccountApplication.setViewportView(AccountApplicationTable);

        ActiveAccounts.setBorder(null);

        ActiveAccountsTable.setForeground(new java.awt.Color(255, 255, 255));
        ActiveAccountsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ActiveAccountsTable.setRowHeight(40);
        ActiveAccounts.setViewportView(ActiveAccountsTable);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Account Applications");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Active Accounts Management");

        newUserAccountButton.setBackground(new java.awt.Color(0, 0, 204));
        newUserAccountButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        newUserAccountButton.setForeground(new java.awt.Color(255, 255, 255));
        newUserAccountButton.setText("CREATE NEW USER ACCOUNT");
        newUserAccountButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newUserAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserAccountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AccountManagementLayout = new javax.swing.GroupLayout(AccountManagement);
        AccountManagement.setLayout(AccountManagementLayout);
        AccountManagementLayout.setHorizontalGroup(
            AccountManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccountManagementLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
            .addGroup(AccountManagementLayout.createSequentialGroup()
                .addComponent(AccountApplication, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ActiveAccounts, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(AccountManagementLayout.createSequentialGroup()
                .addGroup(AccountManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AccountManagementLayout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(jLabel4))
                    .addGroup(AccountManagementLayout.createSequentialGroup()
                        .addGap(406, 406, 406)
                        .addComponent(newUserAccountButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AccountManagementLayout.setVerticalGroup(
            AccountManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccountManagementLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AccountManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AccountManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AccountApplication, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                    .addComponent(ActiveAccounts))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newUserAccountButton, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap())
        );

        Dashboard.setBackground(new java.awt.Color(51, 51, 255));
        Dashboard.setOpaque(false);
        Dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        transactionTable.setBorder(null);
        transactionTable.setOpaque(false);

        transactionTableDash.setForeground(new java.awt.Color(255, 255, 255));
        transactionTableDash.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Timestamp", "Name", "Account Number", "Amount", "Type of Transaction", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        transactionTableDash.setGridColor(new java.awt.Color(255, 255, 255));
        transactionTableDash.setOpaque(false);
        transactionTableDash.setRowHeight(40);
        transactionTable.setViewportView(transactionTableDash);

        Dashboard.add(transactionTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 740, 210));

        accApplication.setOpaque(false);

        jScrollPane2.setBorder(null);
        jScrollPane2.setOpaque(false);

        accApplicationTableDash.setForeground(new java.awt.Color(255, 255, 255));
        accApplicationTableDash.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        accApplicationTableDash.setGridColor(new java.awt.Color(255, 255, 255));
        accApplicationTableDash.setOpaque(false);
        accApplicationTableDash.setRowHeight(40);
        jScrollPane2.setViewportView(accApplicationTableDash);

        javax.swing.GroupLayout accApplicationLayout = new javax.swing.GroupLayout(accApplication);
        accApplication.setLayout(accApplicationLayout);
        accApplicationLayout.setHorizontalGroup(
            accApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        );
        accApplicationLayout.setVerticalGroup(
            accApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accApplicationLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Dashboard.add(accApplication, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 420, 410, 210));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DASHBOARD");
        Dashboard.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 300, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        Dashboard.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 850, 300));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Recent Transaction");
        Dashboard.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 390, -1, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Account Application");
        Dashboard.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 390, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204, 80));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Bank Reserve");

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(292, Short.MAX_VALUE))
        );

        Dashboard.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 300, 340));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Cash Flow");
        Dashboard.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, 30));

        Transactions.setBackground(new java.awt.Color(255, 0, 0));
        Transactions.setForeground(new java.awt.Color(255, 255, 255));
        Transactions.setOpaque(false);
        Transactions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("TRANSACTION MONITORING");
        Transactions.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 6, 682, 51));

        jScrollPane5.setBorder(null);

        TransactionTable.setForeground(new java.awt.Color(255, 255, 255));
        TransactionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Timestamp", "Name", "Account Number", "Amount", "Type of Transaction", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TransactionTable.setRowHeight(40);
        jScrollPane5.setViewportView(TransactionTable);

        Transactions.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 63, 1170, 490));

        jButton5.setBackground(new java.awt.Color(0, 0, 204));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("GENERATE REPORT");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Transactions.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 560, 302, 59));

        AuditAndReport.setBackground(new java.awt.Color(51, 255, 51));
        AuditAndReport.setOpaque(false);
        AuditAndReport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("AUDIT AND REPORT");
        AuditAndReport.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 6, 469, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("BANK RESERVE");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        AuditAndReport.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 1170, 230));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1170, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        AuditAndReport.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1170, 230));

        jButton6.setBackground(new java.awt.Color(0, 0, 204));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("GENERATE REPORT");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        AuditAndReport.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 560, 302, 59));

        javax.swing.GroupLayout MasterPanelLayout = new javax.swing.GroupLayout(MasterPanel);
        MasterPanel.setLayout(MasterPanelLayout);
        MasterPanelLayout.setHorizontalGroup(
            MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Dashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AccountManagement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Transactions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AuditAndReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MasterPanelLayout.setVerticalGroup(
            MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Dashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AccountManagement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Transactions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AuditAndReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(MasterPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 1170, 630));

        SubTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SubTitle.setForeground(new java.awt.Color(255, 255, 255));
        SubTitle.setText("\"Bank Smarter with Qupal.\"");
        getContentPane().add(SubTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, 190, 20));

        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 40));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 810, -1));

        notificationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/notif.png"))); // NOI18N
        getContentPane().add(notificationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 20, 50, 47));

        accountButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acc.png"))); // NOI18N
        accountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountButtonActionPerformed(evt);
            }
        });
        getContentPane().add(accountButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 20, 50, 47));

        BackgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        getContentPane().add(BackgroundImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DashboardbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DashboardbuttonActionPerformed
        // TODO add your handling code here:
        Dashboard.setVisible(true);
        AccountManagement.setVisible(false);
        Transactions.setVisible(false);
        AuditAndReport.setVisible(false);
    }//GEN-LAST:event_DashboardbuttonActionPerformed

    private void DashboardbuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardbuttonMouseEntered
        // TODO add your handling code here:
        Dashboardbutton.setContentAreaFilled(true);
        Dashboardbutton.setBackground(Color.cyan);
        Dashboardbutton.setForeground(Color.black);
    }//GEN-LAST:event_DashboardbuttonMouseEntered

    private void DashboardbuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardbuttonMouseExited
        // TODO add your handling code here:
        Dashboardbutton.setContentAreaFilled(false);
        Dashboardbutton.setForeground(Color.white);
    }//GEN-LAST:event_DashboardbuttonMouseExited

    private void DashboardbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardbuttonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DashboardbuttonMouseClicked

    private void AccManagebuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccManagebuttonMouseEntered
        // TODO add your handling code here:
        AccManagebutton.setContentAreaFilled(true);
        AccManagebutton.setBackground(Color.cyan);
        AccManagebutton.setForeground(Color.black);
    }//GEN-LAST:event_AccManagebuttonMouseEntered

    private void AccManagebuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccManagebuttonMouseExited
        // TODO add your handling code here:
        AccManagebutton.setContentAreaFilled(false);
        AccManagebutton.setForeground(Color.white);
    }//GEN-LAST:event_AccManagebuttonMouseExited

    private void TransactionsbuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TransactionsbuttonMouseEntered
        // TODO add your handling code here:
        Transactionsbutton.setContentAreaFilled(true);
        Transactionsbutton.setBackground(Color.cyan);
        Transactionsbutton.setForeground(Color.black);
    }//GEN-LAST:event_TransactionsbuttonMouseEntered

    private void TransactionsbuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TransactionsbuttonMouseExited
        // TODO add your handling code here:
        Transactionsbutton.setContentAreaFilled(false);
        Transactionsbutton.setForeground(Color.white);
    }//GEN-LAST:event_TransactionsbuttonMouseExited

    private void AudRepbuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AudRepbuttonMouseEntered
        // TODO add your handling code here:
        AudRepbutton.setContentAreaFilled(true);
        AudRepbutton.setBackground(Color.cyan);
        AudRepbutton.setForeground(Color.black);
    }//GEN-LAST:event_AudRepbuttonMouseEntered

    private void AudRepbuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AudRepbuttonMouseExited
        // TODO add your handling code here:
        AudRepbutton.setContentAreaFilled(false);
        AudRepbutton.setForeground(Color.white);
    }//GEN-LAST:event_AudRepbuttonMouseExited

    private void AccManagebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccManagebuttonActionPerformed
        // TODO add your handling code here:
        Dashboard.setVisible(false);
        AccountManagement.setVisible(true);
        Transactions.setVisible(false);
        AuditAndReport.setVisible(false);
        
    }//GEN-LAST:event_AccManagebuttonActionPerformed

    private void TransactionsbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransactionsbuttonActionPerformed
        // TODO add your handling code here:
        Dashboard.setVisible(false);
        AccountManagement.setVisible(false);
        Transactions.setVisible(true);
        AuditAndReport.setVisible(false);
        
    }//GEN-LAST:event_TransactionsbuttonActionPerformed

    private void AudRepbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AudRepbuttonActionPerformed
        // TODO add your handling code here:
        Dashboard.setVisible(false);
        AccountManagement.setVisible(false);
        Transactions.setVisible(false);
        AuditAndReport.setVisible(true);
    }//GEN-LAST:event_AudRepbuttonActionPerformed

    private void CreateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreateMouseEntered
        // TODO add your handling code here:
        Create.setContentAreaFilled(true);
        Create.setBackground(Color.cyan);
        Create.setForeground(Color.black);
    }//GEN-LAST:event_CreateMouseEntered

    private void CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateActionPerformed
        // TODO add your handling code here:
        AdminAccountRegistration adminAcc = new AdminAccountRegistration();
        adminAcc.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_CreateActionPerformed

    private void CreateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreateMouseExited
        // TODO add your handling code here:
        Create.setContentAreaFilled(false);
        Create.setForeground(Color.white);
    }//GEN-LAST:event_CreateMouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void newUserAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserAccountButtonActionPerformed
        // TODO add your handling code here:
        UserAccountRegistration registration = new UserAccountRegistration();
        registration.setVisible(true);
    }//GEN-LAST:event_newUserAccountButtonActionPerformed

    private void accountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountButtonActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(
            this, // Parent component (current frame)
            "Are you sure you want to log out?", // Message
            "Confirm Logout", // Title of the dialog
            JOptionPane.YES_NO_OPTION, // Option type
            JOptionPane.QUESTION_MESSAGE // Icon type
        );

        if (response == JOptionPane.YES_OPTION) {
            this.dispose(); // Close the current window
        }
    }//GEN-LAST:event_accountButtonActionPerformed

    
    private void loadTransactionDataForDashboard(String filePath){
        //Loads all data from Transaction.csv to transaction table in dashboard. 
        //Creates a dynamic table that add rows depending on the number of rows in Transaction.csv
        //Displays information from Transaction.csv row by row
        DefaultTableModel model = (DefaultTableModel) transactionTableDash.getModel();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow){
                    isFirstRow = false;
                    continue;
                }
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadTransactionDataForTransaction(String filePath){
        //Loads all data from Transaction.csv to transaction table in transaction panel. 
        //Creates a dynamic table that add rows depending on the number of rows in Transaction.csv
        //Displays information from Transaction.csv row by row
        DefaultTableModel model = (DefaultTableModel) TransactionTable.getModel();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow){
                    isFirstRow = false;
                    continue;
                }
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadAccountsForAccManagement(String filePath){
        //Loads all data from Accounts.csv to transaction table in account management panel. 
        //Creates a dynamic table that add rows depending on the number of rows in Accounts.csv
        //Displays only the name and the type of account per row
        DefaultTableModel model = (DefaultTableModel) ActiveAccountsTable.getModel();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            
            String line;
             boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow){
                    isFirstRow = false;
                    continue;
                }
                String[] data = line.split(",");
                String name = data[2];
                String type = data[7];
                
                // Create a new array with only 'name' and 'type' to add to the table
                Object[] rowData = {name,type};
                model.addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadAccountsForAccApplication(String filePath){
        //Loads all data from Accounts.csv to transaction table in account management panel. 
        //Creates a dynamic table that add rows depending on the number of rows in Accounts.csv
        //Displays only the name and the type of account per row
        DefaultTableModel model = (DefaultTableModel) AccountApplicationTable.getModel();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow){
                    isFirstRow = false;
                    continue;
                }
                String[] data = line.split(",");
                String name = data[2];
                
                // Create a new array with only 'name' and 'type' to add to the table
                Object[] rowData = {name};
                model.addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadAccountsApplicationForDashboard(String filePath){
        //Loads all data from Accounts.csv to transaction table in account management panel. 
        //Creates a dynamic table that add rows depending on the number of rows in Accounts.csv
        //Displays only the name and the type of account per row
        
        DefaultTableModel model = (DefaultTableModel) accApplicationTableDash.getModel();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow){
                    isFirstRow = false;
                    continue;
                }
                String[] data = line.split(",");
                String name = data[2];
                Object[] rowData = {name};
                model.addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void showUserInfo(int row){
        name = "";
        birthdate = "";
        phoneNumber = "";
        address = "";
        
        File file = new File("AccountApplications.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int currentRow = 0;
            int rowNumber = row+1;
            while ((line = reader.readLine()) != null) {
                if (currentRow == rowNumber) {
                    String[] data = line.split(",");
                    name = data[2];
                    birthdate = data[3];
                    phoneNumber = data[4];
                    address = data[5];
                    // Output all elements for the found name
                    break;
                }
                currentRow++;
            }
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new UserAccountApplication(name, birthdate, phoneNumber, address).setVisible(true);
                }
            });
            //this.setVisible(false);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
//    private void runSearchInfo(String title, String author, String bookNumber, String category, String availability){
//        java.awt.EventQueue.invokeLater(new Runnable() {
//                public void run() {
//                    new SearchInformation(title, author, bookNumber, category, availability).setVisible(true);
////                    bookTitle, author, bookNumber, category
//                }
//            });
//        this.setVisible(false); 
//    
//    }
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
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AccManagebutton;
    private javax.swing.JScrollPane AccountApplication;
    private javax.swing.JTable AccountApplicationTable;
    private javax.swing.JPanel AccountManagement;
    private javax.swing.JScrollPane ActiveAccounts;
    private javax.swing.JTable ActiveAccountsTable;
    private javax.swing.JButton AudRepbutton;
    private javax.swing.JPanel AuditAndReport;
    private javax.swing.JLabel BackgroundImage;
    private javax.swing.JButton Create;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JButton Dashboardbutton;
    private javax.swing.JPanel MasterPanel;
    private javax.swing.JLabel SubTitle;
    private javax.swing.JLabel Title;
    private javax.swing.JTable TransactionTable;
    private javax.swing.JPanel Transactions;
    private javax.swing.JButton Transactionsbutton;
    private javax.swing.JPanel accApplication;
    private javax.swing.JTable accApplicationTableDash;
    private javax.swing.JButton accountButton;
    private javax.swing.JPanel createNewAdminPanel;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton newUserAccountButton;
    private javax.swing.JButton notificationButton;
    private javax.swing.JScrollPane transactionTable;
    private javax.swing.JTable transactionTableDash;
    // End of variables declaration//GEN-END:variables
}
