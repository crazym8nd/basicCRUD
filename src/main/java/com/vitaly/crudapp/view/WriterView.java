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
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список писателей\n" +
            " 5. Выход";
    private final String printWriterList = "Список писателей:\n";
    private final String createWriterMsg = "Создание писателя.\n" + "Введите  первое имя: ";
    private final String editWriterMsg = "Edit writer. Enter ID:";
    private final String deleteWriterMsg = "Delete writer.Enter ID:";

    public void createWriter() {
        System.out.println("Enter first name    ");
        String firstName = scanner.nextLine();
        try{
            Writer createdWriter = writerController.createWriter(firstName);
            System.out.println("Writer created:" + createdWriter);
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    public void editWriter(){
        System.out.println(editWriterMsg);
        try{
            Integer id = Integer.parseInt(scanner.nextLine());
            Writer writerToUpdate = writerController.getById(id);
            System.out.println("Enter new firstname:");
            String firstName = scanner.nextLine();
            writerToUpdate.setFirstName(firstName);
            writerController.update(writerToUpdate);
            System.out.println("Changes saved");
        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public void deleteWriter(){
        System.out.println(deleteWriterMsg);
        Integer id = scanner.nextInt();
        try{
            writerController.delete(id);
            System.out.println("Writer deleted");
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    public void readWriters(){
        List<Writer> writers = null;
        try{
            writers = writerController.getAll();
        } catch (Exception e){
            System.out.println("Error");
        }

        System.out.println(printWriterList);
        writers.sort(Comparator.comparing(Writer::getId));
        for (Writer w : writers){
            System.out.println(w.getId() +" "+ w.getFirstName());
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
                    System.out.println("Choose Option!");
                    break;
            }
        } while (!isExit);
    }





}
