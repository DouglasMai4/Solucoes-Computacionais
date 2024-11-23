package src.screens.product;

import src.database.ProductCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ProductForm extends JFrame {
    private String[] unitMeasurementItems = {"Kilo", "lote", "unidade", "metro", "litros"};

    public ProductForm(DefaultTableModel tableModel, Object[] rowData) {
        ProductCRUD database = new ProductCRUD();

        String name = rowData[0].toString();
        String quantity = rowData[1].toString();
        String price = rowData[2].toString();
        int unitMeasurementIndex = Integer.parseInt(rowData[3].toString());
        String description = rowData[4].toString();
        int rowIndex = Integer.parseInt(rowData[5].toString());
        int id = Integer.parseInt(rowData[6].toString());

        // defaults
        Font font = new Font("Arial", Font.PLAIN, 32);
        int heigth = 32;
        int heigthInput = heigth + 8;
        int widthLabel = 200;
        int widthInput = 300;
        int xInput = 300;
        int xLabel = 50;

        // frame
        setName("Criar");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        // name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        nameLabel.setBounds(xLabel,50, widthLabel, heigth);
        add(nameLabel);
        JTextField nameInput = new JTextField(name);
        nameInput.setFont(font);
        nameInput.setBounds(xInput,50, widthInput, heigthInput);
        add(nameInput);

        // description
        JLabel descriptionLabel = new JLabel("Descricão:");
        descriptionLabel.setFont(font);
        descriptionLabel.setBounds(xLabel,100, widthLabel, heigth);
        add(descriptionLabel);
        JTextField descriptionInput = new JTextField(description);
        descriptionInput.setFont(font);
        descriptionInput.setBounds(xInput,100, widthInput, heigthInput);
        add(descriptionInput);

        // quantity
        JLabel quantityLabel = new JLabel("Quantidade:");
        quantityLabel.setBounds(xLabel,200, widthLabel, heigth);
        quantityLabel.setFont(font);
        add(quantityLabel);
        JTextField quantityInput = new JTextField(quantity);
        quantityInput.setBounds(xInput,200, widthInput, heigthInput);
        add(quantityInput);
        quantityInput.setFont(font);

        // price
        JLabel priceLabel = new JLabel("Valor:");
        priceLabel.setBounds(xLabel,250, widthLabel, heigth);
        priceLabel.setFont(font);
        add(priceLabel);
        JTextField priceInput = new JTextField(price);
        priceInput.setDoubleBuffered(true);
        priceInput.setFont(font);
        priceInput.setBounds(xInput,250, widthInput, heigthInput);
        add(priceInput);

        // unit of measurement
        JLabel unitMeasurementLabel = new JLabel("Unidade de medida:");
        unitMeasurementLabel.setBounds(xLabel, 300, widthLabel, heigth);
        unitMeasurementLabel.setFont(font);
        add(unitMeasurementLabel);
        JComboBox<String> unitMeasurementInput = new JComboBox<>(unitMeasurementItems);
        unitMeasurementInput.setSelectedIndex(unitMeasurementIndex);
        unitMeasurementInput.setFont(font);
        unitMeasurementInput.setBounds(xInput, 300, widthInput, heigthInput);
        add(unitMeasurementInput);

        // button save
        JButton buttonSave = new JButton("Salvar");
        JPanel panelButtonSave = new JPanel();
        buttonSave.setBounds(xInput,350, widthInput, heigthInput);
        buttonSave.setFont(font);
        panelButtonSave.add(buttonSave);
        add(buttonSave);
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                int quantity = Integer.parseInt(quantityInput.getText());
                float price = Float.parseFloat(priceInput.getText());
                String unit = (String) unitMeasurementInput.getSelectedItem();
                String description = descriptionInput.getText();

                Object[] newRow = {
                    id, // id
                    name, // name
                    quantity, // quantity
                    price, // price
                    unit, // measurement unit
                    description, // description
                    rowIndex // row index
                };

                if (rowIndex == -1 && id == -1) {
                    int id = database.insert(name, quantity, price, unit, description);

                    if (id == -1) return;

                    newRow[0] = id;

                    tableModel.addRow(newRow);

                    dispose();
                } else {
                    database.update(id, name, quantity, price, unit, description);

                    tableModel.setValueAt(id, rowIndex, 0);
                    tableModel.setValueAt(name, rowIndex, 1);
                    tableModel.setValueAt(quantity, rowIndex, 2);
                    tableModel.setValueAt(price, rowIndex, 3);
                    tableModel.setValueAt(unit, rowIndex, 4);
                    tableModel.setValueAt(description, rowIndex, 5);
                }

                setVisible(false);
            }
        });

        // button remove
        JButton buttonRemove = new JButton("Remover");
        JPanel panelButtonRemove = new JPanel();
        buttonRemove.setBounds(xInput,400, widthInput, heigthInput);
        buttonRemove.setFont(font);
        panelButtonRemove.add(buttonRemove);
        if (rowIndex != -1) add(buttonRemove);
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.delete(rowIndex);
                tableModel.removeRow(rowIndex);
                setVisible(false);
            }
        });

        // show frame
        setVisible(true);
    }
}
