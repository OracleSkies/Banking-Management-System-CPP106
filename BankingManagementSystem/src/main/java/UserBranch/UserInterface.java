/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserBranch;

import com.mycompany.bankingmanagementsystem.UserAccountRegistration;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import javax.swing.Timer;
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
//        restrictInputToNumbersOnly(AmountLabel);//To restrict the text field for only number 
//        restrictInputToNumbersOnly2(DepositText);//To restrict the text field for only number 
//        restrictInputToNumbersOnly3(AmountLabel2);//To restrict the text field for only number 
        loadCSV(); // Automatically load the CSV file when the JFrame is created
        String filePath = "Transactions.csv";
        computeMoneyAndUpdateLabels(filePath);
        // Example usage of displayLastRow
        displayLastRow("Transactions.csv", ActionDis, DateDis, Amountdis, DescrDis);
        
        cards.setVisible(false);
        payments.setVisible(false);
        
        BillsScroll.setOpaque(false);
        BillsScroll.getViewport().setOpaque(false);

        Dashboard.setVisible(true);
        TransacHis.setVisible(false);
        AccReg.setVisible(false);
        CheckCardY.setVisible(false);
        CheckCardN.setVisible(false);
        bills.setVisible(false);
        shopping.setVisible(false);
        Application.setVisible(false);
        
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
//            CurrentBal.setText(formattedBalance);
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
        AccReg = new javax.swing.JPanel();
        CheckCardY = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        CheckCardN = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        bills = new javax.swing.JPanel();
        BillsScroll = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jToggleButton12 = new javax.swing.JToggleButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jToggleButton13 = new javax.swing.JToggleButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        PayB = new javax.swing.JButton();
        shopping = new javax.swing.JPanel();
        Application = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        payments = new javax.swing.JPanel();
        Shopping = new javax.swing.JButton();
        Bills = new javax.swing.JButton();
        cards = new javax.swing.JPanel();
        application = new javax.swing.JButton();
        checkcard = new javax.swing.JButton();
        Trans = new javax.swing.JButton();
        Dashb = new javax.swing.JButton();
        Accreg = new javax.swing.JButton();
        Payments = new javax.swing.JToggleButton();
        CARD = new javax.swing.JToggleButton();
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
                    .addComponent(jSeparator3)
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
                .addContainerGap(67, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jSeparator4)
                    .addComponent(AddressDis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BdayDis1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(0, 58, Short.MAX_VALUE))
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
                .addContainerGap(69, Short.MAX_VALUE))
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
                .addGap(6, 6, 6)
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Balance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RecentTrans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(AccDEts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(Hist, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        DashboardLayout.setVerticalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboardLayout.createSequentialGroup()
                        .addComponent(Balance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addComponent(RecentTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AccDEts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Hist, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        CheckCardY.setOpaque(false);
        CheckCardY.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chip.png"))); // NOI18N
        CheckCardY.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 110, 100));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Account Number");
        CheckCardY.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 200, 30));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Name");
        CheckCardY.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, 90, 30));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/QP.png"))); // NOI18N
        CheckCardY.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 140, 93));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Card.png"))); // NOI18N
        CheckCardY.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 89, -1, -1));

        jPanel9.add(CheckCardY, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        CheckCardN.setOpaque(false);
        CheckCardN.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("NO CARD APPLICATION");
        CheckCardN.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 680, 70));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("FOR THIS ACCOUNT");
        CheckCardN.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Card.png"))); // NOI18N
        CheckCardN.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 794, 423));

        jPanel9.add(CheckCardN, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        bills.setOpaque(false);

        BillsScroll.setBorder(null);
        BillsScroll.setOpaque(false);

        jPanel1.setOpaque(false);

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/water.png"))); // NOI18N

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/int.png"))); // NOI18N

        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/insurance.png"))); // NOI18N
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/electric.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Electric Utilities");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText(" ₱ 1, 000");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText(" ₱ 500");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Water Utilities");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText(" ₱ 800");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Telecommunications");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText(" ₱ 100");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Cable/Internet");

        jToggleButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cards.png"))); // NOI18N
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });

        jToggleButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Government.png"))); // NOI18N

        jToggleButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loans.png"))); // NOI18N
        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton7ActionPerformed(evt);
            }
        });

        jToggleButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tele.png"))); // NOI18N
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText(" ₱ 10, 000");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Credit Cards");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText(" ₱ 5, 000");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Loans");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Government");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Insurance");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText(" ₱ 600");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText(" ₱ 15, 000");

        jToggleButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transportation.png"))); // NOI18N
        jToggleButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton9ActionPerformed(evt);
            }
        });

        jToggleButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/RealEstate.png"))); // NOI18N
        jToggleButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton10ActionPerformed(evt);
            }
        });

        jToggleButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Healthcare.png"))); // NOI18N
        jToggleButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton11ActionPerformed(evt);
            }
        });

        jToggleButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Schools.png"))); // NOI18N
        jToggleButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton12ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText(" ₱ 800");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText(" ₱ 25, 000");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText(" ₱ 19, 000");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText(" ₱ 18, 000");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Transportation");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Real Estate");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Healthcare");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Schools");

        jToggleButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PaymentSol.png"))); // NOI18N
        jToggleButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton13ActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText(" ₱ 20, 000");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Payment Solution");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(117, 117, 117))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(174, 174, 174))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(52, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(jLabel15)
                        .addGap(107, 107, 107))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel43)))
                .addGap(184, 184, 184)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(jLabel44))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel42))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel46)))
                .addGap(157, 157, 157))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(jLabel18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jLabel20)))
                        .addGap(196, 196, 196)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel22))
                            .addComponent(jLabel21))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(238, 238, 238)
                                .addComponent(jLabel37)
                                .addGap(225, 225, 225))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23)
                                .addGap(189, 189, 189)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel38)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jToggleButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jToggleButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jToggleButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel47))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel48)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton8)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jToggleButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToggleButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 244, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        BillsScroll.setViewportView(jPanel1);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BILLS");

        PayB.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        PayB.setForeground(new java.awt.Color(255, 255, 255));
        PayB.setText("PAY BILLS");
        PayB.setBorderPainted(false);
        PayB.setContentAreaFilled(false);
        PayB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PayBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PayBMouseExited(evt);
            }
        });
        PayB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout billsLayout = new javax.swing.GroupLayout(bills);
        bills.setLayout(billsLayout);
        billsLayout.setHorizontalGroup(
            billsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BillsScroll)
            .addGroup(billsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PayB, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        billsLayout.setVerticalGroup(
            billsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, billsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(billsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PayB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BillsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.add(bills, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        shopping.setOpaque(false);

        javax.swing.GroupLayout shoppingLayout = new javax.swing.GroupLayout(shopping);
        shopping.setLayout(shoppingLayout);
        shoppingLayout.setHorizontalGroup(
            shoppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1170, Short.MAX_VALUE)
        );
        shoppingLayout.setVerticalGroup(
            shoppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jPanel9.add(shopping, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        Application.setOpaque(false);
        Application.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ApplicationMouseEntered(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("CARD APPLICATION");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Name:");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Account Number:");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Phone Number:");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Confirm PIN Number:");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("PIN Number:");

        jButton1.setBackground(new java.awt.Color(0, 0, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("APPLY");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout ApplicationLayout = new javax.swing.GroupLayout(Application);
        Application.setLayout(ApplicationLayout);
        ApplicationLayout.setHorizontalGroup(
            ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ApplicationLayout.createSequentialGroup()
                .addGroup(ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ApplicationLayout.createSequentialGroup()
                        .addGap(425, 425, 425)
                        .addComponent(jLabel31))
                    .addGroup(ApplicationLayout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addGroup(ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ApplicationLayout.createSequentialGroup()
                                .addGroup(ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel35))
                                .addGroup(ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ApplicationLayout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ApplicationLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(268, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ApplicationLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(466, 466, 466))
        );
        ApplicationLayout.setVerticalGroup(
            ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ApplicationLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel31)
                .addGap(88, 88, 88)
                .addGroup(ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ApplicationLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4))
                .addGap(13, 13, 13)
                .addGroup(ApplicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        jPanel9.add(Application, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 1170, 600));

        jPanel11.setOpaque(false);
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        payments.setBackground(new java.awt.Color(5, 22, 80));
        payments.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Shopping.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Shopping.setForeground(new java.awt.Color(255, 255, 255));
        Shopping.setText("SHOPPING");
        Shopping.setBorderPainted(false);
        Shopping.setContentAreaFilled(false);
        Shopping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ShoppingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ShoppingMouseExited(evt);
            }
        });
        Shopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShoppingActionPerformed(evt);
            }
        });
        payments.add(Shopping, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 74, 278, 75));

        Bills.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Bills.setForeground(new java.awt.Color(255, 255, 255));
        Bills.setText("BILLS");
        Bills.setBorderPainted(false);
        Bills.setContentAreaFilled(false);
        Bills.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BillsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BillsMouseExited(evt);
            }
        });
        Bills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BillsActionPerformed(evt);
            }
        });
        payments.add(Bills, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 278, 75));

        jPanel11.add(payments, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        cards.setBackground(new java.awt.Color(5, 22, 80));
        cards.setForeground(new java.awt.Color(255, 255, 255));
        cards.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        application.setBackground(new java.awt.Color(0, 0, 204));
        application.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        application.setForeground(new java.awt.Color(255, 255, 255));
        application.setText("APPLICATION");
        application.setBorderPainted(false);
        application.setContentAreaFilled(false);
        application.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                applicationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                applicationMouseExited(evt);
            }
        });
        application.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applicationActionPerformed(evt);
            }
        });
        cards.add(application, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 278, 75));

        checkcard.setBackground(new java.awt.Color(0, 0, 204));
        checkcard.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        checkcard.setForeground(new java.awt.Color(255, 255, 255));
        checkcard.setText("CHECK CARD");
        checkcard.setBorderPainted(false);
        checkcard.setContentAreaFilled(false);
        checkcard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkcardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkcardMouseExited(evt);
            }
        });
        checkcard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkcardActionPerformed(evt);
            }
        });
        cards.add(checkcard, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 75, 278, 75));

        jPanel11.add(cards, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

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
        jPanel11.add(Trans, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 80, 305, 74));

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
        jPanel11.add(Dashb, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 305, 74));

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
        jPanel11.add(Accreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 320, -1, 74));

        Payments.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Payments.setForeground(new java.awt.Color(255, 255, 255));
        Payments.setText("PAYMENTS    >");
        Payments.setContentAreaFilled(false);
        Payments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PaymentsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PaymentsMouseExited(evt);
            }
        });
        Payments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentsActionPerformed(evt);
            }
        });
        jPanel11.add(Payments, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 240, 305, 74));

        CARD.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        CARD.setForeground(new java.awt.Color(255, 255, 255));
        CARD.setText("CARD       >");
        CARD.setContentAreaFilled(false);
        CARD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CARDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CARDMouseExited(evt);
            }
        });
        CARD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CARDActionPerformed(evt);
            }
        });
        jPanel11.add(CARD, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 160, 305, 74));

        getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 310, 610));
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
        String filePath = "Transactions.csv";  // Update this to your actual file path

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
        AccReg.setVisible(false);
        CheckCardY.setVisible(false);
        CheckCardN.setVisible(false);
        bills.setVisible(false);
        shopping.setVisible(false);
        Application.setVisible(false);
    }//GEN-LAST:event_DashbActionPerformed

    private void TransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransActionPerformed
        // TODO add your handling code here:
        Dashboard.setVisible(false);
        TransacHis.setVisible(true);
        AccReg.setVisible(false);
        CheckCardY.setVisible(false);
        CheckCardN.setVisible(false);
        bills.setVisible(false);
        shopping.setVisible(false);
        Application.setVisible(false);
    }//GEN-LAST:event_TransActionPerformed

    private void AccregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccregActionPerformed
        // TODO add your handling code here:
//        Dashboard.setVisible(false);
//        TransacHis.setVisible(false);
//        Deposit.setVisible(false);
//        Withdraw.setVisible(false);
//        TransCash.setVisible(false);
//        AccReg.setVisible(true);
        
        UserAccountRegistration register = new UserAccountRegistration();
        register.setVisible(true);
    }//GEN-LAST:event_AccregActionPerformed
    
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
            File file = new File("Transactions.csv");
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

 
        
    // Method to fetch the current balance (this should be connected to your application's logic)
//    private double getCurrentBalance() {
//        // Example logic, replace this with your actual implementation
////        String balanceText = CurrentBal.getText().replace("$", ""); // Assuming CurrentBal holds the balance in "$123.45" format
////        return Double.parseDouble(balanceText);
//    }

    // Method to update the balance (e.g., display new balance in the UI)
    private void updateBalance(double newBalance) {
        DecimalFormat df = new DecimalFormat("#.00");
//        CurrentBal.setText("$" + df.format(newBalance));
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
    
    private void NotifsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotifsActionPerformed
        
    }//GEN-LAST:event_NotifsActionPerformed
    
        
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
    
    private void applicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applicationActionPerformed
        // TODO add your handling code here:
        Application.setVisible(true);
        Dashboard.setVisible(false);
        TransacHis.setVisible(false);
        AccReg.setVisible(false);
        CheckCardY.setVisible(false);
        CheckCardN.setVisible(false);
        bills.setVisible(false);
        shopping.setVisible(false);   
    }//GEN-LAST:event_applicationActionPerformed

    private void checkcardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkcardActionPerformed
        // TODO add your handling code here:
        Application.setVisible(false);
        Dashboard.setVisible(false);
        TransacHis.setVisible(false);
        AccReg.setVisible(false);
        CheckCardY.setVisible(false);
        CheckCardN.setVisible(true);
        bills.setVisible(false);
        shopping.setVisible(false);   
    }//GEN-LAST:event_checkcardActionPerformed

    private void CARDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CARDActionPerformed
        // TODO add your handling code here:
        if (CARD.isSelected()){
            cards.setVisible(true);
            CARD.setText("CARD       v");
        } else {
            cards.setVisible(false);
            CARD.setText("CARD       >");
        }
        
    }//GEN-LAST:event_CARDActionPerformed

    private void CARDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CARDMouseEntered
        // TODO add your handling code here:
        CARD.setContentAreaFilled(true);
        CARD.setBackground(Color.cyan);
        CARD.setForeground(Color.black);
    }//GEN-LAST:event_CARDMouseEntered

    private void CARDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CARDMouseExited
        // TODO add your handling code here:
        CARD.setContentAreaFilled(false);
        CARD.setForeground(Color.white);
    }//GEN-LAST:event_CARDMouseExited

    private void PaymentsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaymentsMouseEntered
        // TODO add your handling code here:
        Payments.setContentAreaFilled(true);
        Payments.setBackground(Color.cyan);
        Payments.setForeground(Color.black);
    }//GEN-LAST:event_PaymentsMouseEntered

    private void PaymentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaymentsMouseExited
        // TODO add your handling code here:
        Payments.setContentAreaFilled(false);
        Payments.setForeground(Color.white);
    }//GEN-LAST:event_PaymentsMouseExited

    private void PaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentsActionPerformed
        // TODO add your handling code here:
        if (Payments.isSelected()){
            payments.setVisible(true);
            Payments.setText("PAYMENTS    v");
        } else {
            payments.setVisible(false);
            Payments.setText("PAYMENTS    >");
        
        }
         
    }//GEN-LAST:event_PaymentsActionPerformed

    private void ShoppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShoppingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShoppingActionPerformed

    private void BillsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillsActionPerformed
        // TODO add your handling code here:
        Application.setVisible(false);
        Dashboard.setVisible(false);
        TransacHis.setVisible(false);
        AccReg.setVisible(false);
        CheckCardY.setVisible(false);
        CheckCardN.setVisible(false);
        bills.setVisible(true);
        shopping.setVisible(false);   
    }//GEN-LAST:event_BillsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        jButton1.setContentAreaFilled(true);
        jButton1.setBackground(Color.cyan);
        jButton1.setForeground(Color.black);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        // TODO add your handling code here:
        jButton1.setContentAreaFilled(false);
        jButton1.setForeground(Color.white);
    }//GEN-LAST:event_jButton1MouseExited

    private void ApplicationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ApplicationMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ApplicationMouseEntered

    private void applicationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applicationMouseEntered
        // TODO add your handling code here:
        application.setContentAreaFilled(true);
        application.setBackground(Color.cyan);
        application.setForeground(Color.black);
    }//GEN-LAST:event_applicationMouseEntered

    private void applicationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applicationMouseExited
        // TODO add your handling code here:
        application.setContentAreaFilled(false);
        application.setForeground(Color.white);
    }//GEN-LAST:event_applicationMouseExited

    private void checkcardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkcardMouseEntered
        // TODO add your handling code here:
        checkcard.setContentAreaFilled(true);
        checkcard.setBackground(Color.cyan);
        checkcard.setForeground(Color.black);
    }//GEN-LAST:event_checkcardMouseEntered

    private void checkcardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkcardMouseExited
        // TODO add your handling code here:
        checkcard.setContentAreaFilled(false);
        checkcard.setForeground(Color.white);
    }//GEN-LAST:event_checkcardMouseExited

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jToggleButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton8ActionPerformed

    private void jToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton7ActionPerformed

    private void jToggleButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton9ActionPerformed

    private void jToggleButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton10ActionPerformed

    private void jToggleButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton11ActionPerformed

    private void jToggleButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton12ActionPerformed

    private void jToggleButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton13ActionPerformed

    private void PayBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PayBMouseEntered
        // TODO add your handling code here:
        PayB.setContentAreaFilled(true);
        PayB.setBackground(Color.cyan);
        PayB.setForeground(Color.black);
    }//GEN-LAST:event_PayBMouseEntered

    private void PayBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PayBMouseExited
        // TODO add your handling code here:
        PayB.setContentAreaFilled(false);
        PayB.setForeground(Color.white);
    }//GEN-LAST:event_PayBMouseExited

    private void BillsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillsMouseEntered
        // TODO add your handling code here:
        Bills.setContentAreaFilled(true);
        Bills.setBackground(Color.cyan);
        Bills.setForeground(Color.black);
    }//GEN-LAST:event_BillsMouseEntered

    private void BillsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillsMouseExited
        // TODO add your handling code here:
        Bills.setContentAreaFilled(false);
        Bills.setForeground(Color.white);
    }//GEN-LAST:event_BillsMouseExited

    private void ShoppingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShoppingMouseEntered
        // TODO add your handling code here:
        Shopping.setContentAreaFilled(true);
        Shopping.setBackground(Color.cyan);
        Shopping.setForeground(Color.black);
    }//GEN-LAST:event_ShoppingMouseEntered

    private void ShoppingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShoppingMouseExited
        // TODO add your handling code here:
        Shopping.setContentAreaFilled(false);
        Shopping.setForeground(Color.white);
    }//GEN-LAST:event_ShoppingMouseExited

    private void PayBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PayBActionPerformed
    
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
    private javax.swing.JButton Accreg;
    private javax.swing.JLabel ActionDis;
    private javax.swing.JLabel ActionLabel1;
    private javax.swing.JLabel AddressDis;
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JLabel AmountLabel1;
    private javax.swing.JLabel AmountLabel3;
    private javax.swing.JLabel Amountdis;
    private javax.swing.JPanel Application;
    private javax.swing.JLabel BalDis;
    private javax.swing.JPanel Balance;
    private javax.swing.JLabel BalanceLabel;
    private javax.swing.JLabel BankAccNumLabel;
    private javax.swing.JLabel BdayDis1;
    private javax.swing.JLabel BdayLabel;
    private javax.swing.JButton Bills;
    private javax.swing.JScrollPane BillsScroll;
    private javax.swing.JToggleButton CARD;
    private javax.swing.JPanel CheckCardN;
    private javax.swing.JPanel CheckCardY;
    private javax.swing.JTable DashHis;
    private javax.swing.JButton Dashb;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JLabel DateDis;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JLabel DescrDis;
    private javax.swing.JPanel Hist;
    private javax.swing.JLabel NameDis;
    private javax.swing.JLabel NameLabel1;
    private javax.swing.JButton Notifs;
    private javax.swing.JButton PayB;
    private javax.swing.JToggleButton Payments;
    private javax.swing.JLabel PhoneNumDis;
    private javax.swing.JLabel PhoneNumDis1;
    private javax.swing.JLabel PhoneNumLabel;
    private javax.swing.JButton Profile;
    private javax.swing.JPanel RecentTrans;
    private javax.swing.JButton Shopping;
    private javax.swing.JButton Trans;
    private javax.swing.JPanel TransacHis;
    private javax.swing.JTable Transhis;
    private javax.swing.JButton application;
    private javax.swing.JPanel bills;
    private javax.swing.JPanel cards;
    private javax.swing.JButton checkcard;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JPanel payments;
    private javax.swing.JPanel shopping;
    // End of variables declaration//GEN-END:variables
}
