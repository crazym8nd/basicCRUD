package com.vitaly.crudapp.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Scanner;

public class Label  {
    public static String getlabelPATH() {
        return FILE_PATH;
    }

    private static final String FILE_PATH = "src/main/resources/labels.json";

    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;

    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Label(){
        this.name = name;
        this.status = Status.ACTIVE;
        this.id = id;
    }
    public Label(String name){
        this.name = name;
        this.status = Status.ACTIVE;
        this.id = id;

    }
    public Label(String name, int id){
        this.name = name;
        this.status = Status.ACTIVE;
        this.id = id;

    }

public String toString(){
        return "Label name=" +name+",id=" + id;
}
}
