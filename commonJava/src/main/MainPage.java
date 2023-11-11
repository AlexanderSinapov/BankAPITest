package main;

import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JPanel {
    private Window window;
    private MouseInputs mouseInputs;
    public MainPage(Window window) {
        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        addMouseListener(mouseInputs);

        requestFocus();

        setSize(1280, 720);
        setBackground(new Color(13, 17, 23));

    }
}
