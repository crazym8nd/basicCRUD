package com.vitaly.crudapp.view;


import com.vitaly.crudapp.controller.WriterController;
import com.vitaly.crudapp.model.pojo.Writer;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    private final WriterController writerController = new WriterController();
    private final Scanner scanner = new Scanner(System.in);

    private final String menuWriter = "Выберете действие:\n" +
            " 1. Создать писателя\n" +
            " 2. Редактировать писателя\n" +
            " 3. Удалить писателя\n" +
            " 4. Вывести список писателей\n" +
            " 5. Выход в главное меню";
    private final String printWriterList = "Список писателей:\n";
    private final String createWriterMsg = "Создание писателя.\n" + "Введите  первое имя: ";
    private final String editWriterMsg = "Редактирование писателя. Введите ID:";
    private final String deleteWriterMsg = "Удаление писателя. Введите ID:";

    public void createWriter() {
        System.out.println(createWriterMsg);
        String firstName = scanner.nextLine();
        System.out.println("Введите  фамилию:");
        String lastName = scanner.nextLine();
        try{
            Writer createdWriter = writerController.createWriter(firstName, lastName);
            System.out.println("Писатель создан:" + createdWriter);
        } catch (Exception e){
            System.out.println("Ошибка при создании писателя");
        }
    }

    public void editWriter(){
        readWriters();
        System.out.println(editWriterMsg);

        try{
            Integer id = Integer.parseInt(scanner.nextLine());
            Writer writerToUpdate = writerController.getById(id);
            System.out.println("Введите имя:");
            String firstName = scanner.nextLine();
            writerToUpdate.setFirstName(firstName);
            System.out.println("Введите фамилию:");
            String lastName = scanner.nextLine();
            writerToUpdate.setLastName(lastName);
            writerToUpdate.setWriterPosts(writerController.addPosts(writerController.selectPosts()));
            writerController.update(writerToUpdate);
            System.out.println("Изменения сохранены");
        }catch (Exception e){
            System.out.println("Ошибка при редактировании писателя");
        }
    }

    public void deleteWriter(){
        readWriters();
        System.out.println(deleteWriterMsg);
        Integer id = scanner.nextInt();
        try{
            writerController.delete(id);
            System.out.println("Писатель удален");
        } catch (Exception e){
            System.out.println("Ошибка при удалении писателя");
        }
    }

    public void readWriters(){
        List<Writer> writers = null;
        try{
            writers = writerController.getAll();
        } catch (Exception e){
            System.out.println("Ошибка при получении списка писателей");
        }

        System.out.println(printWriterList);
        writers.sort(Comparator.comparing(Writer::getId));
        for (Writer w : writers){
            System.out.println(w.getId() + " " + w.getFirstName() + " " + w.getLastName() + " " + w.getWriterPosts());
        }
    }

    public void show(){
        boolean isExit = false;
        do {
            System.out.println(menuWriter);
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    createWriter();
                    break;
                case "2":
                    editWriter();
                    break;
                case "3":
                    deleteWriter();
                    break;
                case "4":
                    readWriters();
                    break;
                case "5":
                    isExit = true;
                    break;
                default:
                    System.out.println("Выберите пункт меню");
                    break;
            }
        } while (!isExit);
    }





}
