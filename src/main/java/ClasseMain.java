import controller.Controller;
import view.SimpleLogoView;

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
                Controller controller = new Controller();
                controller.getSimpleLogoView().setVisible(true);
            }
        });

    }
}
