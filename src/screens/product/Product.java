package src.screens.product;

import src.database.ProductCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Product extends JPanel {
    private String[] unitMeasurementItems = {"unidade", "Kilo", "lote",  "metro", "litros"};

    public Product() {
        // set default layout
        setLayout(new GridBagLayout());

        // create table
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("preço");
        tableModel.addColumn("Medida");
        tableModel.addColumn("Descrição");
        JTable table = new JTable(tableModel);

        // get database data
        ProductCRUD database = new ProductCRUD();
        List<ProductCRUD.Model> data = database.getAll();
        int count = database.getCount();
        for (int i = 0; i < count; i++) {
            ProductCRUD.Model product = data.get(i);

            Object[] row = {
                    product.getId(),
                    product.getName(),
                    product.getQuantity(),
                    product.getPrice(),
                    product.getMeasurement_unit(),
                    product.getDescription(),
            };

            tableModel.addRow(row);
        }

        // create table scroll pane
        JScrollPane tableScrollPane = new JScrollPane(table);
        GridBagConstraints gbcTableScrollPane = new GridBagConstraints();
        gbcTableScrollPane.gridx = 0;
        gbcTableScrollPane.gridy = 1;
        gbcTableScrollPane.weightx = 1.0;
        gbcTableScrollPane.weighty = 1.0;
        gbcTableScrollPane.fill = GridBagConstraints.BOTH;

        // create button
        JButton buttonCreate = new JButton("+ Adicionar");
        buttonCreate.setBounds(100, 150, 100, 32);

        // add show create form to button
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] rowData = new Object[]{"", 0, 0, 0, "", -1, -1 };

                ProductForm screen = new ProductForm(tableModel, rowData);

                screen.setVisible(true);
            }
        });

        // add show edit form to button
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            // Get the selected row index
            int rowIndex = table.getSelectedRow();

            int id = Integer.parseInt(table.getValueAt(rowIndex, 0).toString());
            String name = table.getValueAt(rowIndex, 1).toString();
            int quantity = Integer.parseInt(table.getValueAt(rowIndex, 2).toString());
            double price = Double.parseDouble(table.getValueAt(rowIndex, 3).toString());
            Object unitMeasurement = table.getValueAt(rowIndex, 3).toString();
            int unitMeasurementIndex = 0;
            for (int i = 0; i < unitMeasurementItems.length; i++) {
                if (unitMeasurementItems[i] == unitMeasurement) {
                    unitMeasurementIndex = i;
                }
            }
            String description = table.getValueAt(rowIndex, 5).toString();

            Object[] rowData = new Object[]{name, quantity, price, unitMeasurementIndex, description, rowIndex, id };

            ProductForm screen = new ProductForm(tableModel, rowData);

            screen.setVisible(true);
            }
        });

        // create button pane
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonCreate);
        GridBagConstraints gbcButtonPanel = new GridBagConstraints();
        gbcButtonPanel.gridx = 0;
        gbcButtonPanel.gridy = 0;
        gbcButtonPanel.anchor = GridBagConstraints.NORTHEAST;

        // add components
        add(buttonPanel, gbcButtonPanel);
        add(tableScrollPane, gbcTableScrollPane);
    }
}
