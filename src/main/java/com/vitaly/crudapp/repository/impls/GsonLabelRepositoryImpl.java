package com.vitaly.crudapp.repository.impls;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vitaly.crudapp.model.pojo.Label;
import com.vitaly.crudapp.model.pojo.Status;
import com.vitaly.crudapp.repository.LabelRepository;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class GsonLabelRepositoryImpl implements LabelRepository {
    private static final Gson GSON = new Gson();
    private static final String FILE_PATH = "src/main/resources/labels.json";


    private List<Label> labelsDataSet() {
        try (BufferedReader labelsDReader = new BufferedReader(new FileReader(FILE_PATH))) {
            return new Gson().fromJson(labelsDReader, new TypeToken<List<Label>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println("....");
            return Collections.emptyList();
        }
    }

    private void saveChanges(List<Label> labels) {
        try (BufferedWriter labelWriter = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            GSON.toJson(labels, labelWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer generateId(List<Label> labels) {
        return labels.stream()
                .mapToInt(Label::getId).max().orElse(0) + 1;
    }


    @Override
    public Label getById(Integer id) {
        return labelsDataSet().stream().filter(l -> l.getId().equals(id)).findFirst().orElseThrow(() -> new FileSystemNotFoundException("Label not found"));
    }

    @Override
    public List<Label> getAll() {
        return labelsDataSet().stream().filter(l -> l.getStatus().equals(Status.ACTIVE)).collect(Collectors.toList());
    }


    @Override
    public Label save(Label labelToCreate) {
        List<Label> sourceLabels = labelsDataSet();
        labelToCreate.setId(generateId(sourceLabels));
        sourceLabels.add(labelToCreate);
        saveChanges(sourceLabels);
        return labelToCreate;
    }


    @Override
    public Label update(Label labelToUpdate) {
        List<Label> labels = labelsDataSet()
                .stream().map(existingLabel -> {
                    if (existingLabel.getId().equals(labelToUpdate.getId())) {
                        return labelToUpdate;
                    }
                    return existingLabel;
                }).collect(Collectors.toList());
        saveChanges(labels);
        return labelToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        List<Label> labels = labelsDataSet()
                .stream().peek(existingLabel -> {
                    if (existingLabel.getId().equals(id)) {
                        existingLabel.setStatus(Status.DELETED);
                    }
                }).collect(Collectors.toList());
        saveChanges(labels);
    }


}


