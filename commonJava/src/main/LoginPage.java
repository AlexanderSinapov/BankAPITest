package main;

import inputs.MouseInputs;

import javax.swing.border.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {

    public boolean isCurrentPageLogin = false;


    private ImageIcon icon;

    private Window window;
    private MouseInputs mouseInputs;

    public JTextField email;
    public JTextField password;
    private JLabel emailLabel;
    private JLabel passLabel;
    private Font font;
    private JButton LoginBtn;

    public LoginPage(Window window) {

        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        this.email = new JTextField(20);
        this.password = new JTextField(20);
        this.emailLabel = new JLabel("Email address");
        this.passLabel = new JLabel("Password");
        this.font = new Font("Arial", Font.BOLD, 16);
        this.LoginBtn = new JButton("Login");

        addMouseListener(mouseInputs);

        requestFocus();

        setLayout(null);

//        Setting up the Email - text input
        this.email.setBackground(new Color(13, 17, 26));
        this.email.setBorder(new BottomBorder());
        this.email.setForeground(Color.WHITE);
        this.email.setBounds(500, 160, 300, 15);

        this.emailLabel.setFont(this.font);
        this.emailLabel.setBounds(500, 140, 300, 15);
        this.emailLabel.setForeground(Color.WHITE);

//        Setting up the Password - text input
        this.password.setBackground(new Color(13, 17, 26));
        this.password.setBorder(new BottomBorder());
        this.password.setForeground(Color.WHITE);
        this.password.setBounds(500, 240, 300, 15);

        this.passLabel.setFont(this.font);
        this.passLabel.setBounds(500, 220, 300, 15);
        this.passLabel.setForeground(Color.WHITE);

        this.LoginBtn.setBounds(580, 320, 100, 60);
        this.LoginBtn.setBackground(new Color(81, 200, 120));
        this.LoginBtn.setForeground(Color.WHITE);

        LoginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.getWindowFrame().removeLoginPage();
                window.getWindowFrame().addMainPage();
                String textFieldValue = email.getText();
                System.out.println(textFieldValue);
            }
        });

        add(this.LoginBtn);
        add(this.email);
        add(this.password);
        add(this.emailLabel);
        add(this.passLabel);
        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));
    }

    private static class BottomBorder implements Border {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            // Draw only the bottom line
            g.setColor(Color.WHITE);
            g.drawLine(x, y + height - 1, x + width, y + height - 1);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 1, 0); // Insets (top, left, bottom, right)
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }

}