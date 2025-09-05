package pages.forms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PlaceOrderForm {

    private final SelenideElement placeOrderForm = $(By.xpath("//div[@id='orderModal']//form"));
    private final SelenideElement nameField = $("#name");
    private final SelenideElement countryField = $("#country");
    private final SelenideElement cityField = $("#city");
    private final SelenideElement creditCardField = $("#card");
    private final SelenideElement monthField = $("#month");
    private final SelenideElement yearField = $("#year");
    private final SelenideElement purchaseButton = $(By.xpath("//div[@id='orderModal']//button[@class='btn btn-primary']"));
    private final SelenideElement sweetAlert = $(".sweet-alert");
    private final SelenideElement confirmSweetAlertButton = $(By.xpath("//div[@class='sa-confirm-button-container']/button"));


    public void shouldBePlaceOrderFormVisible() {
        placeOrderForm.shouldBe(Condition.visible);
    }
    public void inputValuesIntoForm(String name, String country, String city, String card, String month, String year) {
        nameField.clear();
        countryField.clear();
        cityField.clear();
        creditCardField.clear();
        monthField.clear();
        yearField.clear();
        nameField.setValue(name);
        countryField.setValue(country);
        cityField.setValue(city);
        creditCardField.setValue(card);
        monthField.setValue(month);
        yearField.setValue(year);
    }

    public void clickPurchaseButton() {
        purchaseButton.click();
    }

    public void shouldBeMessageVisible() {
        sweetAlert.shouldBe(Condition.visible);
    }
    public void clickConfirmButton() {
        confirmSweetAlertButton.click();
    }
}
