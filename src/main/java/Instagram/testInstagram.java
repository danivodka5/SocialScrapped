package Instagram;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testInstagram {	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ChromeDriver driver = new ChromeDriver();
		String url = "https://www.instagram.com/";
		driver.get(url);
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div/div/div[2]/div/button[2]")).click();
		System.out.print("User: ");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/article/div[2]/div[1]/div[2]/form/div/div[1]/div/label/input")).sendKeys(sc.next());
		System.out.println("Pass: ");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/article/div[2]/div[1]/div[2]/form/div/div[2]/div/label/input")).sendKeys(sc.next());
		
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/article/div[2]/div[1]/div[2]/form/div/div[3]/button")).click();
			
		
	}
}
