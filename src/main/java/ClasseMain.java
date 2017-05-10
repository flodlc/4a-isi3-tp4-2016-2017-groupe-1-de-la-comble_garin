import controller.ControllerSimpleLogo;

import javax.swing.*;

/**
 * Created by calvi on 26/04/2017.
 */
public class ClasseMain {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                ControllerSimpleLogo controller = new ControllerSimpleLogo();
                controller.getSimpleLogoView().setVisible(true);
            }
        });

    }
}
