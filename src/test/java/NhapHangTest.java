import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.bouncycastle.math.ec.rfc8032.Ed25519;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

@ExtendWith(SeleniumJupiter.class)
public class NhapHangTest {

    private static String urlLogin = "http://localhost:8081";

    //Nhập hàng thành công
    @Test
    public void testNhapHangThanhCong(EdgeDriver driver) throws Exception{
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Trang chủ nhân viên kho
        WebElement button = driver.findElementById("btnNhapHang");
        button.click();

        //Trang tìm kiếm nhà cung cấp
        WebElement rowNCC = driver.findElement(By.id("1"));
        rowNCC.click();

        // Trang tìm mặt hàng
        WebElement rowMatHang = driver.findElement(By.id("54"));
        rowMatHang.click();

        WebElement giaNhap = driver.findElement(By.id("price"));
        WebElement soLuong = driver.findElementById("quantity");
        soLuong.clear();
        soLuong.sendKeys("2");
        WebElement btnNhapHang = driver.findElement(By.id("btnNhapHang"));
        btnNhapHang.click();

        // trang hóa đơn
        WebElement btnSave = driver.findElementById("btnSaveOrder");
        btnSave.click();
        Thread.sleep(2000);

        Assertions.assertEquals(driver.getTitle(), "Trang chủ");

    }
    // Nhập hàng với số lượng mặt hàng nhập trống
    @Test
    public void testNhapHangSoLuongTrong(EdgeDriver driver) throws InterruptedException {
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Trang chủ nhân viên kho
        WebElement button = driver.findElementById("btnNhapHang");
        button.click();

        //Trang tìm kiếm nhà cung cấp
        WebElement rowNCC = driver.findElement(By.id("1"));
        rowNCC.click();

        // Trang tìm mặt hàng
        WebElement rowMatHang = driver.findElement(By.id("54"));
        rowMatHang.click();

        WebElement giaNhap = driver.findElement(By.id("price"));
        WebElement soLuong = driver.findElementById("quantity");
        soLuong.clear();
        //soLuong.sendKeys("5");
        WebElement btnNhapHang = driver.findElement(By.id("btnNhapHang"));
        btnNhapHang.click();
        Thread.sleep(2000);

        // Return lại trang nếu bị lỗi
        Assertions.assertEquals(driver.getTitle(), "Hàng nhập");
    }

    // Nhập hàng với số lượng mặt hàng nhập <= 0
    @Test
    public void testNhapHangSoLuongAm(EdgeDriver driver) throws InterruptedException {
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Trang chủ nhân viên kho
        WebElement button = driver.findElementById("btnNhapHang");
        button.click();

        //Trang tìm kiếm nhà cung cấp
        WebElement rowNCC = driver.findElement(By.id("1"));
        rowNCC.click();

        // Trang tìm mặt hàng
        WebElement rowMatHang = driver.findElement(By.id("54"));
        rowMatHang.click();

        WebElement giaNhap = driver.findElement(By.id("price"));
        WebElement soLuong = driver.findElementById("quantity");
        soLuong.clear();
        soLuong.sendKeys("-50");
        WebElement btnNhapHang = driver.findElement(By.id("btnNhapHang"));
        btnNhapHang.click();
        Thread.sleep(2000);

        String valuesTest = "Value must be greater than or equal to 1.";
        String valuesExpres = "Giá trị phải lớn hơn hoặc bằng 1.";
        Assertions.assertEquals(soLuong.getAttribute("validationMessage").toString(),
                valuesExpres);
        Assertions.assertEquals(driver.getTitle().toString(),"Hàng nhập");

        driver.close();
    }

    // Nhập hàng với số lượng quá lớn
    @Test
    public void testNhapHangSoLuongLon(EdgeDriver driver) throws InterruptedException {
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Trang chủ nhân viên kho
        WebElement button = driver.findElementById("btnNhapHang");
        button.click();

        //Trang tìm kiếm nhà cung cấp
        WebElement rowNCC = driver.findElement(By.id("1"));
        rowNCC.click();

        // Trang tìm mặt hàng
        WebElement rowMatHang = driver.findElement(By.id("54"));
        rowMatHang.click();

        WebElement giaNhap = driver.findElement(By.id("price"));
        WebElement soLuong = driver.findElementById("quantity");
        soLuong.clear();
        soLuong.sendKeys("-50");
        WebElement btnNhapHang = driver.findElement(By.id("btnNhapHang"));
        btnNhapHang.click();
        Thread.sleep(2000);

        String valuesTest = "Value must be greater than or equal to 1.";
        String valuesExpres = "Giá trị phải nhỏ hơn hoặc bằng 10000.";
        Assertions.assertEquals(soLuong.getAttribute("validationMessage").toString(),
                valuesExpres);
        Assertions.assertEquals(driver.getTitle().toString(),"Hàng nhập");

        driver.close();
    }

    // Đơn giá trống
    @Test
    public void testNhapHangDonGiaTrong(EdgeDriver driver) throws InterruptedException {
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Trang chủ nhân viên kho
        WebElement button = driver.findElementById("btnNhapHang");
        button.click();

        //Trang tìm kiếm nhà cung cấp
        WebElement rowNCC = driver.findElement(By.id("1"));
        rowNCC.click();

        // Trang tìm mặt hàng
        WebElement rowMatHang = driver.findElement(By.id("54"));
        rowMatHang.click();

        WebElement giaNhap = driver.findElement(By.id("price"));
        giaNhap.clear();
        WebElement soLuong = driver.findElementById("quantity");
        WebElement btnNhapHang = driver.findElement(By.id("btnNhapHang"));
        btnNhapHang.click();
        Thread.sleep(2000);

        // Return lại trang nếu trường bị lỗi - nhưng ko quay lại
        Assertions.assertEquals(driver.getTitle(), "Hàng nhập");
    }

    // test dơn giá âm
    @Test
    public void testNhapHangDonGiaAm(EdgeDriver driver) throws InterruptedException {
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Trang chủ nhân viên kho
        WebElement button = driver.findElementById("btnNhapHang");
        button.click();

        //Trang tìm kiếm nhà cung cấp
        WebElement rowNCC = driver.findElement(By.id("1"));
        rowNCC.click();

        // Trang tìm mặt hàng
        WebElement rowMatHang = driver.findElement(By.id("54"));
        rowMatHang.click();

        WebElement giaNhap = driver.findElement(By.id("price"));
        giaNhap.clear();
        giaNhap.sendKeys("-5000");
        WebElement soLuong = driver.findElementById("quantity");
        WebElement btnNhapHang = driver.findElement(By.id("btnNhapHang"));
        btnNhapHang.click();
        Thread.sleep(2000);

        String valuesTest = "Value must be greater than or equal to 1.";
        String valuesExpres = "Giá trị phải lớn hơn hoặc bằng 1.";
        Assertions.assertEquals(giaNhap.getAttribute("validationMessage").toString(),
                valuesExpres);
        Assertions.assertEquals(driver.getTitle().toString(),"Hàng nhập");

        driver.close();
    }

    // Nhập hàng với giá nhập quá lớn
    @Test
    public void testNhapHangDonGiaLon(EdgeDriver driver) throws InterruptedException {
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Trang chủ nhân viên kho
        WebElement button = driver.findElementById("btnNhapHang");
        button.click();

        //Trang tìm kiếm nhà cung cấp
        WebElement rowNCC = driver.findElement(By.id("1"));
        rowNCC.click();

        // Trang tìm mặt hàng
        WebElement rowMatHang = driver.findElement(By.id("54"));
        rowMatHang.click();

        WebElement giaNhap = driver.findElement(By.id("price"));
        giaNhap.clear();
        giaNhap.sendKeys("11111111111111111111111");
        WebElement soLuong = driver.findElementById("quantity");
        WebElement btnNhapHang = driver.findElement(By.id("btnNhapHang"));
        btnNhapHang.click();
        Thread.sleep(2000);

        String valuesTest = "Value must be greater than or equal to 1.";
        String valuesExpres = "Giá trị phải nhỏ hơn hoặc bằng 10000.";
        Assertions.assertEquals(soLuong.getAttribute("validationMessage").toString(),
                valuesExpres);
        Assertions.assertEquals(driver.getTitle().toString(),"Hàng nhập");

        driver.close();
    }
}
