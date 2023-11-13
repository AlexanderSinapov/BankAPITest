package main;

import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JPanel {
    private Window window;
    private MouseInputs mouseInputs;
    private Font font;
    private static JPanel infoPanel;
    private static JLabel accServ;
    private static JLabel CardsMenuLabel;
    private JButton accServic;

    public MainPage(Window window) {
        this.window = window;
        this.mouseInputs = new MouseInputs(window);

        this.infoPanel = new JPanel();

        addMouseListener(mouseInputs);

        requestFocus();

        setLayout(null);

        this.infoPanel.setBackground(new Color(13, 17, 37));
        this.infoPanel.setBounds(340, 0, 1280 - 340, 685);

        add(this.infoPanel);

        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));

        this.font = new Font("Arial", Font.BOLD, 30);;

        this.accServ = new JLabel("Account Services");
        this.CardsMenuLabel = new JLabel("Cards");

//        Puttin in the AccServ Label
        this.accServ.setFont(font);
        this.accServ.setForeground(Color.WHITE);

//        Putting in the CardsMenuLabel
        this.CardsMenuLabel.setFont(font);
        this.CardsMenuLabel.setForeground(Color.WHITE);
    }

    public static void AccountServicesPanel() {
        infoPanel.add(accServ);
    }

    public static void removeAccountServicesPanel() {
        infoPanel.remove(accServ);
    }

    public static void addCardsMenuLabel() {infoPanel.add(CardsMenuLabel);}

    public static void removeCardsMenuLabel() {infoPanel.remove(CardsMenuLabel);}


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 16));

//        Setting up the Account Services Btn
        g.setColor(new Color(81, 200, 120));
        g.fillRoundRect(20, 10, 200, 60, 40, 40);
        g.setColor(Color.WHITE);
        g.drawString("Account Services", 20 + (200 - g.getFontMetrics().stringWidth("Account Services")) / 2, 10 + (60 - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent());

//        Setting up the Cards Btn
        g.setColor(new Color(81, 200, 120));
        g.fillRoundRect(20, 80, 200, 60, 40, 40);
        g.setColor(Color.WHITE);
        g.drawString("Cards", 20 + (200 - g.getFontMetrics().stringWidth("Cards")) / 2, 80 + (60 - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent());

//        Setting up the Transactions Btn
        g.setColor(new Color(81, 200, 120));
        g.fillRoundRect(20, 150, 200, 60, 40, 40);
        g.setColor(Color.WHITE);
        g.drawString("Transactions", 20 + (200 - g.getFontMetrics().stringWidth("Transactions")) / 2, 150 + (60 - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent());

//        Setting up the Tax Payments Btn
        g.setColor(new Color(81, 200, 120));
        g.fillRoundRect(20, 220, 200, 60, 40, 40);
        g.setColor(Color.WHITE);
        g.drawString("Tax Payments", 20 + (200 - g.getFontMetrics().stringWidth("Tax Payments")) / 2, 220 + (60 - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent());
    }
}
