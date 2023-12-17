package WindowGui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
	
	private String posts;
	private String seguidores;
	private String siguiendo;
	
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
				
				// Este metodo es para saber cuando esta cargada la pagina web
				// Solucionado en UserGui
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
				// Colocar un Threads si es necesario
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
		
		boolean c = true;
		while (c) {
			try {
				Thread.sleep(1000);
				
				// Header
				// console.log("bio ="+document.getElementsByClassName('_ap3a _aaco _aacu _aacx _aad6 _aade')[0].firstChild.nodeValue);
				// document.getElementsByClassName('_ap3a _aaco _aacu _aacx _aad6 _aade')[0].firstChild.nodeValue;

				// Header
				WebElement header = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div[2]/section/main/div/header/section/div[3]/div[1]/span"));
				System.out.println("header ="+header.getText());
				
				// H1
				WebElement h1 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div[2]/section/main/div/header/section/div[3]/h1"));
				System.out.println("h1="+h1.getText());
				
				// Link
				WebElement link = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div[2]/section/main/div/header/section/div[3]/div[3]/a/span/span"));
				System.out.println("link="+link.getText());
				c = false;
				
			} catch(Exception e) {
				System.out.println("--- ERROR -----");
			}
		}

		// Testing, borrar osea mover		
		
	}
	private void buscarPerfil() {
		System.out.println("Iniciando metodo buscarPerfil()");
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
		
		// Buscamos la informacion del perfil temporalmente
		analizarPerfil(user);
	}
	private void descargarPerfil() {
		System.out.println("Hay un total de "+srcPhotos.size()+" fotos\nDescargando fotos...");
		for (int i=0; i<srcPhotos.size(); i++) {
			ImageDownloader id = new ImageDownloader(srcPhotos.get(i),path,user,i);
		}	
		System.out.println("Todas las fotos han sido descargadas..\nDescargando Informacion..");
		
		File f = new File(path+"\\logs.txt");
	
        try (FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

        	bw.write("LocalDateTime="+LocalDateTime.now());
        	bw.newLine();
        	
        	bw.write("Username="+user);
            bw.newLine(); // Salto de línea tras el texto agregado
            
        	bw.write("Followers="+seguidores);
        	bw.newLine();
        	
        	bw.write("Following="+siguiendo);
        	bw.newLine();
            
            bw.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin DescargarPerfil()");
	}
	private void analizarPerfil(String username) {
		System.out.println("Inicio de analizarPerfil");
		List<WebElement> spanE = driver.findElements(By.className("_ac2a"));
		System.out.println("Tamaño spanE"+spanE.size());
		
		posts = spanE.get(0).getText();
		seguidores = spanE.get(1).getText(); 
		siguiendo = spanE.get(2).getText();
		System.out.println("Fin de analizarPerfil()");
		
		
		/*
		String bion[] = new String[3];
		// Recorremos la lista de elementos span
		for (int i=0; i<spanE.size(); i++) {
			System.out.println(i+" "+spanE.get(i).getText());
			bion[i] = spanE.get(i).getText();
		}
		*/
	}
}
