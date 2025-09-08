package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

import static com.codeborne.selenide.Selenide.*;

public class ProductPage {

    private final SelenideElement addToCartButton = $(".btn-success");
    private final SelenideElement homePageLink = $(By.xpath("//div[@id='navbarExample']//a[contains(text(), \"Home\")]"));


    public void clickOnAddToCartButton() {
        addToCartButton.click();
    }

    public void shouldBeAlertProductAddedDisplayed() {
        Wait().until(webDriver -> {
            try {
                return webDriver.switchTo().alert().getText().contains("Product added");
            } catch (NoAlertPresentException e) {
                return false;
            }
        });
        switchTo().alert().accept();
    }

    public HomePage clickOnHomePage() {
        homePageLink.click();
        return new HomePage();
    }
}
