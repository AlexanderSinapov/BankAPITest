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

        if ((mouseX >= 480 && mouseX <= 630) && (mouseY >= 320 && mouseY <= 380)) {
            window.getWindowFrame().removeMainPage();
            window.getWindowFrame().addLoginPage();
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
