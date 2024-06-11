package screens;

import database.DefaultForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class StorageCreate extends JFrame {
    private String[] unitMeasurementItems = {"kg", "lote", "unidade", "m", "cm"};


    public StorageCreate(DefaultTableModel tableModel, int rowIndex, Object[] rowData) {
        // frame
        JFrame frame = new JFrame("Criar");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);  // Use null layout for absolute positioning

        // name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50,50, 150,30);
        frame.add(nameLabel);
        JTextField nameInput = new JTextField((String) rowData[0]);
        nameInput.setBounds(200,50, 100,30);
        frame.add(nameInput);

        // description
        JLabel descriptionLabel = new JLabel("Descricão:");
        descriptionLabel.setBounds(50,100, 150,30);
        frame.add(descriptionLabel);
        JTextField descriptionInput = new JTextField(rowData[1].toString());
        descriptionInput.setBounds(200,100, 100,60);
        frame.add(descriptionInput);

        // quantity
        JLabel quantityLabel = new JLabel("Quantidade:");
        quantityLabel.setBounds(50,200, 100,30);
        frame.add(quantityLabel);
        JTextField quantityInput = new JTextField(rowData[2].toString());
        quantityInput.setBounds(200,200, 100,30);
        frame.add(quantityInput);

        // price
        JLabel priceLabel = new JLabel("Valor:");
        priceLabel.setBounds(50,250, 100,30);
        frame.add(priceLabel);
        JTextField priceInput = new JTextField(rowData[2].toString());
        priceInput.setBounds(200,250, 100,30);
        frame.add(priceInput);

        // unit of measurement
        JLabel unitMeasurementLabel = new JLabel("Unidade de medida:");
        unitMeasurementLabel.setBounds(50, 300, 200, 30);
        frame.add(unitMeasurementLabel);
        JComboBox<String> unitMeasurementInput = new JComboBox<>(unitMeasurementItems);
        String uniValue = rowData[4].toString();
        int uniIndex = Integer.parseInt(uniValue);
        unitMeasurementInput.setSelectedIndex(uniIndex);
        unitMeasurementInput.setBounds(200, 300, 100, 30);
        frame.add(unitMeasurementInput);

        // create calc button
        JButton saveButton = new JButton("SALVAR");
        saveButton.setBounds(200,350, 100,30);
        frame.add(saveButton);

        // Action Listener for Save Button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get data from input fields
                String name = nameInput.getText();
                int quantity = Integer.parseInt(quantityInput.getText());
                double price = Double.parseDouble(priceInput.getText());
                String unit = (String) unitMeasurementInput.getSelectedItem();
                String description = ""; // You can add a field for description if needed

                // Add the new row to the table model
                tableModel.addRow(new Object[]{name, quantity, price, unit, description});

                // Close the create frame
                frame.dispose();
            }
        });

        // show frame
        frame.setVisible(true);
    }
}
