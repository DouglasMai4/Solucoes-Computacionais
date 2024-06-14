package screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StorageCreate extends JFrame {
    private String[] unitMeasurementItems = {"Kilo", "lote", "unidade", "metro", "litros"};

    public StorageCreate(DefaultTableModel tableModel, Object[] rowData) {
         String name = rowData[0].toString();
         String description = rowData[1].toString();
         int quantity = Integer.parseInt(rowData[2].toString());
         String quantityText = Integer.toString(quantity);
         double price = Double.parseDouble(rowData[3].toString());
         String priceText = Double.toString(price);
         String uniValue = rowData[4].toString();
         int uniIndex = Integer.parseInt(uniValue);
         int rowIndex = Integer.parseInt(rowData[5].toString());


        // defaults
        Font font = new Font("Arial", Font.PLAIN, 32);
        int heigth = 32;
        int heigthInput = heigth + 8;
        int widthLabel = 200;
        int widthInput = 300;
        int xInput = 300;
        int xLabel = 50;

        // frame
        JFrame frame = new JFrame("Criar");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);  // Use null layout for absolute positioning

        // name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        nameLabel.setBounds(xLabel,50, widthLabel, heigth);
        frame.add(nameLabel);
        JTextField nameInput = new JTextField(name);
        nameInput.setFont(font);
        nameInput.setBounds(xInput,50, widthInput, heigthInput);
        frame.add(nameInput);

        // description
        JLabel descriptionLabel = new JLabel("Descricão:");
        descriptionLabel.setFont(font);
        descriptionLabel.setBounds(xLabel,100, widthLabel, heigth);
        frame.add(descriptionLabel);
        JTextField descriptionInput = new JTextField(description);
        descriptionInput.setFont(font);
        descriptionInput.setBounds(xInput,100, widthInput, heigthInput);
        frame.add(descriptionInput);

        // quantity
        JLabel quantityLabel = new JLabel("Quantidade:");
        quantityLabel.setBounds(xLabel,200, widthLabel, heigth);
        quantityLabel.setFont(font);
        frame.add(quantityLabel);
        JTextField quantityInput = new JTextField(quantityText);
        quantityInput.setBounds(xInput,200, widthInput, heigthInput);
        frame.add(quantityInput);
        quantityInput.setFont(font);

        // price
        JLabel priceLabel = new JLabel("Valor:");
        priceLabel.setBounds(xLabel,250, widthLabel, heigth);
        priceLabel.setFont(font);
        frame.add(priceLabel);
        JTextField priceInput = new JTextField(priceText);
        priceInput.setDoubleBuffered(true);
        priceInput.setFont(font);
        priceInput.setBounds(xInput,250, widthInput, heigthInput);
        frame.add(priceInput);

        // unit of measurement
        JLabel unitMeasurementLabel = new JLabel("Unidade de medida:");
        unitMeasurementLabel.setBounds(xLabel, 300, widthLabel, heigth);
        unitMeasurementLabel.setFont(font);
        frame.add(unitMeasurementLabel);
        JComboBox<String> unitMeasurementInput = new JComboBox<>(unitMeasurementItems);
        unitMeasurementInput.setSelectedIndex(uniIndex);
        unitMeasurementInput.setFont(font);
        unitMeasurementInput.setBounds(xInput, 300, widthInput, heigthInput);
        frame.add(unitMeasurementInput);

        // button save
        JButton buttonSave = new JButton("Salvar");
        JPanel panelButtonSave = new JPanel();
        buttonSave.setBounds(xInput,350, widthInput, heigthInput);
        buttonSave.setFont(font);
        panelButtonSave.add(buttonSave);
        frame.add(buttonSave);
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                double price = Double.parseDouble(priceInput.getText());
                int quantity = Integer.parseInt(quantityInput.getText());
                String unitIndex = (String) unitMeasurementInput.getSelectedItem();
                String description = descriptionInput.getText();

                Object[] rowData = new Object[]{name, quantity, price, unitIndex, description, rowIndex};

                if (rowIndex == -1) {
                    tableModel.addRow(rowData);

                    frame.dispose();
                } else {
                    System.out.println(rowIndex);
                    tableModel.setValueAt(name, rowIndex, 0);
                    tableModel.setValueAt(quantity, rowIndex, 1);
                    tableModel.setValueAt(price, rowIndex, 2);
                    tableModel.setValueAt(unitIndex, rowIndex, 3);
                    tableModel.setValueAt(description, rowIndex, 4);
                }

                frame.setVisible(false);
            }
        });

        // button remove
        JButton buttonRemove = new JButton("Remover");
        JPanel panelButtonRemove = new JPanel();
        buttonRemove.setBounds(xInput,450, widthInput, heigthInput);
        buttonRemove.setFont(font);
        panelButtonRemove.add(buttonRemove);
        if (rowIndex != -1) frame.add(buttonRemove);
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(rowIndex);
                tableModel.removeRow(rowIndex);
                frame.setVisible(false);
            }
        });

        // show frame
        frame.setVisible(true);
    }
}
