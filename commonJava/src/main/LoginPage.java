package main;

import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JPanel {

    private ImageIcon icon;

    private Window window;
    private MouseInputs mouseInputs;

    public LoginPage(Window window) {

        this.window = window;
        this.mouseInputs = new MouseInputs(window);
        addMouseListener(mouseInputs);
        requestFocus();

        setSize(1280, 720);
        setBackground(new Color(13, 17, 100));
    }
}
