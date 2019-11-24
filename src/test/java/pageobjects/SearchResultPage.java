package pageobjects;

import gobear.automation.core.base.BasePageObject;
import gobear.automation.core.utils.Converter;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SearchResultPage extends BasePageObject {

    private By resultsTextDiv = By.cssSelector("div.results-text");
    private By resultCardDivs = By.cssSelector("div.card-wrapper");
    private By clearAllButton = By.cssSelector("span.fa-filter ~ a.clear-all");

    private By insurersFiltersLabels = By.cssSelector("div[data-filter-by='insurerId'] > div label");
    private By insurersFiltersCheckBoxes = By.cssSelector("div[data-filter-by='insurerId'] > div input");

    private By collapseSeeMoreButton = By.id("collapseSeemoreBtn");

    private By sortPromotionLabel = By.cssSelector("input[value=promotion-Desc] ~ label");
    private By sortPriceLowToHighLabel = By.cssSelector("input[value=premium-Asc] ~ label");
    private By sortPriceHighToLowLabel = By.cssSelector("input[value=premium-Desc] ~ label");
    private By sortCoverageScoreHighToLowLabel = By.cssSelector("input[value=score-Desc] ~ label");
    private By sortInsurerAToZLabel = By.cssSelector("input[value=insurerName-Asc] ~ label");
    private By sortInsurerZToALabel = By.cssSelector("input[value=insurerName-Desc] ~ label");
    private By sortReviewScoreHighToLowLabel = By.cssSelector("input[value=rating-Desc] ~ label");

    private By cancelTourButton = By.cssSelector("div.tour button[data-role='cancel']");
    private By endTourButton = By.cssSelector("div.tour button[data-role='end']");

    private By selectDestinationButton = By.cssSelector("div[data-gb-destination] button");

    private By datePickerMonthYearTableHeader = By.cssSelector("div.datepicker-days th.datepicker-switch");
    private By datePickerNextMonthButton = By.cssSelector("div.datepicker-days th.next");
    private By datePickerPreviousMonthButton = By.cssSelector("div.datepicker-days th. prev");
    private By dayInMonthTableCells = By.cssSelector("td.day:not(.new):not(.old)");
    private By startDateInput = By.name("dates-startdate");


    private final String SLIDER_XPATH = "//label[contains(text(),'%s')]/following-sibling::div[@class='bootstrap-slider']/div";
    private final String MIN_HANDLE_XPATH = "//label[contains(text(),'%s')]/following-sibling::div[@class='bootstrap-slider']//div[contains(@class,'min-slider-handle')]";
    private final String MAX_HANDLE_XPATH = "//label[contains(text(),'%s')]/following-sibling::div[@class='bootstrap-slider']//div[contains(@class,'max-slider-handle')]";
    private final String FILTERED_RANGE_CATEGORY_XPATH = "//p[text()='%s'][contains(@class,'detail-key')]/following-sibling::p";
    private final String DESTINATION_OPTION_XPATH = "//div[@data-gb-destination]//li/a/span[text()='%s']/following-sibling::link";

    private final String SORT_PROMOTION = "Promotion";
    private final String SORT_PRICE_LOW_TO_HIGH = "Price: Low to high";
    private final String SORT_PRICE_HIGH_TO_LOW = "Price: High to low";
    private final String SORT_COVERAGE_SCORE_HIGH_TO_LOW = "Coverage Score: High to low";
    private final String SORT_INSURER_A_TO_Z = "Insurer: A to Z";
    private final String SORT_INSURER_Z_TO_A = "Insurer: Z to A";
    private final String SORT_REVIEW_SCORE_HIGH_TO_LOW = "Review Score: High to low";

    private Set<String> selectedInsurers = new HashSet<>();

    public SearchResultPage() {
        super();
    }

    public void checkSearchResultPageDisplays() {
        assert waitForElement(resultsTextDiv) != null : "Search Result Page is not displayed";
    }

    public void checkCardCountEqualOrGreaterThan(int expected) {
        int actualCount = countElements(resultCardDivs);
        assert actualCount >= expected : "There're less than " + expected + " result cards shown on Result page. Actual: " + actualCount;
    }

    public void selectsRandomInsurersInFilter(int selectCount) {
        int insurersCount = countElements(insurersFiltersLabels);
        int cardCount = countElements(resultCardDivs);
        Random random = new Random();
        Set<Integer> selectedIndex = new HashSet<>();
        for (int i = 0; i < selectCount; i++) {
            int current = random.nextInt(insurersCount);
            while (selectedIndex.contains(current))
                current = random.nextInt(insurersCount);
            selectedIndex.add(current);
            setCheckBoxByLabel(insurersFiltersCheckBoxes, current, insurersFiltersLabels, current, true);
            selectedInsurers.add(getElementText(insurersFiltersLabels, current).trim());
        }
        waitElementsCountChange(cardCount, resultCardDivs);
    }

    public void setRangeSelectorFilter(String category, int minValue, int maxValue) {
        if (getElementAttribute(collapseSeeMoreButton, "class").contains("collapsed")) {
            click(collapseSeeMoreButton);
        }
        By slider = By.xpath(String.format(SLIDER_XPATH, category));
        By minHandle = By.xpath(String.format(MIN_HANDLE_XPATH, category));
        By maxHandle = By.xpath(String.format(MAX_HANDLE_XPATH, category));
        int cardCount = countElements(resultCardDivs);
        int sliderWidth = getElementSize(slider).width;
        dragSlider(minHandle, sliderWidth, minValue);
        dragSlider(maxHandle, sliderWidth, maxValue);
        waitElementsCountChange(cardCount, resultCardDivs);
    }

    public void selectSortOption(String sortOption) {
        switch (sortOption) {
            case SORT_PROMOTION:
                click(sortPromotionLabel);
                break;
            case SORT_PRICE_LOW_TO_HIGH:
                click(sortPriceLowToHighLabel);
                break;
            case SORT_PRICE_HIGH_TO_LOW:
                click(sortPriceHighToLowLabel);
                break;
            case SORT_COVERAGE_SCORE_HIGH_TO_LOW:
                click(sortCoverageScoreHighToLowLabel);
                break;
            case SORT_INSURER_A_TO_Z:
                click(sortInsurerAToZLabel);
                break;
            case SORT_INSURER_Z_TO_A:
                click(sortInsurerZToALabel);
                break;
            case SORT_REVIEW_SCORE_HIGH_TO_LOW:
                click(sortReviewScoreHighToLowLabel);
        }
        removeElementCache(resultCardDivs);
    }

    public void selectDestination(String destination) {
        click(selectDestinationButton);
        click(By.xpath(String.format(DESTINATION_OPTION_XPATH, destination)));

    }

    public void selectStartDate(String startDate) {
        LocalDate convertedDate = Converter.stringToDate(startDate);
        click(startDateInput);
        String currentDatePickerMonthYear = getElementText(datePickerMonthYearTableHeader);
        YearMonth currentYearMonth = YearMonth.parse(currentDatePickerMonthYear, DateTimeFormatter.ofPattern("MMMM yyyy"));
        YearMonth targetYearMonth = YearMonth.of(convertedDate.getYear(), convertedDate.getMonth());
        while (currentYearMonth.isBefore(targetYearMonth)) {
            click(datePickerNextMonthButton);
            currentDatePickerMonthYear = getElementText(datePickerMonthYearTableHeader);
            currentYearMonth = YearMonth.parse(currentDatePickerMonthYear, DateTimeFormatter.ofPattern("MMMM yyyy"));
        }
        while (currentYearMonth.isAfter(targetYearMonth)) {
            click(datePickerPreviousMonthButton);
            currentDatePickerMonthYear = getElementText(datePickerMonthYearTableHeader);
            currentYearMonth = YearMonth.parse(currentDatePickerMonthYear, DateTimeFormatter.ofPattern("MMMM yyyy"));
        }
        click(dayInMonthTableCells, convertedDate.getDayOfMonth() - 1);
    }

    private void dragSlider(By handle, int sliderWidth, int value) {
        int minValue = Integer.parseInt(getElementAttribute(handle, "aria-valuemin"));
        int maxValue = Integer.parseInt(getElementAttribute(handle, "aria-valuemax"));
        int currentValue = Integer.parseInt(getElementAttribute(handle, "aria-valuenow"));

        double tempPixel = sliderWidth;
        tempPixel = tempPixel / (maxValue - minValue);

        int deltaValue = value - currentValue;
        tempPixel = deltaValue * tempPixel;
        int offsetX = (int) tempPixel;

        actions_clickAndHold(handle);
        actions_moveByOffset(offsetX, 0);
        actions_release();
    }

    public void checkCardInsurerName() {
        int cardCount = countElements(resultCardDivs);
        Set<String> filteredInsurers = new HashSet<>();
        for (int i = 0; i < cardCount; i++) {
            String currentCardInsurer = getElementAttribute(resultCardDivs, i, "data-insuer-name").trim();
            filteredInsurers.add(currentCardInsurer);
        }
        filteredInsurers.removeAll(selectedInsurers);
        assert filteredInsurers.isEmpty() : "Result still contains unselected Insurers: " + filteredInsurers;
    }

    public void verifySortedResultList(String sortOption) {
        switch (sortOption) {
            case SORT_PRICE_HIGH_TO_LOW:
                int previousPlanPrice = Integer.parseInt(getElementAttribute(resultCardDivs, 0, "data-premium"));
                for (int i = 1; i < countElements(resultCardDivs); i++) {
                    int currentPlanPrice = Integer.parseInt(getElementAttribute(resultCardDivs, i, "data-premium"));
                    if (currentPlanPrice > previousPlanPrice)
                        throw new AssertionError("Result List is not sorted with Price: High to low");
                    previousPlanPrice = currentPlanPrice;
                }
                break;
            default:
        }
    }

    public void verifyRangeSelectorFilter(String category) {
        By minHandle = By.xpath(String.format(MIN_HANDLE_XPATH, category));
        By maxHandle = By.xpath(String.format(MAX_HANDLE_XPATH, category));
        By filterRangeValue = By.xpath(String.format(FILTERED_RANGE_CATEGORY_XPATH, category));
        int countResults = countElements(filterRangeValue);
        int minFilteredValue = Integer.parseInt(getElementAttribute(minHandle, "aria-valuenow"));
        int maxFilteredValue = Integer.parseInt(getElementAttribute(maxHandle, "aria-valuenow"));
        for (int i = 0; i < countResults; i++) {
            try {
                int cardValue = Integer.parseInt(getElementText(filterRangeValue, i).replace(",", "").replace("₱", ""));
                assert cardValue >= minFilteredValue && cardValue <= maxFilteredValue;
            } catch (NumberFormatException e) {
                // TODO: 11/24/19 Need a stable solution for parsing value
                e.printStackTrace();
            }
        }
    }

    public void clearAllFilters() {
        int cardCount = countElements(resultCardDivs);
        if (checkElementEnable(clearAllButton)) {
            click(clearAllButton);
            waitElementsCountChange(cardCount, resultCardDivs);
        }
    }

    public void endTour() {
        click(cancelTourButton);
        click(endTourButton);
    }
}
