/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package LoginBranch;

import ATMSystemBranch.StartingWindow;
import com.mycompany.bankingmanagementsystem.AdminMain;
import UserBranch.UserInterfaceMain;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Laurence
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
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
    private int balance;
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        LoginButton = new javax.swing.JButton();
        showPassword = new javax.swing.JCheckBox();
        loginBackButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("USERNAME: ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 260, -1, 130));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PASSWORD:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 440, -1, 50));
        getContentPane().add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 310, 260, 40));
        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 450, 250, 40));

        LoginButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LoginButton.setForeground(new java.awt.Color(255, 255, 255));
        LoginButton.setText("LOGIN");
        LoginButton.setBorderPainted(false);
        LoginButton.setContentAreaFilled(false);
        LoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LoginButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LoginButtonMouseExited(evt);
            }
        });
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        getContentPane().add(LoginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 550, 250, 50));

        showPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        showPassword.setForeground(new java.awt.Color(255, 255, 255));
        showPassword.setText("SHOW PASSWORD");
        showPassword.setContentAreaFilled(false);
        showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(showPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 490, 190, 50));

        loginBackButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        loginBackButton.setForeground(new java.awt.Color(255, 255, 255));
        loginBackButton.setText("BACK");
        loginBackButton.setBorderPainted(false);
        loginBackButton.setContentAreaFilled(false);
        loginBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBackButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBackButtonMouseExited(evt);
            }
        });
        loginBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(loginBackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 630, 250, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Log in BG.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 800));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        // TODO add your handling code here:
        String user = usernameField.getText();
        String pass = passwordField.getText();
        loginAccount(user,pass);
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void LoginButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginButtonMouseEntered
        // TODO add your handling code here:
        LoginButton.setContentAreaFilled(true);
        LoginButton.setBackground(Color.cyan);
        LoginButton.setForeground(Color.black);

    }//GEN-LAST:event_LoginButtonMouseEntered

    private void LoginButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginButtonMouseExited
        // TODO add your handling code here:
        LoginButton.setContentAreaFilled(false);
        LoginButton.setForeground(Color.white);
    }//GEN-LAST:event_LoginButtonMouseExited

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordActionPerformed
        // TODO add your handling code here:
        if(showPassword.isSelected()){
            passwordField.setEchoChar((char)0);
        }else{
            passwordField.setEchoChar('*');
        }
    }//GEN-LAST:event_showPasswordActionPerformed

    private void loginBackButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBackButtonMouseEntered
        // TODO add your handling code here:
        loginBackButton.setContentAreaFilled(true);
        loginBackButton.setBackground(Color.cyan);
        loginBackButton.setForeground(Color.black);
    }//GEN-LAST:event_loginBackButtonMouseEntered

    private void loginBackButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBackButtonMouseExited
        // TODO add your handling code here:
        loginBackButton.setContentAreaFilled(false);
        loginBackButton.setForeground(Color.white);
    }//GEN-LAST:event_loginBackButtonMouseExited

    private void loginBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBackButtonActionPerformed
        // TODO add your handling code here:
        StartingWindow start = new StartingWindow();
        start.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_loginBackButtonActionPerformed

    private String loginAccount(String UserName, String Pass){

          try (BufferedReader reader = new BufferedReader(new FileReader("Accounts.csv"))){
              String line;
              boolean accountFound = false;
              while ((line = reader.readLine()) != null){
                  String[] parts = line.split(",");
                  if(parts[0].equals(UserName) && parts[1].equals(Pass)){
                      accountFound = true;
                      if(parts[7].equals("admin")){
                          AdminMain admin = new AdminMain();
                          admin.setVisible(true);
                          setVisible(false);
                      }else if (parts[7].equals("user")){
                          
                          //DITO IRURUN UNG USER VIEW
                          this.username = parts[0];
                          this.password = parts[1];
                          this.name = parts[2];
                          this.birthdate = parts[3];
                          this.phoneNumber = parts[4];
                          this.address = parts[5];
                          this.accNumber = parts[6];
                          this.type = parts[7];
                          this.card = parts[8];
                          this.balance = Integer.parseInt(parts[10]);
                          java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                new UserInterfaceMain(username,password,name,birthdate,phoneNumber,address,accNumber,type,card,balance).setVisible(true);
                            }
                        });
                          setVisible(false);
                      }
                     break;
                    }
                }
              if (!accountFound){
                JOptionPane.showMessageDialog(this, "Invalid Account", "Error", JOptionPane.ERROR_MESSAGE);
              }
          } catch (IOException e) {
              JOptionPane.showMessageDialog(this, "System Error", "Error", JOptionPane.ERROR_MESSAGE);
          }
        return null;
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginBackButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
