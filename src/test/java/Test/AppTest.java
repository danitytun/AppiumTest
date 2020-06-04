package Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;
    @Test
    public void AppiumTest() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        //Nền tảng test Android, iOS
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);

        //Thiết bị chạy test, lấy qua lệnh adb devices
        cap.setCapability(MobileCapabilityType.DEVICE_NAME,"5200391b59004583");

        //Nếu có apk file
        //cap.setCapability("app","C:/path/app.apk");

        /**Nếu không có apk file, lấy các thông số của app muốn test
         * Mở app trên device và chạy câu lệnh sau
         * adb shell dumpsys window | find "mCurrentFocus"
         */
        cap.setCapability("appPackage","com.sec.android.app.popupcalculator");
        cap.setCapability("appActivity","com.sec.android.app.popupcalculator.Calculator");

        // Khởi tạo android driver
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);

        //Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement firstNumber = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_07"));
        WebElement secondNumber = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_05"));
        WebElement operatorMul = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_mul"));
        WebElement operatorEqual = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_equal"));
        WebElement resultNumber = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_edt_formula"));

        firstNumber.click();
        operatorMul.click();
        secondNumber.click();
        operatorEqual.click();
        Assert.assertEquals("35", resultNumber.getText());
    }
}
