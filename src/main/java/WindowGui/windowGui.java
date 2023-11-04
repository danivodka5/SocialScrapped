package WindowGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.CharBuffer;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

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
		login.setEnabled(false);
		
		passTF.getDocument().addDocumentListener(new DocumentListener() {
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
                // Obtenemos el texto del JTextField y comprobamos si es un número mayor a 5
                try {
                    login.setEnabled((passTF.getText().length() > 5) && (userTF.getText().length() > 0));
                } catch (NumberFormatException e) {
                    // Si no es un número válido, deshabilitamos el botón
                   	login.setEnabled(false);
                }
            }
		});
		
		mainFrame.add(socialLabel);
		mainFrame.add(userTF);
		mainFrame.add(passTF);
		mainFrame.add(login);
		
		// Metodo que se activa al presionar el boton de iniciarSesion
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				metodoDriver(driver);			// Le indicamos al driver que pulse iniciar sesion
				userTF.setText("");				// Limpiamos el campo de usuario
				passTF.setText("");				// Limpiamos el campo de pass
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
		// El boton se activa cuando la longitud de la contraseña es mayor a 5
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("-- 1 --");
		// String name = (String) js.executeScript("const collection = document.getElementsByClassName(\"_aa4b _add6 _ac4d\"); return collection[0].value;");
	
		
		/*
		System.out.println("Intentado almacenar js");
		driver.executeScript("const collection = document.getElementsByClassName(\"_aa4b _add6 _ac4d\")");
		System.out.println("avanzamos de const");
		driver.executeScript("console.log(collection[0])");
		*/
		System.out.println("exito");
		
		// Obtener valores en instagram -> System.out.println(campoa.getAttribute("value"));
		WebElement campoa = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[1]/div/label/input"));
		WebElement campob = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[2]/div/label/input"));
		WebElement sendbutton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[3]/button"));
		
		// Enviar la informacion
		campoa.sendKeys(userTF.getText());
		campob.sendKeys(new String(passTF.getPassword()));
		sendbutton.click();
		
		// Limpiar campos inicio sesion
		campoa.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		campob.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		
		// La contraseña necesita como minimo 6 caracteres.
		// Solo se puede presionar el boton cuando se vaya a iniciar sesion, despues no se puede mas(se cierra)
		
		boolean sesion = true;
		
		while (sesion) {
			System.out.println(js.executeScript("return document.readyState"));
			String state = (String) js.executeScript("return document.readyState");
			if (state.equals("complete")) {
				sesion = false;
			}
		}
		
		// Output1 : Inicio de sesion correcto
		// Output2 : Ayudanos a confirmar que eres tu 
		// Output3 : Contraseña incorrecta
		// saborap asdasdasd
		
		/*
		// Esperamos 3 segundos a cargar la pagina
		try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {  e.printStackTrace(); }
		// Buscamos el div que aparece cuando el inicio de sesion ha fallado
		try {
			WebElement login = driver.findElement(By.className("_ab2z"));			
			System.out.println("_ab2z encontrado");
		} catch (Exception e) { System.out.println("No se ha encontraod ningun error"); }
		js.executeScript("const collection = document.getElementsByClassName(\"_aa4b _add6 _ac4d\"); collection[0].value = '"+ userTF.getText() +"'; collection[1].value = '"+new String(passTF.getPassword())+"'");
		*/
		
/*
 * const collection = document.getElementsByClassName("_aa4b _add6 _ac4d");
	console.log(collection[0]);
	console.log(collection['password']);
	console.log(collection['username']);;

	var text = document.getElementsByClassName("_aa4b _add6 _ac4d");
	var usser = collection[0];
	console.log(usser);
	usser.value = 'saborajackydaniels';
 * 
 */
		//js.executeScript("const collection = document.getElementsByClassName(\"_aa4b _add6 _ac4d\"); collection[0].value = '"+ userTF.getText() +"'; collection[1].value = '"+new String(passTF.getPassword())+"'");
		// const de = fv[0].firstElementChild;	
		// document.getElementsByName("username")[0].value = "lel"; 
		
		// Usted use xpath and javascript
		return driver;
	}
	private void botonIniciarSesionReleased(java.awt.event.KeyEvent evt ) {

	}
}