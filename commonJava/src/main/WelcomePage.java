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
        JLabel WelcomeL = new JLabel("Welcome");
        this.RegisterBtn = new JButton("Register");
        addMouseListener(mouseInputs);

        requestFocus();
        setLayout(null);

        WelcomeL.setBounds(505, 200,300, 80);
        WelcomeL.setFont(MainPage.loadFont("Fonts/Roboto-Medium.ttf", Font.BOLD, 60));
        WelcomeL.setForeground(Color.BLACK);

        this.LoginBtn.setBounds(480, 320,150, 60 );
        this.LoginBtn.setIcon(new ImageIcon("Images/login_FILL0_wght400_GRAD0_opsz24.png"));
        this.LoginBtn.setBackground(new Color(81, 200, 120));
        this.LoginBtn.setForeground(Color.BLACK);

        this.RegisterBtn.setBounds(650, 320, 150, 60);
        this.RegisterBtn.setIcon(new ImageIcon("Images/person_add_FILL0_wght400_GRAD0_opsz24.png"));
        this.RegisterBtn.setBackground(new Color(81, 200, 120));
        this.RegisterBtn.setForeground(Color.BLACK);

        LoginBtn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               window.getWindowFrame().welcomePage.setVisible(false);
               if(!window.getWindowFrame().loginPage.isVisible()){
                   window.getWindowFrame().loginPage.setVisible(true);
                   window.getWindowFrame().loginPage.email.setText("");
                   window.getWindowFrame().loginPage.password.setText("");
               } else window.getWindowFrame().addLoginPage();
           }
        });

        RegisterBtn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               window.getWindowFrame().welcomePage.setVisible(false);
               if(!window.getWindowFrame().registerPage.isVisible()){
                   window.getWindowFrame().registerPage.setVisible(true);
                   window.getWindowFrame().registerPage.Email.setText("");
                   window.getWindowFrame().registerPage.Password.setText("");
                   window.getWindowFrame().registerPage.FullName.setText("");
                   window.getWindowFrame().registerPage.DOB.setText("");
                   window.getWindowFrame().registerPage.PN.setText("");
               } else window.getWindowFrame().addRegisterPage();
           }
        });

        add(this.LoginBtn);
        add(this.RegisterBtn);
        add(WelcomeL);
        setSize(1280, 720);
        setBackground(new Color(255, 255, 255));

    }
}
