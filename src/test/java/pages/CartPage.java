package pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class CartPage {

    private final SelenideElement totalPrice = $("#totalp");
    private final ElementsCollection pricesOfDevices = $$(By.xpath("//tbody/tr/td[3]"));
    private final ElementsCollection rowsInCart = $$("#tbodyid > tr");
    private final SelenideElement tableBody = $("#tbodyid");
    private final SelenideElement placeOrderButton = $(".col-lg-1 > button ");

    public Integer calculateTotalPrice() {
        int totalPrice = 0;
        for (SelenideElement price : pricesOfDevices) {
            String text = price.getText();
            totalPrice = totalPrice + Integer.parseInt(text);
        }
        return totalPrice;
    }

    public void shouldBeCalculatedTotalPriceEqualToDisplayedOne(Integer calculatedTotalPrice) {
        Assert.assertEquals(totalPrice.getText(), calculatedTotalPrice.toString());
    }

    public void deleteDeviceByName(String name) {
        SelenideElement row = rowsInCart
                .filterBy(Condition.text(name))
                .first(); // row that contains the name (works if only td[2] has the name)
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

    public void shouldBeCartSizeOneLess(int initialCartSize) {
        rowsInCart.shouldHave(CollectionCondition.size(initialCartSize - 1));
    }
}
