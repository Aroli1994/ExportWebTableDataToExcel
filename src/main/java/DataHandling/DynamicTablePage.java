package DataHandling;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicTablePage {
	static WebDriver driver;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("http://demo.guru99.com/test/web-table-element.php");

		driver.manage().deleteAllCookies();

		driver.manage().window().maximize();

		List<WebElement> RowsTags = driver.findElements(By.xpath("//table/tbody/tr"));
		int row = RowsTags.size();
		System.out.println("Number of rows:" + row);

		List<WebElement> ColumnTags = driver.findElements(By.xpath("//table/thead/tr/th"));
		int column = ColumnTags.size();
		System.out.println("Number of columns:" + column);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		for (int i = 0; i < row; i++) {
			WebElement element = driver.findElement(By.xpath("//table/tbody/tr[" + (i + 1) + "]/td[1]/a"));
			String value = element.getText();
			if (value.equalsIgnoreCase("jet airways")) {
				System.out.println(value);
				element.click();
				System.out.println("Found '"+value+"' in row:"+row);
				break;
			}
		}
	}

}
