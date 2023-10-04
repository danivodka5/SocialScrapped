package WindowGui;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class test {

	public static void main(String[] args) {
		JFrame mainFrame = new JFrame ("Inicio de Sesion");
		mainFrame.setSize(400, 300);
        mainFrame.setVisible(true);	
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		
		JTextField userTF = new JTextField();
		userTF.setBounds(110,100,150, 25);

		mainFrame.add(userTF);
	}
}
