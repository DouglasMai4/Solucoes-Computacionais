package screens;

import database.ClienteCRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Compras extends JFrame {
	private JTextField id_cliente;

	public Compras() {
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		// Create the table model
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Id");
		tableModel.addColumn("Id Cliente");
		tableModel.addColumn("Id Produto");
		tableModel.addColumn("Quantidade");
		tableModel.addColumn("Finalizado");
		tableModel.addColumn("Valor");

		// Set the title of the JFrame
		setTitle("Compras");

//		JLabel lblNewLabel = new JLabel("Número da compra:");
//		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblNewLabel.setBounds(10, 11, 137, 27);
//		getContentPane().add(lblNewLabel);

//		id_cliente = new JTextField();
//		id_cliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		id_cliente.setBounds(157, 11, 518, 27);
//		getContentPane().add(id_cliente);
//		id_cliente.setColumns(10);

//		JButton search_btn = new JButton("");
////		search_btn.setIcon(new ImageIcon(Compras.class.getResource("/images/icons/magnifying-glass.png")));
//		search_btn.setFont(new Font("Tahoma", Font.PLAIN, 5));
//		search_btn.setBounds(685, 12, 89, 27);
//		getContentPane().add(search_btn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 100, 600, 400);
		getContentPane().add(scrollPane);

		JTable table = new JTable();
		scrollPane.setViewportView(table);

		JButton edit_btn = new JButton("Editar");
		edit_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		edit_btn.setEnabled(false);
		edit_btn.setBounds(10, 50, 260, 27);
		getContentPane().add(edit_btn);

		JButton new_btn = new JButton("Novo");
		new_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] rowData = new Object[]{-1, -1, -1, 0, 0, 0 };

				ComprasForm screen = new ComprasForm(tableModel, rowData);

				screen.setVisible(true);
			}
		});
		new_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		new_btn.setBounds(517, 50, 260, 27);
		getContentPane().add(new_btn);

		JButton btnNewButton = new JButton("Deletar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
//		btnNewButton.setEnabled(false);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(261, 50, 260, 27);
		getContentPane().add(btnNewButton);
	}
}
