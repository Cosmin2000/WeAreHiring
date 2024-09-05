package com.company;

import java.util.ArrayList;
import java.util.TreeSet;

public abstract class Consumer {
    Resume resume;
    ArrayList<Consumer> close_friends = new ArrayList<>();

    public  void setResume(Resume resume){
        this.resume = resume;
    }
    //Adăugarea unor studii;
    public void add(Education education){
        resume.getEducations().add(education);
    }
// Adăugarea unei experient,e profesionale;
    public void add(Experience experience){
        resume.getExperiences().add(experience);
    }
// Adăugarea unui nou cunoscut;
    public void add(Consumer consumer){
        close_friends.add(consumer);
    }
    //Determinarea gradului de prietenie cu un alt utilizator – se realizează o parcurgere în lătime în reteaua
    //socială a utilizatorului;
    public int getDegreeInFriendship(Consumer consumer){
        ArrayList<Consumer> visited = new ArrayList<>();
        ArrayList<Consumer> queue = new ArrayList<>();
        ArrayList<Integer> dist = new ArrayList<>();
        ArrayList<Consumer> parents = new ArrayList<>();
        queue.add(this);
        dist.add(0);
        parents.add(this);
        visited.add(this);
        while ( queue.size() != 0) {
            Consumer cons = queue.get(0);
            queue.remove(0);
            if (consumer.equals(cons))
                return dist.get(parents.indexOf(consumer));
            for (Consumer con : cons.close_friends) {
                if (!visited.contains(con))
                {
                    parents.add(con);
                    dist.add(dist.get(parents.indexOf(cons))+1);
                    queue.add(con);
                    visited.add(con);
                }
            }
        }
        return -1;
    }
// Eliminarea unei persoane din reteaua socială;
    public void remove(Consumer consumer){
        close_friends.remove(consumer);
    }
// Determinarea anului absolvirii;
    public Integer getGraduationYear(){
        int an_absolvire = 0;
        for (Education edu : resume.getEducations()){
            if(edu.data_sfarsit == null && edu.nivel_de_educatie.equals("college"))
                return null;
            if(edu.nivel_de_educatie.equals("college") &&  edu.data_sfarsit.getYear() > an_absolvire)
                an_absolvire = edu.data_sfarsit.getYear();

        }
        return an_absolvire;
    }
// Determinarea mediei studiilor absolvite;
    public Double meanGPA(){
        int nr_studii_absolvite = 0;
        double media = 0;
        for (Education edu : resume.getEducations()) {
            if (edu.data_sfarsit != null) {
                media += edu.media_de_finalizare;
                nr_studii_absolvite++;
            }
        }
        return (media/nr_studii_absolvite);
    }

   static class Resume {
        private Information info;
        private TreeSet<Education> educations;
        private TreeSet<Experience> experiences;

        public Information getInfo (){
            return info;
        }
        public Resume( ResumeBuilder builder) throws ResumeIncompleteException{
            if (builder.info == null || builder.educations == null || builder.educations.size() == 0)
                throw  new ResumeIncompleteException("Information needed");
            this.info = builder.info;
            this.experiences = builder.experiences;
            this.educations = builder.educations;
        }
       public TreeSet<Education> getEducations(){
           return educations;
       }
       public TreeSet<Experience> getExperiences(){
           return experiences;
       }

       public static class ResumeBuilder {
           private Information info;
           private TreeSet<Education> educations;
           private TreeSet<Experience> experiences;

           public ResumeBuilder( Information information,TreeSet<Education> educations){
               info = information;
               this.educations = educations;
           }
           public Resume.ResumeBuilder experience(TreeSet<Experience> experiences){
               this.experiences = experiences;
               return this;
           }
           public Resume build() throws ResumeIncompleteException {
               return  new Resume(this);
           }
       }
    }
    public String toString(){
        return resume.getInfo().getFirstName() + " " + resume.getInfo().getName();
    }
}
