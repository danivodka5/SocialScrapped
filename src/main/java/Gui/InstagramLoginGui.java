package Gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import GuiElements.Boton;
import GuiElements.InstagramPasswordField;
import GuiElements.InstagramTextField;
import MySQL.Conexion;

public class InstagramLoginGui {

	private JFrame frame;
	private InstagramTextField t1;
	private JPasswordField p1;
	private Boton btnlogin;
	private JLabel labelgif;
	
	private InstagramTextField ip;
	private InstagramTextField port;
	private InstagramTextField user;
	private InstagramTextField p;
	
	private Boton btnmysql;
	private JLabel labelip;
	private JLabel labelport;
	
	private ChromeDriver driver;
	private JLabel labelip_2;
	private JLabel labelsql;
	private JLabel labeladv;
	private Conexion conn;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Bug, si el ordenador esta con la fecha incorrecta no se abre
					ChromeDriver driver = new ChromeDriver();
					String url = "https://www.instagram.com/accounts/login/";	
					driver.get(url);

					boolean cookies = true;
					int trys = 0;
					while (cookies){
						try {
							Thread.sleep(1000);
							System.out.println("Cookies localizadas, rechazando..");
							driver.findElement(By.cssSelector("button[class='_a9-- _ap36 _a9_1']")).click();
							cookies = false;
							InstagramLoginGui window = new InstagramLoginGui(driver);
						} catch (Exception e) {
							trys++;	
							System.out.println("No se pudo rechazar las cookies "+trys);
						}
						if (trys > 5) {
							cookies = false;
							System.out.println("\nNo se pudo arrancar el programa");
						}
					}								
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InstagramLoginGui(ChromeDriver driver) {
		initialize(driver);
	}
	private void initialize(final ChromeDriver driver) {	
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		// width,height
		frame.setBounds(100, 100, 550, 920);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		
		// Buscar todos los chromedriver y eliminarlos del administrador de tareas
		// https://stackoverflow.com/questions/21320837/release-selenium-chromedriver-exe-from-memory
		// https://stackoverflow.com/questions/41315488/do-something-when-the-close-button-is-clicked-on-a-jframe-need-clarification
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.out.println("Cerrar programa");
            }
        });
		
		// Boton Login
		btnlogin = new Boton("Entrar",new Color(112,196,240),new Color(0,149,246));
		btnlogin.setFont(new Font("Arial", Font.BOLD, 20));
		btnlogin.setBounds(39, 300, 435, 50);
    	btnlogin.setEnabled(false);
    	btnlogin.setText("<html><font color = white>Entrar</font></html>");
    	
		t1 = new InstagramTextField();
		t1.setBounds(39,159,435,58);
		
		p1 = new InstagramPasswordField();
		p1.setBounds(39, 226, 435, 57);
		
		p1.getDocument().addDocumentListener(new DocumentListener() {
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
                if ((p1.getText().length() > 5) && (t1.getText().length() > 0)) {
                	btnlogin.setEnabled(true);
                	btnlogin.setBooleanEmpty(false);
                	btnlogin.setCursor(true);
                } else {
                	btnlogin.setEnabled(false);
                	btnlogin.setBooleanEmpty(true);
                	btnlogin.setCursor(false);
                }
            }
		});
		
		t1.getDocument().addDocumentListener(new DocumentListener() {
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
                if ((p1.getText().length() > 5) && (t1.getText().length() > 0)) {
                	btnlogin.setBooleanEmpty(false);
                	btnlogin.setEnabled(true);
                } else {
                	btnlogin.setBooleanEmpty(true);
                	btnlogin.setEnabled(false);
                }
            }
		});
		
		
		
		labelgif = new JLabel();
		labelgif.setBounds(227, 302, 60, 47);
		ImageIcon loading = new ImageIcon(InstagramLoginGui.class.getResource("/Images/loadingblue3.gif"));
		loading.setImage(loading.getImage().getScaledInstance(labelgif.getWidth(), labelgif.getHeight()+10, Image.SCALE_DEFAULT));
		labelgif.setIcon(loading);
		
		// Invisible
		labelgif.setVisible(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(labelgif);
		
		frame.getContentPane().add(btnlogin);
		frame.getContentPane().add(p1);
		frame.getContentPane().add(t1);
		
		ip = new InstagramTextField();
		ip.setBounds(39, 570, 190, 58);
		frame.getContentPane().add(ip);
		
		port = new InstagramTextField();
		port.setBounds(361, 570, 114, 58);
		frame.getContentPane().add(port);
		
		user = new InstagramTextField();
		user.setBounds(39, 465, 190, 58);
		frame.getContentPane().add(user);
		
		p = new InstagramTextField();
		p.setBounds(284, 465, 190, 58);
		frame.getContentPane().add(p);
		
		DocumentListener dlsql = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkTextMysql();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkTextMysql();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkTextMysql();
			}
		};
		
		ip.getDocument().addDocumentListener(dlsql);
		port.getDocument().addDocumentListener(dlsql);
		user.getDocument().addDocumentListener(dlsql);
		port.getDocument().addDocumentListener(dlsql);
		
		btnmysql = new Boton("Conectar MYSQL", new Color(112, 196, 240), new Color(0, 149, 246));
		btnmysql.setText("<html><font color = white>Conectar MYSQL</font></html>");
		btnmysql.setFont(new Font("Arial", Font.BOLD, 20));
		btnmysql.setEnabled(false);
		btnmysql.setBounds(39, 650, 435, 50);
		frame.getContentPane().add(btnmysql);
		
		labelip = new JLabel("IP");
		labelip.setBounds(39, 534, 60, 25);
		labelip.setFont(new Font("Arial", Font.PLAIN, 19));
		frame.getContentPane().add(labelip);
		
		labelport = new JLabel("PORT");
		labelport .setFont(new Font("Arial", Font.PLAIN, 19));
		labelport .setBounds(393, 534, 60, 25);
		frame.getContentPane().add(labelport);
	
		
		JLabel labelip_1 = new JLabel("USER");
		labelip_1.setFont(new Font("Arial", Font.PLAIN, 19));
		labelip_1.setBounds(39, 429, 60, 25);
		frame.getContentPane().add(labelip_1);
		
		labelip_2 = new JLabel("PASSWORD");
		labelip_2.setFont(new Font("Arial", Font.PLAIN, 19));
		labelip_2.setBounds(284, 429, 147, 25);
		frame.getContentPane().add(labelip_2);
		
		labelsql = new JLabel("");		
		labelsql.setBounds(39, 750, 435, 82);
		labelsql.setFont(new Font("Arial", Font.PLAIN, 19));
		frame.getContentPane().add(labelsql);
		
		labeladv = new JLabel("New label");
		labeladv.setBounds(39, 371, 435, 47);
		frame.getContentPane().add(labeladv);
	
		btnmysql.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				conn = new Conexion(ip.getText(),port.getText(),user.getText(),p.getText());
				conn.connect();
				
				if (conn.isConnected()) {
					labelsql.setText("<html>CONNECTED TO MYSQL<p>"+conn.createDatabaseIfNotExists()+"</p></html>");
				} else {
					labelsql.setText("ERROR CONNECTION");
				}
				
				user.setText("");
				p.setText("");
				ip.setText("");
				port.setText("");
			}
			
		});
			
		btnlogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//labeladv.setText("");
				btnlogin.setBooleanBlocked(true);
				System.out.println("Boton bloqueado");
				
				Thread loginDriver = new Thread(new Runnable() {
					@Override
					public void run() {
						metodoDriver(driver);
					}
					
				});
				Thread loginGif = new Thread(new Runnable() {
					@Override
					public void run() {
						boolean condicion = true;
						labelgif.setVisible(true);
						labeladv.setVisible(false);
						while (condicion) {
							try {
								Thread.sleep(900);
								driver.findElement(By.xpath("//div[@role='progressbar']"));
								System.out.println("-- Cargando ---");
							} catch(Exception e) {
								System.out.println("Pagina cargada al 100%");
								labelgif.setVisible(false);
								condicion = false;
								labeladv.setVisible(true);
								// Inicio de sesion correcto
								if (driver.getCurrentUrl().contains("next=%2F")) {
									//labeladv.setForeground(Color.green);
									//labeladv.setText("Login Correcto");
									
									if (conn != null) {
										UserGui ug = new UserGui(driver,conn);									
									} else {
										UserGui ug = new UserGui(driver);		
									}
																								
									frame.setVisible(false); 
									frame.dispose(); 	     // Destroy the JFrame object
								} 
								if (driver.getCurrentUrl().contains("challenge")) {
									labeladv.setForeground(Color.red);
									labeladv.setText("Captcha required :/");
								}
								
								else {
									WebElement inc = driver.findElement(By.className("_ab2z"));
									//labeladv.setText(inc.getText());
									// btnlogin.setBooleanBlocked(false);
									
								}
							}
						}
					}
				});
				loginDriver.start();
				loginGif.start();		
			}			
		});	
	}
public ChromeDriver metodoDriver(ChromeDriver driver) {
		WebElement campoa = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[1]/div/label/input"));
		WebElement campob = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[2]/div/label/input"));
			
		// Limpiar campos inicio sesion
		campoa.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		campob.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		
		// Enviar la informacion
		campoa.sendKeys(t1.getText());
		campob.sendKeys(new String(p1.getPassword()));
		campoa.sendKeys(Keys.ENTER);
		
		t1.setText("");				// Limpiamos el campo de usuario
		p1.setText("");				// Limpiamos el campo de pass
		
		return driver;
	}
	private void checkTextMysql() {
		if ((ip.getText().length() >6) && (port.getText().length() > 0) && (user.getText().length() > 0)) {
        	btnmysql.setEnabled(true);
        	btnmysql.setBooleanEmpty(false);
        	btnmysql.setCursor(true);
		} else {
			btnmysql.setEnabled(false);
			btnmysql.setBooleanEmpty(true);
			btnmysql.setCursor(false);
		}
	}
}
