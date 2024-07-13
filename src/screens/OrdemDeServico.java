package screens;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrdemDeServico extends JFrame {
	private JTextField cpf_cnpj;
	private JTable table;
	private JTextField nota_fiscal;
	private JTextField id_venda;

	public OrdemDeServico () {
		setTitle("Ordem de serviço");

		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("CPF/CNPJ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(28, 11, 55, 14);

		getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(163, 76, 94, 29);
		getContentPane().add(btnNewButton);

		cpf_cnpj = new JTextField();
		cpf_cnpj.setBounds(90, 9, 102, 20);
		getContentPane().add(cpf_cnpj);
		cpf_cnpj.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 126, 374, 107);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("NF's °");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(264, 36, 32, 14);
		getContentPane().add(lblNewLabel_1);

		nota_fiscal = new JTextField();
		nota_fiscal.setBounds(306, 34, 86, 20);
		getContentPane().add(nota_fiscal);
		nota_fiscal.setColumns(10);

		id_venda = new JTextField();
		id_venda.setBounds(306, 9, 86, 20);
		getContentPane().add(id_venda);
		id_venda.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Pedido de Venda");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(202, 12, 94, 14);
		getContentPane().add(lblNewLabel_2);
	}

}
