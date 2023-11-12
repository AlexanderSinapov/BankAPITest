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

    private Font font;

    private JPanel accountServices;
    private JLabel accServ;

    public MainPage(Window window) {
        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        this.font = new Font("Arial", Font.BOLD, 30);;

        this.accountServices = new JPanel();
        this.accServ = new JLabel("Account Services");

        addMouseListener(mouseInputs);

        requestFocus();

        setLayout(null);

        this.accountServices.setBackground(new Color(13, 17, 37));
        this.accountServices.setBounds(340, 0, 1280 - 340, 685);

        this.accServ.setFont(font);
        this.accServ.setForeground(Color.WHITE);

        this.accountServices.add(accServ);
        add(this.accountServices);

        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));

    }
}
