package com.vitaly.crudapp.repository.impls;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vitaly.crudapp.model.pojo.Label;
import com.vitaly.crudapp.repository.LabelRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;


public class GsonLabelRepositoryImpl implements LabelRepository {
    private static final Gson gson = new Gson();

    @Override
    public Label getById(Integer id) {
        return null;
    }

    @Override
    public List<Label> getAll() {
        try (BufferedReader labelreader = new BufferedReader(new FileReader(Label.getlabelPATH()))) {
            List<Label> labelslist = new Gson().fromJson(labelreader, new TypeToken<List<Label>>() {}.getType());
            return labelslist;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public Label save(Label labelslist) {
            try(BufferedWriter labelwriter = new BufferedWriter(new FileWriter(Label.getlabelPATH(), true))) {
                gson.toJson(labelslist, labelwriter);
                return labelslist;
            }catch (IOException e){
                throw new RuntimeException(e);
            }

        }


    @Override
    public Label update(Label l) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}

