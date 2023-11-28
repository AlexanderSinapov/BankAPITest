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

    public JTextField email;
    public JPasswordField password;
    private final float alpha = 0.4f;
    private boolean fadeIn = false;

    private final JLabel loginCredentialsIncorrect;

    public LoginPage(Window window) {

        this.window = window;
        MouseInputs mouseInputs = new MouseInputs(window);
        this.email = new JTextField(20);
        this.password = new JPasswordField(20);
        JLabel emailLabel = new JLabel("Email address");
        JLabel passLabel = new JLabel("Password");
        Font font = new Font("Arial", Font.BOLD, 16);
        JButton loginBtn = new JButton("Login");
        JButton backBtn = new JButton("Back");
        JButton forgotPassword = new JButton("Forgot Password");
        this.loginCredentialsIncorrect = new JLabel("Your login credentials are incorrect");

        addMouseListener(mouseInputs);

        requestFocus();

        setLayout(null);

        this.loginCredentialsIncorrect.setVisible(false);

        forgotPassword.setBounds(530,280,200, 30);
        forgotPassword.setBackground(new Color(0,0,0,0));
        forgotPassword.setOpaque(false);
        forgotPassword.setContentAreaFilled(false);
        forgotPassword.setBorderPainted(false);
        forgotPassword.setForeground(Color.BLACK);

        backBtn.setBounds(10, 10, 80, 50);
        backBtn.setBackground(new Color(81, 200, 120));
        backBtn.setForeground(Color.BLACK);

//        Setting up the Email - text input
        this.email.setBackground(new Color(239, 239, 239));
        this.email.setBorder(null);
        this.email.setForeground(Color.BLACK);
        this.email.setBounds(500, 160, 300, 30);

        emailLabel.setFont(font);
        emailLabel.setBounds(500, 140, 300, 15);
        emailLabel.setForeground(Color.BLACK);

//        Setting up the Password - text input
        this.password.setBackground(new Color(239, 239, 239));
        this.password.setBorder(null);
        this.password.setForeground(Color.BLACK);
        this.password.setBounds(500, 240, 300, 30);

        passLabel.setFont(font);
        passLabel.setBounds(500, 220, 300, 15);
        passLabel.setForeground(Color.BLACK);

        loginBtn.setBounds(580, 320, 100, 60);
        loginBtn.setBackground(new Color(81, 200, 120));
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setFont(new Font("Lucida Sans", Font.PLAIN, 20));

        this.loginCredentialsIncorrect.setBackground(new Color(188, 84, 73));
        this.loginCredentialsIncorrect.setForeground(Color.RED);
        this.loginCredentialsIncorrect.setBounds(500, 100, 300, 20);

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(DBUtils.RequestLogin(email.getText(), password.getText())){
                        DBUtils.userData = DBUtils.RequestGetData();
                        MainPage.SetUserData();

                        DBUtils.cards = DBUtils.RequestGetCards();
                        MainPage.SetCards();

                        email.setText("");
                        password.setText("");

                        loginCredentialsIncorrect.setVisible(false);
                        window.getWindowFrame().loginPage.setVisible(false);
                        window.getWindowFrame().addMainPage();
                        MainPage.cardsPanel.setVisible(true);
                    } else {
                        System.out.println("Login Failed!");
                        fadeIn = false;
                        loginCredentialsIncorrect.setVisible(true);

                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.getWindowFrame().welcomePage.setVisible(true);
                window.getWindowFrame().loginPage.setVisible(false);
            }
        });



        forgotPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.getWindowFrame().loginPage.setVisible(false);
                window.getWindowFrame().addForgotPage();
            }
        });

        add(this.loginCredentialsIncorrect);
        add(loginBtn);
        add(this.email);
        add(this.password);
        add(emailLabel);
        add(passLabel);
        add(backBtn);
        add(forgotPassword);
        setSize(1280, 720);
        setBackground(new Color(255, 255, 255));
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