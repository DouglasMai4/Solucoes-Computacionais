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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class Cliente extends JFrame {
	private JTextField nome;
	private JTextField end;
	private JTextField tel;
	private JTextField cidade;
	private JTextField cpf;
	private int editandoLinhaId = -1;
	private int editandoLinhaIndex = -1;

	/**
	 * Initialize the contents of the frame.
	 */
	public Cliente() {
		setBounds(100, 100, 450, 404);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 262, 384, 92);
		getContentPane().add(scrollPane);

		ClienteCRUD database = new ClienteCRUD();

		// create table model
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Id");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Endereço");
		tableModel.addColumn("Cidade");
		tableModel.addColumn("Estado");
		tableModel.addColumn("CPF");
		tableModel.addColumn("Telefone");

		// create table
		JTable table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(137, 11, 153, 52);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 60, 48, 14);
		getContentPane().add(lblNewLabel_1);
		
		nome = new JTextField();
		nome.setBounds(57, 59, 86, 20);
		getContentPane().add(nome);
		nome.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Endereço:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 118, 71, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		end = new JTextField();
		end.setColumns(10);
		end.setBounds(79, 117, 86, 20);
		getContentPane().add(end);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Telefone:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 150, 71, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Cidade:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(10, 178, 71, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Estado:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(10, 203, 71, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		tel = new JTextField();
		tel.setColumns(10);
		tel.setBounds(79, 149, 86, 20);
		getContentPane().add(tel);

		cidade = new JTextField();
		cidade.setColumns(10);
		cidade.setBounds(68, 177, 86, 20);
		getContentPane().add(cidade);

		String[] options = new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
		JComboBox<String> estado = new JComboBox();
		estado.setModel(new DefaultComboBoxModel(options));
		estado.setBounds(77, 201, 66, 22);
		getContentPane().add(estado);

		// Botão Cadastrar
		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.setBounds(10, 228, 89, 23);
		getContentPane().add(cadastrar);
		cadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = nome.getText();
				String adress = end.getText();
				String phone = tel.getText();
				String city = cidade.getText();
				String state = (String) estado.getSelectedItem();
				String CPF = cpf.getText();

				int id = database.insert(name, adress, phone, city, state, CPF);

				Object[] newRow = { id, name, adress, city, state, phone, CPF };

				tableModel.addRow(newRow);
			}
		});

		// Botão Editar
		JButton editar = new JButton("Editar");
		editar.setBounds(109, 228, 89, 23);
		getContentPane().add(editar);
		editar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();

				int id = Integer.parseInt(table.getValueAt(rowIndex, 0).toString());
				String name = table.getValueAt(rowIndex, 1).toString();
				String adress = table.getValueAt(rowIndex, 2).toString();
				String city = table.getValueAt(rowIndex, 3).toString();
				String state = table.getValueAt(rowIndex, 4).toString();
				String CPF = table.getValueAt(rowIndex, 5).toString();
				String phone = table.getValueAt(rowIndex, 6).toString();
				int stateIndex = 0;
				for (int i = 0; i < options.length; i++) {
					if (options[i] == state) {
						stateIndex = i;
					}
				}

				editandoLinhaId = id;
				editandoLinhaIndex = rowIndex;
				nome.setText(name);
				end.setText(adress);
				cidade.setText(city);
				estado.setSelectedIndex(stateIndex);
				cpf.setText(CPF);
				tel.setText(phone);
			}
		});

		// Botão Excluir
		JButton excluir = new JButton("Excluir");
		excluir.setBounds(208, 228, 89, 23);
		getContentPane().add(excluir);
		excluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (editandoLinhaId == -1 || editandoLinhaIndex == -1) return;

				database.delete(editandoLinhaId);
				tableModel.removeRow(editandoLinhaIndex);
			}
		});

		// Botão Atualizar
		JButton atualizar = new JButton("Atualizar");
		atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editandoLinhaId == -1 || editandoLinhaIndex == -1) return;

				String name = nome.getText();
				String adress = end.getText();
				String phone = tel.getText();
				String city = cidade.getText();
				String state = (String) estado.getSelectedItem();
				String CPF = cpf.getText();

				System.out.println(editandoLinhaId);

				boolean updated = database.update(editandoLinhaId, name, adress, city, state, phone, CPF);

				if (updated) {
					table.setValueAt(editandoLinhaId, editandoLinhaIndex, 0);
					table.setValueAt(name, editandoLinhaIndex, 1);
					table.setValueAt(adress, editandoLinhaIndex, 2);
					table.setValueAt(city, editandoLinhaIndex, 3);
					table.setValueAt(state, editandoLinhaIndex, 4);
					table.setValueAt(CPF, editandoLinhaIndex, 5);
					table.setValueAt(phone, editandoLinhaIndex, 6);
				}
			}
		});
		atualizar.setBounds(309, 228, 89, 23);
		getContentPane().add(atualizar);

		List<ClienteCRUD.Model> data = database.getAll();
		int count = database.getCount();
		for (int i = 0; i < count; i++) {
			ClienteCRUD.Model cliente = data.get(i);

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

		JLabel lblNewLabel_1_2 = new JLabel("CPF:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(20, 85, 48, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		cpf = new JTextField();
		cpf.setBounds(57, 87, 86, 20);
		getContentPane().add(cpf);
		cpf.setColumns(10);
	}
}
