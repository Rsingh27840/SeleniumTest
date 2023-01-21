package MavenPack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.beust.jcommander.internal.Lists;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test {

	// TODO Auto-generated method stub

	ChromeDriver driver;
	String path = "/Users/rahulsingh/Downloads/RahulResume.pdf";
	String msg1 = "I believe I have the type of knowledge to succeed in this role";
	String msg2 = "Need to learn new tools and Technology";
	String lurl = "www.linkedin.com/in/rahul-singh-461316118";
	String title="JOIN OUR CREW";
	String qatitle="Numadic Iot Pvt. Ltd. | Full time\n"
			+ "QA Engineer";

	@BeforeTest
	public void beforetest() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://jobs.numadic.com/jobs/Careers");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	

	@Test
	public void Testcase() throws IOException {
		

		WebElement el = driver.findElement(By.xpath("//h2[text()='JOIN OUR CREW']"));
		System.out.println(el.getText());
		Assert.assertEquals(title,el.getText());
		

		driver.findElement(By.className("dropdown")).click();
		
		List<WebElement> elm = driver.findElements(By.xpath("//lyte-drop-item[starts-with(@id,'Lyte_Drop_Item')]"));
		for (WebElement webElement : elm) {

			if (webElement.getText().equalsIgnoreCase("Engineering")) {
				webElement.click();
				break;
			}
		}
		driver.findElement(By.linkText("QA Engineer")).click();
		WebElement t=driver.findElement(By.className("cw-jobheader-info"));
		Assert.assertEquals(true, t.getText().contains(qatitle));
		
		List<WebElement> em = driver.findElements(By.xpath("//lyte-yield[@yield-name='text']"));
		for (WebElement web : em) {

			if (web.getText().equalsIgnoreCase("I'm interested")) {
				web.click();
				break;
			}
		}

		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(path);
		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='crc-resume-progress disN']")));

		driver.findElement(By.xpath("//crux-text-component[@cx-prop-zcqa='manual_Current_CTC']")).sendKeys("12");
		driver.findElement(By.xpath("//crux-text-component[@cx-prop-zcqa='manual_Expected_CTC']")).sendKeys("25");
		driver.findElement(By.xpath("//crux-text-component[@cx-prop-zcqa='manual_Reason_for_change']"))
				.sendKeys(msg2);
		driver.findElement(By.xpath("//crux-text-component[@cx-prop-zcqa='manual_Why_Join_Numadic']"))
				.sendKeys(msg1);
		driver.findElement(By.xpath("//crux-website-component[@cx-prop-zcqa='manual_$LinkedIn']"))
				.sendKeys(lurl);

	}

	@AfterTest
	public void close() {
		driver.close();
	}
}
