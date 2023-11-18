package com.vitaly.crudapp.view;

import com.vitaly.crudapp.model.pojo.Label;

import java.util.Scanner;

public class MainView {

    private final Scanner scanner = new Scanner(System.in);
    private final LabelView labelView = new LabelView();
//    private final PostView postView = new PostView();
//    private final WriterView writerView = new WriterView();
//
//

    private final String menu = "Выберете действие:\n" +
            "1. Лейблы\n" +
            "2. Посты\n" +
            "3. Писатели\n" +
            "4. Выход";


    public void start() {
        boolean isExit = false;
        do {
            System.out.println(menu);
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "1":
                    labelView.show();
                    break;
//                case "2":
//                    postViewSHOW
//                    break;
//                case "3":
//                    writerViewSHOW
//                    break;
                case "4":
                    isExit = true;
                    break;
                default:
                    System.out.println("Если вы не кот, то выберите пункт меню и введите цифру!");
                    break;
            }

        } while (!isExit);
        scanner.close();

    }
}
