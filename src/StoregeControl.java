import javax.swing.*;
import java.awt.event.*;

public class StoregeControl {
    public static void main(String[] args) {
        // frame
        JFrame frame = new JFrame("Criar estoque");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(null);  // Use null layout for absolute positioning

        // name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50,50, 150,30);
        frame.add(nameLabel);
        JTextField nameInput = new JTextField("");
        nameInput.setBounds(200,50, 100,30);
        frame.add(nameInput);

        // quantity
        JLabel quantityLabel = new JLabel("Quantidade:");
        quantityLabel.setBounds(50,100, 100,30);
        frame.add(quantityLabel);
        JTextField quantityInput = new JTextField("");
        quantityInput.setBounds(200,100, 100,30);
        frame.add(quantityInput);

        // quantity
        JLabel priceLabel = new JLabel("Valor:");
        priceLabel.setBounds(50,150, 100,30);
        frame.add(priceLabel);
        JTextField priceInput = new JTextField("");
        priceInput.setBounds(200,150, 100,30);
        frame.add(priceInput);

        // unit of measurement
        JLabel unitMeasurementLabel = new JLabel("Unidade de medida:");
        unitMeasurementLabel.setBounds(50, 200, 200, 30);
        frame.add(unitMeasurementLabel);

        String[] items = {"kg", "lote", "unidade", "m", "cm"};
        JComboBox<String> unitMeasurementInput = new JComboBox<>(items);
        unitMeasurementInput.setBounds(200, 200, 100, 30);
        frame.add(unitMeasurementInput);

        // create calc button
        JButton saveButton = new JButton("SALVAR");
        saveButton.setBounds(200,250, 100,30);
        frame.add(saveButton);

        // show frame
        frame.setVisible(true);
    }
}
