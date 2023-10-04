package WindowGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.openqa.selenium.Dimension;

//        Alt Shift A
public class windowGui {
	
    public static void main(String[] args) {
        windowGui wg = new windowGui();
        
        wg.loadGui();
    }
	
    // Atributos
	private JFrame mainFrame;
	
	private JPanel socialPanel;
	private JLabel socialLabel;
	
	private JTextField userTF = new JTextField();
	private JPasswordField passTF;
	
	private void loadGui() {
		
		mainFrame = new JFrame ("Inicio de Sesion");
		
		//No redimensionable
		mainFrame.setResizable(false);
		
		// Window Size
		mainFrame.setSize(400, 300);
        mainFrame.setVisible(true);	
        
		// Frame Location Center
		mainFrame.setLocationRelativeTo(null);

		// Change Background
		mainFrame.getContentPane().setBackground(new java.awt.Color(57, 93, 145));;
		// Close Window Default
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setLayout(null);
		
		socialPanel = new JPanel();
		socialPanel.setLayout(new BorderLayout());
		
		// SwinConstants = Centrar
		socialLabel = new JLabel("Instagram");
		socialLabel.setForeground(Color.white);
		// socialLabel.setBackground(Color.black);
		socialLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// socialLabel.setOpaque(true);
		
		socialPanel.add(socialLabel, BorderLayout.CENTER);
		
		// (x,y, ancho, altura);
		socialLabel.setFont(new Font("Cocogoose Pro",1,20));
		socialLabel.setBounds(120, 40, 130, 25);	
		
		
		userTF.setBounds(110,100,150, 25);
		
		mainFrame.add(socialLabel);
		mainFrame.add(userTF);
	
	}
}
