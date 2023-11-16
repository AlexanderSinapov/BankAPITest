package main;

import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private static JTextField companyName;
    private static JCheckBox companyInvoice;
    private String[] taxTypes;

    public MainPage(Window window) {
        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        this.font = new Font("Arial", Font.BOLD, 30);
        this.cards = new JButton("Cards");
        this.taxPayments = new JButton("Invoices");
        this.transactions = new JButton("Transactions");
        this.accServBtn = new JButton("Account");
        this.addCard = new JButton("+");
        this.taxPaymentsBtn = new JButton("+");

        this.cardsPanel = new JPanel();
        this.transactionsPanel = new JPanel();
        this.taxPaymentsPanel = new JPanel();
        this.newCard = new JPanel();
        this.infoPanel = new JPanel();
        this.newInvoice = new JPanel();

        this.cardsLabel = new JLabel("Your Cards");

        this.cardTypes = new String[] {"Visa", "MasterCard"};
        this.cardType = new JComboBox<>(cardTypes);
        this.customPin = new JCheckBox("Custom pin");

        this.taxTypes = new String[] {"Water Supply", "Power Supply", "Internet", "Television", "Heat & Hot Water"};
        this.invoiceTo = new JComboBox<>(taxTypes);
        this.companyInvoice = new JCheckBox("I want a private company invoice");
        this.taxPaymentsL = new JLabel("Invoices");
        this.companyName = new JTextField();

        addMouseListener(mouseInputs);

        requestFocus();
        cardsPanel.setLayout(null);
        newCard.setLayout(null);
        newInvoice.setLayout(null);

        setLayout(null);
        this.newCard.setLayout(null);

        this.infoPanel.setBackground(new Color(13, 17, 37));
        this.infoPanel.setBounds(340, 0, 1280 - 340, 685);

        this.cardsPanel.setBackground(new Color(13, 17, 37));
        this.cardsPanel.setBounds(340, 10, 1280 - 340, 685);

        this.addCard.setBackground(new Color(50, 75, 178));
        this.addCard.setBounds(50, 50, 50, 50);
        this.addCard.setForeground(Color.WHITE);

        this.cardsLabel.setBounds(340, 0, 300, 30);
        this.cardsLabel.setForeground(Color.WHITE);
        this.cardsLabel.setFont(font);

        this.cards.setBackground(new Color(81, 200, 120));
        this.cards.setBounds(20, 80, 200, 60);
        this.cards.setForeground(Color.WHITE);

        this.cardType.setBounds(50, 50, 140, 20);

        this.newCard.setBounds(500, 350, 600, 400);
        this.newCard.setBackground(new Color(13, 17, 23));

        this.customPin.setBounds(50, 100, 150, 30);
        this.customPin.setBackground(new Color(13, 17, 23));
        this.customPin.setForeground(Color.WHITE);

        this.accServBtn.setBackground(new Color(81, 200, 120));
        this.accServBtn.setBounds(20, 10, 200, 60);
        this.accServBtn.setForeground(Color.WHITE);

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

        this.taxPaymentsL.setBounds(340, 0, 300, 30);
        this.taxPaymentsL.setFont(font);

        this.companyInvoice.setBounds(50, 80, 200, 20);
        this.companyInvoice.setBackground(new Color(13, 17, 23));
        this.companyInvoice.setForeground(Color.WHITE);

        this.companyName.setBounds(50, 110, 200, 30);
        this.companyName.setBackground(new Color(13, 17, 23));
        this.companyName.setForeground(Color.WHITE);

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


        this.newCard.add(cardType);
        this.newCard.add(customPin);
        this.newCard.setVisible(false);
        this.taxPaymentsPanel.add(taxPaymentsBtn);
        this.newInvoice.add(invoiceTo);
        this.taxPaymentsPanel.add(taxPaymentsL);
        this.newInvoice.add(companyInvoice);
        this.newInvoice.add(companyName);
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
            }
        });

        cards.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               AccountServicesPanel(false);
               TaxPaymentsV(false);
               addCardsMenuLPanel(true);
           }
        });

        addCard.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               newCard();
           }
        });

        taxPayments.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
                AccountServicesPanel(false);
                addCardsMenuLPanel(false);
                TaxPaymentsV(true);
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
//
    }

    public static void AccountServicesPanel(Boolean bool) {
        infoPanel.setVisible(bool);
    }

    public static void addCardsMenuLPanel(Boolean bool) {cardsPanel.setVisible(bool);};

    public static void TaxPaymentsV(Boolean bool) {taxPaymentsPanel.setVisible(bool);}
    public static void NewInvoiceV(Boolean bool) {newInvoice.setVisible(bool);}

    public static void newCard() {
        newCard.setVisible(true);
    }
    public static void newInvoice(Boolean bool) {
        taxPaymentsBtn.setVisible(bool);
    }

    public static void companyInvoice(Boolean bool) {
        companyName.setVisible(bool);
    }
}