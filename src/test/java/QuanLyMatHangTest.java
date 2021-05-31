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
import org.openqa.selenium.edge.EdgeDriver;
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
    public void addSuccessMatHang(EdgeDriver driver) throws InterruptedException{
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
    @Test
    public void addMatHangTrongTen(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang thêm mặt hàng
        driver.get(url4);
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.clear();
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("15000");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("15500");
        WebElement buttonLuu = driver.findElement(By.id("btnAdd"));
        buttonLuu.click();

        WebElement textError = driver.findElementById("error_name");
        Assertions.assertEquals(textError.getText(),"Tên mặt hàng không được để trống!");
        Assertions.assertEquals(driver.getTitle().toString(),"Thêm mới sản phẩm");
    }

    /* Thêm mặt hàng với giá nhập trống*/
    @Test
    public void addMatHangGiaNhapTrong(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang thêm mặt hàng
        driver.get(url4);
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("15500");
        WebElement buttonLuu = driver.findElement(By.id("btnAdd"));
        buttonLuu.click();

        Assertions.assertEquals(driver.getTitle().toString(),"Thêm mới sản phẩm");

        // lý do lỗi : trường nhập giá là số, nhưng nếu xóa hết thì nó là khoảng trắng
        // không thể convert khoảng trắng sang Integer được nên lỗi 404 luôn
    }

    /* Thêm mặt hàng với giá bán trống */
    @Test
    public void addMatHangGiaBanTrong(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang thêm mặt hàng
        driver.get(url4);
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("15500");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        WebElement buttonLuu = driver.findElement(By.id("btnAdd"));
        buttonLuu.click();

        Assertions.assertEquals(driver.getTitle().toString(),"Thêm mới sản phẩm");
        // Lỗi y như cái trên

    }

    /* Thêm mặt hàng với giá nhập là số <= 0 */
    @Test
    public void addMatHangGiaNhapAm(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang thêm mặt hàng
        driver.get(url4);
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("-100");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("15500");
        WebElement buttonLuu = driver.findElement(By.id("btnAdd"));
        buttonLuu.click();

        String valuesTest = "Value must be greater than or equal to 1.";
        String valuesExpres = "Giá trị phải lớn hơn hoặc bằng 1.";
        Assertions.assertEquals(giaNhap.getAttribute("validationMessage").toString(),
                valuesTest);
        Assertions.assertEquals(driver.getTitle().toString(),"Thêm mới sản phẩm");
    }

    /* Thêm mặt hàng với giá bán là số <=0 */
    @Test
    public void addMatHangGiaBanAm(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang thêm mặt hàng
        driver.get(url4);
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("15500");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("-100");
        WebElement buttonLuu = driver.findElement(By.id("btnAdd"));
        buttonLuu.click();

        String valuesTest = "Value must be greater than or equal to 1.";
        Assertions.assertEquals(giaBan.getAttribute("validationMessage").toString(),
                valuesTest);
        Assertions.assertEquals(driver.getTitle().toString(),"Thêm mới sản phẩm");
    }


    /* Thêm mặt hàng với giá bán quá lớn > 1 tỷ */
    @Test
    public void addMatHangGiaBanLon(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang thêm mặt hàng
        driver.get(url4);
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("15500");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("100000000000");
        WebElement buttonLuu = driver.findElement(By.id("btnAdd"));
        buttonLuu.click();

        String valuesTest = "Value must be less than or equal to 100000000.";
        Assertions.assertEquals(giaBan.getAttribute("validationMessage").toString(),
                valuesTest);
        Assertions.assertEquals(driver.getTitle().toString(),"Thêm mới sản phẩm");
    }

    /* Thêm mặt hàng với giá nhập quá lớn > 1 tỷ */
    @Test
    public void addMatHangGiaNhapLon(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang thêm mặt hàng
        driver.get(url4);
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("100000000000");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("11500");
        WebElement buttonLuu = driver.findElement(By.id("btnAdd"));
        buttonLuu.click();

        String valuesTest = "Value must be less than or equal to 100000000.";
        Assertions.assertEquals(giaNhap.getAttribute("validationMessage").toString(),
                valuesTest);
        Assertions.assertEquals(driver.getTitle().toString(),"Thêm mới sản phẩm");
    }

    /* Thêm mặt hàng với giá nhập lớn hơn giá bán */
    @Test
    public void addMatHangGiaNhapLonHonGiaBan(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang thêm mặt hàng
        driver.get(url4);
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("17000");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("15000");
        WebElement buttonLuu = driver.findElement(By.id("btnAdd"));
        buttonLuu.click();

        WebElement error_price = driver.findElementById("error_price");
        String valuesTest = "Value must be less than or equal to 100000000.";
        Assertions.assertEquals(error_price.getText().toString(),
                "Giá bán phải lớn hơn giá nhập!");
        Assertions.assertEquals(driver.getTitle().toString(),"Thêm mới sản phẩm");
    }

    /*--------------------------------------------------------------------------*/
    /* Sửa thông tin mặt hàng */
    @Test
    public void testEditSuccessMatHang(EdgeDriver driver) throws InterruptedException{
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

    /* Sửa mặt hàng với tên mặt hàng trống */
    @Test
    public void testEditMatHangTenTrong(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang tìm kiếm mặt hàng
        driver.get(url5);
        WebElement row = driver.findElement(By.id("54"));
        row.click();

        // vào trang sửa
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.clear();
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("15000");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("15500");


        WebElement btnSua = driver.findElement(By.id("btnSua"));
        btnSua.click();
        Thread.sleep(2000); // xem dialog
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // Kiểm tra
        Assertions.assertEquals(driver.getTitle().toString(),"Sửa xóa sản phẩm");
        WebElement textError = driver.findElementById("error_name");
        Assertions.assertEquals(textError.getText(),"Tên mặt hàng không được để trống!");

    }

    /* Sửa mặt hàng với giá nhập trống*/
    @Test
    public void testEditMatHangGiaNhapTrong(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang tìm kiếm mặt hàng
        driver.get(url5);
        WebElement row = driver.findElement(By.id("54"));
        row.click();

        // vào trang sửa
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("15500");

        // bấm nút sửa
        WebElement btnSua = driver.findElement(By.id("btnSua"));
        btnSua.click();
        // click oke trên dialog
        Thread.sleep(2000); // xem dialog
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Assertions.assertEquals(driver.getTitle().toString(),"Sửa xóa sản phẩm");

        // lý do lỗi : trường nhập giá là số, nhưng nếu xóa hết thì nó là khoảng trắng
        // không thể convert khoảng trắng sang Integer được nên lỗi 404 luôn
    }

    /* Sửa mặt hàng với giá bán trống */
    @Test
    public void testEditMatHangGiaBanTrong(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang tìm kiếm mặt hàng
        driver.get(url5);
        WebElement row = driver.findElement(By.id("54"));
        row.click();

        // vào trang sửa
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("15500");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();

        WebElement btnSua = driver.findElement(By.id("btnSua"));
        btnSua.click();

        // click oke trên dialog
        Thread.sleep(2000); // xem dialog
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Assertions.assertEquals(driver.getTitle().toString(),"Sửa xóa sản phẩm");
        // Lỗi y như cái trên

    }

    /* Sửa mặt hàng với giá nhập là số <= 0 */
    @Test
    public void editMatHangGiaNhapAm(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang tìm kiếm mặt hàng
        driver.get(url5);
        WebElement row = driver.findElement(By.id("54"));
        row.click();

        //vào trang sửa
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("-100");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("15500");

        WebElement btnSua = driver.findElement(By.id("btnSua"));
        btnSua.click();

        // click oke trên dialog
        Thread.sleep(2000); // xem dialog
        Alert alert = driver.switchTo().alert();
        alert.accept();

        String valuesTest = "Value must be greater than or equal to 1.";
        String valuesExpres = "Giá trị phải lớn hơn hoặc bằng 1.";
        Assertions.assertEquals(giaNhap.getAttribute("validationMessage").toString(),
                valuesTest);
        Assertions.assertEquals(driver.getTitle().toString(),"Sửa xóa sản phẩm");
    }

    /* Sửa mặt hàng với giá bán là số <=0 */
    @Test
    public void editMatHangGiaBanAm(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang tìm kiếm mặt hàng
        driver.get(url5);
        WebElement row = driver.findElement(By.id("54"));
        row.click();

        // vào trang sửa
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("15500");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("-100");

        WebElement btnSua = driver.findElement(By.id("btnSua"));
        btnSua.click();

        // click oke trên dialog
        Thread.sleep(2000); // xem dialog
        Alert alert = driver.switchTo().alert();
        alert.accept();

        String valuesTest = "Value must be greater than or equal to 1.";
        Assertions.assertEquals(giaBan.getAttribute("validationMessage").toString(),
                valuesTest);
        Assertions.assertEquals(driver.getTitle().toString(),"Sửa xóa sản phẩm");
    }


    /* Sửa mặt hàng với giá bán quá lớn > 1 tỷ */
    @Test
    public void editMatHangGiaBanLon(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang tìm kiếm mặt hàng
        driver.get(url5);
        WebElement row = driver.findElement(By.id("54"));
        row.click();

        // vào trang sửa thông tin
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("15500");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("100000000000");

        WebElement btnSua = driver.findElement(By.id("btnSua"));
        btnSua.click();

        // click oke trên dialog
        Thread.sleep(2000); // xem dialog
        Alert alert = driver.switchTo().alert();
        alert.accept();

        String valuesTest = "Value must be less than or equal to 100000000.";
        Assertions.assertEquals(giaBan.getAttribute("validationMessage").toString(),
                valuesTest);
        Assertions.assertEquals(driver.getTitle().toString(),"Sửa xóa sản phẩm");
    }

    /* Sửa mặt hàng với giá nhập quá lớn > 1 tỷ */
    @Test
    public void editMatHangGiaNhapLon(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang tìm kiếm mặt hàng
        driver.get(url5);
        WebElement row = driver.findElement(By.id("54"));
        row.click();

        // vào trang sửa
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("100000000000");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("11500");

        WebElement btnSua = driver.findElement(By.id("btnSua"));
        btnSua.click();

        // click oke trên dialog
        Thread.sleep(2000); // xem dialog
        Alert alert = driver.switchTo().alert();
        alert.accept();

        String valuesTest = "Value must be less than or equal to 100000000.";
        Assertions.assertEquals(giaNhap.getAttribute("validationMessage").toString(),
                valuesTest);
        Assertions.assertEquals(driver.getTitle().toString(),"Sửa xóa sản phẩm");
    }

    /* Sửa mặt hàng với giá nhập lớn hơn giá bán */
    @Test
    public void editMatHangGiaNhapLonHonGiaBan(EdgeDriver driver) throws Exception{
        // đăng nhập
        driver.manage().window().maximize();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("huutu2302");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        //Vào trang tìm kiếm mặt hàng
        driver.get(url5);
        WebElement row = driver.findElement(By.id("54"));
        row.click();

        // sang sang sửa
        WebElement tenMatHang = driver.findElement(By.id("name"));
        tenMatHang.sendKeys("Sách Địa Lý");
        WebElement giaNhap = driver.findElement(By.id("price1"));
        giaNhap.clear();
        giaNhap.sendKeys("17000");
        WebElement giaBan = driver.findElement(By.id("price2"));
        giaBan.clear();
        giaBan.sendKeys("15000");

        WebElement btnSua = driver.findElement(By.id("btnSua"));
        btnSua.click();

        // click oke trên dialog
        Thread.sleep(2000); // xem dialog
        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement error_price = driver.findElementById("error_price");
        String valuesTest = "Value must be less than or equal to 100000000.";
        Assertions.assertEquals(error_price.getText().toString(),
                "Giá bán phải lớn hơn giá nhập!");
        Assertions.assertEquals(driver.getTitle().toString(),"Sửa xóa sản phẩm");
    }

    /*--------------------------------------------------------------------------*/
    @Test
    public void testDeleteSuccessMatHang(EdgeDriver driver) throws InterruptedException {
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
