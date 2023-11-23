package com.vitaly.crudapp.view;

import com.vitaly.crudapp.controller.LabelController;
import com.vitaly.crudapp.model.pojo.Label;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class LabelView {

    private final LabelController labelController = new LabelController();
    private final Scanner scanner = new Scanner(System.in);

    private final String menuLabel = "Выберете действие:\n" +
            " 1. Создать лейбл\n" +
            " 2. Редактировать лейбл\n" +
            " 3. Удалить лейбл\n" +
            " 4. Вывести список лейблов\n" +
            " 5. Выход в главное меню";
    private final String printLabelList = "Список лейблов:\n";
    private final String createLabelMsg = "Создание лейбла.\n" + "Введите название: ";
    private final String editLabelMsg = "Редактирование лейбла. Введите ID:";
    private final String deleteLabelMsg = "Удаление лейбла.Введите ID:";

    public void createLabel() {
        System.out.println(createLabelMsg);
        String name = scanner.nextLine();
        try{Label createdLabel = labelController.createLabel(name);
            System.out.println("Лейбл создан:" + createdLabel);
        } catch (Exception e){
            System.out.println("Ошибка при создании лейбла");
        }
    }

    public void editLabel(){
        readLabels();
        System.out.println(editLabelMsg);
        try{
            Integer id = Integer.parseInt(scanner.nextLine());
            Label labelToUpdate = labelController.getById(id);
            System.out.println("Введите новое имя:");
            String name = scanner.nextLine();
            labelToUpdate.setName(name);
            labelController.update(labelToUpdate);
            System.out.println("Изменения сохранены");
        }catch (Exception e){
            System.out.println("Не получилось изменить лейбл");
        }
    }

    public void deleteLabel(){
        readLabels();
        System.out.println(deleteLabelMsg);
        Integer id = scanner.nextInt();
        try{
            labelController.delete(id);
            System.out.println("Лейбл удален");
        } catch (Exception e){
            System.out.println("Ошибка при удалении лейбла");
        }
    }

    public void readLabels(){
        List<Label> labels = null;
        try{
            labels = labelController.getAll();
        } catch (Exception e){
            System.out.println("Ошибка при получении списка лейблов");
        }

        System.out.println(printLabelList);
        labels.sort(Comparator.comparing(Label::getId));
        for (Label l : labels){
            System.out.println(l.getId() +" "+ l.getName());
        }
    }

    public void show(){
        boolean isExit = false;
        do {
            System.out.println(menuLabel);
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    createLabel();
                    break;
                case "2":
                    editLabel();
                    break;
                case "3":
                    deleteLabel();
                    break;
                case "4":
                    readLabels();
                    break;
                case "5":
                    isExit = true;
                    break;
                default:
                    System.out.println("Выберите пункт меню.");
                    break;
            }
        } while (!isExit);
    }

}



