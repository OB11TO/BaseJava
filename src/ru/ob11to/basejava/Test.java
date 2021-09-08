package ru.ob11to.basejava;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Test extends JPanel {

    Image img = new ImageIcon("/home/obiito/Загрузки/aksi.png").getImage();

    public static void main(String[] args) throws InterruptedException {

        System.err.println("Ты пришел сюда посмотреть на самых горячих девочек?");
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();

        if (str1.equals("да")) {
            System.err.println("Показать тебе самую красивую ?");
            String str2 = scanner.nextLine();
            if (str2.equals("давай")) {
                JFrame fr = new JFrame();
                fr.setVisible(true);



                fr.setLayout(null);
                fr.setSize(639, 831);

                Test m = new Test();
//устанавливаем размеры и координаты компонента для размещения в родителя с абсолютным позиционированием
                m.setBounds(0, 0, 1000, 1000);
                fr.add(m);

//обязательная вещь, говорит о том что когда ты закроешь окно - и приложение тоже должно закрыться, если этого не сделать, то после закрытия окна приложение продолжит работу и будет висеть в памяти
                fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fr.setVisible(true);


                TimeUnit.SECONDS.sleep(15);
                for(int i = 0; i < 5; i++ ) {
                    System.out.println();
                    System.out.println("ИУУУУУУУУ!!!!!!!!!!!");
                }
            }
        }


    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);

    }
}