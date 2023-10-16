package WindowGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// Alt Shift A
public class windowGui {

    // Atributos
	private JFrame mainFrame;
	
	private JPanel socialPanel;
	private JLabel socialLabel;
	
	private JTextField userTF = new JTextField();
	private JPasswordField passTF = new JPasswordField();
	
	private JButton login = new JButton();
	private boolean status = false;

	private StringBuffer a ;
	
	public void loadGui(final ChromeDriver driver) {
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
		userTF.setBorder(new EmptyBorder(0,0,0,0));


		passTF.setBounds(110, 150, 150, 25);
		passTF.setBorder(new EmptyBorder(0,0,0,0));
		
		
		login.setText("Iniciar Sesión");
		login.setBounds(110,200,150,25);
		
		mainFrame.add(socialLabel);
		mainFrame.add(userTF);
		mainFrame.add(passTF);
		mainFrame.add(login);
		
		// Metodo que se activa al presionar el boton de iniciarSesion
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Metodo que recibe un driver
				metodoDriver(driver);
			}			
		});
		

	}	
	public StringBuffer getA() {
		a.append(userTF.getText());
		return a;
	}
	public CharBuffer getB() {
		return CharBuffer.wrap(passTF.getPassword());
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public ChromeDriver metodoDriver(ChromeDriver driver) {
		WebElement campoa = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[1]/div/label/input"));
		WebElement campob = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[2]/div/label/input"));
		WebElement sendbutton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[3]/button"));
		char [] k  = passTF.getPassword();
		
		campoa.sendKeys(userTF.getText());
		campob.sendKeys(new String(passTF.getPassword()));
		sendbutton.click();
		Arrays.fill(k, '*');
		
		// Si la contraseña es muy corta no se envia nada
		// Solo se puede presionar el boton cuando se vaya a iniciar sesion, despues no se puede mas(se cierra)
		
		// Esperamos 3 segundos a cargar la pagina
		try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {  e.printStackTrace(); }
		
		// Este es el caso en que se falle el inicio de sesion
		try {
			WebElement login = driver.findElement(By.className("_ab2z"));
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[1]/div/label/input")).clear();
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[1]/div/label/input")).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[2]/div/label/input")).clear();
			
			System.out.println("primero encontrado");
		} catch (Exception e) { System.out.println("No se ha encontrado el fallo de sesion"); }
		
		return driver;
	}
}