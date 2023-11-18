package com.vitaly.crudapp.controller;

import com.vitaly.crudapp.model.pojo.Label;
import com.vitaly.crudapp.model.pojo.Status;
import com.vitaly.crudapp.repository.LabelRepository;
import com.vitaly.crudapp.repository.impls.GsonLabelRepositoryImpl;

import java.util.List;

public class LabelController {
    private final LabelRepository labelRepository = new GsonLabelRepositoryImpl();
    public Label createLabel(String name) {
        Label labelToCreate = new Label();
        labelToCreate.setName(name);
        labelToCreate.setStatus(Status.ACTIVE);

        return labelRepository.save(labelToCreate);
    }
    public List<Label> getAll(){
        return labelRepository.getAll();
    }

    public Label getById( Integer id){
        return labelRepository.getById(id);
    }
    public void update (Label label){
        labelRepository.update(label);
    }
    public void delete(Integer id){
        labelRepository.deleteById(id);
    }

}
