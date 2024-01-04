package Daniel;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

public class TrasteandoJavascript {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		String script = "let f = ['elemento1', 'elemento2', 'elemento3'] ; return(f);";
		List<String> array = (List<String>) js.executeScript(script);
		
		System.out.println("Script "+array.size());
		
		for (int i=0; i<array.size(); i++) {
			System.out.println(array.get(i));
		}
	}
}
