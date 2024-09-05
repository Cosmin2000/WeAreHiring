package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    JButton admin;
    JButton manager;
    JButton user;
    JLabel image;
    public Menu(){
        super("Menu");
        String path = "src/com/company/image3.jpg";
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Color color = new Color(53, 188, 187);
        setResizable(false);
        setLayout(new GridLayout(2,1));
        setPreferredSize(new Dimension(300,500));
        // Creez butoanele
        admin = new JButton("Admin");
        admin.setPreferredSize(new Dimension(125,35));
        admin.setForeground(Color.WHITE);
        admin.setBackground(color);
        admin.addActionListener(this);
        //manager
        manager = new JButton("Manager");
        manager.setForeground(Color.WHITE);
        manager.setBackground(color);
        manager.setPreferredSize(new Dimension(125,35));
        manager.addActionListener(this);
        //user
        user = new JButton("Profile");
        user.setForeground(Color.WHITE);
        user.setBackground(color);
        user.setPreferredSize(new Dimension(125,35));
        user.addActionListener(this);
        ImageIcon icon = new ImageIcon(path);
        image = new JLabel(icon);
        //Creez panel separat pentru butoane
        JPanel buttons = new JPanel();
        add(image);
        buttons.setLayout(new FlowLayout());
        buttons.add(Box.createVerticalStrut(30),BoxLayout.Y_AXIS,0);
        buttons.add(admin,1);
        buttons.add(Box.createVerticalStrut(10),2);
        buttons.add(manager,BoxLayout.Y_AXIS,3);
        buttons.add(Box.createVerticalStrut(10),4);
        buttons.add(user,BoxLayout.Y_AXIS,5);
        setVisible(true);
        add(buttons);
        pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if ( button != null){
            if ( button == user )
            {
                Profile profile = new Profile();
                setVisible(false);
                profile.setLocation(getX()+getWidth(),getY());
                profile.addWindowListener(new WindowListenerForMe( this));
            }
           else if ( button == manager)
            {
                SelectManager managerPage = new SelectManager();
                managerPage.setLocation(getX()+getWidth(),getY());
                setVisible(false);
                managerPage.addWindowListener(new WindowListenerForMe(this));
            }
           else
            {
                Admin admin1 = new Admin();
                admin1.setLocation(getX()+getWidth(),getY());
                setVisible(false);
                admin1.addWindowListener(new WindowListenerForMe(this));
            }
        }
    }
}
