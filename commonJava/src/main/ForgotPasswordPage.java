package main;

import inputs.MouseInputs;
import utils.DBUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ForgotPasswordPage extends JPanel {
    private Window window;
    private MouseInputs mouseInputs;

    private final JLabel emailAndPIN;
    private final JLabel confirmPasswordLabel;
    private final JTextField confirmPassword;
    private final JTextField email;
    private final JButton submit;

    public ForgotPasswordPage(Window window){

        JLabel forgotPasswordLabel = new JLabel("Forgot Password");
        Font font = new Font("Arial", Font.BOLD, 20);
        this.emailAndPIN = new JLabel("Email");
        this.email = new JTextField();
        this.submit = new JButton("Submit");
        JButton backBtn = new JButton("Back");
        this.confirmPassword = new JTextField();
        this.confirmPasswordLabel = new JLabel("Confirm Password");

        addMouseListener(mouseInputs);

        requestFocus();

        setLayout(null);

        forgotPasswordLabel.setFont(new Font("Arial", Font.BOLD, 25));
        forgotPasswordLabel.setBounds(515, 10, 250, 40);
        forgotPasswordLabel.setForeground(Color.BLACK);

        backBtn.setBounds(10, 10, 80, 50);
        backBtn.setBackground(new Color(81, 200, 120));
        backBtn.setForeground(Color.BLACK);

        this.emailAndPIN.setFont(font);
        this.emailAndPIN.setBounds(500, 80, 200, 40);
        this.emailAndPIN.setForeground(Color.BLACK);

        this.email.setFont(new Font("Arial", Font.PLAIN, 20));
        this.email.setBounds(500,121,250,40);
        this.email.setBackground(new Color(239, 239, 239));
        this.email.setForeground(Color.BLACK);
        this.email.setBorder(null);

        this.submit.setBounds(580, 220, 100, 40);
        this.submit.setBackground(new Color(81, 200, 120));
        this.submit.setForeground(Color.BLACK);

        this.confirmPasswordLabel.setFont(font);
        this.confirmPasswordLabel.setBounds(500, 160, 250, 40);
        this.confirmPasswordLabel.setForeground(Color.BLACK);
        this.confirmPasswordLabel.setVisible(false);

        this.confirmPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        this.confirmPassword.setBounds(500,201,250,40);
        this.confirmPassword.setBackground(new Color(239, 239, 239));
        this.confirmPassword.setForeground(Color.black);
        this.confirmPassword.setBorder(null);
        this.confirmPassword.setVisible(false);

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

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
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

        add(forgotPasswordLabel);
        add(emailAndPIN);
        add(email);
        add(submit);
        add(backBtn);
        add(confirmPasswordLabel);
        add(confirmPassword);
        setSize(1280, 720);
        setBackground(new Color(255,255, 255));
    }
}
