/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ATMSystemBranch;

import UserBranch.UserInterface;
import com.mycompany.bankingmanagementsystem.AdminMain;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author yan
 */

public class PinWindow extends javax.swing.JFrame {

    /**
     * Creates new form PinWindow
     */
    private String accNumber = "";
    private String pinNumber = "";
    
    private String getBalance = "";
    private int balance;
    
    public PinWindow() {
        initComponents();
        AccountNumberPanel.setVisible(true);
        PinPanel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AccountNumberPanel = new javax.swing.JPanel();
        ATMSystemLabel = new javax.swing.JLabel();
        accNumberLabel = new javax.swing.JLabel();
        accNumberField = new javax.swing.JTextField();
        accKeypad = new javax.swing.JPanel();
        accKey1 = new javax.swing.JButton();
        accKey2 = new javax.swing.JButton();
        accKey3 = new javax.swing.JButton();
        accKey4 = new javax.swing.JButton();
        accKey5 = new javax.swing.JButton();
        accKey6 = new javax.swing.JButton();
        accKey7 = new javax.swing.JButton();
        accKey8 = new javax.swing.JButton();
        accKey9 = new javax.swing.JButton();
        accKeyX = new javax.swing.JButton();
        accKey0 = new javax.swing.JButton();
        accKeyOK = new javax.swing.JButton();
        backLabel = new javax.swing.JLabel();
        accBackButton = new javax.swing.JButton();
        PinPanel = new javax.swing.JPanel();
        pinPanel = new javax.swing.JPanel();
        pinKey1 = new javax.swing.JButton();
        pinKey2 = new javax.swing.JButton();
        pinKey3 = new javax.swing.JButton();
        pinKey4 = new javax.swing.JButton();
        pinKey5 = new javax.swing.JButton();
        pinKey6 = new javax.swing.JButton();
        pinKey7 = new javax.swing.JButton();
        pinKey8 = new javax.swing.JButton();
        pinKey9 = new javax.swing.JButton();
        pinKeyX = new javax.swing.JButton();
        pinKey0 = new javax.swing.JButton();
        pinKeyOK = new javax.swing.JButton();
        ATMSystemLabel1 = new javax.swing.JLabel();
        accNumberLabel1 = new javax.swing.JLabel();
        backLabel1 = new javax.swing.JLabel();
        pinBackButton = new javax.swing.JButton();
        pinNumberField = new javax.swing.JPasswordField();
        BGLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        AccountNumberPanel.setOpaque(false);
        AccountNumberPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ATMSystemLabel.setFont(new java.awt.Font("Segoe UI", 1, 54)); // NOI18N
        ATMSystemLabel.setForeground(new java.awt.Color(255, 255, 255));
        ATMSystemLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ATMSystemLabel.setText("ATM SYSTEM");
        AccountNumberPanel.add(ATMSystemLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, -1, -1));

        accNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accNumberLabel.setForeground(new java.awt.Color(255, 255, 255));
        accNumberLabel.setText("Enter Account Number");
        AccountNumberPanel.add(accNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 257, -1));

        accNumberField.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        AccountNumberPanel.add(accNumberField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 257, -1));

        accKeypad.setOpaque(false);
        accKeypad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        accKey1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKey1.setText("1");
        accKey1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKey1ActionPerformed(evt);
            }
        });
        accKeypad.add(accKey1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 19, 75, 50));

        accKey2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKey2.setText("2");
        accKey2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKey2ActionPerformed(evt);
            }
        });
        accKeypad.add(accKey2, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 19, 75, 50));

        accKey3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKey3.setText("3");
        accKey3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKey3ActionPerformed(evt);
            }
        });
        accKeypad.add(accKey3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 19, 75, 50));

        accKey4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKey4.setText("4");
        accKey4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKey4ActionPerformed(evt);
            }
        });
        accKeypad.add(accKey4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 81, 75, 50));

        accKey5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKey5.setText("5");
        accKey5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKey5ActionPerformed(evt);
            }
        });
        accKeypad.add(accKey5, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 81, 75, 50));

        accKey6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKey6.setText("6");
        accKey6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKey6ActionPerformed(evt);
            }
        });
        accKeypad.add(accKey6, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 81, 75, 50));

        accKey7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKey7.setText("7");
        accKey7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKey7ActionPerformed(evt);
            }
        });
        accKeypad.add(accKey7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 143, 75, 50));

        accKey8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKey8.setText("8");
        accKey8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKey8ActionPerformed(evt);
            }
        });
        accKeypad.add(accKey8, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 143, 75, 50));

        accKey9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKey9.setText("9");
        accKey9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKey9ActionPerformed(evt);
            }
        });
        accKeypad.add(accKey9, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 143, 75, 50));

        accKeyX.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKeyX.setText("<--");
        accKeyX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKeyXActionPerformed(evt);
            }
        });
        accKeypad.add(accKeyX, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 205, 75, 50));

        accKey0.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKey0.setText("0");
        accKey0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKey0ActionPerformed(evt);
            }
        });
        accKeypad.add(accKey0, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 205, 75, 50));

        accKeyOK.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accKeyOK.setText("OK");
        accKeyOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accKeyOKActionPerformed(evt);
            }
        });
        accKeypad.add(accKeyOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 205, 75, 50));

        AccountNumberPanel.add(accKeypad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 257, 290));

        backLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        backLabel.setForeground(new java.awt.Color(51, 204, 255));
        backLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backLabel.setText("Back");
        backLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        AccountNumberPanel.add(backLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 641, 100, 50));

        accBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accBackButtonActionPerformed(evt);
            }
        });
        AccountNumberPanel.add(accBackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 641, 75, 50));

        PinPanel.setBackground(new java.awt.Color(0, 0, 204));
        PinPanel.setOpaque(false);
        PinPanel.setPreferredSize(new java.awt.Dimension(800, 800));
        PinPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pinPanel.setOpaque(false);
        pinPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pinKey1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKey1.setText("1");
        pinKey1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKey1ActionPerformed(evt);
            }
        });
        pinPanel.add(pinKey1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 19, 75, 50));

        pinKey2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKey2.setText("2");
        pinKey2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKey2ActionPerformed(evt);
            }
        });
        pinPanel.add(pinKey2, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 19, 75, 50));

        pinKey3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKey3.setText("3");
        pinKey3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKey3ActionPerformed(evt);
            }
        });
        pinPanel.add(pinKey3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 19, 75, 50));

        pinKey4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKey4.setText("4");
        pinKey4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKey4ActionPerformed(evt);
            }
        });
        pinPanel.add(pinKey4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 81, 75, 50));

        pinKey5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKey5.setText("5");
        pinKey5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKey5ActionPerformed(evt);
            }
        });
        pinPanel.add(pinKey5, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 81, 75, 50));

        pinKey6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKey6.setText("6");
        pinKey6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKey6ActionPerformed(evt);
            }
        });
        pinPanel.add(pinKey6, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 81, 75, 50));

        pinKey7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKey7.setText("7");
        pinKey7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKey7ActionPerformed(evt);
            }
        });
        pinPanel.add(pinKey7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 143, 75, 50));

        pinKey8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKey8.setText("8");
        pinKey8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKey8ActionPerformed(evt);
            }
        });
        pinPanel.add(pinKey8, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 143, 75, 50));

        pinKey9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKey9.setText("9");
        pinKey9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKey9ActionPerformed(evt);
            }
        });
        pinPanel.add(pinKey9, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 143, 75, 50));

        pinKeyX.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKeyX.setText("<--");
        pinKeyX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKeyXActionPerformed(evt);
            }
        });
        pinPanel.add(pinKeyX, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 205, 75, 50));

        pinKey0.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKey0.setText("0");
        pinKey0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKey0ActionPerformed(evt);
            }
        });
        pinPanel.add(pinKey0, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 205, 75, 50));

        pinKeyOK.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pinKeyOK.setText("OK");
        pinKeyOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinKeyOKActionPerformed(evt);
            }
        });
        pinPanel.add(pinKeyOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 205, 75, 50));

        PinPanel.add(pinPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 257, 290));

        ATMSystemLabel1.setFont(new java.awt.Font("Segoe UI", 1, 54)); // NOI18N
        ATMSystemLabel1.setForeground(new java.awt.Color(255, 255, 255));
        ATMSystemLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ATMSystemLabel1.setText("ATM SYSTEM");
        PinPanel.add(ATMSystemLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, -1, -1));

        accNumberLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        accNumberLabel1.setForeground(new java.awt.Color(255, 255, 255));
        accNumberLabel1.setText("Enter PIN Number");
        PinPanel.add(accNumberLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 257, -1));

        backLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        backLabel1.setForeground(new java.awt.Color(51, 204, 255));
        backLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backLabel1.setText("Back");
        backLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        PinPanel.add(backLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 641, 100, 50));

        pinBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinBackButtonActionPerformed(evt);
            }
        });
        PinPanel.add(pinBackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 641, 75, 50));

        pinNumberField.setPreferredSize(new java.awt.Dimension(64, 38));
        PinPanel.add(pinNumberField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 260, 40));

        BGLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ATM Main.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AccountNumberPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PinPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(BGLabel)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AccountNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PinPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(BGLabel)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="EVENTS"> 
    private void accKey1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKey1ActionPerformed
        // TODO add your handling code here:
        accNumber = accNumber+ "1";
        accNumberField.setText(accNumber);
    }//GEN-LAST:event_accKey1ActionPerformed

    private void accKey2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKey2ActionPerformed
        // TODO add your handling code here:
        accNumber = accNumber+ "2";
        accNumberField.setText(accNumber);
    }//GEN-LAST:event_accKey2ActionPerformed

    private void accKey3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKey3ActionPerformed
        // TODO add your handling code here:
        accNumber = accNumber+ "3";
        accNumberField.setText(accNumber);
    }//GEN-LAST:event_accKey3ActionPerformed

    private void accKey4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKey4ActionPerformed
        // TODO add your handling code here:
        accNumber = accNumber+ "4";
        accNumberField.setText(accNumber);
    }//GEN-LAST:event_accKey4ActionPerformed

    private void accKey5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKey5ActionPerformed
        // TODO add your handling code here:
        accNumber = accNumber+ "5";
        accNumberField.setText(accNumber);
    }//GEN-LAST:event_accKey5ActionPerformed

    private void accKey6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKey6ActionPerformed
        // TODO add your handling code here:
        accNumber = accNumber+ "6";
        accNumberField.setText(accNumber);
    }//GEN-LAST:event_accKey6ActionPerformed

    private void accKey7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKey7ActionPerformed
        // TODO add your handling code here:
        accNumber = accNumber+ "7";
        accNumberField.setText(accNumber);
    }//GEN-LAST:event_accKey7ActionPerformed

    private void accKey8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKey8ActionPerformed
        // TODO add your handling code here:
        accNumber = accNumber+ "8";
        accNumberField.setText(accNumber);
    }//GEN-LAST:event_accKey8ActionPerformed

    private void accKey9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKey9ActionPerformed
        // TODO add your handling code here:
        accNumber = accNumber+ "9";
        accNumberField.setText(accNumber);
    }//GEN-LAST:event_accKey9ActionPerformed

    private void accKey0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKey0ActionPerformed
        // TODO add your handling code here:
        accNumber = accNumber+ "0";
        accNumberField.setText(accNumber);
    }//GEN-LAST:event_accKey0ActionPerformed

    private void accKeyXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKeyXActionPerformed
        // TODO add your handling code here:
        if ("".equals(accNumber)){
            return;
        }else{
            accNumber = accNumber.substring(0, accNumber.length() - 1);
            accNumberField.setText(accNumber);
        }
    }//GEN-LAST:event_accKeyXActionPerformed

    private void accKeyOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accKeyOKActionPerformed
        // TODO add your handling code here:
        AccountNumberPanel.setVisible(false);
        PinPanel.setVisible(true);
        pinNumberField.setText(pinNumber);
    }//GEN-LAST:event_accKeyOKActionPerformed

    private void pinKey1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKey1ActionPerformed
        // TODO add your handling code here:
        pinNumber = pinNumber+ "1";
        pinNumberField.setText(pinNumber);
    }//GEN-LAST:event_pinKey1ActionPerformed

    private void pinKey2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKey2ActionPerformed
        // TODO add your handling code here:
        pinNumber = pinNumber+ "2";
        pinNumberField.setText(pinNumber);
    }//GEN-LAST:event_pinKey2ActionPerformed

    private void pinKey3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKey3ActionPerformed
        // TODO add your handling code here:
        pinNumber = pinNumber+ "3";
        pinNumberField.setText(pinNumber);
    }//GEN-LAST:event_pinKey3ActionPerformed

    private void pinKey4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKey4ActionPerformed
        // TODO add your handling code here:
        pinNumber = pinNumber+ "4";
        pinNumberField.setText(pinNumber);
    }//GEN-LAST:event_pinKey4ActionPerformed

    private void pinKey5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKey5ActionPerformed
        // TODO add your handling code here:
        pinNumber = pinNumber+ "5";
        pinNumberField.setText(pinNumber);
    }//GEN-LAST:event_pinKey5ActionPerformed

    private void pinKey6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKey6ActionPerformed
        // TODO add your handling code here:
        pinNumber = pinNumber+ "6";
        pinNumberField.setText(pinNumber);
    }//GEN-LAST:event_pinKey6ActionPerformed

    private void pinKey7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKey7ActionPerformed
        // TODO add your handling code here:
        pinNumber = pinNumber+ "7";
        pinNumberField.setText(pinNumber);
    }//GEN-LAST:event_pinKey7ActionPerformed

    private void pinKey8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKey8ActionPerformed
        // TODO add your handling code here:
        pinNumber = pinNumber+ "8";
        pinNumberField.setText(pinNumber);
    }//GEN-LAST:event_pinKey8ActionPerformed

    private void pinKey9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKey9ActionPerformed
        // TODO add your handling code here:
        pinNumber = pinNumber+ "9";
        pinNumberField.setText(pinNumber);
    }//GEN-LAST:event_pinKey9ActionPerformed

    private void pinKeyXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKeyXActionPerformed
        // TODO add your handling code here:
        if ("".equals(pinNumber)){
            return;
        }else{
            pinNumber = pinNumber.substring(0, pinNumber.length() - 1);
            pinNumberField.setText(pinNumber);
        }
    }//GEN-LAST:event_pinKeyXActionPerformed

    private void pinKey0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKey0ActionPerformed
        // TODO add your handling code here:
        pinNumber = pinNumber+ "0";
        pinNumberField.setText(pinNumber);
    }//GEN-LAST:event_pinKey0ActionPerformed

    private void pinKeyOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinKeyOKActionPerformed
        // TODO add your handling code here:
        ATMLogin(accNumber,pinNumber);
    }//GEN-LAST:event_pinKeyOKActionPerformed

    private void accBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accBackButtonActionPerformed
        // TODO add your handling code here:
//        AccountNumberPanel.setVisible(false);
//        PinPanel.setVisible(true);
    }//GEN-LAST:event_accBackButtonActionPerformed

    private void pinBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinBackButtonActionPerformed
        // TODO add your handling code here:
        AccountNumberPanel.setVisible(true);
        PinPanel.setVisible(false);
        accNumberField.setText(accNumber);
    }//GEN-LAST:event_pinBackButtonActionPerformed

    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="FUNCTIONS"> 
    private void ATMLogin(String acctNum, String pin){
          try (BufferedReader reader = new BufferedReader(new FileReader("Accounts.csv"))){
              String line;
              boolean accountFound = false;
              while ((line = reader.readLine()) != null){
                  String[] parts = line.split(",");
                  if(parts[6].equals(acctNum) && parts[9].equals(pin)){
                      accountFound = true;
                      String username = parts[0];
                      String password = parts[1];
                      String name = parts[2];
                      String birthdate = parts[3];
                      String phoneNumber = parts[4];
                      String address = parts[5];
//                      String accNumber;
                      String type = parts[7];
                      String card = parts[8];
//                      String pin;
                      getBalance = parts[10];
                      balance = Integer.parseInt(getBalance);
                      java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new ATMWindow(username,password,name,birthdate,phoneNumber,address,acctNum,type,card,pin,balance).setVisible(true);
                        }
                    });
                      setVisible(false);
                     break;
                    }
                }
              if (!accountFound){
                JOptionPane.showMessageDialog(this, "Invalid Account", "Error", JOptionPane.ERROR_MESSAGE);
              }
          } catch (IOException e) {
              JOptionPane.showMessageDialog(this, "System Error", "Error", JOptionPane.ERROR_MESSAGE);
          }
    }
    
    // </editor-fold> 
    
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
            java.util.logging.Logger.getLogger(PinWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PinWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PinWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PinWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PinWindow().setVisible(true);
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="VARIABLES">  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ATMSystemLabel;
    private javax.swing.JLabel ATMSystemLabel1;
    private javax.swing.JPanel AccountNumberPanel;
    private javax.swing.JLabel BGLabel;
    private javax.swing.JPanel PinPanel;
    private javax.swing.JButton accBackButton;
    private javax.swing.JButton accKey0;
    private javax.swing.JButton accKey1;
    private javax.swing.JButton accKey2;
    private javax.swing.JButton accKey3;
    private javax.swing.JButton accKey4;
    private javax.swing.JButton accKey5;
    private javax.swing.JButton accKey6;
    private javax.swing.JButton accKey7;
    private javax.swing.JButton accKey8;
    private javax.swing.JButton accKey9;
    private javax.swing.JButton accKeyOK;
    private javax.swing.JButton accKeyX;
    private javax.swing.JPanel accKeypad;
    private javax.swing.JTextField accNumberField;
    private javax.swing.JLabel accNumberLabel;
    private javax.swing.JLabel accNumberLabel1;
    private javax.swing.JLabel backLabel;
    private javax.swing.JLabel backLabel1;
    private javax.swing.JButton pinBackButton;
    private javax.swing.JButton pinKey0;
    private javax.swing.JButton pinKey1;
    private javax.swing.JButton pinKey2;
    private javax.swing.JButton pinKey3;
    private javax.swing.JButton pinKey4;
    private javax.swing.JButton pinKey5;
    private javax.swing.JButton pinKey6;
    private javax.swing.JButton pinKey7;
    private javax.swing.JButton pinKey8;
    private javax.swing.JButton pinKey9;
    private javax.swing.JButton pinKeyOK;
    private javax.swing.JButton pinKeyX;
    private javax.swing.JPasswordField pinNumberField;
    private javax.swing.JPanel pinPanel;
    // End of variables declaration//GEN-END:variables
//</editor-fold>
}
