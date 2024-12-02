/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserBranch;

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
public class BankStatement extends javax.swing.JFrame {

    /**
     * Creates new form BankStatement
     */
    public BankStatement() {
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
        DepDis.setText(String.valueOf(wordCountMap.getOrDefault("Deposit", 0)));
        WidDis.setText(String.valueOf(wordCountMap.getOrDefault("Withdraw", 0)));
        BillDis.setText(String.valueOf(wordCountMap.getOrDefault("Bill", 0)));
        ExpDis.setText(String.valueOf(wordCountMap.getOrDefault("Expense", 0)));
        TotalBal.setText(String.valueOf(calculateTotalBalance(wordCountMap)));
    }

    // Calculate the total balance from word counts (Example calculation)
    private int calculateTotalBalance(Map<String, Integer> wordCountMap) {
        // Just an example: you can implement actual logic based on your requirements
        return wordCountMap.getOrDefault("Deposit", 0) - wordCountMap.getOrDefault("Withdraw", 0);
    }

    public void displayAllTransactionsAndUpdateLabels(String filePath) {
        double totalDeposits = 0.0;
        double totalWithdrawals = 0.0;
        double totalBills = 0.0;
        double totalExpense = 0.0;

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
                    if (action.equalsIgnoreCase("Deposit")) {
                        totalDeposits += money;
                    }  else if (action.equalsIgnoreCase("Bill")) {
                        totalBills += money;
                    } else if (action.equalsIgnoreCase("Expense")) {
                        totalExpense += money;
                    }else if (action.equalsIgnoreCase("Withdraw")) {
                        totalWithdrawals += money; // Withdrawals are added positively
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid money value: " + amountStr);
                }
            }

            // Calculate the total balance (sum of deposits, withdrawals, bills, and expenses)
            double totalBalance = totalDeposits + totalBills + totalBills - totalExpense + totalExpense - totalWithdrawals - totalWithdrawals;

            // Format totals
            String formattedDeposits = "$" + df.format(totalDeposits);
            String formattedWithdrawals = "$" + df.format(totalWithdrawals);
            String formattedBills = "$" + df.format(totalBills);
            String formattedExpense = "$" + df.format(totalExpense);
            String formattedBalance = "$" + df.format(totalBalance);

            // Display summary totals in the console
            System.out.println("\nSummary:");
            System.out.println("Total Deposits: " + formattedDeposits);
            System.out.println("Total Withdrawals: " + formattedWithdrawals);
            System.out.println("Total Bills: " + formattedBills);
            System.out.println("Total Expenses: " + formattedExpense);
            System.out.println("Total Balance: " + formattedBalance);

            // Update the JLabels (replace with your actual JLabel names)
            ExpTotal1.setText(formattedExpense);   
            BillTotal1.setText(formattedBills);
            WidTotal1.setText(formattedWithdrawals);
            DepTotal1.setText(formattedDeposits);
            TotalBal.setText(formattedBalance);

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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        WidDis = new javax.swing.JLabel();
        Confirm = new javax.swing.JButton();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        DepDis = new javax.swing.JLabel();
        ExpDis = new javax.swing.JLabel();
        BillDis = new javax.swing.JLabel();
        WidTotal1 = new javax.swing.JLabel();
        TotalBal = new javax.swing.JLabel();
        BillTotal1 = new javax.swing.JLabel();
        DepTotal1 = new javax.swing.JLabel();
        ExpTotal1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.setMinimumSize(new java.awt.Dimension(1490, 800));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1490, 800));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setOpaque(false);

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

        WidDis.setBackground(new java.awt.Color(255, 255, 255));
        WidDis.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        WidDis.setForeground(new java.awt.Color(255, 255, 255));
        WidDis.setText("0");

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
        jLabel122.setText("Bills");

        jLabel123.setBackground(new java.awt.Color(255, 255, 255));
        jLabel123.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(255, 255, 255));
        jLabel123.setText("Expenditures");

        jLabel125.setBackground(new java.awt.Color(255, 255, 255));
        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(255, 255, 255));
        jLabel125.setText("Withdraws");

        jLabel132.setBackground(new java.awt.Color(255, 255, 255));
        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(255, 255, 255));
        jLabel132.setText("Ending Balance");

        DepDis.setBackground(new java.awt.Color(255, 255, 255));
        DepDis.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        DepDis.setForeground(new java.awt.Color(255, 255, 255));
        DepDis.setText("0");

        ExpDis.setBackground(new java.awt.Color(255, 255, 255));
        ExpDis.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        ExpDis.setForeground(new java.awt.Color(255, 255, 255));
        ExpDis.setText("0");

        BillDis.setBackground(new java.awt.Color(255, 255, 255));
        BillDis.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        BillDis.setForeground(new java.awt.Color(255, 255, 255));
        BillDis.setText("0");

        WidTotal1.setBackground(new java.awt.Color(255, 255, 255));
        WidTotal1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        WidTotal1.setForeground(new java.awt.Color(255, 255, 255));
        WidTotal1.setText("  ");

        TotalBal.setBackground(new java.awt.Color(255, 255, 255));
        TotalBal.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        TotalBal.setForeground(new java.awt.Color(255, 255, 255));
        TotalBal.setText("0");

        BillTotal1.setBackground(new java.awt.Color(255, 255, 255));
        BillTotal1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        BillTotal1.setForeground(new java.awt.Color(255, 255, 255));
        BillTotal1.setText("  ");

        DepTotal1.setBackground(new java.awt.Color(255, 255, 255));
        DepTotal1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        DepTotal1.setForeground(new java.awt.Color(255, 255, 255));
        DepTotal1.setText("  ");

        ExpTotal1.setBackground(new java.awt.Color(255, 255, 255));
        ExpTotal1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        ExpTotal1.setForeground(new java.awt.Color(255, 255, 255));
        ExpTotal1.setText("  ");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Adress");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Account Number");

        Name.setBackground(new java.awt.Color(255, 255, 255));
        Name.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Name.setForeground(new java.awt.Color(255, 255, 255));
        Name.setText("Name");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(261, 261, 261)
                                .addComponent(jLabel106)
                                .addGap(178, 178, 178)
                                .addComponent(jLabel107))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel123)
                                            .addComponent(jLabel125)
                                            .addComponent(jLabel122)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel108)
                                            .addComponent(jLabel3)
                                            .addComponent(Name))
                                        .addGap(93, 93, 93)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(WidDis)
                                            .addComponent(BillDis)
                                            .addComponent(DepDis)
                                            .addComponent(ExpDis)))
                                    .addComponent(jLabel132))
                                .addGap(303, 303, 303)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TotalBal)
                                    .addComponent(ExpTotal1)
                                    .addComponent(DepTotal1)
                                    .addComponent(BillTotal1)
                                    .addComponent(WidTotal1)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(404, 404, 404)
                        .addComponent(Confirm)))
                .addContainerGap(414, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel106)
                            .addComponent(jLabel107))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DepDis)
                            .addComponent(DepTotal1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(WidDis)
                            .addComponent(WidTotal1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BillDis)
                            .addComponent(BillTotal1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ExpDis)
                            .addComponent(ExpTotal1))
                        .addGap(18, 18, 18)
                        .addComponent(TotalBal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Confirm))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(Name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel108)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel125)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel122)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel123)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel132)))
                .addGap(343, 396, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 1200, 960));

        jLabel105.setBackground(new java.awt.Color(255, 255, 255));
        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setText("Bank Statement");
        jPanel1.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 1290, 750));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Untitled design.png"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        UserInterface user = new UserInterface();
        user.setVisible(true);
        setVisible(false);

    }//GEN-LAST:event_ConfirmActionPerformed

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
            java.util.logging.Logger.getLogger(BankStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BankStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BankStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BankStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BankStatement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BillDis;
    private javax.swing.JLabel BillTotal1;
    private javax.swing.JButton Confirm;
    private javax.swing.JLabel DepDis;
    private javax.swing.JLabel DepTotal1;
    private javax.swing.JLabel ExpDis;
    private javax.swing.JLabel ExpTotal1;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel TotalBal;
    private javax.swing.JLabel WidDis;
    private javax.swing.JLabel WidTotal1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
