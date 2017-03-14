package com.bigmacdev.all_med.model;


import java.io.Serializable;

public class Person implements Serializable{

    private static final long serialVersionUID = 1L;

    protected String fName, mName, lName;
    protected int dobY, dobM, dobD;

    public Person(String f, String l, int y, int m, int d){
        fName=f;
        lName=l;
        dobY=y;
        dobM=m;
        dobD=d;
    }
    public Person(String f, String l, String mn, int y, int m, int d){
        fName=f;
        lName=l;
        dobY=y;
        dobM=m;
        dobD=d;
        mName=mn;
    }
    public String getfName(){
        return fName;
    }
    public int getDobY() {return dobY;}
    public int getDobM(){return dobM;}
    public int getDobD() {return dobD;}
    public String getlName() {return lName;}
    public String getName(){return lName+"_"+fName;}
}
