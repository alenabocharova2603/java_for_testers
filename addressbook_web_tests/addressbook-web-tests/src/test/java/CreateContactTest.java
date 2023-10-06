import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateContactTest extends TestBase{

    @Test
    public void createContact() {
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys("Nina");
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys("Ivanova");
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys("3 Internacounal, 243");
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys("+98567841456");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys("nina_kot@koler.com");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("home page")).click();

    }
}
