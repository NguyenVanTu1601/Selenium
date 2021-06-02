import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

@ExtendWith(SeleniumJupiter.class)
public class NhaCungCapTest {
    private static String urlAdd = "http://localhost:8081/suppliers/new";
    private static String urlLogin = "http://localhost:8081";
    private static String urlSearch = "http://localhost:8081/importproduct";


    /* ------------------------------------------------------------------- */
    // Thêm nhà cung cấp
    // Chưa test
    @Test
    public void testThemNhaCungCapSuccess(EdgeDriver driver){
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        // mở trang add ncc
        driver.get(urlAdd);
        WebElement tenNCC = driver.findElement(By.id("name"));
        WebElement tenChu = driver.findElement(By.id("name2"));
        WebElement diachi = driver.findElementById("address");
        WebElement sdt = driver.findElementById("phone");
        WebElement buttonThem = driver.findElementById("btnAdd");

        tenNCC.sendKeys("Bách hóa Thanh Xuân");
        tenChu.sendKeys("Bách hóa");
        diachi.sendKeys("C12, Nguyễn Trãi, P. Thanh Xuân Bắc, Q. Thanh Xuân, Tp. Hà Nội");
        sdt.sendKeys("038 257 059");

        buttonThem.click();

        Assertions.assertEquals(driver.getTitle(), "Tìm kiếm");
        driver.close();

    }

    // để trống tên nhà cung cấp
    @Test
    public void testThemNCCTenDeTrong(EdgeDriver driver) throws Exception{
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        // mở trang add ncc
        driver.get(urlAdd);
        WebElement tenNCC = driver.findElement(By.id("name"));
        WebElement tenChu = driver.findElement(By.id("name2"));
        WebElement diachi = driver.findElementById("address");
        WebElement sdt = driver.findElementById("phone");
        WebElement buttonThem = driver.findElementById("btnAdd");


        tenNCC.clear();
        tenChu.sendKeys("Bách hóa");
        diachi.sendKeys("C12, Nguyễn Trãi, P. Thanh Xuân Bắc, Q. Thanh Xuân, Tp. Hà Nội");
        sdt.sendKeys("038 257 059");
        buttonThem.click();
        Thread.sleep(3000);

        // đặt error ở đây chứ k đặt trc khi click bởi vì click xong mới hiện lỗi
        WebElement error_name = driver.findElementById("error_name");
        Assertions.assertEquals(error_name.getText(),"Tên nhà cung cấp không được để trống");
        Assertions.assertEquals(driver.getTitle(), "Thêm nhà cung cấp");
    }

    // để trống tên chủ cửa hàng
    @Test
    public void testThemNCCTenChuDeTrong(EdgeDriver driver) throws Exception{
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        // mở trang add ncc
        driver.get(urlAdd);
        WebElement tenNCC = driver.findElement(By.id("name"));
        WebElement tenChu = driver.findElement(By.id("name2"));
        WebElement diachi = driver.findElementById("address");
        WebElement sdt = driver.findElementById("phone");
        WebElement buttonThem = driver.findElementById("btnAdd");

        tenNCC.sendKeys("Bách hóa Thanh Xuân");
        tenChu.clear();
        diachi.sendKeys("C12, Nguyễn Trãi, P. Thanh Xuân Bắc, Q. Thanh Xuân, Tp. Hà Nội");
        sdt.sendKeys("038 257 059");
        buttonThem.click();

        WebElement error_name2 = driver.findElementById("error_name2");
        Assertions.assertEquals(error_name2.getText(),"Tên chủ cửa hàng không được để trống");
        Assertions.assertEquals(driver.getTitle(), "Thêm nhà cung cấp");
    }

    // Địa chỉ để trống
    @Test
    public void testThemNCCDiaChiDeTrong(EdgeDriver driver) throws Exception{
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        // mở trang add ncc
        driver.get(urlAdd);
        WebElement tenNCC = driver.findElement(By.id("name"));
        WebElement tenChu = driver.findElement(By.id("name2"));
        WebElement diachi = driver.findElementById("address");
        WebElement sdt = driver.findElementById("phone");
        WebElement buttonThem = driver.findElementById("btnAdd");

        tenNCC.sendKeys("Bách hóa Thanh Xuân");
        tenChu.sendKeys("Bách hóa");
        diachi.clear();
        sdt.sendKeys("038 257 059");
        buttonThem.click();

        WebElement error_address = driver.findElementById("error_add");
        Assertions.assertEquals(error_address.getText(),"Địa chỉ nhà cung cấp không được để trống");
        Assertions.assertEquals(driver.getTitle(), "Thêm nhà cung cấp");
    }

    // số điện thoại để trông
    @Test
    public void testThemNCCSoDienThoaiDeTrong(EdgeDriver driver) throws Exception{
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        // mở trang add ncc
        driver.get(urlAdd);
        WebElement tenNCC = driver.findElement(By.id("name"));
        WebElement tenChu = driver.findElement(By.id("name2"));
        WebElement diachi = driver.findElementById("address");
        WebElement sdt = driver.findElementById("phone");
        WebElement buttonThem = driver.findElementById("btnAdd");

        tenNCC.sendKeys("Bách hóa Thanh Xuân");
        tenChu.sendKeys("Bách hóa");
        diachi.sendKeys("C12, Nguyễn Trãi, P. Thanh Xuân Bắc, Q. Thanh Xuân, Tp. Hà Nội");
        sdt.clear();
        buttonThem.click();

        WebElement error_phone = driver.findElementById("error_phone");
        Assertions.assertEquals(error_phone.getText(),"Số điện thoại không được để trống");
        Assertions.assertEquals(driver.getTitle(), "Thêm nhà cung cấp");
    }

    // để trống toàn bộ
    @Test
    public void testThemNCCDeTrongToanBo(EdgeDriver driver) throws Exception{
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        // mở trang add ncc
        driver.get(urlAdd);
        WebElement tenNCC = driver.findElement(By.id("name"));
        WebElement tenChu = driver.findElement(By.id("name2"));
        WebElement diachi = driver.findElementById("address");
        WebElement sdt = driver.findElementById("phone");
        WebElement buttonThem = driver.findElementById("btnAdd");

        tenNCC.clear();
        tenChu.clear();
        diachi.clear();;
        sdt.clear();
        buttonThem.click();
        Thread.sleep(3000);

        WebElement error_name = driver.findElementById("error_name");
        WebElement error_name2 = driver.findElementById("error_name2");
        WebElement error_add = driver.findElementById("error_add");
        WebElement error_phone = driver.findElementById("error_phone");

        Assertions.assertEquals(error_name.getText(),"Tên nhà cung cấp không được để trống");
        Assertions.assertEquals(error_name2.getText(),"Tên chủ cửa hàng không được để trống");
        Assertions.assertEquals(error_add.getText(),"Địa chỉ nhà cung cấp không được để trống");
        Assertions.assertEquals(error_phone.getText(),"Số điện thoại không được để trống");
        Assertions.assertEquals(driver.getTitle(), "Thêm nhà cung cấp");
    }

    /*-------------------------------------------------------------------------------*/
    //Tìm kiếm nhà cung cấp theo tên
    //Tìm kiếm có dữ liệu trả về
    @Test
    public void testTimNhaCungCapCoKetQua(EdgeDriver driver){
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        driver.get(urlSearch);
        WebElement inputSearch = driver.findElementById("inputSearch");
        WebElement buttonSearch = driver.findElementById("btnSearch");
        inputSearch.sendKeys("Nhà Sách");
        buttonSearch.click();

        WebElement table = driver.findElement(By.xpath("//*[@id='table_nhacungcap']/tbody"));
        List< WebElement > rows_table = table.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();

        System.out.println(rows_count + "");
        Assertions.assertEquals(driver.getTitle(),"Tìm kiếm");
        Assertions.assertEquals(rows_count, 2);

    }

    // Tìm với từ khóa trống
    // trả về danh sách tất cả nhà cung cấp
    @Test
    public void testTimNhaCungCapVoiDauVaoTrong(EdgeDriver driver){
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        driver.get(urlSearch);
        WebElement inputSearch = driver.findElementById("inputSearch");
        WebElement buttonSearch = driver.findElementById("btnSearch");
        inputSearch.clear();
        buttonSearch.click();


        WebElement table = driver.findElement(By.xpath("//*[@id='table_nhacungcap']/tbody"));
        List< WebElement > rows_table = table.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();

        System.out.println(rows_count + "");
        Assertions.assertEquals(driver.getTitle(),"Tìm kiếm");
        Assertions.assertEquals(rows_count, 6);
    }

    // Tìm với từ khóa không tồn tại
    @Test
        public void testTimNhaCungCapKhongCoKQ(EdgeDriver driver){
        // Đăng nhập
        driver.manage().window().maximize();
        driver.get(urlLogin);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tunguyen1601");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        driver.get(urlSearch);
        WebElement inputSearch = driver.findElementById("inputSearch");
        WebElement buttonSearch = driver.findElementById("btnSearch");
        inputSearch.sendKeys("Búa tạ");
        buttonSearch.click();


        WebElement table = driver.findElement(By.xpath("//*[@id='table_nhacungcap']/tbody"));
        List< WebElement > rows_table = table.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();

        System.out.println(rows_count + "");
        Assertions.assertEquals(driver.getTitle(),"Tìm kiếm");
        Assertions.assertEquals(rows_count, 0);
    }
}
