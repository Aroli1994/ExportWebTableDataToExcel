package DataHandling;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicDataExportToExcel {
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
		String value = null;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (i == 0) {
					WebElement element = driver.findElement(By.xpath("//table/thead/tr[1]/th[" + (j + 1) + "]"));
					value = element.getText();
					// System.out.println(value);
					ExportToExcel.ExcelWriter(i, j, value);
				} else if (j == 0) {
					WebElement element = driver
							.findElement(By.xpath("//table/tbody/tr[" + (i + 1) + "]/td[" + (j + 1) + "]/a"));
					value = element.getText();
					// System.out.println(value);
					ExportToExcel.ExcelWriter(i, j, value);
				} else if (j == 4) {
					WebElement element = driver
							.findElement(By.xpath("//table/tbody/tr[" + (i + 1) + "]/td[" + (j + 1) + "]/font"));
					value = element.getText();
					// System.out.println(value);
					ExportToExcel.ExcelWriter(i, j, value);
				} else {
					WebElement element = driver
							.findElement(By.xpath("//table/tbody/tr[" + (i + 1) + "]/td[" + (j + 1) + "]"));
					value = element.getText();
					// System.out.println(value);
					ExportToExcel.ExcelWriter(i, j, value);
				}
			}
		}
		if (ExportToExcel.status) {
			System.out.println("WebTable data exported to excel successfully");
		} else {
			System.out.println("WebTable data export operation has failed.Try again...");
		}
		driver.quit();
	}

}
