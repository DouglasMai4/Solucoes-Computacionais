import database.EstoqueCRUD;
import screens.StorageCreate;
import screens.StorageList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EstoqueCRUD database = new EstoqueCRUD();

        // Insert a new contact
//        database.insert("Produto A", 100, 9.90, "kilo", "Descrição do Produto A");
//        database.update(3, "Produto Z", 12, 99.9, "unidade", "Descrição do Produto Z");
//        database.delete(4);

        // Retrieve all contacts
        System.out.println("All Contacts:");
        List<EstoqueCRUD.Model> rows = database.getAll();

        for (EstoqueCRUD.Model estoque : rows) {
            System.out.println(estoque.getId() + ", " + estoque.getNome() + ", " + estoque.getQuantidade() + ", " + estoque.getValor() + ", " + estoque.getUni_medida() + ", " + estoque.getDescricao());
        }
//        StorageList ScreenList = new StorageList();
//
//        ScreenList.setVisible(true);
    }
}