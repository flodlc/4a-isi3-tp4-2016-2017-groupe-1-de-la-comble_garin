package view;

import controller.ControllerHomePage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Created by Lucas on 02/06/2017.
 */
public class SettingPage extends JFrame {
    private ControllerHomePage controller;

    public SettingPage(ControllerHomePage controller) {
        this.controller = controller;

        this.setTitle("TORTUE : Setting Page");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        JLabel jlabel = new JLabel("Réglages");
        jlabel.setFont(new Font("Verdana",1,20));
        jlabel.setForeground(Color.red);
        panel1.add(jlabel);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(4);
        formatter.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField text1 = new JFormattedTextField(formatter);
        formatter.setMaximum(100);
        JFormattedTextField text2 = new JFormattedTextField(formatter);
        formatter.setMaximum(50);
        JFormattedTextField text3 = new JFormattedTextField(formatter);
        formatter.setMaximum(150);
        JFormattedTextField text4 = new JFormattedTextField(formatter);
        JLabel label1 =new JLabel("Nombre de groupe :");
        JLabel label2 =new JLabel("Nombre de tortue par groupe :");
        JLabel label3 =new JLabel("Portée de la vue d'une tortue :");
        JLabel label4 =new JLabel("Portée de la bombe :");
        label1.setForeground(Color.white);
        label2.setForeground(Color.white);
        label3.setForeground(Color.white);
        label4.setForeground(Color.white);

        panel1.add(label1);
        panel1.add(text1);
        panel1.add(label2);
        panel1.add(text2);
        panel1.add(label3);
        panel1.add(text3);
        panel1.add(label4);
        panel1.add(text4);
        panel1.setBackground(new Color(0, 61, 79));
        this.setContentPane(panel1);
        setResizable(false);
        this.setBackground(new Color(0, 61, 79));

        //favicon
        ImageIcon icon = new ImageIcon("images/favicon.png");
        this.setIconImage(icon.getImage());

        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
        dispose();
    }
}
