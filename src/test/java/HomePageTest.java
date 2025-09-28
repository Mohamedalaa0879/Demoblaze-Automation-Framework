import org.DemoBlaze.CartPage;
import org.DemoBlaze.HomePage;
import org.DemoBlaze.LoginPage;
import org.DemoBlaze.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {

private RegisterPage signupPage;
private LoginPage loginPage;
private CartPage cartPage;
private HomePage homePage;
    @BeforeClass
   public void setUpPageObjects() {
      homePage = new HomePage(driver);

    }
@Test
public void verifyMainNavBarIsDisplayedWithoutLogin (){
    Assert.assertTrue(homePage.isMainNavBarCompleteWithoutLogin(),
            "Main navigation bar is missing one or more elements!");
}
    @Test
    public void verifyMainNavBarIsEnableWithoutLogin (){
        Assert.assertTrue(homePage. isMainNavBarEnablewithoutLogin(),
                "Main navigation bar is missing one or more elements not enable!");
    }
    @Test
    public void verifyMainBarIncludesAllFunctionsWithoutLogin (){
        System.out.println(homePage.MainBarText());
        Assert.assertTrue(
                homePage.MainBarText().contains("Home")&&
                        homePage.MainBarText().contains("Contact") &&
                        homePage.MainBarText().contains("About us") &&
                       homePage.MainBarText().contains("Cart")&&
                      homePage.MainBarText().contains("Log in") &&
                       homePage.MainBarText().contains("Sign up"),
                "One or more main function are missing from the main Bar!");

    }
    @Test (dependsOnMethods = "Validlogin")
    public void verifyLogoIsDsplayed (){
        Assert.assertTrue(homePage.isLogoDisplayed());
        Assert.assertEquals(homePage.getLogoText(),"PRODUCT STORE");
        System.out.println(homePage.getLogoText());
        Assert.assertTrue(homePage.isLogoWhite());
        System.out.println(homePage.getLogoColor());
        System.out.println(homePage.isLogoWhite());

    }
    @Test (dependsOnMethods = "Validlogin")
    public void verifyCategoriesIncludesAllItems(){
        Assert.assertTrue(
                homePage.SHOWCategoryMenu().contains("CATEGORIES") &&
                        homePage.SHOWCategoryMenu().contains("Phones") &&
                        homePage.SHOWCategoryMenu().contains("Laptops") &&
                        homePage.SHOWCategoryMenu().contains("Monitors"),
                "One or more categories are missing from the menu!");
        System.out.println(homePage.SHOWCategoryMenu());
    }
    @Test (dependsOnMethods = "Validlogin")
    public void VerifyPaginationButtonsEnabled (){
        Assert.assertTrue(homePage.arePaginationButtonsEnabled());
    }
    @Test (dependsOnMethods = "Validlogin")
    public void VerifyAllFooterAppears (){
        Assert.assertTrue(homePage.isFooterDisplayed());
        System.out.println("Footer text is: " +homePage.getFooterText());
    }
    @Test (dependsOnMethods = "Validlogin")
    public void VerifyDellItemAppear (){
     //  Assert.assertTrue(homePage.isDellItemIsDisplayed());
      //  homePage.ClickOnDellItem();
    }
    @Test (dependsOnMethods = "Validlogin")
    public void verifyProductSearchAcrossPages()  {
       String targetProduct ="2017 Dell 15.6 Inch";
        boolean result = homePage.searchForDellItem(targetProduct);
       Assert.assertTrue(result,targetProduct +" not found in any page!");
    }


}
