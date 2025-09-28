import org.DemoBlaze.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.Random;

public class TestBase {

protected static WebDriver driver;
    public static String registeredUser;
    public static String PasswordUser;
    //for placeOrder
    public static String Name;
    public static String Creditcard;

    String[] countries = {"Egypt", "USA", "UK", "Germany", "France", "Canada"};
    Random rand = new Random();
    String randomCountry = countries[rand.nextInt(countries.length)];

    String[] cities = {"Cairo", "Alexandria", "Giza", "Luxor", "Aswan", "Hurghada"};
    String randomCity = cities[rand.nextInt(cities.length)];

    String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    Random randmonth = new Random();
    String randomMonth = months[randmonth.nextInt(months.length)];



    @BeforeSuite
    public void setup (){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");

    }

    @AfterSuite
    public void quit () throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
    @DataProvider(name = "Invalid Data For Registration")
    public Object[][] getInvalidDataForRegistration() {
        return new Object[][] {
                {"", ""},                        // الاتنين فاضيين
                {"", "123456789"},               // اليوزر فاضي
                {"MOHAMED_TEST", ""},            // الباسورد فاضي
                {RegisterPageTest.registeredUser, RegisterPageTest.PasswordUser}
                // يوزر موجود بالفعل (مكرر)
        };
    }
    @DataProvider(name = "Invalid Data For Login")
    public Object[][] getInvalidDataForLogin() {
        return new Object[][] {
                {"", "", "Please fill out Username and Password."},                        // الاتنين فاضيين
                {"", RegisterPageTest.PasswordUser, "Please fill out Username and Password."},               // اليوزر فاضي
                {RegisterPageTest.registeredUser, "", "Please fill out Username and Password."},            // الباسورد فاضي
                {"wrongUser", RegisterPageTest.PasswordUser, "User does not exist."},          // يوزر غلط
                {RegisterPageTest.registeredUser, "wrongPass", "Wrong password."}    // باسورد غلط
        };
    }
    @DataProvider(name = "invalidOrderData")
    public Object[][] getInvalidOrderData() {
        return new Object[][]{
                // الحالة 1: كله فاضي
                {"", "", "", "", "", "", "Please fill out Name and Creditcard."},

                // الحالة 2: الاسم موجود – الكارت فاضي
                {"MOAHMED", "Egypt", "Cairo", "", "12", "2025", "Please fill out Name and Creditcard."},

                // الحالة 3: الكارت موجود – الاسم فاضي
                {"", "Egypt", "Cairo", "1234567890123456", "12", "2025", "Please fill out Name and Creditcard."}
        };
    }

}
