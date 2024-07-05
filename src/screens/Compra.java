package screens;

import database.ClienteCRUD;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class Compra extends JFrame {
	private int rowIndex = -1;

	/**
	 * Initialize the contents of the frame.
	 */
	public Compra() {
		setBounds(100, 100, 500, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Compras");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 350, 384, 300);
		getContentPane().add(scrollPane);

		// create table model
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Id Cliente");
		tableModel.addColumn("Id Produto");
		tableModel.addColumn("Quantidade");
		tableModel.addColumn("Valor");

		// create table
		JTable table = new JTable(tableModel);
//		Font font = new Font("Arial", Font.PLAIN, 32);

		scrollPane.setViewportView(table);
		// Set the font size for the header
		JTableHeader header = table.getTableHeader();
//		header.setFont(font); // Adjust the size as needed

		// Set the font size for cells
//		table.setFont(font); // Adjust the size as needed

		table.setRowHeight(44);
		JLabel clienteIdLabel = new JLabel("Cliente id");
		clienteIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		clienteIdLabel.setBounds(50, 50, 100, 32);
		getContentPane().add(clienteIdLabel);

		JTextField cliente_id = new JTextField();
		cliente_id.setBounds(300, 50, 100, 32);
		getContentPane().add(cliente_id);
		cliente_id.setColumns(10);

		JLabel produtoIdLabel = new JLabel("Produto id");
		produtoIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		produtoIdLabel.setBounds(50, 100, 150, 32);
		getContentPane().add(produtoIdLabel);

		JTextField produto_id = new JTextField();
		produto_id.setBounds(300, 100, 100, 32);
		getContentPane().add(produto_id);
		produto_id.setColumns(10);

		JLabel valorLabel = new JLabel("Quantidade: ");
		valorLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		valorLabel.setBounds(50, 150, 150, 32);
		getContentPane().add(valorLabel);
		
		JTextField quantidade = new JTextField();
		quantidade.setColumns(10);
		quantidade.setBounds(300, 150, 100, 32);
		getContentPane().add(quantidade);

		JLabel finalizadoLabel = new JLabel("Valor: ");
		finalizadoLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		finalizadoLabel.setBounds(50, 200, 100, 32);
		getContentPane().add(finalizadoLabel);

		JTextField valor = new JTextField();
		valor.setColumns(10);
		valor.setBounds(300, 200, 100, 32);
		getContentPane().add(valor);

		// Botão Cadastrar
		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.setBounds(10, 300, 89, 23);
		getContentPane().add(cadastrar);
		cadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cId = cliente_id.getText();
				String pId = produto_id.getText();
				String quantity = quantidade.getText();
				String price = valor.getText();

				Object[] newRow = { cId, pId, quantity, price };

				tableModel.addRow(newRow);
			}
		});

		// Botão Editar
		JButton editar = new JButton("Editar");
		editar.setBounds(109, 300, 89, 23);
		getContentPane().add(editar);
		editar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowIndex = table.getSelectedRow();
//
				String cId = table.getValueAt(rowIndex, 0).toString();
				String pId = table.getValueAt(rowIndex, 1).toString();
				String quantity = table.getValueAt(rowIndex, 2).toString();
				String price = table.getValueAt(rowIndex, 3).toString();

				cliente_id.setText(cId);
				produto_id.setText(pId);
				quantidade.setText(quantity);
				valor.setText(price);
			}
		});

		// Botão Excluir
		JButton excluir = new JButton("Excluir");
		excluir.setBounds(208, 300, 89, 23);
		getContentPane().add(excluir);
		excluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowIndex = table.getSelectedRow();

				if (rowIndex == -1) return;
//
				tableModel.removeRow(rowIndex);
			}
		});

		// Botão Atualizar
		JButton atualizar = new JButton("Atualizar");
		atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rowIndex == -1) return;

				String cId = cliente_id.getText();
				String pId = produto_id.getText();
				String quantity = quantidade.getText();
				String price = valor.getText();

				tableModel.setValueAt(cId, rowIndex, 0);
				tableModel.setValueAt(pId, rowIndex, 1);
				tableModel.setValueAt(quantity, rowIndex, 2);
				tableModel.setValueAt(price, rowIndex, 3);
			}
		});
		atualizar.setBounds(309, 300, 89, 23);
		getContentPane().add(atualizar);
	}
}
