package Main;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1DownloadImage {

	public static void main(String[] args) {
		
		// Cuando entro con el internet del movil no me sale el captcha
		// Para no tenger una ventana emergente
		// ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless");
		
		ChromeDriver driver = new ChromeDriver();
		String url = "https://www.infojobs.net/";
		driver.get(url);

		// Dejamos cargar la pagina 2 Segundos
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Buscamos todas las imagenes
			
		List<WebElement> imageElement = driver.findElements(By.tagName("img"));
		for (int i=0; i< imageElement.size(); i++) {
			String imageUrl = imageElement.get(i).getAttribute("src");
			System.out.println(imageUrl);
		}
		

		
		for (int i=0; i< imageElement.size(); i++) {
			try {
				System.out.println("1");
				URL uimg = new URL(imageElement.get(i).getAttribute("src"));
				System.out.println("2");
				InputStream is = uimg.openStream();
				System.out.println("3");
				FileOutputStream os = new FileOutputStream("D:\\Nueva_carpeta\\atest\\imagen"+i+".png");
				System.out.println("4");
				byte[] b = new byte[2048];
				System.out.println("5");
				int length;

				while ((length = is.read(b)) != -1) {
				    os.write(b, 0, length);
				}
				is.close();
				os.close();
			}
			catch(Exception e) {
				System.out.println("La descarga de la imagen " +i+ " ha fallado");
			}
		}
			
		
		/*
		// Metodo 1
		
		try {
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			FileOutputStream os = new FileOutputStream("C:\\Users\\vodka\\Desktop\\gato.jpeg");
			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
			    os.write(b, 0, length);
			}
			is.close();
			os.close();
			driver.quit();
			
		} catch (MalformedURLException e) {
			System.out.println("Ha fallao");
		}
		*/
		
		// Buscamos todas las imagenes
		/*
		List<WebElement> imageElement = driver.findElements(By.tagName("img"));
		for (int i=0; i< imageElement.size(); i++) {
			String imageUrl = imageElement.get(i).getAttribute("src");
			System.out.println(imageUrl);
		}
		*/

	}

}
