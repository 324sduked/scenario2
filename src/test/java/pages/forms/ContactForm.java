package pages.forms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class ContactForm {

    private final SelenideElement contactForm = $(By.xpath("//div[@id='exampleModal']//form"));
    private final SelenideElement contactEmailField = $("#recipient-email");
    private final SelenideElement contactNameField = $("#recipient-name");
    private final SelenideElement messageField = $("#message-text");
    private final SelenideElement sendMessageButton = $("#exampleModal .modal-footer .btn.btn-primary");


    public void shouldBeContactFormVisible(){
        contactForm.shouldBe(Condition.visible);
    }
    public void fillContactForm(String email, String name, String message){
        contactEmailField.clear();
        contactNameField.clear();
        messageField.clear();
        contactEmailField.setValue(email);
        contactNameField.setValue(name);
        messageField.setValue(message);
    }

    public void clickSendMessageButton(){
        sendMessageButton.click();
    }
    public void shouldBeAlertMessageVisible(){
        Assert.assertTrue(switchTo().alert().getText().contains("Thanks for the message!!"));
    }
}
