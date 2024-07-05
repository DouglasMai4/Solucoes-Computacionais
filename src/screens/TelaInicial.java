package screens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class TelaInicial extends JFrame {
	public TelaInicial() {
		setBounds(100, 100, 580, 439);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton ordem_de_servico = new JButton("ORDEM DE SERVIÇO");
		ordem_de_servico.setFont(new Font("Agency FB", Font.BOLD | Font.ITALIC, 17));
		ordem_de_servico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdemDeServico screen = new OrdemDeServico();

				screen.setVisible(true);
			}
		});
		ordem_de_servico.setBounds(54, 161, 163, 73);
		getContentPane().add(ordem_de_servico);
		
		JButton compras = new JButton("COMPRAS");
		compras.setFont(new Font("Agency FB", Font.BOLD | Font.ITALIC, 17));
		compras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Compra screen = new Compra();

				screen.setVisible(true);
			}
		});
		compras.setBounds(352, 161, 163, 73);
		getContentPane().add(compras);
		
		JButton estoque = new JButton("ESTOQUE");
		estoque.setFont(new Font("Agency FB", Font.BOLD | Font.ITALIC, 17));
		estoque.setBounds(54, 316, 163, 73);
		getContentPane().add(estoque);
		estoque.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 Estoque screen = new Estoque();

				 screen.setVisible(true);
			 }
		 });
		
		JButton clientes = new JButton("CLIENTES");
		clientes.setFont(new Font("Agency FB", Font.BOLD | Font.ITALIC, 17));
		clientes.setBounds(352, 316, 163, 73);
		getContentPane().add(clientes);
		clientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente screen = new Cliente();

				screen.setVisible(true);
			}
		});

		JLabel lblNewLabel = new JLabel("Autoturbo");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
		lblNewLabel.setBounds(199, 39, 163, 38);
		getContentPane().add(lblNewLabel);
	}
}
