package WindowGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	// Atributo ChromeDriver
	private ChromeDriver driver;
	private JButton login = new JButton();
	
	public windowGui(ChromeDriver driver) {
		this.driver = driver;
		loadGui();
	}
	
	private void loadGui() {
		mainFrame = new JFrame ("Instagram Login");
		
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
		
		// La contraseña necesita como minimo 6 caracteres.
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
				metodoDriver();			// Le indicamos al driver que pulse iniciar sesion
				checkLogin();
				userTF.setText("");				// Limpiamos el campo de usuario
				passTF.setText("");				// Limpiamos el campo de pass
			}			
		});	
	}	

	private void metodoDriver() {
	
		// String name = (String) js.executeScript("const collection = document.getElementsByClassName(\"_aa4b _add6 _ac4d\"); return collection[0].value;");
		// Obtener valores en instagram -> System.out.println(campoa.getAttribute("value"));
		WebElement campoa = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[1]/div/label/input"));
		WebElement campob = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[2]/div/label/input"));
		WebElement sendbutton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[1]/section/main/div/div/div[1]/div[2]/form/div/div[3]/button/div"));
			
		// Limpiar campos inicio sesion
		campoa.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		campob.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		
		// Enviar la informacion
		campoa.sendKeys(userTF.getText());
		campob.sendKeys(new String(passTF.getPassword()));
		campoa.sendKeys(Keys.ENTER);
		
		passTF.setText("");
		
		/*
		sendbutton.click();
		
		// Solo se puede presionar el boton cuando se vaya a iniciar sesion, despues no se puede mas(se cierra)
	
		*/
	
		
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
		System.out.println("Intentado almacenar js");
		driver.executeScript("const collection = document.getElementsByClassName(\"_aa4b _add6 _ac4d\")");
		System.out.println("avanzamos de const");
		driver.executeScript("console.log(collection[0])");
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
	}
	private boolean checkLogin() {
		// Output1 : Inicio de sesion correcto
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println(js.executeScript("return document.readyState"));
		boolean condicion = true;
		while(condicion) {
			try { Thread.sleep(1000); } catch (InterruptedException e) { }
			String url = driver.getCurrentUrl();
			
			// Inicio de sesion
			if (url.contains("?next=%2F")){
				System.out.println("Iniciando sesion ...");
				chooseUser cu = new chooseUser(driver);		
				condicion = false;
				
				mainFrame.setVisible(false); // you can't see me!
				mainFrame.dispose(); 	     // Destroy the JFrame object
				return true;
			}
			// Recuperar Cuenta
			if (url.contains("https://www.instagram.com/challenge")) {
				System.out.println("Error instagram challenge");
				driver.get("https://www.instagram.com/");
				condicion = false;
			} 
			// Contraseña incorrecta : saborap asdasdasd
			else {
				try {
					WebElement inc = driver.findElement(By.className("_ab2z"));
					System.out.println("inc ="+inc.getText()); // Ponerle esto al usuario
					condicion = false;
				}catch(Exception e) { }
			}
		}
		return false;
	}
	
	public boolean loginBoolean() {
		return login.isEnabled();
	}
	public void userTFsetText(String text) {
		userTF.setText(text);
	}
	public void passTFsetText(String text) {
		passTF.setText(text);
	}
}


/*
 * 
 * 		boolean condicion = true;
	while(condicion) {
		try { Thread.sleep(1000); } catch (InterruptedException e) { }
		String url = driver.getCurrentUrl();
		// Inicio de sesion
		if (url.contains("?next=%2F")){
			System.out.println("Iniciando sesion ...");
			chooseUser cu = new chooseUser(driver);		
			condicion = false;
			
			mainFrame.setVisible(false); //you can't see me!
			mainFrame.dispose(); 	     //Destroy the JFrame object
		}
		// Recuperar Cuenta
		if (url.contains("https://www.instagram.com/challenge")) {
			driver.get("https://www.instagram.com/");
			condicion = false;
			System.out.println("Error instagram challenge");
		} 
		// Contraseña incorrecta : saborap asdasdasd
		else {
			try {
				WebElement inc = driver.findElement(By.className("_ab2z"));
				System.out.println("inc ="+inc.getText()); // Ponerle esto al usuario
				condicion = false;
			}catch(Exception e) { }
		}
	}
 * 
 */
