package src.screens.purchase;

import src.database.PurchaseCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class PurchaseForm extends JFrame {
    String[] stateItems = new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    public PurchaseForm(DefaultTableModel tableModel, Object[] rowData) {
        PurchaseCRUD database = new PurchaseCRUD();

        setName("Criar");

        int id = Integer.parseInt(rowData[0].toString());
        int customerId = Integer.parseInt(rowData[1].toString());
        int productId = Integer.parseInt(rowData[2].toString());
        int quantity = Integer.parseInt(rowData[3].toString());
        boolean finished = Boolean.parseBoolean(rowData[4].toString());
        float price = Float.parseFloat(rowData[5].toString());
        int rowIndex = Integer.parseInt(rowData[6].toString());

        // defaults
        Font font = new Font("Arial", Font.PLAIN, 32);
        int heigth = 32;
        int heigthInput = heigth + 8;
        int widthLabel = 200;
        int widthInput = 300;
        int xInput = 300;
        int xLabel = 50;
        Font fontRadio = new Font("Arial", Font.PLAIN, 24);

        // frame
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        // customer id
        JLabel customerLabel = new JLabel("Id do cliente:");
        customerLabel.setFont(font);
        customerLabel.setBounds(xLabel,50, widthLabel, heigth);
        add(customerLabel);
        JTextField customerInput = new JTextField(customerId == -1 ? "" : String.valueOf(customerId));
        customerInput.setFont(font);
        customerInput.setBounds(xInput,50, widthInput, heigthInput);
        add(customerInput);

        // product id
        JLabel productLabel = new JLabel("Id do produto:");
        productLabel.setFont(font);
        productLabel.setBounds(xLabel,100, widthLabel, heigth);
        add(productLabel);
        JTextField productInput = new JTextField(productId == -1 ? "" : String.valueOf(productId));
        productInput.setFont(font);
        productInput.setBounds(xInput,100, widthInput, heigthInput);
        add(productInput);

        // quantity
        JLabel quantityLabel = new JLabel("Quantidade:");
        quantityLabel.setBounds(xLabel,150, widthLabel, heigth);
        quantityLabel.setFont(font);
        add(quantityLabel);
        JTextField quantityInput = new JTextField(quantity == -1 ? "" : String.valueOf(quantity));
        quantityInput.setBounds(xInput,150, widthInput, heigthInput);
        add(quantityInput);
        quantityInput.setFont(font);

        // finished
        JLabel finishedLabel = new JLabel("Finalizado:");
        finishedLabel.setBounds(xLabel,200, widthLabel, heigth);
        finishedLabel.setFont(font);
        add(finishedLabel);
        JRadioButton trueFinishedButton = new JRadioButton("Sim");
        trueFinishedButton.setMnemonic(KeyEvent.VK_R);
        trueFinishedButton.setActionCommand("true");
        trueFinishedButton.setSelected(finished);
        trueFinishedButton.setFont(fontRadio);
        trueFinishedButton.setBounds(xInput,200, widthInput / 2, heigthInput);
        add(trueFinishedButton);
        JRadioButton falseFinishedButton = new JRadioButton("Não");
        falseFinishedButton.setMnemonic(KeyEvent.VK_R);
        falseFinishedButton.setActionCommand("false");
        falseFinishedButton.setFont(fontRadio);
        falseFinishedButton.setSelected(!finished);
        falseFinishedButton.setBounds(xInput + widthInput / 2,200, widthInput, heigthInput);
        add(falseFinishedButton);
        ButtonGroup finishedButtonGroup = new ButtonGroup();
        finishedButtonGroup.add(trueFinishedButton);
        finishedButtonGroup.add(falseFinishedButton);

        // price
        JLabel priceLabel = new JLabel("Valor:");
        priceLabel.setBounds(xLabel,250, widthLabel, heigth);
        priceLabel.setFont(font);
        add(priceLabel);
        JTextField priceInput = new JTextField(price == -1 ? "" : String.valueOf(price));
        priceInput.setDoubleBuffered(true);
        priceInput.setFont(font);
        priceInput.setBounds(xInput,250, widthInput, heigthInput);
        add(priceInput);

        // button save
        JButton buttonSave = new JButton("Salvar");
        JPanel panelButtonSave = new JPanel();
        buttonSave.setBounds(xInput,300, widthInput, heigthInput);
        buttonSave.setFont(font);
        panelButtonSave.add(buttonSave);
        add(buttonSave);
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int customerId = 0;
                int productId = 0;
                int quantity = 0;
                float price = 0;
                boolean finished = trueFinishedButton.isSelected();

                try {
                    customerId = Integer.parseInt(customerInput.getText());

                    if (customerId < 0) {
                        throw new NumberFormatException("Customer id number must be greater than 0");
                    }
                }
                catch (NumberFormatException err) {
                    System.out.println("Invalid input: " + err.getMessage());

                    JOptionPane.showMessageDialog(
                        null,
                        "Por favor, informe apenas numeros para o id do cliente.",
                        "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }

                try {
                    productId = Integer.parseInt(productInput.getText());

                    if (productId < 0) {
                        throw new NumberFormatException("Product id number must be greater than 0");
                    }
                }
                catch (NumberFormatException err) {
                    System.out.println("Invalid input: " + err.getMessage());

                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, informe apenas numeros para o id do cliente.",
                            "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }

                try {
                    quantity = Integer.parseInt(quantityInput.getText());

                    if (quantity < 0) {
                        throw new NumberFormatException("Quantity number must be greater than 0");
                    }
                }
                catch (NumberFormatException err) {
                    System.out.println("Invalid input: " + err.getMessage());

                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, informe apenas numeros para a quantidade.",
                            "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }

                try {
                    price = Float.parseFloat(priceInput.getText());

                    if (price < 0) {
                        throw new NumberFormatException("Price number must be greater than 0");
                    }
                }
                catch (NumberFormatException err) {
                    System.out.println("Invalid input: " + err.getMessage());

                    JOptionPane.showMessageDialog(
                            null,
                            "Por favor, informe apenas numeros para o preco.",
                            "Erro", JOptionPane.ERROR_MESSAGE
                    );
                }

                if (customerId == 0 || productId == 0 || quantity == 0 || price == 0) return;

                Object[] newRow = {
                    id, // id
                    customerId, // customer id
                    productId, // product id
                    quantity, // quantity
                    finished, // finished
                    price, // price
                    rowIndex // row index
                };

                // insert on table
                if (rowIndex == -1 && id == -1) {
                    int id = database.insert(customerId, productId, quantity, finished, price);

                    if (id == -1) return;

                    newRow[0] = id;

                    tableModel.addRow(newRow);

                    dispose();
                    setVisible(false);
                }

                // update on table
                else {
                    database.update(id, customerId, productId, quantity, finished, price);

                    tableModel.setValueAt(id, rowIndex, 0);
                    tableModel.setValueAt(customerId, rowIndex, 1);
                    tableModel.setValueAt(productId, rowIndex, 2);
                    tableModel.setValueAt(quantity, rowIndex, 3);
                    tableModel.setValueAt(finished, rowIndex, 4);
                    tableModel.setValueAt(price, rowIndex, 5);
                    setVisible(false);
                }
            }
        });

        // button remove
        JButton buttonRemove = new JButton("Remover");
        JPanel panelButtonRemove = new JPanel();
        buttonRemove.setBounds(xInput,350, widthInput, heigthInput);
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
