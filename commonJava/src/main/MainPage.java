package main;

import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JPanel {

    private MouseInputs mouseInputs;
    private Window window;

    public MainPage(Window window) {
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

//        Setting up the Login Button
        g.setColor(new Color(81, 200, 120));
        g.fillRoundRect(480, 320, 150, 60, 40, 40);
        g.setColor(Color.WHITE);
        g.drawString("Login", 480 + (150 - g.getFontMetrics().stringWidth("Login")) / 2, 320 + (60 - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent());

//        Setting up the Register Button
        g.setColor(new Color(50, 75, 178));
        g.fillRoundRect(650, 320, 150, 60, 40, 40);
        g.setColor(Color.WHITE);
        g.drawString("Register", 650 + (150 - g.getFontMetrics().stringWidth("Register")) / 2, 320 + (60 - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent());


//        Setting up the Welcome Text
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString("Welcome to VistaTrust - Bank", (1280 - g.getFontMetrics().stringWidth("Welcome to VistaTrust - Bank")) / 2, (520 - g.getFontMetrics().getHeight()) / 2);
    }
}
