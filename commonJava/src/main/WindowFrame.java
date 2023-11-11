package main;

import javax.swing.*;

public class WindowFrame extends JFrame {

    private WelcomePage welcomePage;
    private LoginPage loginPage;
    private RegisterPanel registerPage;
    private MainPage mainPage;
    private ImageIcon image;


    public WindowFrame(WelcomePage panel, LoginPage loginPage, RegisterPanel registerPage, MainPage mainPage)  {

        this.welcomePage = panel;
        this.loginPage = loginPage;
        this.registerPage = registerPage;
        this.mainPage = mainPage;
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

    }

}
