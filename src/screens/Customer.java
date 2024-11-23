package src.screens;

import src.database.CustomerCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class Customer extends JPanel {
    String[] stateItems = new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    public Customer() {
        // set default layout
        setLayout(new GridBagLayout());

        // create table
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Endereço");
        tableModel.addColumn("Cidade");
        tableModel.addColumn("Estado");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Telefone");
        JTable table = new JTable(tableModel);

        // get database data
        CustomerCRUD database = new CustomerCRUD();
        List<CustomerCRUD.Model> data = database.getAll();
        int count = database.getCount();
        for (int i = 0; i < count; i++) {
            CustomerCRUD.Model cliente = data.get(i);

            Object[] row = {
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getEndereco(),
                    cliente.getCidade(),
                    cliente.getEstado(),
                    cliente.getCpf(),
                    cliente.getTelefone(),
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
                    "", // name
                    "", // address
                    "", // city
                    0, // state index
                    "", // cpf
                    "", // phone
                    -1, // row index
                };

                CustomerForm screen = new CustomerForm(tableModel, rowData);

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
                String address = table.getValueAt(rowIndex, 2).toString();
                String city = table.getValueAt(rowIndex, 3).toString();
                String stateValue = table.getValueAt(rowIndex, 4).toString();
                int state = 0;
                for (int i = 0; i < stateItems.length; i++) {
                    if (Objects.equals(stateItems[i], stateValue)) {
                        state = i;
                    }
                }
                String cpf = table.getValueAt(rowIndex, 5).toString();
                String phone = table.getValueAt(rowIndex, 6).toString();

                Object[] rowData = new Object[]{
                        id, // id
                        name, // name
                        address, // address
                        city, // city
                        state, // state
                        cpf, // cpf
                        phone, // phone
                        rowIndex, // row index
                };

                CustomerForm screen = new CustomerForm(tableModel, rowData);

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
