package com.vitaly.crudapp.model.pojo;

import java.util.List;

public class Post {
    private Integer id;
    private String title;
    private String content;
    private Status status;
    private List<Label> postLabels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public List<Label> getPostLabels() {
        return postLabels;
    }

    public void setPostLabels(List<Label> postLabels) {
        this.postLabels = postLabels;
    }
    public String toString() {
        return "Post id=" + id + "\n" + "Post title=" + title + "\n" + "Post content=" + content + "\n" + "Labels=" + postLabels;
    }

}

