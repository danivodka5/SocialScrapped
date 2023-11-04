package Tutorial;

import org.openqa.selenium.chrome.ChromeDriver;

public class Tutorial1 {

	public static void main(String[] args) {
		
		/* 
		 * Creo un objeto ChromeDriver, driver que accedera al inicio de sesion de instagram
		 */
		ChromeDriver driver = new ChromeDriver();
		String url = "https://www.instagram.com/accounts/login/";	
		
		
		// Al usar el metodo .get(url) le indico que quiero ir a la url especificada.
		driver.get(url);

		// Quiero que busque un texto
		
		// Quiero que de click a un boton
		
	}
}
