package pageobjects;

import gobear.automation.core.base.BasePageObject;
import org.openqa.selenium.By;
import utils.Constants;

public class HomePage extends BasePageObject {

    public enum Product {
        INSURANCE,
        CREDIT_CARDS,
        LOANS
    }

    public enum SubProduct {
        CAR,
        TRAVEL,
        PERSONAL_LOAN,
        NONE
    }

    private By searchModuleDiv = By.cssSelector("div.tabs-products.search-form-module");

    private By insuranceTabLink = By.cssSelector("a[aria-controls='Insurance']");

    private By travelTabLink = By.cssSelector("#Insurance a[href='#Travel']");

    private By showResultButton = By.name("product-form-submit");

    public HomePage() {
        super();
    }

    public void navigate() {
        navigate(Constants.UAT_URL);
        waitForElement(searchModuleDiv);
    }

    public void selectProduct(Product product, SubProduct subProduct) {
        switch (product) {
            case INSURANCE:
                click(insuranceTabLink);
                switch (subProduct) {
                    case TRAVEL:
                        click(travelTabLink);
                        break;
                    case CAR:
                    case NONE:
                    default:
                        throw new cucumber.api.PendingException();
                }
                break;
            case LOANS:
            case CREDIT_CARDS:
            default:
                throw new cucumber.api.PendingException();
        }
    }

    public void clickShowResult(){
        click(showResultButton);
    }
}
