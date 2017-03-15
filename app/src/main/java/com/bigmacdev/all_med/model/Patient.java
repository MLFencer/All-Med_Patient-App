package com.bigmacdev.all_med.model;

import net.maritimecloud.internal.core.javax.json.Json;
import net.maritimecloud.internal.core.javax.json.JsonObject;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;



public class Patient extends Person implements Serializable{
    private static final long serialVersionUID = 2L;

    private ArrayList<Diagnosis> diagnosis = new ArrayList<Diagnosis>();
    private ArrayList<Practice> approvedPractices = new ArrayList<Practice>();

    private String jsonString;
    private String password="";

    //---Constructors------
    public Patient(Person p){
        super(p.getfName(),p.getlName(),p.getDobY(),p.getDobM(),p.getDobD());
    }
    public Patient(String f, String l, int y, int m, int d){
        super(f,l,y,m,d);
    }

    //-------Create Patient from Json---------
    public Patient(JsonObject jo){
        this.password=jo.getString("password");
        JsonObject personalInfo = jo.getJsonObject("personal_info");
        JsonObject name = personalInfo.getJsonObject("name");
        this.fName=name.getString("first");
        this.lName=name.getString("last");
        JsonObject dob = personalInfo.getJsonObject("dob");
        this.dobD=dob.getInt("day");
        this.dobM=dob.getInt("month");
        this.dobY=dob.getInt("year");
    }

    //-------Create Json------
    public String toJson(){
        JsonObject jo = Json.createObjectBuilder()
                .add("password",password)
                .add("personal_info", Json.createObjectBuilder()
                        .add("name", Json.createObjectBuilder()
                                .add("first", fName)
                                //.add("middle", mName)
                                .add("last", lName)
                        )
                        .add("dob", Json.createObjectBuilder()
                                .add("day",dobD)
                                .add("month",dobM)
                                .add("year", dobY)
                        )
                ).build();
        return jo.toString();
    }


    //-----Password Operations------
    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}
}
