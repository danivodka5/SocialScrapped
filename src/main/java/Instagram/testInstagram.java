package Instagram;
//hola soy david
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
		// Objeto ventana
		windowGui wg = new windowGui();
		wg.loadGui(driver);
	}
}
