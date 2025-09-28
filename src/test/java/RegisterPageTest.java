import org.DemoBlaze.HomePage;
import org.DemoBlaze.LoginPage;
import org.DemoBlaze.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class RegisterPageTest extends TestBase {
    private RegisterPage signupPage;
    private HomePage homePage;
    private LoginPage loginPage;

@BeforeMethod
public void setUpPageObjects() {

      homePage = new HomePage(driver);
    signupPage = homePage.clickingOnSignupButton(); // لازم تعملها هنا كمان

}

    @AfterMethod
    public void resetAfterEachTest() {
        signupPage.resetAfterAlert();   // ← استدعاء الـPage Object method
    }

    @Test (priority = 1)
    public void ValidRegisteration() throws InterruptedException {

        Thread.sleep(2000);
        // هننا علشان اول ميضغط علي registrer page ويدخلني لصفحة registerابقي كده نقلت التحكم كله للregister page
        System.out.println(signupPage.getPlaceHolder());
        Assert.assertEquals(signupPage.getPlaceHolder(),"Username: | Password:");
        registeredUser = "User_" + System.currentTimeMillis();
        PasswordUser= "pass_" + System.currentTimeMillis();
        signupPage.enterUserName(registeredUser);
        Thread.sleep(2000);
        signupPage.enterPassword(PasswordUser);
        Thread.sleep(2000);
        System.out.println(registeredUser  +  PasswordUser);

        signupPage.clickingOnSignUpButton();
        Thread.sleep(2000);
        String actualMessage = signupPage.getAlertMessageAndAccept();
        Assert.assertEquals(actualMessage, "Sign up successful.", "Unexpected message for new user!");
    }
    @Test (priority = 2, dataProvider = "Invalid Data For Registration")

    public void invalidRegistration(String userName ,String Password) throws InterruptedException {
       // signupPage = homePage.clickingOnSignupButton(); // لازم تعملها هنا كمان

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        signupPage.enterUserName(userName);
        signupPage.enterPassword(Password);
        signupPage.clickingOnSignUpButton();

        String alertMessage = signupPage.getAlertMessageAndAccept();
        System.out.println("Alert: " + alertMessage);
        // اعمل assert حسب السيناريو
        if (userName.isEmpty() || Password.isEmpty()) {
            Assert.assertEquals(alertMessage, "Please fill out Username and Password.",
                    "Expected empty fields message not shown!");
        } else {
            Assert.assertEquals(alertMessage, "This user already exist.",
                    "Expected 'user already exists' message not shown!");
        }
//signupPage.resetAfterAlert();

    }
}
