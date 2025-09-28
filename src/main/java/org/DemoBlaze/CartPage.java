package org.DemoBlaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends pageBase {
    public CartPage(WebDriver driver) {
        super(driver);
    }
    By headTitle = By.xpath("//h2[text()='Products']");
    WebElement head_Title_Element;
    By MyTable = By.xpath("//table[@class='table table-bordered table-hover table-striped']\n");
    WebElement MyTable_Element;
    By rows = By.tagName("tr");
    List <WebElement> Rows_Element;
    By Coulums = By.tagName("th");
    List<WebElement> coloums_Element;
    By DeleteButton = By.xpath("//a[contains(text(),'Delete')]");

    WebElement Delete_Button_Element;
    By PlaceOrderButton = By.xpath("//button[text()='Place Order']");
    WebElement Place_Order_Button_Element;
    By TotalPriceValue = By.id("totalp");
    WebElement Total_Price_Value_Element;
    By SUMofPriceForAllItems = By.xpath("//table[@class='table table-bordered table-hover table-striped']/tbody/tr/td[3]");
    List<WebElement> SUM_of_Price_For_AllItems;


    public String getHeadTiltleText (){
        head_Title_Element = driver.findElement(headTitle);
        System.out.println(head_Title_Element.getText());
        return head_Title_Element.getText();
    }
    public String getAllItemsForTheTable(){
        MyTable_Element = driver.findElement(MyTable);
        System.out.println(MyTable_Element.getText());
        return MyTable_Element.getText();

    }
    public List<WebElement> getAllRows() {
        Rows_Element= driver.findElements(rows);
        System.out.println("Number of rows: " + Rows_Element.size());
        System.out.println(Rows_Element.get(0).getText());
        return Rows_Element;
    }
    public List<WebElement> getAllColums() {
        coloums_Element= driver.findElements(Coulums);
        System.out.println("Number of coulms: " + coloums_Element.size());
        System.out.println(coloums_Element.get(1).getText());
        return coloums_Element;
    }
    public  void  clickingOnDeleteButton (){
        Delete_Button_Element = driver.findElement(DeleteButton);
        wait.until(ExpectedConditions.elementToBeClickable(Delete_Button_Element));
        clicking(Delete_Button_Element);
    }

    public  placeOrderModelPage  clickingOnPlaceOrderButton (){
        Place_Order_Button_Element = driver.findElement(PlaceOrderButton);
        wait.until(ExpectedConditions.elementToBeClickable(Place_Order_Button_Element));
        clicking(Place_Order_Button_Element);
        return new placeOrderModelPage(driver);

    }
    public int getSumOfAllProductPrices() {
        SUM_of_Price_For_AllItems = driver.findElements(SUMofPriceForAllItems);
        int sum = 0;

        for (WebElement price : SUM_of_Price_For_AllItems) {
            String priceText = price.getText().replaceAll("[^0-9]", "");
            if (!priceText.isEmpty()) {
                sum += Integer.parseInt(priceText);
            }
        }
        System.out.println("Sum of all product prices: " + sum);
        return sum;
    }
    public int getTotaLPriceValueFromCart (){
        Total_Price_Value_Element = driver.findElement(TotalPriceValue);
        // انا خليته من web element ل string
        // وبعدين حولت من string to int
        String Total_Price_Value_Text = Total_Price_Value_Element.getText();
        System.out.println(Total_Price_Value_Element.getText());
        int  Total_Price_Value = Integer.parseInt(Total_Price_Value_Text);
        return Total_Price_Value ;
    }
        public boolean IsDeleteEnable() {
            return driver.findElement(DeleteButton).isEnabled();
        }
    public boolean IsDeleteDisplayed() {
        return driver.findElement(DeleteButton).isDisplayed();
    }
    public boolean IsPlaceOrderEnable() {
        return driver.findElement(PlaceOrderButton).isEnabled();
    }
    public boolean IsPlaceOrderDisplayed() {
        return driver.findElement(PlaceOrderButton).isDisplayed();
    }


    }






//    By PicForProduct = By.xpath("//img[@src='imgs/dell.jpg']");
//    WebElement Pic_For_Product_Element;
//    By PicForProduct = By.xpath("//img[@src='imgs/dell.jpg']");
//    WebElement Pic_For_Product_Element;



