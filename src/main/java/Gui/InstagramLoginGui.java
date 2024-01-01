package Gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import GuiElements.Boton;
import GuiElements.InstagramPasswordField;
import GuiElements.InstagramTextField;
import WindowGui.chooseUser;

public class InstagramLoginGui {

	private JFrame frame;
	private InstagramTextField t1;
	private JPasswordField p1;
	private Boton btnlogin;
	private JLabel labelgif;
	private JLabel labeladv;
	
	private ChromeDriver driver;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Bug, si el ordenador esta con la fecha incorrecta no se abre
					ChromeDriver driver = new ChromeDriver();
					String url = "https://www.instagram.com/accounts/login/";	
					driver.get(url);

					boolean cookies = true;
					while (cookies){
						try {
							Thread.sleep(1000);
							System.out.println("Cookies localizadas, rechazando..");
							driver.findElement(By.cssSelector("button[class='_a9-- _ap36 _a9_1']")).click();
							cookies = false;
						} catch (Exception e) {
							System.out.println("No se pudo rechazar las cookies");
						}
					}	
					InstagramLoginGui window = new InstagramLoginGui(driver);
					
					
					
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
		frame.setBounds(100, 100, 548, 535);
		
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
		frame.getContentPane().add(labelgif);
		
		frame.getContentPane().add(btnlogin);
		frame.getContentPane().add(p1);
		frame.getContentPane().add(t1);
		
		labeladv = new JLabel("");
		labeladv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labeladv.setBounds(109, 385, 311, 110);
		labeladv.setForeground(Color.red);
		//labeladv.setText(String.format("<html><body style=\"text-align: justify;  text-justify: inter-word;\">%s</body></html>", "Tu contraseña no es correcta. Compruébala."));
		frame.getContentPane().add(labeladv);
		
		
		btnlogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labeladv.setText("");
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
						while (condicion) {
							try {
								Thread.sleep(900);
								driver.findElement(By.xpath("//div[@role='progressbar']"));
								System.out.println("-- Cargando ---");
							} catch(Exception e) {
								System.out.println("Pagina cargada al 100%");
								labelgif.setVisible(false);
								condicion = false;
								
								// Inicio de sesion correcto
								if (driver.getCurrentUrl().contains("next=%2F")) {
									labeladv.setForeground(Color.green);
									labeladv.setText("Login Correcto");
									
									UserGui ug = new UserGui(driver);
																	
									frame.setVisible(false); 
									frame.dispose(); 	     // Destroy the JFrame object
								} else {
									WebElement inc = driver.findElement(By.className("_ab2z"));
									labeladv.setText(inc.getText());
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
}
