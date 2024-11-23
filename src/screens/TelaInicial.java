package src.screens;

import javax.swing.*;

public class TelaInicial extends JFrame {
	public TelaInicial() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setName("Controle de Estoque");

		JTabbedPane tabs = new JTabbedPane();
		tabs.setBounds(0, 0, 800, 600);

		JPanel Customers = new Customer();
		tabs.add("Clientes", Customers);

		JPanel Storage = new Storage();
		tabs.add("Storage", Storage);

		add(tabs);
	}
}
