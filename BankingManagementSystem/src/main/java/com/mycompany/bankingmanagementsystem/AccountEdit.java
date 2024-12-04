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
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Laurence
 */
public class AccountEdit extends javax.swing.JFrame {

    //=================== Naka EME pa ang acct number, Ayusin yon
    
    /**
     * Creates new form USERACCOUNTADMINISTRATION
     */
    private int row;
    private String type;
    private String accNumber;
    private String card;
    public AccountEdit(String username, String password,String name,String birthdate,String phoneNumber,String address,String accNumber,String type,String card,int row) {
        initComponents();
        usernameField.setText(username);
        passwordField.setText(password);
        nameField.setText(name);
        birthdateField.setText(birthdate);
        phoneNumField.setText(phoneNumber);
        addressField.setText(address);
        this.accNumber = accNumber;
        this.type = type;
        this.card = card;
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

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jSlider1 = new javax.swing.JSlider();
        jSlider2 = new javax.swing.JSlider();
        jToggleButton1 = new javax.swing.JToggleButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        pass = new javax.swing.JLabel();
        cpass = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        birthdateField = new javax.swing.JTextField();
        phoneNumField = new javax.swing.JTextField();
        confirmPassField = new javax.swing.JPasswordField();
        passwordField = new javax.swing.JPasswordField();
        backButton = new javax.swing.JButton();
        ConfirmButton = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jFormattedTextField1.setText("jFormattedTextField1");

        jScrollPane1.setViewportView(jTextPane1);

        jToggleButton1.setText("jToggleButton1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jFormattedTextField2.setText("jFormattedTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Account Edit Information");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 440, -1));

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Address:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 500, 170, 90));

        username.setBackground(new java.awt.Color(255, 255, 255));
        username.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("Username:");
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 88, 210, 80));

        pass.setBackground(new java.awt.Color(255, 255, 255));
        pass.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        pass.setForeground(new java.awt.Color(255, 255, 255));
        pass.setText("Password:");
        jPanel1.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 168, 180, 60));

        cpass.setBackground(new java.awt.Color(255, 255, 255));
        cpass.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        cpass.setForeground(new java.awt.Color(255, 255, 255));
        cpass.setText("Confirm Password:");
        jPanel1.add(cpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 320, 100));

        Name.setBackground(new java.awt.Color(255, 255, 255));
        Name.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        Name.setForeground(new java.awt.Color(255, 255, 255));
        Name.setText("Name:");
        jPanel1.add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 120, 80));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("BirthDate:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 190, 90));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Phone Number:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 270, 60));
        jPanel1.add(addressField, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 530, 340, 50));
        addressField.getAccessibleContext().setAccessibleDescription("");

        jPanel1.add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 340, 50));
        nameField.getAccessibleContext().setAccessibleDescription("");

        jPanel1.add(birthdateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, 340, 50));
        jPanel1.add(phoneNumField, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 460, 340, 50));
        phoneNumField.getAccessibleContext().setAccessibleDescription("");

        jPanel1.add(confirmPassField, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 340, 50));
        jPanel1.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 340, 50));

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
        jPanel1.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 600, 210, 80));

        ConfirmButton.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        ConfirmButton.setForeground(new java.awt.Color(255, 255, 255));
        ConfirmButton.setText("CONFIRM");
        ConfirmButton.setBorderPainted(false);
        ConfirmButton.setContentAreaFilled(false);
        ConfirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ConfirmButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ConfirmButtonMouseExited(evt);
            }
        });
        ConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ConfirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 600, 210, 80));
        jPanel1.add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 340, 50));
        usernameField.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 1250, 710));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Untitled design.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        AdminMain admin = new AdminMain();
        admin.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void ConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmButtonActionPerformed
        // TODO add your handling code here:
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPassField.getText();
        String name = nameField.getText();
        String birthdate = birthdateField.getText();
        String phoneNumber = phoneNumField.getText();
        String address = addressField.getText();
        
        //Registration Error Handling
        if (username.isEmpty()|| password.isEmpty()|| confirmPassword.isEmpty()|| name.isEmpty()|| birthdate.isEmpty()|| phoneNumber.isEmpty()|| address.isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this,"Please fill all the required information.","Input Error",javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this,"Passwords don't match","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (phoneNumber.matches(".*[a-zA-Z].*")){
            JOptionPane.showMessageDialog(this,"Invalid Phone Number","Error",JOptionPane.ERROR_MESSAGE);
            return;
        } else if (phoneNumber.matches("\\d+")){
            deleteAccount(row);
            accountEditWriteOnFile(username,password,name,birthdate,phoneNumber,address,accNumber,type,card);
            AdminMain admin = new AdminMain();
            admin.setVisible(true);
            setVisible(false);
        } else{
            JOptionPane.showMessageDialog(this,"Error: Phone number must only contain numbers.","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
    }//GEN-LAST:event_ConfirmButtonActionPerformed

    // <editor-fold defaultstate="collapsed" desc="MOUSE HOVER CODE">  
    private void ConfirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfirmButtonMouseEntered
        // TODO add your handling code here:
        ConfirmButton.setContentAreaFilled(true);
        ConfirmButton.setForeground(Color.black);
        ConfirmButton.setBackground(Color.cyan);
    }//GEN-LAST:event_ConfirmButtonMouseEntered

    private void ConfirmButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfirmButtonMouseExited
        // TODO add your handling code here:
        ConfirmButton.setContentAreaFilled(false);
        ConfirmButton.setForeground(Color.white);
        
      
    }//GEN-LAST:event_ConfirmButtonMouseExited

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

    //</editor-fold>
    
    private void accountEditWriteOnFile(String username, String password, String name, String birthdate, String phoneNumber, String address, String accNumber, String type, String card) {
        try (var writer = new BufferedWriter(new FileWriter("Accounts.csv", true))){
            writer.write(username + "," + password + "," + name + "," + birthdate + "," + phoneNumber + "," + address+ "," + accNumber + "," + type + "," + card);
            writer.newLine();

            JOptionPane.showMessageDialog(this, "Account edited successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void deleteAccount(int row){
        String filePath = "Accounts.csv";  // Change to your CSV file path
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
//            java.util.logging.Logger.getLogger(AccountEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AccountEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AccountEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AccountEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AccountEdit().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConfirmButton;
    private javax.swing.JLabel Name;
    private javax.swing.JTextField addressField;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField birthdateField;
    private javax.swing.JPasswordField confirmPassField;
    private javax.swing.JLabel cpass;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel pass;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField phoneNumField;
    private javax.swing.JLabel username;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
