package com.bigmacdev.all_med;

import net.maritimecloud.internal.core.javax.json.Json;
import net.maritimecloud.internal.core.javax.json.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;



public class Patient extends Person implements Serializable{
    private static final long serialVersionUID = 2L;

    private ArrayList<Diagnosis> diagnosis = new ArrayList<Diagnosis>();
    private ArrayList<Practice> approvedPractices = new ArrayList<Practice>();

    private String jsonString;
    private String gender="";
    private String password="";
    private String homeNumber="";
    private String cellNumber="";
    private String address="";
    private String city="";
    private String state="";
    private String zip="";
    private String ssn="";
    private String email="";
    private String emergencyContact="";
    private String emergencyContactRelationship="";
    private String eCHomeNumber="";
    private String eCCellNumber="";


    //------Getters-----------
    public String getGender(){return gender;}
    public String getPassword(){return password;}
    public String getHomeNumber(){return homeNumber;}
    public String getCellNumber(){return cellNumber;}
    public String getAddress() {return address;}
    public String getCity(){return city.substring(0,0).toLowerCase()+city.substring(1,city.length());}
    public String getState(){return state;}
    public String getZip() {return zip;}
    public String getSsn() {return ssn.substring(ssn.length()-4,ssn.length());}
    public String getEmail(){return email;}
    public String getEmergencyContact(){return emergencyContact;}
    public String getEmergencyContactRelationship(){return emergencyContactRelationship;}
    public String geteCHomeNumber(){return eCHomeNumber;}
    public String geteCCellNumber(){return eCHomeNumber;}
    //------------------------------

    //------Setters--------------
    public void setGender(String s){gender=s;}
    public void setPassword(String password){this.password=password;}
    public void setHomeNumber(String s){homeNumber=s;}
    public void setCellNumber(String s){cellNumber=s;}
    public void setAddress(String s){address=s;}
    public void setCity(String s){city=s;}
    public void setState(String s){state=s;}
    public void setZip(String s){zip=s;}
    public void setSsn(String s){ssn=s;}
    public void setEmail(String s){email=s;}
    public void setEmergencyContact(String s){emergencyContact=s;}
    public void setEmergencyContactRelationship(String s){emergencyContactRelationship=s;}
    public void seteCHomeNumber(String s){eCHomeNumber=s;}
    public void seteCCellNumber(String s){eCCellNumber=s;}
    //----------------------------------

    //---Constructors------------------------------------------------------------
    public Patient(){}
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
    //-----------------------------------------------------------------------------

    //-------Create Json------
    public String toJson(){
        JsonObject jo = Json.createObjectBuilder()
                .add("password",password)
                .add("personal_info", Json.createObjectBuilder()
                        .add("name", Json.createObjectBuilder()
                                .add("first", fName)
                                .add("middle", mName)
                                .add("last", lName)
                        )
                        .add("dob", Json.createObjectBuilder()
                                .add("day",dobD)
                                .add("month",dobM)
                                .add("year", dobY)
                        )

                )
                .add("ssn",ssn)
                .add("contact", Json.createObjectBuilder()
                    .add("home",homeNumber)
                    .add("cell",cellNumber)
                    .add("email",email)
                    .add("address",address)
                    .add("city",city)
                    .add("state",state)
                    .add("zip",zip)
                )
                .add("emergency", Json.createObjectBuilder()
                    .add("contact",emergencyContact)
                    .add("relationship",emergencyContactRelationship)
                    .add("home",eCHomeNumber)
                    .add("cell",eCCellNumber)
                )
                .build();
        return jo.toString();
    }
    //------------------------------

}
