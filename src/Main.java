import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args) {

        JFrame obj = new JFrame();
        Mäng mäng = new Mäng();
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Ruudulõhkuja");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(mäng);
        obj.setLocationRelativeTo(null);
    }
}

