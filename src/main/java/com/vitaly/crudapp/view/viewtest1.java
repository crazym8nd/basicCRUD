package com.vitaly.crudapp.view;

import com.google.gson.Gson;

import com.vitaly.crudapp.model.pojo.Label;
import com.vitaly.crudapp.repository.impls.GsonLabelRepositoryImpl;

import java.io.*;
import java.util.List;


public class viewtest1 implements Serializable {

    public static void main(String[] args) {
        GsonLabelRepositoryImpl impl = new GsonLabelRepositoryImpl();
        List<Label> labels = impl.getAll();
        System.out.println(labels);

//        try (BufferedWriter labelwriter = new BufferedWriter(new FileWriter(Label.getlabelPATH(), true))) {
//           gson.toJson(label1, labelwriter);
//       } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("OK");


//        List<Label> getAll(){
//            try (List<Label> labelslist= new ArrayList<>(); BufferedReader labelreader = new BufferedReader(new FileReader(Label.getlabelPATH()))) {
//                labelslist = new Gson().fromJson(labelreader, (Type) Label.class);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        Label save(Label l){return null;};
//        Label update(Integer t){return null;};
//        void deleteById(Integer id){};
//
////    List<Label> getAll() {
////        try (BufferedReader labelreader = new BufferedReader(new FileReader(Label.getlabelPATH()))) {
////          return null;
////        }
////    }
//
//
////    Label getById(Integer id) {
////        return null;
////    }
//
////    Label update(Label t){
////        return null;
////    }
////    void deleteById(Integer id) {
////
////    }
//
//    }
    }
}

