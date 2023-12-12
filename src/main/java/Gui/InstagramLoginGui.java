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
					window.frame.setVisible(true);
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
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 366, 333);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Boton Login
		btnlogin = new Boton("Entrar",new Color(112,196,240),new Color(0,149,246));
		btnlogin.setFont(new Font("Arial", Font.BOLD, 12));
		btnlogin.setBounds(39, 202, 269, 32);
    	btnlogin.setEnabled(false);
    	btnlogin.setText("<html><font color = white>Entrar</font></html>");
    	
		t1 = new InstagramTextField();
		t1.setBounds(39,106,270,37);
		
		p1 = new InstagramPasswordField();
		p1.setBounds(39, 151, 270, 37);
		
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
		labelgif.setBounds(156, 202, 37, 31);
		ImageIcon loading = new ImageIcon(InstagramLoginGui.class.getResource("/Images/loadingblue2.gif"));
		loading.setImage(loading.getImage().getScaledInstance(labelgif.getWidth(), labelgif.getHeight()+10, Image.SCALE_DEFAULT));
		labelgif.setIcon(loading);
		
		// Invisible
		labelgif.setVisible(false);
		frame.getContentPane().add(labelgif);
		
		frame.getContentPane().add(btnlogin);
		frame.getContentPane().add(p1);
		frame.getContentPane().add(t1);
		
		labeladv = new JLabel("");
		labeladv.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labeladv.setBounds(39, 244, 269, 31);
		labeladv.setForeground(Color.red);
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
								t1.setText("");				// Limpiamos el campo de usuario
								p1.setText("");				// Limpiamos el campo de pass
								labelgif.setVisible(false);
								condicion = false;
								btnlogin.setBooleanBlocked(false);
								
								// Inicio de sesion correcto
								if (driver.getCurrentUrl().contains("next=%2F")) {
									labeladv.setForeground(Color.green);
									labeladv.setText("Login Correcto");
									chooseUser user = new chooseUser(driver);
									frame.setVisible(false); // you can't see me!
									frame.dispose(); 	     // Destroy the JFrame object
								} else {
									WebElement inc = driver.findElement(By.className("_ab2z"));
									labeladv.setText(inc.getText());
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
		/*
		t1.setText("");				// Limpiamos el campo de usuario
		p1.setText("");				// Limpiamos el campo de pass
		*/
		return driver;
	}
}
