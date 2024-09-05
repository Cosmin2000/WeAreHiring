package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DepartmentsOnGUI extends JFrame {
    DefaultListModel<Department> deps;
    JList<Department> departs_list;
    JList<Employee> employee_list;
    JList<String> jobs_list;
    JButton show_jobs;
    JButton show_employees;
    JButton show_salary;
    JLabel salary;
    JLabel salary_label;
    JLabel jobs_label;
    JLabel employees_label;
    JPanel panel;

    public DepartmentsOnGUI (ArrayList<Department> departments) {
        super("Departments");
        Color color = new Color(53, 188, 187);
        setBackground(Color.white);
        setVisible(true);
        setLayout(new GridLayout(2,2));
        setMinimumSize(new Dimension(900,500));
        //Butoanele
        show_jobs = new JButton("Show jobs");
        show_employees = new JButton("Show employees");
        show_salary = new JButton("Show budget");
        show_jobs.setEnabled(false);
        show_employees.setEnabled(false);
        show_salary.setEnabled(false);
        //Label-uri
        jobs_label = new JLabel("Jobs");
        employees_label = new JLabel("Employees");
        salary = new JLabel();
        salary_label = new JLabel("Salary Budget");
        jobs_label.setVisible(false);
        employees_label.setVisible(false);
        salary_label.setVisible(false);
        salary.setVisible(false);

        JPanel salary_panel = new JPanel();
        JPanel jobs_panel = new JPanel();
        panel = new JPanel();
        salary_panel.setBackground(Color.white);
        jobs_panel.setBackground(Color.white);
        panel.setBackground(Color.white);
        employee_list = new JList<>();
        jobs_list = new JList<>();
        employee_list.setSelectionBackground(Color.white);
        employee_list.setSelectionForeground(color);
        jobs_list.setSelectionBackground(Color.white);
        jobs_list.setSelectionForeground(color);
        JScrollPane employee_scroll = new JScrollPane(employee_list);
        employee_scroll.setVisible(false);
        JScrollPane jobs_scroll = new JScrollPane(jobs_list);
        deps = new DefaultListModel<>();
        for ( Department department: departments)
            deps.addElement(department);
        departs_list = new JList<>(deps);
        departs_list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        departs_list.setSelectionBackground(Color.white);
        departs_list.setSelectionForeground(color);
        departs_list.setBorder(BorderFactory.createEmptyBorder());
        departs_list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if ( e.getValueIsAdjusting()){
                    show_jobs.setEnabled(true);
                    show_employees.setEnabled(true);
                    show_salary.setEnabled(true);
                    show_salary.setForeground(Color.WHITE);
                    show_salary.setBackground(color);
                    show_jobs.setForeground(Color.WHITE);
                    show_jobs.setBackground(color);
                    show_employees.setForeground(Color.WHITE);
                    show_employees.setBackground(color);
                }
                if (!e.getValueIsAdjusting()){
                    jobs_scroll.setVisible(false);
                    jobs_label.setVisible(false);
                    salary.setVisible(false);
                    employee_scroll.setVisible(false);
                    salary_label.setVisible(false);
                    employees_label.setVisible(false);
                }
            }
        });
        JScrollPane departs_scroll = new JScrollPane(departs_list);
        add(departs_scroll);
        show_jobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                if ( button != null){
                    Department department = departs_list.getSelectedValue();
                    DefaultListModel<String> job_model = new DefaultListModel<>();
                    for (Job job : department.getJobs())
                    {
                        if (!job_model.contains(job.nume_job))
                            job_model.addElement(job.nume_job);
                    }
                    for (Employee employee: department.getEmployees()) {
                        if (!job_model.contains(employee.getJobName()))
                            job_model.addElement(employee.getJobName());
                    }
                    jobs_list.setModel(job_model);
                    jobs_label.setVisible(true);
                    jobs_scroll.setVisible(true);
                    jobs_panel.add(jobs_scroll);
                    }
            }
        });
        show_employees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button =(JButton)e.getSource();
                if ( button != null){
                    Department department = departs_list.getSelectedValue();
                    DefaultListModel<Employee> emodel = new DefaultListModel<>();
                    for (Employee employee : department.getEmployees())
                        emodel.addElement(employee);
                    employee_list.setModel(emodel);
                    employees_label.setVisible(true);
                    employee_scroll.setVisible(true);
                    salary_panel.add(employee_scroll);
                }
            }
        });
        show_salary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button =(JButton)e.getSource();
                if ( button != null){
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    Department department = departs_list.getSelectedValue();
                    Double salariul = department.getTotalSalaryBudget();
                    salary.setVisible(true);
                    salary_label.setVisible(true);
                    salary_panel.setVisible(true);
                    salary.setText(decimalFormat.format(salariul));
                }
            }
        });

        jobs_panel.setLayout(new BoxLayout(jobs_panel,BoxLayout.PAGE_AXIS));
        jobs_panel.add(jobs_label);
        jobs_panel.add(Box.createVerticalStrut(10));

        JPanel totalEmployeePanel = new JPanel();
        totalEmployeePanel.setBackground(Color.white);
        totalEmployeePanel.setLayout(new BoxLayout(totalEmployeePanel,BoxLayout.X_AXIS));
        //Panel pentru a arata salariul
        JPanel budgetPanel = new JPanel();
        budgetPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        budgetPanel.setLayout(new BoxLayout(budgetPanel,BoxLayout.Y_AXIS));
        budgetPanel.setBackground(Color.white);
        budgetPanel.add(Box.createHorizontalStrut(10));
        budgetPanel.add(salary_label,BorderLayout.CENTER,0);
        budgetPanel.add(Box.createVerticalStrut(10),1);
        budgetPanel.add(salary,2);
        //Panel pentru a afisa angajatii
        salary_panel.setLayout(new BoxLayout(salary_panel,BoxLayout.Y_AXIS));
        salary_panel.add(employees_label);
        salary_panel.add(Box.createVerticalStrut(10));
        //Panel pentru care afiseaza salariul si angajatii
        totalEmployeePanel.add(salary_panel);
        totalEmployeePanel.add(budgetPanel);
        //Panel din jumatarea dreapta in care se afiseaza informatii
        panel.add(show_jobs);
        panel.add(show_employees);
        panel.add(show_salary);

        add(totalEmployeePanel);
        add(panel);
        add(jobs_panel);

    }
}
