package main;

import inputs.MouseInputs;
import utils.DBUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ForgotPasswordPage extends JPanel {
    // Instance variables
    private Window window;
    private MouseInputs mouseInputs;

    private final JLabel emailAndPIN;
    private final JLabel confirmPasswordLabel;
    private final JTextField confirmPassword;
    private final JTextField email;
    private final JButton submit;

    // Constructor for the ForgotPasswordPage class
    public ForgotPasswordPage(Window window){

        // Initializing components
        JLabel forgotPasswordLabel = new JLabel("Forgot Password");
        Font font = new Font("Arial", Font.BOLD, 20);
        this.emailAndPIN = new JLabel("Email");
        this.email = new JTextField();
        this.submit = new JButton("Submit");
        JButton backBtn = new JButton("Back");
        this.confirmPassword = new JTextField();
        this.confirmPasswordLabel = new JLabel("Confirm Password");

        // Adding mouse listener
        addMouseListener(mouseInputs);

        // Requesting focus for the panel
        requestFocus();

        // Setting layout to null for absolute positioning of components
        setLayout(null);

        // Styling and positioning of GUI components
        forgotPasswordLabel.setFont(new Font("Arial", Font.BOLD, 25));
        forgotPasswordLabel.setBounds(515, 10, 250, 40);
        forgotPasswordLabel.setForeground(Color.WHITE);

        backBtn.setBounds(10, 10, 80, 50);
        backBtn.setBackground(new Color(81, 200, 120));
        backBtn.setForeground(Color.WHITE);

        this.emailAndPIN.setFont(font);
        this.emailAndPIN.setBounds(500, 80, 200, 40);
        this.emailAndPIN.setForeground(Color.WHITE);

        this.email.setFont(new Font("Arial", Font.PLAIN, 20));
        this.email.setBounds(500,121,250,40);
        this.email.setBackground(new Color(18, 25, 33));
        this.email.setForeground(Color.WHITE);
        this.email.setBorder(null);

        this.submit.setBounds(580, 220, 100, 40);
        this.submit.setBackground(new Color(81, 200, 120));
        this.submit.setForeground(Color.WHITE);

        this.confirmPasswordLabel.setFont(font);
        this.confirmPasswordLabel.setBounds(500, 160, 250, 40);
        this.confirmPasswordLabel.setForeground(Color.WHITE);
        this.confirmPasswordLabel.setVisible(false);

        this.confirmPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        this.confirmPassword.setBounds(500,201,250,40);
        this.confirmPassword.setBackground(new Color(18, 25, 33));
        this.confirmPassword.setForeground(Color.WHITE);
        this.confirmPassword.setBorder(null);
        this.confirmPassword.setVisible(false);

        // ActionListener for the back button
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.getWindowFrame().welcomePage.setVisible(true);
                window.getWindowFrame().removeForgotPage();
                confirmPassword.setVisible(false);
                confirmPasswordLabel.setVisible(false);
                emailAndPIN.setText("Email");
                email.setText("");
                confirmPassword.setText("");
                submit.setBounds(580, 220, 100, 40);
            }
        });

        // ActionListener for the submit button
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    // Handling different stages of the password recovery process
                    if(Objects.equals(emailAndPIN.getText(), "Email")) {
                        if (DBUtils.RequestForgotPasswordFirst(email.getText())) {
                            System.out.println("Email Sent!");
                            emailAndPIN.setText("Reset PIN");
                            email.setText("");
                        } else System.out.println("Unauthorized or user not in DB!");
                    } else if (Objects.equals(emailAndPIN.getText(), "Reset PIN")) {
                        if(DBUtils.RequestForgotPasswordSecond(email.getText())){
                            System.out.println("PIN correct!");
                            emailAndPIN.setText("New Password");
                            email.setText("");
                            submit.setBounds(580,250,100,40);
                            confirmPassword.setVisible(true);
                            confirmPasswordLabel.setVisible(true);
                        } else System.out.println("PIN incorrect!");
                    } else {
                        if(Objects.equals(email.getText(), confirmPassword.getText())){
                            if(DBUtils.RequestForgotPasswordFinal(email.getText())){
                                window.getWindowFrame().welcomePage.setVisible(true);
                                window.getWindowFrame().removeForgotPage();
                                confirmPassword.setVisible(false);
                                confirmPasswordLabel.setVisible(false);
                                emailAndPIN.setText("Email");
                                email.setText("");
                                confirmPassword.setText("");
                                submit.setBounds(580, 220, 100, 40);
                                System.out.println("Changed Password!");
                            } else System.out.println("Request Response error!");
                        } else System.out.println("Passwords don't match!");
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        // Adding components to the panel
        add(forgotPasswordLabel);
        add(emailAndPIN);
        add(email);
        add(submit);
        add(backBtn);
        add(confirmPasswordLabel);
        add(confirmPassword);

        // Setting panel size and background color
        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));
    }
}
