package main;
import javax.swing.*;

public class Window extends JPanel {
    private WelcomePage welcomePage;
    private LoginPage loginPage;
    private RegisterPanel registerPage;
    private DebitCardTemplate DebitCard;
    private MainPage mainPage;
    private WindowFrame windowFrame;
    private ForgotPasswordPage fpPage;

    private Page currentPage;

    public enum Page {
        MAIN,
        LOGIN,
        REGISTER,
        WELCOME
    }

    public Window() {
        this.welcomePage = new WelcomePage(this);
        this.loginPage = new LoginPage(this);
        this.registerPage = new RegisterPanel(this);
        this.mainPage = new MainPage(this);
        this.fpPage = new ForgotPasswordPage(this);
        this.DebitCard = new DebitCardTemplate(this);
        this.windowFrame = new WindowFrame(welcomePage, loginPage, registerPage, mainPage, fpPage, DebitCard);
        this.windowFrame.addWelcomePage();

        this.setCurrentPage(Page.WELCOME);
        this.windowFrame.addWelcomePage();
    }

    public WindowFrame getWindowFrame() {
        return windowFrame;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page page) {
        this.currentPage = page;
    }
}
