package com.vitaly.crudapp.controller;

import com.vitaly.crudapp.model.pojo.Label;
import com.vitaly.crudapp.model.pojo.Post;
import com.vitaly.crudapp.model.pojo.Status;
import com.vitaly.crudapp.repository.PostRepository;
import com.vitaly.crudapp.repository.impls.GsonLabelRepositoryImpl;
import com.vitaly.crudapp.repository.impls.GsonPostRepositoryImpl;
import com.vitaly.crudapp.view.LabelView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostController {
    private final PostRepository postRepository = new GsonPostRepositoryImpl();
    private final Scanner scanner = new Scanner(System.in);
    public Post createPost(String title, String content) {
        Post postToCreate = new Post();
        postToCreate.setTitle(title);
        postToCreate.setContent(content);
        postToCreate.setStatus(Status.ACTIVE);
        postToCreate.setPostLabels(addLabels(selectLabels()));
        return postRepository.save(postToCreate);
    }
    public List<Post> getAll(){
        return postRepository.getAll();
    }

    public Post getById( Integer id){
        return postRepository.getById(id);
    }
    public void update (Post post){
        postRepository.update(post);
    }
    public void delete(Integer id){
        postRepository.deleteById(id);
    }

    public List<Integer> selectLabels(){

        List<Integer> labelsIds = new ArrayList<>();
        LabelView lv = new LabelView();
        lv.readLabels();
        boolean addLabels = true;
            while (addLabels) {
                System.out.println("Enter labels ID to add:");
                Integer labelId = Integer.parseInt(scanner.nextLine());
                labelsIds.add(labelId);

                System.out.println("dobavit ewe kategoriy? (y/n)");
                String answer = scanner.nextLine();
                if (!answer.equalsIgnoreCase("y")) {
                    addLabels = false;
                }
            }
        return labelsIds;
    }
    public List<Label> addLabels(List<Integer> labelsIds){
        List<Label> labels = new ArrayList<>();
        for (Integer id : labelsIds) {
            Label label = new GsonLabelRepositoryImpl().getById(id);
            labels.add(label);
        }
        return labels;
    }

}
