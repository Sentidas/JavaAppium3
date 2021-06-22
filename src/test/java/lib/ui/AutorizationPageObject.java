package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AutorizationPageObject extends MainPageObject{

    private static final String
            LOGIN_BUTTON = "xpath://a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            CONTEINER_WITH_BUTTON = "xpath://div[@class='drawer drawer-container__drawer position-fixed visible']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AutorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Clicking the button for auth")
    public void clickAuthButton() {
        this.waitForELementPresent(CONTEINER_WITH_BUTTON, "Cannot find auth conteiner", 5);
        this.waitForELementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }
    @Step("Enter login and password for auth")
    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the login input", 5);

    }
    @Step("Enter the button for submit authorization")
    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);
    }
}
