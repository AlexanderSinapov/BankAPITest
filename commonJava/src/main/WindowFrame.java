package main;

import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame {

    public WelcomePage welcomePage;
    public LoginPage loginPage;
    public RegisterPanel registerPage;
    private MainPage mainPage;
    private ImageIcon image;
    private ForgotPasswordPage fpPage;


    public WindowFrame(WelcomePage panel, LoginPage loginPage, RegisterPanel registerPage, MainPage mainPage, ForgotPasswordPage forgotPassword)  {

        this.welcomePage = panel;
        this.loginPage = loginPage;
        this.registerPage = registerPage;
        this.mainPage = mainPage;
        this.fpPage = forgotPassword;
        image = new ImageIcon("commonJava/Resources/Images/VistaTrustBankLogo.png");

        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("VistaTrust - Bank");
        setIconImage(image.getImage());

        addWelcomePage();
        setVisible(true);
    }

    public void removeWelcomePage() {
        remove(welcomePage);
        revalidate();
    }
    public void addWelcomePage() {
        add(welcomePage);
        revalidate();
    }

    public void addForgotPage(){
        add(fpPage);
        revalidate();
    }

    public void removeForgotPage(){
        remove(fpPage);
        revalidate();
    }

    public void removeLoginPage() {
        remove(loginPage);
        revalidate();
    }
    public void addLoginPage() {
        add(loginPage);
        revalidate();
    }


    public void removeRegisterPage() {
        remove(registerPage);
        revalidate();
    }
    public void addRegisterPage() {
        add(registerPage);
        revalidate();
    }

    public void addMainPage() {
        add(mainPage);
        revalidate();
    }
    public void removeMainPage() {
        remove(mainPage);
        revalidate();
    }

}
