package com.vitaly.crudapp.view;

import com.google.gson.Gson;
import com.vitaly.crudapp.model.pojo.Label;
import com.vitaly.crudapp.repository.impls.GsonLabelRepositoryImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class viewtest1 implements Serializable {

    public static void main(String[] args) {
        Gson gson = new Gson();
        List<Label> labelslist = new ArrayList<>();
        Label label1 = new Label();

        try (BufferedWriter labelwriter = new BufferedWriter(new FileWriter(Label.getlabelPATH(), true))) {
           gson.toJson(label1, labelwriter);
       } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("OK");

    }
}
