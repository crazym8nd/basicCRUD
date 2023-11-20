package com.vitaly.crudapp.controller;

import com.vitaly.crudapp.model.pojo.Post;
import com.vitaly.crudapp.model.pojo.Status;
import com.vitaly.crudapp.model.pojo.Writer;
import com.vitaly.crudapp.repository.WriterRepository;
import com.vitaly.crudapp.repository.impls.GsonWriterRepositoryImpl;


import java.util.List;

public class WriterController {

    private final WriterRepository writerRepository= new GsonWriterRepositoryImpl();
    public Writer createWriter(String firstName) {
        Writer writerToCreate = new Writer();
        writerToCreate.setFirstName(firstName);
        writerToCreate.setStatus(Status.ACTIVE);
        writerToCreate.setLastName("teamplate");
        return writerRepository.save(writerToCreate);
    }
    public List<Writer> getAll(){
        return writerRepository.getAll();
    }

    public Writer getById( Integer id){
        return writerRepository.getById(id);
    }
    public void update (Writer writer){
        writerRepository.update(writer);
    }
    public void delete(Integer id){
        writerRepository.deleteById(id);
    }


}
