package testInstagram;

import static org.junit.Assert.*;
import org.junit.Test;
import Instagram.testInstagram;

public class testIG {
	
	@Test
	public void testDesactivarCookies() {
		testInstagram t1 = new testInstagram();
		boolean resultado = t1.desactivarCookies();
		assertFalse(resultado);
	}
	
	@Test
	public void testIniciar() {
		testInstagram t1 = new testInstagram();
		
		String url = "https://www.instagram.com/accounts/login/";
		String resultado = t1.getUrl();
		assertEquals(resultado, url);
	}
}
