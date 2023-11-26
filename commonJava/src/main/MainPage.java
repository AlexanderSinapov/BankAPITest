package main;

import inputs.MouseInputs;
import org.json.JSONObject;
import utils.DBUtils;


import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.util.Objects;

public class MainPage extends JPanel {
    private Font font;
    private static JPanel infoPanel;
    private static JPanel cardsPanel;
    private static JPanel taxPaymentsPanel;
    private static JScrollPane TaxScrollPane;

    private final JComboBox<String> cardType;
    private static JPanel newCard;
    private final JCheckBox customPin;

    private static JButton taxPaymentsBtn;
    private static JPanel newInvoice;
    private static JLabel CustomPinL;
    private static JTextField CustomPinF;
    private static JTextField NicknameF;
    private static JTextField companyName;
    private static JCheckBox companyInvoice;
    private static JTextField cardNumber;
    private static JPanel invoiceInfo;
    //    Setting up the account page
    private static JButton ViewProfile;
    private JLabel EmailLabel;
    private JLabel PassLabel;
    private JLabel FullNameLabel;
    private JLabel NumberOfCards;
    private JLabel PN;
    private static JPanel card;
    private static JLabel cardLabel;
    private static JButton legitCardBtn;
    private static JButton isLegitCardBtn;
    private static JTextField legitCardTF;
    private static JPanel legitCardP;

    private class  CardDetails{
        String CardHolder;
        String ExpDate;
        String CardNick;
        String CardNumber;
        String Balance;
    }

    public MainPage(Window window) {
//        Setting variable values
        MouseInputs mouseInputs = new MouseInputs(window);
        this.font = new Font("Arial", Font.BOLD, 30);
        JButton cards = new JButton("Cards");
        JButton taxPayments = new JButton("Invoices");
        JButton transactions = new JButton("Transactions");
        JButton accServBtn = new JButton("Account");
        JButton addCard = new JButton("+");
        taxPaymentsBtn = new JButton("+");
        JButton invoiceSubmit = new JButton("Submit");
        JLabel newCardL = new JLabel("New Card");
        JLabel cardTypeL = new JLabel("Card Type");
        CustomPinL = new JLabel("Custom Pin (4 digits)");
        JLabel nicknameL = new JLabel("Card Nickname");
        NicknameF = new JTextField();
        JButton newCardSubmit = new JButton("Submit");
        legitCardBtn = new JButton("Check card");
        isLegitCardBtn = new JButton("Check card number");
        legitCardTF = new JTextField();
        //taxPaymentsViewport = new JPanel();


        cardsPanel = new JPanel();
        JPanel transactionsPanel = new JPanel();
        taxPaymentsPanel = new JPanel();
        newCard = new JPanel();
        infoPanel = new JPanel();
        newInvoice = new JPanel();
        invoiceInfo = new JPanel();
        legitCardP = new JPanel();

        JLabel cardsLabel = new JLabel("Your Cards");

        String[] cardTypes = new String[]{"Visa", "MasterCard"};
        this.cardType = new JComboBox<>(cardTypes);
        this.customPin = new JCheckBox("Custom pin");

        String[] taxTypes = new String[]{"Water Supply", "Power Supply", "Internet", "Television", "Heat & Hot Water"};
        JComboBox<String> invoiceTo = new JComboBox<>(taxTypes);
        companyInvoice = new JCheckBox("I want a private company invoice");
        JLabel taxPaymentsL = new JLabel("Invoices");
        companyName = new JTextField();
        cardNumber = new JTextField();
        CustomPinF = new JTextField();

        addMouseListener(mouseInputs);

        requestFocus();
        cardsPanel.setLayout(null);
        newCard.setLayout(null);
        newInvoice.setLayout(null);

        setLayout(null);
        newCard.setLayout(null);

        infoPanel.setBackground(new Color(13, 17, 37));
        infoPanel.setBounds(340, 0, 1280 - 340, 685);

//        Setting up the CardCreationUI
        cardsPanel.setBackground(new Color(13, 17, 37));
        cardsPanel.setBounds(340, 0, 1280 - 340, 685);

        addCard.setBackground(new Color(50, 75, 178));
        addCard.setBounds(50, 50, 50, 50);
        addCard.setForeground(Color.WHITE);

        newCardSubmit.setBackground(new Color(50, 75, 178));
        newCardSubmit.setBounds(220,190,150,50);
        newCardSubmit.setForeground(Color.WHITE);

        cardsLabel.setBounds(340, 10, 300, 30);
        cardsLabel.setForeground(Color.WHITE);
        cardsLabel.setFont(font);

        cards.setBackground(new Color(81, 200, 120));
        cards.setBounds(20, 80, 200, 60);
        cards.setForeground(Color.WHITE);

        this.cardType.setBounds(50, 70, 140, 20);
        this.cardType.setBackground(new Color(18, 25, 33));
        this.cardType.setForeground(Color.WHITE);

        newCard.setBounds(500, 350, 600, 400);
        newCard.setBackground(new Color(13, 17, 23));

        this.customPin.setBounds(46, 90, 150, 30);
        this.customPin.setBackground(new Color(13, 17, 23));
        this.customPin.setForeground(Color.WHITE);

        CustomPinL.setFont(new Font("Arial", Font.BOLD, 20));
        CustomPinL.setBounds(46,115, 200, 35);
        CustomPinL.setForeground(Color.WHITE);
        CustomPinL.setVisible(false);

        CustomPinF.setBounds(46,150,150, 30);
        CustomPinF.setBackground(new Color(18, 25, 33));
        CustomPinF.setBorder(null);
        CustomPinF.setForeground(Color.WHITE);
        CustomPinF.setVisible(false);

        accServBtn.setBackground(new Color(81, 200, 120));
        accServBtn.setBounds(20, 10, 200, 60);
        accServBtn.setForeground(Color.WHITE);

//        Setting up the InvoiceUI
        taxPayments.setBackground(new Color(81, 200, 120));
        taxPayments.setBounds(20, 220, 200, 60);
        taxPayments.setForeground(Color.WHITE);

        newInvoice.setBounds(500, 350, 600, 400);
        newInvoice.setBackground(new Color(13, 17, 23));

        taxPaymentsBtn.setBackground(new Color(50, 75, 178));
        taxPaymentsBtn.setBounds(50, 50, 50, 50);
        taxPaymentsBtn.setForeground(Color.WHITE);

        taxPaymentsPanel.setBackground(new Color(13, 17, 37));
        taxPaymentsPanel.setBounds(340, 0, 1280 - 340, 685);

        invoiceTo.setBounds(50, 50, 140, 20);

        taxPaymentsL.setBounds(350, 10, 300, 30);
        taxPaymentsL.setForeground(Color.white);
        taxPaymentsL.setFont(font);

        cardTypeL.setFont(new Font("Arial", Font.BOLD, 20));
        cardTypeL.setBounds(50,40,100,30);
        cardTypeL.setForeground(Color.WHITE);

        nicknameL.setFont(new Font("Arial", Font.BOLD, 20));
        nicknameL.setBounds(250, 40, 180,30);
        nicknameL.setForeground(Color.WHITE);

        NicknameF.setBounds(250,66, 150, 30);
        NicknameF.setBackground(new Color(18, 25, 33));
        NicknameF.setBorder(null);
        NicknameF.setForeground(Color.WHITE);

        newCardL.setFont(font);
        newCardL.setBounds(220,10,150,40);
        newCardL.setForeground(Color.WHITE);

        companyInvoice.setBounds(50, 80, 200, 20);
        companyInvoice.setBackground(new Color(13, 17, 23));
        companyInvoice.setForeground(Color.WHITE);

        companyName.setBounds(50, 110, 200, 30);
        companyName.setBackground(new Color(13, 17, 23));
        companyName.setForeground(Color.WHITE);

        cardNumber.setBounds(50, 150, 200, 30);
        cardNumber.setBackground(new Color(13, 17, 23));
        cardNumber.setForeground(Color.WHITE);

        invoiceSubmit.setBounds(350, 290, 100, 40);
        invoiceSubmit.setBackground(new Color(50, 75, 175));
        invoiceSubmit.setForeground(Color.WHITE);

        transactions.setBackground(new Color(81, 200, 120));
        transactions.setBounds(20, 150, 200, 60);
        transactions.setForeground(Color.WHITE);

//        Legit Card UI
        legitCardBtn.setBackground(new Color(81, 200, 120));
        legitCardBtn.setBounds(20, 260, 200, 60);
        legitCardBtn.setForeground(Color.WHITE);

//        this.infoPanel.setVisible(false);

        cardsPanel.add(cardsLabel);
        cardsPanel.add(addCard);

        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));

        this.font = new Font("Arial", Font.BOLD, 30);

        JLabel accServ = new JLabel("Account Services");
        JLabel cardsMenuLabel = new JLabel("Cards");

//        Puttin in the AccServ Label
        accServ.setFont(font);
        accServ.setForeground(Color.WHITE);

//        Putting in the CardsMenuLabel
        cardsMenuLabel.setFont(font);
        cardsMenuLabel.setForeground(Color.WHITE);

        taxPaymentsPanel.setVisible(false);
//        this.taxPaymentsBtn.setVisible(false);
        newInvoice.setVisible(false);

        taxPaymentsPanel.setLayout(null);
        companyName.setVisible(false);
        cardNumber.setVisible(false);


        newCard.add(cardType);
        newCard.add(nicknameL);
        newCard.add(NicknameF);
        newCard.add(cardTypeL);
        newCard.add(newCardL);
        newCard.add(newCardSubmit);
        newCard.add(customPin);
        newCard.add(CustomPinL);
        newCard.add(CustomPinF);
        newCard.setVisible(false);
        taxPaymentsPanel.add(taxPaymentsBtn);
        newInvoice.add(invoiceTo);
        taxPaymentsPanel.add(taxPaymentsL);
        newInvoice.add(companyInvoice);
        newInvoice.add(companyName);
        newInvoice.add(cardNumber);
        newInvoice.add(invoiceSubmit);
        infoPanel.add(accServ);
        add(newCard);
        add(infoPanel);
        add(cardsPanel);
        add(newInvoice);
        add(taxPaymentsPanel);
        add(accServBtn);
        add(taxPayments);
        add(transactions);
        add(cards);
        add(legitCardBtn);

        accServBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccountServicesPanel(true);
                TaxPaymentsV(false);
                addCardsMenuLPanel(false);
                newCard(false);
                NewInvoiceV(false);
            }
        });

        newCardSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    newCard.setVisible(false);
            }
        });

        newCardSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(DBUtils.RequestNewCard(Objects.requireNonNull(cardType.getSelectedItem()).toString(), NicknameF.getText(), CustomPinF.getText())){
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
               int height = 100;
               for(int i = 0; i < DBUtils.cards.length() && i < 2; i++, height += 220){
                   JSONObject obj = DBUtils.cards.getJSONObject(i);
                   CardDetails details = new CardDetails();
                   details.CardNumber = (String) obj.get("number");
                   details.CardHolder = (String) obj.get("name");
                   details.ExpDate = obj.get("month") + "/" + obj.get("year");
                   details.CardNick = (String) obj.get("nickname");
                   if(details.CardNumber.startsWith("4")){
                       CardInfo(height, "commonJava/Resources/Images/VisaLogo.png", details);
                   } else CardInfo(height, "commonJava/Resources/Images/MCLogo.png", details);
                   System.out.println(obj.toString());

               }
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
               if(newCard.isVisible()){
                   CustomPinF.setText("");
                   NicknameF.setText("");
                   newCard(false);
               } else newCard(true);

           }
        });

        taxPayments.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
                AccountServicesPanel(false);
                addCardsMenuLPanel(false);
                TaxPaymentsV(true);
                newCard(false);
               int height = 100;
               for(int i = 0; i < 2; i++, height += 80){
                    InvoiceInfo(height, "Water Bill", "18.00");
                    InvoiceInfo(height, "Power Bill", "16.99");

//                   JSONObject obj = DBUtils.cards.getJSONObject(i);
//                   CardDetails details = new CardDetails();
//                   details.CardNumber = (String) obj.get("number");
//                   details.CardHolder = (String) obj.get("name");
//                   details.ExpDate = obj.get("month") + "/" + obj.get("year");
//                   details.CardNick = (String) obj.get("nickname");
//                   if(details.CardNumber.startsWith("4")){
//                       InvoiceInfo(height, "Power Bill");
//                   } else InvoiceInfo(height, "Water Bill");

               }
           }
        });

        taxPaymentsBtn.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
                NewInvoiceV(true);
           }
        });

        companyInvoice.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               companyInvoice(companyInvoice.isSelected());
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

    public void CardInfo(int y, String logoPath, CardDetails details) {
        JLabel nick = new JLabel(details.CardNick);
        JLabel number = new JLabel(details.CardNumber);
        JLabel holder = new JLabel(details.CardHolder);
        JLabel expDate = new JLabel(details.ExpDate);
        JPanel DebitCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon(logoPath).getImage();
                g.drawImage(img, 295, 125, this);
            }
        };

        nick.setBounds(20,20,300,30);
        nick.setFont(new Font("Ariel", Font.BOLD, 20));
        nick.setForeground(Color.WHITE);

        number.setBounds(20,45,180,25);
        number.setFont(new Font("Ariel", Font.BOLD, 16));
        number.setForeground(Color.WHITE);

        holder.setBounds(20,85,200,25);
        holder.setFont(new Font("Ariel", Font.BOLD, 16));
        holder.setForeground(Color.WHITE);

        expDate.setBounds(20,65,120,25);
        expDate.setFont(new Font("Ariel", Font.BOLD, 16));
        expDate.setForeground(Color.WHITE);

        DebitCard.setBounds(220, y, 400, 200);
        DebitCard.setBackground(new Color(20, 26, 57));
        DebitCard.setForeground(Color.WHITE);
        DebitCard.setFont(this.font);

        DebitCard.add(number);
        DebitCard.add(holder);
        DebitCard.add(nick);
        DebitCard.add(expDate);
        DebitCard.setLayout(null);
        cardsPanel.add(DebitCard);
    }

    public void InvoiceInfo(int y, String name, String price) {
        JLabel nick = new JLabel(name);
        JLabel paid = new JLabel(price);
        JPanel DebitCard = new JPanel();

        nick.setBounds(20,20,300,20);
        nick.setFont(new Font("Ariel", Font.BOLD, 20));
        nick.setForeground(Color.WHITE);

        paid.setBounds(300,20,150,20);
        paid.setFont(new Font("Ariel", Font.BOLD, 20));
        paid.setForeground(Color.WHITE);


        //DebitCard.setBounds(220, y, 400, 60);
        DebitCard.setBackground(new Color(20, 26, 57));
        DebitCard.setForeground(Color.WHITE);
        DebitCard.setFont(this.font);

        DebitCard.add(nick);
        DebitCard.add(paid);
        DebitCard.setLayout(null);
        //taxPaymentsViewport.add(DebitCard);
    }
}