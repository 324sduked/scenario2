package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class CartPage {

    private final SelenideElement totalPrice = $("#totalp");
    private final ElementsCollection pricesOfDevices = $$(By.xpath("//tbody/tr/td[3]"));

    public ElementsCollection getPricesOfDevices() {
        return pricesOfDevices;
    }

    private final ElementsCollection rowsInCart = $$("#tbodyid > tr");

    private final SelenideElement tableBody = $("#tbodyid");
    private final SelenideElement placeOrderButton = $(".col-lg-1 > button ");


    public SelenideElement getTotalPrice() {
        return totalPrice;
    }

    public void deleteDeviceByName(String name) {
        SelenideElement row = rowsInCart
                .filterBy(Condition.text(name))
                .first();
        row.should(exist);
        row.$(By.xpath("td[4]/a")).click();

    }

    public void shouldBeTbodyVisible() {
        tableBody.shouldBe(Condition.visible);
        sleep(2000);
    }

    public int getCartSize() {
        return rowsInCart.size();
    }

    public void clickPlaceOrderButton() {
        placeOrderButton.click();
    }

    public List<Integer> getDevicePrices() {
        return getPricesOfDevices().stream()
                .map(el -> Integer.parseInt(el.getText()))
                .toList();
    }

    public int getDisplayedTotal() {
        return Integer.parseInt(getTotalPrice().getText());
    }
}
