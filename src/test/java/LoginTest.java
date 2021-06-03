import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

@ExtendWith(SeleniumJupiter.class)
public class LoginTest {

    @Test
    public void testDangNhapDungThongTin(EdgeDriver driver) throws InterruptedException{
        driver.get("http://localhost:8081");
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();
        Thread.sleep(2000);
        Assertions.assertEquals(driver.getTitle(), "Trang chủ");
        driver.close();
    }

    @Test
    public void testDangNhapSaiTenDangNhap(EdgeDriver driver) throws InterruptedException{
        driver.get("http://localhost:8081");
        Thread.sleep(1000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();
        WebElement textError = driver.findElement(By.id("error"));
        Thread.sleep(2000);

        Assertions.assertEquals(textError.getText(), "Tài khoản đăng nhập không tồn tại");

        driver.close();
    }

    @Test
    public void testDangNhapSaiMatKhau(EdgeDriver driver) throws InterruptedException{
        driver.get("http://localhost:8081");
        Thread.sleep(1000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("1234");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();
        WebElement textError = driver.findElement(By.id("error"));
        Thread.sleep(2000);

        Assertions.assertEquals(textError.getText(), "Tài khoản đăng nhập không tồn tại");

        driver.close();
    }

    @Test
    public void testDangNhapTenDangNhapTrong(EdgeDriver driver) throws InterruptedException {
        driver.get("http://localhost:8081");
        Thread.sleep(1000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();
        WebElement textError = driver.findElement(By.id("error_username"));
        Assertions.assertEquals(textError.getText(), "Tên đăng nhập không được để trống");

        driver.close();
    }

    @Test
    public void testDangNhapMatKhauTrong(EdgeDriver driver) throws InterruptedException {
        driver.get("http://localhost:8081");
        Thread.sleep(1000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();
        WebElement textError = driver.findElement(By.id("error_pass"));
        Thread.sleep(2000);

        Assertions.assertEquals(textError.getText(), "Mật khẩu không được để trống");

        driver.close();
    }

    @Test
    public void testDangNhapMatKhauVaTenDangNhapTrong(EdgeDriver driver)
            throws InterruptedException {
        driver.get("http://localhost:8081");
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();
        WebElement textError = driver.findElement(By.id("error_username"));
        Thread.sleep(2000);

        Assertions.assertEquals(textError.getText(), "Tên đăng nhập không được để trống");

        driver.close();
    }

}
