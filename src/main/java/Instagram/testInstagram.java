package Instagram;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import WindowGui.windowGui;

public class testInstagram {	
	private static ChromeDriver driver;
	private static String url;
	private static boolean cookies = true;
	
	public static void main(String[] args) {	
		testInstagram ti = new testInstagram();
	}
	
	public testInstagram() {
		iniciar();
		desactivarCookies();
		
		if (!cookies) {
			windowGui wg = new windowGui(driver);
		}
	}
	private static void iniciar() {
		driver = new ChromeDriver();
		url = "https://www.instagram.com/accounts/login/";	
		driver.get(url);

	}	
	public boolean desactivarCookies() {
		while (cookies) {
			try {
				Thread.sleep(1000);
				System.out.println("Cookies localizadas, rechazando..");
				driver.findElement(By.cssSelector("button[class='_a9-- _ap36 _a9_1']")).click();
				cookies = false;
				return cookies;
			} catch (Exception e) {
				System.out.println("No se pudo rechazar las cookies");
			}
		}
		return cookies;
	}
	public String getUrl() {
		return url;
	}
	
}
