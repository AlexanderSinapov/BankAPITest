package main;

import javax.swing.*;
import inputs.MouseInputs;
import utils.DBUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    public boolean isCurrentPageRegister = false;
    public JTextField FullName;
    public JTextField Email;
    public JPasswordField Password;
    public JTextField DOB;
    public JTextField PN;

    private final JLabel RegisterCredentialsIncorrect;

    public RegisterPanel(Window window) {
        MouseInputs mouseInputs = new MouseInputs(window);
        this.FullName = new JTextField();
        this.Email = new JTextField();
        this.Password = new JPasswordField();
        this.DOB = new JTextField();
        this.PN = new JTextField();
        JButton registerBtn = new JButton("Register");
        JLabel argumentsNotFilled = new JLabel("You haven't filled everything or \n the arguments already exist");

        JLabel fullName = new JLabel("Full Name");
        JLabel email = new JLabel("Email");
        JLabel password = new JLabel("Password");
        JLabel dob = new JLabel("Date of Birth(yyyy/mm/dd)");
        JLabel pn = new JLabel("Phone Number");
        JButton backBtnRegister = new JButton("Back");
        this.RegisterCredentialsIncorrect = new JLabel("Your register credentials are incorrect");

        addMouseListener(mouseInputs);

        requestFocus();
        setLayout(null);
        this.RegisterCredentialsIncorrect.setVisible(false);

        backBtnRegister.setBounds(10, 10, 80, 50);
        backBtnRegister.setBackground(new Color(81, 200, 120));
        backBtnRegister.setForeground(Color.BLACK);

        //        Setting up the Email - text input
        this.Email.setBackground(new Color(239, 239, 239));
        this.Email.setBorder(null);
        this.Email.setBounds(500, 160, 300, 30);
        this.Email.setForeground(Color.BLACK);

        email.setFont(new Font("Arial", Font.BOLD, 16));
        email.setBounds(500, 140, 300, 15);
        email.setForeground(Color.BLACK);

        //        Setting up the Email - text input
        this.FullName.setBackground(new Color(239, 239, 239));
        this.FullName.setBorder(null);
        this.FullName.setBounds(500, 160 + 80, 300, 30);
        this.FullName.setForeground(Color.BLACK);

        fullName.setFont(new Font("Arial", Font.BOLD, 16));
        fullName.setBounds(500, 140 + 80, 300, 15);
        fullName.setForeground(Color.BLACK);

        //        Setting up the Email - text input
        this.Password.setBackground(new Color(239, 239, 239));
        this.Password.setBorder(null);
        this.Password.setBounds(500, 160 + 160, 300, 30);
        this.Password.setForeground(Color.BLACK);

        password.setFont(new Font("Arial", Font.BOLD, 16));
        password.setBounds(500, 140 + 160, 300, 15);
        password.setForeground(Color.BLACK);

        //        Setting up the Email - text input
        this.DOB.setBackground(new Color(239, 239, 239));
        this.DOB.setBorder(null);
        this.DOB.setBounds(500, 160 + 240, 300, 30);
        this.DOB.setForeground(Color.BLACK);

        dob.setFont(new Font("Arial", Font.BOLD, 16));
        dob.setBounds(500, 140 + 240, 300, 15);
        dob.setForeground(Color.BLACK);

        //        Setting up the Email - text input
        this.PN.setBackground(new Color(239, 239, 239));
        this.PN.setBorder(null);
        this.PN.setBounds(500, 160 + 320, 300, 30);
        this.PN.setForeground(Color.BLACK);

        pn.setFont(new Font("Arial", Font.BOLD, 16));
        pn.setBounds(500, 140 + 320, 300, 15);
        pn.setForeground(Color.BLACK);

        registerBtn.setBounds(580, 160 + 400, 100, 60);
        registerBtn.setBackground(new Color(81, 200, 120));
        registerBtn.setForeground(Color.BLACK);

        argumentsNotFilled.setBounds(500, 100, 100, 30);
        argumentsNotFilled.setForeground(Color.RED);

        this.RegisterCredentialsIncorrect.setBackground(new Color(188, 84, 73));
        this.RegisterCredentialsIncorrect.setForeground(Color.RED);
        this.RegisterCredentialsIncorrect.setBounds(500, 100, 300, 10);
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (DBUtils.RequestRegister(Email.getText(), Password.getText(), FullName.getText(), DOB.getText(), PN.getText())) {
                        window.getWindowFrame().removeRegisterPage();
                        if (!window.getWindowFrame().loginPage.isVisible()) {
                            window.getWindowFrame().loginPage.setVisible(true);
                            RegisterCredentialsIncorrect.setVisible(false);
                        } else window.getWindowFrame().addLoginPage();

                    } else {
                        System.out.println("Register Failed!");
                        RegisterCredentialsIncorrect.setVisible(true);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        backBtnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.getWindowFrame().registerPage.setVisible(false);
                window.getWindowFrame().welcomePage.setVisible(true);
            }
        });

        add(this.RegisterCredentialsIncorrect);
        add(this.FullName);
        add(this.Email);
        add(this.Password);
        add(this.DOB);
        add(this.PN);
        add(fullName);
        add(email);
        add(password);
        add(dob);
        add(pn);
        add(registerBtn);
        add(backBtnRegister);
        setSize(1280, 720);
        setBackground(new Color(255, 255, 255));
    }}