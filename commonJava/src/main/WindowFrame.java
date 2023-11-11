package main;

import javax.swing.*;

public class WindowFrame extends JFrame {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPanel registerPage;
    private ImageIcon image;


    public WindowFrame(MainPage panel, LoginPage loginPage, RegisterPanel registerPage)  {

        this.mainPage = panel;
        this.loginPage = loginPage;
        this.registerPage = registerPage;
        image = new ImageIcon("commonJava/Resources/Images/VistaTrustBankLogo.png");

        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("VistaTrust - Bank");
        setIconImage(image.getImage());

        addMainPage();
        setVisible(true);
    }

    public void removeMainPage() {
        remove(mainPage);
        revalidate();
    }
    public void addMainPage() {
        add(mainPage);
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

}
