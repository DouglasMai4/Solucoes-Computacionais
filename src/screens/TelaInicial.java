package screens;
import javax.swing.*;

public class TelaInicial extends JFrame {
	public TelaInicial() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel Storage = new Storage();

		JTabbedPane tabs = new JTabbedPane();
		tabs.setBounds(0, 0, 600, 600);
		tabs.add("Storage", Storage);

		add(tabs);
	}
}
