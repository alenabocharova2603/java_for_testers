import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    public static WebDriver driver;

    public static void createGroup(String group_name, String group_header, String group_footer) {
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys("Group1");
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys("Group header 1");
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys("Group footer 1");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    protected static void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
    }

    protected static boolean isGroupPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    protected static void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    protected static void openHomePage() {
        driver.findElement(By.linkText("home")).click();
    }

    protected static void openAddNewPage() {
        driver.findElement(By.linkText("add new")).click();
    }

    protected static void CreateContact(String firstname, String lastname, String address, String mobile, String email) {
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
    }

    protected static void RemoveContact() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
        driver.switchTo().alert().accept();
    }

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Chrome\\chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver", "C:\\Firefox\\geckodriver.exe");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
        driver.get("http://localhost/addressbook/");
        driver.manage().window().setSize(new Dimension(550, 693));
        driver.findElement(By.name("user")).sendKeys("admin");
        driver.findElement(By.name("pass")).click();
        driver.findElement(By.name("pass")).sendKeys("secret");
        driver.findElement(By.cssSelector("input:nth-child(7)")).click();

    }

    public static boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    protected boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }
}

