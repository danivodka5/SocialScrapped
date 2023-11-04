package Instagram;

import java.nio.CharBuffer;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import WindowGui.windowGui;

public class testInstagram {	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ChromeDriver driver = new ChromeDriver();
		String url = "https://www.instagram.com/accounts/login/";	
		driver.get(url);

		boolean cookies = true;
		while (cookies){
			try {
				Thread.sleep(1000);
				System.out.println("Cookies localizadas, rechazando..");
				driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div/div/div[2]/div/button[2]")).click();
				cookies = false;
			} catch (Exception e) {
				System.out.println("Cookies no localizadas");
			}
		}
		
		// Rechazar cookies
		driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div/div/div[2]/div/button[2]")).click();
		
		// Objeto ventana
		windowGui wg = new windowGui();
		wg.loadGui(driver);
		
		// 1 minuto
	
		// ChronoUnit.MINUTES.getDuration()
		// System.out.println(duration.toMinutes());	
		//WebDriverWait wait = new WebDriverWait(driver, duration); 
		
	
		
		// Cuando haya cargado la pagina abro una ventana pidicendo credenciales
		
		// Si la credencial es correcta cierro la ventana.
		
		// Como detecto el inicio de sesion correcto exactamente?
	
	/*
	try {
		TimeUnit.SECONDS.sleep(2);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	*/
}
}
