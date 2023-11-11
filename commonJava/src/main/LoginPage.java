package main;

import inputs.MouseInputs;

import javax.swing.border.Border;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JPanel {

    public boolean isCurrentPageLogin = false;


    private ImageIcon icon;

    private Window window;
    private MouseInputs mouseInputs;

    public JTextField email;
    public JTextField password;

    public LoginPage(Window window) {

        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        this.email = new JTextField("Email: ", 20);
        this.password = new JTextField("Password: ", 20);

        addMouseListener(mouseInputs);

        requestFocus();

        this.email.setBackground(new Color(13, 17, 26));
        this.email.setBorder(new BottomBorder());
        this.email.setForeground(Color.WHITE);

        this.password.setBackground(new Color(13, 17, 26));
        this.password.setBorder(new BottomBorder());
        this.password.setForeground(Color.WHITE);

        add(this.email);
        add(this.password);
        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 16));

        g.setColor(new Color(50, 75, 178));
        g.fillRoundRect(10, 10, 100, 60, 40, 40);
        g.setColor(Color.WHITE);
        g.drawString("⇽ Back", 10 + (100 - g.getFontMetrics().stringWidth("Login")) / 2, 10 + (60 - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent());

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
