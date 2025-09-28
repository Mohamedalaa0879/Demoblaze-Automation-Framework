package org.DemoBlaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends pageBase{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    By UserNameField = By.id("loginusername");
    WebElement UserName;
    By PasswordField = By.id("loginpassword");
    WebElement Password;
    By LoginButton = By.xpath("//button[@class='btn btn-primary' and @onclick='logIn()']");
    WebElement LoginpBtn;
    By CloseButton = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[1]");
    WebElement CloseBtn;
    By PlaceHolderUserName = By.xpath("//label[@for='log-name' ]");
    By PlaceHolderPassword = By.xpath("//label[@for='log-pass' ]");
    By CloseIcon = By.xpath("//button[@type='button' and contains(@class,'close')]/span[@aria-hidden='true']\n");
    WebElement CloseIco;
    By NameOfUser = By.id("nameofuser");
    String nameOfUser;
    By Logout = By.id("logout2");
    String logout;
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
    public void clickingOnLoginButton(){
        LoginpBtn =driver.findElement(LoginButton);
        clicking(LoginpBtn);
    }
    public String getPlaceHolder(){
        String PlaceHolderUserName1 = driver.findElement(PlaceHolderUserName).getText();
        String PlaceHolderPassword1 = driver.findElement(PlaceHolderPassword).getText();
        return PlaceHolderUserName1 + " | " +  PlaceHolderPassword1;
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
            wait.until(ExpectedConditions.visibilityOfElementLocated(CloseButton));

            // هدوس على زرار الكلوز
            CloseBtn = driver.findElement(CloseButton);
            CloseBtn.click();

            // استنى لحد ما المودال يختفي
        //    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("signInModal")));

        } catch (Exception e) {
            // لو المودال مش مفتوح أصلاً → طنش
        }
    }
    public String getNameTitleAfterLogin(){
        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(NameOfUser));
        nameOfUser =nameElement.getText();
        System.out.println(nameOfUser);
        return nameOfUser;
    }
    public boolean isNameTitleEnabled() {
        return driver.findElement(NameOfUser).isEnabled();
    }
    public String getLogoutTextAfterLogin(){
        logout = driver.findElement(Logout).getText();
        System.out.println(nameOfUser);
        return logout;
    }
    public boolean isLogoutEnabled() {
        return driver.findElement(Logout).isEnabled();
    }

}
