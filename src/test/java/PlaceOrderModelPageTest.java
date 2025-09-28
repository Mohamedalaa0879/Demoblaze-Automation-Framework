import org.DemoBlaze.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class PlaceOrderModelPageTest  extends TestBase{
private CartPage cartPage;
private HomePage homePage;
private ItemPage itemPage;
private placeOrderModelPage placeOrder_ModelPage;

 //private final SoftAssert softAssert =new SoftAssert();

@BeforeMethod
public void setUpPageObjects() {
    homePage = new HomePage(driver);
    cartPage =new CartPage(driver);

    placeOrder_ModelPage  =  cartPage.clickingOnPlaceOrderButton();

}
@AfterMethod
public void resetAfterEachTest() {
    placeOrder_ModelPage.resetAfterAlert();   // ← استدعاء الـPage Object method
}
@Test (priority = 2,dependsOnMethods = "verifyDeleteAndReAddProductToCart")
public void ValidOrder() throws InterruptedException {
    Name = "Name_" + System.currentTimeMillis();
    Creditcard= "card" + System.currentTimeMillis();
    Thread.sleep(2000);
    // هننا علشان اول ميضغط علي registrer page ويدخلني لصفحة registerابقي كده نقلت التحكم كله للregister page
//    System.out.println(placeOrder_ModelPage.getHeadTitle());
    Assert.assertEquals(placeOrder_ModelPage.getHeadTitle(),"Place order");
    Thread.sleep(2000);
    int priceInCart = cartPage.getTotaLPriceValueFromCart();
    int priceInModal = placeOrder_ModelPage.getTotalPriceBeforeorder();
 Assert.assertEquals(priceInModal,priceInCart,"Price in place order modal does not match the price in cart!");

placeOrder_ModelPage.EnterName(Name);
   Thread.sleep(1000);
    placeOrder_ModelPage.EnterCountry(randomCountry);
    Thread.sleep(1000);
    placeOrder_ModelPage.EnterCity(randomCity);
    Thread.sleep(1000);
    placeOrder_ModelPage.EnterCreditcard(Creditcard);
    Thread.sleep(1000);
    placeOrder_ModelPage.EnterMonth(randomMonth);
    Thread.sleep(1000);
    placeOrder_ModelPage.EnterYear("2025");
    Thread.sleep(1000);
    placeOrder_ModelPage.clickingPurchaseButton();
    Thread.sleep(3000);

    String ConfirmationMessage= placeOrder_ModelPage.getConfirmationMessageAfterComleteOrder();
    String actualId = placeOrder_ModelPage.getOrderId();
    String ActualName= placeOrder_ModelPage.getName();
    String actualCreditCard = placeOrder_ModelPage.getCardNumber();
    String actualDate = placeOrder_ModelPage.getDate(); // تاريخ اليوم أو اللي ظهر في الكونفرميشن
    int actualAmount = placeOrder_ModelPage.getAmount();
    Assert.assertTrue(ConfirmationMessage.contains("Thank you for your purchase"));
    Assert.assertEquals(ActualName,Name);
    Assert.assertEquals(actualCreditCard, Creditcard, "Credit card in confirmation does not match the entered credit card.");
   Assert.assertEquals(actualAmount, priceInCart, "Price in confirmation does not match cart total.");
    Assert.assertNotNull(actualId, "Order ID should not be null.");
    Assert.assertFalse(actualId.isEmpty(), "Order ID should be unique and not empty.");
    //
    Assert.assertEquals(actualDate, java.time.LocalDate.now().toString(), "Date in confirmation does not match today's date.");

    //  softAssert.assertAll();

    placeOrder_ModelPage.clickingOnOkButton();

}
    @Test (priority = 1,dependsOnMethods = "verifyDeleteAndReAddProductToCart",dataProvider = "invalidOrderData")
    public void invalidOrder(String name,
                             String country,
                             String city,
                             String creditCard,
                             String month,
                             String year,
                             String expectedMessage) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        placeOrder_ModelPage.EnterName(name);
        placeOrder_ModelPage.EnterCountry(country);
        placeOrder_ModelPage.EnterCity(city);
        placeOrder_ModelPage.EnterCreditcard(creditCard);
        placeOrder_ModelPage.EnterMonth(month);
        placeOrder_ModelPage.EnterYear(year);
        placeOrder_ModelPage.clickingPurchaseButton();

        String alertMessage = placeOrder_ModelPage.getAlertMessageForInvalidData();
        System.out.println("Alert: " + alertMessage);
        // اعمل assert حسب السيناريو
        if (name.isEmpty() || creditCard.isEmpty()) {
            Assert.assertEquals(alertMessage, expectedMessage);
        }
        else {
                // أي حاجة تانية معناها البيانات غير صحيحة
                Assert.assertEquals(alertMessage, expectedMessage);

        }

    }
}