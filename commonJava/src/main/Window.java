package main;

public class Window {
    private MainPage mainPage;
    private LoginPage loginPage;
    private WindowFrame windowFrame;

    public Window() {
        this.mainPage = new MainPage(this);
        this.loginPage = new LoginPage(this);
        this.windowFrame = new WindowFrame(mainPage, loginPage);
        this.windowFrame.addMainPage();
    }

    public WindowFrame getWindowFrame() {
        return windowFrame;
    }
}
