package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private final SelenideElement signUpButton = $("#signin2");
    private final SelenideElement logInButton = $("#login2");
    private final SelenideElement welcomeAfterLoggingIn = $("#nameofuser");
    private final SelenideElement contactButton = $(By.xpath("//div[@id='navbarExample']//li/a[text()='Contact']"));
    private final SelenideElement monitorsCategory = $(By.xpath("//a[contains(@onclick, \"byCat('monitor')\")]"));
    private final SelenideElement laptopsCategory = $(By.xpath("//div[@class='list-group']/a[text()='Laptops']"));
    private final ElementsCollection displayedDevicesTitles = $$(By.xpath("//div[@id='tbodyid']//h4"));
    private final SelenideElement cartButton = $("#cartur");

    private final Map<Navigation, SelenideElement> navMap = Map.of(
            Navigation.SIGN_UP, signUpButton,
            Navigation.LOG_IN, logInButton,
            Navigation.CONTACT, contactButton,
            Navigation.CART, cartButton,
            Navigation.LAPTOPS_CATEGORY, laptopsCategory,
            Navigation.MONITORS_CATEGORY, monitorsCategory
    );

    public void click(Navigation linkItem) {
        navMap.get(linkItem).click();
    }

    public void clickOnSpecificDevice(String deviceName) {
        displayedDevicesTitles.findBy(Condition.text(deviceName)).shouldBe(visible).click();
    }

    public void shouldBeDevicesDisplayed(List<String> expectedDevices) {
        sleep(1000);
        displayedDevicesTitles.forEach(title -> title.shouldBe(clickable));
        boolean areDevicesDisplayed = displayedDevicesTitles.stream()
                .anyMatch(device ->
                        expectedDevices.stream()
                                .anyMatch(expected -> device.getText().contains(expected))
                );

        Assert.assertTrue(areDevicesDisplayed,
                "Expected at least one of " + expectedDevices + " to be displayed");
    }

    public void shouldBeHomePageDisplayed() {
        String url = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertEquals(url, "https://www.demoblaze.com/index.html");
    }

    public void shouldBeWelcomeAfterLoggingIn(String expectedUsername) {
        welcomeAfterLoggingIn.shouldHave(Condition.text("Welcome " + expectedUsername));
    }

    public ElementsCollection getDisplayedDevicesTitles() {
        return displayedDevicesTitles;
    }

}
