package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.NodeList;

import GuiElements.Boton;
import GuiElements.BotonPanel;
import GuiElements.UserGuiSearchBar;
import GuiElements.UserPanel;
import MySQL.Conexion;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

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
	private Boton btnanuser;
	
	private ChromeDriver driver;
	private String user;
	private boolean userExists;
	
	private UserPanel userpanel;
	private JLabel labelNotFound;
	private Conexion conn;


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
	public UserGui(ChromeDriver driver, Conexion conn) {
		this.conn = conn;
		this.driver = driver;
		initialize();
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
		labelNotFound.setBounds(64, 149, 328, 246);
		labelNotFound.setVisible(false);
		frame.getContentPane().add(labelNotFound);
		
		
		// Boton AnalizarUsuario
		btnanuser = new Boton("Analizar Usuario", new Color(112, 196, 240), new Color(0, 149, 246));
		btnanuser.setVisible(true);
		btnanuser.setText("<html><font color = white>Analizar Usuario</font></html>");
		btnanuser.setFont(new Font("Arial",Font.BOLD, 16));
		btnanuser.setEnabled(false);
		btnanuser.setBounds(30,120,267,50);
		frame.getContentPane().add(btnanuser);
		
		btnanuser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Iniciando el metodo AnalizarPerfil()");
				analizarPerfil();
				
			}			
		});
		
		
		// Boton buscar
		btnbuscar = new Boton("Buscar", new Color(112, 196, 240), new Color(0, 149, 246));
		btnbuscar.setBounds(322, 26, 95, 40);
		btnbuscar.setVisible(true);
		btnbuscar.setText("<html><font color = white>Buscar</font></html>");
		btnbuscar.setFont(new Font("Arial", Font.BOLD, 16));
		btnbuscar.setEnabled(false);
		userpanel.add(btnbuscar);
		
		// Metodo boton buscar
		btnbuscar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("me pulsaron");
				user = ugsb.getText();
				
				
				//ejecutarBusqueda();
				findInstagramStories();
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
			labelZoom.setBounds(43, 547, 55, 52);
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
	private void ejecutarBusqueda() {
		driver.get("https://www.instagram.com/"+user);
		System.out.println("Inicio del metodo ejecutarBusqueda() usuario: "+user);
		
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

		System.out.println("Pagina de usuario Cargada ");
		
		// El usuario existe?	
		userExists = (boolean) js.executeScript("const userl = document.getElementsByClassName('x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xbxaen2 x1u72gb5 x1t1ogtf x13zrc24 x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh xl56j7k')[0]; if (typeof userl !== 'undefined') { return(false); } else { return (true); }");
		
		if (userExists) {
			System.out.println("El usuario existe");
			btnanuser.setEnabled(true);
			btnanuser.setBooleanEmpty(false);
			btnanuser.setCursor(true);
			
			// Comprobamos si el usuario esta en la base de datos y de paso lo creamos
			System.out.println(conn.doesUserExists(user));
			
			// CREAR USUARIO, ESCOGER USUARIO
			if ((!(conn == null)) &&(!conn.doesUserExists(user))) {
				System.out.println(conn.createUser(user));
			}
			
					
		} else {
			System.out.println("El usuario no existe");
			
        	btnanuser.setBooleanEmpty(true);
        	btnanuser.setEnabled(false);
        	btnanuser.setCursor(false);  
        	
			String adv = (String) js.executeScript("return document.getElementsByClassName('x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xbxaen2 x1u72gb5 x1t1ogtf x13zrc24 x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh xl56j7k')[0].textContent;");
			System.out.println("adv ="+adv);
			labelNotFound.setText(adv);
			labelNotFound.setVisible(true);
		}
	
	
		Thread userFound = new Thread(new Runnable() {
			 @Override
			 public void run() {
				 
			 }
		});	
		//userFound.start();

	}
	
	private void analizarPerfil() {
		System.out.println("Iniciamos metodo analizarFollowers");
		
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
			System.out.println("error en "+e.getMessage());
		}		
		
		
	}
	
	private void analizarFollowers() {
		System.out.println("Iniciando metodo analizar Followers() ");
		driver.get("https://www.instagram.com/"+user+"/followers/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// Buscamos si se ha abierto el div
		boolean loading = true;
		while (loading) {
			loading = loadingFollows(js);
			
		}
		
		// Nosotros siempre seremos el primer seguidor
		System.out.println("\n\nSegunda etapa del metodo--");
		
		int t = 0;
		loading = true;
		while (loading) {
			try {		/*
				String sc = "const scrollBar = document.getElementsByClassName('_aano')[0];  let childrenbar = scrollBar.firstChild.firstChild.childNodes; return(childrenbar.length)";		
				int seguidores = (int) js.executeScript(sc);
				System.out.println("Seguidores = "+seguidores);
				*/
				Thread.sleep(150);
				js.executeScript("const scrollBar = document.getElementsByClassName('_aano')[0];  scrollBar.scrollTop = scrollBar.scrollHeight; ");	
				driver.findElement(By.xpath("//div[@class='x78zum5 xdt5ytf xl56j7k']"));		
				System.out.println("Cargando barra");
				//int ref = (int) js.executeScript(sc);
				/*
				if (seguidores != ref) {
					System.out.println("Seguidores = "+ref);
				}
				*/
				
				t = 0;
			}catch (Exception e) {
				System.out.println("No se ha encontrado el final");
				t++;
				if (t > 6) {
					// Intentar almacenar los followers como un array.
					loading = false;
					
					int segn =  ((Long) js.executeScript("let container = document.getElementsByClassName('_aano')[0]; let childrenbar = container.firstChild.firstChild.childNodes; let f = []; for (let i=0; i<childrenbar.length; i++) { f[i] = childrenbar[i].firstChild.firstChild.firstChild.childNodes[1].firstChild.firstChild.firstChild.firstChild.textContent; } return(f.length);")).intValue();
					
					System.out.println("segn "+segn);
					String sega[] = new String[segn];

					String segscript = "let container = document.getElementsByClassName('_aano')[0]; let childrenbar = container.firstChild.firstChild.childNodes; let f = []; for (let i=0; i<childrenbar.length; i++) { f[i] = childrenbar[i].firstChild.firstChild.firstChild.childNodes[1].firstChild.firstChild.firstChild.firstChild.textContent; }";	

					for(int i=0; i<segn; i++) {
						sega[i] = (String) js.executeScript(segscript+"return(f["+i+"]"+");");
					}	
					System.out.println("fin de coleccion");
					for(int i=0; i<segn; i++) {
						System.out.println("usuario "+sega[i]);
					}
					
				}
			}
		}
	}

	private boolean loadingFollows(JavascriptExecutor js) {
		try {
			Thread.sleep(900);
			driver.findElement(By.xpath("//div[contains(@class, 'x1n2onr6 xzkaem6')]"));
			String sname = "return (document.getElementsByClassName('_aano')[0].firstChild.firstChild.firstChild.firstChild.childNodes[0].firstChild.childNodes[1].firstChild.firstChild.firstChild.textContent);";
			String snamejs = (String) js.executeScript(sname);
			
			// Una vez aparezca el div
			if (snamejs.length() > 1) {
				System.out.println("El Menu ha cargado (hemos encontrado el div)"+ snamejs);
				return false;
			}
		} catch (Exception e) {
			System.out.println("Menu Followers cargando...");
			return true;
		}
		return true;
	}
	
	private void findInstagramStories() {
		driver.get("https://www.instagram.com/stories/"+user);
		System.out.println("Inicio del metodo testHistoriaInstagram usuario: "+user);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Metodo para comprobar cuando la pagina ha cargado completamente
		boolean loading = true;
		while (loading) {
			try {
				Thread.sleep(100);
				String status = (String) js.executeScript("return document.getElementById('splash-screen').style.display;");
				if (status.equals("none")) {
					
					WebElement verh = driver.findElement(By.xpath("//div[contains(@class, 'x9f619 xjbqb8w x78zum5 x168nmei x13lgxp2 x5pf9jr xo71vjh xg87l8a x1n2onr6 x1plvlek xryxfnj x1c4vz4f x2lah0s xdt5ytf xqjyukv x1qjc9v5 x1oa3qoh x1nhvcw1')]"));
					verh.click();
					System.out.println("Fin");
					loading = false;
				}
			} catch (InterruptedException e) { System.out.println("Cargando.."); }
		}

		int nimg = ((Long) js.executeScript("let images = document.getElementsByTagName('img'); return images.length")).intValue();
		System.out.println("Imagenes = "+nimg);
		
		loading = true;
		
		ArrayList<String> storysrc = new ArrayList<String>()
				;
		int cstories = 0;
		while (loading) {
			try {
				// Y si es un video?
				
				// Primero obtenemos la foto
				System.out.println("Foto encontrada");
				storysrc.add((String)js.executeScript("let imgstories = document.getElementsByTagName('img'); "
						+ "return(imgstories[3].src)"));
				
				
				driver.findElements(By.cssSelector("svg[class='x1lliihq x1n2onr6 xq3z1fi']")).get(3).click();
				//List<WebElement> divsvg = driver.findElements( By.cssSelector("svg[class='x1lliihq x1n2onr6 xq3z1fi']"));
				//System.out.println(divsvg.size());
				//divsvg.get(3).click();
				cstories++;
				Thread.sleep(2000);
			}catch (Exception e) {
				loading = false;
			}
		}
		
		System.out.println("Hay un total de "+cstories+" instagram stories ");
		
		System.out.println("Leyendo las direcciones =");
		for (int i=0; i<storysrc.size(); i++) {
			System.out.println("imagen "+i+" "+storysrc.get(i));
		}
		
	}
}
