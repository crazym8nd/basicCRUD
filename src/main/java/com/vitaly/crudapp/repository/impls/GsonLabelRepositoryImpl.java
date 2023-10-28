package com.vitaly.crudapp.repository.impls;

import com.google.gson.Gson;
import com.vitaly.crudapp.model.pojo.Label;
import com.vitaly.crudapp.repository.LabelRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public interface GsonLabelRepositoryImpl extends LabelRepository {
    Gson gson = new Gson();

//    List<Label> getAll() {
//        try (BufferedReader labelreader = new BufferedReader(new FileReader(Label.getlabelPATH()))) {
//          return null;
//        }
//    }


//    Label getById(Integer id) {
//        return null;
//    }

//    Label update(Label t){
//        return null;
//    }
//    void deleteById(Integer id) {
//
//    }

 }

