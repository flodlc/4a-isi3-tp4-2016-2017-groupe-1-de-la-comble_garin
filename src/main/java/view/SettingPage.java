package view;

import controller.ControllerHomePage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Created by Lucas on 02/06/2017.
 */
public class SettingPage extends JFrame {
    private ControllerHomePage controller;
    private JFormattedTextField nbGroupe;
    private JFormattedTextField nbTortues;
    private JFormattedTextField champVision;
    private JFormattedTextField porteeBombe;

    public SettingPage(ControllerHomePage controller) {
        this.controller = controller;

        this.setTitle("TORTUE : Setting Page");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        JLabel jlabel = new JLabel("Réglages");
        jlabel.setFont(new Font("Verdana", 1, 20));
        jlabel.setForeground(Color.red);
        panel1.add(jlabel);

        nbGroupe = createFormattedTextField(0, 4, 1);
        nbTortues = createFormattedTextField(0, 250, 200);
        champVision = createFormattedTextField(0, 360, 120);
        porteeBombe = createFormattedTextField(0, 100, 70);
        JLabel label1 = new JLabel("Nombre de groupe (0 - 4) :");
        JLabel label2 = new JLabel("Nombre de tortue par groupe (0 - 250) :");
        JLabel label3 = new JLabel("Champ de vision d'une tortue (0 - 360 en dégrés) :");
        JLabel label4 = new JLabel("Portée de la bombe (0 - 100) :");
        label1.setForeground(Color.white);
        label2.setForeground(Color.white);
        label3.setForeground(Color.white);
        label4.setForeground(Color.white);

        panel1.add(label1);
        panel1.add(nbGroupe);
        panel1.add(label2);
        panel1.add(nbTortues);
        panel1.add(label3);
        panel1.add(champVision);
        panel1.add(label4);
        panel1.add(porteeBombe);
        panel1.setBackground(new Color(0, 61, 79));
        this.setContentPane(panel1);
        setResizable(false);
        this.setBackground(new Color(0, 61, 79));
        JButton button = new JButton("VALIDER");
        button.setPreferredSize(new Dimension(120, 100));
        button.setBackground(new Color(32, 134, 166));
        button.setBorder(new LineBorder(new Color(102, 172, 194)));
        button.addActionListener(this.controller);
        Font font = new Font("Courier", Font.BOLD, 13);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        this.add(button);
        //favicon
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/favicon.png"));
        this.setIconImage(icon.getImage());

        this.setVisible(true);
    }

    private JFormattedTextField createFormattedTextField(int min, int max, int defaultValue) {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(min);
        formatter.setMaximum(max);
        formatter.setAllowsInvalid(true);
        formatter.setCommitsOnValidEdit(false);
        JFormattedTextField jFormattedTextField = new JFormattedTextField(formatter);
        jFormattedTextField.setValue(defaultValue);
        return jFormattedTextField;
    }

    public void close() {
        this.setVisible(false);
        dispose();
    }

    public int getNbGroupe() {
        try {
            Number n = (Number) this.nbGroupe.getValue();
            return n.intValue();
        } catch (Exception e) {
            return 1;
        }
    }

    public int getNbTortues() {
        try {
            Number n = (Number) this.nbTortues.getValue();
            return n.intValue();
        } catch (Exception e) {
            return 1;
        }
    }

    public int getPorteeTortue() {
        try {
            Number n = (Number) this.champVision.getValue();
            return n.intValue();
        } catch (Exception e) {
            return 1;
        }
    }

    public int getPorteeBombe() {
        try {
            Number n = (Number) this.porteeBombe.getValue();
            return n.intValue();
        } catch (Exception e) {
            return 1;
        }
    }


}
