package com.vitaly.crudapp.controller;

import com.vitaly.crudapp.model.pojo.Post;
import com.vitaly.crudapp.model.pojo.Status;
import com.vitaly.crudapp.model.pojo.Writer;
import com.vitaly.crudapp.repository.WriterRepository;
import com.vitaly.crudapp.repository.impls.GsonPostRepositoryImpl;
import com.vitaly.crudapp.repository.impls.GsonWriterRepositoryImpl;
import com.vitaly.crudapp.view.PostView;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterController {

    private final WriterRepository writerRepository= new GsonWriterRepositoryImpl();
    private final Scanner scanner = new Scanner(System.in);
    public Writer createWriter(String firstName, String lastName, List<Post> posts) {
        Writer writerToCreate = new Writer();
        writerToCreate.setFirstName(firstName);
        writerToCreate.setLastName(lastName);
        writerToCreate.setStatus(Status.ACTIVE);
        writerToCreate.setWriterPosts(posts);
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
