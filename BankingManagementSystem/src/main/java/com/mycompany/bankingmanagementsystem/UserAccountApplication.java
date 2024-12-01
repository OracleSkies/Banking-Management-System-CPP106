/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.bankingmanagementsystem;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Laurence
 */
public class UserAccountApplication extends javax.swing.JFrame {

    /**
     * Creates new form USERACCOUNTAPPLICATION_ADMIN___
     */
    private String username;
    private String password;
    private int row;
    
    public UserAccountApplication(String username, String password,String name,String birthdate,String phoneNumber,String address,int row) {
        initComponents();
        this.username = username;
        this.password = password;
        nameLabel.setText(name);
        birthLabel.setText(birthdate);
        phoneLabel.setText(phoneNumber);
        addressLabel.setText(address);
        this.row = row;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        DECLINE = new javax.swing.JButton();
        APPROVE = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        birthLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, -1, -1));

        Name.setBackground(new java.awt.Color(255, 255, 255));
        Name.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        Name.setForeground(new java.awt.Color(255, 255, 255));
        Name.setText("Name:");
        jPanel2.add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 120, 80));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("BirthDate:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 190, 90));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Phone Number:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 270, 60));

        DECLINE.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        DECLINE.setForeground(new java.awt.Color(255, 255, 255));
        DECLINE.setText("DECLINE");
        DECLINE.setBorderPainted(false);
        DECLINE.setContentAreaFilled(false);
        DECLINE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DECLINEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DECLINEMouseExited(evt);
            }
        });
        DECLINE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DECLINEActionPerformed(evt);
            }
        });
        jPanel2.add(DECLINE, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 470, 210, 80));

        APPROVE.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        APPROVE.setForeground(new java.awt.Color(255, 255, 255));
        APPROVE.setText("APPROVE");
        APPROVE.setBorderPainted(false);
        APPROVE.setContentAreaFilled(false);
        APPROVE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                APPROVEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                APPROVEMouseExited(evt);
            }
        });
        APPROVE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                APPROVEActionPerformed(evt);
            }
        });
        jPanel2.add(APPROVE, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, 210, 80));

        backButton.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("BACK");
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButtonMouseExited(evt);
            }
        });
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        jPanel2.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 600, 210, 80));

        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        nameLabel.setBackground(new java.awt.Color(255, 255, 255));
        nameLabel.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 350, 50));

        birthLabel.setBackground(new java.awt.Color(255, 255, 255));
        birthLabel.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        birthLabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(birthLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 350, 50));

        phoneLabel.setBackground(new java.awt.Color(255, 255, 255));
        phoneLabel.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        phoneLabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(phoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 260, 350, 50));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("User Account Application");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 440, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Address:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 170, 90));

        addressLabel.setBackground(new java.awt.Color(255, 255, 255));
        addressLabel.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        addressLabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(addressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, 350, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 1250, 710));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Untitled design.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="EVENTS">
    private void DECLINEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DECLINEMouseEntered
        // TODO add your handling code here:
        DECLINE.setContentAreaFilled(true);
        DECLINE.setForeground(Color.black);
        DECLINE.setBackground(Color.red);
    }//GEN-LAST:event_DECLINEMouseEntered

    private void DECLINEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DECLINEMouseExited
        // TODO add your handling code here:
        DECLINE.setContentAreaFilled(false);
        DECLINE.setForeground(Color.white);

    }//GEN-LAST:event_DECLINEMouseExited

    private void DECLINEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DECLINEActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(
            this, // Parent component (current frame)
            "Are you sure you want to decline this account application?", // Message
            "Confirm Account Decline", // Title of the dialog
            JOptionPane.YES_NO_OPTION, // Option type
            JOptionPane.QUESTION_MESSAGE // Icon type
        );

        if (response == JOptionPane.YES_OPTION) {
            deleteAccountFromFile(row);
            JOptionPane.showMessageDialog(this, "Account application declined!");
            refreshAccountTables();
            this.dispose(); // Close the current window
        }
        
    }//GEN-LAST:event_DECLINEActionPerformed

    private void APPROVEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_APPROVEMouseEntered
        // TODO add your handling code here:
        APPROVE.setContentAreaFilled(true);
        APPROVE.setForeground(Color.black);
        APPROVE.setBackground(Color.green);
    }//GEN-LAST:event_APPROVEMouseEntered

    private void APPROVEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_APPROVEMouseExited
        // TODO add your handling code here:
        APPROVE.setContentAreaFilled(false);
        APPROVE.setForeground(Color.white);

    }//GEN-LAST:event_APPROVEMouseExited

    private void APPROVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_APPROVEActionPerformed
        // TODO add your handling code here:
        String usernameVar;
        String passwordVar;
        usernameVar = this.username;
        passwordVar = this.password;
        String name = nameLabel.getText();
        String birthdate = birthLabel.getText();
        String phoneNumber = phoneLabel.getText();
        String address = addressLabel.getText();
//        String type = this.type;
        RNGforAccountNumber();
        accountApplicationWriteOnFile(usernameVar,passwordVar,name,birthdate,phoneNumber,address);
        deleteAccountFromFile(row);
        refreshAccountTables();
        setVisible(false);
    }//GEN-LAST:event_APPROVEActionPerformed

    private void backButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseEntered
        // TODO add your handling code here:
        backButton.setContentAreaFilled(true);
        backButton.setForeground(Color.black);
        backButton.setBackground(Color.cyan);
    }//GEN-LAST:event_backButtonMouseEntered

    private void backButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseExited
        // TODO add your handling code here:
        backButton.setContentAreaFilled(false);
        backButton.setForeground(Color.white);
    }//GEN-LAST:event_backButtonMouseExited

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        returnToAdmin();
    }//GEN-LAST:event_backButtonActionPerformed

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="FUNCTIONALITIES">
    private void deleteAccountFromFile(int row){
        //THIS METHOD DECLINES THE APPLICATION OF THE USER. IT REMOVES ITS INFORMATION IN THE CSV
        String filePath = "AccountApplications.csv";  // Change to your CSV file path
        int rowToDelete = row; // Index of the row to delete (0-based index)

        // Read the CSV file into a List of Strings (rows)
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);  // Add each line to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the specific row (if it exists)
        if (rowToDelete >= 0 && rowToDelete < lines.size()) {
            lines.remove(rowToDelete); // Remove the row at the specified index
        } else {
            System.out.println("Row index is out of bounds.");
            return;
        }

        // Write the updated content back to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();  // Write each line back into the file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void refreshAccountTables(){
        AdminMain admin = new AdminMain();
        admin.setVisible(true);
        admin.loadAccountsForAccApplication("AccountApplications.csv");
    }
    
    private void accountApplicationWriteOnFile(String username,String password,String name,String birthdate,String phoneNumber,String address){
        //ADDS THE ACCOUNT TO ACCOUNT DATABASE
        try (var writer = new BufferedWriter(new FileWriter("Accounts.csv", true))){
            int accountNumber = RNGforAccountNumber();
            writer.write(username + "," + password + "," + name + "," + birthdate + "," + phoneNumber + "," + address+ "," + accountNumber + "," + "user" + "," + "no");
            writer.newLine();

            JOptionPane.showMessageDialog(this, "Account Verified!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private int RNGforAccountNumber(){
        Random random = new Random();
        // Generate a random 7-digit number
        int randomNumber = 10000000 + random.nextInt(90000); // Ensures the number is always 7 digits
        return randomNumber;
    }
    
    private void returnToAdmin(){
        AdminMain admin = new AdminMain();
        admin.setVisible(true);
        setVisible(false);
    }
    
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MAIN(Nonfunctional)">
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
//            java.util.logging.Logger.getLogger(UserAccountApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(UserAccountApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(UserAccountApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(UserAccountApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        
//
//        /* Create and display the form */
////        java.awt.EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                new UserAccountApplication().setVisible(true);
////            }
////        });
//
//    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="WIDGET VARIABLES">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton APPROVE;
    private javax.swing.JButton DECLINE;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel birthLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel phoneLabel;
    // End of variables declaration//GEN-END:variables
}
//</editor-fold>