package testWindowGui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import WindowGui.windowGui;

public class testWindowGui {

	
	// NO ES POSIBLE REALIZAR EL TEST YA QUE JUNIT NO SOPORTA APOYO EN INTERFACES
	@Test
	public void testLoginButton() {
		ChromeDriver driver = new ChromeDriver();
		windowGui wg = new windowGui(driver);

		/*
		 * 1 Test
		 * El boton de login se activara unicamente cuando haiga mas de 1 caracter en user mas de 5 en pass
		 */
		
		boolean resultado = wg.loginBoolean();
		assertFalse(resultado);
		
		/*
		 * 2 Test
		 * El boton de login se activara unicamente cuando haiga mas de 1 caracter en user mas de 5 en pass
		 */

		
		wg.userTFsetText("1");
		wg.passTFsetText("123456");
		
		resultado = wg.loginBoolean();
		assertTrue(resultado);
	}
}
