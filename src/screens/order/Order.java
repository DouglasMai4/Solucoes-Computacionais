package src.screens.order;

import src.database.OrderCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class Order extends JPanel {
    String[] stateItems = new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    public Order() {
        // set default layout
        setLayout(new GridBagLayout());

        // create table
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("Id do cliente");
        tableModel.addColumn("Descrição");
        tableModel.addColumn("Finalizado");
        tableModel.addColumn("Preço");
        JTable table = new JTable(tableModel);

        // get database data
        OrderCRUD database = new OrderCRUD();
        List<OrderCRUD.Model> data = database.getAll();
        int count = database.getCount();
        for (int i = 0; i < count; i++) {
            OrderCRUD.Model customer = data.get(i);

            Object[] row = {
                customer.getId(),
                customer.getCustomer_id(),
                customer.getDescription(),
                customer.getFinished(),
                customer.getPrice(),
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
                Object[] rowData = new Object[]{
                    -1, // id
                    -1, // customer id
                    "", // description
                    false, // finished
                    -1, // price
                    -1, // row index
                };

                OrderForm screen = new OrderForm(tableModel, rowData);

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
            int customerId = Integer.parseInt(table.getValueAt(rowIndex, 1).toString());
            String description = table.getValueAt(rowIndex, 2).toString();
            boolean finished = Boolean.parseBoolean(table.getValueAt(rowIndex, 3).toString());
            float price = Float.parseFloat(table.getValueAt(rowIndex, 4).toString());

            Object[] rowData = new Object[]{
                    id, // id
                    customerId, // customer id
                    description, // description
                    finished, // finished
                    price, // price
                    rowIndex, // row index
            };

            OrderForm screen = new OrderForm(tableModel, rowData);

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
