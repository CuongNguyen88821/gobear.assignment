package stepdefinitions;

import io.cucumber.java.en.*;
import pageobjects.HomePage;

public class HomePageStepDef {
    private HomePage pageObject;

    public HomePageStepDef(HomePage pageObject) {
        this.pageObject = pageObject;
    }

    @Given("User navigates to GoBear Home Page")
    public void user_navigates_to_GoBear_Home_Page() {
        pageObject.navigate();
    }

    @When("User fills Search Details for Travel Insurance")
    public void user_fills_Search_Details_for_Travel_Insurance() {
        pageObject.selectProduct(HomePage.Product.INSURANCE, HomePage.SubProduct.TRAVEL);
        pageObject.clickShowResult();
    }

}
