package com.vitaly.crudapp.view;

import com.vitaly.crudapp.controller.LabelController;
import com.vitaly.crudapp.model.pojo.Label;
import com.vitaly.crudapp.repository.impls.GsonLabelRepositoryImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class LabelView {

    private final LabelController labelController = new LabelController();
    private final Scanner scanner = new Scanner(System.in);

    private final String menuLabel = "Выберете действие:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список лейблов\n" +
            " 5. Выход";
    private final String printLabelList = "Список лейблов:\n";
    private final String createLabelMsg = "Создание лейбла.\n" + "Введите название: ";
    private final String editLabelMsg = "Edit label. Enter ID:";
    private final String deleteLabelMsg = "Delete Label.Enter ID:";

    public void createLabel() {
        System.out.println("Enter name");
        String name = scanner.nextLine();

        Label createdLabel = labelController.createLabel(name);
        System.out.println("Label created:" + createdLabel);
    }

    public void editLabel(){
        System.out.println(editLabelMsg);
        Integer id = scanner.nextInt();
        Label labelToUpdate = labelController.getById(id);

        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        labelToUpdate.setName(name);
        labelController.update(labelToUpdate);
        System.out.println("Changes saved");
    }
    public void deleteLabel(){
        System.out.println(deleteLabelMsg);
        Integer id = scanner.nextInt();
        labelController.delete(id);
        System.out.println("Label delted");
    }

    public void readLabels(){
        List<Label> labels = labelController.getAll();
        System.out.println(printLabelList);
        Collections.sort(labels, Comparator.comparing(Label::getId));
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
                    System.out.println("Choose Option!");
                    break;
            }
        } while (!isExit);
    }

}



