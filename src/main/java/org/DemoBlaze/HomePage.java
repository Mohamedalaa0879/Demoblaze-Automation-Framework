package org.DemoBlaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage  extends pageBase {

    public HomePage(WebDriver driver) {
        super(driver);

    }
    By signUPButton = By.id("signin2");
    WebElement SignupButtonElement;
    By signUpModel = By.xpath("//div[@class='modal-content']");
    By loginButton = By.id("login2");
    WebElement LoginButtonElement;
    By cartButton = By.id("cartur");
    WebElement CartButtonElement;
   // By itemLink =  By.partialLinkText("2017 Dell 15.6 Inch");
 //   WebElement ItemLinkElement;
    // هيكون فيها get text
    By category =  By.xpath("//*[@id=\"contcont\"]/div/div[1]");
    String CategoryElement;
    By logo =  By.id("nava");
    String Logo;
    WebElement Logoo;
    By HomeLink = By.partialLinkText("Home");
     By ContactLink = By.partialLinkText("Contact");
     By AboutUsLink = By.partialLinkText("About us");
    By MainBar = By.xpath("//*[@id='navbarExample']/ul");
By Footer = By.id("footc");
    By nextButton = By.xpath("//button[@id='next2' and not(contains(@style,'display: none'))]");
    By previousButton = By.id("prev2");
Boolean NextButton;
WebElement NextBtn;
Boolean PreviousButton;
    //By dellItem = By.partialLinkText("2017 Dell 15.6 ");
//WebElement DellItem ;
// ده اللي فيه كل اللابات والموبايلات  والشاشات
By productsContainer = By.id("tbodyid");
By PriceInHomePage = By.xpath("//*[@id=\"tbodyid\"]/div[4]/div/div/h5");
String Price_In_HomePage;

    public RegisterPage clickingOnSignupButton () {
    SignupButtonElement = driver.findElement(signUPButton);
    clicking(SignupButtonElement);
    return new RegisterPage(driver);
}
    public String SignUpModel() {
        return  driver.findElement(signUpModel).getText();
    }

    public LoginPage clickingOnLoginButton () {
        LoginButtonElement = driver.findElement(loginButton);
        clicking(LoginButtonElement);
        return new LoginPage(driver);
    }
    public CartPage clickingOnCartButton () {
        CartButtonElement = driver.findElement(cartButton);
        clicking(CartButtonElement);
        return new CartPage(driver);
    }
    public CartPage clickingOnHomePage () {
       WebElement HomeLinkElement = driver.findElement(HomeLink);
        clicking(HomeLinkElement);
        return new CartPage(driver);
    }
  //  public ItemPage clickingItemlink () {
     //   ItemLinkElement = driver.findElement(itemLink);
     //   clicking(ItemLinkElement);
      //  return new ItemPage(driver);
  //  }
    public String SHOWCategoryMenu () {
       return   CategoryElement   = driver.findElement(category).getText();
    }
    public String getLogoText () {
       return   Logo  = driver.findElement(logo).getText();
       // clicking(Logoo);
    }
    public String getLogoColor() {
       return Logo  = driver.findElement(logo).getCssValue("color");

    }
    public boolean isLogoWhite() {
        String color = getLogoColor();
        return color.equals("rgba(255, 255, 255, 1)");

    }
    public boolean isLogoDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }
    public boolean isHomeDisplayed() {
        return driver.findElement(HomeLink).isDisplayed();
    }
    public boolean isContactDisplayed() {
        return driver.findElement(ContactLink).isDisplayed();
    }

    public boolean isAboutUsDisplayed() {
        return driver.findElement(AboutUsLink).isDisplayed();
    }

    public boolean isCartDisplayed() {
        return driver.findElement(cartButton).isDisplayed();
    }
    public boolean isLoginDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public boolean isSignUpDisplayed() {
        return driver.findElement(signUPButton).isDisplayed();
    }
    public boolean isMainNavBarCompleteWithoutLogin() {
        return isHomeDisplayed()
                && isContactDisplayed()
                && isAboutUsDisplayed()
                && isCartDisplayed()
                && isLoginDisplayed()
                && isSignUpDisplayed();

}
public boolean isLogoEnable() {
    return driver.findElement(logo).isDisplayed();
}
public boolean isHomeenable() {
    return driver.findElement(HomeLink).isDisplayed();
}
public boolean isContactenable() {
    return driver.findElement(ContactLink).isDisplayed();
}

public boolean isAboutUsenable() {
    return driver.findElement(AboutUsLink).isDisplayed();
}

public boolean isCartenable() {
    return driver.findElement(cartButton).isDisplayed();
}
public boolean isLoginenable() {
    return driver.findElement(loginButton).isDisplayed();
}

public boolean isSignUpenable() {
    return driver.findElement(signUPButton).isDisplayed();
}
public boolean isMainNavBarEnablewithoutLogin() {
    return isHomeenable()
            && isContactenable()
            && isAboutUsenable()
            && isCartenable()
            && isLoginenable()
            && isSignUpenable();
}
//    public boolean isMainNavBarEnablewithLoginSuccessfully() {
//        return isHomeenable()
//                && isContactenable()
//                && isAboutUsenable()
//                && isCartenable()
//                && isLoginenable()
//                && isSignUpenable();
//    }

    public String MainBarText() {
        return  driver.findElement(MainBar).getText();
    }
    public String getFooterText (){

        return driver.findElement(Footer).getText();
    }
    public boolean isFooterDisplayed(){
    return driver.findElement(Footer).isDisplayed();
    }
    public boolean arePaginationButtonsEnabled(){
        NextButton= driver.findElement(nextButton).isEnabled();
        PreviousButton= driver.findElement(previousButton).isEnabled();
        return NextButton &&  PreviousButton;
    }
   public boolean isDellItemIsDisplayed(){
       wait.until(ExpectedConditions.visibilityOfElementLocated(productsContainer));
       System.out.println(driver.findElement(productsContainer).getText().contains("2017 Dell"));
       return driver.findElement(productsContainer).getText().contains("2017 Dell");
   }

    public void ClickOnNextButton() {
        WebElement nextBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(nextButton));
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
        clicking(nextBtn);
    }

    //  public void  ClickOnDellItem (){
    //    DellItem = driver.findElement(dellItem);
     //   clicking(DellItem);
   // }
    // البحث باستخدام for loop مع break
  public boolean searchForDellItem(String productName) {
      wait.until(ExpectedConditions.visibilityOfElementLocated(productsContainer));

      for (int page = 1; ; page++) {
          // هل الـ Dell ظاهر في الصفحة الحالية؟
          if (isDellItemIsDisplayed()) {
              System.out.println("Dell item found on page: " + page);
              return true;
          }

          // تحقق من حالة زر Next
          List<WebElement> nextButtons = driver.findElements(nextButton);
          if (nextButtons.isEmpty() || !nextButtons.get(0).isDisplayed() || !nextButtons.get(0).isEnabled()) {
              System.out.println("Dell item not found after page: " + page);
              break;
          }

          // اضغط Next
          ClickOnNextButton();

      }
      return false;
  }
  public String getPriceForDellItemInHomePage(){
      Price_In_HomePage =driver.findElement(PriceInHomePage) .getText();
      System.out.println(Price_In_HomePage);
      return Price_In_HomePage;
  }

}








