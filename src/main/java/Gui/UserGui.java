package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import GuiElements.Boton;
import GuiElements.BotonPanel;
import GuiElements.UserGuiSearchBar;
import GuiElements.UserPanel;
import WindowGui.chooseUser;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;

public class UserGui {

	private JFrame frame;
	private ImageIcon icon1;
	private UserGuiSearchBar ugsb;
	private Boton btnbuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGui window = new UserGui();
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
	public UserGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 384, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JLabel labelZoom = new JLabel("");
		labelZoom.setBounds(21, 340, 35, 34);
		
		icon1 = new ImageIcon(InstagramLoginGui.class.getResource("/Images/ZoomInsta2.png"));
		
		ImageIcon temp1 = icon1;
		temp1.setImage(temp1.getImage().getScaledInstance(labelZoom.getWidth(), labelZoom.getHeight(), Image.SCALE_SMOOTH));
		labelZoom.setIcon(temp1);
		frame.getContentPane().add(labelZoom);
	
		BotonPanel bp = new BotonPanel();
		bp.setBounds(19, 339, 40, 37);
		
		bp.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	labelZoom.setBounds(0, 0, 300, 300);
		    	
		    	ImageIcon temp2 = icon1;
		    	temp2.setImage(temp2.getImage().getScaledInstance(labelZoom.getWidth(), labelZoom.getHeight(), Image.SCALE_SMOOTH));    	
		    	labelZoom.setIcon(icon1);

		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	labelZoom.setBounds(20, 341, 35, 34);
		    }
		});
		frame.getContentPane().add(bp);

		UserPanel userpanel = new UserPanel();
		userpanel.setBounds(0, 331, 370, 53);
		userpanel.setLayout(null);
		frame.getContentPane().add(userpanel);
		
		ugsb = new UserGuiSearchBar();
		ugsb.setBounds(21, 38, 240, 34);
		frame.getContentPane().add(ugsb);
		
		btnbuscar = new Boton("Buscar", new Color(112, 196, 240), new Color(0, 149, 246));
		btnbuscar.setText("<html><font color = white>Buscar</font></html>");
		btnbuscar.setFont(new Font("Arial", Font.BOLD, 12));
		btnbuscar.setBounds(276, 38, 84, 34);
		frame.getContentPane().add(btnbuscar);		
		
		JButton btnNewButton = new JButton("Descargar Perfil");
		btnNewButton.setBounds(51, 116, 259, 34);
		frame.getContentPane().add(btnNewButton);
		
		ugsb.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkEnableButton();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkEnableButton();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {	
				checkEnableButton();
			}
            private void checkEnableButton() {
                if (ugsb.getText().length() > 0) {
                	System.out.println("me activo");
                	btnbuscar.setBooleanEmpty(false);
                	btnbuscar.setEnabled(true);
                	btnbuscar.setCursor(true);
                } else {
                	btnbuscar.setBooleanEmpty(true);
                	btnbuscar.setEnabled(false);
                	btnbuscar.setCursor(false);                	
                }
            }
		});
	
		

	}
}
