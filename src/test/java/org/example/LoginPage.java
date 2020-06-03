package org.example;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginPage {
    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//form//*[@id=\"passp-field-login\"]")
    private WebElement loginField;
    //form//div[*class = "passp-sign-in-button"]
    @FindBy(xpath = "//form/*[@class=\"passp-button passp-sign-in-button\"]")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@data-t=\"field:error-login\"]")
    private WebElement loginErr;


    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }

    public boolean is_element_present(WebElement d) {
        try {
            d.getText();
        }
        catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void ErrLogin() {
        assertTrue(is_element_present(loginErr), "The system has accepted the username");
        //assert loginErr.getText().contains(errMsg): "The system has accepted the username";
    }
}