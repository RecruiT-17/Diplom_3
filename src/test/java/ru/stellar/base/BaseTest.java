package ru.stellar.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Properties;

public abstract class BaseTest {

    protected WebDriver driver;
    protected static Properties config = new Properties();

    private static final String YANDEX_DRIVER_PATH =
            "C:\\Users\\Farruh\\Downloads\\yandexdriver-26.8.0.1788-win64\\yandexdriver.exe";

    static {
        try (InputStream in = BaseTest.class
                .getClassLoader()
                .getResourceAsStream("driver-config.properties")) {
            if (in != null) {
                // Читаем в UTF-8 на случай русских комментариев в файле
                config.load(new InputStreamReader(in, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить driver-config.properties", e);
        }
    }

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        Allure.parameter("Браузер", browser);

        if ("yandex".equals(browser)) {
            driver = createYandexDriver();
        } else {
            driver = createChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(config.getProperty("base.url"));
    }

    private WebDriver createChromeDriver() {
        System.clearProperty("webdriver.chrome.driver");

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private WebDriver createYandexDriver() {
        String yandexPath = config.getProperty("yandex.binary.path");
        File browserFile = new File(yandexPath);
        if (!browserFile.exists()) {
            throw new IllegalStateException(
                    "Yandex Browser не найден: " + yandexPath
                            + "\nПроверьте yandex.binary.path в driver-config.properties.");
        }

        File yandexDriverFile = new File(YANDEX_DRIVER_PATH);
        if (!yandexDriverFile.exists()) {
            throw new IllegalStateException(
                    "YandexDriver не найден: " + YANDEX_DRIVER_PATH
                            + "\nПроверьте, что файл yandexdriver.exe лежит по этому пути.");
        }

        System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexPath);
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Attachment(value = "Скриншот", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}