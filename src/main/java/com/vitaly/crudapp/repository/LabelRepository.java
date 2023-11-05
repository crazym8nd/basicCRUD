package com.vitaly.crudapp.repository;

import com.vitaly.crudapp.model.pojo.Label;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface LabelRepository extends GenericRepository<Label, Integer> {
        Label getById(Integer id);
    List<Label> getAll();
    Label save(Label l);
    Label update(Integer id);
    void deleteById(Integer id);
}
