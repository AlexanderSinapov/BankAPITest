package main;

import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame {

    // References to different panels in the application
    public WelcomePage welcomePage;
    public LoginPage loginPage;
    public RegisterPanel registerPage;
    public MainPage mainPage;
    private ImageIcon image;
    private ForgotPasswordPage fpPage;

    // Constructor for the WindowFrame
    public WindowFrame(WelcomePage panel, LoginPage loginPage, RegisterPanel registerPage, MainPage mainPage, ForgotPasswordPage forgotPassword)  {

        // Initialize instance variables with the provided panels
        this.welcomePage = panel;
        this.loginPage = loginPage;
        this.registerPage = registerPage;
        this.mainPage = mainPage;
        this.fpPage = forgotPassword;
        image = new ImageIcon("commonJava/Resources/Images/VistaTrustBankLogo.png");

        // Configure JFrame settings
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("VistaTrust - Bank");
        setIconImage(image.getImage());

        // Initially, add the welcome page to the frame and make it visible
        addWelcomePage();
        setVisible(true);
    }

    // Method to remove the welcome page from the frame
    public void removeWelcomePage() {
        remove(welcomePage);
        revalidate();
    }

    // Method to add the welcome page to the frame
    public void addWelcomePage() {
        add(welcomePage);
        revalidate();
    }

    // Method to add the forgot password page to the frame
    public void addForgotPage(){
        add(fpPage);
        revalidate();
    }

    // Method to remove the forgot password page from the frame
    public void removeForgotPage(){
        remove(fpPage);
        revalidate();
    }

    // Method to remove the login page from the frame
    public void removeLoginPage() {
        remove(loginPage);
        revalidate();
    }

    // Method to add the login page to the frame
    public void addLoginPage() {
        add(loginPage);
        revalidate();
    }

    // Method to remove the register page from the frame
    public void removeRegisterPage() {
        remove(registerPage);
        revalidate();
    }

    // Method to add the register page to the frame
    public void addRegisterPage() {
        add(registerPage);
        revalidate();
    }

    // Method to add the main page to the frame
    public void addMainPage() {
        add(mainPage);
        revalidate();
    }

    // Method to remove the main page from the frame
    public void removeMainPage() {
        remove(mainPage);
        revalidate();
    }
}
