package main;

import inputs.MouseInputs;

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
    private JButton closeCreateCardMenu;
    private JButton submitCard;

    private JLabel cardsLabel;
    private static JLabel pinLabel;
    private static JLabel cardTypeLabel;
    private static JLabel cardNicknameLabel;
    private static JLabel createCardLabel;
    private JComboBox<String> cardType;
    private static JPanel newCard;
    private String[] cardTypes;
    private static JTextField customPinField;
    private static JTextField cardNickname;
    private JCheckBox customPin;

    public MainPage(Window window) {
        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        this.font = new Font("Arial", Font.BOLD, 30);
        this.cards = new JButton("Cards");
        this.taxPayments = new JButton("Tax Payments");
        this.transactions = new JButton("Transactions");
        this.accServBtn = new JButton("Account");
        this.addCard = new JButton("+");
        this.closeCreateCardMenu = new JButton("X");
        this.submitCard = new JButton("Submit");

        this.cardsPanel = new JPanel();
        this.transactionsPanel = new JPanel();
        this.taxPaymentsPanel = new JPanel();
        this.newCard = new JPanel();
        this.infoPanel = new JPanel();

        this.cardsLabel = new JLabel("Your Cards");
        this.cardTypeLabel = new JLabel("Card Type");
        this.pinLabel = new JLabel("PIN (4 digits)");
        this.cardNicknameLabel = new JLabel("Card Name");
        this.createCardLabel = new JLabel("Create a Card");

        this.cardTypes = new String[] {"Visa", "MasterCard"};
        this.cardType = new JComboBox<>(cardTypes);
        this.customPin = new JCheckBox("Custom pin");
        this.customPinField = new JTextField();
        this.cardNickname = new JTextField();

        addMouseListener(mouseInputs);

        requestFocus();
        cardsPanel.setLayout(null);
        newCard.setLayout(null);

        setLayout(null);
        this.newCard.setLayout(null);

        this.infoPanel.setBackground(new Color(13, 17, 37));
        this.infoPanel.setBounds(340, 0, 1280 - 340, 685);

        this.cardsPanel.setBackground(new Color(13, 17, 37));
        this.cardsPanel.setBounds(340, 10, 1280 - 340, 685);

        this.addCard.setBackground(new Color(50, 75, 178));
        this.addCard.setBounds(50, 50, 50, 50);
        this.addCard.setForeground(Color.WHITE);

        this.closeCreateCardMenu.setBackground(new Color(50, 75, 178));
        this.closeCreateCardMenu.setBounds(545, 10, 45, 45);
        this.closeCreateCardMenu.setForeground(Color.WHITE);

        this.submitCard.setBackground(new Color(50, 75, 178));
        this.submitCard.setBounds(300, 120, 90, 45);
        this.submitCard.setForeground(Color.WHITE);

        this.cardsLabel.setBounds(340, 0, 300, 30);
        this.cardsLabel.setForeground(Color.WHITE);
        this.cardsLabel.setFont(font);

        this.cards.setBackground(new Color(81, 200, 120));
        this.cards.setBounds(20, 80, 200, 60);
        this.cards.setForeground(Color.WHITE);

        this.cardType.setBounds(50, 115, 140, 20);

        this.newCard.setBounds(500, 350, 600, 400);
        this.newCard.setBackground(new Color(13, 17, 23));

        this.customPin.setBounds(47, 140, 150, 30);
        this.customPin.setBackground(new Color(13, 17, 23));
        this.customPin.setForeground(Color.WHITE);

        this.accServBtn.setBackground(new Color(81, 200, 120));
        this.accServBtn.setBounds(20, 10, 200, 60);
        this.accServBtn.setForeground(Color.WHITE);

        this.taxPayments.setBackground(new Color(81, 200, 120));
        this.taxPayments.setBounds(20, 220, 200, 60);
        this.taxPayments.setForeground(Color.WHITE);

        this.transactions.setBackground(new Color(81, 200, 120));
        this.transactions.setBounds(20, 150, 200, 60);
        this.transactions.setForeground(Color.WHITE);

        this.customPinField.setBounds(50 , 190, 150, 30);
        this.customPinField.setVisible(false);

        this.pinLabel.setBounds(50, 169, 150, 20);
        this.pinLabel.setFont(new Font("Arial", Font.BOLD, 17));
        this.pinLabel.setForeground(Color.WHITE);
        this.pinLabel.setVisible(false);

        this.cardNicknameLabel.setBounds(50, 39, 150, 20);
        this.cardNicknameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.cardNicknameLabel.setForeground(Color.WHITE);

        this.cardNickname.setBounds(50 , 60, 150, 30);

        this.createCardLabel.setBounds(220, 10, 200, 20);
        this.createCardLabel.setFont(new Font("Arial", Font.BOLD, 23));
        this.createCardLabel.setForeground(Color.WHITE);

        this.cardTypeLabel.setBounds(50, 94, 150, 20);
        this.cardTypeLabel.setFont(new Font("Arial", Font.BOLD, 17));
        this.cardTypeLabel.setForeground(Color.WHITE);

//        this.infoPanel.setVisible(false);

        this.cardsPanel.add(cardsLabel);
        this.cardsPanel.add(addCard);

        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));

        this.font = new Font("Arial", Font.BOLD, 30);

        this.accServ = new JLabel("Account Services");
        this.CardsMenuLabel = new JLabel("Cards");

//        Putting in the AccServ Label
        this.accServ.setFont(font);
        this.accServ.setForeground(Color.WHITE);

//        Putting in the CardsMenuLabel
        this.CardsMenuLabel.setFont(font);
        this.CardsMenuLabel.setForeground(Color.WHITE);


        this.newCard.add(cardType);
        this.newCard.add(customPin);
        this.newCard.add(customPinField);
        this.newCard.add(cardNickname);
        this.newCard.add(cardNicknameLabel);
        this.newCard.add(createCardLabel);
        this.newCard.add(pinLabel);
        this.newCard.add(closeCreateCardMenu);
        this.newCard.add(submitCard);
        this.newCard.add(cardTypeLabel);
        this.newCard.setVisible(false);
        add(this.newCard);
        add(this.infoPanel);
        add(this.cardsPanel);
        add(this.accServBtn);
        add(this.taxPayments);
        add(this.transactions);
        add(this.cards);

        accServBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccountServicesPanel();
                removeCardsMenuPanel();
            }
        });

        cards.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               removeAccountServicesPanel();
               addCardsMenuLPanel();
           }
        });

        addCard.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               newCard();
           }
        });

        customPin.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(customPin.isSelected()){
                    setVisibilityPinField(true);
                } else {
                    setVisibilityPinField(false);
                }
            }
        });

        closeCreateCardMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newCard.setVisible(false);
            }
        });

        submitCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
//
    }

    public static void AccountServicesPanel() {
        infoPanel.setVisible(true);
    }

    public static void removeAccountServicesPanel() {
        infoPanel.setVisible(false);
    }

    public static void addCardsMenuLPanel() {cardsPanel.setVisible(true);};

    public static void removeCardsMenuPanel() {cardsPanel.setVisible(false);}

    public static void setVisibilityPinField(Boolean value) {
        customPinField.setVisible(value);
        pinLabel.setVisible(value);
    }

    public static void newCard() {
        newCard.setVisible(true);
    }
}