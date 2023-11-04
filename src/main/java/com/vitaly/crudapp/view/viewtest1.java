package com.vitaly.crudapp.view;

import com.vitaly.crudapp.model.pojo.Label;
import com.vitaly.crudapp.repository.impls.GsonLabelRepositoryImpl;

import java.util.List;


public class viewtest1 {

    public static void main(String[] args) {
        GsonLabelRepositoryImpl impl = new GsonLabelRepositoryImpl();
        List<Label> labels = impl.getAll();
        Label labelnext = new Label("next", 8);
        System.out.println(labels);
        System.out.println("OK");


    }
}

