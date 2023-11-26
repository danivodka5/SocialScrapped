package WindowGui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class chooseUser extends JFrame {
	
	private JTextField userInput = new JTextField(15);
	private JButton checkButton = new JButton("Analizar");
	private JButton dirButton = new JButton("Ruta");
	private JButton download = new JButton("Descargar");
	private ChromeDriver driver;
	
	private String user = "";
	private String path = "";
	
	public chooseUser(ChromeDriver driver) {
		this.driver = driver;
		windowConfig();
	}
	
	public void windowConfig () {
		download.setEnabled(false);
		
		setTitle("Username");
		setSize(500, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		checkButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				user = userInput.getText();
				ejecutarBusqueda(user);
				System.out.println("user length "+user.length());
				
				try {
					Thread.sleep(2000);
					buscarPerfil();
				} catch (InterruptedException e1) { }
				
				//if usuario existe
				if ( (path.length() > 1) && (user.length() > 1)) {
					System.out.println("permitimos boton");
					download.setEnabled(true);
				}
			}
		});
		
		dirButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				directoryWindow dw = new directoryWindow();
				path = dw.getWindow();
				
				//if usuario existe
				if ( (path.length() > 1) && (user.length() > 1)) {
					System.out.println("permitimos boton");
					download.setEnabled(true);
				}
			}
		});
			
		download.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				descargarPerfil();
			}
		});
		
		add(userInput);
		add(checkButton);
		add(dirButton);
		add(download);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	private void ejecutarBusqueda (String username) {
		driver.get("https://www.instagram.com/"+username);
	}
	private void buscarPerfil() {
		System.out.println("Iniciando metodo descargarPerfil()");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean condicion = true;
		while (condicion ) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			try {
				Thread.sleep(700);
				System.out.println("Estamos cargando el perfil...");
				driver.findElement(By.className("_aanh"));					// div cargando = "_aanh"
			} catch (Exception e) {
				condicion = false;
			}
		}
		List<WebElement> photos = driver.findElements(By.cssSelector("div[class='_aatp']"));	
		System.out.println("Lista tamaño = "+photos.size());
		/*
		for (int i=0; i<photos.size(); i++) {
			try {
				List<WebElement> nose = photos.get(i).findElements(By.xpath("./../*"));
				System.out.println("nose="+(i+1)+ " "+nose.size());
				
				System.out.println(nose.get(i).findElement(By.name("_aatp")));
				
			} catch (Exception e) {
				System.out.println("No se encontro la clase en la foto "+i);
			}			
		}
		*/
		System.out.println("Fin de cargar imagenes\n");
		
		
		// Si hay una secuencia(un post con varias imagenes) se entra, sino solo descargamos la imagen
		// Todas las fotos derivan de la clase "_aagu", las que tienen secuencia son ademas "_aatp";
		
		// Esquema, me voy a la foto, le pregunto si tiene secuencia, si la tiene 
	}
	private void descargarPerfil() {
		
	}
}
