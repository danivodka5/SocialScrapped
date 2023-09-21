package Main;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.firefox.FirefoxDriver;

public class Adblock {

	public static void main(String[] args) {
		FirefoxDriver driver = new FirefoxDriver();
		Path path = Paths.get("C:\\Users\\vodka\\Desktop\\Infojobs\\src\\main\\resources\\ublock\\ublock_origin-1.51.0.xpi");
		driver.installExtension(path);
        driver.get("https://www.infojobs.net/");

	}

}
