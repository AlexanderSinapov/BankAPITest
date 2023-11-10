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


        g.setColor(new Color(0, 0, 0));
        g.fillRoundRect(400, 400, 100, 40, 15, 15);
    }
}
