package com.vitaly.crudapp.view;

import com.vitaly.crudapp.controller.PostController;
import com.vitaly.crudapp.model.pojo.Post;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PostView {
    private final PostController postController = new PostController();
    private final Scanner scanner = new Scanner(System.in);

    private final String menuPost = "Выберете действие:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список постов\n" +
            " 5. Выход";
    private final String printPostList = "Список постов:\n";
    private final String createPostMsg = "Создание поста.\n" + "Введите заголовок: ";
    private final String editPostMsg = "Edit post. Enter ID:";
    private final String deletePostMsg = "Delete Post.Enter ID:";

    public void createPost() {
        System.out.println("Enter title");
        String title = scanner.nextLine();
        try{
            Post createdPost = postController.createPost(title);
            System.out.println("Post created:" + createdPost);
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    public void editPost(){
        System.out.println(editPostMsg);
        try{
            Integer id = Integer.parseInt(scanner.nextLine());
            Post postToUpdate = postController.getById(id);
            System.out.println("Enter new title:");
            String title = scanner.nextLine();
            postToUpdate.setTitle(title);
            postController.update(postToUpdate);
            System.out.println("Changes saved");
        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public void deletePost(){
        System.out.println(deletePostMsg);
        Integer id = scanner.nextInt();
        try{
            postController.delete(id);
            System.out.println("Post deleted");
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    public void readPosts(){
        List<Post> posts = null;
        try{
            posts = postController.getAll();
        } catch (Exception e){
            System.out.println("Error");
        }

        System.out.println(printPostList);
        posts.sort(Comparator.comparing(Post::getId));
        for (Post p : posts){
            System.out.println(p.getId() +" "+ p.getTitle());
        }
    }

    public void show(){
        boolean isExit = false;
        do {
            System.out.println(menuPost);
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    createPost();
                    break;
                case "2":
                    editPost();
                    break;
                case "3":
                    deletePost();
                    break;
                case "4":
                    readPosts();
                    break;
                case "5":
                    isExit = true;
                    break;
                default:
                    System.out.println("Choose Option!");
                    break;
            }
        } while (!isExit);
    }


}
