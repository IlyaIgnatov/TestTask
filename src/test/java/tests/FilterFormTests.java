package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AllListingsPage;
import pages.SearchResultPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static data.QueryParamsName.*;
import static data.ValuesOfAmenities.*;
import static helpers.Utils.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;



public class FilterFormTests extends TestBase{
    final private SearchResultPage searchResultPage = new SearchResultPage();
    final private AllListingsPage allListingsPage = new AllListingsPage();

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful search without any amenities, beds&rooms and prices in filter")
    void withoutAmenities_BedsAndRooms_PricesTest() {

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Click apply without any amenities, beds&rooms and prices", () -> {
            searchResultPage
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.someResultFounded.as("... properties found").shouldBe(Condition.visible);
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(AMENITIES.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_FROM.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_TO.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BEDS.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BEDROOMS.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BATHROOMS.getParamName());

        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful found result by price")
    void correctStartAndUpperPriceInRequestTest() {
        String price_from = "150";
        String price_to = "499";

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set values of start and upper prices", () -> {
            searchResultPage
                    .setPriceFrom(price_from)
                    .setPriceTo(price_to)
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.someResultFounded.as("... properties found").shouldBe(Condition.visible);
            assertThat(price_from).isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(PRICE_FROM.getParamName())[1]).split("&")[0]);
            assertThat(price_to).isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(PRICE_TO.getParamName())[1]).split("&")[0]);
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful found no results. Start price too high")
    void startPriceTooHighTest() {
        String price_from = "1111";

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set value of start price", () -> {
            searchResultPage
                    .setPriceFrom(price_from)
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.noSearchResultsMsg.as("No results").shouldBe(Condition.visible);
            assertThat(price_from).isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(PRICE_FROM.getParamName())[1]).split("&")[0]);
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_TO.getParamName());
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful found no results. Upper price too low")
    void upperPriceTooLowTest() {
        String price_to = "5";

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set value of upper price", () -> {
            searchResultPage
                    .setPriceTo(price_to)
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.noSearchResultsMsg.as("No results").shouldBe(Condition.visible);
            assertThat(price_to).isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(PRICE_TO.getParamName())[1]).split("&")[0]);
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_FROM.getParamName());
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful found results. Use Integer max value as a upper price")
    void integerMaxValueAsUpperPriceTest() {
        String price_to = Integer.toString(Integer.MAX_VALUE);

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set value of upper price", () -> {
            searchResultPage
                    .setPriceTo(price_to)
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.someResultFounded.as("... properties found").shouldBe(Condition.visible);
            assertThat(price_to).isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(PRICE_TO.getParamName())[1]).split("&")[0]);
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_FROM.getParamName());
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful found results. Use Long max value as a upper price")
    void longMaxValueAsUpperPriceTest() {
        String price_to = Long.toString(Long.MAX_VALUE);

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set value of upper price", () -> {
            searchResultPage
                    .setPriceTo(price_to)
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.someResultFounded.as("... properties found").shouldBe(Condition.visible);
            assertThat(price_to).isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(PRICE_TO.getParamName())[1]).split("&")[0]);
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_FROM.getParamName());
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful found no results. Use Integer max value as a start price")
    void integerMaxValueAsStartPriceTest() {
        String price_from = Integer.toString(Integer.MAX_VALUE);

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set value of lower price", () -> {
            searchResultPage
                    .setPriceFrom(price_from)
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.noSearchResultsMsg.as("No results").shouldBe(Condition.visible);
            assertThat(price_from).isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(PRICE_FROM.getParamName())[1]).split("&")[0]);
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_TO.getParamName());
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Unsuccessful found no results. Use Long max value as a start price")
    void longMaxValueAsStartPriceTest() {
        String price_from = Long.toString(Long.MAX_VALUE);

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set value of upper price", () -> {
            searchResultPage
                    .setPriceFrom(price_from)
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.noSearchResultsMsg.as("No results").shouldBe(Condition.visible);
            assertThat(price_from).isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(PRICE_FROM.getParamName())[1]).split("&")[0]);
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_TO.getParamName());
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful found result by beds/bedrooms/bathrooms")
    void roomsAndBedsAmountTest() {
        int bedNumberOfClicks = 2;
        int bedroomsNumberOfClicks = 4;
        int bathroomsNumberOfClicks = 3;

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set values of beds/bedrooms/bathrooms", () -> {
            searchResultPage
                    .clickBedsPlusButton(bedNumberOfClicks)
                    .clickBedroomsPlusButton(bedroomsNumberOfClicks)
                    .clickBathroomsPlusButton(bathroomsNumberOfClicks)
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.someResultFounded.as("... properties found").shouldBe(Condition.visible);
            assertThat(String.valueOf(bedNumberOfClicks)).isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(NUMBER_OF_BEDS.getParamName())[1]).split("&")[0]);
            assertThat(String.valueOf(bedroomsNumberOfClicks)).isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(NUMBER_OF_BEDROOMS.getParamName())[1]).split("&")[0]);
            assertThat(String.valueOf(bathroomsNumberOfClicks)).isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(NUMBER_OF_BATHROOMS.getParamName())[1]).split("&")[0]);
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful found result by beds/bedrooms/bathrooms (LowerBound)")
    void roomsAndBedsAmountLowerBoundTest() {
        int bedNumberOfClicks = 2;
        int bedroomsNumberOfClicks = 2;
        int bathroomsNumberOfClicks = 2;

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set values of beds/bedrooms/bathrooms", () -> {
            searchResultPage
                    .clickBedsMinusButton(bedNumberOfClicks)
                    .clickBedroomsMinusButton(bedroomsNumberOfClicks)
                    .clickBathroomsMinusButton(bathroomsNumberOfClicks)
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.someResultFounded.as("... properties found").shouldBe(Condition.visible);
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BEDS.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BEDROOMS.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BATHROOMS.getParamName());
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful found result by beds/bedrooms/bathrooms (UpperBound)")
    void roomsAndBedsAmountUpperBoundTest() {
        int bedNumberOfClicks = 11;
        int bedroomsNumberOfClicks = 11;
        int bathroomsNumberOfClicks = 11;

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set values of beds/bedrooms/bathrooms", () -> {
            searchResultPage
                    .clickBedsPlusButton(bedNumberOfClicks)
                    .clickBedroomsPlusButton(bedroomsNumberOfClicks)
                    .clickBathroomsPlusButton(bathroomsNumberOfClicks)
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.noSearchResultsMsg.as("No results").shouldBe(Condition.visible);
            assertThat("10").isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(NUMBER_OF_BEDS.getParamName())[1]).split("&")[0]);
            assertThat("10").isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(NUMBER_OF_BEDROOMS.getParamName())[1]).split("&")[0]);
            assertThat("10").isEqualTo(((getWebDriver().getCurrentUrl())
                    .split(NUMBER_OF_BATHROOMS.getParamName())[1]).split("&")[0]);
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful using plus/minus beds/bedrooms/bathrooms buttons")
    void roomsAndBedsAmountPlusMinusButtonsTest() {

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set values of beds/bedrooms/bathrooms", () -> {
            searchResultPage
                    .clickBedsPlusButton(7)
                    .clickBedroomsPlusButton(8)
                    .clickBathroomsPlusButton(9)
                    .clickBedsMinusButton(2)
                    .clickBedroomsMinusButton(4)
                    .clickBathroomsMinusButton(6);
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.bedsCurrentValue.shouldHave(Condition.text("5"));
            searchResultPage.bedroomsCurrentValue.shouldHave(Condition.text("4"));
            searchResultPage.bathroomsCurrentValue.shouldHave(Condition.text("3"));
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful search with all amenities")
    void allAmenitiesTest() {

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set all amenities checkboxes", () -> {
            searchResultPage
                    .clickBeachFrontCheckBox()
                    .clickFreeWiFiCheckBox()
                    .clickAirConditioningCheckBox()
                    .clickPetsAllowedCheckBox()
                    .clickStreetParkingCheckBox()
                    .clickSwimmingPoolCheckBox()
                    .clickKitchenCheckBox()
                    .clickWashingMachineCheckBox()
                    .clickHotTubCheckBox()
                    .clickSuitableForChildrenCheckBox()
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.someResultFounded.as("... properties found").shouldBe(Condition.visible);
            assertThat(getAppliedAmenitiesList()).size().isEqualTo(10);
            assertThat(getAppliedAmenitiesList()).contains(BEACH_FRONT.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(FREE_WIFI.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(AIR_CONDITIONING.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(PETS_ALLOWED.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(STREET_PARKING.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(SWIMMING_POOL.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(KITCHEN.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(WASHING_MACHINE.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(HOT_TUB.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(SUITABLE_FOR_CHILDREN.getParamValue());

        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful deselect amenities checkboxes")
    void deselectAmenitiesCheckboxesTest() {

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Select and deselect some amenities checkboxes", () -> {
            searchResultPage
                    .clickBeachFrontCheckBox()
                    .clickFreeWiFiCheckBox()
                    .clickAirConditioningCheckBox()
                    .clickPetsAllowedCheckBox()
                    .clickStreetParkingCheckBox()
                    .clickSwimmingPoolCheckBox()
                    .clickKitchenCheckBox()
                    .clickWashingMachineCheckBox()
                    .clickHotTubCheckBox()
                    .clickSuitableForChildrenCheckBox()
            //deselect some:
                    .clickFreeWiFiCheckBox()
                    .clickPetsAllowedCheckBox()
                    .clickSwimmingPoolCheckBox()
                    .clickHotTubCheckBox()
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.someResultFounded.as("... properties found").shouldBe(Condition.visible);
            assertThat(getAppliedAmenitiesList()).size().isEqualTo(6);
            assertThat(getAppliedAmenitiesList()).contains(BEACH_FRONT.getParamValue());
            assertThat(getAppliedAmenitiesList()).doesNotContain(FREE_WIFI.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(AIR_CONDITIONING.getParamValue());
            assertThat(getAppliedAmenitiesList()).doesNotContain(PETS_ALLOWED.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(STREET_PARKING.getParamValue());
            assertThat(getAppliedAmenitiesList()).doesNotContain(SWIMMING_POOL.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(KITCHEN.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(WASHING_MACHINE.getParamValue());
            assertThat(getAppliedAmenitiesList()).doesNotContain(HOT_TUB.getParamValue());
            assertThat(getAppliedAmenitiesList()).contains(SUITABLE_FOR_CHILDREN.getParamValue());
        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("Successful search after \"clear all\"")
    void searchAfterClearAllTest() {

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set all types of values and clear all", () -> {
            searchResultPage
                    .setPriceFrom("10")
                    .setPriceTo("500")
                    .clickBedsPlusButton(2)
                    .clickBedroomsPlusButton(3)
                    .clickBathroomsPlusButton(4)
                    .clickBeachFrontCheckBox()
                    .clickFreeWiFiCheckBox()
                    .clickAirConditioningCheckBox()
                    .clickPetsAllowedCheckBox()
                    .clickStreetParkingCheckBox()
                    .clickSwimmingPoolCheckBox()
                    .clickKitchenCheckBox()
                    .clickWashingMachineCheckBox()
                    .clickHotTubCheckBox()
                    .clickSuitableForChildrenCheckBox()
                    .clickClearAllButton()
                    .clickApplyButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.someResultFounded.as("... properties found").shouldBe(Condition.visible);
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(AMENITIES.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_FROM.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_TO.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BEDS.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BEDROOMS.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BATHROOMS.getParamName());

        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("Filters form")
    @Story("Filtering search result page")
    @DisplayName("No new search after \"close filter popup\" without apply")
    void noNewSearchWithoutApplyTest() {

        step("Open filters form on search result page (prepare for test)", () -> {
            searchResultPage.openSearchResultPage()
                    .clickOpenFiltersFormButton();
        });

        step("Set all types of values and close popup without apply", () -> {
            searchResultPage
                    .setPriceFrom("10")
                    .setPriceTo("500")
                    .clickBedsPlusButton(2)
                    .clickBedroomsPlusButton(3)
                    .clickBathroomsPlusButton(4)
                    .clickBeachFrontCheckBox()
                    .clickFreeWiFiCheckBox()
                    .clickAirConditioningCheckBox()
                    .clickPetsAllowedCheckBox()
                    .clickStreetParkingCheckBox()
                    .clickSwimmingPoolCheckBox()
                    .clickKitchenCheckBox()
                    .clickWashingMachineCheckBox()
                    .clickHotTubCheckBox()
                    .clickSuitableForChildrenCheckBox()
                    .clickCloseFiltersFormPopupButton();
        });

        step("Check filtering result and correct values in request", () -> {
            searchResultPage.someResultFounded.as("... properties found").shouldBe(Condition.visible);
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(AMENITIES.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_FROM.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(PRICE_TO.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BEDS.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BEDROOMS.getParamName());
            assertThat(getWebDriver().getCurrentUrl()).doesNotContain(NUMBER_OF_BATHROOMS.getParamName());

        });
    }

    @Test
    @Owner("iignatov")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("UI")
    @Feature("All listing page")
    @Story("Display all listings")
    @DisplayName("Successful display all of listed items")
    void displayAllListedItems() {
        step("Open all listing page (prepare for test)", () -> {
            allListingsPage.openAllListingsPage()
                    .scroolToLastExistingListing();
        });

        step("Assert all of listed items are displayed", () -> {
            assertThat($$x("//a[contains(@href, '/listings/')]").size())
                    .isEqualTo(Integer.parseInt
                            (allListingsPage.countOfListings.text().split("\\(")[1].split("\\)")[0]));

        });
    }
}
