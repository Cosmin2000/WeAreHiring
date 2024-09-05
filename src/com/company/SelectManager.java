package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectManager extends JFrame implements ListSelectionListener {
    JList<Manager> managerList;
    JLabel managerLabel;
    JButton button;
    Color color = new Color(53, 188, 187);

    public  SelectManager(){
        super("Manager Mode");
        JFrame frame = this;
        setResizable(false);
        setVisible(true);
        setMinimumSize(new Dimension(500,400));
        managerLabel = new JLabel("Select a Manager");
        button = new JButton("Manager Page");
        button.setEnabled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() != null) {
                    Manager manager = managerList.getSelectedValue();
                    ManagerPage managerPage = new ManagerPage(manager);
                    managerPage.setVisible(true);
                    setVisible(false);
                    managerPage.addWindowListener(new WindowListenerForMe(frame));
                }
            }
        });
        DefaultListModel<Manager> managerModel = new DefaultListModel<>();
        Application application = Application.getInstance();
        for (Company company : application.getCompanies()){
            managerModel.addElement(company.getManager());
        }
        managerList = new JList<>(managerModel);
        managerList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        managerList.setSelectionBackground(Color.white);
        managerList.setSelectionForeground(color);
        managerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()){
                    button.setBackground(color);
                    button.setForeground(Color.WHITE);
                    button.setEnabled(true);
                }
            }
        });
        JScrollPane scroll = new JScrollPane(managerList);
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(Box.createHorizontalStrut(130),0);
        panel.add(managerLabel,1);
        panel.add(Box.createVerticalStrut(10),2);
        panel.add(scroll,3);
        panel.add(Box.createVerticalStrut(10),4);
        panel.add(button,5);
        panel.add(Box.createVerticalStrut(20));
        add(panel);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()){
            button.setBackground(color);
            button.setForeground(Color.WHITE);
            button.setEnabled(true);
        }
    }
}
