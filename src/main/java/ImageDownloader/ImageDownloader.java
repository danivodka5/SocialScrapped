package ImageDownloader;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImageDownloader {

	// Atributos
		private ChromeDriver driver;
		
		// Constructor
		
		public ImageDownloader(ChromeDriver driver) {
			this.driver = driver;
		}
		// Getters and setters
		public ChromeDriver getDriver() {
			return driver;
		}
		public void setDriver(ChromeDriver driver) {
			this.driver = driver;
		}
		
		public List<WebElement> searchAllImages(){
			List<WebElement> imageElement = driver.findElements(By.tagName("img"));
			for (int i=0; i< imageElement.size(); i++) {
				String imageUrl = imageElement.get(i).getAttribute("src");
				System.out.println(imageUrl);
			}
			return imageElement;
		}
	
}
