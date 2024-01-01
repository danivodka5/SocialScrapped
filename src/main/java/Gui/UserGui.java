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

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import GuiElements.Boton;
import GuiElements.BotonPanel;
import GuiElements.UserGuiSearchBar;
import GuiElements.UserPanel;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.DropMode;

public class UserGui {
	// Atributos
	private JFrame frame;
	private ImageIcon icon1;
	
	private UserGuiSearchBar ugsb;
	private Boton btnbuscar;
	
	private ChromeDriver driver;
	private String user;
	
	private UserPanel userpanel;
	
	private boolean searching;
	private JLabel labelNotFound;

	
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
	
	// Solo si el perfil existe mostrar las opciones, de lo contrario no.
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setResizable(false);
		//frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 470, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ERROR CAUSADO POR ->>>>>>>>
		frame.getContentPane().setLayout(null);
		
		icon1 = new ImageIcon(InstagramLoginGui.class.getResource("/Images/ZoomInsta2.png"));
		
		ImageIcon temp1 = icon1;

		userpanel = new UserPanel();
		userpanel.setBounds(0, 0, 456, 79);
		frame.getContentPane().add(userpanel);
		userpanel.setLayout(null);
		
		ugsb = new UserGuiSearchBar();
		ugsb.setBounds(28, 23, 274, 45);
		userpanel.add(ugsb);
		ugsb.setHorizontalAlignment(SwingConstants.CENTER);
		ugsb.setColumns(15);
		
		// Label Not found
		labelNotFound = new JLabel("");
		labelNotFound.setBounds(64, 149, 294, 38);
		labelNotFound.setVisible(false);
		frame.getContentPane().add(labelNotFound);
		
		// Boton buscar
		btnbuscar = new Boton("Buscar", new Color(112, 196, 240), new Color(0, 149, 246));
		btnbuscar.setBounds(322, 26, 95, 40);
		userpanel.add(btnbuscar);
		btnbuscar.setVisible(true);
		btnbuscar.setText("<html><font color = white>Buscar</font></html>");
		btnbuscar.setFont(new Font("Arial", Font.BOLD, 16));
		btnbuscar.setEnabled(false);
		
		// Metodo boton buscar
		btnbuscar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("me pulsaron");
				user = ugsb.getText();
				ejecutarBusqueda(user);
				
				
			}
		});
		
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
		
			BotonPanel bp = new BotonPanel();
			bp.setBounds(26, 534, 83, 72);
			frame.getContentPane().add(bp);
			
			final JLabel labelZoom = new JLabel("");
			labelZoom.setBounds(10, 135, 55, 52);
			frame.getContentPane().add(labelZoom);
			temp1.setImage(temp1.getImage().getScaledInstance(labelZoom.getWidth(), labelZoom.getHeight(), Image.SCALE_SMOOTH));
			labelZoom.setIcon(temp1);
			
			
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
	}
	private void ejecutarBusqueda(String username) {
		driver.get("https://www.instagram.com/"+username);
		System.out.println("Inicio del metodo ejecutarBusqueda() usuario: "+username);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Metodo para comprobar cuando la pagina ha cargado completamente
		boolean loading = true;
		while (loading) {
			try {
				Thread.sleep(100);
				String status = (String) js.executeScript("return document.getElementById('splash-screen').style.display;");
				if (status.equals("none")) {
					loading = false;
				}
			} catch (InterruptedException e) { e.printStackTrace(); }
		}

		System.out.println("Usuario Cargado ");
		
		// El usuario existe?	
		boolean userExists = (boolean) js.executeScript("const userl = document.getElementsByClassName('x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xbxaen2 x1u72gb5 x1t1ogtf x13zrc24 x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh xl56j7k')[0]; if (typeof userl !== 'undefined') { return(false); } else { return (true); }");
		
		if (userExists) {
			System.out.println("El usuario existe");
			
		} else {
			System.out.println("El usuario no existe");
			String adv = (String) js.executeScript("return document.getElementsByClassName('x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xbxaen2 x1u72gb5 x1t1ogtf x13zrc24 x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh xl56j7k')[0].textContent;");
			System.out.println("adv ="+adv);
			labelNotFound.setText(adv);
			labelNotFound.setVisible(true);
		}
				
		Thread userFound = new Thread(new Runnable() {
			 @Override
			 public void run() {
						try {
							JavascriptExecutor js = (JavascriptExecutor) driver;
							// Title
							System.out.println("Titulo= "+js.executeScript("return document.getElementsByClassName('x1lliihq x1plvlek xryxfnj x1n2onr6 x193iq5w xeuugli x1fj9vlw x13faqbe x1vvkbs x1s928wv xhkezso x1gmr53x x1cpjm7i x1fgarty x1943h6x x1i0vuye xvs91rp x1s688f x5n08af x10wh9bi x1wdrske x8viiok x18hxmgj')[0].textContent;"));
							
							// Biography
							String scriptBio = "const bio = document.getElementsByClassName('_ap3a _aaco _aacu _aacx _aad6 _aade')[0];";
							String script2 = "if (typeof bio !== 'undefined') {return (bio.textContent);} else {return ''};";
							String res = (String) js.executeScript(scriptBio+script2);
							System.out.println("Bio = "+res);
							
							// URL
							//js = (JavascriptExecutor) driver;
							String scriptURL = "const url =  document.getElementsByClassName('x3nfvp2')[6].getElementsByTagName('a')[0]; if (typeof url !== 'undefined'){return (url.textContent);} else {return ''}";
							System.out.println("URL="+js.executeScript(scriptURL));
							
						}catch (Exception e) {

						}		 
			 }
		});	
		userFound.start();
	}
}
