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
           
        FoodScroll.setOpaque(false);
        FoodScroll.getViewport().setOpaque(false);
        
        ClothingsScroll.setOpaque(false);
        ClothingsScroll.getViewport().setOpaque(false);
        
        AppliancesScroll.setOpaque(false);
        AppliancesScroll.getViewport().setOpaque(false);
        
        shoppingTab.setOpaque(false);
        shoppingTab.setBackground(new java.awt.Color(204, 204, 204, 80));
        shoppingTab.setForeground(Color.white);
        
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

        jToggleButton14 = new javax.swing.JToggleButton();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jToggleButton15 = new javax.swing.JToggleButton();
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
        GenRep = new javax.swing.JButton();
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
        shoppingTab = new javax.swing.JTabbedPane();
        FoodScroll = new javax.swing.JScrollPane();
        Foods = new javax.swing.JPanel();
        jToggleButton19 = new javax.swing.JToggleButton();
        jToggleButton22 = new javax.swing.JToggleButton();
        jToggleButton23 = new javax.swing.JToggleButton();
        jToggleButton24 = new javax.swing.JToggleButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jToggleButton25 = new javax.swing.JToggleButton();
        jToggleButton26 = new javax.swing.JToggleButton();
        jToggleButton27 = new javax.swing.JToggleButton();
        jToggleButton28 = new javax.swing.JToggleButton();
        jToggleButton29 = new javax.swing.JToggleButton();
        jToggleButton30 = new javax.swing.JToggleButton();
        jToggleButton31 = new javax.swing.JToggleButton();
        jToggleButton32 = new javax.swing.JToggleButton();
        jLabel75 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        ClothingsScroll = new javax.swing.JScrollPane();
        Clothings = new javax.swing.JPanel();
        jToggleButton69 = new javax.swing.JToggleButton();
        jToggleButton70 = new javax.swing.JToggleButton();
        jToggleButton71 = new javax.swing.JToggleButton();
        jToggleButton72 = new javax.swing.JToggleButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jToggleButton73 = new javax.swing.JToggleButton();
        jToggleButton74 = new javax.swing.JToggleButton();
        jToggleButton75 = new javax.swing.JToggleButton();
        jToggleButton76 = new javax.swing.JToggleButton();
        jToggleButton77 = new javax.swing.JToggleButton();
        jToggleButton78 = new javax.swing.JToggleButton();
        jToggleButton79 = new javax.swing.JToggleButton();
        jToggleButton80 = new javax.swing.JToggleButton();
        jLabel193 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel196 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jLabel200 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        jLabel202 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jLabel203 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        jLabel207 = new javax.swing.JLabel();
        jLabel208 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jLabel212 = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        jLabel214 = new javax.swing.JLabel();
        jLabel215 = new javax.swing.JLabel();
        jLabel216 = new javax.swing.JLabel();
        jLabel217 = new javax.swing.JLabel();
        jLabel218 = new javax.swing.JLabel();
        jLabel219 = new javax.swing.JLabel();
        jLabel220 = new javax.swing.JLabel();
        jLabel221 = new javax.swing.JLabel();
        jLabel222 = new javax.swing.JLabel();
        jLabel223 = new javax.swing.JLabel();
        jLabel224 = new javax.swing.JLabel();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel225 = new javax.swing.JLabel();
        jLabel226 = new javax.swing.JLabel();
        jLabel227 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        jLabel229 = new javax.swing.JLabel();
        jLabel230 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel231 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel232 = new javax.swing.JLabel();
        jLabel233 = new javax.swing.JLabel();
        jLabel234 = new javax.swing.JLabel();
        jLabel235 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel236 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        AppliancesScroll = new javax.swing.JScrollPane();
        Appliances = new javax.swing.JPanel();
        jToggleButton33 = new javax.swing.JToggleButton();
        jToggleButton58 = new javax.swing.JToggleButton();
        jToggleButton59 = new javax.swing.JToggleButton();
        jToggleButton60 = new javax.swing.JToggleButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jToggleButton61 = new javax.swing.JToggleButton();
        jToggleButton62 = new javax.swing.JToggleButton();
        jToggleButton63 = new javax.swing.JToggleButton();
        jToggleButton64 = new javax.swing.JToggleButton();
        jToggleButton65 = new javax.swing.JToggleButton();
        jToggleButton66 = new javax.swing.JToggleButton();
        jToggleButton67 = new javax.swing.JToggleButton();
        jToggleButton68 = new javax.swing.JToggleButton();
        jLabel160 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel167 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel170 = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
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

        jToggleButton14.setText("jToggleButton14");

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jToggleButton15.setText("jToggleButton15");

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

        GenRep.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        GenRep.setText("Genrate Report");
        GenRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenRepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TransacHisLayout = new javax.swing.GroupLayout(TransacHis);
        TransacHis.setLayout(TransacHisLayout);
        TransacHisLayout.setHorizontalGroup(
            TransacHisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransacHisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransacHisLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GenRep)
                .addGap(504, 504, 504))
        );
        TransacHisLayout.setVerticalGroup(
            TransacHisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransacHisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GenRep, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
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
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        shopping.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        shopping.setOpaque(false);

        shoppingTab.setBackground(new java.awt.Color(255, 255, 255));
        shoppingTab.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        shoppingTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        FoodScroll.setBorder(null);
        FoodScroll.setDoubleBuffered(true);
        FoodScroll.setOpaque(false);

        Foods.setOpaque(false);
        Foods.setPreferredSize(new java.awt.Dimension(1170, 873));
        Foods.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/c2.jpg"))); // NOI18N
        Foods.add(jToggleButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 269, 223));

        jToggleButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chocolitos.jpg"))); // NOI18N
        Foods.add(jToggleButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 269, 223));

        jToggleButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/milo.jpg"))); // NOI18N
        jToggleButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton23ActionPerformed(evt);
            }
        });
        Foods.add(jToggleButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 269, 220));

        jToggleButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Noodles.jpg"))); // NOI18N
        jToggleButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton24ActionPerformed(evt);
            }
        });
        Foods.add(jToggleButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 269, 223));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel51.setText("Pancit Canton pack");
        Foods.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 230, -1, 28));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel52.setText(" ₱ 65");
        Foods.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 240, 60, 40));

        jToggleButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ligosardines.jpg"))); // NOI18N
        jToggleButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton25ActionPerformed(evt);
            }
        });
        Foods.add(jToggleButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 269, 213));

        jToggleButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/luncheon meat.jpg"))); // NOI18N
        Foods.add(jToggleButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, 269, 213));

        jToggleButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gummy.jpg"))); // NOI18N
        jToggleButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton27ActionPerformed(evt);
            }
        });
        Foods.add(jToggleButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 269, 213));

        jToggleButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pancitchili.jpg"))); // NOI18N
        jToggleButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton28ActionPerformed(evt);
            }
        });
        Foods.add(jToggleButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 269, 220));

        jToggleButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/century tuna.jpg"))); // NOI18N
        jToggleButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton29ActionPerformed(evt);
            }
        });
        Foods.add(jToggleButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 269, 230));

        jToggleButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/presto.jpg"))); // NOI18N
        jToggleButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton30ActionPerformed(evt);
            }
        });
        Foods.add(jToggleButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 580, 269, 230));

        jToggleButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ramen.jpg"))); // NOI18N
        jToggleButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton31ActionPerformed(evt);
            }
        });
        Foods.add(jToggleButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 580, 269, 230));

        jToggleButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/meatloaf.jpg"))); // NOI18N
        jToggleButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton32ActionPerformed(evt);
            }
        });
        Foods.add(jToggleButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 580, 269, 230));

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel75.setText("Quantity:");
        Foods.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 260, -1, 28));
        Foods.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 260, 50, 28));

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel76.setText(" ₱ 27");
        Foods.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 60, 40));

        jLabel129.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel129.setText("Gummy");
        Foods.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, -1, 28));
        Foods.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 540, 50, 28));

        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel131.setText(" ₱ 80");
        Foods.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 60, 40));

        jLabel132.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel132.setText("C2 Baon Pack x6");
        Foods.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, 28));

        jLabel133.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel133.setText("Quantity:");
        Foods.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, -1, 28));
        Foods.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 50, 28));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel53.setText(" ₱ 45");
        Foods.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 60, 40));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel54.setText("Chocolitos");
        Foods.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, -1, 28));

        jLabel134.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel134.setText("Quantity:");
        Foods.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 260, -1, 28));
        Foods.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 50, 28));

        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel135.setText(" ₱ 50");
        Foods.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 60, 40));

        jLabel136.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel136.setText("Noodles 6/Packs");
        Foods.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, -1, 28));

        jLabel137.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel137.setText("Quantity:");
        Foods.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, 28));
        Foods.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 50, 28));

        jLabel138.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel138.setText("Milo Pack");
        Foods.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 520, -1, 28));

        jLabel139.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel139.setText("Luncheon Meat");
        Foods.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 510, -1, 28));

        jLabel140.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel140.setText("Century Tuna x3");
        Foods.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 810, -1, 28));

        jLabel141.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel141.setText("Presto 10/Pack");
        Foods.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 810, -1, 28));

        jLabel142.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel142.setText("Nissin Ramen x5");
        Foods.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 810, -1, 28));

        jLabel143.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel143.setText("Meatloaf x3");
        Foods.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 810, -1, 28));

        jLabel144.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel144.setText("Ligo Sardines x3");
        Foods.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, -1, 28));

        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel145.setText(" ₱ 115");
        Foods.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 530, -1, 40));

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel146.setText(" ₱ 106");
        Foods.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 520, 80, 40));

        jLabel147.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel147.setText(" ₱ 95");
        Foods.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 810, 60, 40));

        jLabel148.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel148.setText(" ₱ 50");
        Foods.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 810, 60, 40));

        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel149.setText(" ₱ 40");
        Foods.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 810, 60, 40));

        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel150.setText(" ₱ 82");
        Foods.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 810, 60, 40));

        jLabel151.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel151.setText(" ₱ 80");
        Foods.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 60, 40));

        jLabel152.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel152.setText("Quantity:");
        Foods.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 550, -1, 28));

        jLabel153.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel153.setText("Quantity:");
        Foods.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 540, -1, 28));

        jLabel154.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel154.setText("Quantity:");
        Foods.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 840, -1, 28));

        jLabel155.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel155.setText("Quantity:");
        Foods.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 840, -1, 28));

        jLabel156.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel156.setText("Quantity:");
        Foods.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 840, -1, 28));

        jLabel157.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel157.setText("Quantity:");
        Foods.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 840, -1, 28));

        jLabel158.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel158.setText("Quantity:");
        Foods.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, -1, 28));

        jLabel159.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel159.setText("Quantity:");
        Foods.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 540, -1, 28));
        Foods.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 550, 50, 28));
        Foods.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 540, 50, 28));
        Foods.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 840, 50, 28));
        Foods.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 840, 50, 28));
        Foods.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 840, 50, 28));
        Foods.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 840, 50, 28));
        Foods.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 50, 28));

        FoodScroll.setViewportView(Foods);

        shoppingTab.addTab("Foods", FoodScroll);

        ClothingsScroll.setBorder(null);
        ClothingsScroll.setOpaque(false);

        Clothings.setOpaque(false);
        Clothings.setPreferredSize(new java.awt.Dimension(1170, 873));
        Clothings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButton69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1t1.jpg"))); // NOI18N
        Clothings.add(jToggleButton69, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 269, 223));

        jToggleButton70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bloods.jpg"))); // NOI18N
        Clothings.add(jToggleButton70, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 269, 223));

        jToggleButton71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Make money.jpg"))); // NOI18N
        jToggleButton71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton71ActionPerformed(evt);
            }
        });
        Clothings.add(jToggleButton71, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 269, 220));

        jToggleButton72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Blouse.jpg"))); // NOI18N
        jToggleButton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton72ActionPerformed(evt);
            }
        });
        Clothings.add(jToggleButton72, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 269, 223));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel59.setText("Sleeveless Dress");
        Clothings.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 230, -1, 28));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel60.setText(" ₱ 225");
        Clothings.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 240, 80, 40));

        jToggleButton73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Trendy Tshirt.jpg"))); // NOI18N
        jToggleButton73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton73ActionPerformed(evt);
            }
        });
        Clothings.add(jToggleButton73, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 269, 213));

        jToggleButton74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3in1 Suit.jpg"))); // NOI18N
        jToggleButton74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton74ActionPerformed(evt);
            }
        });
        Clothings.add(jToggleButton74, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, 269, 213));

        jToggleButton75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/waffle shorts.jpg"))); // NOI18N
        jToggleButton75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton75ActionPerformed(evt);
            }
        });
        Clothings.add(jToggleButton75, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 269, 213));

        jToggleButton76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sleevelessdress.jpg"))); // NOI18N
        jToggleButton76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton76ActionPerformed(evt);
            }
        });
        Clothings.add(jToggleButton76, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 269, 220));

        jToggleButton77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DoubleLiningDress.jpg"))); // NOI18N
        jToggleButton77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton77ActionPerformed(evt);
            }
        });
        Clothings.add(jToggleButton77, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 269, 230));

        jToggleButton78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bossing.jpg"))); // NOI18N
        jToggleButton78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton78ActionPerformed(evt);
            }
        });
        Clothings.add(jToggleButton78, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 580, 269, 230));

        jToggleButton79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ThugLife.jpg"))); // NOI18N
        jToggleButton79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton79ActionPerformed(evt);
            }
        });
        Clothings.add(jToggleButton79, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 580, 269, 230));

        jToggleButton80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/butterfly dress.jpg"))); // NOI18N
        jToggleButton80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton80ActionPerformed(evt);
            }
        });
        Clothings.add(jToggleButton80, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 580, 269, 230));

        jLabel193.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel193.setText("Quantity:");
        Clothings.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 260, -1, 28));
        Clothings.add(jTextField30, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 260, 50, 28));

        jLabel194.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel194.setText(" ₱ 49");
        Clothings.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 60, 40));

        jLabel195.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel195.setText("Waffle Shorts");
        Clothings.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, -1, 28));
        Clothings.add(jTextField31, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 540, 50, 28));

        jLabel196.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel196.setText(" ₱ 75");
        Clothings.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 60, 40));

        jLabel197.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel197.setText("B1 T1 Tshirt");
        Clothings.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, 28));

        jLabel198.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel198.setText("Quantity:");
        Clothings.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, -1, 28));
        Clothings.add(jTextField32, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 50, 28));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel61.setText(" ₱ 87");
        Clothings.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 60, 40));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel62.setText("Bloods Tshirt");
        Clothings.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, -1, 28));

        jLabel199.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel199.setText("Quantity:");
        Clothings.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 260, -1, 28));
        Clothings.add(jTextField33, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 50, 28));

        jLabel200.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel200.setText(" ₱ 167");
        Clothings.add(jLabel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 80, 40));

        jLabel201.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel201.setText("Blouse");
        Clothings.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, -1, 28));

        jLabel202.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel202.setText("Quantity:");
        Clothings.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, 28));
        Clothings.add(jTextField34, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 50, 28));

        jLabel203.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel203.setText("Money Tshirt");
        Clothings.add(jLabel203, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 520, -1, 28));

        jLabel204.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel204.setText("3in1 Suit");
        Clothings.add(jLabel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 510, -1, 28));

        jLabel205.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel205.setText("Double Lining Dress");
        Clothings.add(jLabel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 810, -1, 28));

        jLabel206.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel206.setText("Bossing Tshirt");
        Clothings.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 810, -1, 28));

        jLabel207.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel207.setText("Thug Life Tshirt");
        Clothings.add(jLabel207, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 810, -1, 28));

        jLabel208.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel208.setText("Butterfly Dress");
        Clothings.add(jLabel208, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 810, -1, 28));

        jLabel209.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel209.setText("Trendy Tshirt");
        Clothings.add(jLabel209, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, -1, 28));

        jLabel210.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel210.setText(" ₱ 115");
        Clothings.add(jLabel210, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 530, -1, 40));

        jLabel211.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel211.setText(" ₱ 195");
        Clothings.add(jLabel211, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 520, 80, 40));

        jLabel212.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel212.setText(" ₱ 92");
        Clothings.add(jLabel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 810, 60, 40));

        jLabel213.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel213.setText(" ₱ 115");
        Clothings.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 810, 80, 40));

        jLabel214.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel214.setText(" ₱ 156");
        Clothings.add(jLabel214, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 810, 80, 40));

        jLabel215.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel215.setText(" ₱ 99");
        Clothings.add(jLabel215, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 810, 60, 40));

        jLabel216.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel216.setText(" ₱ 54");
        Clothings.add(jLabel216, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 60, 40));

        jLabel217.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel217.setText("Quantity:");
        Clothings.add(jLabel217, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 550, -1, 28));

        jLabel218.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel218.setText("Quantity:");
        Clothings.add(jLabel218, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 540, -1, 28));

        jLabel219.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel219.setText("Quantity:");
        Clothings.add(jLabel219, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 840, -1, 28));

        jLabel220.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel220.setText("Quantity:");
        Clothings.add(jLabel220, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 840, -1, 28));

        jLabel221.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel221.setText("Quantity:");
        Clothings.add(jLabel221, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 840, -1, 28));

        jLabel222.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel222.setText("Quantity:");
        Clothings.add(jLabel222, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 840, -1, 28));

        jLabel223.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel223.setText("Quantity:");
        Clothings.add(jLabel223, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, -1, 28));

        jLabel224.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel224.setText("Quantity:");
        Clothings.add(jLabel224, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 540, -1, 28));
        Clothings.add(jTextField35, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 550, 50, 28));
        Clothings.add(jTextField36, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 540, 50, 28));
        Clothings.add(jTextField37, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 840, 50, 28));
        Clothings.add(jTextField38, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 840, 50, 28));
        Clothings.add(jTextField39, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 840, 50, 28));
        Clothings.add(jTextField40, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 840, 50, 28));
        Clothings.add(jTextField41, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 50, 28));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel63.setText(" ₱ 225");
        Clothings.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 240, 80, 40));

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel64.setText("Sleeveless Dress");
        Clothings.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 230, -1, 28));

        jLabel225.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel225.setText("Waffle Shorts");
        Clothings.add(jLabel225, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, -1, 28));

        jLabel226.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel226.setText("Quantity:");
        Clothings.add(jLabel226, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 260, -1, 28));

        jLabel227.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel227.setText(" ₱ 49");
        Clothings.add(jLabel227, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 60, 40));

        jLabel228.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel228.setText("B1 T1 Tshirt");
        Clothings.add(jLabel228, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, 28));

        jLabel229.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel229.setText(" ₱ 75");
        Clothings.add(jLabel229, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 60, 40));

        jLabel230.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel230.setText("Quantity:");
        Clothings.add(jLabel230, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, -1, 28));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel65.setText(" ₱ 87");
        Clothings.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 60, 40));

        jLabel231.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel231.setText(" ₱ 75");
        Clothings.add(jLabel231, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 60, 40));

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel66.setText("Sleeveless Dress");
        Clothings.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 230, -1, 28));

        jLabel232.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel232.setText("Quantity:");
        Clothings.add(jLabel232, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 260, -1, 28));

        jLabel233.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel233.setText("Waffle Shorts");
        Clothings.add(jLabel233, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, -1, 28));

        jLabel234.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel234.setText("B1 T1 Tshirt");
        Clothings.add(jLabel234, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, 28));

        jLabel235.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel235.setText(" ₱ 49");
        Clothings.add(jLabel235, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 60, 40));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel67.setText(" ₱ 225");
        Clothings.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 240, 80, 40));

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel68.setText(" ₱ 87");
        Clothings.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 60, 40));

        jLabel236.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel236.setText("Quantity:");
        Clothings.add(jLabel236, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, -1, 28));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel69.setText("Bloods Tshirt");
        Clothings.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, -1, 28));

        ClothingsScroll.setViewportView(Clothings);

        shoppingTab.addTab("Clothing", ClothingsScroll);

        AppliancesScroll.setBorder(null);
        AppliancesScroll.setOpaque(false);

        Appliances.setOpaque(false);
        Appliances.setPreferredSize(new java.awt.Dimension(1170, 873));
        Appliances.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1t1 Standfan.jpg"))); // NOI18N
        Appliances.add(jToggleButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 269, 223));

        jToggleButton58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MeatShredder.jpg"))); // NOI18N
        Appliances.add(jToggleButton58, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 269, 223));

        jToggleButton59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/StainlessSteamer.jpg"))); // NOI18N
        jToggleButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton59ActionPerformed(evt);
            }
        });
        Appliances.add(jToggleButton59, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 269, 213));

        jToggleButton60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Mini Water dispenser.jpg"))); // NOI18N
        jToggleButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton60ActionPerformed(evt);
            }
        });
        Appliances.add(jToggleButton60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 269, 223));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel55.setText("Electric Kettle");
        Appliances.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 230, -1, 28));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel56.setText(" ₱ 157");
        Appliances.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 240, 80, 40));

        jToggleButton61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Clipfan.jpg"))); // NOI18N
        jToggleButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton61ActionPerformed(evt);
            }
        });
        Appliances.add(jToggleButton61, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 269, 213));

        jToggleButton62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Whisks Hand Mixer.jpg"))); // NOI18N
        Appliances.add(jToggleButton62, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, 269, 213));

        jToggleButton63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Electric cooker.jpg"))); // NOI18N
        jToggleButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton63ActionPerformed(evt);
            }
        });
        Appliances.add(jToggleButton63, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 269, 213));

        jToggleButton64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/electric kettle.jpg"))); // NOI18N
        jToggleButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton64ActionPerformed(evt);
            }
        });
        Appliances.add(jToggleButton64, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 269, 220));

        jToggleButton65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Baking Mixer.jpg"))); // NOI18N
        jToggleButton65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton65ActionPerformed(evt);
            }
        });
        Appliances.add(jToggleButton65, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 269, 230));

        jToggleButton66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Wall Fan.jpg"))); // NOI18N
        jToggleButton66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton66ActionPerformed(evt);
            }
        });
        Appliances.add(jToggleButton66, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 580, 269, 230));

        jToggleButton67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Air Fryer.jpg"))); // NOI18N
        jToggleButton67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton67ActionPerformed(evt);
            }
        });
        Appliances.add(jToggleButton67, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 580, 269, 230));

        jToggleButton68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/food processor.jpg"))); // NOI18N
        jToggleButton68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton68ActionPerformed(evt);
            }
        });
        Appliances.add(jToggleButton68, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 580, 269, 230));

        jLabel160.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel160.setText("Quantity:");
        Appliances.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 260, -1, 28));
        Appliances.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 260, 50, 28));

        jLabel161.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel161.setText(" ₱ 527");
        Appliances.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 520, 80, 40));

        jLabel162.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel162.setText("Electric Cooker");
        Appliances.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, -1, 28));
        Appliances.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 540, 50, 28));

        jLabel163.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel163.setText(" ₱ 201");
        Appliances.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 240, -1, 40));

        jLabel164.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel164.setText("B1T1 StandFan");
        Appliances.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, 28));

        jLabel165.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel165.setText("Quantity:");
        Appliances.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, -1, 28));
        Appliances.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 50, 28));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel57.setText(" ₱ 85");
        Appliances.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 60, 40));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel58.setText("Meat Shredder");
        Appliances.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, -1, 28));

        jLabel166.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel166.setText("Quantity:");
        Appliances.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 260, -1, 28));
        Appliances.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 50, 28));

        jLabel167.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel167.setText(" ₱ 87");
        Appliances.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 60, 40));

        jLabel168.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel168.setText("Mini Water Dispenser");
        Appliances.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, -1, 28));

        jLabel169.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel169.setText("Quantity:");
        Appliances.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, 28));
        Appliances.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 50, 28));

        jLabel170.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel170.setText("Stainless Steamer");
        Appliances.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 520, -1, 28));

        jLabel171.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel171.setText("Whisks Hand Mixer");
        Appliances.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 510, -1, 28));

        jLabel172.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel172.setText("Baking Mixer");
        Appliances.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 810, -1, 28));

        jLabel173.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel173.setText("Wall Fan");
        Appliances.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 810, -1, 28));

        jLabel174.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel174.setText("Air Fryer");
        Appliances.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 810, -1, 28));

        jLabel175.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel175.setText("Food Processor");
        Appliances.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 810, -1, 28));

        jLabel176.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel176.setText("Clip Fan");
        Appliances.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, -1, 28));

        jLabel177.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel177.setText(" ₱ 225");
        Appliances.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 530, -1, 40));

        jLabel178.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel178.setText(" ₱ 175");
        Appliances.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 520, 80, 40));

        jLabel179.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel179.setText(" ₱ 438");
        Appliances.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 810, 80, 40));

        jLabel180.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel180.setText(" ₱ 454");
        Appliances.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 810, 80, 40));

        jLabel181.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel181.setText(" ₱ 119");
        Appliances.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 810, 80, 40));

        jLabel182.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel182.setText(" ₱ 123");
        Appliances.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 810, 80, 40));

        jLabel183.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel183.setText(" ₱ 128");
        Appliances.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 80, 40));

        jLabel184.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel184.setText("Quantity:");
        Appliances.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 550, -1, 28));

        jLabel185.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel185.setText("Quantity:");
        Appliances.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 540, -1, 28));

        jLabel186.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel186.setText("Quantity:");
        Appliances.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 840, -1, 28));

        jLabel187.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel187.setText("Quantity:");
        Appliances.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 840, -1, 28));

        jLabel188.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel188.setText("Quantity:");
        Appliances.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 840, -1, 28));

        jLabel189.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel189.setText("Quantity:");
        Appliances.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 840, -1, 28));

        jLabel190.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel190.setText("Quantity:");
        Appliances.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, -1, 28));

        jLabel191.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel191.setText("Quantity:");
        Appliances.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 540, -1, 28));
        Appliances.add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 550, 50, 28));
        Appliances.add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 540, 50, 28));
        Appliances.add(jTextField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 840, 50, 28));
        Appliances.add(jTextField26, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 840, 50, 28));
        Appliances.add(jTextField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 840, 50, 28));
        Appliances.add(jTextField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 840, 50, 28));
        Appliances.add(jTextField29, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 50, 28));

        AppliancesScroll.setViewportView(Appliances);

        shoppingTab.addTab("Appliances", AppliancesScroll);

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("SHOPPING");

        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cart.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout shoppingLayout = new javax.swing.GroupLayout(shopping);
        shopping.setLayout(shoppingLayout);
        shoppingLayout.setHorizontalGroup(
            shoppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shoppingTab)
            .addGroup(shoppingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        shoppingLayout.setVerticalGroup(
            shoppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shoppingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(shoppingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(shoppingTab, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
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

  
 
    private void NotifsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotifsActionPerformed
        
    }//GEN-LAST:event_NotifsActionPerformed
       
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
        Application.setVisible(false);
        Dashboard.setVisible(false);
        TransacHis.setVisible(false);
        AccReg.setVisible(false);
        CheckCardY.setVisible(false);
        CheckCardN.setVisible(false);
        bills.setVisible(false);
        shopping.setVisible(true);  
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
        BillsGUI billsGUI = new BillsGUI();
        billsGUI.setVisible(true);
    }//GEN-LAST:event_PayBActionPerformed

    private void jToggleButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton32ActionPerformed

    private void jToggleButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton31ActionPerformed

    private void jToggleButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton30ActionPerformed

    private void jToggleButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton29ActionPerformed

    private void jToggleButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton28ActionPerformed

    private void jToggleButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton27ActionPerformed

    private void jToggleButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton25ActionPerformed

    private void jToggleButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton24ActionPerformed

    private void jToggleButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton23ActionPerformed

    private void jToggleButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton59ActionPerformed

    private void jToggleButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton60ActionPerformed

    private void jToggleButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton61ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton61ActionPerformed

    private void jToggleButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton63ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton63ActionPerformed

    private void jToggleButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton64ActionPerformed

    private void jToggleButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton65ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton65ActionPerformed

    private void jToggleButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton66ActionPerformed

    private void jToggleButton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton67ActionPerformed

    private void jToggleButton68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton68ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton68ActionPerformed

    private void jToggleButton71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton71ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton71ActionPerformed

    private void jToggleButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton72ActionPerformed

    private void jToggleButton73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton73ActionPerformed

    private void jToggleButton75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton75ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton75ActionPerformed

    private void jToggleButton76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton76ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton76ActionPerformed

    private void jToggleButton77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton77ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton77ActionPerformed

    private void jToggleButton78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton78ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton78ActionPerformed

    private void jToggleButton79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton79ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton79ActionPerformed

    private void jToggleButton80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton80ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton80ActionPerformed

    private void jToggleButton74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton74ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton74ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        CartGUI1 cart = new CartGUI1();
        cart.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void GenRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenRepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenRepActionPerformed

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
    private javax.swing.JPanel Appliances;
    private javax.swing.JScrollPane AppliancesScroll;
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
    private javax.swing.JPanel Clothings;
    private javax.swing.JScrollPane ClothingsScroll;
    private javax.swing.JTable DashHis;
    private javax.swing.JButton Dashb;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JLabel DateDis;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JLabel DescrDis;
    private javax.swing.JScrollPane FoodScroll;
    private javax.swing.JPanel Foods;
    private javax.swing.JButton GenRep;
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
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel235;
    private javax.swing.JLabel jLabel236;
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
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
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
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton14;
    private javax.swing.JToggleButton jToggleButton15;
    private javax.swing.JToggleButton jToggleButton19;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton22;
    private javax.swing.JToggleButton jToggleButton23;
    private javax.swing.JToggleButton jToggleButton24;
    private javax.swing.JToggleButton jToggleButton25;
    private javax.swing.JToggleButton jToggleButton26;
    private javax.swing.JToggleButton jToggleButton27;
    private javax.swing.JToggleButton jToggleButton28;
    private javax.swing.JToggleButton jToggleButton29;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton30;
    private javax.swing.JToggleButton jToggleButton31;
    private javax.swing.JToggleButton jToggleButton32;
    private javax.swing.JToggleButton jToggleButton33;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton58;
    private javax.swing.JToggleButton jToggleButton59;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton60;
    private javax.swing.JToggleButton jToggleButton61;
    private javax.swing.JToggleButton jToggleButton62;
    private javax.swing.JToggleButton jToggleButton63;
    private javax.swing.JToggleButton jToggleButton64;
    private javax.swing.JToggleButton jToggleButton65;
    private javax.swing.JToggleButton jToggleButton66;
    private javax.swing.JToggleButton jToggleButton67;
    private javax.swing.JToggleButton jToggleButton68;
    private javax.swing.JToggleButton jToggleButton69;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton70;
    private javax.swing.JToggleButton jToggleButton71;
    private javax.swing.JToggleButton jToggleButton72;
    private javax.swing.JToggleButton jToggleButton73;
    private javax.swing.JToggleButton jToggleButton74;
    private javax.swing.JToggleButton jToggleButton75;
    private javax.swing.JToggleButton jToggleButton76;
    private javax.swing.JToggleButton jToggleButton77;
    private javax.swing.JToggleButton jToggleButton78;
    private javax.swing.JToggleButton jToggleButton79;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton80;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JPanel payments;
    private javax.swing.JPanel shopping;
    private javax.swing.JTabbedPane shoppingTab;
    // End of variables declaration//GEN-END:variables
}
