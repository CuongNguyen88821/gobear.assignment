package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.SearchResultPage;

public class SearchResultPageStepDef {
    private SearchResultPage pageObject;

    public SearchResultPageStepDef(SearchResultPage pageObject) {
        this.pageObject = pageObject;
    }

    @Then("Search Result Page for Travel Insurance displays")
    public void search_Result_Page_for_Travel_Insurance_displays() {
        pageObject.checkSearchResultPageDisplays();
        pageObject.endTour();
    }

    @Then("There're at least {int} cards displayed on Search Result Page")
    public void there_re_at_least_cards_displayed_on_Search_Result_Page(Integer minimumCardCount) {
        pageObject.checkCardCountEqualOrGreaterThan(minimumCardCount);
    }

    @When("User selects {int} Insurers from Filters section")
    public void userSelectsInsurersFromFiltersSection(int selectCount) {
        pageObject.selectsRandomInsurersInFilter(selectCount);
    }

    @Then("There're only plans from the selected Insurers displayed on Result Page")
    public void thereReOnlyPlansFromTheSelectedInsurersDisplayedOnResultPage() {
        pageObject.checkCardInsurerName();
    }

    @When("User sets Range Selector for {string} with min value = {int} and max value = {int}")
    public void userSetsRangeSelectorForWithMinValueAndMaxValue(String category, int minValue, int maxValue) {
        pageObject.setRangeSelectorFilter(category, minValue, maxValue);
    }

    @Given("User clears all filters")
    public void userClearsAllFilters() {
        pageObject.clearAllFilters();
    }

    @Then("There're only plans with {string} in selected range displayed on Result Page")
    public void thereReOnlyPlansWithInSelectedRangeDisplayedOnResultPage(String category) {
        pageObject.verifyRangeSelectorFilter(category);
    }

    @When("User selects Sort Option {string}")
    public void userSelectsSortOption(String sortOption) {
        pageObject.selectSortOption(sortOption);
    }

    @Then("Search Result plans are sorted by {string}")
    public void searchResultPlansAreSortedBy(String sortOption) {
        pageObject.verifySortedResultList(sortOption);
    }

    @When("User selects Destination as {string}")
    public void userSelectsDestinationAs(String destination) {
        pageObject.selectDestination(destination);
    }

    @When("User selects Start Date as {string}")
    public void userSelectsStartDateAs(String startDate) {
        pageObject.selectStartDate(startDate);
    }
}
