package screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class StorageList extends JFrame {
    private String[] unitMeasurementItems = {"unidade", "Kilo", "lote",  "metro", "litros"};
    private String[] columnNames = {"Nome", "Quantidade", "Valor", "Medida", "Descrição"};

    public StorageList() {
        // Create data for the table
        Object[][] data = {
                {"Mangeira", 100, 9.00, unitMeasurementItems[3], "Mangeira de compressor"},
                {"Pneu", 20, 120.00, unitMeasurementItems[2], "Pneu de fusca preto"},
                {"Óleo", 50, 60.00, unitMeasurementItems[0],"Óleo de motor"},
        };

        // defaults
        Font font = new Font("Arial", Font.PLAIN, 32);
        int heigth = 32;

        setLayout(new GridBagLayout());

        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        // Set the title of the JFrame
        setTitle("Estoque");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the JFrame
        setSize(1100, 800);

        // Center the JFrame on the screen
        setLocationRelativeTo(null);

        // Create a panel for the button
        JPanel buttonPanel = new JPanel();
        JButton buttonCreate = new JButton("+ Adicionar");
        buttonCreate.setFont(font);
        buttonCreate.setBounds(100, 100, 100, heigth);
        buttonPanel.add(buttonCreate);
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] rowData = new Object[]{"", "", 100, 10.00, 0, -1};

                StorageCreate screen = new StorageCreate(tableModel, rowData);

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

                // Get uni index
                Object uni = table.getValueAt(rowIndex, 3);
                String uniLabel = uni.toString();
                int unitIndex = 0;
                for (int i = 0; i < unitMeasurementItems.length; i++) {
                    String unitMeasurement = unitMeasurementItems[i];

                    if (unitMeasurement == uniLabel) {
                        unitIndex = i;
                    }

                }

                String name = table.getValueAt(rowIndex, 0).toString();
                String description = table.getValueAt(rowIndex, 4).toString();
                int quantity = Integer.parseInt(table.getValueAt(rowIndex, 1).toString());
                double price = Double.parseDouble(table.getValueAt(rowIndex, 2).toString());

                Object[] rowData = new Object[]{name, description, quantity, price, unitIndex, rowIndex};

                StorageCreate screen = new StorageCreate(tableModel, rowData);

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
