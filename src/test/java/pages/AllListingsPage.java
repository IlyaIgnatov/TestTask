package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AllListingsPage {
    public SelenideElement
            allListingPageWelcomeMsg = $(byText("Properties")),
            throbber = $x("//div[contains(@style, 'text-align: center;')]"),
            countOfListings = $x("//span[text()='All']").lastChild();


    @Step("Open the all listings page")
    public AllListingsPage openAllListingsPage() {
        open(UI_Endpoints.ALL_LISTING);
        isAllListingsPageOpened();
        return this;
    }

    @Step("Check that the filter page is opened")
    public AllListingsPage isAllListingsPageOpened() {
        allListingPageWelcomeMsg.shouldBe(Condition.visible);
        return this;
    }

    @Step("Check that the filter page is opened")
    public AllListingsPage scroolToLastExistingListing() {
        int countOfListingBeforeScroll;
        int countOfListingAfterScroll = 0;
        do {
            countOfListingBeforeScroll=countOfListingAfterScroll;
            Selenide.sleep(1000);
            throbber.shouldNotBe(Condition.visible);
            countOfListingAfterScroll = $$x("//a[contains(@href, '/listings/')]").size();
            $$x("//a[contains(@href, '/listings/')]").last().scrollIntoView(true);
        }
        while (countOfListingAfterScroll > countOfListingBeforeScroll);
        return this;
    }
}

