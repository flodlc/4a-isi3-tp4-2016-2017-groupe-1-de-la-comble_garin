package view;

import controller.ControllerHomePage;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class HomePagePanel extends JPanel {
    private ControllerHomePage controllerHomePage;

    public HomePagePanel(ControllerHomePage controllerHomePage) {
        this.controllerHomePage = controllerHomePage;
    }

    public void paintComponent(Graphics g) {
        this.setBorder(new EmptyBorder(20, 10, 80, 10));
        this.setLayout(new BorderLayout());
        JPanel title = new JPanel();
        title.setBorder(new EmptyBorder(10, 10, 30, 10));
        JLabel positionLabel = new JLabel(new ImageIcon("images/image.png"));
        title.add(positionLabel);
        title.setBackground(new Color(0, 61, 79));
        this.add(title, BorderLayout.NORTH);
        this.add(this.createButton("MODE ALEATOIRE"), BorderLayout.EAST);
        this.add(this.createButton("MODE SURVIVOR"), BorderLayout.WEST);
    }

    public JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(120, 100));
        button.setBackground(new Color(32, 134, 166));
        button.setBorder(new LineBorder(new Color(102, 172, 194)));
        button.addActionListener(this.controllerHomePage);
        Font font = new Font("Courier", Font.BOLD, 13);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        return button;
    }

}