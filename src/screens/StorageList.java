package screens;

import database.DefaultForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Objects;

public class StorageList extends JFrame {
    private String[] unitMeasurementItems = {"kg", "lote", "unidade", "m", "cm"};

    public StorageList() {
        // Create column names
        String[] columnNames = {"Nome", "Quantidade", "Valor", "Medida", "Descrição"};

        // Create data for the table
        Object[][] data = {
                {"Mangeira", 100, 9.00, unitMeasurementItems[3], "Mangeira de compressor"},
                {"Pneu", 20, 120.00, unitMeasurementItems[2], "Pneu de fusca preto"},
                {"Óleo", 50, 60.00, unitMeasurementItems[0],"Óleo de motor"},
        };

        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        // Set the title of the JFrame
        setTitle("Estoque");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the JFrame
        setSize(400, 600);

        // Center the JFrame on the screen
        setLocationRelativeTo(null);

        // Create add button
        JButton ButtonCreate = new JButton("+ Adicionar");
        ButtonCreate.setBounds(50, 100, 200, 30);
        ButtonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                StorageCreate screen = new StorageCreate(tableModel, form);

//                screen.setVisible(true);
            }
        });
        add(ButtonCreate);

        // Create the JTable with the table model
        JTable table = new JTable(tableModel);
        // Add a MouseListener to the table to handle row clicks
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the selected row index
                int selectedRow = table.getSelectedRow();

                Object uni = table.getValueAt(selectedRow, 3);
                String uniLabel = uni.toString();

                int uniIndex = 0;

                for (int i = 0; i < unitMeasurementItems.length; i++) {
                    String unitMeasurement = unitMeasurementItems[i];
                    if (unitMeasurement == uniLabel) {
                        uniIndex = i;
                    }
                }

                Object[] rowData = {
                    table.getValueAt(selectedRow, 0), // name
                    table.getValueAt(selectedRow, 4), // description
                    table.getValueAt(selectedRow, 1), // quantity
                    table.getValueAt(selectedRow, 2), // price
                    uniIndex, // uni.
                };

                StorageCreate screen = new StorageCreate(tableModel, selectedRow, rowData);

                screen.setVisible(true);
            }
        });

        // Add the table to a scroll pane (to support scrolling)
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the JFrame
        add(scrollPane, BorderLayout.CENTER);
    }
}
