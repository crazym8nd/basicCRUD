package com.vitaly.crudapp.repository.impls;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vitaly.crudapp.model.pojo.Post;
import com.vitaly.crudapp.model.pojo.Status;
import com.vitaly.crudapp.repository.PostRepository;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GsonPostRepositoryImpl implements PostRepository {
    private static final Gson GSON = new Gson();
    private static final String FILE_PATH = "src/main/resources/posts.json";


    private List<Post> postsDataSet() {
        try (BufferedReader postsReader = new BufferedReader(new FileReader(FILE_PATH))) {
            return new Gson().fromJson(postsReader, new TypeToken<List<Post>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println("....");
            return Collections.emptyList();
        }
    }

    private void saveChangesPosts(List<Post> posts) {
        try (BufferedWriter postWriter = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            GSON.toJson(posts, postWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer generateId(List<Post> posts) {
        return posts.stream()
                .mapToInt(Post::getId).max().orElse(0) + 1;
    }


    @Override
    public Post getById(Integer id) {
        return postsDataSet().stream().filter(l -> l.getId().equals(id)).findFirst().orElseThrow(() -> new FileSystemNotFoundException("Post not found"));
    }

    @Override
    public List<Post> getAll() {
        return postsDataSet().stream().filter(l -> l.getStatus().equals(Status.ACTIVE)).collect(Collectors.toList());
    }


    @Override
    public Post save(Post postToCreate) {
        List<Post> sourcePosts = postsDataSet();
        postToCreate.setId(generateId(sourcePosts));
        sourcePosts.add(postToCreate);
        saveChangesPosts(sourcePosts);
        return postToCreate;
    }


    @Override
    public Post update(Post postToUpdate) {
        List<Post> posts = postsDataSet()
                .stream().map(existingLabel -> {
                    if (existingLabel.getId().equals(postToUpdate.getId())) {
                        return postToUpdate;
                    }
                    return existingLabel;
                }).collect(Collectors.toList());
        saveChangesPosts(posts);
        return postToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        List<Post> posts = postsDataSet()
                .stream().peek(existingLabel -> {
                    if (existingLabel.getId().equals(id)) {
                        existingLabel.setStatus(Status.DELETED);
                    }
                }).collect(Collectors.toList());
        saveChangesPosts(posts);
    }




}
