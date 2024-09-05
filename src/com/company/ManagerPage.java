package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManagerPage extends JFrame{
    JList<Request<Job, Consumer>> requests;
    JButton accept;
    JButton reject;
    JLabel requestLabel;
    JProgressBar progressBar;
    Timer t;
    int contor = 0;

    public ManagerPage(Manager manager){
        super("Manager Page");
        setResizable(false);
        setVisible(true);
        setLayout(new GridLayout(2,2));
        setMinimumSize(new Dimension(800,400));
        Color color = new Color(53, 188, 187);
        JPanel progressPanel = new JPanel();
        progressPanel.setBackground(Color.white);
        progressPanel.setLayout(new BoxLayout(progressPanel,BoxLayout.Y_AXIS));

        progressBar = new JProgressBar(0,100);
        progressBar.setSize(200,400);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBackground(color);
        progressBar.setForeground(color);
        progressBar.addChangeListener(e -> {
            if ( progressBar.getValue() == 100){
                t.stop();
                contor = 0;
                progressBar.setString("Done");

            }
        });
        t = new Timer(10, e -> {
            if (contor <= 100){
                progressBar.setValue(++contor);
            }
        });

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        requestLabel = new JLabel("Requests");
        String path = "src/com/company/iconfinder_Accept_85350.jpg";
        String path1 ="src/com/company/cancel_delete_remove_stop-512.jpg";
        ImageIcon icon = new ImageIcon(path);
        accept = new JButton(icon);
        accept.setBorderPainted(false);
        ImageIcon rej = new ImageIcon(path1);
        reject = new JButton(rej);
        accept.setPreferredSize(new Dimension(100,36));
        reject.setPreferredSize(new Dimension(100,36));
        accept.setEnabled(false);
        reject.setEnabled(false);
        reject.setBorderPainted(false);
        buttons.add(accept);
        buttons.setBackground(Color.white);
        buttons.add(reject);
        progressPanel.add(buttons);
        progressPanel.add(progressBar);
        DefaultListModel<Request<Job,Consumer>> model = new DefaultListModel<>();
        for (Request<Job,Consumer> request : manager.getCereri_angajare()){
            model.addElement(request);
        }
        requests = new JList<>(model);
        requests.setBorder(null);
        requests.setSelectionForeground(color);
        requests.setSelectionBackground(Color.white);
        requests.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(requests);
        requests.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()){
                accept.setEnabled(true);
                reject.setEnabled(true);
                accept.setBackground(Color.white);
                accept.setForeground(Color.WHITE);
                reject.setBackground(Color.white);
                reject.setForeground(Color.WHITE);
            }

        });
        accept.addActionListener(e -> {
            JButton button = (JButton)e.getSource();
            if(button != null){
                progressBar.setString(null);
                t.start();
                progressBar.setValue(contor);
                Application application = Application.getInstance();
                Request<Job,Consumer> request = requests.getSelectedValue();
                User user =(User)request.getValue1();
                Job job = request.getKey();
                if ( application.getUsers().contains(user)){
                    if (job.nr_angajati_nevoie > 0) {
                        model.removeElement(request);
                        application.getUsers().remove(user);
                        job.candidati.remove(user);
                        Employee employee = user.convert();
                        employee.setSalariul(job.salariul_primit);
                        employee.setNume_companie(job.nume_companie);
                        for (String comp : user.companies) {
                            Company compan = application.getCompany(comp);
                            compan.removeObserver(user);
                        }
                        Company company = application.getCompany(job.nume_companie);
                        for (Department dep : company.getDepartaments()) {
                            ArrayList<Job> jobs_dep = dep.getJobs();
                            if (jobs_dep.contains(job)) {
                                dep.add(employee);
                                break;
                            }
                        }
                        job.nr_angajati_nevoie--;
                        if (job.nr_angajati_nevoie == 0)
                        {
                            job.disponibilitate = false;
                        }
                    }
                    else
                    {
                        JOptionPane error = new JOptionPane("Acest Job este inchis", JOptionPane.ERROR_MESSAGE);
                        JDialog dialog =error.createDialog("Error");
                        dialog.setVisible(true);
                        model.removeElement(request);
                    }
                }
                else {
                    JOptionPane error = new JOptionPane("Candidatul a fost angajat deja", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog =error.createDialog("Error");
                    dialog.setVisible(true);
                    job.candidati.remove(user);
                    model.removeElement(request);
                }
                manager.getCereri_angajare().remove(request);
                accept.setEnabled(false);
                reject.setEnabled(false);
            }
        });
        reject.addActionListener(e -> {
            JButton button =(JButton)e.getSource();
            if ( button != null){
                progressBar.setString(null);
                t.start();
                progressBar.setValue(contor);
                Application application = Application.getInstance();
                Request<Job,Consumer> request = requests.getSelectedValue();
                request.getKey().candidati.remove(request.getValue1());
                model.removeElement(request);
                manager.getCereri_angajare().remove(request);
                if (application.getCompany(request.getKey().nume_companie).observers.contains(request.getValue1()))
                    application.getCompany(request.getKey().nume_companie).notifyObserver((Observer) request.getValue1(), new Notification("Sorry, you're rejected from " + manager.nume_companie + " Please try again later"));
                reject.setEnabled(false);
                accept.setEnabled(false);
            }
        });
        JPanel requestPanel= new JPanel();
        requestPanel.setBackground(Color.white);
        requestPanel.setLayout(new BoxLayout(requestPanel,BoxLayout.PAGE_AXIS));
        requestPanel.add(Box.createHorizontalStrut(90),0);
        requestPanel.add(requestLabel,1);
        requestPanel.add(Box.createVerticalStrut(10),2);
        requestPanel.add(scroll,3);
        add(requestPanel);
        add(progressPanel);

    }

}
