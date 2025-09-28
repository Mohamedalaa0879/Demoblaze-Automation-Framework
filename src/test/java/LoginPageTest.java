import org.DemoBlaze.HomePage;
import org.DemoBlaze.LoginPage;
import org.DemoBlaze.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPageTest extends TestBase{
    private RegisterPage signupPage;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpPageObjects() {
      homePage = new HomePage(driver);
        loginPage = homePage.clickingOnLoginButton(); // لازم تعملها هنا كمان

    }
    @AfterMethod
    public void resetAfterEachTest() {
        loginPage.resetAfterAlert();   // ← استدعاء الـPage Object method
    }
    @Test(priority = 2,dependsOnMethods = "ValidRegisteration")
    public void Validlogin() throws InterruptedException {

        Thread.sleep(1000);
        // هننا علشان اول ميضغط علي registrer page ويدخلني لصفحة registerابقي كده نقلت التحكم كله للregister page
        System.out.println(loginPage.getPlaceHolder());
        Assert.assertEquals(loginPage.getPlaceHolder(),"Username: | Password:");
        Thread.sleep(1000);

        loginPage.enterUserName(RegisterPageTest.registeredUser);
        loginPage.enterPassword(RegisterPageTest.PasswordUser);
        loginPage.clickingOnLoginButton();
      String actualText = loginPage.getNameTitleAfterLogin();
        String actualLogoutText = loginPage.getLogoutTextAfterLogin();

    Assert.assertEquals(actualText,"Welcome "+RegisterPageTest.registeredUser,"Expected username not displayed after login!");
        Assert.assertEquals(actualLogoutText,"Log out");
Assert.assertTrue(loginPage.isNameTitleEnabled()&&loginPage.isLogoutEnabled());

    }
    @Test (priority = 1, dataProvider = "Invalid Data For Login")

    public void invalidLogin(String userName ,String Password,String ExpectedMessage) throws InterruptedException {
        // signupPage = homePage.clickingOnSignupButton(); // لازم تعملها هنا كمان

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Thread.sleep(1000);
        loginPage.enterUserName(userName);
        Thread.sleep(1000);
        loginPage.enterPassword(Password);
        loginPage.clickingOnLoginButton();

        String alertMessage = loginPage.getAlertMessageForInvalidData();
        System.out.println("Alert: " + alertMessage);
        // اعمل assert حسب السيناريو
        if (userName.isEmpty() || Password.isEmpty()) {
            Assert.assertEquals(alertMessage, ExpectedMessage);
        } else if (userName.equals(registeredUser) && !Password.equals(PasswordUser)) {
            // يوزر صح + باسورد غلط
            Assert.assertEquals(alertMessage, ExpectedMessage,
                    "Expected 'Wrong password' message not shown!");
        } else {
            // أي حاجة تانية معناها يوزر مش موجود
            Assert.assertEquals(alertMessage, ExpectedMessage);
        }

    }
}