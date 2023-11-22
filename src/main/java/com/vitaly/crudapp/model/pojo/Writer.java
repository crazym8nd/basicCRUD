package com.vitaly.crudapp.model.pojo;

import java.util.List;

public class Writer {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Integer> postsIds;
    private List<Post> writerPosts;
    private Status status;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public List<Integer> getPostsIds() {
        return postsIds;
    }

    public void setPostsIds(List<Integer> postsIds) {
        this.postsIds = postsIds;
    }


}
