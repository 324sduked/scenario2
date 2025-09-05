package pages.forms;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;

public abstract class BaseForm {

    protected abstract SelenideElement usernameField();

    protected abstract SelenideElement passwordField();

    protected abstract SelenideElement submitButton();

    protected abstract SelenideElement Form();

    public void fillIn(String username, String password) {
        usernameField().clear();
        passwordField().clear();
        usernameField().sendKeys(username);
        passwordField().sendKeys(password);
    }
    public void clickSubmitButton() {
        submitButton().click();
    }

    public void shouldBeFormVisible() {
        Form().shouldBe(visible);
    }
    public void acceptAlert() {
        switchTo().alert().accept();
    }

}
