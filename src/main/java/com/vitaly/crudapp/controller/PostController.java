package com.vitaly.crudapp.controller;

import com.vitaly.crudapp.model.pojo.Post;
import com.vitaly.crudapp.model.pojo.Status;
import com.vitaly.crudapp.repository.PostRepository;
import com.vitaly.crudapp.repository.impls.GsonPostRepositoryImpl;

import java.util.List;

public class PostController {
    private final PostRepository postRepository = new GsonPostRepositoryImpl();
    public Post createPost(String title) {
        Post postToCreate = new Post();
        postToCreate.setTitle(title);
        postToCreate.setStatus(Status.ACTIVE);
        postToCreate.setContent("");
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


}
