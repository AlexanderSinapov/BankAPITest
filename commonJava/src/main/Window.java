package main;

public class Window {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPanel registerPage;
    private WindowFrame windowFrame;

    public Window() {
        this.mainPage = new MainPage(this);
        this.loginPage = new LoginPage(this);
        this.registerPage = new RegisterPanel(this);
        this.windowFrame = new WindowFrame(mainPage, loginPage, registerPage);
        this.windowFrame.addMainPage();
    }

    public WindowFrame getWindowFrame() {
        return windowFrame;
    }
}
