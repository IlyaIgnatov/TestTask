package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SearchResultPage {
    public SelenideElement
            noSearchResultsMsg = $(byText("No results")),
            searchResultPageWelcomeMsg = $("#app").$(byText("properties found")),
            bedsCurrentValue = $(".__modal").$(byText("Beds")).parent().$("span"),
            bedroomsCurrentValue = $(".__modal").$(byText("Bedrooms")).parent().$("span"),
            bathroomsCurrentValue = $(".__modal").$(byText("Bathrooms")).parent().$("span"),
            throbber = $x("//div[contains(@style, 'text-align: center;')]"),
            someResultFounded = searchResultPageWelcomeMsg;

    SelenideElement
            filtersForm = $(".__modal"),
            filtersPopupTitle = $x("//div[text()='Filters']"),
            priceFromInput = filtersForm.$("input[placeholder='From']"),
            priceToInput = filtersForm.$("input[placeholder='To']"),
            bedsMinusButton = filtersForm.$(byText("Beds")).parent().$$("button").get(0),
            bedsPlusButton = filtersForm.$(byText("Beds")).parent().$$("button").get(1),
            bedroomsMinusButton = filtersForm.$(byText("Bedrooms")).parent().$$("button").get(0),
            bedroomsPlusButton = filtersForm.$(byText("Bedrooms")).parent().$$("button").get(1),
            bathroomsMinusButton = filtersForm.$(byText("Bathrooms")).parent().$$("button").get(0),
            bathroomsPlusButton = filtersForm.$(byText("Bathrooms")).parent().$$("button").get(1),

            beachFrontCheckBox = filtersForm.$(byText("Beach front")),
            freeWiFiCheckBox = filtersForm.$(byText("Free WiFi")),
            airConditioningCheckBox = filtersForm.$(byText("Air conditioning")),
            petsAllowedCheckBox = filtersForm.$(byText("Pets allowed")),
            streetParkingCheckBox = filtersForm.$(byText("Street parking")),
            swimmingPoolCheckBox = filtersForm.$(byText("Swimming pool")),
            kitchenCheckBox = filtersForm.$(byText("Kitchen")),
            washingMachineCheckBox = filtersForm.$(byText("Washing Machine")),
            hotTubCheckBox = filtersForm.$(byText("Hot tub")),
            suitableForChildrenCheckBox = filtersForm.$(byText("Suitable for children")),

            clearAllButton = filtersForm.$(byText("Clear all")),
            applyButton = filtersForm.$(byText("Apply")),


            openFiltersFormButton = $x("//span[text()='Filter']"),
            closeFiltersFormPopupButton = $x("//*[name()='use' and @*='#close']");


    @Step("Open the searchResult page")
    public SearchResultPage openSearchResultPage() {
        open(UI_Endpoints.SEARCH_RESULT_PAGE);
        isSearchResultPageOpened();
        return this;
    }

    @Step("Check that the searchResult page is opened")
    public SearchResultPage isSearchResultPageOpened() {
        searchResultPageWelcomeMsg.shouldBe(Condition.visible, Duration.ofSeconds(15));
        return this;
    }

    @Step("Click the filter button and open filters form")
    public SearchResultPage clickOpenFiltersFormButton() {
        openFiltersFormButton.click();
        filtersPopupTitle.shouldBe(visible);
        return this;
    }

    @Step("Set price value \"From\"")
    public SearchResultPage setPriceFrom(String location) {
        priceFromInput.click();
        priceFromInput.setValue(location);
        priceFromInput.shouldNotBe(Condition.empty);
        filtersPopupTitle.click();
        return this;
    }

    @Step("Set price value \"To\"")
    public SearchResultPage setPriceTo(String location) {
        priceToInput.click();
        priceToInput.setValue(location);
        priceToInput.shouldNotBe(Condition.empty);
        filtersPopupTitle.click();
        return this;
    }

    @Step("Click beds plus button")
    public SearchResultPage clickBedsPlusButton() {
        bedsPlusButton.click();
        return this;
    }
    @Step("Click beds plus button")
    public SearchResultPage clickBedsPlusButton(int numberOfClicks) {
        for (int i = 0; i < numberOfClicks; i++) {
            bedsPlusButton.click();
        }
        return this;
    }

    @Step("Click beds minus button")
    public SearchResultPage clickBedsMinusButton() {
        bedsMinusButton.click();
        return this;
    }

    @Step("Click beds minus button")
    public SearchResultPage clickBedsMinusButton(int numberOfClicks) {
        for (int i = 0; i < numberOfClicks; i++) {
            bedsMinusButton.click();
        }
        return this;
    }

    @Step("Click bedrooms plus button")
    public SearchResultPage clickBedroomsPlusButton() {
        bedroomsPlusButton.click();
        return this;
    }

    @Step("Click bedrooms plus button")
    public SearchResultPage clickBedroomsPlusButton(int numberOfClicks) {
        for (int i = 0; i < numberOfClicks; i++) {
            bedroomsPlusButton.click();
        }
        return this;
    }

    @Step("Click bedrooms minus button")
    public SearchResultPage clickBedroomsMinusButton() {
        bedroomsMinusButton.click();
        return this;
    }

    @Step("Click bedrooms minus button")
    public SearchResultPage clickBedroomsMinusButton(int numberOfClicks) {
        for (int i = 0; i < numberOfClicks; i++) {
            bedroomsMinusButton.click();
        }
        return this;
    }

    @Step("Click bathrooms plus button")
    public SearchResultPage clickBathroomsPlusButton() {
        bathroomsPlusButton.click();
        return this;
    }

    @Step("Click bathrooms plus button")
    public SearchResultPage clickBathroomsPlusButton(int numberOfClicks) {
        for (int i = 0; i < numberOfClicks; i++) {
            bathroomsPlusButton.click();
        }
        return this;
    }

    @Step("Click bathrooms minus button")
    public SearchResultPage clickBathroomsMinusButton() {
        bathroomsMinusButton.click();
        return this;
    }

    @Step("Click bathrooms minus button")
    public SearchResultPage clickBathroomsMinusButton(int numberOfClicks) {
        for (int i = 0; i < numberOfClicks; i++) {
            bathroomsMinusButton.click();
        }
        return this;
    }

    @Step("Click the beachFront checkBox")
    public SearchResultPage clickBeachFrontCheckBox() {
        beachFrontCheckBox.click();
        return this;
    }

    @Step("Click the freeWiFi checkBox")
    public SearchResultPage clickFreeWiFiCheckBox() {
        freeWiFiCheckBox.click();
        return this;

    }

    @Step("Click the airConditioning checkBox")
    public SearchResultPage clickAirConditioningCheckBox() {
        airConditioningCheckBox.click();
        return this;
    }

    @Step("Click the petsAllowed checkBox")
    public SearchResultPage clickPetsAllowedCheckBox() {
        petsAllowedCheckBox.click();
        return this;
    }

    @Step("Click the streetParking checkBox")
    public SearchResultPage clickStreetParkingCheckBox() {
        streetParkingCheckBox.click();
        return this;
    }

    @Step("Click the swimmingPool checkBox")
    public SearchResultPage clickSwimmingPoolCheckBox() {
        swimmingPoolCheckBox.click();
        return this;
    }

    @Step("Click the kitchen checkBox")
    public SearchResultPage clickKitchenCheckBox() {
        kitchenCheckBox.click();
        return this;
    }

    @Step("Click the washingMachine checkBox")
    public SearchResultPage clickWashingMachineCheckBox() {
        washingMachineCheckBox.click();
        return this;
    }

    @Step("Click the hotTub checkBox")
    public SearchResultPage clickHotTubCheckBox() {
        hotTubCheckBox.click();
        return this;
    }

    @Step("Click the suitableForChildren checkBox")
    public SearchResultPage clickSuitableForChildrenCheckBox() {
        suitableForChildrenCheckBox.click();
        return this;
    }

    @Step("Click the clearAll button")
    public SearchResultPage clickClearAllButton() {
        clearAllButton.click();
        return this;
    }

    @Step("Click the apply button")
    public SearchResultPage clickApplyButton() {
        applyButton.click();
        Selenide.sleep(1000);
        return this;
    }

    @Step("Click the closeFiltersFormPopup button")
    public SearchResultPage clickCloseFiltersFormPopupButton() {
        closeFiltersFormPopupButton.click();
        Selenide.sleep(1000);
        return this;
    }
}
