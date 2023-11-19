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

    private JLabel ForgotPasswordLabel;
    private JLabel emailAndPIN;
    private JLabel confirmPasswordLabel;
    private JTextField confirmPassword;
    private JTextField email;
    private JButton submit;
    private Font font;
    private JButton BackBtn;

    public ForgotPasswordPage(Window window){

        this.ForgotPasswordLabel = new JLabel("Forgot Password");
        this.font = new Font("Arial", Font.BOLD, 20);
        this.emailAndPIN = new JLabel("Email");
        this.email = new JTextField();
        this.submit = new JButton("Submit");
        this.BackBtn = new JButton("Back");
        this.confirmPassword = new JTextField();
        this.confirmPasswordLabel = new JLabel("Confirm Password");

        addMouseListener(mouseInputs);

        requestFocus();

        setLayout(null);

        this.ForgotPasswordLabel.setFont(new Font("Arial", Font.BOLD, 25));
        this.ForgotPasswordLabel.setBounds(515, 10, 250, 40);
        this.ForgotPasswordLabel.setForeground(Color.WHITE);

        this.BackBtn.setBounds(10, 10, 80, 50);
        this.BackBtn.setBackground(new Color(81, 200, 120));
        this.BackBtn.setForeground(Color.WHITE);

        this.emailAndPIN.setFont(this.font);
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

        this.confirmPasswordLabel.setFont(this.font);
        this.confirmPasswordLabel.setBounds(500, 160, 250, 40);
        this.confirmPasswordLabel.setForeground(Color.WHITE);
        this.confirmPasswordLabel.setVisible(false);

        this.confirmPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        this.confirmPassword.setBounds(500,201,250,40);
        this.confirmPassword.setBackground(new Color(18, 25, 33));
        this.confirmPassword.setForeground(Color.WHITE);
        this.confirmPassword.setBorder(null);
        this.confirmPassword.setVisible(false);

        BackBtn.addActionListener(new ActionListener() {
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

        add(ForgotPasswordLabel);
        add(emailAndPIN);
        add(email);
        add(submit);
        add(BackBtn);
        add(confirmPasswordLabel);
        add(confirmPassword);
        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));
    }
}
