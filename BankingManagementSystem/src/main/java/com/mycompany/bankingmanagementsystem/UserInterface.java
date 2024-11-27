/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.bankingmanagementsystem;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.text.PlainDocument;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Laurence
 */
public class UserInterface extends javax.swing.JFrame {

    /**
     * Creates new form UserInterface
     */
    public UserInterface() {
        initComponents();
        restrictInputToNumbersOnly(AmountLabel);//To restrict the text field for only number 
        restrictInputToNumbersOnly2(DepositText);//To restrict the text field for only number 
        restrictInputToNumbersOnly3(AmountLabel2);//To restrict the text field for only number 
        loadCSV(); // Automatically load the CSV file when the JFrame is created
        String filePath = "Transaction.csv";
        computeMoneyAndUpdateLabels(filePath);
        // Example usage of displayLastRow
        displayLastRow("Transaction.csv", ActionDis, DateDis, Amountdis, DescrDis);

        Dashboard.setVisible(true);
        TransacHis.setVisible(false);
        Deposit.setVisible(false);
        Withdraw.setVisible(false);
        TransCash.setVisible(false);
        AccReg.setVisible(false);
        
        Transhis.setOpaque(false);
        Transhis.setBackground(new java.awt.Color(204, 204, 204, 80));
        ((DefaultTableCellRenderer)Transhis.getDefaultRenderer(Object.class)).setBackground(new java.awt.Color(204, 204, 204, 80));
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        Transhis.setShowGrid(false);
        
        DashHis.setOpaque(false);
        DashHis.setBackground(new java.awt.Color(204, 204, 204, 80));
        ((DefaultTableCellRenderer)DashHis.getDefaultRenderer(Object.class)).setBackground(new java.awt.Color(204, 204, 204, 80));
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        DashHis.setShowGrid(false);
    }
        
    public void computeMoneyAndUpdateLabels(String filePath) {
    double totalDeposits = 0.0;
    double totalWithdrawals = 0.0;

    // Create a DecimalFormat for formatting money values
    DecimalFormat df = new DecimalFormat("#");

    // Validate the file path
    if (filePath == null || filePath.isEmpty()) {
        System.out.println("Invalid file path.");
        return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        boolean isFirstLine = true;

        // Display the file content in the console or GUI
        System.out.println("Reading CSV file:");
        System.out.printf("%-15s %-15s %-10s %-30s%n", "Action", "Date", "Amount", "Description");

        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                // Skip the header if present
                isFirstLine = false;
                continue;
            }

            // Split the line by commas (columns: Action, Date, Amount, Description)
            String[] values = line.split(",");
            if (values.length < 4) {
                System.out.println("Skipping invalid line: " + line);
                continue;
            }

            String action = values[0].trim();
            String date = values[1].trim();
            String amountStr = values[2].trim();
            String description = values[3].trim();

            // Display the row data
            System.out.printf("%-15s %-15s %-10s %-30s%n", action, date, amountStr, description);

            try {
                // Parse the amount
                double money = Double.parseDouble(amountStr);

                // Add deposits and withdrawals to their respective totals
                if (action.equalsIgnoreCase("Deposited")) {
                    totalDeposits += money;
                } else if (action.equalsIgnoreCase("Withdraw")) {
                    totalWithdrawals += money; // Withdrawals are added positively
                }
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid money value: " + amountStr);
            }
        }

        // Calculate the total balance (sum of deposits and withdrawals)
        double totalBalance = totalDeposits + totalWithdrawals;

        // Format totals
        String formattedDeposits = "$" + df.format(totalDeposits);
        String formattedWithdrawals = "$" + df.format(totalWithdrawals);
        String formattedBalance = "$" + df.format(totalBalance);

        // Update the JLabels (replace with your actual JLabel names)
        CurrentBal.setText(formattedBalance);
        BalDis.setText(formattedBalance);


    } catch (IOException e) {
        System.out.println("Error reading the file: " + filePath);
        e.printStackTrace();
    }
}





    public void displayLastRow(String filePath, JLabel ActionDis, JLabel DateDis, JLabel Amountdis, JLabel DescrDis) {
        File file = new File(filePath);

        // Check if the file exists and is a valid file
        if (!file.exists() || !file.isFile()) {
            JOptionPane.showMessageDialog(null, "File does not exist or is not a valid file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            // Read all rows and store them in a list
            while ((line = br.readLine()) != null) {
                // Skip empty lines
                if (!line.trim().isEmpty()) {
                    rows.add(line.split(","));
                }
            }

            // Check if the file has at least one row
            if (rows.size() > 0) {
                // Get the last row
                String[] lastRow = rows.get(rows.size() - 1);

                // Ensure that the row has at least 4 columns
                if (lastRow.length >= 4) {
                    // Set ActionDis
                    String action = lastRow[0].trim();
                    ActionDis.setText(action.isEmpty() ? "N/A" : action);

                    // Set DateDis
                    String date = lastRow[4].trim();
                    DateDis.setText(date.isEmpty() ? "N/A" : date);

                    // Set Amountdis
                    String amount = lastRow[3].trim();
                    Amountdis.setText(amount.isEmpty() ? "N/A" : amount);

                    // Set DescrDis (for description, handle empty strings)
                    String description = lastRow[5].trim();
                    DescrDis.setText(description.isEmpty() ? "No description" : description);
                } else {
                    JOptionPane.showMessageDialog(null, "Row does not have enough columns.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "CSV file is empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        Profile = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Notifs = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        Trans = new javax.swing.JButton();
        Depos = new javax.swing.JButton();
        Withd = new javax.swing.JButton();
        Transc = new javax.swing.JButton();
        Dashb = new javax.swing.JButton();
        Accreg = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        Dashboard = new javax.swing.JPanel();
        RecentTrans = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        DateLabel = new javax.swing.JLabel();
        ActionLabel1 = new javax.swing.JLabel();
        AmountLabel1 = new javax.swing.JLabel();
        AmountLabel3 = new javax.swing.JLabel();
        DateDis = new javax.swing.JLabel();
        ActionDis = new javax.swing.JLabel();
        DescrDis = new javax.swing.JLabel();
        Amountdis = new javax.swing.JLabel();
        Balance = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        BalDis = new javax.swing.JLabel();
        BalanceLabel = new javax.swing.JLabel();
        AccDEts = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        NameDis = new javax.swing.JLabel();
        PhoneNumDis = new javax.swing.JLabel();
        BdayLabel = new javax.swing.JLabel();
        NameLabel1 = new javax.swing.JLabel();
        PhoneNumLabel = new javax.swing.JLabel();
        BdayDis1 = new javax.swing.JLabel();
        AddressLabel = new javax.swing.JLabel();
        AddressDis = new javax.swing.JLabel();
        BankAccNumLabel = new javax.swing.JLabel();
        PhoneNumDis1 = new javax.swing.JLabel();
        Hist = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DashHis = new javax.swing.JTable();
        TransacHis = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Transhis = new javax.swing.JTable();
        Deposit = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        CurrentBal = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        DepositText = new javax.swing.JTextField();
        DepositButton = new javax.swing.JButton();
        Withdraw = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        AmountLabel = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        FiveKyawButton = new javax.swing.JButton();
        FiveHundoButton = new javax.swing.JButton();
        OnehundoButton = new javax.swing.JButton();
        TenKyawButton = new javax.swing.JButton();
        OnekyawButton = new javax.swing.JButton();
        Withdrawbutton = new javax.swing.JButton();
        TransCash = new javax.swing.JPanel();
        Withdraw1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        AccountNumber = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        TransferButton = new javax.swing.JButton();
        AmountLabel2 = new javax.swing.JTextField();
        AccReg = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/acc.png"))); // NOI18N
        Profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfileActionPerformed(evt);
            }
        });
        getContentPane().add(Profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 20, 50, 50));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("QuPAL Banking System");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 530, 70));

        Notifs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/notif.png"))); // NOI18N
        Notifs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotifsActionPerformed(evt);
            }
        });
        getContentPane().add(Notifs, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 20, 50, 50));

        jPanel11.setOpaque(false);

        Trans.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Trans.setForeground(new java.awt.Color(255, 255, 255));
        Trans.setText("TRANSACTION HISTORY");
        Trans.setBorderPainted(false);
        Trans.setContentAreaFilled(false);
        Trans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TransMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TransMouseExited(evt);
            }
        });
        Trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransActionPerformed(evt);
            }
        });

        Depos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Depos.setForeground(new java.awt.Color(255, 255, 255));
        Depos.setText("DEPOSIT");
        Depos.setBorderPainted(false);
        Depos.setContentAreaFilled(false);
        Depos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DeposMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DeposMouseExited(evt);
            }
        });
        Depos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeposActionPerformed(evt);
            }
        });

        Withd.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Withd.setForeground(new java.awt.Color(255, 255, 255));
        Withd.setText("WITHDRAW");
        Withd.setBorderPainted(false);
        Withd.setContentAreaFilled(false);
        Withd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                WithdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                WithdMouseExited(evt);
            }
        });
        Withd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WithdActionPerformed(evt);
            }
        });

        Transc.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Transc.setForeground(new java.awt.Color(255, 255, 255));
        Transc.setText("TRANSFER CASH");
        Transc.setBorderPainted(false);
        Transc.setContentAreaFilled(false);
        Transc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TranscMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TranscMouseExited(evt);
            }
        });
        Transc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TranscActionPerformed(evt);
            }
        });

        Dashb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Dashb.setForeground(new java.awt.Color(255, 255, 255));
        Dashb.setText("DASHBOARD");
        Dashb.setBorderPainted(false);
        Dashb.setContentAreaFilled(false);
        Dashb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DashbMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DashbMouseExited(evt);
            }
        });
        Dashb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DashbActionPerformed(evt);
            }
        });

        Accreg.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Accreg.setForeground(new java.awt.Color(255, 255, 255));
        Accreg.setText("ACCOUNT REGISTRATION");
        Accreg.setBorderPainted(false);
        Accreg.setContentAreaFilled(false);
        Accreg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AccregMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AccregMouseExited(evt);
            }
        });
        Accreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccregActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Accreg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Trans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Depos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Withd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Transc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Dashb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(Dashb, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Trans, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Depos, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Withd, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Transc, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Accreg, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 310, 610));

        jPanel9.setBackground(new java.awt.Color(0, 153, 102));
        jPanel9.setOpaque(false);
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Dashboard.setBackground(new java.awt.Color(204, 204, 204, 80));
        Dashboard.setOpaque(false);

        RecentTrans.setBackground(new java.awt.Color(204, 204, 204, 80));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("RECENT TRANSACTION");

        DateLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        DateLabel.setText("DATE:");

        ActionLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ActionLabel1.setText("ACTION:");

        AmountLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        AmountLabel1.setText("DESCRIPTION:");

        AmountLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        AmountLabel3.setText("AMOUNT:");

        DateDis.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        ActionDis.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        DescrDis.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        Amountdis.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout RecentTransLayout = new javax.swing.GroupLayout(RecentTrans);
        RecentTrans.setLayout(RecentTransLayout);
        RecentTransLayout.setHorizontalGroup(
            RecentTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RecentTransLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(61, 61, 61))
            .addGroup(RecentTransLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RecentTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addGroup(RecentTransLayout.createSequentialGroup()
                        .addGroup(RecentTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AmountLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AmountLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ActionLabel1)
                            .addComponent(DateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(RecentTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DescrDis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(RecentTransLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(RecentTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Amountdis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DateDis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ActionDis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        RecentTransLayout.setVerticalGroup(
            RecentTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RecentTransLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(RecentTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RecentTransLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(ActionDis, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DateDis, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(Amountdis, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(DescrDis, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RecentTransLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(DateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ActionLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AmountLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AmountLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );

        Balance.setBackground(new java.awt.Color(204, 204, 204, 80));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("BALANCE");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        BalDis.setBackground(new java.awt.Color(255, 255, 255));
        BalDis.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BalDis.setForeground(new java.awt.Color(255, 255, 255));

        BalanceLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BalanceLabel.setForeground(new java.awt.Color(255, 255, 255));
        BalanceLabel.setText("BALANCE:");

        javax.swing.GroupLayout BalanceLayout = new javax.swing.GroupLayout(Balance);
        Balance.setLayout(BalanceLayout);
        BalanceLayout.setHorizontalGroup(
            BalanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BalanceLayout.createSequentialGroup()
                .addGroup(BalanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BalanceLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(BalanceLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(BalanceLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(BalanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BalanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BalDis, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BalanceLayout.setVerticalGroup(
            BalanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BalanceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BalanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BalDis, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        AccDEts.setBackground(new java.awt.Color(204, 204, 204, 80));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ACCOUNT DETAILS");

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        NameDis.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        PhoneNumDis.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        BdayLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BdayLabel.setText("Birthday:");

        NameLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        NameLabel1.setText("Name:");

        PhoneNumLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        PhoneNumLabel.setText("PhoneNumer:");

        BdayDis1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        AddressLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        AddressLabel.setText("Address:");

        AddressDis.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N

        BankAccNumLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BankAccNumLabel.setText("Bank Account Number:");

        PhoneNumDis1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout AccDEtsLayout = new javax.swing.GroupLayout(AccDEts);
        AccDEts.setLayout(AccDEtsLayout);
        AccDEtsLayout.setHorizontalGroup(
            AccDEtsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccDEtsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AccDEtsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(AddressDis, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(BdayDis1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(PhoneNumDis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NameDis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(AccDEtsLayout.createSequentialGroup()
                        .addGroup(AccDEtsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AccDEtsLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel5))
                            .addComponent(BdayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PhoneNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BankAccNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PhoneNumDis1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        AccDEtsLayout.setVerticalGroup(
            AccDEtsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccDEtsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(NameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NameDis, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(BdayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BdayDis1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PhoneNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PhoneNumDis, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddressDis, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BankAccNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PhoneNumDis1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Hist.setBackground(new java.awt.Color(204, 204, 204, 80));
        Hist.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        Hist.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 42, 350, 10));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("HISTORY");
        Hist.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 12, -1, 24));

        jScrollPane2.setBorder(null);
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        DashHis.setBackground(new java.awt.Color(255, 255, 255, 80));
        DashHis.setForeground(new java.awt.Color(255, 255, 255));
        DashHis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TimeStamp", "Aciotn", "Amount", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(DashHis);
        if (DashHis.getColumnModel().getColumnCount() > 0) {
            DashHis.getColumnModel().getColumn(0).setResizable(false);
            DashHis.getColumnModel().getColumn(1).setResizable(false);
            DashHis.getColumnModel().getColumn(2).setResizable(false);
            DashHis.getColumnModel().getColumn(3).setResizable(false);
        }

        Hist.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 350, 520));

        javax.swing.GroupLayout DashboardLayout = new javax.swing.GroupLayout(Dashboard);
        Dashboard.setLayout(DashboardLayout);
        DashboardLayout.setHorizontalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Balance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RecentTrans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AccDEts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Hist, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        DashboardLayout.setVerticalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AccDEts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addComponent(Balance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RecentTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Hist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel9.add(Dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        TransacHis.setBackground(new java.awt.Color(204, 204, 204, 80));
        TransacHis.setOpaque(false);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255, 80));
        jScrollPane1.setBorder(null);

        Transhis.setBackground(new java.awt.Color(255, 255, 255, 80));
        Transhis.setForeground(new java.awt.Color(255, 255, 255));
        Transhis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Timestamp", "Action", "Amount", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Transhis.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(Transhis);
        if (Transhis.getColumnModel().getColumnCount() > 0) {
            Transhis.getColumnModel().getColumn(0).setResizable(false);
            Transhis.getColumnModel().getColumn(1).setResizable(false);
            Transhis.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout TransacHisLayout = new javax.swing.GroupLayout(TransacHis);
        TransacHis.setLayout(TransacHisLayout);
        TransacHisLayout.setHorizontalGroup(
            TransacHisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransacHisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
                .addContainerGap())
        );
        TransacHisLayout.setVerticalGroup(
            TransacHisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransacHisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.add(TransacHis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        Deposit.setOpaque(false);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGO.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deposit.jpg"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CASH DEPOSIT");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Enter Amount:");

        CurrentBal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        CurrentBal.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Current Balance:");

        DepositText.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        DepositText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepositTextActionPerformed(evt);
            }
        });

        DepositButton.setBackground(new java.awt.Color(0, 0, 204));
        DepositButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        DepositButton.setForeground(new java.awt.Color(255, 255, 255));
        DepositButton.setText("DEPOSIT");
        DepositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepositButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DepositLayout = new javax.swing.GroupLayout(Deposit);
        Deposit.setLayout(DepositLayout);
        DepositLayout.setHorizontalGroup(
            DepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DepositLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
            .addGroup(DepositLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel10)
                .addGroup(DepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DepositLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(DepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addGroup(DepositLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CurrentBal, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DepositText, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DepositLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DepositButton, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(69, 69, 69))
        );
        DepositLayout.setVerticalGroup(
            DepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DepositLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(DepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DepositLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(DepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DepositLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(DepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CurrentBal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(113, 113, 113)
                        .addGroup(DepositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DepositText))
                        .addGap(18, 18, 18)
                        .addComponent(DepositButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DepositLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jPanel9.add(Deposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        Withdraw.setOpaque(false);
        Withdraw.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGO.png"))); // NOI18N
        Withdraw.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/withdraw.jpg"))); // NOI18N
        Withdraw.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("CASH WITHDRAW");
        Withdraw.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 656, 55));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Enter Amount:");
        Withdraw.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 240, 45));

        AmountLabel.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        AmountLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AmountLabelActionPerformed(evt);
            }
        });
        Withdraw.add(AmountLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, 400, 45));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Select Amount:");
        Withdraw.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, -1, 40));

        FiveKyawButton.setBackground(new java.awt.Color(51, 102, 255));
        FiveKyawButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        FiveKyawButton.setForeground(new java.awt.Color(255, 255, 255));
        FiveKyawButton.setText("₱5,000");
        FiveKyawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiveKyawButtonActionPerformed(evt);
            }
        });
        Withdraw.add(FiveKyawButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 380, 130, 52));

        FiveHundoButton.setBackground(new java.awt.Color(51, 204, 255));
        FiveHundoButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        FiveHundoButton.setForeground(new java.awt.Color(255, 255, 255));
        FiveHundoButton.setText("₱500");
        FiveHundoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiveHundoButtonActionPerformed(evt);
            }
        });
        Withdraw.add(FiveHundoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 310, 130, 52));

        OnehundoButton.setBackground(new java.awt.Color(51, 255, 255));
        OnehundoButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        OnehundoButton.setForeground(new java.awt.Color(255, 255, 255));
        OnehundoButton.setText("₱100");
        OnehundoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnehundoButtonActionPerformed(evt);
            }
        });
        Withdraw.add(OnehundoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 310, 130, 52));

        TenKyawButton.setBackground(new java.awt.Color(51, 51, 255));
        TenKyawButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TenKyawButton.setForeground(new java.awt.Color(255, 255, 255));
        TenKyawButton.setText("₱10,000");
        TenKyawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TenKyawButtonActionPerformed(evt);
            }
        });
        Withdraw.add(TenKyawButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 380, 120, 52));

        OnekyawButton.setBackground(new java.awt.Color(51, 153, 255));
        OnekyawButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        OnekyawButton.setForeground(new java.awt.Color(255, 255, 255));
        OnekyawButton.setText("₱1,000");
        OnekyawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnekyawButtonActionPerformed(evt);
            }
        });
        Withdraw.add(OnekyawButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 310, 120, 52));

        Withdrawbutton.setBackground(new java.awt.Color(0, 0, 204));
        Withdrawbutton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Withdrawbutton.setForeground(new java.awt.Color(255, 255, 255));
        Withdrawbutton.setText("WITHDRAW");
        Withdrawbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WithdrawbuttonActionPerformed(evt);
            }
        });
        Withdraw.add(Withdrawbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 470, 180, 71));

        jPanel9.add(Withdraw, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        TransCash.setOpaque(false);

        Withdraw1.setOpaque(false);
        Withdraw1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGO.png"))); // NOI18N
        Withdraw1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/transfer.jpg"))); // NOI18N
        Withdraw1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("CASH TRANSFER");
        Withdraw1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 656, 55));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Enter Amount:");
        Withdraw1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 240, 45));

        AccountNumber.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        Withdraw1.add(AccountNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 360, 670, 45));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Enter Account Number:");
        Withdraw1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 310, 400, 40));

        TransferButton.setBackground(new java.awt.Color(0, 0, 204));
        TransferButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TransferButton.setForeground(new java.awt.Color(255, 255, 255));
        TransferButton.setText("TRANSFER");
        TransferButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransferButtonActionPerformed(evt);
            }
        });
        Withdraw1.add(TransferButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 460, 180, 71));

        AmountLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        AmountLabel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AmountLabel2ActionPerformed(evt);
            }
        });
        Withdraw1.add(AmountLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, 420, 45));

        javax.swing.GroupLayout TransCashLayout = new javax.swing.GroupLayout(TransCash);
        TransCash.setLayout(TransCashLayout);
        TransCashLayout.setHorizontalGroup(
            TransCashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransCashLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Withdraw1, javax.swing.GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE))
        );
        TransCashLayout.setVerticalGroup(
            TransCashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransCashLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Withdraw1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
        );

        jPanel9.add(TransCash, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        javax.swing.GroupLayout AccRegLayout = new javax.swing.GroupLayout(AccReg);
        AccReg.setLayout(AccRegLayout);
        AccRegLayout.setHorizontalGroup(
            AccRegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1170, Short.MAX_VALUE)
        );
        AccRegLayout.setVerticalGroup(
            AccRegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jPanel9.add(AccReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 1170, 600));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 530, 10));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("\"Bank Smarter with Qupal.\"");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Design 2.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 800));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileActionPerformed
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
    }//GEN-LAST:event_ProfileActionPerformed

    private void WithdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WithdActionPerformed
        // TODO add your handling code here:
        Dashboard.setVisible(false);
        TransacHis.setVisible(false);
        Deposit.setVisible(false);
        Withdraw.setVisible(true);
        TransCash.setVisible(false);
        AccReg.setVisible(false);
    }//GEN-LAST:event_WithdActionPerformed

    private void DashbMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashbMouseEntered
        // TODO add your handling code here:
        Dashb.setContentAreaFilled(true);
        Dashb.setBackground(Color.cyan);
        Dashb.setForeground(Color.black);
    }//GEN-LAST:event_DashbMouseEntered

    private void DashbMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashbMouseExited
        // TODO add your handling code here:
        Dashb.setContentAreaFilled(false);
        Dashb.setForeground(Color.white);
    }//GEN-LAST:event_DashbMouseExited

    private void TransMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TransMouseEntered
        // TODO add your handling code here:
        Trans.setContentAreaFilled(true);
        Trans.setBackground(Color.cyan);
        Trans.setForeground(Color.black);
    }//GEN-LAST:event_TransMouseEntered

    private void TransMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TransMouseExited
        // TODO add your handling code here:
        Trans.setContentAreaFilled(false);
        Trans.setForeground(Color.white);
    }//GEN-LAST:event_TransMouseExited
    
    private void loadCSV() {
        // Specify the path to the CSV file (you can change this to your file's path)
        String filePath = "Transaction.csv";  // Update this to your actual file path

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            ArrayList<String[]> data = new ArrayList<>();

            // Skip the first line (headers) since we already defined them in the JTable
            br.readLine();

            // Read the rest of the data
            while ((line = br.readLine()) != null) {
                // Split each line by comma
                String[] columns = line.split(",");

                // Ensure we have enough columns to avoid ArrayIndexOutOfBoundsException
                if (columns.length >= 6) {
                    // Extract only the needed columns: Timestamp, Amount, Action, Description
                    String timestamp = columns[0];       // Timestamp
                    String amount = columns[3];          // Amount
                    String action = columns[4];          // Action
                    String description = columns[5];     // Description

                    // Add the filtered data row to the list
                    data.add(new String[]{timestamp, amount, action, description});
                }
            }

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = new DefaultTableModel() {
                // Override isCellEditable to make cells non-editable
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;  // Make all cells non-editable
                }
            };

            // Add the required columns to the model
            model.addColumn("Timestamp");
            model.addColumn("Amount");
            model.addColumn("Action");
            model.addColumn("Description");

            // Add data rows to the model
            for (String[] row : data) {
                model.addRow(row);
            }

            // Set the model to the JTable
            Transhis.setModel(model);
            DashHis.setModel(model);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
        }
    }



    
    private void DeposMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeposMouseEntered
        // TODO add your handling code here:
        Depos.setContentAreaFilled(true);
        Depos.setBackground(Color.cyan);
        Depos.setForeground(Color.black);
    }//GEN-LAST:event_DeposMouseEntered

    private void DeposMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeposMouseExited
        // TODO add your handling code here:
        Depos.setContentAreaFilled(false);
        Depos.setForeground(Color.white);
    }//GEN-LAST:event_DeposMouseExited

    private void WithdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WithdMouseEntered
        // TODO add your handling code here:
        Withd.setContentAreaFilled(true);
        Withd.setBackground(Color.cyan);
        Withd.setForeground(Color.black);
    }//GEN-LAST:event_WithdMouseEntered

    private void WithdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WithdMouseExited
        // TODO add your handling code here:
        Withd.setContentAreaFilled(false);
        Withd.setForeground(Color.white);
    }//GEN-LAST:event_WithdMouseExited

    private void TranscMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TranscMouseEntered
        // TODO add your handling code here:
        Transc.setContentAreaFilled(true);
        Transc.setBackground(Color.cyan);
        Transc.setForeground(Color.black);
    }//GEN-LAST:event_TranscMouseEntered

    private void TranscMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TranscMouseExited
        // TODO add your handling code here:
        Transc.setContentAreaFilled(false);
        Transc.setForeground(Color.white);
    }//GEN-LAST:event_TranscMouseExited

    private void AccregMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccregMouseEntered
        // TODO add your handling code here:
        Accreg.setContentAreaFilled(true);
        Accreg.setBackground(Color.cyan);
        Accreg.setForeground(Color.black);
    }//GEN-LAST:event_AccregMouseEntered

    private void AccregMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccregMouseExited
        // TODO add your handling code here:
        Accreg.setContentAreaFilled(false);
        Accreg.setForeground(Color.white);
    }//GEN-LAST:event_AccregMouseExited

    private void DashbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DashbActionPerformed
        // TODO add your handling code here:
        Dashboard.setVisible(true);
        TransacHis.setVisible(false);
        Deposit.setVisible(false);
        Withdraw.setVisible(false);
        TransCash.setVisible(false);
        AccReg.setVisible(false);
        
    }//GEN-LAST:event_DashbActionPerformed

    private void DeposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeposActionPerformed
        // TODO add your handling code here:
        Dashboard.setVisible(false);
        TransacHis.setVisible(false);
        Deposit.setVisible(true);
        Withdraw.setVisible(false);
        TransCash.setVisible(false);
        AccReg.setVisible(false);
    }//GEN-LAST:event_DeposActionPerformed

    private void TransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransActionPerformed
        // TODO add your handling code here:
        Dashboard.setVisible(false);
        TransacHis.setVisible(true);
        Deposit.setVisible(false);
        Withdraw.setVisible(false);
        TransCash.setVisible(false);
        AccReg.setVisible(false);
    }//GEN-LAST:event_TransActionPerformed

    private void TranscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TranscActionPerformed
        // TODO add your handling code here:
        Dashboard.setVisible(false);
        TransacHis.setVisible(false);
        Deposit.setVisible(false);
        Withdraw.setVisible(false);
        TransCash.setVisible(true);
        AccReg.setVisible(false);
    }//GEN-LAST:event_TranscActionPerformed

    private void AccregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccregActionPerformed
        // TODO add your handling code here:
        Dashboard.setVisible(false);
        TransacHis.setVisible(false);
        Deposit.setVisible(false);
        Withdraw.setVisible(false);
        TransCash.setVisible(false);
        AccReg.setVisible(true);
    }//GEN-LAST:event_AccregActionPerformed

    private void FiveKyawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiveKyawButtonActionPerformed
        AmountLabel.setText("5000");
    }//GEN-LAST:event_FiveKyawButtonActionPerformed

    private void TransferButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransferButtonActionPerformed
        // Get the text from the JTextField
        String accountNumber = AccountNumber.getText();

        // Check if the account number exists in the CSV file
        boolean found = checkInCSV(accountNumber);

        // Show the result in a message dialog
        if (found) {
            String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
            String amount = AmountLabel2.getText(); // Assuming you have a JTextField for amount
            saveToCSV("Transfer", timestamp, amount); // Save the transfer details

            JOptionPane.showMessageDialog(this, "Successfully Transferred");
        } else {
            JOptionPane.showMessageDialog(this, "User not found.");
        }
    }//GEN-LAST:event_TransferButtonActionPerformed
    
    public boolean checkInCSV(String text) {
        File csvFile = new File("Accounts.csv"); // Path to your CSV file
        if (!csvFile.exists()) {
            return false; // File does not exist
        }

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                for (String column : columns) {
                    if (column.trim().equalsIgnoreCase(text.trim())) {
                        return true; // Text found
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Text not found
    }

    
    private void saveToCSV(String buttonText, String timestamp, String amount) {
        FileWriter writer = null;
        try {
            File file = new File("Transaction.csv");
            boolean isNewFile = !file.exists();

            // Open the file in append mode
            writer = new FileWriter(file, true);

            // Write header if the file is new
            if (isNewFile) {
                writer.append("Timestamp,User,Type,Amount,Button,Description\n");
            }

            // Append the transaction details
            writer.append(timestamp)
                  .append(",USER,BAN,")
                  .append(amount)
                  .append(",")
                  .append(buttonText)
                  .append(",No Description\n");

        } catch (IOException e) {
            // Log and notify the user of the error
            System.err.println("Error saving to CSV: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error saving to CSV: " + e.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing writer: " + e.getMessage());
            }
        }
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
    }

 
    
    private void FiveHundoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiveHundoButtonActionPerformed
        AmountLabel.setText("500");
    }//GEN-LAST:event_FiveHundoButtonActionPerformed

    private void OnehundoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnehundoButtonActionPerformed
        AmountLabel.setText("100");
    }//GEN-LAST:event_OnehundoButtonActionPerformed

    private void WithdrawbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WithdrawbuttonActionPerformed
        // Get the amount entered by the user
        String amountText = AmountLabel.getText();  // Get text from the JTextField
        AmountLabel.setText(""); // Clear any previous error message

        // Check if the input is a valid number
        if (isValidAmount(amountText)) {
            double amount = Double.parseDouble(amountText); // Parse the amount as a double
            double currentBalance = getCurrentBalance(); // Fetch the current balance (assumes a method for this)

            // Ensure the balance after withdrawal remains at least $100
            if (currentBalance - amount < 100) {
                JOptionPane.showMessageDialog(this, 
                    "Cannot withdraw. Balance must remain at least $100 after withdrawal.", 
                    "Insufficient Balance", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get the current timestamp
            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Save the data to a CSV file
            saveToCSV("Withdraw", timestamp, amountText);  // Pass the amountText to the method

            // Optionally, pop up a window showing the button action details
            popUpWindow1("Withdraw", timestamp, amountText);  // Pass the amountText here

            // Update the balance (assumes a method to update the balance)
            updateBalance(currentBalance - amount);
        } else {
            // Show an error message if the input is not a valid number
            AmountLabel.setText("Please enter a valid number.");
        }
    }//GEN-LAST:event_WithdrawbuttonActionPerformed
    
    // Method to fetch the current balance (this should be connected to your application's logic)
    private double getCurrentBalance() {
        // Example logic, replace this with your actual implementation
        String balanceText = CurrentBal.getText().replace("$", ""); // Assuming CurrentBal holds the balance in "$123.45" format
        return Double.parseDouble(balanceText);
    }

    // Method to update the balance (e.g., display new balance in the UI)
    private void updateBalance(double newBalance) {
        DecimalFormat df = new DecimalFormat("#.00");
        CurrentBal.setText("$" + df.format(newBalance));
        BalDis.setText("$" + df.format(newBalance));
    }
    // Helper method to check if the input is a valid numeric amount
    private boolean isValidAmount(String str) {
        try {
            // Ensure the value is a valid decimal number (including decimals)
            Double.parseDouble(str);  // Attempt to parse the input as a number
            return str.matches("\\d+(\\.\\d{1,2})?");  // Validate format (optional decimal)
        } catch (NumberFormatException e) {
            return false;  // It is not a valid number
        }
    }
    
    private void popUpWindow1(String buttonText, String timestamp, String amount) {
        // Create a new JFrame for the popup window
        JFrame popup = new JFrame("Withdraw Click Details");
        popup.setSize(325, 325);
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Center the popup window on the screen
        popup.setLocationRelativeTo(null);

        // Add a label with the button click information
        JLabel label = new JLabel("<html>Details: " + timestamp + "<br>Time: " + amount  + "<br>Amount: " + buttonText + "</html>", SwingConstants.CENTER);

        popup.add(label);

        // Display the popup
        popup.setVisible(true);
    }


    // Restrict JTextField to numbers only (including decimals)
    private void restrictInputToNumbersOnly(JTextField textField) {
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]*\\.?[0-9]*")) {  // Allow digits and a single decimal point
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[0-9]*\\.?[0-9]*")) {  // Allow digits and a single decimal point
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
    
    private void AmountLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmountLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AmountLabelActionPerformed

    private void TenKyawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TenKyawButtonActionPerformed
        AmountLabel.setText("10000");
    }//GEN-LAST:event_TenKyawButtonActionPerformed

    private void OnekyawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnekyawButtonActionPerformed
       AmountLabel.setText("1000");
    }//GEN-LAST:event_OnekyawButtonActionPerformed

    private void NotifsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotifsActionPerformed
        
    }//GEN-LAST:event_NotifsActionPerformed

    private void DepositButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepositButtonActionPerformed
         // Get the amount entered by the user
        String amountText = DepositText.getText();  // Get text from the JTextField
        DepositText.setText("");  // Clear the text field

        // Check if the input is a valid number
        if (isValidAmount(amountText)) {
            // Convert the string to a double
            double depositAmount = Double.parseDouble(amountText);

            // Get the current timestamp
            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Save the data to a CSV file
            saveToCSV("Deposited", timestamp, amountText);

            // Optionally, pop up a window showing the button action details
            popUpWindow2("Deposited", timestamp, amountText);

            // Update the balance after deposit
            double currentBalance = getCurrentBalance();
            double newBalance = currentBalance + depositAmount;
            updateBalance(newBalance);  // Update the displayed balance

        } else {
            // Show an error message if the input is not a valid number
            DepositText.setText("Please enter a valid number.");
        }       
    }//GEN-LAST:event_DepositButtonActionPerformed
    
    
    private void AmountLabel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmountLabel2ActionPerformed
        
    }//GEN-LAST:event_AmountLabel2ActionPerformed
    
    private void restrictInputToNumbersOnly3(JTextField textField) {
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]*\\.?[0-9]*")) {  // Allow digits and a single decimal point
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[0-9]*\\.?[0-9]*")) {  // Allow digits and a single decimal point
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
    
    private void DepositTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepositTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DepositTextActionPerformed
    
    private void popUpWindow2(String buttonText, String timestamp, String amount) {
        // Create a new JFrame for the popup window
        JFrame popup = new JFrame("Deposited Click Details");
        popup.setSize(325, 325);
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Center the popup window on the screen
        popup.setLocationRelativeTo(null);

        // Add a label with the button click information
        JLabel label = new JLabel("<html>Details: " + buttonText + "<br>Time: " + timestamp + "<br>Amount: " + amount + "</html>", SwingConstants.CENTER);

        popup.add(label);

        // Display the popup
        popup.setVisible(true);
    }
    
    private void restrictInputToNumbersOnly2(JTextField textField) {
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]*\\.?[0-9]*")) {  // Allow digits and a single decimal point
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[0-9]*\\.?[0-9]*")) {  // Allow digits and a single decimal point
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
    
    
    
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
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AccDEts;
    private javax.swing.JPanel AccReg;
    private javax.swing.JTextField AccountNumber;
    private javax.swing.JButton Accreg;
    private javax.swing.JLabel ActionDis;
    private javax.swing.JLabel ActionLabel1;
    private javax.swing.JLabel AddressDis;
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JTextField AmountLabel;
    private javax.swing.JLabel AmountLabel1;
    private javax.swing.JTextField AmountLabel2;
    private javax.swing.JLabel AmountLabel3;
    private javax.swing.JLabel Amountdis;
    private javax.swing.JLabel BalDis;
    private javax.swing.JPanel Balance;
    private javax.swing.JLabel BalanceLabel;
    private javax.swing.JLabel BankAccNumLabel;
    private javax.swing.JLabel BdayDis1;
    private javax.swing.JLabel BdayLabel;
    private javax.swing.JLabel CurrentBal;
    private javax.swing.JTable DashHis;
    private javax.swing.JButton Dashb;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JLabel DateDis;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JButton Depos;
    private javax.swing.JPanel Deposit;
    private javax.swing.JButton DepositButton;
    private javax.swing.JTextField DepositText;
    private javax.swing.JLabel DescrDis;
    private javax.swing.JButton FiveHundoButton;
    private javax.swing.JButton FiveKyawButton;
    private javax.swing.JPanel Hist;
    private javax.swing.JLabel NameDis;
    private javax.swing.JLabel NameLabel1;
    private javax.swing.JButton Notifs;
    private javax.swing.JButton OnehundoButton;
    private javax.swing.JButton OnekyawButton;
    private javax.swing.JLabel PhoneNumDis;
    private javax.swing.JLabel PhoneNumDis1;
    private javax.swing.JLabel PhoneNumLabel;
    private javax.swing.JButton Profile;
    private javax.swing.JPanel RecentTrans;
    private javax.swing.JButton TenKyawButton;
    private javax.swing.JButton Trans;
    private javax.swing.JPanel TransCash;
    private javax.swing.JPanel TransacHis;
    private javax.swing.JButton Transc;
    private javax.swing.JButton TransferButton;
    private javax.swing.JTable Transhis;
    private javax.swing.JButton Withd;
    private javax.swing.JPanel Withdraw;
    private javax.swing.JPanel Withdraw1;
    private javax.swing.JButton Withdrawbutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    // End of variables declaration//GEN-END:variables
}
