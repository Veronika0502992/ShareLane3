import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest
{

    @Test
    public void zipCode5Digits()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        boolean isSignUpPageOpened = driver.findElement(By.cssSelector("input[value=Register]")).isDisplayed();
        assertTrue(isSignUpPageOpened, "Страница регистрации не открылась");
        driver.quit();

    }

    @Test
    public void zipCode4Digits()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("1111");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        String actualError = driver.findElement(By.cssSelector("span[class = error_message")).getText();
        assertEquals(actualError, "Oops, error on page. ZIP code should have 5 digits",
                "Wrong error message shown");
        //<span class="error_message">Oops, error on page. ZIP code should have 5 digits</span>
        //driver.quit();

    }

    @Test
    public void zipCode6Digits()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        String actualError = driver.findElement(By.cssSelector("span[class = error_message")).getText();
        assertEquals(actualError, "Oops, error on page. ZIP code should have 5 digits",
                "Wrong error message show");
        //<span class="error_message">Oops, error on page. ZIP code should have 5 digits</span>
        //driver.quit();
    }

    @Test
    public void zipCodeNothing()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        String actualError = driver.findElement(By.cssSelector("span[class = error_message")).getText();
        assertEquals(actualError, "Oops, error on page. ZIP code should have 5 digits",
                "Wrong error message show");
        //driver.quit();
    }
    @Test
    public void zipCodeSymbols()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("iii*i");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        String actualError = driver.findElement(By.cssSelector("span[class = error_message")).getText();
        assertEquals(actualError, "Oops, error on page. ZIP code should have 5 digits",
                "Wrong error message show");
        //driver.quit();
    }

    @Test
    public void successfulSignUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("kkk");
        driver.findElement(By.name("last_name")).sendKeys("jjj");
        driver.findElement(By.name("email")).sendKeys("jjjj@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("12345678");
        driver.findElement(By.name("password2")).sendKeys("12345678");
        driver.findElement(By.cssSelector("input[value=Register")).click();
        String actualError = driver.findElement(By.cssSelector("span[class=confirmation_message]")).getText();
        assertEquals(actualError, "Account is created!",
                "User was not registered");
        //driver.quit();



    }

    @Test
    public void NegativeSignUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("");
        driver.findElement(By.name("last_name")).sendKeys("jjj");
        driver.findElement(By.name("email")).sendKeys("jjjj@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("12345678");
        driver.findElement(By.name("password2")).sendKeys("12345678");
        driver.findElement(By.cssSelector("input[value=Register")).click();
        String actualError = driver.findElement(By.cssSelector("span[class=error_message]")).getText();
        assertEquals(actualError, "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "User was not registered");
        //driver.quit();


    }

    @Test
    public void SignUpNegativeEmail()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("kkk");
        driver.findElement(By.name("last_name")).sendKeys("jjj");
        driver.findElement(By.name("email")).sendKeys("jjjj@@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("12345678");
        driver.findElement(By.name("password2")).sendKeys("12345678");
        driver.findElement(By.cssSelector("input[value=Register")).click();
        String actualError = driver.findElement(By.cssSelector("span[class=error_message]")).getText();
        assertEquals(actualError, "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "User was not registered");
        //driver.quit();

    }

    @Test
    public void SignUpNothing()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("");
        driver.findElement(By.name("last_name")).sendKeys("");
        driver.findElement(By.name("email")).sendKeys("");
        driver.findElement(By.name("password1")).sendKeys("");
        driver.findElement(By.name("password2")).sendKeys("");
        driver.findElement(By.cssSelector("input[value=Register")).click();
        String actualError = driver.findElement(By.cssSelector("span[class=error_message]")).getText();
        assertEquals(actualError, "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "User was not registered");
        //driver.quit();

    }

    @Test
    public void SignUpIncorrectPassword()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("kkk");
        driver.findElement(By.name("last_name")).sendKeys("jjj");
        driver.findElement(By.name("email")).sendKeys("jjjj@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("12345678");
        driver.findElement(By.name("password2")).sendKeys("12345687");
        driver.findElement(By.cssSelector("input[value=Register")).click();
        String actualError = driver.findElement(By.cssSelector("span[class=error_message]")).getText();
        assertEquals(actualError, "Oops, error on page. Some of your fields have invalid data or email was previously used",
                "User was not registered");
        //driver.quit();

    }

    @Test
    public void successfulSignUp1()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("kkk");
        driver.findElement(By.name("last_name")).sendKeys("jjj");
        driver.findElement(By.name("email")).sendKeys("jjjj@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("12345678");
        String actualType = driver.findElement(By.name("password1")).getAttribute("type");
        assertEquals(actualType, "password","Неверный пароль");
        driver.findElement(By.name("password2")).sendKeys("12345678");
        driver.findElement(By.cssSelector("input[value=Register")).click();
        String actualError = driver.findElement(By.cssSelector("span[class=confirmation_message]")).getText();
        assertEquals(actualError, "Account is created!",
                "User was not registered");
        //driver.quit();
    }
}


