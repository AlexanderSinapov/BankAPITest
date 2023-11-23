package main;

import inputs.MouseInputs;
import utils.DBUtils;

import javax.swing.border.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {

    public boolean isCurrentPageLogin = false;
    private ImageIcon icon;

    public Window window;
    private MouseInputs mouseInputs;

    public JTextField email;
    public JTextField password;
    private JLabel emailLabel;
    private JLabel passLabel;
    private Font font;
    private JButton LoginBtn;
    private JButton BackBtn;
    private JButton ForgotPassword;
    private float alpha = 0.4f;
    private boolean fadeIn = false;

    private JLabel loginCredentialsIncorrect;

    public LoginPage(Window window) {

        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        this.email = new JTextField(20);
        this.password = new JTextField(20);
        this.emailLabel = new JLabel("Email address");
        this.passLabel = new JLabel("Password");
        this.font = new Font("Arial", Font.BOLD, 16);
        this.LoginBtn = new JButton("Login");
        this.BackBtn = new JButton("Back");
        this.ForgotPassword = new JButton("Forgot Password");
        this.loginCredentialsIncorrect = new JLabel("Your login credentials are incorrect");

        addMouseListener(mouseInputs);

        requestFocus();

        setLayout(null);
//        setContentPane();

        this.loginCredentialsIncorrect.setVisible(false);

        this.ForgotPassword.setBounds(530,280,200, 30);
        this.ForgotPassword.setBackground(new Color(0,0,0,0));
        this.ForgotPassword.setOpaque(false);
        this.ForgotPassword.setContentAreaFilled(false);
        this.ForgotPassword.setBorderPainted(false);
        this.ForgotPassword.setForeground(Color.WHITE);

        this.BackBtn.setBounds(10, 10, 80, 50);
        this.BackBtn.setBackground(new Color(81, 200, 120));
        this.BackBtn.setForeground(Color.WHITE);

//        Setting up the Email - text input
        this.email.setBackground(new Color(18, 25, 33));
        this.email.setBorder(null);
        this.email.setForeground(Color.WHITE);
        this.email.setBounds(500, 160, 300, 15);

        this.emailLabel.setFont(this.font);
        this.emailLabel.setBounds(500, 140, 300, 15);
        this.emailLabel.setForeground(Color.WHITE);

//        Setting up the Password - text input
        this.password.setBackground(new Color(18, 25, 33));
        this.password.setBorder(null);
        this.password.setForeground(Color.WHITE);
        this.password.setBounds(500, 240, 300, 15);

        this.passLabel.setFont(this.font);
        this.passLabel.setBounds(500, 220, 300, 15);
        this.passLabel.setForeground(Color.WHITE);

        this.LoginBtn.setBounds(580, 320, 100, 60);
        this.LoginBtn.setBackground(new Color(81, 200, 120));
        this.LoginBtn.setForeground(Color.WHITE);

        this.loginCredentialsIncorrect.setBackground(new Color(188, 84, 73));
        this.loginCredentialsIncorrect.setForeground(Color.RED);
        this.loginCredentialsIncorrect.setBounds(500, 100, 300, 20);

        LoginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(DBUtils.RequestLogin(email.getText(), password.getText())){ //email.getText(), password.getText()))
                        window.getWindowFrame().removeLoginPage();
                        window.getWindowFrame().addMainPage();
                        loginCredentialsIncorrect.setVisible(false);
                    } else {
                        System.out.println("Login Failed!");
                        fadeIn = false;
//                        window.getWindowFrame().removeLoginPage();
//                        window.getWindowFrame().addMainPage();
                        loginCredentialsIncorrect.setVisible(true);

                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        BackBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.getWindowFrame().welcomePage.setVisible(true);
                window.getWindowFrame().loginPage.setVisible(false);
                window.getWindowFrame().setBackground(new Color(13, 17, 23));
            }
        });



        ForgotPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.getWindowFrame().loginPage.setVisible(false);
                window.getWindowFrame().addForgotPage();
            }
        });

        add(this.loginCredentialsIncorrect);
        add(this.LoginBtn);
        add(this.email);
        add(this.password);
        add(this.emailLabel);
        add(this.passLabel);
        add(this.BackBtn);
        add(this.ForgotPassword);
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

//    public void FadingPanel() {
//        Timer timer = new Timer(40, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (fadeIn) {
//                    alpha += 0.1f;
//                    if (alpha <= 1.0f) {
//                        alpha = 1.0f;
//                        fadeIn = false;
//                    }
//                } else {
//                    alpha -= 0.1f;
//                    if (alpha <= 0.0f) {
//                        alpha = 0.0f;
//                        fadeIn = false;
//                    }
//                }
//                repaint();
//            }
//        });
//        timer.start();
//
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
//        g2d.setColor(Color.GREEN);
//        g2d.fillRect(0, 0, 20, 10);
//        g2d.dispose();
//    }

}