package com.company;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class userProfile extends JFrame{
    JLabel name;
    JLabel surname;
    JLabel birthdate;
    JLabel phone;
    JLabel genre;
    JLabel email;
    JLabel languages;
    JLabel companies;

    public userProfile(User user){
        super("User Info");
        setVisible(true);
        setBackground(Color.white);
        setMinimumSize(new Dimension(1200,500));
        setLayout(new GridLayout(3,2));
        //info contine info1 si info2 fiind creat pentru asezare
        JPanel info = new JPanel();
        info.setBackground(Color.white);
        JPanel dates = new JPanel();
        dates.setLayout(new BoxLayout(dates,BoxLayout.X_AXIS));
        JLabel informationLabel = new JLabel("INFO");
        //Facem un panel info1 care tipul de informatie
        JPanel info1 = new JPanel();
        info1.setBackground(Color.white);
        info1.setLayout(new BoxLayout(info1,BoxLayout.X_AXIS));
        //info2 contine datele utilizatorului
        JPanel info2 = new JPanel();
        info2.setBackground(Color.white);
        info2.setLayout(new BoxLayout(info2,BoxLayout.X_AXIS));
        name = new JLabel("First Name:");
        JLabel name1 = new JLabel(user.resume.getInfo().getFirstName());
        surname = new JLabel("Surname:");
        JLabel surname1 = new JLabel(user.resume.getInfo().getName());
        birthdate = new JLabel("Birthdate:");
        JLabel birthdate1 = new JLabel(user.resume.getInfo().getData());
        phone = new JLabel("Phone:");
        JLabel phone1 = new JLabel(user.resume.getInfo().getTelefon());
        genre = new JLabel("Genre:");
        JLabel genre1 = new JLabel(user.resume.getInfo().getSex());
        email = new JLabel("Email:");
        JLabel email1 = new JLabel(user.resume.getInfo().getEmail());
        languages = new JLabel("Languages:");
        companies = new JLabel("Interested Companies:");
        JLabel languagesLevel = new JLabel("Languages level:");
        info.setLayout(new GridLayout(2,1));

        info1.add(name);
        info1.add(Box.createHorizontalStrut(5));
        info1.add(name1);
        info1.add(Box.createHorizontalStrut(25));
        info1.add(surname);
        info1.add(Box.createHorizontalStrut(5));
        info1.add(surname1);
        info1.add(Box.createHorizontalStrut(25));
        info1.add(birthdate);
        info1.add(Box.createHorizontalStrut(5));
        info1.add(birthdate1);
        info2.add(phone);
        info2.add(Box.createHorizontalStrut(5));
        info2.add(phone1);
        info2.add(Box.createHorizontalStrut(25));
        info2.add(genre);
        info2.add(Box.createHorizontalStrut(5));
        info2.add(genre1);
        info2.add(Box.createHorizontalStrut(25));
        info2.add(email);
        info2.add(Box.createHorizontalStrut(5));
        info2.add(email1);
        info1.add(Box.createHorizontalStrut(25));
        info1.add(languages);
        info1.add(Box.createHorizontalStrut(5));
        for (String lang : user.resume.getInfo().getLanguages().get(0)){
            JLabel language;
            if (!lang.equals(user.resume.getInfo().getLanguages().get(0).get(user.resume.getInfo().getLanguages().get(0).size()-1)))
                language = new JLabel(lang+", ");
            else
                language = new JLabel(lang);
            info1.add(language);
        }
        info1.add(Box.createHorizontalStrut(25));
        info1.add(companies);
        info1.add(Box.createHorizontalStrut(5));
        for (String company : user.companies){
            JLabel comp;
            if ( !company.equals(user.companies.get(user.companies.size()-1)))
                comp = new JLabel(company+", ");
            else
                comp = new JLabel(company);
            info1.add(comp);
        }


        info2.add(Box.createHorizontalStrut(25));
        info2.add(languagesLevel);
        info2.add(Box.createHorizontalStrut(5));

        for (String level : user.resume.getInfo().getLanguages().get(1)){
            JLabel language;
            if (!level.equals(user.resume.getInfo().getLanguages().get(1).get(user.resume.getInfo().getLanguages().get(1).size()-1)))
                language = new JLabel(level+", ");
            else
                language = new JLabel(level);
            info2.add(language);
        }

        info.add(info1);
        info.add(info2);
        //Panel-ul dates contine informatiile user-ului si un label
        dates.setBackground(Color.white);
        dates.add(Box.createHorizontalStrut(5),0);
        dates.add(informationLabel,1);
        dates.add(Box.createHorizontalStrut(60),2);
        dates.add(info,3);
        add(dates);
        //Experiences.
        JLabel experienceLabel = new JLabel("Experience");
        JPanel experiencePanel= new JPanel();
        experiencePanel.setBackground(Color.white);
        //pan.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        experiencePanel.setLayout(new BoxLayout(experiencePanel,BoxLayout.X_AXIS));
        experiencePanel.add(Box.createHorizontalStrut(5),0);
        experiencePanel.add(experienceLabel,1);
        experiencePanel.add(Box.createHorizontalStrut(10),2);
        for (Experience experience : user.resume.getExperiences()){
            JPanel exp = new JPanel();
            exp.setBackground(Color.white);
            exp.setAlignmentX(JPanel.LEFT_ALIGNMENT);
            exp.setLayout(new BoxLayout(exp,BoxLayout.Y_AXIS));
            JLabel data_inceput = new JLabel(experience.data_inceput.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            JLabel data_sfarsit;
            if (experience.data_final != null)
                data_sfarsit = new JLabel(experience.data_final.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            else
                data_sfarsit = new JLabel("null");
            JLabel company = new JLabel(experience.compania);
            JLabel pozitia = new JLabel(experience.pozitia);
            exp.add(data_inceput);
            exp.add(Box.createVerticalStrut(10));
            exp.add(data_sfarsit);
            exp.add(Box.createVerticalStrut(10));
            exp.add(company);
            exp.add(Box.createVerticalStrut(10));
            exp.add(pozitia);
            experiencePanel.add(Box.createHorizontalStrut(15));
            experiencePanel.add(exp);
        }

        //Education


        JLabel educationLabel = new JLabel("Education");
        // add(experienceLabel);
        JPanel eduPanel = new JPanel();
        eduPanel.setBackground(Color.white);
        eduPanel.setLayout(new BoxLayout(eduPanel,BoxLayout.X_AXIS));
        eduPanel.add(Box.createHorizontalStrut(5),0);
        eduPanel.add(educationLabel,1);
        //Box box = new Box(BoxLayout.X_AXIS);
        //box.setBackground(Color.white);
        eduPanel.add(Box.createHorizontalStrut(15),2);
        for (Education education : user.resume.getEducations()){
            JPanel edu = new JPanel();
            edu.setBackground(Color.white);
            edu.setLayout(new BoxLayout(edu,BoxLayout.Y_AXIS));
            JLabel data_inceput = new JLabel(education.data_inceput.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            JLabel data_sfarsit;
            if (education.data_sfarsit != null)
                data_sfarsit = new JLabel(education.data_sfarsit.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            else
                data_sfarsit = new JLabel("null");
            JLabel institutie = new JLabel(education.institutie);
            JLabel level = new JLabel(education.nivel_de_educatie);
            JLabel media= new JLabel(education.media_de_finalizare.toString());
            edu.add(level);
            edu.add(Box.createVerticalStrut(10));
            edu.add(institutie);
            edu.add(Box.createVerticalStrut(10));
            edu.add(data_inceput);
            edu.add(Box.createVerticalStrut(10));
            edu.add(data_sfarsit);
            edu.add(Box.createVerticalStrut(10));
            edu.add(media);
            eduPanel.add(Box.createHorizontalStrut(15));
            eduPanel.add(edu);
            eduPanel.add(Box.createHorizontalStrut(20));
        }
        add(eduPanel);
        add(experiencePanel);

    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D graphics = (Graphics2D)g;
        Color color = new Color(53, 188, 187);
        g.setColor(color);
        graphics.setBackground(Color.white);
        graphics.drawLine(85, 0, 85, 170);
        graphics.drawLine(0, 180, 75, 180);
        graphics.drawLine(95, 180, 1400, 180);
        graphics.drawLine(85, 190, 85, 330);
        graphics.drawLine(0, 340, 75, 340);
        graphics.drawLine(95, 340, 1400, 340);
        graphics.drawLine(85, 350, 85, 580);
    }
}
