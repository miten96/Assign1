package com.example.assign1.Model;

public class Record {
    private long pid;
    private String id;
    private String weight;
    private String age;
    private String flag;
    private String date;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Record(long pid, String id, String weight, String age, String flag, String date) {
        this.pid = pid;
        this.id = id;
        this.weight = weight;
        this.age = age;
        this.flag = flag;
        this.date = date;
    }


}