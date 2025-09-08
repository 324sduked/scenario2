package pages.forms;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class SignUpForm extends BaseForm {

    private final SelenideElement signInForm = $(By.xpath("//div[@id='signInModal']//form"));
    private final SelenideElement usernameTextArea = $("#sign-username");
    private final SelenideElement passwordTextArea = $("#sign-password");
    private final SelenideElement submitButton = $(By.xpath("//div[@id='signInModal']//div[@class='modal-footer']/button[@class='btn btn-primary']"));

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

    public void shouldBeAlertThatSaysToFillOutBothTextAreasVisible() {
        Assert.assertEquals(switchTo().alert().getText(), "Please fill out Username and Password.");
        switchTo().alert().accept();
    }

    public void shouldBeAlertSignUpSuccessfulVisible() {
        Assert.assertEquals(switchTo().alert().getText(), "Sign up successful.");
        switchTo().alert().accept();
    }
}
