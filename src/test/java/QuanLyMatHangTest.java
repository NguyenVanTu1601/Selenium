import com.google.j2objc.annotations.LoopTranslation;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;


@ExtendWith(SeleniumJupiter.class)
public class QuanLyMatHangTest {
    public static String url = "http://localhost:8081";
    public static String url2 = "http://localhost:8081/home";
    public static String url3 = "http://localhost:8081/productmanagement";
    public static String url4 = "http://localhost:8081/products/add";
    public static String url5 = "http://localhost:8081/products/edit";
    public static String url6 = "http://localhost:8081/products/delete";
    public static String url7 = "http://localhost:8081/products/";

    /* Thêm mặt hàng */
    @Test
    public void addSuccessMatHang(InternetExplorerDriver driver) throws InterruptedException{
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        // chuyển sang trang chủ
        WebElement buttonQuanly = driver.findElement(By.id("quanly"));
        buttonQuanly.click();
        Thread.sleep(1000);

        // sang trang quản lý mặt hàng
        WebElement buttonThemMoi = driver.findElement(By.id("btnNew"));
        buttonThemMoi.click();
        Thread.sleep(1000);

        // thêm thông tin mặt hàng
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Tin Học");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("15000");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("15500");
        WebElement buttonLuu = driver.findElement(By.id("btnAdd"));
        buttonLuu.click();

        // kiểm tra xem có chuyển đúng về trang Quản lý hay ko
        Assertions.assertEquals(driver.getTitle(), "Quản lý mặt hàng");
    }

    /* Thêm mặt hàng với tên mặt hàng trống */


    /* Thêm mặt hàng với giá nhập trống*/

    /* Thêm mặt hàng với giá bán trống */

    /* Thêm mặt hàng với giá nhập là số <= 0 */

    /* Thêm mặt hàng với giá bán là số <=0 */

    /* Sửa thông tin mặt hàng */
    @Test
    public void testEditSuccessMatHang(InternetExplorerDriver driver) throws InterruptedException{
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        // chuyển sang trang chủ
        WebElement buttonQuanly = driver.findElement(By.id("quanly"));
        buttonQuanly.click();
        Thread.sleep(1000);

        // sang trang quản lý mặt hàng
        WebElement buttonSua = driver.findElement(By.id("btnEdit"));
        buttonSua.click();
        Thread.sleep(1000);

        // trang tìm kiếm mặt hàng
        //driver.get(url5);
        WebElement row = driver.findElement(By.id("54"));
        row.click();

        // giao diện sửa
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.clear();
        tenMatHang.sendKeys("Sách Tin 12");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        WebElement giaBan = driver.findElement(By.id("price2"));

        WebElement btnSua = driver.findElement(By.id("btnSua"));
        btnSua.click();
        Thread.sleep(2000); // xem dialog

        Alert alert = driver.switchTo().alert();
        alert.accept();

        Assertions.assertEquals(driver.getTitle(),"Quản lý mặt hàng");
        Thread.sleep(5000);
    }

    @Test
    public void testDeleteSuccessMatHang(InternetExplorerDriver driver) throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        // chuyển sang trang chủ
        WebElement buttonQuanly = driver.findElement(By.id("quanly"));
        buttonQuanly.click();
        Thread.sleep(1000);

        // sang trang quản lý mặt hàng
        WebElement buttonSua = driver.findElement(By.id("btnDelete"));
        buttonSua.click();
        Thread.sleep(1000);

        // trang tìm kiếm mặt hàng
        //driver.get(url5);
        WebElement row = driver.findElement(By.id("54"));
        row.click();

        // giao diện sửa-xóa

        WebElement btnXoa = driver.findElement(By.id("btnXoa"));
        btnXoa.click();
        Thread.sleep(2000); // xem dialog

        Alert alert = driver.switchTo().alert();
        alert.accept();

        Assertions.assertEquals(driver.getTitle(),"Quản lý mặt hàng");
        Thread.sleep(5000);
    }
}
