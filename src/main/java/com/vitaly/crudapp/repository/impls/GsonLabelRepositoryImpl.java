package com.vitaly.crudapp.repository.impls;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vitaly.crudapp.model.pojo.Label;
import com.vitaly.crudapp.model.pojo.Status;
import com.vitaly.crudapp.repository.LabelRepository;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.List;
import java.util.Scanner;


public class GsonLabelRepositoryImpl implements LabelRepository {
    private static Gson gson = new Gson();


    private List<Label> labelsdataset() {
        try (BufferedReader labelsdreader = new BufferedReader(new FileReader(Label.getlabelPATH()))) {
            List<Label> labelsdataset = new Gson().fromJson(labelsdreader, new TypeToken<List<Label>>(){}.getType());
            return labelsdataset;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Label getById(Integer id) {
        return getAll().stream().filter(l -> l.getId().equals(id)).findFirst().orElseThrow(() -> new FileSystemNotFoundException("Label not found"));
    }

    @Override
    public List<Label> getAll() {
        return labelsdataset();
    }


    @Override
    public Label save(Label label) {
        List<Label> sourcelabels = getAll();
        sourcelabels.add(label);
        savechanges(sourcelabels);
        return label;
    }


    @Override
    public Label update(Integer id) {
        Label updatedlabel = getById(id);
        updatedlabel.setName(new Scanner(System.in).nextLine());
        save(updatedlabel);
    return updatedlabel;
    }

    @Override
    public void deleteById(Integer id) {
        Label label = getById(id);
        label.setStatus(Status.DELETED);
    }

    private void savechanges(List<Label> labels) {
        List<Label> labelsbefore = labels;
        try (BufferedWriter labelwriter = new BufferedWriter(new FileWriter(Label.getlabelPATH(), false))){
            gson.toJson(labelsbefore, labelwriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}
}


