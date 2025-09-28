package org.DemoBlaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class placeOrderModelPage  extends pageBase{
    public placeOrderModelPage(WebDriver driver) {
        super(driver);
    }

    By Title = By.id("orderModalLabel");
    String Title_Element;
    By TotalPrice = By.id("totalm");
    String TotalPrice_ELement;
    By NameField = By.id("name");
    WebElement NameField_ELement;
    By CountryField = By.id("country");
    WebElement CountryField_ELement;
    By CityField = By.id("city");
    WebElement CityField_ELement;
    By CreditcardField = By.id("card");
    WebElement Creditcard_ELement;
    By monthField = By.id("month");
    WebElement monthField_ELement;
    By yearField = By.id("year");
    WebElement yearField_ELement;
    By PurchaseBtn = By.xpath("//button[text()='Purchase']");
    WebElement PurchaseBtn_Element;
    By CloseBtn = By.xpath("//*[@id=orderModal]/div/div/div[3]/button[1]");
    WebElement CloseBtn_Element;
   By xICONBtn = By.xpath("//div[@id='orderModal']//button[@class='close']");
    // //*[@id="orderModal"]/div/div/div[1]/button
   WebElement xICONBtn_Element;

    /// successfully Message Loactor

    By SuccessfullyMsg = By.xpath("//h2[contains(text(),'Thank you for your purchase!')]");
    String SuccessfullyMsg_Element;
    By Detalis = By.xpath("//p[@class='lead text-muted ']");
    WebElement Detalis_Element;
    By ID = By.xpath("//p[@class='lead text-muted ']/text()[1]");
    String ID_Element;
    By Amount = By.xpath("//p[@class='lead text-muted ']/text()[2]");
    String Amount_Element;
    By CardNumber = By.xpath("//p[@class='lead text-muted ']/text()[3]");
    String CardNumber_Element;
    By Name = By.xpath("//p[@class='lead text-muted ']/text()[4]");
    String Name_Element;
    By Date = By.xpath("//p[@class='lead text-muted ']/text()[5]");
    String Date_Element;
    By OkBtn = By.xpath("//button[@class='confirm btn btn-lg btn-primary']");
    WebElement OkBtn_Element;

//    By Logout = By.id("logout2");
//    String logout;

    public String getHeadTitle(){
        Title_Element =  driver.findElement(Title).getText();
        System.out.println(Title_Element);
        return Title_Element;
    }
    public int getTotalPriceBeforeorder(){
        TotalPrice_ELement =  driver.findElement(TotalPrice).getText().trim();
        System.out.println(TotalPrice_ELement);
        // عملت كده غلشان االرقم طالع حروف وارقام وانا عاوز اقارنه برقم طالع ارقام بس
        String numberOnly = TotalPrice_ELement.replaceAll("[^0-9]", ""); // يبقى بس "700"
      // بعد كده حولته من حرف او ارقام
        return Integer.parseInt(numberOnly);
    }
    public void EnterName (String name){
        NameField_ELement =driver.findElement(NameField);
        NameField_ELement.click();
        NameField_ELement.clear();
        sendkey(NameField_ELement,name);

    }
    public void EnterCountry (String Country){
        CountryField_ELement =driver.findElement(CountryField);
        CountryField_ELement.clear();
        CountryField_ELement.click();
        sendkey( CountryField_ELement,Country);
    }
    public void EnterCity (String City){
        CityField_ELement =driver.findElement(CityField);
        CityField_ELement.clear();
        CityField_ELement.click();
        sendkey( CityField_ELement,City);
    }
    public void EnterCreditcard (String Creditcard){
        Creditcard_ELement =driver.findElement(CreditcardField);
        Creditcard_ELement.clear();
        Creditcard_ELement.click();
        sendkey( Creditcard_ELement,Creditcard);
    }
    public void EnterMonth (String Month){
        monthField_ELement =driver.findElement(monthField);
        monthField_ELement.clear();
        monthField_ELement.click();
        sendkey( monthField_ELement,Month);
    }
    public void EnterYear (String Year){
        yearField_ELement =driver.findElement(yearField);
        yearField_ELement.clear();
        yearField_ELement.click();
        sendkey( yearField_ELement,Year);
    }
    public void clickingPurchaseButton(){
        PurchaseBtn_Element =driver.findElement(PurchaseBtn);
        clicking(PurchaseBtn_Element);
    }
    public String getAlertMessageForInvalidData() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());   // ✅ استنى لحد ما الـ
        String alert =  driver.switchTo().alert().getText();
        System.out.println(alert);
        driver.switchTo().alert().accept();
        return alert;

    }
    public void resetAfterAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // هستنى لحد ما زرار الكلوز يبقى ظاهر
            wait.until(ExpectedConditions.visibilityOfElementLocated(xICONBtn));

            // هدوس على زرار الكلوز
            xICONBtn_Element = driver.findElement(xICONBtn);
            xICONBtn_Element.click();

            // استنى لحد ما المودال يختفي
            //    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("signInModal")));

        } catch (Exception e) {
            // لو المودال مش مفتوح أصلاً → طنش
        }
    }
    public String getConfirmationMessageAfterComleteOrder(){
        SuccessfullyMsg_Element =  driver.findElement(SuccessfullyMsg).getText();
    //    System.out.println(SuccessfullyMsg_Element);
        return SuccessfullyMsg_Element;
    }
    private String[] getDetailsParts() {
        Detalis_Element = driver.findElement(Detalis);
        String allDetails = Detalis_Element.getText().trim();
       // System.out.println("Full Details: \n" + allDetails);
        return allDetails.split("\n");
    }
    public String getOrderId(){
        String idLine = getDetailsParts()[0];   // Id: 1553782
        System.out.println(idLine);
        return idLine.replace("Id: ", "").trim();
    }

    public int getAmount(){
        String amountLine = getDetailsParts()[1]; // Amount: 0 USD
       System.out.println(amountLine);
        String numberOnly = amountLine.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberOnly);
    }
    public String getCardNumber(){
        String cardLine = getDetailsParts()[2]; // Card Number: سيس
      System.out.println(cardLine);
        return cardLine.replace("Card Number: ", "").trim();
    }
    public String getName(){
        String nameLine = getDetailsParts()[3]; // Name: يسي
       System.out.println(nameLine);
        return nameLine.replace("Name: ", "").trim();
    }
    public  String getDate(){
        String dateLine = getDetailsParts()[4]; // Date: 24/8/2025
        System.out.println(dateLine);
        return dateLine.replace("Date: ", "").trim();
    }
    public void clickingOnOkButton(){
        OkBtn_Element =driver.findElement(OkBtn);
        clicking(OkBtn_Element);
    }


}
