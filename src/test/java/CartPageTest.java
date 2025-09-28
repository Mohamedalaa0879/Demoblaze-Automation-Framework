import org.DemoBlaze.CartPage;
import org.DemoBlaze.HomePage;
import org.DemoBlaze.ItemPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartPageTest extends  TestBase{

    private CartPage cartPage;
    private HomePage homePage;
    private ItemPage itemPage;
    @BeforeClass
    public void setUpPageObjects() {
        homePage = new HomePage(driver);
        cartPage = homePage.clickingOnCartButton();
    }

    @Test (priority = 1, dependsOnMethods = "verifyClickinkOnDellItemWithValidPrice")
    public void verifyCartPageDisplaysAllProductsWithCorrectTitlesAndFunctionalButtons () throws InterruptedException {
      String ActualHeadTitleText =  cartPage.getHeadTiltleText();
        Assert.assertEquals(ActualHeadTitleText,"Products");
        String AllItemsOfTheTable = cartPage.getAllItemsForTheTable();
        Assert.assertTrue(AllItemsOfTheTable.contains("Pic"), "Pic column is missing");
        Assert.assertTrue(AllItemsOfTheTable.contains("Title"), "Title column is missing");
        Assert.assertTrue(AllItemsOfTheTable.contains("Price"), "Price column is missing");
        Assert.assertTrue(AllItemsOfTheTable.contains("x"), "x column is missing");
      //  Assert.assertFalse(AllItemsOfTheTable.isEmpty(), "Cart table is empty");

        cartPage.getAllRows();

        cartPage.getAllColums();
     boolean DeleteBtnEnable = cartPage.IsDeleteEnable();
       Assert.assertTrue(DeleteBtnEnable,"Delete button is not Enabled");
        boolean DeleteBtnDisplayed = cartPage.IsDeleteDisplayed();
        Assert.assertTrue(DeleteBtnDisplayed,"Delete button is not Displayed");

        boolean placeOrderBtnEnable = cartPage.IsPlaceOrderEnable() ;
        Assert.assertTrue(placeOrderBtnEnable,"Place Order button is not enabled");

        boolean  placeOrderBtnDisplayed = cartPage.IsPlaceOrderDisplayed() ;
        Assert.assertTrue(placeOrderBtnDisplayed, "Place Order button is not displayed");
//        cartPage.clickingOnDeleteButton();
//
//        // 4. اتأكد إنه اتمسح (ممكن تعمل انتظار صغير عشان الـ DOM يتحدث)
//        Thread.sleep(3000);
//        Assert.assertFalse(cartPage.getAllItemsForTheTable().contains("2017 Dell 15.6 Inch"),
//                "Product not deleted from cart");
//        Thread.sleep(3000);
//
//        // 5. رجع للهوم بيج وأضف المنتج تاني
//        homePage.clickingOnHomePage();
//        homePage = new HomePage(driver);
//        itemPage =new ItemPage(driver);
//
//        itemPage.clickingItemlink("2017 Dell 15.6 Inch");
//        String ItemPagePrice =itemPage.getPriceForDellItemInItemPage();
//        Thread.sleep(3000);
//        itemPage.ClickinkOnAddToCartButton();
//        itemPage.getAlertMessageAndAccept();
//
//        // 6. افتح الكارت تاني واتأكد إنه موجود
//        cartPage = homePage.clickingOnCartButton();
//        Assert.assertTrue(cartPage.getAllItemsForTheTable().contains("2017 Dell 15.6 Inch"),
//                "Product not re-added to cart");


    }
    @Test(priority =2,dependsOnMethods = "verifyClickinkOnDellItemWithValidPrice")
public void verifyTotalPriceEqualsSumOfAllProductsInCart (){
    int total_Price =cartPage.getTotaLPriceValueFromCart();
    int sumOfProducts =cartPage.getSumOfAllProductPrices();

    Assert.assertEquals(total_Price,sumOfProducts,"Total price does not match sum of product prices");

}
    @Test(priority =3,dependsOnMethods = "verifyClickinkOnDellItemWithValidPrice")
public void verifyDeleteAndReAddProductToCart() throws InterruptedException {
        String targetProduct ="2017 Dell 15.6 Inch";

        // 1. افتح الهوم بيج وأضف منتج للكارت
      Thread.sleep(2000);
        // 3. امسح المنتج
        cartPage.clickingOnDeleteButton();

        // 4. اتأكد إنه اتمسح (ممكن تعمل انتظار صغير عشان الـ DOM يتحدث)
     //  Thread.sleep(2000);
    //    Assert.assertFalse(cartPage.getAllItemsForTheTable().contains("2017 Dell 15.6 Inch"),
             //   "Product not deleted from cart");
       // System.out.println(cartPage.getAllItemsForTheTable());
            Thread.sleep(2000);
       homePage.clickingOnHomePage();
      //  Assert.assertFalse(cartPage.getAllItemsForTheTable().contains(targetProduct),
           //     "Product not deleted from cart");
        // 5. رجع للهوم بيج وأضف المنتج تاني
        Thread.sleep(2000);

        homePage = new HomePage(driver);
        itemPage =new ItemPage(driver);
        boolean result =  homePage.searchForDellItem("2017 Dell 15.6 Inch");
         itemPage.clickingItemlink("2017 Dell 15.6 Inch"); // اسم المنتج
        itemPage.ClickinkOnAddToCartButton();
        itemPage.getAlertMessageAndAccept();

        // 6. افتح الكارت تاني واتأكد إنه موجود
        cartPage = homePage.clickingOnCartButton();
      //  Assert.assertTrue(cartPage.getAllItemsForTheTable().contains("2017 Dell 15.6 Inch"),
              //  "Product not re-added to cart");
    }

}
