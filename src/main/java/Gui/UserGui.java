package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.openqa.selenium.chrome.ChromeDriver;

import GuiElements.Boton;
import GuiElements.BotonPanel;
import GuiElements.UserGuiSearchBar;
import GuiElements.UserPanel;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class UserGui {
	// Atributos
	private JFrame frame;
	private ImageIcon icon1;
	
	private ChromeDriver driver;
	private String user;
	private JTextField textField;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGui window = new UserGui();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	// Constructor
	public UserGui(ChromeDriver driver) {
		this.driver = driver;
		initialize();
		
	}
	
	// Constructor testing
	public UserGui() {
		initialize();
	}
	
	// Metodo Inicial
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 768, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 754, 652);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		
		UserGuiSearchBar ugsb = new UserGuiSearchBar();
		ugsb.setBounds(27, 21, 264, 45);
		panel.add(ugsb);
		ugsb.setColumns(10);
		
		// Boton Buscar Perfil
		Boton bsearch = new Boton("Buscar",new Color(112,196,240),new Color(0,149,246));
		bsearch.setFont(new Font("Arial", Font.BOLD, 18));
		bsearch.setBounds(317, 21, 101, 45);
		bsearch.setEnabled(false);
		bsearch.setText("<html><font color = white>Buscar</font></html>");
		panel.add(bsearch);	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 254, 754, 398);
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		// Boton Descargar Perfil
		Boton bdp = new Boton("Descargar", new Color(112, 196, 240), new Color(0, 149, 246));
		bdp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bdp.setText("<html><font color = white>Descargar</font></html>");
		bdp.setFont(new Font("Arial", Font.BOLD, 18));
		bdp.setEnabled(false);
		panel_1.add(bdp);
		
		Boton bsearch_2 = new Boton("Buscar", new Color(112, 196, 240), new Color(0, 149, 246));
		bsearch_2.setText("<html><font color = white>Buscar</font></html>");
		bsearch_2.setFont(new Font("Arial", Font.BOLD, 18));
		bsearch_2.setEnabled(false);
		panel_1.add(bsearch_2);
			

		
	}
	private void ejecutarBusqueda(String username) {
		driver.get("https://www.instagram.com/"+username);
	}
}
