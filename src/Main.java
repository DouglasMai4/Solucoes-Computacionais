import database.Estoque;
import screens.StorageCreate;
import screens.StorageList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StorageList ScreenList = new StorageList();

        ScreenList.setVisible(true);
    }
}