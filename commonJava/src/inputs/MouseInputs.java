package inputs;

import main.Window;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseInputListener, MouseMotionListener {

    private Window window;

    public MouseInputs(Window window) {
        this.window = window;
    }

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

        if (window.getMainPage() == Window.Page.WELCOME) {
            if ((mouseX >= 480 && mouseX <= 630) && (mouseY >= 320 && mouseY <= 380)) {
                window.getWindowFrame().removeWelcomePage();
                window.getWindowFrame().addLoginPage();
                window.setCurrentPage(Window.Page.LOGIN);
                System.out.println("Login");
            }

            if ((mouseX >= 650 && mouseX <= 800) && (mouseY >= 320 && mouseY <= 380)) {
                window.getWindowFrame().removeWelcomePage();
                window.getWindowFrame().addRegisterPage();
                System.out.println("Register");
            }
            else if (window.getMainPage() == Window.Page.LOGIN) {
                if ((mouseX >= 10 && mouseX <= 110) && (mouseY >= 10 && mouseY <= 70)) {
                    window.getWindowFrame().removeLoginPage();
                    window.getWindowFrame().addWelcomePage();
                    window.setCurrentPage(Window.Page.WELCOME);
                }

            }
//          else if (window.getMainPage() == Window.Page.REGISTER) {
//
//        }
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
