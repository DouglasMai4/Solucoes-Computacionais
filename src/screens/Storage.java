package screens;

import database.EstoqueCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Storage extends JPanel {
    private String[] unitMeasurementItems = {"unidade", "Kilo", "lote",  "metro", "litros"};

    public Storage() {
        // defaults
        Font font = new Font("Arial", Font.PLAIN, 32);
        int heigth = 32;

        setLayout(new GridBagLayout());

        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Valor");
        tableModel.addColumn("Medida");
        tableModel.addColumn("Descrição");

        // get data from server
        EstoqueCRUD database = new EstoqueCRUD();
        List<EstoqueCRUD.Model> data = database.getAll();
        int count = database.getCount();
        for (int i = 0; i < count; i++) {
            EstoqueCRUD.Model estoque = data.get(i);

            Object[] row = {
                    estoque.getId(),
                    estoque.getNome(),
                    estoque.getQuantidade(),
                    estoque.getValor(),
                    estoque.getUni_medida(),
                    estoque.getDescricao(),
            };

            tableModel.addRow(row);
        }

        // Create a panel for the button
        JPanel buttonPanel = new JPanel();
        JButton buttonCreate = new JButton("+ Adicionar");

        buttonCreate.setFont(font);
        buttonCreate.setBounds(100, 150, 100, heigth);
        buttonPanel.add(buttonCreate);
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] rowData = new Object[]{"", 0, 0, 0, "", -1, -1 };

                EstoqueForm screen = new EstoqueForm(tableModel, rowData);

                screen.setVisible(true);
            }
        });

        // create table
        JTable table = new JTable(tableModel);

        // Set the font size for the header
        JTableHeader header = table.getTableHeader();
        header.setFont(font); // Adjust the size as needed

        // Set the font size for cells
        table.setFont(font); // Adjust the size as needed

        table.setRowHeight(44);

        // show edit form
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

                EstoqueForm screen = new EstoqueForm(tableModel, rowData);

                screen.setVisible(true);
            }
        });

        // Add the table to a scroll pane (to support scrolling)
        JScrollPane scrollPane = new JScrollPane(table);

        // Constraints for buttonPanel
        GridBagConstraints gbcButtonPanel = new GridBagConstraints();
        gbcButtonPanel.gridx = 0;
        gbcButtonPanel.gridy = 0;
        gbcButtonPanel.anchor = GridBagConstraints.NORTHEAST; // Align to the top-right corner

        // Constraints for scrollPane
        GridBagConstraints gbcScrollPane = new GridBagConstraints();
        gbcScrollPane.gridx = 0;
        gbcScrollPane.gridy = 1;
        gbcScrollPane.weightx = 1.0;
        gbcScrollPane.weighty = 1.0;
        gbcScrollPane.fill = GridBagConstraints.BOTH;

        // Add components to the frame using BorderLayout
        add(buttonPanel, gbcButtonPanel);
        add(scrollPane, gbcScrollPane);
    }
}
