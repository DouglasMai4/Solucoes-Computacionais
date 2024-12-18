package src.screens.customer;

import src.database.CustomerCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerForm extends JFrame {
    String[] stateItems = new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    public CustomerForm(DefaultTableModel tableModel, Object[] rowData) {
        CustomerCRUD database = new CustomerCRUD();

        setName("Criar");

        int id = Integer.parseInt(rowData[0].toString());
        String name = rowData[1].toString();
        int phone = Integer.parseInt(rowData[2].toString());
        int document = Integer.parseInt(rowData[3].toString());
        String address = rowData[4].toString();
        String city = rowData[5].toString();
        int stateIndex = Integer.parseInt(rowData[6].toString());
        int rowIndex = Integer.parseInt(rowData[7].toString());

        // defaults
        Font font = new Font("Arial", Font.PLAIN, 32);
        int heigth = 32;
        int heigthInput = heigth + 8;
        int widthLabel = 200;
        int widthInput = 300;
        int xInput = 300;
        int xLabel = 50;

        // frame
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

        // phone
        JLabel phoneLabel = new JLabel("Telefone:");
        phoneLabel.setBounds(xLabel,250, widthLabel, heigth);
        phoneLabel.setFont(font);
        add(phoneLabel);
        JTextField phoneInput = new JTextField(phone == -1 ? "" : String.valueOf(phone));
        phoneInput.setDoubleBuffered(true);
        phoneInput.setFont(font);
        phoneInput.setBounds(xInput,250, widthInput, heigthInput);
        add(phoneInput);

        // document
        JLabel documentLabel = new JLabel("CPF:");
        documentLabel.setBounds(xLabel,200, widthLabel, heigth);
        documentLabel.setFont(font);
        add(documentLabel);
        JTextField documentInput = new JTextField(document == -1 ? "" : String.valueOf(document));
        documentInput.setDoubleBuffered(true);
        documentInput.setFont(font);
        documentInput.setBounds(xInput,200, widthInput, heigthInput);
        add(documentInput);

        // address
        JLabel addressLabel = new JLabel("Endereço:");
        addressLabel.setBounds(xLabel,100, widthLabel, heigth);
        addressLabel.setFont(font);
        add(addressLabel);
        JTextField addressInput = new JTextField(address);
        addressInput.setDoubleBuffered(true);
        addressInput.setFont(font);
        addressInput.setBounds(xInput,100, widthInput, heigthInput);
        add(addressInput);

        // city
        JLabel cityLabel = new JLabel("Cidade:");
        cityLabel.setBounds(xLabel,150, widthLabel, heigth);
        cityLabel.setFont(font);
        add(cityLabel);
        JTextField cityInput = new JTextField(city);
        cityInput.setDoubleBuffered(true);
        cityInput.setFont(font);
        cityInput.setBounds(xInput,150, widthInput, heigthInput);
        add(cityInput);

        // state
        JLabel stateLabel = new JLabel("Unidade de medida:");
        stateLabel.setBounds(xLabel, 300, widthLabel, heigth);
        stateLabel.setFont(font);
        add(stateLabel);
        JComboBox<String> stateInput = new JComboBox<>(stateItems);
        stateInput.setSelectedIndex(stateIndex);
        stateInput.setFont(font);
        stateInput.setBounds(xInput, 300, widthInput, heigthInput);
        add(stateInput);

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
                int phone = 0;
                int document = 0;

                try {
                    phone = Integer.parseInt(phoneInput.getText());

                    if (phone < 0) {
                        throw new NumberFormatException("Phone number must be greater than 0");
                    }
                }
                catch (NumberFormatException err) {
                    System.out.println("Invalid input: " + err.getMessage());

                    JOptionPane.showMessageDialog(
                        null,
                        "Por favor, informe apenas numeros para o telefone.",
                        "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }

                try {
                    document = Integer.parseInt(documentInput.getText());

                    if (document < 0) {
                        throw new NumberFormatException("Document number must be greater than 0");
                    }
                }
                catch (NumberFormatException err) {
                    System.out.println("Invalid input: " + err.getMessage());

                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, informe apenas numeros para o CPF.",
                            "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }

                if (phone == 0 || document == 0) return;

                String address = addressInput.getText();
                String city = cityInput.getText();
                String state = (String) stateInput.getSelectedItem();

                Object[] newRow = {
                    id, // id
                    name, // name
                    phone, // phone
                    document, // document
                    address, // address
                    city, // city
                    state, // state
                    rowIndex // row index
                };

                // insert on table
                if (rowIndex == -1 && id == -1) {
                    int id = database.insert(name, phone, document, address, city, state);

                    if (id == -1) return;

                    newRow[0] = id;

                    tableModel.addRow(newRow);

                    dispose();
                    setVisible(false);
                }

                // update on table
                else {
                    database.update(id, name, phone, document, address, city, state);

                    tableModel.setValueAt(id, rowIndex, 0);
                    tableModel.setValueAt(name, rowIndex, 1);
                    tableModel.setValueAt(phone, rowIndex, 2);
                    tableModel.setValueAt(document, rowIndex, 3);
                    tableModel.setValueAt(address, rowIndex, 4);
                    tableModel.setValueAt(city, rowIndex, 5);
                    tableModel.setValueAt(state, rowIndex, 6);
                    setVisible(false);
                }
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
                database.delete(id);
                tableModel.removeRow(rowIndex);
                setVisible(false);
            }
        });
    }
}
