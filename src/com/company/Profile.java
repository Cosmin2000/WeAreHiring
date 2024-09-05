package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile extends JFrame implements ActionListener {
    JLabel userLabel;
    JTextField userField;
    Application application = Application.getInstance();
    JButton button;
    int contor;
    Timer t;
    boolean found ;
    JProgressBar progressBar;
    User user = null;
    public Profile(){
        super("Search");
        JFrame frame = this;
        setVisible(true);
        setMinimumSize(new Dimension(500,400));
        Color color = new Color(53, 188, 187);
        userLabel = new JLabel("First Name & Surname");
        userField = new JTextField();
        button =  new JButton("Find");
        button.setBackground(color);
        button.setForeground(Color.white);
        userField.setPreferredSize(new Dimension(200,26));
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        GroupLayout groupLayout = new GroupLayout(panel);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        button.addActionListener(this);
        panel.add(userLabel);
        panel.add(userField);
        panel.add(button);
        setLayout(new BorderLayout());
        add(panel,BorderLayout.CENTER);
        progressBar = new JProgressBar(0,100);
        progressBar.setBackground(Color.white);
        progressBar.setForeground(color);
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
        progressBar.setBounds(0,0,420,50);
        progressBar.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if ( progressBar.getValue() == 100){
                    t.stop();
                    contor = 0;
                    if (found)
                    {   frame.setVisible(false);
                        progressBar.setString("Found");
                        userProfile userProfile = new userProfile(user);
                        userProfile.addWindowListener(new WindowListenerForMe(frame));
                    }
                    else
                        progressBar.setString("Not Found");

                }
            }
        });
        add(progressBar,BorderLayout.SOUTH);

        t = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contor <= 100){
                    progressBar.setValue(++contor);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        if ( button != null){
            progressBar.setString(null);
            t.start();
            progressBar.setValue(contor);
            String name = userField.getText();
            String[] names = name.split(" ");
            for ( int l = 2; l < names.length;l++) {
                names[l - 1].concat(" " + names[l]);
            }
            found = false;
            for (User user1 : application.getUsers()) {
                if (user1.resume.getInfo().getFirstName().equalsIgnoreCase(names[0]) && user1.resume.getInfo().getName().equalsIgnoreCase(names[1])) {
                    found = true;
                    user = user1;
                }
            }
        }
    }
}
