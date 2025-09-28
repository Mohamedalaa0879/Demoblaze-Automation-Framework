import org.DemoBlaze.CartPage;
import org.DemoBlaze.HomePage;
import org.DemoBlaze.ItemPage;
import org.DemoBlaze.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ItemPageTest extends TestBase {

    private LoginPage loginPage;
    private CartPage cartPage;
    private HomePage homePage;
    private ItemPage itemPage;

    @Test
    public void verifyClickinkOnDellItemWithValidPrice() throws InterruptedException {
        homePage = new HomePage(driver);
        itemPage =new ItemPage(driver);
        String ItemHomePrice = homePage.getPriceForDellItemInHomePage();
itemPage.clickingItemlink("2017 Dell 15.6 Inch");
        String ItemPagePrice =itemPage.getPriceForDellItemInItemPage();
        Assert.assertTrue(ItemPagePrice.contains(ItemHomePrice));
        Thread.sleep(3000);
        itemPage.ClickinkOnAddToCartButton();
itemPage.getAlertMessageAndAccept();
    }
}

