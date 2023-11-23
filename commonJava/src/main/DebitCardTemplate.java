package main;

import javax.swing.*;
import java.awt.*;

public class DebitCardTemplate extends JPanel {
    private static Window window;
    private static JLabel cardNumber;
    private static JLabel expDate;
    private static JLabel TwoNames;
    private static JLabel Nickname;

    public DebitCardTemplate(Window window){
        this.cardNumber = new JLabel("Card Number");
        this.expDate = new JLabel("Expiration Date");
        this.TwoNames = new JLabel("Two Names");
        this.Nickname = new JLabel("Card Nickname");

        this.Nickname.setFont(new Font("Ariel", Font.BOLD, 20));
        this.Nickname.setBounds(100, 100, 100, 20);
        this.Nickname.setForeground(Color.WHITE);

        add(cardNumber);
        add(expDate);
        add(TwoNames);
        add(Nickname);
        setSize(500, 300);
        setBackground(new Color(18, 25, 33));
    }
}
