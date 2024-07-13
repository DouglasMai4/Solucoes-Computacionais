package screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComprasForm extends JFrame {
    public ComprasForm(DefaultTableModel tableModel, Object[] rowData) {
        setBounds(100, 100, 450, 140);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel nome_label = new JLabel("Cliente");
        nome_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nome_label.setBounds(10, 11, 87, 14);
        getContentPane().add(nome_label);

        JTextField cliente = new JTextField("");
        cliente.setBounds(107, 8, 317, 22);

//        DefaultComboBoxModel modelClientes = new DefaultComboBoxModel<>();

//        try {
//            while (clientesData.next()) {
//                String nomeCliente = clientesData.getString("nome");
//                modelClientes.addElement(nomeCliente);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        clientes.setModel(modelClientes);
        getContentPane().add(cliente);

//        ResultSet produtosData = db.getProdutos();
        JLabel peca_label = new JLabel("Produto");
        peca_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        peca_label.setBounds(10, 36, 64, 14);
        getContentPane().add(peca_label);

        JTextField produto = new JTextField("");
        produto.setBounds(107, 34, 219, 22);

//        try {
//            while (produtosData.next()) {
//                String nomeProduto = produtosData.getString("nome");
//                String idProduto = produtosData.getString("id");
//                JLabel productLabel = new JLabel("Product: " + nomeProduto + " (ID: " + idProduto + ")");
//
//                produto.add(productLabel);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //produto.setModel(modelProdutos);
        getContentPane().add(produto);

        JLabel quantidade_label = new JLabel("Quantidade");
        quantidade_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        quantidade_label.setBounds(10, 61, 87, 14);
        getContentPane().add(quantidade_label);

        JTextField quantidade = new JTextField();
        quantidade.setBounds(107, 60, 86, 20);
        getContentPane().add(quantidade);
        quantidade.setColumns(10);

        JLabel valor_label = new JLabel("Valor");
        valor_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        valor_label.setBounds(250, 61, 46, 14);
        getContentPane().add(valor_label);

        JTextField valor = new JTextField();
        valor.setBounds(306, 60, 118, 20);
        getContentPane().add(valor);
        valor.setColumns(10);

        JButton produto_adicionar = new JButton("ADICIONAR");
//        produto_adicionar.setIcon(new ImageIcon(ComprasNovo.class.getResource("/images/icons/plus.png")));
        produto_adicionar.setBounds(336, 34, 89, 23);
        produto_adicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cliente_id = cliente.getText();
                String produto_id = produto.getText();
                String quantity = quantidade.getText();
                String value = valor.getText();

                Object[] newRow = {0, cliente_id, produto_id, quantity, value };
                tableModel.addRow(newRow);
                setVisible(false);
            }
        });
        getContentPane().add(produto_adicionar);
    }
}
