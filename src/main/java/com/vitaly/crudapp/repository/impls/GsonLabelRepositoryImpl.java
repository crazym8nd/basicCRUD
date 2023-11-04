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
            List<Label> labelsdataset = new Gson().fromJson(labelsdreader, new TypeToken<List<Label>>() {
            }.getType());
            return labelsdataset;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Label getById(Integer id) {
        return labelsdataset().stream().filter(l -> l.getId().equals(id)).findFirst().orElseThrow(() -> new FileSystemNotFoundException("Label not found"));
    }

    @Override
    public List<Label> getAll() {
        return labelsdataset();
    }


    @Override
    public Label save(Label label) {
        List<Label> sourcelabels = labelsdataset();
        sourcelabels.add(label);
        try (BufferedWriter labelwriter = new BufferedWriter(new FileWriter(Label.getlabelPATH(), true))) {
            gson.toJson(sourcelabels, labelwriter);
            return label;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Label update(Label label) {
        Label updatedlabel = new Label();
        updatedlabel.setName(new Scanner(System.in).nextLine());
    return updatedlabel;
    }

    @Override
    public void deleteById(Integer id) {
        Label label = labelsdataset().stream().filter(l -> l.getId().equals(id)).findFirst().orElseThrow(() -> new FileSystemNotFoundException("Label not found"));
        label.setStatus(Status.DELETED);
        savechanges(label);
    }

    private void savechanges(Label label) {
        List<Label> labelsbefore = labelsdataset();
        labelsbefore.add(label);
        try (BufferedWriter labelwriter = new BufferedWriter(new FileWriter(Label.getlabelPATH(), false))){
            gson.toJson(labelsbefore, labelwriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}
}


