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
    public BankStatement(String name,String accNum, String address) {
        initComponents();
//        loadDataFromCSVFiles(); // Call the method to load data
//        String filePath = "Transactions.csv";
//        displayAllTransactionsAndUpdateLabels(filePath);
        updateLabelsByUser(name,accNum,address);
        
        
    }
     // <editor-fold defaultstate="collapsed" desc="METHODS NI GABUYO">  
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
        depCountDisplay.setText(String.valueOf(wordCountMap.getOrDefault("Deposit", 0)));
        withCountDisplay.setText(String.valueOf(wordCountMap.getOrDefault("Withdraw", 0)));
        transCountDisplay.setText(String.valueOf(wordCountMap.getOrDefault("Bill", 0)));
        payCountDisplay.setText(String.valueOf(wordCountMap.getOrDefault("Expense", 0)));
        endingBalanceDisplay.setText(String.valueOf(calculateTotalBalance(wordCountMap)));
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
            double totalBalance = totalDeposits + totalBills + totalBills - totalExpense + totalExpense - totalWithdrawals;

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
            payTotalDisplay.setText(formattedExpense);   
            transTotalDisplay.setText(formattedBills);
            withTotalDisplay.setText(formattedWithdrawals);
            depTotalDisplay.setText(formattedDeposits);
            endingBalanceDisplay.setText(formattedBalance);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + filePath);
            e.printStackTrace();
        }
    }

    // </editor-fold>  
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
        withCountDisplay = new javax.swing.JLabel();
        Confirm = new javax.swing.JButton();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        depCountDisplay = new javax.swing.JLabel();
        payCountDisplay = new javax.swing.JLabel();
        transCountDisplay = new javax.swing.JLabel();
        withTotalDisplay = new javax.swing.JLabel();
        endingBalanceDisplay = new javax.swing.JLabel();
        transTotalDisplay = new javax.swing.JLabel();
        depTotalDisplay = new javax.swing.JLabel();
        payTotalDisplay = new javax.swing.JLabel();
        addressDisplay = new javax.swing.JLabel();
        accNumberDisplay = new javax.swing.JLabel();
        nameDisplay = new javax.swing.JLabel();
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
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel106.setBackground(new java.awt.Color(255, 255, 255));
        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 255, 255));
        jLabel106.setText("Instances");
        jPanel5.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 134, -1, -1));

        jLabel107.setBackground(new java.awt.Color(255, 255, 255));
        jLabel107.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 255));
        jLabel107.setText("Amount");
        jPanel5.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 134, -1, -1));

        jLabel108.setBackground(new java.awt.Color(255, 255, 255));
        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 255));
        jLabel108.setText("Deposits");
        jPanel5.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 191, -1, -1));

        withCountDisplay.setBackground(new java.awt.Color(255, 255, 255));
        withCountDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        withCountDisplay.setForeground(new java.awt.Color(255, 255, 255));
        withCountDisplay.setText("0");
        jPanel5.add(withCountDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 260, 110, -1));

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
        jPanel5.add(Confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 512, -1, -1));

        jLabel122.setBackground(new java.awt.Color(255, 255, 255));
        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(255, 255, 255));
        jLabel122.setText("Money Transfer");
        jPanel5.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 323, 280, -1));

        jLabel123.setBackground(new java.awt.Color(255, 255, 255));
        jLabel123.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(255, 255, 255));
        jLabel123.setText("Payments");
        jPanel5.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 383, 270, -1));

        jLabel125.setBackground(new java.awt.Color(255, 255, 255));
        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(255, 255, 255));
        jLabel125.setText("Withdraws");
        jPanel5.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 257, -1, -1));

        jLabel132.setBackground(new java.awt.Color(255, 255, 255));
        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(255, 255, 255));
        jLabel132.setText("Ending Balance");
        jPanel5.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 449, -1, -1));

        depCountDisplay.setBackground(new java.awt.Color(255, 255, 255));
        depCountDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        depCountDisplay.setForeground(new java.awt.Color(255, 255, 255));
        depCountDisplay.setText("0");
        jPanel5.add(depCountDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 200, 120, -1));

        payCountDisplay.setBackground(new java.awt.Color(255, 255, 255));
        payCountDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        payCountDisplay.setForeground(new java.awt.Color(255, 255, 255));
        payCountDisplay.setText("0");
        jPanel5.add(payCountDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 392, 110, -1));

        transCountDisplay.setBackground(new java.awt.Color(255, 255, 255));
        transCountDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        transCountDisplay.setForeground(new java.awt.Color(255, 255, 255));
        transCountDisplay.setText("0");
        jPanel5.add(transCountDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 326, 130, -1));

        withTotalDisplay.setBackground(new java.awt.Color(255, 255, 255));
        withTotalDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        withTotalDisplay.setForeground(new java.awt.Color(255, 255, 255));
        withTotalDisplay.setText("1");
        jPanel5.add(withTotalDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 260, 200, -1));

        endingBalanceDisplay.setBackground(new java.awt.Color(255, 255, 255));
        endingBalanceDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        endingBalanceDisplay.setForeground(new java.awt.Color(255, 255, 255));
        endingBalanceDisplay.setText("1");
        jPanel5.add(endingBalanceDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 450, 250, -1));

        transTotalDisplay.setBackground(new java.awt.Color(255, 255, 255));
        transTotalDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        transTotalDisplay.setForeground(new java.awt.Color(255, 255, 255));
        transTotalDisplay.setText("1");
        jPanel5.add(transTotalDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 320, 210, -1));

        depTotalDisplay.setBackground(new java.awt.Color(255, 255, 255));
        depTotalDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        depTotalDisplay.setForeground(new java.awt.Color(255, 255, 255));
        depTotalDisplay.setText("1");
        jPanel5.add(depTotalDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, 200, -1));

        payTotalDisplay.setBackground(new java.awt.Color(255, 255, 255));
        payTotalDisplay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        payTotalDisplay.setForeground(new java.awt.Color(255, 255, 255));
        payTotalDisplay.setText("1");
        jPanel5.add(payTotalDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 390, 230, -1));

        addressDisplay.setBackground(new java.awt.Color(255, 255, 255));
        addressDisplay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        addressDisplay.setForeground(new java.awt.Color(255, 255, 255));
        addressDisplay.setText("Adress");
        jPanel5.add(addressDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 82, 328, -1));

        accNumberDisplay.setBackground(new java.awt.Color(255, 255, 255));
        accNumberDisplay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        accNumberDisplay.setForeground(new java.awt.Color(255, 255, 255));
        accNumberDisplay.setText("Account Number");
        jPanel5.add(accNumberDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 44, 345, -1));

        nameDisplay.setBackground(new java.awt.Color(255, 255, 255));
        nameDisplay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        nameDisplay.setForeground(new java.awt.Color(255, 255, 255));
        nameDisplay.setText("Name");
        jPanel5.add(nameDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 6, 384, -1));

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

    // <editor-fold defaultstate="collapsed" desc="EVENTS">  
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

        setVisible(false);
    }//GEN-LAST:event_ConfirmActionPerformed

    // </editor-fold >  
    
// <editor-fold defaultstate="collapsed" desc="FUNCTIONALITIES">  
    private void updateLabelsByUser(String name,String accNum, String address){
        nameDisplay.setText(name);
        accNumberDisplay.setText(accNum);
        addressDisplay.setText(address);
        String deposit = Integer.toString(countByAction("DEPOSIT",accNum));
        String withdraw = Integer.toString(countByAction("WITHDRAW",accNum));
        String transfer = Integer.toString(countByAction("MONEY TRANSFER",accNum));
        String payment = Integer.toString(countByAction("PAYMENTS",accNum));
        depCountDisplay.setText(deposit);
        withCountDisplay.setText(withdraw);
        transCountDisplay.setText(transfer);
        payCountDisplay.setText(payment);
        String depositAmount = Integer.toString(addByAction("DEPOSIT",accNum));
        String withdrawAmount = Integer.toString(addByAction("WITHDRAW",accNum));
        String transferAmount = Integer.toString(addByAction("MONEY TRANSFER",accNum));
        String paymentAmount = Integer.toString(addByAction("PAYMENTS",accNum));
        depTotalDisplay.setText("₱" + depositAmount);
        withTotalDisplay.setText("₱" +withdrawAmount);
        transTotalDisplay.setText("₱" +transferAmount);
        payTotalDisplay.setText("₱" +paymentAmount);
        String endBalance = Integer.toString(endingBalance(addByAction("DEPOSIT",accNum),addByAction("WITHDRAW",accNum),addByAction("MONEY TRANSFER",accNum),addByAction("PAYMENTS",accNum)));
        endingBalanceDisplay.setText("₱" + endBalance);
    }
    
    private int countByAction(String action, String accNum){
        //Counts all the action done by the user
        int actionCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("Transactions.csv"))) {
            String line;
            // Read the CSV file line by line
            while ((line = br.readLine()) != null) {
                // Split the line into columns by comma
                String[] columns = line.split(",");
                
                // Check if the first column (fruit) is "lemon"
                if (columns[4].trim().equalsIgnoreCase(action) && columns[2].trim().equalsIgnoreCase(accNum)) {
                    actionCount++;
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actionCount;
    }
    
    private int addByAction(String action, String accNum){
        //gets the sum of amount per action done by the user
        int actionAmount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("Transactions.csv"))) {
            String line;
            // Read the CSV file line by line
            while ((line = br.readLine()) != null) {
                // Split the line into columns by comma
                String[] columns = line.split(",");
                
                // Check if the first column (fruit) is "lemon"
                if (columns[4].trim().equalsIgnoreCase(action) && columns[2].trim().equalsIgnoreCase(accNum)) {
                    int amountToInt = Integer.parseInt(columns[3]);
                    actionAmount = actionAmount + amountToInt;
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actionAmount;
    }
    
    private int endingBalance(int deposit, int withdraw, int transfer, int payments){
        //This calculate the ending balance of the user
        int endBalance = deposit - (withdraw + transfer + payments);
        return endBalance;
    }
    // </editor-fold >  
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(BankStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BankStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BankStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BankStatement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BankStatement().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Confirm;
    private javax.swing.JLabel accNumberDisplay;
    private javax.swing.JLabel addressDisplay;
    private javax.swing.JLabel depCountDisplay;
    private javax.swing.JLabel depTotalDisplay;
    private javax.swing.JLabel endingBalanceDisplay;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel nameDisplay;
    private javax.swing.JLabel payCountDisplay;
    private javax.swing.JLabel payTotalDisplay;
    private javax.swing.JLabel transCountDisplay;
    private javax.swing.JLabel transTotalDisplay;
    private javax.swing.JLabel withCountDisplay;
    private javax.swing.JLabel withTotalDisplay;
    // End of variables declaration//GEN-END:variables
}
