package main;

import inputs.MouseInputs;
import org.json.JSONObject;
import utils.DBUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class MainPage extends JPanel {
    private Font font;
    private static JPanel infoPanel;
    public static JPanel cardsPanel;
    private final JComboBox<String> cardType;
    private static JPanel newCard;
    private static JCheckBox customPin;
    private static JLabel CustomPinL;
    private static JTextField CustomPinF;
    private static JTextField NicknameF;
    //    Setting up the account page
    private JLabel EmailLabel;
    private JLabel FullNameLabel;
    private JLabel NumberOfCards;
    private JLabel DateOfBirth;
    private JLabel PN;
    public static Font TopPanelFont = loadFont("commonJava/Resources/Fonts/Roboto-Medium.ttf", Font.BOLD, 30);
    private static JButton addCard;
    private static JTextField legitCardTF;
    private static JPanel legitCardP;

    private static class  CardDetails{
        String CardHolder;
        String ExpDate;
        String CardNick;
        String CardNumber;
        String Balance;
    }

    private static ArrayList<JPanel> cardList = new ArrayList();

    public MainPage(Window window) {
//        Setting variable values
        MouseInputs mouseInputs = new MouseInputs(window);
        this.font = new Font("Arial", Font.BOLD, 30);
        JButton cards = new JButton("Cards");
        JButton taxPayments = new JButton("Invoices");
        JButton accServBtn = new JButton("Account");
        JLabel TopPanelL = new JLabel("Your Cards");
        addCard = new JButton();
        JButton LegitCardB = new JButton("Submit");
        JLabel LegitCardL = new JLabel("Card Number");
        JLabel LegitCardMSG = new JLabel();
        JLabel newCardL = new JLabel("New Card");
        JLabel cardTypeL = new JLabel("Card Type");
        CustomPinL = new JLabel("Custom Pin (4 digits)");
        JLabel nicknameL = new JLabel("Card Nickname");
        NicknameF = new JTextField();
        JButton newCardSubmit = new JButton("Submit");
        JButton legitCardBtn = new JButton("Check card");
        JButton isLegitCardBtn = new JButton("Check card number");
        legitCardTF = new JTextField();
        JButton logOut = new JButton("Logout");

        cardsPanel = new JPanel();
        JPanel topPanel = new JPanel();
        newCard = new JPanel();
        infoPanel = new JPanel();
        legitCardP = new JPanel();

        String[] cardTypes = new String[]{"Visa", "MasterCard"};
        this.cardType = new JComboBox<>(cardTypes);
        customPin = new JCheckBox("Custom pin");
        JTextField cardNumber = new JTextField();
        CustomPinF = new JTextField();

        cardsPanel.setLayout(null);
        newCard.setLayout(null);
        newCard.setLayout(null);
        legitCardP.setLayout(null);
        setLayout(null);

        addMouseListener(mouseInputs);
        requestFocus();

        ;

//        Setting up the CardCreationUI
        cardsPanel.setBackground(new Color(239, 239, 239));
        SetMainPanelBounds(cardsPanel);

        addCard.setBackground(new Color(44, 140, 153));
        addCard.setIcon(new ImageIcon("commonJava/Resources/Images/add_circle_FILL0_wght400_GRAD0_opsz24.png"));
        addCard.setBounds(50, 50, 50, 50);
        addCard.setForeground(Color.WHITE);

        newCardSubmit.setBackground(new Color(44, 140, 153));
        newCardSubmit.setBounds(220,190,150,50);
        newCardSubmit.setForeground(Color.BLACK);

        cards.setBackground(null);
        cards.setBorder(null);
        cards.setIcon(new ImageIcon("commonJava/Resources/Images/credit_card_FILL0_wght400_GRAD0_opsz24.png"));
        cards.setBounds(20, 80, 200, 60);
        cards.setForeground(Color.BLACK);

        this.cardType.setBounds(50, 70, 140, 20);
        this.cardType.setBackground(new Color(239, 239, 239));
        this.cardType.setForeground(Color.BLACK);

        newCard.setBounds(500, 350, 600, 400);
        newCard.setBackground(new Color(255, 255, 255));

        newCardL.setFont(font);
        newCardL.setBounds(220,10,150,40);
        newCardL.setForeground(Color.BLACK);

        customPin.setBounds(46, 90, 150, 30);
        customPin.setBackground(new Color(255, 255, 255));
        customPin.setForeground(Color.BLACK);

        CustomPinL.setFont(new Font("Arial", Font.BOLD, 20));
        CustomPinL.setBounds(46,115, 200, 35);
        CustomPinL.setForeground(Color.BLACK);
        CustomPinL.setVisible(false);

        CustomPinF.setBounds(46,150,150, 30);
        CustomPinF.setBackground(new Color(239, 239, 239));
        CustomPinF.setBorder(null);
        CustomPinF.setForeground(Color.BLACK);
        CustomPinF.setVisible(false);

        cardTypeL.setFont(new Font("Arial", Font.BOLD, 20));
        cardTypeL.setBounds(50,40,100,30);
        cardTypeL.setForeground(Color.BLACK);

        nicknameL.setFont(new Font("Arial", Font.BOLD, 20));
        nicknameL.setBounds(250, 40, 180,30);
        nicknameL.setForeground(Color.BLACK);

        NicknameF.setBounds(250,66, 150, 30);
        NicknameF.setBackground(new Color(239, 239, 239));
        NicknameF.setBorder(null);
        NicknameF.setForeground(Color.BLACK);

        accServBtn.setBackground(null);
        accServBtn.setBorder(null);
        accServBtn.setIcon(new ImageIcon("commonJava/Resources/Images/account_circle_FILL0_wght400_GRAD0_opsz24.png"));
        accServBtn.setBounds(20, 10, 200, 60);
        accServBtn.setForeground(Color.BLACK);

        infoPanel.setBackground(new Color(239, 239, 239));
        SetMainPanelBounds(infoPanel);



//        Legit Card UI
        legitCardBtn.setBackground(null);
        legitCardBtn.setBorder(null);
        legitCardBtn.setIcon(new ImageIcon("commonJava/Resources/Images/credit_score_FILL0_wght400_GRAD0_opsz24.png"));
        legitCardBtn.setBounds(20, 150, 200, 60);
        legitCardBtn.setForeground(Color.BLACK);

        legitCardTF.setBackground(new Color(220, 220, 220));
        legitCardTF.setBounds(300, 60, 300, 30);
        legitCardTF.setBorder(null);
        legitCardTF.setForeground(Color.BLACK);

        cardNumber.setBounds(50, 150, 200, 30);
        cardNumber.setBackground(new Color(13, 17, 23));
        cardNumber.setForeground(Color.WHITE);

        LegitCardL.setFont(new Font("Arial", Font.BOLD, 20));
        LegitCardL.setBounds(300, 30, 200, 30);
        LegitCardL.setForeground(Color.BLACK);

        LegitCardB.setBounds(400, 100, 80, 50);
        LegitCardB.setBackground(new Color(44, 140, 153));
        LegitCardB.setForeground(Color.BLACK);

        LegitCardMSG.setBounds(400, 130, 150, 60);
        LegitCardMSG.setVisible(false);

        SetMainPanelBounds(legitCardP);
        legitCardP.setBackground(new Color(239, 239, 239));
        legitCardP.add(LegitCardL);
        legitCardP.add(LegitCardB);
        legitCardP.add(LegitCardMSG);

        // Setting Log Out button
        logOut.setBackground(null);
        logOut.setBorder(null);
        logOut.setIcon(new ImageIcon("commonJava/Resources/Images/logout_FILL0_wght400_GRAD0_opsz24.png"));
        logOut.setBounds(20, 590, 200, 60);
        logOut.setForeground(Color.BLACK);

        setSize(1280, 720);
        setBackground(new Color(81, 200, 120));

        this.font = new Font("Arial", Font.BOLD, 30);

        JLabel cardsMenuLabel = new JLabel("Cards");

//        Putting in the CardsMenuLabel
        cardsMenuLabel.setFont(font);
        cardsMenuLabel.setForeground(Color.WHITE);

        cardNumber.setVisible(false);

        topPanel.setBounds(240,0, 1280 - 240, 70);
        topPanel.setBackground(new Color(255, 255, 255));
        topPanel.setLayout(null);

        TopPanelL.setBounds(20, 10, 300, 50);
        TopPanelL.setForeground(new Color(0, 0, 0));
        TopPanelL.setFont(TopPanelFont);
        topPanel.add(TopPanelL);

        cards.setHorizontalAlignment(JLabel.LEFT);
        accServBtn.setHorizontalAlignment(JLabel.LEFT);
        legitCardBtn.setHorizontalAlignment(JLabel.LEFT);
        logOut.setHorizontalAlignment(JLabel.LEFT);


        AddMouseListenerButton(cards);
        AddMouseListenerButton(accServBtn);
        AddMouseListenerButton(legitCardBtn);
        AddMouseListenerButton(logOut);

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
        legitCardP.setVisible(false);
        legitCardP.add(legitCardTF);
        legitCardP.add(isLegitCardBtn);
        cardsPanel.add(addCard);
        add(newCard);
        add(infoPanel);
        add(cardsPanel);
        add(accServBtn);
        add(cards);
        add(legitCardBtn);
        add(logOut);
        add(legitCardP);
        add(topPanel);




        accServBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccountServicesPanel(true);
                addCardsMenuLPanel(false);
                newCard(false);
                legitCardP.setVisible(false);
                TopPanelL.setText("Account Details");
            }
        });

        newCardSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    newCard.setVisible(false);
            }
        });

        newCardSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(DBUtils.RequestNewCard(Objects.requireNonNull(cardType.getSelectedItem()).toString(), NicknameF.getText(), CustomPinF.getText(), customPin.isSelected())){
                    newCard.setVisible(false);
                    System.out.println("New Card Added");
                    refreshCardsPanel();
                } else System.out.println("Action Failed!");
            }
        });

        cards.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               Component[] components = cardsPanel.getComponents();
               for (Component component : components) {
                   if (component instanceof JButton && component != addCard) {
                       // Remove all components that are JButton and not the specificButton
                       cardsPanel.remove(component);
                   }
               }
               cardsPanel.revalidate();
               cardsPanel.repaint();
               TopPanelL.setText("Your Cards");
               DBUtils.cards = DBUtils.RequestGetCards();
               AccountServicesPanel(false);
               addCardsMenuLPanel(true);
               legitCardP.setVisible(false);
               int height = 100;
               if(DBUtils.cards != null){
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
                   }
               }
               cardsPanel.revalidate();
               cardsPanel.repaint();
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
                    CustomPinF.setText("");
                }
            }
        });

        addCard.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if(newCard.isVisible()){
                   newCard(false);
                   ResetNewCard();
               } else newCard(true);

           }
        });

        taxPayments.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
                AccountServicesPanel(false);
                addCardsMenuLPanel(false);
                newCard(false);
                legitCardP.setVisible(false);
           }
        });

        legitCardBtn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               legitCardP.setVisible(true);
               AccountServicesPanel(false);
               addCardsMenuLPanel(false);
               newCard(false);
               TopPanelL.setText("Check Card");
               LegitCardMSG.setVisible(false);
               legitCardTF.setText("");
           }
        });

        logOut.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
//               setVisible(false);
               window.getWindowFrame().removeMainPage();
               window.getWindowFrame().loginPage.setVisible(true);
           }
        });

        LegitCardB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(DBUtils.RequestCheckCard(legitCardTF.getText())){
                    LegitCardMSG.setText("Card is valid!");
                    LegitCardMSG.setForeground(Color.GREEN);
                    LegitCardMSG.setVisible(true);
                } else {
                    LegitCardMSG.setText("Card is invalid!");
                    LegitCardMSG.setForeground(Color.RED);
                    LegitCardMSG.setVisible(true);
                }
            }
        });


//
    }

    public static void AccountServicesPanel(Boolean bool) {
        infoPanel.setVisible(bool);
    }

    public static void addCardsMenuLPanel(Boolean bool) {cardsPanel.setVisible(bool);};

    public static void newCard(Boolean bool) {
        newCard.setVisible(bool);
    }

    public void CardInfo(int y, String logoPath, CardDetails details) {
        JLabel nick = new JLabel(details.CardNick);
        JLabel number = new JLabel(details.CardNumber);
        JLabel holder = new JLabel(details.CardHolder);
        JLabel expDate = new JLabel(details.ExpDate);
        JButton removeCard = new JButton();
        JPanel DebitCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon(logoPath).getImage();
                g.drawImage(img, 295, 125, this);
                System.out.println("Card added");
                System.out.println(cardList);
            }
        };

        nick.setBounds(20,20,300,30);
        nick.setFont(new Font("Ariel", Font.BOLD, 20));
        nick.setForeground(Color.WHITE);

        removeCard.setBounds(360, 10, 30, 30);
        removeCard.setIcon(new ImageIcon("commonJava/Resources/Images/do_not_disturb_on_FILL0_wght400_GRAD0_opsz24.png"));
        removeCard.setBackground(null);

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
        DebitCard.setBackground(new Color(13, 17, 23));
        DebitCard.setForeground(Color.WHITE);
        DebitCard.setFont(this.font);

        JPanel finalDebitCard = DebitCard;
        removeCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(DBUtils.RequestCloseCard(details.CardNumber)){
                    cardsPanel.remove(DebitCard);
                    cardList.remove(DebitCard); // Remove from the list
//                    cardsPanel.revalidate();
                    cardsPanel.repaint();
                    System.out.println(cardList);
                    System.out.println("Card removed");
                }
            }
        });

        DebitCard.add(number);
        DebitCard.add(removeCard);
        DebitCard.add(holder);
        DebitCard.add(nick);
        DebitCard.add(expDate); 
        DebitCard.setLayout(null);
        cardsPanel.add(DebitCard);

        cardList.add(DebitCard);  // Add the card to the list
//        cardsPanel.add(DebitCard);
    }

    public static void AddMouseListenerButton(JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(89, 222, 132)); // Set text color when mouse over
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.BLACK); // Restore default text color
                button.setBackground(null); // Restore default text color
            }
        });
    }

    public static void SetMainPanelBounds(JPanel panel){
        panel.setBounds(240, 70, 1280 - 240, 685);
    }

    public static Font loadFont(String path, int style, int size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new java.io.File(path)).deriveFont(style, size);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void refreshCardsPanel(){
        Component[] components = cardsPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton && component != addCard) {
                // Remove all components that are JButton and not the specificButton
                cardsPanel.remove(component);
            }
        }
        cardsPanel.revalidate();
        cardsPanel.repaint();
        DBUtils.cards = DBUtils.RequestGetCards();
        int height = 100;
        if(DBUtils.cards != null){
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
            }
        }
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private static void ResetNewCard(){
        CustomPinF.setVisible(false);
        CustomPinL.setVisible(false);
        NicknameF.setText("");
        customPin.setSelected(false);
        CustomPinF.setText("");
    }

}