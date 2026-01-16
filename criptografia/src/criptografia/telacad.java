package criptografia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telacad {

	private JFrame frame;
	private JTextField textusuario;
	private JTextField textsenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telacad window = new telacad();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public telacad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 462, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 17));
		lblNewLabel.setBounds(191, 28, 95, 33);
		frame.getContentPane().add(lblNewLabel);
		
		textusuario = new JTextField();
		textusuario.setBounds(82, 89, 332, 43);
		frame.getContentPane().add(textusuario);
		textusuario.setColumns(10);
		
		textsenha = new JTextField();
		textsenha.setColumns(10);
		textsenha.setBounds(82, 143, 332, 43);
		frame.getContentPane().add(textsenha);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 103, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 157, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textusuario.getText();
				String senha = textsenha.getText();
				String hashgerado = BCrypt.hashpw(senha, BCrypt.gensalt());
				BancoDeDados banc = new BancoDeDados();
				banc.conectar();
				if(banc.estaConectado()) {
					banc.inserirdado(usuario, hashgerado);
					JOptionPane.showMessageDialog(null,"Cadastro feito","Aviso",1);
				}else {
					JOptionPane.showMessageDialog(null,"Erro","Aviso",0);
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewButton.setBounds(184, 211, 102, 35);
		frame.getContentPane().add(btnNewButton);
	}
}
