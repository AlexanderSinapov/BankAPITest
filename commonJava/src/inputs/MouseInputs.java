package inputs;

import main.Window;
import main.MainPage;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class MouseInputs extends JPanel implements MouseInputListener, MouseMotionListener {

    private Window window;
    private MainPage mainPage;

    public MouseInputs(Window window) {
        this.window = window;
    }

    private String Email = "sinapov.aleksander@gmail.com";
    private String Password = "e3e13d22b16a";

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (window.getCurrentPage() == Window.Page.WELCOME) {
            if ((mouseX >= 480 && mouseX <= 630) && (mouseY >= 320 && mouseY <= 380)) {
                window.getWindowFrame().removeWelcomePage();
                window.getWindowFrame().addLoginPage();
                window.setCurrentPage(Window.Page.LOGIN);
                System.out.println("Login");
            }

            if ((mouseX >= 650 && mouseX <= 800) && (mouseY >= 320 && mouseY <= 380)) {
                window.getWindowFrame().removeWelcomePage();
                window.getWindowFrame().addRegisterPage();
                window.setCurrentPage(Window.Page.REGISTER);
                System.out.println("Register");
            }

            }
//          else if (window.getMainPage() == Window.Page.REGISTER) {
//
//          }
        if (window.getCurrentPage() == Window.Page.LOGIN) {
            if ((mouseX >= 10 && mouseX <= 110) && (mouseY >= 10 && mouseY <= 70)) {
                window.getWindowFrame().removeLoginPage();
                window.getWindowFrame().addWelcomePage();
                window.setCurrentPage(Window.Page.WELCOME);
                System.out.println("Back");
            }
            if ((mouseX >= 580 && mouseX <= 680) && (mouseY >= 320 && mouseY <= 380)) {
                window.getWindowFrame().removeLoginPage();
                window.getWindowFrame().addMainPage();
                window.setCurrentPage(Window.Page.MAIN);
                System.out.println("Logged In");
            }
        }

        if (window.getCurrentPage() == Window.Page.MAIN) {
            if ((mouseX >= 20 && mouseX <= 220) && (mouseY >= 80 && mouseY <= 140)) {
                try {
                    MainPage.removeAccountServicesPanel();
                    MainPage.addCardsMenuLabel();
                }
                catch(Exception error) {
                    System.out.println("asd" + error);
                }
                System.out.println("Opened Cards Menu");
            }

            if ((mouseX >= 20 && mouseX <= 220) && (mouseY >= 10 && mouseY <= 70)) {
                MainPage.AccountServicesPanel();
                System.out.println("Opened Account Service Menu");
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
