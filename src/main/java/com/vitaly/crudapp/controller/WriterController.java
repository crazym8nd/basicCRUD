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
    public Writer createWriter(String firstName, String lastName) {
        Writer writerToCreate = new Writer();
        writerToCreate.setFirstName(firstName);
        writerToCreate.setLastName(lastName);
        writerToCreate.setStatus(Status.ACTIVE);
        writerToCreate.setWriterPosts(addPosts(selectPosts()));
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

    public List<Integer> selectPosts() {
        List<Integer> postsIds = new ArrayList<>();
        PostView pv = new PostView();
        pv.readPosts();
        boolean addPosts = true;
        while (addPosts) {
            System.out.println("Enter posts ID to add:");
            Integer postId = Integer.parseInt(scanner.nextLine());
            postsIds.add(postId);

            System.out.println("add more posts? (y/n)");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("y")) {
                addPosts = false;
            }
        }
        return postsIds;
    }
    public List<Post> addPosts(List<Integer> postsIds){
        List<Post> posts = new ArrayList<>();
        for(Integer id : postsIds){
            Post post = new GsonPostRepositoryImpl().getById(id);
            posts.add(post);
        }
        return posts;
    }

}
