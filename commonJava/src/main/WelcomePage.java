package main;

import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JPanel {

    private MouseInputs mouseInputs;
    private Window window;
    private JButton LoginBtn;
    private JButton RegisterBtn;

    public WelcomePage(Window window) {
        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        this.LoginBtn = new JButton("Login");
        this.RegisterBtn = new JButton("Register");
        addMouseListener(mouseInputs);

        requestFocus();
        setLayout(null);

        this.LoginBtn.setBounds(480, 320,150, 60 );
        this.LoginBtn.setBackground(new Color(81, 200, 120));
        this.LoginBtn.setForeground(Color.WHITE);

        this.RegisterBtn.setBounds(650, 320, 150, 60);
        this.RegisterBtn.setBackground(new Color(50, 75, 178));
        this.RegisterBtn.setForeground(Color.WHITE);

        LoginBtn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               window.getWindowFrame().removeWelcomePage();
               window.getWindowFrame().addLoginPage();
           }
        });

        RegisterBtn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               window.getWindowFrame().removeWelcomePage();
               window.getWindowFrame().addRegisterPage();
           }
        });

        add(this.LoginBtn);
        add(this.RegisterBtn);
        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 16));

//        Setting up the Welcome Text
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString("Welcome to VistaTrust - Bank", (1280 - g.getFontMetrics().stringWidth("Welcome to VistaTrust - Bank")) / 2, (520 - g.getFontMetrics().getHeight()) / 2);
    }
}
