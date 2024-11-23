package src.screens;

import src.screens.customer.Customer;
import src.screens.product.Product;
import src.screens.order.Order;

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

		JPanel Product = new Product();
		tabs.add("Product", Product);

		JPanel Order = new Order();
		tabs.add("Order", Order);

		add(tabs);
	}
}
