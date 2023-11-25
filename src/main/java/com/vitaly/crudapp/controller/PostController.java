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

    public Post createPost(String title, String content, List<Label> labels) {
        Post postToCreate = new Post();
        postToCreate.setTitle(title);
        postToCreate.setContent(content);
        postToCreate.setStatus(Status.ACTIVE);
        postToCreate.setPostLabels(labels);
        return postRepository.save(postToCreate);
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }

    public Post getById(Integer id) {
        return postRepository.getById(id);
    }

    public void update(Post post) {
        postRepository.update(post);
    }

    public void delete(Integer id) {
        postRepository.deleteById(id);
    }
}


