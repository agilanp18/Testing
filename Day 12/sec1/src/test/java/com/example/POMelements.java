package com.example;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class POMelements {
    WebDriver driver;
    
    public POMelements (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='html-body']/div[3]/header/div[2]/div[3]/div[2]/div")
    WebElement sclick;
    @FindBy(xpath = "//*[@id=\"search\"]")
    WebElement searchbox;
    public void clicksearch(String sitem)
    {
        sclick.click();
        searchbox.sendKeys(sitem+Keys.ENTER);

    }
    @FindBy(xpath = "//*[@id='minicart-content-wrapper']/div[2]/div[4]/div/a/span/span")
    WebElement res1;
    public void asses1()
    {
        Assert.assertTrue(res1.getText().contains("Cart"));
    }
    @FindBy(xpath = "//*[@id='ui-id-2']")
    WebElement grocery;
    public void getGrocery() {
        grocery.click();
    }
    @FindBy(xpath = "//*[@id=\"am-shopby-container\"]/ol/li[1]/div")
    WebElement res2;
    public void asses2()
    {
        Assert.assertTrue(res2.getText().contains("Oil"));
    }
    @FindBy(xpath = "//*[@id='social-login-authentication']/div/div/div")
    WebElement res3;
    public void asses3() throws InterruptedException
    {
        String res=res3.getText();
        Assert.assertTrue(res.contains("Invalid"));
    }
    @FindBy(xpath = "//*[@id='email']")
    WebElement email;
    public void clkmail(String mail) 
    {
        email.sendKeys(mail);
    }
    @FindBy(xpath = "//*[@id=\"pass\"]")
    WebElement pass;
    public void clkpass(String passwrd) 
    {
        pass.sendKeys(passwrd);
    }


}
