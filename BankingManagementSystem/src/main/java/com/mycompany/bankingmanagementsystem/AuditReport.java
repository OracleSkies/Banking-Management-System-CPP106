/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.bankingmanagementsystem;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Laurence
 */
public class AuditReport extends javax.swing.JFrame {

    /**
     * Creates new form AuditReport
     */
    public AuditReport() {
        initComponents();
        loadDataFromCSVFiles(); // Call the method to load data
        String filePath = "Transactions.csv";
        displayAllTransactionsAndUpdateLabels(filePath);
    }
    
    
        
       
    
    
    // Method to load and count word occurrences from CSV files
    private void loadDataFromCSVFiles() {
        // Define the list of CSV file paths
        String[] csvFiles = {"Transactions.csv", "Accounts.csv", "AccountApplications.csv"}; 

        // Define a map to store word counts
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Loop through each CSV file
        for (String csvFile : csvFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split(","); // Assuming CSV is comma-separated
                    for (String word : words) {
                        word = word.trim(); // Remove any extra spaces
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + csvFile);
            }
        }

        // Now display the results in the labels
        displayResults(wordCountMap);
    }

    // Method to update the JLabel components with the total count of words
    private void displayResults(Map<String, Integer> wordCountMap) {
        // Example: Display word counts in specific JLabels
        depCountDisplay.setText(String.valueOf(wordCountMap.getOrDefault("DEPOSIT", 0)));
        witCountDisplay.setText(String.valueOf(wordCountMap.getOrDefault("WITHDRAW", 0)));
        transCountDisplay.setText(String.valueOf(wordCountMap.getOrDefault("MONEY TRANSFER", 0)));
        payCountDisplay.setText(String.valueOf(wordCountMap.getOrDefault("EXPENSE", 0)));
        payCountDisplay.setText(String.valueOf(wordCountMap.getOrDefault("PAYMENTS", 0)));
        endBalanceDisplay.setText(String.valueOf(calculateTotalBalance(wordCountMap)));
    }

    // Calculate the total balance from word counts (Example calculation)
    private int calculateTotalBalance(Map<String, Integer> wordCountMap) {
        // Just an example: you can implement actual logic based on your requirements
        return wordCountMap.getOrDefault("DEPOSIT", 0) - wordCountMap.getOrDefault("WITHDRAW", 0);
    }

    public void displayAllTransactionsAndUpdateLabels(String filePath) {
        double totalDeposits = 0.0;
        double totalWithdrawals = 0.0;
        double totalTransfer = 0.0;
        double totalPayments = 0.0;

        // Create a DecimalFormat for formatting money values
        DecimalFormat df = new DecimalFormat("#");

        // Validate the file path
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Invalid file path.");
            return;
        }

        System.out.println("Displaying all transactions:");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    // Skip the header if present
                    isFirstLine = false;
                    continue;
                }

                // Split the line by commas (columns: Action, Date, Amount, Description)
                String[] values = line.split(",");
                if (values.length < 4) {
                    continue;
                }

                String action = values[4].trim();
                String date = values[0].trim();
                String amountStr = values[3].trim();
                String description = values[5].trim();

                // Display the row data
                System.out.printf("%-15s %-15s %-10s %-30s%n", action, date, amountStr, description);

                try {
                    // Parse the amount
                    double money = Double.parseDouble(amountStr);

                    // Add deposits and withdrawals to their respective totals
                    if (action.equalsIgnoreCase("DEPOSIT")) {
                        totalDeposits += money;
                    }  else if (action.equalsIgnoreCase("BILL")) {
                        totalTransfer += money;
                    } else if (action.equalsIgnoreCase("EPXENSE")) {
                        totalPayments += money;
                    } else if (action.equalsIgnoreCase("PAYMENTS")) {
                        totalPayments += money;
                    }else if (action.equalsIgnoreCase("WITHDRAW")) {
                        totalWithdrawals += money; // Withdrawals are added positively
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid money value: " + amountStr);
                }
            }

            // Calculate the total balance (sum of deposits, withdrawals, bills, and expenses)
            double totalBalance = totalDeposits + totalTransfer + totalTransfer - totalPayments + totalPayments - totalWithdrawals;

            // Format totals
            String formattedDeposits = "$" + df.format(totalDeposits);
            String formattedWithdrawals = "$" + df.format(totalWithdrawals);
            String formattedBills = "$" + df.format(totalTransfer);
            String formattedExpense = "$" + df.format(totalPayments);
            String formattedBalance = "$" + df.format(totalBalance);

            // Display summary totals in the console
            System.out.println("\nSummary:");
            System.out.println("Total Deposits: " + formattedDeposits);
            System.out.println("Total Withdrawals: " + formattedWithdrawals);
            System.out.println("Total Bills: " + formattedBills);
            System.out.println("Total Expenses: " + formattedExpense);
            System.out.println("Total Balance: " + formattedBalance);

            // Update the JLabels (replace with your actual JLabel names)
            paymentsTotalDisplay.setText(formattedExpense);   
            transTotalDisplay.setText(formattedBills);
            witTotalDisplay.setText(formattedWithdrawals);
            depTotalDisplay.setText(formattedDeposits);
            endBalanceDisplay.setText(formattedBalance);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + filePath);
            e.printStackTrace();
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

        jLabel124 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        witCountDisplay = new javax.swing.JLabel();
        Confirm = new javax.swing.JButton();
        jLabel122 = new javax.swing.JLabel();
        Payments = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        depCountDisplay = new javax.swing.JLabel();
        payCountDisplay = new javax.swing.JLabel();
        transCountDisplay = new javax.swing.JLabel();
        witTotalDisplay = new javax.swing.JLabel();
        endBalanceDisplay = new javax.swing.JLabel();
        transTotalDisplay = new javax.swing.JLabel();
        depTotalDisplay = new javax.swing.JLabel();
        paymentsTotalDisplay = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();

        jLabel124.setBackground(new java.awt.Color(255, 255, 255));
        jLabel124.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(255, 255, 255));
        jLabel124.setText("Deposits");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setOpaque(false);

        jLabel105.setBackground(new java.awt.Color(255, 255, 255));
        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setText("Audit Summary");

        jLabel106.setBackground(new java.awt.Color(255, 255, 255));
        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 255, 255));
        jLabel106.setText("Instances");

        jLabel107.setBackground(new java.awt.Color(255, 255, 255));
        jLabel107.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 255));
        jLabel107.setText("Amount");

        jLabel108.setBackground(new java.awt.Color(255, 255, 255));
        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 255));
        jLabel108.setText("Deposits");

        witCountDisplay.setBackground(new java.awt.Color(255, 255, 255));
        witCountDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        witCountDisplay.setForeground(new java.awt.Color(255, 255, 255));
        witCountDisplay.setText("1");

        Confirm.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Confirm.setForeground(new java.awt.Color(255, 255, 255));
        Confirm.setText("CONFIRM");
        Confirm.setBorderPainted(false);
        Confirm.setContentAreaFilled(false);
        Confirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ConfirmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ConfirmMouseExited(evt);
            }
        });
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        jLabel122.setBackground(new java.awt.Color(255, 255, 255));
        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(255, 255, 255));
        jLabel122.setText("Money Transfer");

        Payments.setBackground(new java.awt.Color(255, 255, 255));
        Payments.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Payments.setForeground(new java.awt.Color(255, 255, 255));
        Payments.setText("Payments");

        jLabel125.setBackground(new java.awt.Color(255, 255, 255));
        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(255, 255, 255));
        jLabel125.setText("Withdraws");

        jLabel132.setBackground(new java.awt.Color(255, 255, 255));
        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(255, 255, 255));
        jLabel132.setText("Ending Balance");

        depCountDisplay.setBackground(new java.awt.Color(255, 255, 255));
        depCountDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        depCountDisplay.setForeground(new java.awt.Color(255, 255, 255));
        depCountDisplay.setText("1");

        payCountDisplay.setBackground(new java.awt.Color(255, 255, 255));
        payCountDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        payCountDisplay.setForeground(new java.awt.Color(255, 255, 255));
        payCountDisplay.setText("1");

        transCountDisplay.setBackground(new java.awt.Color(255, 255, 255));
        transCountDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        transCountDisplay.setForeground(new java.awt.Color(255, 255, 255));
        transCountDisplay.setText("1");

        witTotalDisplay.setBackground(new java.awt.Color(255, 255, 255));
        witTotalDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        witTotalDisplay.setForeground(new java.awt.Color(255, 255, 255));
        witTotalDisplay.setText("1");

        endBalanceDisplay.setBackground(new java.awt.Color(255, 255, 255));
        endBalanceDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        endBalanceDisplay.setForeground(new java.awt.Color(255, 255, 255));
        endBalanceDisplay.setText("1");

        transTotalDisplay.setBackground(new java.awt.Color(255, 255, 255));
        transTotalDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        transTotalDisplay.setForeground(new java.awt.Color(255, 255, 255));
        transTotalDisplay.setText("1");

        depTotalDisplay.setBackground(new java.awt.Color(255, 255, 255));
        depTotalDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        depTotalDisplay.setForeground(new java.awt.Color(255, 255, 255));
        depTotalDisplay.setText("1");

        paymentsTotalDisplay.setBackground(new java.awt.Color(255, 255, 255));
        paymentsTotalDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        paymentsTotalDisplay.setForeground(new java.awt.Color(255, 255, 255));
        paymentsTotalDisplay.setText("1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addComponent(jLabel106))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel132)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Payments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel108)
                                .addComponent(jLabel122, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(94, 94, 94)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(depCountDisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                .addComponent(witCountDisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(transCountDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payCountDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(depTotalDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(witTotalDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(transTotalDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paymentsTotalDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endBalanceDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(312, 312, 312))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(382, 382, 382)
                        .addComponent(jLabel105))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(418, 418, 418)
                        .addComponent(Confirm)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel105)
                .addGap(47, 47, 47)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106)
                    .addComponent(jLabel107))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel108)
                            .addComponent(depCountDisplay))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel125)
                            .addComponent(witCountDisplay))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel122)
                            .addComponent(transCountDisplay))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Payments)
                            .addComponent(payCountDisplay))
                        .addGap(29, 29, 29)
                        .addComponent(jLabel132))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(depTotalDisplay)
                        .addGap(30, 30, 30)
                        .addComponent(witTotalDisplay)
                        .addGap(29, 29, 29)
                        .addComponent(transTotalDisplay)
                        .addGap(29, 29, 29)
                        .addComponent(paymentsTotalDisplay)
                        .addGap(29, 29, 29)
                        .addComponent(endBalanceDisplay)))
                .addGap(75, 75, 75)
                .addComponent(Confirm)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 1200, 690));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1460, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 1460, 5));
        jPanel8.getAccessibleContext().setAccessibleName("");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1460, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 1460, 5));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Untitled design.png"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, -1, -1));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        AdminMain main = new AdminMain();
        main.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_ConfirmActionPerformed

    private void ConfirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfirmMouseEntered
        // TODO add your handling code here:
        Confirm.setContentAreaFilled(true);
        Confirm.setForeground(Color.black);
        Confirm.setBackground(Color.cyan);
    }//GEN-LAST:event_ConfirmMouseEntered

    private void ConfirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfirmMouseExited
        // TODO add your handling code here:
        Confirm.setContentAreaFilled(false);
        Confirm.setForeground(Color.white);
        
    }//GEN-LAST:event_ConfirmMouseExited

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
            java.util.logging.Logger.getLogger(AuditReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuditReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuditReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuditReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuditReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Confirm;
    private javax.swing.JLabel Payments;
    private javax.swing.JLabel depCountDisplay;
    private javax.swing.JLabel depTotalDisplay;
    private javax.swing.JLabel endBalanceDisplay;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel payCountDisplay;
    private javax.swing.JLabel paymentsTotalDisplay;
    private javax.swing.JLabel transCountDisplay;
    private javax.swing.JLabel transTotalDisplay;
    private javax.swing.JLabel witCountDisplay;
    private javax.swing.JLabel witTotalDisplay;
    // End of variables declaration//GEN-END:variables
}
