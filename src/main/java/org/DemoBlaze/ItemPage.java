package org.DemoBlaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemPage extends pageBase {
    public ItemPage(WebDriver driver) {
        super(driver);
    }

    By itemLink =  By.partialLinkText("2017 Dell 15.6 Inch");
    WebElement ItemLinkElement;
    By PriceInItemPage = By.xpath("//h3[@class='price-container']");
    String Price_In_ItemPage;
    By addToCartBtn = By.xpath("//a[text()='Add to cart']");
    WebElement AddToCartButton;


    public ItemPage clickingItemlink(String productName) {
        if (new HomePage(driver).searchForDellItem(productName)) {
         ItemLinkElement = driver.findElement(itemLink);
            System.out.println("Proceed with Dell item actions...");
            //ItemLinkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(itemLink));

            ItemLinkElement = wait.until(ExpectedConditions.elementToBeClickable(itemLink));
            clicking(ItemLinkElement); // دالة click موجودة عندك في pageBase
        } else {
            System.out.println(productName + " was not found!");
        }
        return new ItemPage(driver);
    }


    public String getPriceForDellItemInItemPage(){
        Price_In_ItemPage =driver.findElement(PriceInItemPage).getText() ;
        System.out.println(Price_In_ItemPage);
        return Price_In_ItemPage;
    }


    public void ClickinkOnAddToCartButton(){
        AddToCartButton = driver.findElement(addToCartBtn);
        clicking(AddToCartButton);
    }


    public String getAlertMessageAndAccept() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        String alert =  driver.switchTo().alert().getText();
        System.out.println(alert);
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        return alert;

    }
}
//    public ItemPage clickingItemlink (HomePage) {
//        ItemLinkElement = driver.findElement(itemLink);
//        if (homePage.searchForDellItem("2017 Dell")) {
//            System.out.println("Proceed with Dell item actions...");
//            // تقدر تضغط عليه أو تكمل خطوات تانية
//            clicking(ItemLinkElement);
//        }
//        else {
//            System.out.println("Dell item was not found!");
//            // هنا ممكن تعمل assert أو ترمي Exception
//        }
//        return new ItemPage(driver);



  //  public void  ClickOnDellItem (){
  //      DellItem = driver.findElement(dellItem);
      //  clicking(DellItem);

    // البحث باستخدام for loop مع break



