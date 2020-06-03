package org.example;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LoginPage {
    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "#passp-field-login")
    private WebElement loginField;

    @FindBy(css = "button.control.button2.button2_view_classic.button2_size_l.button2_theme_action.button2_width_max.button2_type_submit.passp-form-button")
    private WebElement loginBtn;

    @FindBy(css = ".passp-form-field__error")
    private WebElement loginErr;


    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }


    public void ErrLogin(String errMsg) {
        assert loginErr.getText().contains(errMsg): "";
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }
}
