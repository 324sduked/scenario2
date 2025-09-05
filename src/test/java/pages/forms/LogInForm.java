package pages.forms;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LogInForm extends BaseForm {

    private final SelenideElement signInForm = $(By.xpath("//div[@id='logInModal']//form"));
    private final SelenideElement usernameTextArea = $("#loginusername");
    private final SelenideElement passwordTextArea = $("#loginpassword");
    private final SelenideElement submitButton = $(By.xpath("//div[@id='logInModal']//div[@class='modal-footer']/button[@class='btn btn-primary']"));

    @Override
    protected SelenideElement usernameField() {
        return usernameTextArea;
    }

    @Override
    protected SelenideElement passwordField() {
        return passwordTextArea;
    }

    @Override
    protected SelenideElement submitButton() {
        return submitButton;
    }

    @Override
    protected SelenideElement Form() {
        return signInForm;
    }
}
