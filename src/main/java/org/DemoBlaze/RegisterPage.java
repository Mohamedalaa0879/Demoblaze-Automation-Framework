package org.DemoBlaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends pageBase {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    By UserNameField = By.id("sign-username");
    WebElement UserName;
    By PasswordField = By.id("sign-password");
    WebElement Password;
    By SignUpButton = By.xpath("//button[@class='btn btn-primary' and @onclick='register()']");
    WebElement SignUpBtn;
    By CloseButton = By.xpath("//button[@class='btn btn-secondary' and @data-dismiss='modal']");
    WebElement CloseBtn;
    By PlaceHolderUserName = By.xpath("//label[@for='sign-username' ]");
    By PlaceHolderPassword = By.xpath("//label[@for='sign-password' ]");
    By CloseIcon = By.xpath("//div[@id='signInModal']//button[@class='close' and @data-dismiss='modal']");
    WebElement CloseIco;

    public void enterUserName (String name){
        UserName =driver.findElement(UserNameField);
        UserName.click();
        UserName.clear();
        sendkey(UserName,name);

    }
    public void enterPassword (String password){
        Password =driver.findElement(PasswordField);
        Password.clear();
        Password.click();
        sendkey(Password,password);
}
    public void clickingOnSignUpButton(){
        SignUpBtn =driver.findElement(SignUpButton);
        clicking(SignUpBtn);
    }
//    public void clickingOnCloseButton(){
//        CloseBtn =driver.findElement(CloseButton);
//        clicking(CloseBtn);
//    }
    public String getPlaceHolder(){
        String PlaceHolderUserName1 = driver.findElement(PlaceHolderUserName).getText();
        String PlaceHolderPassword1 = driver.findElement(PlaceHolderPassword).getText();
return PlaceHolderUserName1 + " | " +  PlaceHolderPassword1;
    }

    public String getAlertMessageAndAccept() throws InterruptedException {
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
            wait.until(ExpectedConditions.visibilityOfElementLocated(CloseIcon));

            // هدوس على زرار الكلوز
            CloseIco = driver.findElement(CloseIcon);
            CloseIco.click();

            // استنى لحد ما المودال يختفي
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("signInModal")));

        } catch (Exception e) {
            // لو المودال مش مفتوح أصلاً → طنش
        }
    }



}


