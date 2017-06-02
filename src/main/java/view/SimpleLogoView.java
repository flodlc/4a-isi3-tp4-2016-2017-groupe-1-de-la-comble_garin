package view;


import controller.ControllerMain;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/*************************************************************************

 Un petit Logo minimal qui devra etre ameliore par la suite

 J. Ferber - 1999-2001

 Cours de DESS TNI - Montpellier II

 @version 2.0
 @date 25/09/


 **************************************************************************/


public class SimpleLogoView extends AbstractSimpleLogoView {

    protected ControllerMain controller;

    public SimpleLogoView(ControllerMain controller) {
        super();
        this.feuilleDessinView = new FeuilleDessinView(this);
        this.controller = controller;
        logoInit();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }
}