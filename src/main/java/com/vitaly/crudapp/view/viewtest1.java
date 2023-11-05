package com.vitaly.crudapp.view;

import com.vitaly.crudapp.model.pojo.Label;
import com.vitaly.crudapp.repository.impls.GsonLabelRepositoryImpl;

import java.util.List;


public class viewtest1 {

    public static void main(String[] args) {
        GsonLabelRepositoryImpl impl = new GsonLabelRepositoryImpl();
        Label labelnext = new Label("next", 8);
        impl.update(1);

        System.out.println(impl);


    }
}

