package com.example.assign1;

public class CowLogs {

   public int cowID;
   public String cowType;
   public float age;
   public float weight;
   public String condition;
   public float loclang;
   public float loclat;
   public String time;

//    public  CowLogs() {
//        cowID = null;
//        cowType = null;
//        age = null;
//        weight = null;
//        condition = null;
//        loclang = null;
//        loclat = null;
//        time = null;
//    }

    public CowLogs (int cowID, String cowType, String time, Float weight, Float age, String condition, Float loclang, Float loclat) {
        cowType = cowType;
        cowID = cowID;
        time = time;
        weight = weight;
        age = age;
        condition = condition;
        loclang = loclang;
        loclat = loclat;

    }
    public int getCowID() {
        return this.cowID;
        }

    public String getCowType() {
        return this.cowType;
    }

    public String getTime() {
        return  this.time;
    }

    public float getWeight() {
        return  this.weight;
    }

    public float getAge() {
        return  this.age;
    }

    public String getCondition() {
        return  this.condition;
    }

    public float getLoclang() {
        return  this.loclang;
    }

    public  float getLoclat() {
        return this.loclat;
    }
}

