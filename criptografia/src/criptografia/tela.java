package criptografia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class tela {

	private JFrame frame;
	private JPasswordField textsenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tela window = new tela();
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
	public tela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 353);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("   Login");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 17));
		lblNewLabel.setBounds(199, 38, 89, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea textusuario = new JTextArea();
		textusuario.setBounds(90, 112, 332, 34);
		frame.getContentPane().add(textusuario);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 116, 60, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(20, 197, 60, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				String usuario = textusuario.getText();
				String senha = textsenha.getText();
				BancoDeDados banc = new BancoDeDados();
				banc.conectar();
				if(banc.estaConectado()) {
					if(banc.verificarusuario(usuario) && banc.verificarsenha(usuario,senha))
						JOptionPane.showMessageDialog(null, "Acesso liberado", "Aviso",1);
				}else {
					JOptionPane.showMessageDialog(null, "Acesso negado","Aviso",0);
				}
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnNewButton.setBounds(199, 249, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		textsenha = new JPasswordField();
		textsenha.setBounds(90, 195, 332, 34);
		frame.getContentPane().add(textsenha);
	}
}
