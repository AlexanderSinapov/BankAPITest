package main;

import javax.swing.*;
import inputs.MouseInputs;

import java.awt.*;

public class RegisterPanel extends JPanel {
    public boolean isCurrentPageRegister = false;
    private Window window;
    private MouseInputs mouseInputs;

    public RegisterPanel(Window window) {
        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        addMouseListener(mouseInputs);

        requestFocus();

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
}
