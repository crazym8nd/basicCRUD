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
            " 1. Создать пост\n" +
            " 2. Редактировать пост\n" +
            " 3. Удалить пост\n" +
            " 4. Вывести список постов\n" +
            " 5. Выход в главное меню";
    private final String printPostList = "Список постов:\n";
    private final String createPostMsg = "Создание поста.\n" + "Введите заголовок: ";
    private final String editPostMsg = "Редактирование поста. Введите ID:";
    private final String deletePostMsg = "Удаление пост. Введите ID:";

    public void createPost() {
        System.out.println(createPostMsg);
        String title = scanner.nextLine();
        System.out.println("Введите контент:");
        String content = scanner.nextLine();
        try {
            Post createdPost = postController.createPost(title, content);
            System.out.println("Пост создан:\n" + createdPost);
        } catch (Exception e) {
            System.out.println("Ошибка при создании поста");
        }
    }

    public void editPost() {
        readPosts();
        System.out.println(editPostMsg);
        try {
            Integer id = Integer.parseInt(scanner.nextLine());
            Post postToUpdate = postController.getById(id);
            System.out.println(postToUpdate);
            System.out.println("Введите новое имя:");
            String title = scanner.nextLine();
            System.out.println("Введите новый контент:");
            String content = scanner.nextLine();
            postToUpdate.setTitle(title);
            postToUpdate.setContent(content);
            postToUpdate.setPostLabels(postController.addLabels(postController.selectLabels()));
            postController.update(postToUpdate);
            System.out.println("Изменения сохранены");
        } catch (Exception e) {
            System.out.println("Ошибка при редактировании поста");
        }
    }

    public void deletePost() {
        System.out.println(deletePostMsg);
        Integer id = scanner.nextInt();
        try {
            postController.delete(id);
            System.out.println("Пост удален");
        } catch (Exception e) {
            System.out.println("Ошибка при удалени поста");
        }
    }

    public void readPosts() {
        List<Post> posts = null;
        try {
            posts = postController.getAll();
        } catch (Exception e) {
            System.out.println("Ошибка при чтении списка постов");
        }

        System.out.println(printPostList);
        posts.sort(Comparator.comparing(Post::getId));
        for (Post p : posts) {
            System.out.println(p.getId() + " " + p.getTitle() + " " + p.getContent() + " " + p.getPostLabels());
        }
    }

    public void show() {
        boolean isExit = false;
            do{
                System.out.println(menuPost);
                String selectmenu = scanner.nextLine();
                switch (selectmenu) {
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
                        System.out.println("Выберите действие из меню.");
                        break;
                }

            }while (!isExit);
    }
}

