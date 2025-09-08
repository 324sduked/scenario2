package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.Navigation;
import pages.ProductPage;
import pages.forms.ContactForm;
import pages.forms.LogInForm;
import pages.forms.PlaceOrderForm;
import pages.forms.SignUpForm;

import static com.codeborne.selenide.Selenide.open;
import static utils.TestStrings.*;

public class E2EeCommerceTests {

    private HomePage page;
    private final SignUpForm signUpForm = new SignUpForm();
    private final LogInForm logInForm = new LogInForm();
    private final ContactForm contactForm = new ContactForm();

    @BeforeClass
    public void beforeClass() {
        open("https://www.demoblaze.com/index.html");
        page = new HomePage();

    }

    @Test(priority = 1)
    public void tryToSignUpWithOnlyUsernameAndCheckIfAlertIsDisplayedAndThenSignUpSuccessfully() {
        page.click(Navigation.SIGN_UP);

        signUpForm.shouldBeFormVisible();

        signUpForm.fillIn(validSignInUsername, "");
        signUpForm.clickSubmitButton();

        signUpForm.shouldBeAlertThatSaysToFillOutBothTextAreasVisible();


        signUpForm.fillIn(validSignInUsername, validSignInPassword);
        signUpForm.clickSubmitButton();

        signUpForm.shouldBeAlertSignUpSuccessfulVisible();

    }

    @Test(priority = 2)
    public void LogInSuccessfulTest() {
        page.click(Navigation.LOG_IN);


        logInForm.shouldBeFormVisible();

        logInForm.fillIn(validLoginUsername, validLoginPassword);

        logInForm.clickSubmitButton();

        page.shouldBeWelcomeAfterLoggingIn(validLoginUsername);
    }

    @Test(priority = 3)
    public void contactFormTest() {
        page.click(Navigation.CONTACT);

        contactForm.shouldBeContactFormVisible();

        contactForm.fillContactForm(validContactEmail, validContactName, validMessage);

        contactForm.clickSendMessageButton();

        contactForm.shouldBeAlertMessageVisible();
    }

    @Test(priority = 4)
    public void ShoppingCartTest() {
        page.click(Navigation.LAPTOPS_CATEGORY);

        page.shouldBeDevicesDisplayed(expectedLaptopsList);

        page.clickOnSpecificDevice(macBookAir);
        ProductPage macBookAirPage = new ProductPage();
        macBookAirPage.clickOnAddToCartButton();
        macBookAirPage.shouldBeAlertProductAddedDisplayed();

        page = macBookAirPage.clickOnHomePage();
        page.shouldBeHomePageDisplayed();
        page.click(Navigation.MONITORS_CATEGORY);

        page.shouldBeDevicesDisplayed(expectedMonitorsList);

        page.clickOnSpecificDevice(appleMonitor24);
        ProductPage appleMonitor24Page = new ProductPage();
        appleMonitor24Page.clickOnAddToCartButton();
        appleMonitor24Page.shouldBeAlertProductAddedDisplayed();
        appleMonitor24Page.clickOnAddToCartButton();
        appleMonitor24Page.shouldBeAlertProductAddedDisplayed();
        appleMonitor24Page.clickOnHomePage();
        page.shouldBeHomePageDisplayed();
        page.click(Navigation.CART);
        CartPage cartPage = new CartPage();
        cartPage.shouldBeTbodyVisible();

        int initialCartSize = cartPage.getCartSize();

        cartPage.deleteDeviceByName(appleMonitor24);
        cartPage.shouldBeTbodyVisible();
        Assert.assertEquals(initialCartSize - 1, cartPage.getCartSize());

        int calculatedTotal = cartPage.getDevicePrices().stream().mapToInt(Integer::intValue).sum();

        Assert.assertEquals(cartPage.getDisplayedTotal(), calculatedTotal);

        cartPage.clickPlaceOrderButton();
        PlaceOrderForm placeOrderForm = new PlaceOrderForm();
        placeOrderForm.shouldBePlaceOrderFormVisible();
        placeOrderForm.inputValuesIntoForm(name, country, city, card, month, year);
        placeOrderForm.clickPurchaseButton();
        placeOrderForm.shouldBeMessageVisible();

    }

}

