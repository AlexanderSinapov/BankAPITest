package main;
import javax.swing.*;
import java.awt.*;
public class Window extends JPanel {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPanel registerPage;
    private WindowFrame windowFrame;

    private Page currentPage;

    public enum Page {
        MAIN,
        LOGIN,
        REGISTER
    }

    public Window() {
        this.mainPage = new MainPage(this);
        this.loginPage = new LoginPage(this);
        this.registerPage = new RegisterPanel(this);
        this.windowFrame = new WindowFrame(mainPage, loginPage, registerPage);
        this.windowFrame.addMainPage();

        this.setCurrentPage(Page.MAIN);
        this.windowFrame.addMainPage();
    }

    public WindowFrame getWindowFrame() {
        return windowFrame;
    }

    public Page getMainPage() {
        return currentPage;
    }

    public void setCurrentPage(Page page) {
        this.currentPage = page;
    }
}
