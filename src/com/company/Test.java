package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeSet;

public class Test {
    private static Application application = Application.getInstance();
    public static void Read() throws Exception {
        ArrayList<Manager> manageri = new ArrayList<>();
        ArrayList<Employee> lista_employees = new ArrayList<>();
        ArrayList<Recruiter> list_recruiters = new ArrayList<>();
        String path = "src/com/consumers.json";
        Object obj = new JSONParser().parse(new FileReader(path));
        JSONObject jo = (JSONObject)obj;
        String mypath = "src/com/jobs.json";
        Object my_obj = new JSONParser().parse(new FileReader(mypath));
        JSONObject my_jo = (JSONObject)my_obj;
        JSONArray managers = (JSONArray)jo.get("managers");
        for (Object o : managers) {
            JSONObject manager = (JSONObject) o;
            TreeSet<Education> educations = new TreeSet<>();
            TreeSet<Experience> experiences = new TreeSet<>();
            String working_company = null;
            String nume = (String) manager.get("name");
            String email = (String) manager.get("email");
            String date_of_birth = (String) manager.get("date_of_birth");
            String phone = (String) manager.get("phone");
            String genre = (String) manager.get("genre");
            Long salary = (long) manager.get("salary");
            double salariul = (double) salary;
            String[] names = nume.split(" ");
            for (int l = 2; l < names.length; l++) {
                names[l - 1].concat(" " + names[l]);
            }
            JSONArray education = (JSONArray) manager.get("education");
            for (Object value : education) {
                JSONObject step = (JSONObject) value;
                String start_date = (String) step.get("start_date");
                LocalDate data_inceput = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String end_date = (String) step.get("end_date");
                LocalDate data_sfarsit = null;
                if (end_date != null)
                    data_sfarsit = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String nivel = (String) step.get("level");
                String institutie = (String) step.get("name");
                Number grade = (Number) step.get("grade");
                Double media = grade.doubleValue();
                educations.add(new Education(data_inceput, data_sfarsit, institutie, nivel, media));
            }
            JSONArray experience = (JSONArray) manager.get("experience");
            for (Object value : experience) {
                JSONObject ciclu = (JSONObject) value;
                String start_date = (String) ciclu.get("start_date");
                LocalDate data_inceput = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String end_date = (String) ciclu.get("end_date");
                String company = (String) ciclu.get("company");
                LocalDate data_sfarsit = null;
                if (end_date != null) {
                    data_sfarsit = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                } else
                    working_company = company;
                String position = (String) ciclu.get("position");
                experiences.add(new Experience(data_inceput, data_sfarsit, company, position));

            }
            JSONArray languages = (JSONArray) manager.get("languages");
            ArrayList<String> limbi = new ArrayList<>();
            for (Object language : languages) limbi.add((String) language);
            JSONArray languages_level = (JSONArray) manager.get("languages_level");
            ArrayList<String> level = new ArrayList<>();
            for (int l = 0; l < languages_level.size(); l++)
                level.add((String) languages.get(l));
            ArrayList<ArrayList<String>> lang = new ArrayList<>();
            lang.add(limbi);
            lang.add(level);
            Information information = new Information(names[1], names[0], phone, email, date_of_birth, genre, lang);
            Manager manager1 = new Manager(working_company, salariul);
            Manager.Resume resume = new Manager.Resume.ResumeBuilder(information, educations).experience(experiences).build();
            manager1.setResume(resume);
            manageri.add(manager1);

        }
        application.add(new Company(manageri.get(0),manageri.get(0).nume_companie));
        application.add(new Company(manageri.get(1),manageri.get(1).nume_companie));
        for (Company companie : application.getCompanies()){
            companie.add(DepartmentFactory.getDepartment("IT"));
            companie.add(DepartmentFactory.getDepartment("Management"));
            companie.add(DepartmentFactory.getDepartment("Marketing"));
            companie.add(DepartmentFactory.getDepartment("Finance"));
        }
        JSONArray jobs = (JSONArray)my_jo.get("jobs");
        for (int i = 0; i < jobs.size();i++){
            JSONObject job = (JSONObject)jobs.get(i);
            Integer year_min = null;
            Integer year_max = null;
            Integer experience_max = null;
            Double media_max =null;
            String name = (String) job.get("name");
            String company = (String) job.get("company");
            Number nr = (Number) job.get("noPosition");
            int noPosition = nr.intValue();
            Number salariul = (Number)job.get("salary");
            Double salary = salariul.doubleValue();
            Number y_min = (Number) job.get("year_min");
            if (y_min != null)
                year_min = y_min.intValue();
            Number y_max = (Number) job.get("year_max");
            if (y_max != null)
                year_max = y_max.intValue();
            Number e_min = (Number) job.get("experience_min");
            Integer experience_min = e_min.intValue();
            Long e_max = (Long) job.get("experience_max");
            if (e_max != null)
                experience_max = e_max.intValue();
            Number m_min = (Number) job.get("average_min");
            Double media_min = m_min.doubleValue();
            Long m_max = (Long) job.get("average_max");
            if (m_max != null)
                media_max = m_max.doubleValue();
            Constraint<Integer> exp = new Constraint<>(experience_min,experience_max);
            Constraint<Integer> absolvire = new Constraint<>(year_min,year_max);
            Constraint<Double> medie = new Constraint<>(media_min,media_max);
            Job job1 = new Job(company,name,true,absolvire,exp,medie,noPosition,salary);
            application.getCompany(company).getDepartaments().get(0).add(job1);
        }

        JSONArray ja = (JSONArray) jo.get("employees");
        for (Object o : ja) {
            JSONObject employer = (JSONObject) o;
            TreeSet<Education> educations = new TreeSet<>();
            TreeSet<Experience> experiences = new TreeSet<>();
            String working_company = null;
            String working_department = null;
            String nume = (String) employer.get("name");
            String email = (String) employer.get("email");
            String phone = (String) employer.get("phone");
            String date_of_birth = (String) employer.get("date_of_birth");
            String sex = (String) employer.get("genre");
            Long salary = (long) employer.get("salary");
            double salariul = (double) salary;
            String[] names = nume.split(" ");
            for (int l = 2; l < names.length; l++) {
                names[l - 1].concat(" " + names[l]);
            }
            JSONArray education = (JSONArray) employer.get("education");
            for (int j = 0; j < education.size(); j++) {
                JSONObject step = (JSONObject) education.get(j);
                String start_date = (String) step.get("start_date");
                LocalDate data_inceput = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String end_date = (String) step.get("end_date");
                LocalDate data_sfarsit = null;
                if (end_date != null)
                    data_sfarsit = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String nivel = (String) step.get("level");
                String institutie = (String) step.get("name");
                Number grade = (Number) step.get("grade");
                Double media = grade.doubleValue();
                educations.add(new Education(data_inceput, data_sfarsit, institutie, nivel, media));
            }
            JSONArray experience = (JSONArray) employer.get("experience");
            for (int k = 0; k < experience.size(); k++) {
                JSONObject ciclu = (JSONObject) experience.get(k);
                String start_date = (String) ciclu.get("start_date");
                LocalDate data_inceput = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String end_date = (String) ciclu.get("end_date");
                String company = (String) ciclu.get("company");
                String department = (String) ciclu.get("department");
                LocalDate data_sfarsit = null;
                if (end_date != null) {
                    data_sfarsit = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                } else {
                    working_company = company;
                    working_department = department;
                }
                String position = (String) ciclu.get("position");
                experiences.add(new Experience(data_inceput, data_sfarsit, company, position));
            }
            JSONArray languages = (JSONArray) employer.get("languages");
            ArrayList<String> limbi = new ArrayList<>();
            for (int l = 0; l < languages.size(); l++)
                limbi.add((String) languages.get(l));
            JSONArray languages_level = (JSONArray) employer.get("languages_level");
            ArrayList<String> level = new ArrayList<>();
            for (int l = 0; l < languages_level.size(); l++)
                level.add((String) languages.get(l));
            ArrayList<ArrayList<String>> lang = new ArrayList<>();
            lang.add(limbi);
            lang.add(level);
            Information information = new Information(names[1], names[0], phone, email, date_of_birth, sex, lang);
            Employee employee = new Employee(working_company, salariul);
            Employee.Resume resume = new Employee.Resume.ResumeBuilder(information, educations).experience(experiences).build();
            employee.setResume(resume);
            lista_employees.add(employee);
            Company companie = application.getCompany(working_company);
            if (working_department != null) {
                Department department = DepartmentFactory.getDepartment(working_department);
                companie.add(employee, department);
            }
        }
        JSONArray recruiters = (JSONArray)jo.get("recruiters");
        for (int i = 0; i < recruiters.size();i++){
            JSONObject recruiter = (JSONObject)recruiters.get(i);
            TreeSet<Education> educations = new TreeSet<>();
            TreeSet<Experience> experiences = new TreeSet<>();
            String working_company = null;
            String nume = (String)recruiter.get("name");
            String email = (String)recruiter.get("email");
            String date_of_birth= (String)recruiter.get("date_of_birth");
            String phone = (String)recruiter.get("phone");
            String genre = (String)recruiter.get("genre");
            Long salary = (long) recruiter.get("salary");
            double salariul = (double)salary;
            String[] names = nume.split(" ");
            for ( int l = 2; l < names.length;l++){
                names[l-1].concat(" " + names[l]);
            }
            JSONArray education =(JSONArray)recruiter.get("education");
            for ( int j = 0; j < education.size();j++){
                JSONObject step = (JSONObject)education.get(j);
                String start_date =(String)step.get("start_date");
                LocalDate data_inceput = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String end_date =(String)step.get("end_date");
                LocalDate data_sfarsit = null;
                if (end_date != null)
                    data_sfarsit = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String nivel = (String)step.get("level");
                String institutie = (String)step.get("name");
                Number grade = (Number) step.get("grade");
                Double media = grade.doubleValue();
                educations.add(new Education(data_inceput,data_sfarsit,institutie,nivel, media));
            }
            JSONArray experience =(JSONArray)recruiter.get("experience");
            for ( int k = 0; k < experience.size();k++){
                JSONObject ciclu = (JSONObject)experience.get(k);
                String start_date =(String)ciclu.get("start_date");
                LocalDate data_inceput = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String end_date =(String)ciclu.get("end_date");
                String company = (String)ciclu.get("company");
                LocalDate data_sfarsit = null;
                if (end_date != null)
                {
                    data_sfarsit = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                }
                else
                    working_company = company;
                String position = (String)ciclu.get("position");
                experiences.add(new Experience(data_inceput,data_sfarsit,company,position));
            }
            JSONArray languages = (JSONArray)recruiter.get("languages");
            ArrayList<String> limbi = new ArrayList<>();
            for (int l = 0; l < languages.size();l++)
                limbi.add((String)languages.get(l));
            JSONArray languages_level = (JSONArray)recruiter.get("languages_level");
            ArrayList<String> level = new ArrayList<>();
            for (int l = 0; l < languages_level.size();l++)
                level.add((String)languages.get(l));
            ArrayList<ArrayList<String>> lang = new ArrayList<>();
            lang.add(limbi);
            lang.add(level);
            Information information = new Information(names[1],names[0],phone,email,date_of_birth,genre,lang);
            Recruiter recr = new Recruiter(working_company,salariul);
            Recruiter.Resume resume = new Recruiter.Resume.ResumeBuilder(information,educations).experience(experiences).build();
            recr.setResume(resume);
            list_recruiters.add(recr);
            application.getCompany(working_company).add(recr);

        }

        JSONArray users = (JSONArray)jo.get("users");
        for (int i = 0; i < users.size();i++){
            JSONObject user = (JSONObject)users.get(i);
            TreeSet<Education> educations = new TreeSet<>();
            TreeSet<Experience> experiences = new TreeSet<>();
            String nume = (String)user.get("name");
            String email = (String)user.get("email");
            String date_of_birth= (String)user.get("date_of_birth");
            String phone = (String)user.get("phone");
            String genre = (String)user.get("genre");
            String[] names = nume.split(" ");
            for ( int l = 2; l < names.length;l++){
                names[l-1].concat(" " + names[l]);
            }
            JSONArray education =(JSONArray)user.get("education");
            for ( int j = 0; j < education.size();j++){
                JSONObject step = (JSONObject)education.get(j);
                String start_date =(String)step.get("start_date");
                LocalDate data_inceput = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String end_date =(String)step.get("end_date");
                LocalDate data_sfarsit = null;
                if (end_date != null)
                    data_sfarsit = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String nivel = (String)step.get("level");
                String institutie = (String)step.get("name");
                Number grade = (Number) step.get("grade");
                Double media = grade.doubleValue();
                educations.add(new Education(data_inceput,data_sfarsit,institutie,nivel, media));
            }
            JSONArray experience =(JSONArray)user.get("experience");
            for ( int k = 0; k < experience.size();k++){
                JSONObject ciclu = (JSONObject)experience.get(k);
                String start_date =(String)ciclu.get("start_date");
                LocalDate data_inceput = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String end_date =(String)ciclu.get("end_date");
                String company = (String)ciclu.get("company");
                LocalDate data_sfarsit = null;
                if (end_date != null)
                    data_sfarsit = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String position = (String)ciclu.get("position");
                experiences.add(new Experience(data_inceput,data_sfarsit,company,position));
            }
            JSONArray languages = (JSONArray)user.get("languages");
            ArrayList<String> limbi = new ArrayList<>();
            for (int l = 0; l < languages.size();l++)
                limbi.add((String)languages.get(l));
            JSONArray languages_level = (JSONArray)user.get("languages_level");
            ArrayList<String> level = new ArrayList<>();
            for (int l = 0; l < languages_level.size();l++)
                level.add((String)languages.get(l));
            ArrayList<ArrayList<String>> lang = new ArrayList<>();
            lang.add(limbi);
            lang.add(level);
            JSONArray companies = (JSONArray) user.get("interested_companies");
            ArrayList<String> interested_companies = new ArrayList<>();
            for (int l = 0; l < companies.size();l++)
                interested_companies.add((String)companies.get(l));
            Information information = new Information(names[1],names[0],phone,email,date_of_birth,genre,lang);
            User user1 = new User();
            User.Resume resume = new User.Resume.ResumeBuilder(information,educations).experience(experiences).build();
            user1.setResume(resume);
            user1.companies =interested_companies;
            application.add(user1);

        }
        // RETEAUA SOCIALA
        User u1 = application.getUsers().get(0);
        u1.add(application.getUsers().get(1));
        u1.add(lista_employees.get(2));
        User u2 = application.getUsers().get(1);
        u2.add(application.getUsers().get(0));
        u2.add(lista_employees.get(6));
        u2.add(list_recruiters.get(0));
        User u3 = application.getUsers().get(2);
        u3.add(application.getUsers().get(3));
        u3.add(lista_employees.get(2));
        User u4 = application.getUsers().get(3);
        u4.add(application.getUsers().get(2));
        u4.add(lista_employees.get(9));
        Employee e1 = lista_employees.get(0);
        e1.add(lista_employees.get(9));
        e1.add(list_recruiters.get(2));
        Employee e3 = lista_employees.get(2);
        e3.add(lista_employees.get(5));
        e3.add(list_recruiters.get(1));
        e3.add(application.getUsers().get(0));
        e3.add(application.getUsers().get(2));
        Employee e6 = lista_employees.get(5);
        e6.add(list_recruiters.get(3));
        e6.add(lista_employees.get(2));
        Employee e10 = lista_employees.get(9);
        e10.add(application.getUsers().get(3));
        e10.add(lista_employees.get(1));
        Employee e7 = lista_employees.get(6);
        e7.add(application.getUsers().get(1));
        Recruiter r1 = list_recruiters.get(0);
        r1.add(application.getUsers().get(1));
        Recruiter r2 = list_recruiters.get(1);
        r2.add(lista_employees.get(2));
        Recruiter r3 = list_recruiters.get(2);
        r3.add(lista_employees.get(1));
        Recruiter r4 = list_recruiters.get(3);
        r4.add(lista_employees.get(5));

        for ( User user: application.getUsers()){
            for (Job job: application.getJobs(user.companies)){
                job.appply(user);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Test.Read();
        for ( Job job : application.getCompany("Google").getJobs()){
            application.getCompany("Google").getManager().process(job);
        }
        for ( Job job : application.getCompany("Amazon").getJobs()){
            application.getCompany("Amazon").getManager().process(job);
        }
        System.out.println("\n==============================Users=========================================");
        for ( User usr : application.getUsers()){
            System.out.println(usr+" "+ usr.ani_de_experienta()+" "+ usr.getTotalScore() + usr.getGraduationYear()+"\n");
        }
        System.out.println("\n=======================ANGAJATI GOOGLE DEPARTAMENT IT================================");
        for ( Employee empl : application.getCompany("Google").getDepartaments().get(0).getEmployees())
            System.out.println(empl);
        System.out.println("\n=======================ANGAJATI AMAZON DEPARTAMENT IT================================");
        for ( Employee empl : application.getCompany("Amazon").getDepartaments().get(0).getEmployees())
            System.out.println(empl);
    }
}