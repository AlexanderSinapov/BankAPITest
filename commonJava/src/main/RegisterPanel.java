package main;

import javax.swing.*;
import inputs.MouseInputs;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    public boolean isCurrentPageRegister = false;
    private Window window;
    private MouseInputs mouseInputs;
    private JButton RegisterBtn;
    private JTextField FullName;
    private JTextField Email;
    private JTextField Password;
    private JTextField DOB;
    private JTextField PN;

    private JLabel fullName;
    private JLabel email;
    private JLabel password;
    private JLabel dob;
    private JLabel pn;

    private Font font;

    public RegisterPanel(Window window) {
        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        this.FullName = new JTextField();
        this.Email = new JTextField();
        this.Password = new JTextField();
        this.DOB = new JTextField();
        this.PN = new JTextField();
        this.RegisterBtn = new JButton("Register");

        this.fullName = new JLabel("Full Name");
        this.email = new JLabel("Email");
        this.password = new JLabel("Password");
        this.dob = new JLabel("Date of Birth");
        this.pn = new JLabel("Phone Number");

        addMouseListener(mouseInputs);

        requestFocus();
        setLayout(null);

        //        Setting up the Email - text input
        this.Email.setBackground(new Color(13, 17, 26));
        this.Email.setBorder(new BottomBorder());
        this.Email.setBounds(500, 160, 300, 15);
        this.Email.setForeground(Color.WHITE);

        this.email.setFont(this.font);
        this.email.setBounds(500, 140, 300, 15);
        this.email.setForeground(Color.WHITE);

        //        Setting up the Email - text input
        this.FullName.setBackground(new Color(13, 17, 26));
        this.FullName.setBorder(new BottomBorder());
        this.FullName.setBounds(500, 160 + 80, 300, 15);
        this.FullName.setForeground(Color.WHITE);

        this.fullName.setFont(this.font);
        this.fullName.setBounds(500, 140 + 80, 300, 15);
        this.fullName.setForeground(Color.WHITE);

        //        Setting up the Email - text input
        this.Password.setBackground(new Color(13, 17, 26));
        this.Password.setBorder(new BottomBorder());
        this.Password.setBounds(500, 160 + 160, 300, 15);
        this.Password.setForeground(Color.WHITE);

        this.password.setFont(this.font);
        this.password.setBounds(500, 140 + 160, 300, 15);
        this.password.setForeground(Color.WHITE);

        //        Setting up the Email - text input
        this.DOB.setBackground(new Color(13, 17, 26));
        this.DOB.setBorder(new BottomBorder());
        this.DOB.setBounds(500, 160 + 240, 300, 15);
        this.DOB.setForeground(Color.WHITE);

        this.dob.setFont(this.font);
        this.dob.setBounds(500, 140 + 240, 300, 15);
        this.dob.setForeground(Color.WHITE);

        //        Setting up the Email - text input
        this.PN.setBackground(new Color(13, 17, 26));
        this.PN.setBorder(new BottomBorder());
        this.PN.setBounds(500, 160 + 320, 300, 15);
        this.PN.setForeground(Color.WHITE);

        this.pn.setFont(this.font);
        this.pn.setBounds(500, 140 + 320, 300, 15);
        this.pn.setForeground(Color.WHITE);

        this.RegisterBtn.setBounds(580, 160 + 400, 100, 60);
        this.RegisterBtn.setBackground(new Color(81, 200, 120));
        this.RegisterBtn.setForeground(Color.WHITE);

        RegisterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.getWindowFrame().removeRegisterPage();
                window.getWindowFrame().addMainPage();
            }
        });

        add(this.FullName);
        add(this.Email);
        add(this.Password);
        add(this.DOB);
        add(this.PN);
        add(this.fullName);
        add(this.email);
        add(this.password);
        add(this.dob);
        add(this.pn);
        add(this.RegisterBtn);
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
