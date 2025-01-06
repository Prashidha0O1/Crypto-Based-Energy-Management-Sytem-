/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.energymanagement.views;

import com.energymanagement.model.EnergyTransactionModel;
import com.energymanagement.controller.ValidationUtil;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.energymanagement.controller.algorithms.InsertionSort;
import com.energymanagement.controller.algorithms.MergeSort;
import com.energymanagement.controller.algorithms.SelectionSort;
import com.energymanagement.controller.algorithms.BinarySearch;
import java.awt.Dimension;

/**
 *
 * @author Prashidha
 */
public class EnergyManagementApp extends javax.swing.JFrame {

    private java.awt.CardLayout cardLayout;
    private List<EnergyTransactionModel> energyTransactions;

    /**
     * Creates new form NewJFrame
     */
    public EnergyManagementApp() {
//        Image applogo =  new ImageIcon(this.getClass().getResource("./logo.PNG")).getImage();
//        this.setIconImage(applogo);
        setResizable(false);
        setLocation(200, 10);
        initComponents();
        initializeLayout();
        startProgress();
        initData();
    }

    private void loadScreen(String screenName) {
        cardLayout.show(getContentPane(), screenName);
    }

    private void initializeLayout() {
        cardLayout = new java.awt.CardLayout();
        getContentPane().setLayout(cardLayout);

        // Add panels with unique identifiers
        getContentPane().add(pnlLoadingScreen, "LoadingScreen");
        getContentPane().add(pnlMainHomeScreen, "MainScreen");
        getContentPane().add(pnlLoginScreen, "LoginScreen");

        // Start with the loading screen
        loadScreen("LoadingScreen");
    }

    private void startProgress() {
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {  // Use String instead of Void
            @Override
            protected Void doInBackground() throws Exception {
                String[] messages = {
                    "Initializing...",
                    "Loading Resources...",
                    "Creating Login Wallets...",
                    "Connecting to Server...",
                    "Finalizing Setup..."
                };

                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(60); // Simulated delay (60ms per step for 6 seconds total)

                    // Update messages at specific milestones (20%, 40%, etc.)
                    if (i % 20 == 0 && i / 20 < messages.length) {
                        publish(messages[i / 20] + "|" + i); // Publish message and percentage together
                    } else {
                        publish("|" + i); // Only percentage update
                    }
                }
                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                for (String chunk : chunks) {
                    String[] parts = chunk.split("\\|");

                    if (!parts[0].isEmpty()) {
                        lblStatus.setText(parts[0]); // Update the status message
                    }

                    int percentage = Integer.parseInt(parts[1]);
                    lblStatusPercentage.setText(percentage + "%"); // Update percentage label
                    prgsbarLoading.setValue(percentage); // Update the progress bar
                }
            }

            @Override
            protected void done() {
                loadScreen("LoginScreen"); // Load the next screen when done
            }
        };
        worker.execute(); // Start the worker thread
    }

    private void initData() {
        // Initialize the energyTransactions list
        energyTransactions = new LinkedList<>();

        // Create a table model with columns
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{}, // Empty data initially
                new String[]{
                    "Transaction ID", "User ID", "Energy Units", "Token Type",
                    "Payment Amount", "Energy Source", "Energy Type", "Location"
                }
        );

        // Set the model to the JTable
        tblEnergyTransactions.setModel(model);

        // Add some sample data for demonstration
        energyTransactions.add(new EnergyTransactionModel("T0041", "0x34y5y6y7", 120.0, "TokenD", 250.0, "Geothermal", "Type4", "Bhaktapur"));
        energyTransactions.add(new EnergyTransactionModel("T0051", "0x56z7z8z9", 80.5, "TokenE", 175.0, "Biomass", "Type5", "Chitwan"));
        energyTransactions.add(new EnergyTransactionModel("T0061", "0x78a9a0a1", 65.0, "TokenF", 130.0, "Solar", "Type6", "Dharan"));
        energyTransactions.add(new EnergyTransactionModel("T0071", "0x90b1b2b3", 150.0, "TokenG", 300.0, "Wind", "Type7", "Butwal"));
        energyTransactions.add(new EnergyTransactionModel("T0081", "0x12c3c4c5", 90.0, "TokenH", 180.0, "Hydro", "Type8", "Biratnagar"));
        energyTransactions.add(new EnergyTransactionModel("T0091", "0x34d5d6d7", 70.0, "TokenI", 140.0, "Geothermal", "Type9", "Janakpur"));
        energyTransactions.add(new EnergyTransactionModel("T0101", "0x56e7e8e9", 110.5, "TokenJ", 220.0, "Biomass", "Type10", "Nepalgunj"));
        energyTransactions.add(new EnergyTransactionModel("T0111", "0x78f9f0f1", 130.0, "TokenK", 260.0, "Solar", "Type11", "Hetauda"));
        energyTransactions.add(new EnergyTransactionModel("T0121", "0x90g1g2g3", 95.0, "TokenL", 190.0, "Wind", "Type12", "Dhangadhi"));
        energyTransactions.add(new EnergyTransactionModel("T0131", "0x12h3h4h5", 125.0, "TokenM", 250.0, "Hydro", "Type13", "Birgunj"));

        // Populate the table with sample data
        for (EnergyTransactionModel energy : energyTransactions) {
            model.addRow(new Object[]{
                energy.getTransactionId(),
                energy.getUserId(),
                energy.getEnergyUnits(),
                energy.getTokenType(),
                energy.getPaymentAmount(),
                energy.getEnergySource(),
                energy.getEnergyType(),
                energy.getLocation()
            });
        }
    }

    private void recordEnergyTransaction(EnergyTransactionModel energy) {
        // Add the transaction to the list
        energyTransactions.add(energy);

        // Get the table model
        DefaultTableModel model = (DefaultTableModel) tblEnergyTransactions.getModel();

        // Add a new row with all energy transaction attributes
        model.addRow(new Object[]{
            energy.getTransactionId(),
            energy.getUserId(),
            energy.getEnergyUnits(),
            energy.getTokenType(),
            energy.getPaymentAmount(),
            energy.getEnergySource(),
            energy.getEnergyType(),
            energy.getLocation()
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMainHomeScreen = new javax.swing.JPanel();
        btnLogOut = new javax.swing.JButton();
        tabbedPaneMain = new javax.swing.JTabbedPane();
        pnlHomeScreen = new javax.swing.JPanel();
        lblSlogan2 = new javax.swing.JLabel();
        lblSlogan1 = new javax.swing.JLabel();
        lblHomeSubSlogan = new javax.swing.JLabel();
        lblBackgroundImage = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pnlWhitepaperScreen = new javax.swing.JPanel();
        lblEnerCoinWhitepaper = new javax.swing.JLabel();
        lblChillGuy = new javax.swing.JLabel();
        pnlDashboard = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEnergyTransactions = new javax.swing.JTable();
        lblTableHeading = new javax.swing.JLabel();
        txtTfldransactionId = new javax.swing.JTextField();
        txtfldUserId = new javax.swing.JTextField();
        txtfldEnergyUnits = new javax.swing.JTextField();
        cmbTokenType = new javax.swing.JComboBox<>();
        txtfldPaymentAmount = new javax.swing.JTextField();
        cmbEnergySource = new javax.swing.JComboBox<>();
        btnRecordTransaction = new javax.swing.JButton();
        btnRemoveRecords = new javax.swing.JButton();
        btnUpdateRecords = new javax.swing.JButton();
        txtfldLocation = new javax.swing.JTextField();
        btnClearForm = new javax.swing.JButton();
        lblTransactionErrorMsg = new javax.swing.JLabel();
        lblUserIDErrorMsg = new javax.swing.JLabel();
        lblErrorEnergyUnits = new javax.swing.JLabel();
        lblErrorPaymentAmount = new javax.swing.JLabel();
        lblLocationErrror = new javax.swing.JLabel();
        cmbEnergy = new javax.swing.JComboBox<>();
        lblMessage = new javax.swing.JLabel();
        btnSort = new javax.swing.JButton();
        cmbSortingBy = new javax.swing.JComboBox<>();
        cmbSortingIn = new javax.swing.JComboBox<>();
        txtFldSearchBar = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        pnlAboutUs = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        pnlLoginScreen = new javax.swing.JPanel();
        pnlLeftLoginScreen = new javax.swing.JPanel();
        lblSolana = new javax.swing.JLabel();
        lblEth = new javax.swing.JLabel();
        lblLeftLOgo = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblLoginWithCredentials = new javax.swing.JLabel();
        txtFieldLOginUsername = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        lblForgotPassword = new javax.swing.JLabel();
        lblLoginError = new javax.swing.JLabel();
        pwdFldLogin = new javax.swing.JPasswordField();
        jRadioButton1 = new javax.swing.JRadioButton();
        pnlLoadingScreen = new javax.swing.JPanel();
        prgsbarLoading = new javax.swing.JProgressBar();
        lblLogoEnerChain = new javax.swing.JLabel();
        lblEnerChain = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblStatusPercentage = new javax.swing.JLabel();

        pnlMainHomeScreen.setBackground(new java.awt.Color(2, 136, 138));
        pnlMainHomeScreen.setMaximumSize(new java.awt.Dimension(1125, 768));
        pnlMainHomeScreen.setMinimumSize(new java.awt.Dimension(1125, 768));
        pnlMainHomeScreen.setPreferredSize(new java.awt.Dimension(1125, 768));
        pnlMainHomeScreen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogOut.setBackground(new java.awt.Color(2, 136, 138));
        btnLogOut.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnLogOut.setText("Log Out");
        btnLogOut.setBorder(new javax.swing.border.MatteBorder(null));
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        pnlMainHomeScreen.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 110, 50));

        tabbedPaneMain.setBackground(new java.awt.Color(73, 127, 174));
        tabbedPaneMain.setMaximumSize(new java.awt.Dimension(1024, 768));
        tabbedPaneMain.setMinimumSize(new java.awt.Dimension(1024, 768));
        tabbedPaneMain.setPreferredSize(new java.awt.Dimension(1024, 768));

        pnlHomeScreen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSlogan2.setBackground(new java.awt.Color(0, 255, 255));
        lblSlogan2.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lblSlogan2.setForeground(new java.awt.Color(0, 255, 255));
        lblSlogan2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSlogan2.setText("Renewable Energy");
        pnlHomeScreen.add(lblSlogan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 480, 70));

        lblSlogan1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lblSlogan1.setForeground(new java.awt.Color(0, 255, 255));
        lblSlogan1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSlogan1.setText("Greener Future With");
        pnlHomeScreen.add(lblSlogan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 570, 77));

        lblHomeSubSlogan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHomeSubSlogan.setText("<html>\nNow with crypto, we're powering sustainable energy solutions </br>\nfor a decentralized future.\n\n</hml>");
        pnlHomeScreen.add(lblHomeSubSlogan, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 460, -1));

        lblBackgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/energymanagement/resources/backgrou.png"))); // NOI18N
        pnlHomeScreen.add(lblBackgroundImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -90, 1110, 780));

        jButton1.setText("jButton1");
        pnlHomeScreen.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, -1, -1));

        tabbedPaneMain.addTab("Home", pnlHomeScreen);

        pnlWhitepaperScreen.setBackground(new java.awt.Color(2, 136, 138));

        lblEnerCoinWhitepaper.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEnerCoinWhitepaper.setText("<html>\n    <h1>Whitepaper: EnerCoin - Proof of Energy <h1>\n    <be>Introduction</be>\n    <p>\n      Our native token is designed to revolutionize the way energy is utilized within blockchain networks. By implementing a Proof of Energy (PoE) consensus mechanism, we ensure that the energy usage within the network is optimized, reducing waste and increasing efficiency.\n    </p>\n    <br>\n    <be>What is Proof of Energy?</be>\n    <p>\n      Proof of Energy is a novel consensus mechanism that calculates the energy consumption of blockchain operations. Unlike traditional consensus mechanisms, PoE monitors the actual energy used during transactions and mining processes. This approach incentivizes nodes to maintain energy efficiency, thereby promoting sustainable blockchain practices.\n    </p>\n    <br>\n    <be>How PoE Works</be>\n    <p>\n      Nodes within the network are required to report their energy usage to the network’s smart contract. The smart contract then validates these reports and calculates the total energy consumption for the network. Rewards are distributed based on the energy efficiency of each node, incentivizing users to adopt energy-efficient practices.\n    </p>\n    <br>\n    <be>Implementation</be>\n      Our native token is built on a custom blockchain that integrates PoE. The blockchain uses smart contracts to monitor energy usage, and the tokenomics are designed to reward participants for their energy-efficient actions. The protocol will be tested and validated through pilot programs to ensure scalability and reliability.\n<br>\n  </div>\n</html>\n");
        lblEnerCoinWhitepaper.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblChillGuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/energymanagement/resources/chill.png"))); // NOI18N

        javax.swing.GroupLayout pnlWhitepaperScreenLayout = new javax.swing.GroupLayout(pnlWhitepaperScreen);
        pnlWhitepaperScreen.setLayout(pnlWhitepaperScreenLayout);
        pnlWhitepaperScreenLayout.setHorizontalGroup(
            pnlWhitepaperScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWhitepaperScreenLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblEnerCoinWhitepaper, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(lblChillGuy, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlWhitepaperScreenLayout.setVerticalGroup(
            pnlWhitepaperScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWhitepaperScreenLayout.createSequentialGroup()
                .addComponent(lblEnerCoinWhitepaper, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlWhitepaperScreenLayout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(lblChillGuy, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPaneMain.addTab("WhitePaper", pnlWhitepaperScreen);

        pnlDashboard.setBackground(new java.awt.Color(2, 136, 138));
        pnlDashboard.setMaximumSize(new java.awt.Dimension(1125, 768));
        pnlDashboard.setMinimumSize(new java.awt.Dimension(1125, 768));
        pnlDashboard.setPreferredSize(new java.awt.Dimension(1125, 768));
        pnlDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblEnergyTransactions.setBackground(new java.awt.Color(0, 255, 255));
        tblEnergyTransactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "TransactionID", "UserID", "EnergyUnits", "TokenType", "PaymentAmount", "Energy", "EnergySource ", "Location"
            }
        ));
        tblEnergyTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEnergyTransactionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEnergyTransactions);

        pnlDashboard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1111, 156));

        lblTableHeading.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTableHeading.setText("Energy Supply Transactions");
        pnlDashboard.add(lblTableHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 6, 240, -1));

        txtTfldransactionId.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TransactionID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        txtTfldransactionId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTfldransactionIdActionPerformed(evt);
            }
        });
        pnlDashboard.add(txtTfldransactionId, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 145, 61));

        txtfldUserId.setBorder(javax.swing.BorderFactory.createTitledBorder("UserID"));
        pnlDashboard.add(txtfldUserId, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 145, 61));

        txtfldEnergyUnits.setBorder(javax.swing.BorderFactory.createTitledBorder("EnergyUnits"));
        pnlDashboard.add(txtfldEnergyUnits, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 250, 145, 61));

        cmbTokenType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ETH", "SOL", "BTC", "DOGE", "TRX", "SLC" }));
        cmbTokenType.setBorder(javax.swing.BorderFactory.createTitledBorder("TokenType"));
        cmbTokenType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTokenTypeActionPerformed(evt);
            }
        });
        pnlDashboard.add(cmbTokenType, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 250, 145, 61));

        txtfldPaymentAmount.setBorder(javax.swing.BorderFactory.createTitledBorder("Payment Amount"));
        txtfldPaymentAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfldPaymentAmountActionPerformed(evt);
            }
        });
        pnlDashboard.add(txtfldPaymentAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 145, 61));

        cmbEnergySource.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solar", "Windmill", "Hydropower", "Nuclear Plant", "Coal Plant", " " }));
        cmbEnergySource.setBorder(javax.swing.BorderFactory.createTitledBorder("EnergySource"));
        pnlDashboard.add(cmbEnergySource, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, 140, 60));

        btnRecordTransaction.setBackground(new java.awt.Color(255, 153, 255));
        btnRecordTransaction.setText("Add");
        btnRecordTransaction.setBorder(null);
        btnRecordTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordTransactionActionPerformed(evt);
            }
        });
        pnlDashboard.add(btnRecordTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, 110, 44));

        btnRemoveRecords.setBackground(new java.awt.Color(255, 153, 255));
        btnRemoveRecords.setText("Delete");
        btnRemoveRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveRecordsActionPerformed(evt);
            }
        });
        pnlDashboard.add(btnRemoveRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 580, 110, 48));

        btnUpdateRecords.setBackground(new java.awt.Color(255, 153, 255));
        btnUpdateRecords.setText("Update");
        btnUpdateRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRecordsActionPerformed(evt);
            }
        });
        pnlDashboard.add(btnUpdateRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 580, 110, 48));

        txtfldLocation.setBorder(javax.swing.BorderFactory.createTitledBorder("Location"));
        txtfldLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfldLocationActionPerformed(evt);
            }
        });
        pnlDashboard.add(txtfldLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 430, 145, 61));

        btnClearForm.setBackground(new java.awt.Color(255, 153, 255));
        btnClearForm.setText("Clear");
        btnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFormActionPerformed(evt);
            }
        });
        pnlDashboard.add(btnClearForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 580, 110, 46));
        pnlDashboard.add(lblTransactionErrorMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 260, 20));
        pnlDashboard.add(lblUserIDErrorMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 300, 10));
        pnlDashboard.add(lblErrorEnergyUnits, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 320, 170, -1));
        pnlDashboard.add(lblErrorPaymentAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 499, 181, -1));
        pnlDashboard.add(lblLocationErrror, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 505, 134, -1));

        cmbEnergy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electrical", "Thermal", "Mechanical ", "Nuclear" }));
        cmbEnergy.setBorder(javax.swing.BorderFactory.createTitledBorder("Energy"));
        pnlDashboard.add(cmbEnergy, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, 145, 61));
        pnlDashboard.add(lblMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, 461, 14));

        btnSort.setText("Sort");
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });
        pnlDashboard.add(btnSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 106, 30));

        cmbSortingBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Transaction ID", "Location", "Energy Units", " " }));
        cmbSortingBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSortingByActionPerformed(evt);
            }
        });
        pnlDashboard.add(cmbSortingBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 133, 30));

        cmbSortingIn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        cmbSortingIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSortingInActionPerformed(evt);
            }
        });
        pnlDashboard.add(cmbSortingIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 129, 30));
        pnlDashboard.add(txtFldSearchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 195, 30));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/energymanagement/resources/search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        pnlDashboard.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 70, 30));

        tabbedPaneMain.addTab("Dashboard", pnlDashboard);

        pnlAboutUs.setBackground(new java.awt.Color(2, 136, 138));

        jLabel1.setText("<html>\n<section style=\"display: flex; justify-content: center; padding: 2rem; background-color: #f9fafc;\">\n  <div style=\"display: flex; max-width: 1200px; flex-direction: row; gap: 1.5rem; align-items: center;\">\n    <!-- Left Image Section -->\n    <div style=\"flex: 1; position: relative;\">\n      <h2>Learn More</h2>\n    </div>\n    <!-- Right Details Section -->\n    <div style=\"flex: 2; display: flex; gap: 1.5rem; flex-wrap: wrap;\">\n      <!-- Detail Cards -->\n      <div style=\"background-color: #fff; border: 1px solid #e0e0e0; border-radius: 10px; text-align: center; padding: 1rem; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); flex: 1;\">\n        <div style=\"font-size: 2rem; margin-bottom: 0.5rem;\">⚡</div>\n        <h3 style=\"font-size: 1.2rem; margin-bottom: 0.5rem; color: #2c3e50;\">Projects Explore</h3>\n        <p style=\"font-size: 0.9rem; color: #7f8c8d;\">We convert wind turbine to 100% wind energy</p>\n      </div>\n      <div style=\"background-color: #d1f2eb; border: 1px solid #27ae60; border-radius: 10px; text-align: center; padding: 1rem; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); flex: 1;\">\n        <div style=\"font-size: 2rem; margin-bottom: 0.5rem;\">📊</div>\n        <h3 style=\"font-size: 1.2rem; margin-bottom: 0.5rem; color: #2c3e50;\">Statistics Data</h3>\n        <p style=\"font-size: 0.9rem; color: #7f8c8d;\">We convert wind turbine to 100% wind energy</p>\n      </div>\n      <div style=\"background-color: #fff; border: 1px solid #e0e0e0; border-radius: 10px; text-align: center; padding: 1rem; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); flex: 1;\">\n        <div style=\"font-size: 2rem; margin-bottom: 0.5rem;\">🌍</div>\n        <h3 style=\"font-size: 1.2rem; margin-bottom: 0.5rem; color: #2c3e50;\">Zero Emission</h3>\n        <p style=\"font-size: 0.9rem; color: #7f8c8d;\">We convert wind turbine to 100% wind energy</p>\n      </div>\n      <div style=\"background-color: #fff; border: 1px solid #e0e0e0; border-radius: 10px; text-align: center; padding: 1rem; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); flex: 1;\">\n        <div style=\"font-size: 2rem; margin-bottom: 0.5rem;\">🔋</div>\n        <h3 style=\"font-size: 1.2rem; margin-bottom: 0.5rem; color: #2c3e50;\">Clean Energy</h3>\n        <p style=\"font-size: 0.9rem; color: #7f8c8d;\">We convert wind turbine to 100% wind energy</p>\n      </div>\n      <div style=\"background-color: #fff; border: 1px solid #e0e0e0; border-radius: 10px; text-align: center; padding: 1rem; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); flex: 1;\">\n        <div style=\"font-size: 2rem; margin-bottom: 0.5rem;\">🌾</div>\n        <h3 style=\"font-size: 1.2rem; margin-bottom: 0.5rem; color: #2c3e50;\">Hectare Area</h3>\n        <p style=\"font-size: 0.9rem; color: #7f8c8d;\">We convert wind turbine to 100% wind energy</p>\n      </div>\n      <div style=\"background-color: #fff; border: 1px solid #e0e0e0; border-radius: 10px; text-align: center; padding: 1rem; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); flex: 1;\">\n        <div style=\"font-size: 2rem; margin-bottom: 0.5rem;\">🌬️</div>\n        <h3 style=\"font-size: 1.2rem; margin-bottom: 0.5rem; color: #2c3e50;\">Megawatt Capacity</h3>\n        <p style=\"font-size: 0.9rem; color: #7f8c8d;\">We convert wind turbine to 100% wind energy</p>\n      </div>\n    </div>\n  </div>\n</section>\n\n\n\n</html>");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/energymanagement/resources/about us image.PNG"))); // NOI18N

        javax.swing.GroupLayout pnlAboutUsLayout = new javax.swing.GroupLayout(pnlAboutUs);
        pnlAboutUs.setLayout(pnlAboutUsLayout);
        pnlAboutUsLayout.setHorizontalGroup(
            pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAboutUsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        pnlAboutUsLayout.setVerticalGroup(
            pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAboutUsLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        tabbedPaneMain.addTab("About Us", pnlAboutUs);

        pnlMainHomeScreen.add(tabbedPaneMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1120, 720));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/energymanagement/resources/logoo.png"))); // NOI18N
        pnlMainHomeScreen.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, -1));

        pnlLoginScreen.setBackground(new java.awt.Color(6, 72, 83));
        pnlLoginScreen.setMaximumSize(new java.awt.Dimension(1125, 768));
        pnlLoginScreen.setMinimumSize(new java.awt.Dimension(1125, 768));
        pnlLoginScreen.setPreferredSize(new java.awt.Dimension(1125, 768));

        pnlLeftLoginScreen.setBackground(new java.awt.Color(2, 136, 138));
        pnlLeftLoginScreen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 255)));
        pnlLeftLoginScreen.setForeground(new java.awt.Color(0, 102, 102));

        lblSolana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/energymanagement/resources/solana.png"))); // NOI18N

        lblEth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/energymanagement/resources/ethereum.png"))); // NOI18N

        lblLeftLOgo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/energymanagement/resources/capture.PNG"))); // NOI18N
        lblLeftLOgo.setText("jLabel12");

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblName.setText("EnerChain");

        javax.swing.GroupLayout pnlLeftLoginScreenLayout = new javax.swing.GroupLayout(pnlLeftLoginScreen);
        pnlLeftLoginScreen.setLayout(pnlLeftLoginScreenLayout);
        pnlLeftLoginScreenLayout.setHorizontalGroup(
            pnlLeftLoginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLeftLoginScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEth, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSolana)
                .addGap(15, 15, 15))
            .addGroup(pnlLeftLoginScreenLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLeftLoginScreenLayout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(lblLeftLOgo, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        pnlLeftLoginScreenLayout.setVerticalGroup(
            pnlLeftLoginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLoginScreenLayout.createSequentialGroup()
                .addGroup(pnlLeftLoginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSolana, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlLeftLoginScreenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblEth, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(67, 67, 67)
                .addComponent(lblLeftLOgo)
                .addGap(30, 30, 30)
                .addComponent(lblName)
                .addContainerGap(278, Short.MAX_VALUE))
        );

        lblLoginWithCredentials.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblLoginWithCredentials.setForeground(new java.awt.Color(114, 218, 211));
        lblLoginWithCredentials.setText("Login with your credentials ");

        txtFieldLOginUsername.setBackground(new java.awt.Color(0, 204, 204));
        txtFieldLOginUsername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtFieldLOginUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtFieldLOginUsername.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        txtFieldLOginUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldLOginUsernameActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(0, 204, 204));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblForgotPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblForgotPassword.setForeground(new java.awt.Color(114, 218, 211));
        lblForgotPassword.setText("Forgot your Password? Register");

        lblLoginError.setForeground(new java.awt.Color(255, 51, 51));

        pwdFldLogin.setBackground(new java.awt.Color(0, 204, 204));
        pwdFldLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pwdFldLogin.setForeground(new java.awt.Color(255, 255, 255));
        pwdFldLogin.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)));

        jRadioButton1.setForeground(new java.awt.Color(0, 255, 255));
        jRadioButton1.setText("Remember this computer.");

        javax.swing.GroupLayout pnlLoginScreenLayout = new javax.swing.GroupLayout(pnlLoginScreen);
        pnlLoginScreen.setLayout(pnlLoginScreenLayout);
        pnlLoginScreenLayout.setHorizontalGroup(
            pnlLoginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginScreenLayout.createSequentialGroup()
                .addComponent(pnlLeftLoginScreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addGroup(pnlLoginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginScreenLayout.createSequentialGroup()
                        .addGroup(pnlLoginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pwdFldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFieldLOginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginScreenLayout.createSequentialGroup()
                        .addGroup(pnlLoginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginScreenLayout.createSequentialGroup()
                        .addGroup(pnlLoginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLoginError, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLoginWithCredentials))
                        .addGap(39, 39, 39))))
        );
        pnlLoginScreenLayout.setVerticalGroup(
            pnlLoginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginScreenLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(lblLoginWithCredentials)
                .addGap(28, 28, 28)
                .addComponent(lblLoginError, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(txtFieldLOginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(pwdFldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblForgotPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addGap(12, 12, 12)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(pnlLeftLoginScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1125, 768));
        setMinimumSize(new java.awt.Dimension(1125, 768));
        setPreferredSize(new java.awt.Dimension(1125, 768));

        pnlLoadingScreen.setBackground(new java.awt.Color(2, 136, 138));
        pnlLoadingScreen.setMaximumSize(new java.awt.Dimension(1125, 768));
        pnlLoadingScreen.setMinimumSize(new java.awt.Dimension(1125, 768));
        pnlLoadingScreen.setPreferredSize(new java.awt.Dimension(1125, 768));

        lblLogoEnerChain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/energymanagement/resources/capture.PNG"))); // NOI18N
        lblLogoEnerChain.setText("jLabel2");

        lblEnerChain.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblEnerChain.setForeground(new java.awt.Color(255, 255, 255));
        lblEnerChain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEnerChain.setText("EnerChain");

        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lblStatusPercentage.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N

        javax.swing.GroupLayout pnlLoadingScreenLayout = new javax.swing.GroupLayout(pnlLoadingScreen);
        pnlLoadingScreen.setLayout(pnlLoadingScreenLayout);
        pnlLoadingScreenLayout.setHorizontalGroup(
            pnlLoadingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prgsbarLoading, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoadingScreenLayout.createSequentialGroup()
                .addContainerGap(418, Short.MAX_VALUE)
                .addGroup(pnlLoadingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoadingScreenLayout.createSequentialGroup()
                        .addComponent(lblLogoEnerChain, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(358, 358, 358))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoadingScreenLayout.createSequentialGroup()
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(395, 395, 395)
                        .addComponent(lblStatusPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoadingScreenLayout.createSequentialGroup()
                        .addComponent(lblEnerChain, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(316, 316, 316))))
        );
        pnlLoadingScreenLayout.setVerticalGroup(
            pnlLoadingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoadingScreenLayout.createSequentialGroup()
                .addContainerGap(210, Short.MAX_VALUE)
                .addComponent(lblLogoEnerChain)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEnerChain)
                .addGap(194, 194, 194)
                .addGroup(pnlLoadingScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStatusPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prgsbarLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlLoadingScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLoadingScreen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        loadScreen("LoginScreen");
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void txtFieldLOginUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldLOginUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldLOginUsernameActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // Get the username and password input
        String username = txtFieldLOginUsername.getText();
        String password = String.valueOf(pwdFldLogin.getPassword());

        // Check if username or password is empty
        if (username.isEmpty() || password.isEmpty()) {
            lblLoginError.setText("Please enter your username and password.");
        } // Check if username and password are incorrect
        else if (!username.equals("admin") || !password.equals("admin")) {
            lblLoginError.setText("Username and password mismatch.");
        } // If credentials are correct, proceed to load the main screen
        else {
            lblLoginError.setText(""); // Clear any previous error messages
            loadScreen("MainScreen");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtTfldransactionIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTfldransactionIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTfldransactionIdActionPerformed

    private void cmbTokenTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTokenTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTokenTypeActionPerformed

    private void txtfldPaymentAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfldPaymentAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfldPaymentAmountActionPerformed

    private void txtfldLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfldLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfldLocationActionPerformed

    private void highlightInvalidField(JTextField textField, String title, Color color) {
        // Set a new border with the provided title and color
        textField.setBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        javax.swing.BorderFactory.createLineBorder(color, 2), // Border with desired color
                        title, // Title for the border
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new java.awt.Font("Segoe UI", 1, 12), // Font settings
                        color // Font color matches border color
                )
        );

        // Prevent layout issues
        textField.revalidate();
        textField.repaint();
    }

    private void resetFieldBorderWithTitle(JTextField textField, String title) {
        // Reset the border to its default state with the title
        textField.setBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        javax.swing.BorderFactory.createLineBorder(Color.GRAY, 1), // Default gray border
                        title, // Title for the border
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new java.awt.Font("Segoe UI", 1, 12), // Font settings
                        Color.BLACK // Default font color
                )
        );
        // Prevent layout issues
        textField.revalidate();
        textField.repaint();
    }

    private void resetAllUpdateFields() {
        // Reset all field borders
        resetFieldBorderWithTitle(txtTfldransactionId, "Transaction ID");
        resetFieldBorderWithTitle(txtfldUserId, "User ID");
        resetFieldBorderWithTitle(txtfldEnergyUnits, "Energy Units");
        resetFieldBorderWithTitle(txtfldPaymentAmount, "Payment Amount");
        resetFieldBorderWithTitle(txtfldLocation, "Location");

        // Reset all error labels
        lblTransactionErrorMsg.setText("");
        lblUserIDErrorMsg.setText("");
        lblErrorEnergyUnits.setText("");
        lblErrorPaymentAmount.setText("");
        lblLocationErrror.setText("");
        lblMessage.setText("");
    }

    private void loadListToTable(List<EnergyTransactionModel> energyTransactions) {
        DefaultTableModel model = (DefaultTableModel) tblEnergyTransactions.getModel();

        //clear existing rows if needed
        model.setRowCount(0);

        //populate the table with drone data
        energyTransactions.forEach(energy -> model.addRow(new Object[]{
            energy.getTransactionId(),
            energy.getUserId(),
            energy.getEnergyUnits(),
            energy.getTokenType(),
            energy.getPaymentAmount(),
            energy.getEnergySource(),
            energy.getEnergyType(),
            energy.getLocation()
        }));
    }

    private void sortTransactionList(String selectedColumn) {
        // Sorting based on selected column
        SelectionSort selectionSort = new SelectionSort();
        InsertionSort insertionSort = new InsertionSort();
        MergeSort mergeSort = new MergeSort();

        if (selectedColumn.equals("Energy Units")) {
            boolean isDesc = false; // Set sorting order
            energyTransactions = selectionSort.sortByEnergyUnits(energyTransactions, isDesc);
        } else if (selectedColumn.equals("Transaction ID")) {
            boolean isDesc = false;
            energyTransactions = insertionSort.sortByTransactonID(energyTransactions, isDesc);
        } else if (selectedColumn.equals("Location")) {
            boolean isDesc = false;
            energyTransactions = mergeSort.sortByLocation(energyTransactions, isDesc);
        }
    }

    public void showSearchResult(EnergyTransactionModel result) {
        if (result == null) {
            JOptionPane.showMessageDialog(null, "No matching record found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Define column names
        String[] columnNames = {"Transaction ID", "User ID", "Energy Units", "Payment Amount", "Location", "Token Type", "Energy Source", "Energy Type"};

        // Populate table data
        Object[][] data = {
            {result.getTransactionId(), result.getUserId(), result.getEnergyUnits(),
                result.getPaymentAmount(), result.getLocation(), result.getTokenType(),
                result.getEnergySource(), result.getEnergyType()}
        };

        // Create table model and table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);

        // Set column widths for better visibility
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Transaction ID
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // User ID
        table.getColumnModel().getColumn(2).setPreferredWidth(120); // Energy Units
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Payment Amount
        table.getColumnModel().getColumn(4).setPreferredWidth(120); // Location
        table.getColumnModel().getColumn(5).setPreferredWidth(130); // Token Type
        table.getColumnModel().getColumn(6).setPreferredWidth(150); // Energy Source
        table.getColumnModel().getColumn(7).setPreferredWidth(150); // Energy Type

        // Create a scroll pane and set its size
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1200, 100));

        // Show the table in a dialog box
        JOptionPane optionPane = new JOptionPane(scrollPane, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog(null, "Search Result");
        dialog.setSize(1200, 100);
        dialog.setVisible(true);
    }


    private void btnRecordTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordTransactionActionPerformed

        // Get input values
        String transactionId = txtTfldransactionId.getText();
        String userId = txtfldUserId.getText();
        String energyUnits = txtfldEnergyUnits.getText();
        String paymentAmount = txtfldPaymentAmount.getText();
        String location = txtfldLocation.getText();
        String tokenType = cmbTokenType.getSelectedItem().toString();
        String energySource = cmbEnergySource.getSelectedItem().toString();
        String energyType = cmbEnergy.getSelectedItem().toString();

        // Reset error messages
        lblTransactionErrorMsg.setText("");
        lblUserIDErrorMsg.setText("");
        lblErrorEnergyUnits.setText("");
        lblErrorPaymentAmount.setText("");
        lblLocationErrror.setText("");

        // Validation flags
        boolean isValid = true;

        if (transactionId.isEmpty() && userId.isEmpty() && energyUnits.isEmpty()
                && paymentAmount.isEmpty() && location.isEmpty()) {
            lblMessage.setText("Please Enter Valid Values"); // Set general error message
            lblMessage.setForeground(Color.RED); // Set text color to red
            return; // Exit early since all fields are empty
        }

        // Validate inputs
        if (!ValidationUtil.isValidTransactionId(transactionId, lblTransactionErrorMsg)) {
            isValid = false;
            highlightInvalidField(txtTfldransactionId, "TransactionID", Color.RED);

        } else {
            resetFieldBorderWithTitle(txtTfldransactionId, "TransactionID");
        }
        if (!ValidationUtil.isValidUserId(userId, lblUserIDErrorMsg)) {
            isValid = false;
            highlightInvalidField(txtfldUserId, "UserID", Color.RED);
        } else {
            resetFieldBorderWithTitle(txtfldUserId, "UserID");
        }

        if (!ValidationUtil.isValidNumericValue(energyUnits, lblErrorEnergyUnits)) {
            isValid = false;
            highlightInvalidField(txtfldEnergyUnits, "EnergyUnits", Color.RED);
        } else {
            resetFieldBorderWithTitle(txtfldEnergyUnits, "EnergyUnits");
        }

        if (!ValidationUtil.isValidNumericValue(paymentAmount, lblErrorPaymentAmount)) {
            isValid = false;
            highlightInvalidField(txtfldPaymentAmount, "PaymentAmount", Color.RED);
        } else {
            resetFieldBorderWithTitle(txtfldPaymentAmount, "PaymentAmount");
        }

        if (!ValidationUtil.isValidLocation(location, lblLocationErrror)) {
            isValid = false;
            highlightInvalidField(txtfldLocation, "Location", Color.RED);
        } else {
            resetFieldBorderWithTitle(txtfldLocation, "Location");
        }
        // If all validations pass
        if (isValid) {
            try {
                String transactionIdValue = transactionId;
                String userIdValue = userId;
                double energyUnitsValue = Double.parseDouble(energyUnits);
                double paymentAmountValue = Double.parseDouble(paymentAmount);

                // Create the EnergyTransaction object
                EnergyTransactionModel transaction = new EnergyTransactionModel(
                        transactionIdValue,
                        userIdValue,
                        energyUnitsValue,
                        tokenType,
                        paymentAmountValue,
                        energySource,
                        energyType,
                        location
                );

                // Record the transaction
                recordEnergyTransaction(transaction);

                // Show success message
                JOptionPane.showMessageDialog(this, "Transaction data has been successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error parsing numeric values. Please check the inputs.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please correct the highlighted errors and try again.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnRecordTransactionActionPerformed

    private void tblEnergyTransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEnergyTransactionsMouseClicked
        tblEnergyTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = tblEnergyTransactions.getSelectedRow();
                if (selectedRow != -1) {
                    txtTfldransactionId.setText(tblEnergyTransactions.getValueAt(selectedRow, 0).toString());
                    txtfldUserId.setText(tblEnergyTransactions.getValueAt(selectedRow, 1).toString());
                    txtfldEnergyUnits.setText(tblEnergyTransactions.getValueAt(selectedRow, 2).toString());
                    cmbTokenType.setSelectedItem(tblEnergyTransactions.getValueAt(selectedRow, 3).toString());
                    txtfldPaymentAmount.setText(tblEnergyTransactions.getValueAt(selectedRow, 4).toString());
                    cmbEnergySource.setSelectedItem(tblEnergyTransactions.getValueAt(selectedRow, 5).toString());
                    cmbEnergy.setSelectedItem(tblEnergyTransactions.getValueAt(selectedRow, 6).toString());
                    txtfldLocation.setText(tblEnergyTransactions.getValueAt(selectedRow, 7).toString());
                }
            }
        });
    }//GEN-LAST:event_tblEnergyTransactionsMouseClicked

    private void btnUpdateRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRecordsActionPerformed
        int selectedRow = tblEnergyTransactions.getSelectedRow();
        if (selectedRow != -1) {
            // Ask for confirmation
            int res = JOptionPane.showConfirmDialog(this,
                    "Do you want to update this transaction?",
                    "Confirm Update",
                    JOptionPane.YES_NO_OPTION);

            if (res == JOptionPane.YES_OPTION) {
                // Get input data
                String transactionId = txtTfldransactionId.getText().trim();
                String userId = txtfldUserId.getText().trim();
                String energyUnits = txtfldEnergyUnits.getText().trim();
                String paymentAmount = txtfldPaymentAmount.getText().trim();
                String location = txtfldLocation.getText().trim();
                String tokenType = cmbTokenType.getSelectedItem().toString();
                String energySource = cmbEnergySource.getSelectedItem().toString();
                String energyType = cmbEnergy.getSelectedItem().toString();

                // Reset all error labels and borders
                resetAllUpdateFields();

                boolean isValid = true;

                // Validate inputs
                if (!ValidationUtil.isValidTransactionId(transactionId, lblTransactionErrorMsg)) {
                    isValid = false;
                    highlightInvalidField(txtTfldransactionId, "Transaction ID", Color.RED);
                }

                if (!ValidationUtil.isValidUserId(userId, lblUserIDErrorMsg)) {
                    isValid = false;
                    highlightInvalidField(txtfldUserId, "User ID", Color.RED);
                }

                if (!ValidationUtil.isValidNumericValue(energyUnits, lblErrorEnergyUnits)) {
                    isValid = false;
                    highlightInvalidField(txtfldEnergyUnits, "Energy Units", Color.RED);
                }

                if (!ValidationUtil.isValidNumericValue(paymentAmount, lblErrorPaymentAmount)) {
                    isValid = false;
                    highlightInvalidField(txtfldPaymentAmount, "Payment Amount", Color.RED);
                }

                if (!ValidationUtil.isValidLocation(location, lblLocationErrror)) {
                    isValid = false;
                    highlightInvalidField(txtfldLocation, "Location", Color.RED);
                }

                if (isValid) {
                    // Update transaction data in the table
                    DefaultTableModel model = (DefaultTableModel) tblEnergyTransactions.getModel();
                    model.setValueAt(transactionId, selectedRow, 0);
                    model.setValueAt(userId, selectedRow, 1);
                    model.setValueAt(energyUnits, selectedRow, 2);
                    model.setValueAt(paymentAmount, selectedRow, 3);
                    model.setValueAt(location, selectedRow, 4);
                    model.setValueAt(tokenType, selectedRow, 5);
                    model.setValueAt(energySource, selectedRow, 6);
                    model.setValueAt(energyType, selectedRow, 7);

                    lblMessage.setText("Transaction data updated successfully.");
                    lblMessage.setForeground(Color.GREEN);
                } else {
                    lblMessage.setText("Invalid input. Please correct the highlighted fields.");
                    lblMessage.setForeground(Color.RED);
                }
            }
        } else {
            lblMessage.setText("No transaction selected. Please select a transaction to update.");
            lblMessage.setForeground(Color.ORANGE);
        }
    }//GEN-LAST:event_btnUpdateRecordsActionPerformed

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        int res = JOptionPane.showConfirmDialog(this, "Do you want to clear all fields?", "Confirm Clear", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            txtTfldransactionId.setText("");
            txtfldUserId.setText("");
            txtfldEnergyUnits.setText("");
            txtfldPaymentAmount.setText("");
            txtfldLocation.setText("");
            cmbTokenType.setSelectedIndex(0);
            cmbEnergySource.setSelectedIndex(0);
            cmbEnergy.setSelectedIndex(0);
            lblMessage.setText("");
        }
    }//GEN-LAST:event_btnClearFormActionPerformed

    private void btnRemoveRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveRecordsActionPerformed
        String transactionIdToRemove = txtTfldransactionId.getText();
        DefaultTableModel model = (DefaultTableModel) tblEnergyTransactions.getModel();

        // Show confirmation dialog
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Do you really want to delete transaction ID: " + transactionIdToRemove + "?",
                "Delete Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        // Proceed only if the user confirms deletion
        if (confirmation == JOptionPane.YES_OPTION) {
            boolean recordFound = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 0).equals(transactionIdToRemove)) {
                    model.removeRow(i);
                    recordFound = true;
                    JOptionPane.showMessageDialog(this, "Transaction ID " + transactionIdToRemove + " has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }

            if (!recordFound) {
                JOptionPane.showMessageDialog(this, "Transaction ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRemoveRecordsActionPerformed

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        String selectedField = cmbSortingBy.getSelectedItem().toString();  // For selecting Transaction ID, EnergyUnits, or Location
        String selectedOrder = cmbSortingIn.getSelectedItem().toString();  // For selecting Ascending/Descending

        // Check if a valid field is selected
        if (selectedField.equals("Select Field")) {
            JOptionPane.showMessageDialog(this, "Please select a valid field for sorting (Transaction ID, Location, EnergyUnits).", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if no field is selected
        }

        // Determine the sort order: true for descending, false for ascending
        boolean isDesc = selectedOrder.equals("Descending");

        // Initialize the sorted list variable
        List<EnergyTransactionModel> sortedList = null;

        // Sort the data based on the selected field
        if (selectedField.equals("Energy Units")) {
            // Use SelectionSort for Energy Units
            SelectionSort selectionSort = new SelectionSort();
            sortedList = selectionSort.sortByEnergyUnits(energyTransactions, isDesc);
        } else if (selectedField.equals("Transaction ID")) {
            // Use InsertionSort for Transaction ID
            InsertionSort insertionSort = new InsertionSort();
            sortedList = insertionSort.sortByTransactonID(energyTransactions, isDesc);
        } else if (selectedField.equals("Location")) {
            // Use MergeSort for Location
            MergeSort mergeSort = new MergeSort();
            sortedList = mergeSort.sortByLocation(energyTransactions, isDesc);
        }

        // Check if sortedList is not null before loading it into the table
        if (sortedList != null && !sortedList.isEmpty()) {
            loadListToTable(sortedList);
        } else {
            JOptionPane.showMessageDialog(this, "No data available for sorting.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSortActionPerformed

    private void cmbSortingInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSortingInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSortingInActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // Get user inputs
        String searchValue = txtFldSearchBar.getText().trim();
        String selectedColumn = cmbSortingBy.getSelectedItem().toString();

        // Validate input
        if (searchValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a value to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BinarySearch binarySearch = new BinarySearch();

        // Sort the transaction list based on the selected column
        sortTransactionList(selectedColumn);

        // Perform binary search
        EnergyTransactionModel result = binarySearch.searchByField(searchValue, selectedColumn, energyTransactions, 0, energyTransactions.size() - 1);

        // Display result
        showSearchResult(result);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cmbSortingByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSortingByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSortingByActionPerformed

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
            java.util.logging.Logger.getLogger(EnergyManagementApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnergyManagementApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnergyManagementApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnergyManagementApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnergyManagementApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRecordTransaction;
    private javax.swing.JButton btnRemoveRecords;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSort;
    private javax.swing.JButton btnUpdateRecords;
    private javax.swing.JComboBox<String> cmbEnergy;
    private javax.swing.JComboBox<String> cmbEnergySource;
    private javax.swing.JComboBox<String> cmbSortingBy;
    private javax.swing.JComboBox<String> cmbSortingIn;
    private javax.swing.JComboBox<String> cmbTokenType;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackgroundImage;
    private javax.swing.JLabel lblChillGuy;
    private javax.swing.JLabel lblEnerChain;
    private javax.swing.JLabel lblEnerCoinWhitepaper;
    private javax.swing.JLabel lblErrorEnergyUnits;
    private javax.swing.JLabel lblErrorPaymentAmount;
    private javax.swing.JLabel lblEth;
    private javax.swing.JLabel lblForgotPassword;
    private javax.swing.JLabel lblHomeSubSlogan;
    private javax.swing.JLabel lblLeftLOgo;
    private javax.swing.JLabel lblLocationErrror;
    private javax.swing.JLabel lblLoginError;
    private javax.swing.JLabel lblLoginWithCredentials;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoEnerChain;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSlogan1;
    private javax.swing.JLabel lblSlogan2;
    private javax.swing.JLabel lblSolana;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatusPercentage;
    private javax.swing.JLabel lblTableHeading;
    private javax.swing.JLabel lblTransactionErrorMsg;
    private javax.swing.JLabel lblUserIDErrorMsg;
    private javax.swing.JPanel pnlAboutUs;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlHomeScreen;
    private javax.swing.JPanel pnlLeftLoginScreen;
    private javax.swing.JPanel pnlLoadingScreen;
    private javax.swing.JPanel pnlLoginScreen;
    private javax.swing.JPanel pnlMainHomeScreen;
    private javax.swing.JPanel pnlWhitepaperScreen;
    private javax.swing.JProgressBar prgsbarLoading;
    private javax.swing.JPasswordField pwdFldLogin;
    private javax.swing.JTabbedPane tabbedPaneMain;
    private javax.swing.JTable tblEnergyTransactions;
    private javax.swing.JTextField txtFieldLOginUsername;
    private javax.swing.JTextField txtFldSearchBar;
    private javax.swing.JTextField txtTfldransactionId;
    private javax.swing.JTextField txtfldEnergyUnits;
    private javax.swing.JTextField txtfldLocation;
    private javax.swing.JTextField txtfldPaymentAmount;
    private javax.swing.JTextField txtfldUserId;
    // End of variables declaration//GEN-END:variables
}
