package main;

import inputs.MouseInputs;
import utils.DBUtils;


import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainPage extends JPanel {
    private Window window;
    private MouseInputs mouseInputs;
    private Font font;
    private static JPanel infoPanel;
    private static JPanel cardsPanel;
    private static JPanel transactionsPanel;
    private static JPanel taxPaymentsPanel;
    private static JLabel accServ;
    private static JLabel CardsMenuLabel;
    private JButton accServBtn;
    private JButton transactions;
    private JButton taxPayments;
    private JButton cards;
    private JButton addCard;

    private JLabel cardsLabel;
    private JComboBox<String> cardType;
    private static JPanel newCard;
    private String[] cardTypes;
    private JCheckBox customPin;

//    Setting up the Tax Payments UI
    private JLabel taxPaymentsL;
    private static JButton taxPaymentsBtn;
    private static JPanel newInvoice;
    private JComboBox invoiceTo;
    private static JLabel NewCardL;
    private static JLabel CardTypeL;
    private static JLabel CustomPinL;
    private static JTextField CustomPinF;
    private static JLabel NicknameL;
    private static JTextField NicknameF;
    private static JButton NewCardSubmit;
    private static JTextField companyName;
    private static JCheckBox companyInvoice;
    private String[] taxTypes;
    private static JTextField cardNumber;
    private static JPanel invoiceInfo;
    private static JButton invoiceSubmit;
//    Setting up the account page
    private static JButton ViewProfile;
    private JLabel EmailLabel;
    private JLabel PassLabel;
    private JLabel FullNameLabel;
    private JLabel NumberOfCards;
    private JLabel PN;

    public MainPage(Window window) {
//        Setting variable values
        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        this.font = new Font("Arial", Font.BOLD, 30);
        this.cards = new JButton("Cards");
        this.taxPayments = new JButton("Invoices");
        this.transactions = new JButton("Transactions");
        this.accServBtn = new JButton("Account");
        this.addCard = new JButton("+");
        this.taxPaymentsBtn = new JButton("+");
        this.invoiceSubmit = new JButton("Submit");
        this.NewCardL = new JLabel("New Card");
        this.CardTypeL = new JLabel("Card Type");
        this.CustomPinL = new JLabel("Custom Pin (4 digits)");
        this.NicknameL = new JLabel("Card Nickname");
        this.NicknameF = new JTextField();
        this.NewCardSubmit = new JButton("Submit");


        this.cardsPanel = new JPanel();
        this.transactionsPanel = new JPanel();
        this.taxPaymentsPanel = new JPanel();
        this.newCard = new JPanel();
        this.infoPanel = new JPanel();
        this.newInvoice = new JPanel();
        this.invoiceInfo = new JPanel();

        this.cardsLabel = new JLabel("Your Cards");

        this.cardTypes = new String[] {"Visa", "MasterCard"};
        this.cardType = new JComboBox<>(cardTypes);
        this.customPin = new JCheckBox("Custom pin");

        this.taxTypes = new String[] {"Water Supply", "Power Supply", "Internet", "Television", "Heat & Hot Water"};
        this.invoiceTo = new JComboBox<>(taxTypes);
        this.companyInvoice = new JCheckBox("I want a private company invoice");
        this.taxPaymentsL = new JLabel("Invoices");
        this.companyName = new JTextField();
        this.cardNumber = new JTextField();
        this.CustomPinF = new JTextField();

        addMouseListener(mouseInputs);

        requestFocus();
        cardsPanel.setLayout(null);
        newCard.setLayout(null);
        newInvoice.setLayout(null);

        setLayout(null);
        this.newCard.setLayout(null);

        this.infoPanel.setBackground(new Color(13, 17, 37));
        this.infoPanel.setBounds(340, 0, 1280 - 340, 685);

//        Setting up the CardCreationUI
        this.cardsPanel.setBackground(new Color(13, 17, 37));
        this.cardsPanel.setBounds(340, 0, 1280 - 340, 685);

        this.addCard.setBackground(new Color(50, 75, 178));
        this.addCard.setBounds(50, 50, 50, 50);
        this.addCard.setForeground(Color.WHITE);

        this.NewCardSubmit.setBackground(new Color(50, 75, 178));
        this.NewCardSubmit.setBounds(220,190,150,50);
        this.NewCardSubmit.setForeground(Color.WHITE);

        this.cardsLabel.setBounds(340, 10, 300, 30);
        this.cardsLabel.setForeground(Color.WHITE);
        this.cardsLabel.setFont(font);

        this.cards.setBackground(new Color(81, 200, 120));
        this.cards.setBounds(20, 80, 200, 60);
        this.cards.setForeground(Color.WHITE);

        this.cardType.setBounds(50, 70, 140, 20);
        this.cardType.setBackground(new Color(18, 25, 33));
        this.cardType.setForeground(Color.WHITE);

        this.newCard.setBounds(500, 350, 600, 400);
        this.newCard.setBackground(new Color(13, 17, 23));

        this.customPin.setBounds(46, 90, 150, 30);
        this.customPin.setBackground(new Color(13, 17, 23));
        this.customPin.setForeground(Color.WHITE);

        this.CustomPinL.setFont(new Font("Arial", Font.BOLD, 20));
        this.CustomPinL.setBounds(46,115, 200, 35);
        this.CustomPinL.setForeground(Color.WHITE);
        this.CustomPinL.setVisible(false);

        this.CustomPinF.setBounds(46,150,150, 30);
        this.CustomPinF.setBackground(new Color(18, 25, 33));
        this.CustomPinF.setBorder(null);
        this.CustomPinF.setForeground(Color.WHITE);
        this.CustomPinF.setVisible(false);

        this.accServBtn.setBackground(new Color(81, 200, 120));
        this.accServBtn.setBounds(20, 10, 200, 60);
        this.accServBtn.setForeground(Color.WHITE);

//        Setting up the InvoiceUI
        this.taxPayments.setBackground(new Color(81, 200, 120));
        this.taxPayments.setBounds(20, 220, 200, 60);
        this.taxPayments.setForeground(Color.WHITE);

        this.newInvoice.setBounds(500, 350, 600, 400);
        this.newInvoice.setBackground(new Color(13, 17, 23));

        this.taxPaymentsBtn.setBackground(new Color(50, 75, 178));
        this.taxPaymentsBtn.setBounds(50, 50, 50, 50);
        this.taxPaymentsBtn.setForeground(Color.WHITE);

        this.taxPaymentsPanel.setBackground(new Color(13, 17, 37));
        this.taxPaymentsPanel.setBounds(340, 0, 1280 - 340, 685);

        this.invoiceTo.setBounds(50, 50, 140, 20);

        this.taxPaymentsL.setBounds(350, 10, 300, 30);
        this.taxPaymentsL.setForeground(Color.white);
        this.taxPaymentsL.setFont(font);

        this.CardTypeL.setFont(new Font("Arial", Font.BOLD, 20));
        this.CardTypeL.setBounds(50,40,100,30);
        this.CardTypeL.setForeground(Color.WHITE);

        this.NicknameL.setFont(new Font("Arial", Font.BOLD, 20));
        this.NicknameL.setBounds(250, 40, 180,30);
        this.NicknameL.setForeground(Color.WHITE);

        this.NicknameF.setBounds(250,66, 150, 30);
        this.NicknameF.setBackground(new Color(18, 25, 33));
        this.NicknameF.setBorder(null);
        this.NicknameF.setForeground(Color.WHITE);

        this.NewCardL.setFont(font);
        this.NewCardL.setBounds(220,10,150,40);
        this.NewCardL.setForeground(Color.WHITE);

        this.companyInvoice.setBounds(50, 80, 200, 20);
        this.companyInvoice.setBackground(new Color(13, 17, 23));
        this.companyInvoice.setForeground(Color.WHITE);

        this.companyName.setBounds(50, 110, 200, 30);
        this.companyName.setBackground(new Color(13, 17, 23));
        this.companyName.setForeground(Color.WHITE);

        this.cardNumber.setBounds(50, 150, 200, 30);
        this.cardNumber.setBackground(new Color(13, 17, 23));
        this.cardNumber.setForeground(Color.WHITE);

        this.invoiceSubmit.setBounds(350, 290, 100, 40);
        this.invoiceSubmit.setBackground(new Color(50, 75, 175));
        this.invoiceSubmit.setForeground(Color.WHITE);

        this.transactions.setBackground(new Color(81, 200, 120));
        this.transactions.setBounds(20, 150, 200, 60);
        this.transactions.setForeground(Color.WHITE);

//        this.infoPanel.setVisible(false);

        this.cardsPanel.add(cardsLabel);
        this.cardsPanel.add(addCard);

        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));

        this.font = new Font("Arial", Font.BOLD, 30);

        this.accServ = new JLabel("Account Services");
        this.CardsMenuLabel = new JLabel("Cards");

//        Puttin in the AccServ Label
        this.accServ.setFont(font);
        this.accServ.setForeground(Color.WHITE);

//        Putting in the CardsMenuLabel
        this.CardsMenuLabel.setFont(font);
        this.CardsMenuLabel.setForeground(Color.WHITE);

        this.taxPaymentsPanel.setVisible(false);
//        this.taxPaymentsBtn.setVisible(false);
        this.newInvoice.setVisible(false);

        this.taxPaymentsPanel.setLayout(null);
        this.companyName.setVisible(false);
        this.cardNumber.setVisible(false);


        this.newCard.add(cardType);
        this.newCard.add(NicknameL);
        this.newCard.add(NicknameF);
        this.newCard.add(CardTypeL);
        this.newCard.add(NewCardL);
        this.newCard.add(NewCardSubmit);
        this.newCard.add(customPin);
        this.newCard.add(CustomPinL);
        this.newCard.add(CustomPinF);
        this.newCard.setVisible(false);
        this.taxPaymentsPanel.add(taxPaymentsBtn);
        this.newInvoice.add(invoiceTo);
        this.taxPaymentsPanel.add(taxPaymentsL);
        this.newInvoice.add(companyInvoice);
        this.newInvoice.add(companyName);
        this.newInvoice.add(cardNumber);
        this.newInvoice.add(invoiceSubmit);
        this.infoPanel.add(accServ);
        add(this.newCard);
        add(this.infoPanel);
        add(this.cardsPanel);
        add(this.newInvoice);
        add(this.taxPaymentsPanel);
        add(this.accServBtn);
        add(this.taxPayments);
        add(this.transactions);
        add(this.cards);

        accServBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccountServicesPanel(true);
                TaxPaymentsV(false);
                addCardsMenuLPanel(false);
                newCard(false);
                NewInvoiceV(false);
            }
        });

        NewCardSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    newCard.setVisible(false);
            }
        });

        NewCardSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(DBUtils.RequestNewCard(cardType.getSelectedItem().toString(), NicknameF.getText(), CustomPinF.getText())){
                    newCard.setVisible(false);
                    System.out.println("New Card Added");
                } else System.out.println("Action Failed!");
            }
        });

        cards.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               AccountServicesPanel(false);
               TaxPaymentsV(false);
               addCardsMenuLPanel(true);
               NewInvoiceV(false);
           }
        });

        customPin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(customPin.isSelected()){
                    CustomPinF.setVisible(true);
                    CustomPinL.setVisible(true);
                } else {
                    CustomPinF.setVisible(false);
                    CustomPinL.setVisible(false);
                }
            }
        });

        addCard.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               newCard(true);
           }
        });

        taxPayments.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
                AccountServicesPanel(false);
                addCardsMenuLPanel(false);
                TaxPaymentsV(true);
                newCard(false);
           }
        });

        taxPaymentsBtn.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
                NewInvoiceV(true);
           }
        });

        companyInvoice.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if (companyInvoice.isSelected()) {
                   companyInvoice(true);
               } else {
                   companyInvoice(false);
               }
           }
        });

        invoiceSubmit.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               newInvoice.setVisible(false);
           }
        });


//
    }

    public static void AccountServicesPanel(Boolean bool) {
        infoPanel.setVisible(bool);
    }

    public static void addCardsMenuLPanel(Boolean bool) {cardsPanel.setVisible(bool);};

    public static void TaxPaymentsV(Boolean bool) {taxPaymentsPanel.setVisible(bool);}
    public static void NewInvoiceV(Boolean bool) {newInvoice.setVisible(bool);}

    public static void newCard(Boolean bool) {
        newCard.setVisible(bool);
    }
    public static void newInvoice(Boolean bool) {
        taxPaymentsBtn.setVisible(bool);
    }

    public static void companyInvoice(Boolean bool) {
        companyName.setVisible(bool);
        cardNumber.setVisible(bool);
    }

    public static void InvoiceInfo(int x, int y, int width, int height) {
        invoiceInfo.setBounds(x, y, width, height);
        invoiceInfo.setBackground(new Color(13, 17, 32));
        invoiceInfo.setForeground(Color.WHITE);

        taxPaymentsPanel.add(invoiceInfo);
    }
}