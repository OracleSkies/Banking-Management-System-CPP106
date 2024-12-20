/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserBranch;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class BillsGUI extends javax.swing.JFrame {

    /**
     * Creates new form BillsGUI
     */
    
    private String username;
    private String password;
    private String name;
    private String birthdate;
    private String phoneNumber;
    private String address;
    private String accNumber;
    private String type;
    private String card;
    private String pin;
    private int balance;
    private int reserve;
    public BillsGUI(String username,
            String password,
            String name,
            String birthdate,
            String phoneNumber,
            String address,
            String accNumber,
            String type,
            String card,
            String pin,
            int balance) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.accNumber = accNumber;
        this.type = type;
        this.card = card;
        this.pin = pin;
        this.balance = balance;
        initComponents();
       
        BillsTable.setOpaque(false);
        ((DefaultTableCellRenderer)BillsTable.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        BillsTable.setShowGrid(false);
        
        // Example usage of the loadCSV function
        loadCSV("output.csv");
        calculateTotal();

    }
    
    private void loadCSV(String filePath) {
        DefaultTableModel model = (DefaultTableModel) BillsTable.getModel();
        model.setRowCount(0); // Clear existing rows
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Assuming CSV is comma-separated
                model.addRow(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void calculateTotal() {
        DefaultTableModel model = (DefaultTableModel) BillsTable.getModel();
        double total = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                // Parse the value in the second column as a double
                double amount = Double.parseDouble(model.getValueAt(i, 1).toString());
                total += amount;
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format in row " + (i + 1) + ": " + e.getMessage());
            }
        }

        // Display the total in the BillDis label
        BillDis.setText(String.format("%.2f", total));
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        BillsTable = new javax.swing.JTable();
        Decline = new javax.swing.JButton();
        Confirm = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BillDis = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        BillsTable.setForeground(new java.awt.Color(255, 255, 255));
        BillsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Bills", "Amount"
            }
        ));
        jScrollPane1.setViewportView(BillsTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 590, 220));

        Decline.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Decline.setForeground(new java.awt.Color(255, 255, 255));
        Decline.setText("DECLINE");
        Decline.setBorderPainted(false);
        Decline.setContentAreaFilled(false);
        Decline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DeclineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DeclineMouseExited(evt);
            }
        });
        Decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeclineActionPerformed(evt);
            }
        });
        getContentPane().add(Decline, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 160, 50));

        Confirm.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
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
        getContentPane().add(Confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 170, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pay Bills");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 90, 30));

        BillDis.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        BillDis.setForeground(new java.awt.Color(255, 255, 255));
        BillDis.setText("000000");
        getContentPane().add(BillDis, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 150, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bills.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DeclineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeclineActionPerformed
        // Clear the table
        DefaultTableModel model = (DefaultTableModel) BillsTable.getModel();
        model.setRowCount(0); // Clear table rows

        // Clear the CSV file
        try (java.io.FileWriter fw = new java.io.FileWriter("output.csv", false)) {
            fw.write(""); // Overwrite with an empty string
            System.out.println("CSV file cleared successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear the total display
        BillDis.setText("000000");

        // Optionally hide the form
        setVisible(false);
    }//GEN-LAST:event_DeclineActionPerformed

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        // Get the total amount from the BillDis label
        String totalAmount = BillDis.getText();
        
//        if (subtractPaymentToBalance(totalAmount) == false){
//            return;
//        }
        // Prepare the data to write
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        String name = this.name; // Replace with dynamic input if needed
        String bankAccountNumber = this.accNumber; // Replace with dynamic input if available
        String action = "PAYMENTS";
        String description = "Payment of Bills"; // Replace with specific or dynamic input if needed

        String csvLine = String.format("%s,%s,%s,%s,%s,%s%n", 
                                       timestamp, 
                                       name, 
                                       bankAccountNumber, 
                                       totalAmount, 
                                       action, 
                                       description);

        // Write to the Transaction.csv file
        try (java.io.FileWriter fw = new java.io.FileWriter("Transactions.csv", true)) {
            // Check if the file is empty to write the header first
            java.io.File file = new java.io.File("Transactions.csv");
            if (file.length() == 0) {
                fw.write("Timestamp,Name,Bank Account number,Amount,Action,Description\n");
            }
            fw.write(csvLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Optionally provide user feedback
        javax.swing.JOptionPane.showMessageDialog(this, "Transaction confirmed and recorded!");
        setVisible(false);
    }//GEN-LAST:event_ConfirmActionPerformed

    private boolean subtractPaymentToBalance(String paymentNumber){
        getReserve();
        int witToInt = Integer.parseInt(paymentNumber);
        int reserveCheck = this.reserve + witToInt; // This line of code add the current reserve with the withdraw amount to compare if the withdraw amount will overflow the bank reserve
        
        if (reserveCheck > 1000000){
            JOptionPane.showMessageDialog(this, "Cannot withdraw money. Bank reserve will reach maximum capacity", "Bank Reserve Issue", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(balance < witToInt){
            JOptionPane.showMessageDialog(this, "Cannot withdraw money. Insufficient balance", "Insufficient Balance", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        balance = balance - witToInt;
        writeLine(username,password,name,birthdate,phoneNumber,address,accNumber,type,card,pin,balance);
        this.balance = balance;
        this.reserve = reserve + witToInt;
        updateReserve();
        return true;
    }
    
    private void writeLine(String username,
            String password,
            String name,
            String birthdate,
            String phoneNumber,
            String address,
            String accNumber,
            String type,
            String card,
            String pin,
            int balanceToDB){
        
         try (var writer = new BufferedWriter(new FileWriter("Accounts.csv", true))){
            writer.write(username + "," + password + "," + name + "," + birthdate + "," + phoneNumber + "," + address+ "," + accNumber + "," + type + "," + card + "," + pin + "," + balanceToDB);
            writer.newLine();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void getReserve(){
        // THIS METHOD READS THE INTEGER IN THE FILE
        try (BufferedReader br = new BufferedReader(new FileReader("bankReserve.csv"))) {
            String line;
            // Read the CSV file line by line
            if ((line = br.readLine()) != null) {
                this.reserve = Integer.parseInt(line.trim());
                // Check if the first column (fruit) is "lemon"
            }
            br.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void updateReserve(){
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("bankReserve.csv"))){
            writer.write(String.valueOf(this.reserve));  // Convert the integer to a string and write it to the file
            writer.close();
            } catch (IOException e) {
                System.out.println("Error reading or writing to file: " + e.getMessage());
            }
    }
    private void ConfirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfirmMouseEntered
        // TODO add your handling code here:
        Confirm.setContentAreaFilled(true);
        Confirm.setBackground(Color.cyan);
        Confirm.setForeground(Color.black);
    }//GEN-LAST:event_ConfirmMouseEntered

    private void ConfirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfirmMouseExited
        // TODO add your handling code here:
        Confirm.setContentAreaFilled(false);
        Confirm.setForeground(Color.white);
    }//GEN-LAST:event_ConfirmMouseExited

    private void DeclineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeclineMouseEntered
        // TODO add your handling code here:
        Decline.setContentAreaFilled(true);
        Decline.setBackground(Color.cyan);
        Decline.setForeground(Color.black);
    }//GEN-LAST:event_DeclineMouseEntered

    private void DeclineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeclineMouseExited
        // TODO add your handling code here:
        Decline.setContentAreaFilled(false);
        Decline.setForeground(Color.white);
    }//GEN-LAST:event_DeclineMouseExited

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(BillsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BillsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BillsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BillsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BillsGUI().setVisible(true);
//            }
//        });
//        //</editor-fold>
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BillDis;
    private javax.swing.JTable BillsTable;
    private javax.swing.JButton Confirm;
    private javax.swing.JButton Decline;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
