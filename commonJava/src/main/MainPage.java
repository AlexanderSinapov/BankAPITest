package main;

import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JPanel {
    private Window window;
    private MouseInputs mouseInputs;
    private String Email;
    private String Password;
    private String DOB;
    private String FullName;
    private String Cards;
    private String Pin;

    private JLabel EmailL;
    private JLabel PasswordL;
    private JLabel DOBL;
    private JLabel FullNameL;
    private JLabel CardsL;
    private JLabel PinL;

    private Font font;

    public MainPage(Window window) {
        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        this.EmailL = new JLabel();
        this.PasswordL = new JLabel();
        this.DOBL = new JLabel();
        this.FullNameL = new JLabel();
        this.CardsL = new JLabel();
        this.PinL = new JLabel();
        this.font = new Font("Arial", Font.BOLD, 16);

        addMouseListener(mouseInputs);

        requestFocus();

        setLayout(null);
        this.EmailL.setFont(font);
        this.EmailL.setBounds(230, 10, 30, 30);
        this.EmailL.setForeground(Color.WHITE);

        add(this.EmailL);
        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

//        Setting up the Account Services Panel
        g.setColor(new Color(33, 38, 45));
        g.fillRoundRect(220, 0, 1060, 685, 40, 40);
    }
}
