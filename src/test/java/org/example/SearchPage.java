package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Set;

public class SearchPage {
    //конструктор класса
    public WebDriver driver;
    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // определение локатора поля ввода запроса
    @FindBy(xpath = "//*[@class = \"home-arrow__search\"]//*[@id=\"text\"]")
    private WebElement searchField;

    //определение локатора кнопки поиска
    @FindBy(xpath = "//*[@class = \"home-arrow__search\"]//*[@role=\"button\"]")
    private WebElement searchBtn;

    ///определение локатора кнопки входа
    @FindAll(@FindBy(xpath = "//*[@id=\"search-result\"]//*[@class=\"organic__url-text\"]"))
    private List<WebElement> searchResList;

    //метод для ввода поискового запроса
    public void inputRequest(String strSearch) {
        searchField.sendKeys(strSearch);
    }

    //метод для осуществления нажатия кнопки поиска
    public void clickSearchBtn() {
        searchBtn.click();
    }

    //переход на страницу почты
    public void GoSearchItem(String ItemText) {
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        for(int i = 0;  i < searchResList.size(); i++) {
            WebElement element = searchResList.get(i);
            if (element.getText().contains(ItemText)) {
                element.click();
                String newWindow = (new WebDriverWait(driver, 10))
                        .until(new ExpectedCondition<String>() {
                                   public String apply(WebDriver driver) {
                                       Set<String> newWindowsSet = driver.getWindowHandles();
                                       newWindowsSet.removeAll(oldWindowsSet);
                                       return newWindowsSet.size() > 0 ?
                                               newWindowsSet.iterator().next() : null;
                                   }
                               }
                        );
                driver.switchTo().window(newWindow);
                break;
            }
        }
    }

}
