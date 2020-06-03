package org.example;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class LoginTest {
    public static SearchPage searchPage;
    public static MailPage mailPage;
    public static LoginPage loginPage;
    public static WebDriver driver;
    public static String genlogin;
    public static boolean resTest;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        searchPage = new SearchPage(driver);
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://yandex.ru/");
    }

    public static String generateLogin(int len) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwyz";
        String num = "0123456789";
        String loginSymbols = chars + num;
        Random rnd = new Random();
        char[] login = new char[len];
        for (int i = 0; i < len; i++) {
            login[i] = loginSymbols.charAt(rnd.nextInt(loginSymbols.length()));
        }
        String str = new String(login);
        return str;
    }


    @Test
    public void loginTest() {
        resTest = false;
        // генерация уникального логина, длинна логина указывается как параметр
        genlogin = generateLogin(8);
        //вводим поисковый запрос
        searchPage.inputRequest("Яндекс почта");
        //нажимаем кнопку поиска
        searchPage.clickSearchBtn();
        //ищем первый эллемент соответсвующий строке и переходим на него
        searchPage.GoSearchItem("Яндекс.Почта — бесплатная электронная почта");
        // Поиск кнопки "Войти" скрул страницы
        mailPage.clickEnterBtn();
        // Ввод уникального логина
        loginPage.inputLogin(genlogin);
        //нажимаем кнопку войти
        loginPage.clickLoginBtn();
        //проверка что логина нет
        loginPage.ErrLogin("Такого аккаунта нет");
        resTest = true;
    }

    // закрытие окна браузера
    @After
    public void tearDown() {
        //driver.quit();
        File folder = new File("results");
        if (!folder.exists()) {
            folder.mkdir();
        }
        String res;
        if (resTest) {res = "passed";} else {res = "failed";}
        String data = "<test>\n" +
                "   <name>yandex</name>\n" +
                "   <login>" + genlogin + "</login>\n" +
                "   <date>" + new Date().toString() +"</date>\n" +
                "   <result>" + res + "</result>\n" +
                "</test>";

        File file = new File(folder.getAbsolutePath() + File.separator + "result.xml");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


