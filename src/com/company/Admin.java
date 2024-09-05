package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame  implements ListSelectionListener {
    Application application = Application.getInstance();
    JPanel users_panel;
    JLabel usersLabel;
    JLabel companiesLabel;
    JList<Company> companies_list;
    JList<User> users_list;
    JButton show_departments;
    DefaultListModel<Company> companyDefaultListModel;
    DefaultListModel<User> userDefaultListModel;
    JPanel companies_panel;
    public Admin () {
        super("Admin Page");
        JFrame frame = this;
        setLayout(new GridLayout(1,2));
        setMinimumSize(new Dimension(550,500));
        setVisible(true);
        Color color = new Color(53, 188, 187);
        usersLabel = new JLabel("Users");
        companiesLabel = new JLabel("Companies");
        //Panel pentru Users
        users_panel = new JPanel();
        users_panel.setBackground(Color.white);
        users_panel.setLayout(new BoxLayout(users_panel,BoxLayout.Y_AXIS));
        users_panel.add(Box.createVerticalStrut(10));
        users_panel.add(usersLabel);
        users_panel.add(Box.createVerticalStrut(20));
        //Panel pentru companii
        companies_panel = new JPanel();
        companies_panel.setBackground(Color.WHITE);
        companies_panel.setLayout(new BoxLayout(companies_panel,BoxLayout.Y_AXIS));
        companies_panel.add(Box.createVerticalStrut(10));
        companies_panel.add(companiesLabel,BorderLayout.CENTER);
        companies_panel.add(Box.createVerticalStrut(20));
        companyDefaultListModel = new DefaultListModel<>();
        userDefaultListModel = new DefaultListModel<>();
        for ( Company company: application.getCompanies()){
            companyDefaultListModel.addElement(company);
        }
        for ( User user : application.getUsers()){
            userDefaultListModel.addElement(user);
        }
        companies_list = new JList<>(companyDefaultListModel);
        users_list = new JList<>(userDefaultListModel);
        users_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        users_list.setSelectionForeground(color);
        users_list.setSelectionBackground(Color.white);
        companies_list.addListSelectionListener(this);
        companies_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        companies_list.setSelectionForeground(color);
        companies_list.setSelectionBackground(Color.white);
        JScrollPane scroll_company = new JScrollPane(companies_list);
        JScrollPane scroll_user = new JScrollPane(users_list);
        users_panel.add(scroll_user);
        users_panel.add(Box.createVerticalStrut(46));
        companies_panel.add(scroll_company,BorderLayout.WEST);
        show_departments = new JButton("Show Departments");
        show_departments.setEnabled(false);
        show_departments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                if ( button != null)
                {
                    Company company=companies_list.getSelectedValue();
                    DepartmentsOnGUI department_class = new DepartmentsOnGUI(company.getDepartaments());
                    setVisible(false);
                    department_class.addWindowListener(new WindowListenerForMe(frame));
                }
            }
        });

        companies_panel.add(Box.createVerticalStrut(10));
        companies_panel.add(show_departments,BorderLayout.CENTER);
        companies_panel.add(Box.createVerticalStrut(10));
        add(users_panel);
        add(companies_panel);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if ( !e.getValueIsAdjusting()){
            Color color = new Color(53, 188, 187);
            show_departments.setForeground(Color.WHITE);
            show_departments.setBackground(color);
            show_departments.setEnabled(true);
        }
    }
}
