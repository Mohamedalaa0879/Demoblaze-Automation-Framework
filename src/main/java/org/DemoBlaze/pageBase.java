package org.DemoBlaze;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class pageBase {
   protected WebDriver driver ;
    protected WebDriverWait wait;

    public pageBase (WebDriver driver){
this.driver =  driver;
this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
     }
public  void  clicking (WebElement element){
element.click();
}
public void sendkey (WebElement element,String text){
         element.sendKeys(text);
}


}
