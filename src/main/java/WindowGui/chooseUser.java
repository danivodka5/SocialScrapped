package WindowGui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

import ImageDownloader.ImageDownloader;

public class chooseUser extends JFrame {
	
	private JTextField userInput = new JTextField(15);
	private JButton checkButton = new JButton("Analizar");
	private JButton dirButton = new JButton("Ruta");
	private JButton download = new JButton("Descargar");
	private ChromeDriver driver;
	
	private String user = "";
	private String path = "";
	private ArrayList<String> srcPhotos;
	
	public chooseUser(ChromeDriver driver) {
		this.driver = driver;
		windowConfig();
	}
	
	private void windowConfig () {
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
				
				// Implementar if usuario existe
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
		srcPhotos = new ArrayList<>();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean condicion = true;
		while (condicion ) {
						
			List<WebElement> divphotos = driver.findElements(By.cssSelector("div[class='_aagv']"));	
			System.out.println("Cantidad "+divphotos.size());
			
			for (WebElement div : divphotos) {
				List<WebElement> images = div.findElements(By.tagName("img"));
				for (WebElement image : images ) {
					String srcset = image.getAttribute("src");
					// Lo añado a una lista, si en la lista no esta.
					if (!srcPhotos.contains(srcset)) {
						srcPhotos.add(srcset);
					}
				}
			}
			
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			try {
				Thread.sleep(700);
				System.out.println("Estamos cargando el perfil...");
				driver.findElement(By.className("_aanh"));					// div cargando = "_aanh"
			} catch (Exception e) {
				condicion = false;
			}
		}
		System.out.println("Fin de buscarPerfil\n");
		
		
		// Si hay una secuencia(un post con varias imagenes) se entra, sino solo descargamos la imagen
		// Todas las fotos derivan de la clase "_aagu", las que tienen secuencia son ademas "_aatp";
		// Esquema, me voy a la foto, le pregunto si tiene secuencia, si la tiene 
	}
	private void descargarPerfil() {
		System.out.println("Hay un total de "+srcPhotos.size()+" fotos\n");
		for (int i=0; i<srcPhotos.size(); i++) {
			ImageDownloader id = new ImageDownloader(srcPhotos.get(i),path,user,i);
		}	
		System.out.println("Fin DescargarPerfil");
	}
	
}
