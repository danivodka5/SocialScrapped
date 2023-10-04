package Instagram;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UserGui extends JFrame{
	
	private JTextField inputField;
	private JButton vb;
	
	public static void main(String[] args) {
		new UserGui();
	}
	public UserGui() {
		// Titulo de ventana
		setTitle("Iniciar sesion en SocialScrapper");
		// Tamaño de ventana
		setSize(350, 100);
		// Cerrar al clickear X
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		// Tamaño de la barra
		inputField = new JTextField(30);
		vb = new JButton("Introducir");
		
		add(inputField);
		add(vb);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		// Metodo para mostrar ventana de texto
		vb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valor = inputField.getText();
				// Para cuando el usuario nos introduzca un usuario incorrecto
				JOptionPane.showMessageDialog(null, "Usuario introducido","Exito", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false); //you can't see me!
				dispose();
				System.out.println(valor);
				
				
			}
			
		});
	}
}
