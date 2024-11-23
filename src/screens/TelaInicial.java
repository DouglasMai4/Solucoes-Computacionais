package src.screens;
import src.screens.customer.Customer;
import src.screens.purchase.Purchase;
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

		JPanel customers = new Customer();
		tabs.add("Clientes", customers);

		JPanel product = new Product();
		tabs.add("Product", product);

		JPanel order = new Order();
		tabs.add("Order", order);

		JPanel purchase = new Purchase();
		tabs.add("Purchase", purchase);

		add(tabs);
	}
}
