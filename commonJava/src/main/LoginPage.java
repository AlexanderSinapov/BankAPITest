package main;

import inputs.MouseInputs;
import utils.DBUtils;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {

    // Variables to manage login state
    public boolean isCurrentPageLogin = false;
    private ImageIcon icon;

    // Reference to the main application window
    public Window window;

    // UI components for email, password, and feedback messages
    public JTextField email;
    public JPasswordField password;
    private final float alpha = 0.4f;
    private boolean fadeIn = false;

    private final JLabel loginCredentialsIncorrect;

    // Constructor for the LoginPage
    public LoginPage(Window window) {

        // Initialize instance variables
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

        // Add mouse listener for user inputs
        addMouseListener(mouseInputs);

        // Set the focus to this panel
        requestFocus();

        // Set layout to null for custom component placement
        setLayout(null);

        // Initialize and configure UI components
        this.loginCredentialsIncorrect.setVisible(false);

        // Configure Forgot Password button
        forgotPassword.setBounds(530, 280, 200, 30);
        forgotPassword.setBackground(new Color(0, 0, 0, 0));
        forgotPassword.setOpaque(false);
        forgotPassword.setContentAreaFilled(false);
        forgotPassword.setBorderPainted(false);
        forgotPassword.setForeground(Color.BLACK);

        // Configure Back button
        backBtn.setBounds(10, 10, 80, 50);
        backBtn.setBackground(new Color(81, 200, 120));
        backBtn.setForeground(Color.BLACK);

        // Configure Email input
        this.email.setBackground(new Color(239, 239, 239));
        this.email.setBorder(null);
        this.email.setForeground(Color.BLACK);
        this.email.setBounds(500, 160, 300, 30);

        emailLabel.setFont(font);
        emailLabel.setBounds(500, 140, 300, 15);
        emailLabel.setForeground(Color.BLACK);

        // Configure Password input
        this.password.setBackground(new Color(239, 239, 239));
        this.password.setBorder(null);
        this.password.setForeground(Color.BLACK);
        this.password.setBounds(500, 240, 300, 30);

        passLabel.setFont(font);
        passLabel.setBounds(500, 220, 300, 15);
        passLabel.setForeground(Color.BLACK);

        // Configure Login button
        loginBtn.setBounds(580, 320, 100, 60);
        loginBtn.setBackground(new Color(81, 200, 120));
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setFont(new Font("Lucida Sans", Font.PLAIN, 20));

        // Configure feedback label for incorrect login credentials
        this.loginCredentialsIncorrect.setBackground(new Color(188, 84, 73));
        this.loginCredentialsIncorrect.setForeground(Color.RED);
        this.loginCredentialsIncorrect.setBounds(500, 100, 300, 20);

        // Add action listener for the Login button
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Attempt to log in using entered credentials
                    if (DBUtils.RequestLogin(email.getText(), password.getText())) {
                        // If successful, retrieve user data and display the main page
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
                        // If login fails, display an error message
                        System.out.println("Login Failed!");
                        fadeIn = false;
                        loginCredentialsIncorrect.setVisible(true);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Add action listener for the Back button
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate back to the welcome page
                window.getWindowFrame().welcomePage.setVisible(true);
                window.getWindowFrame().loginPage.setVisible(false);
            }
        });

        // Add action listener for the Forgot Password button
        forgotPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the forgot password page
                window.getWindowFrame().loginPage.setVisible(false);
                window.getWindowFrame().addForgotPage();
            }
        });

        // Add components to the panel
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
}