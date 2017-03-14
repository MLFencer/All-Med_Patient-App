package com.bigmacdev.all_med.model;

import java.io.Serializable;
import java.util.ArrayList;



public class Patient extends Person implements Serializable{
    private static final long serialVersionUID = 2L;

    ArrayList<Diagnosis> diagnosis = new ArrayList<Diagnosis>();
    ArrayList<Practice> approvedPractices = new ArrayList<Practice>();
    private String jsonString;
    private String password="";


    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}
    public Patient(Person p){
        super(p.getfName(),p.getlName(),p.getDobY(),p.getDobM(),p.getDobD());
    }

    public Patient(String f, String l, int y, int m, int d){
        super(f,l,y,m,d);
    }

}
