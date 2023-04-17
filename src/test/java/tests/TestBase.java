package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.AllureAttachments;
import helpers.DriverSettings;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;



@ExtendWith({AllureJunit5.class})
public class TestBase {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();
        Configuration.baseUrl = "https://kamil-demo.alpinizm.uz";
    }

    @BeforeEach
     void beforeEach(){
    }

    @AfterEach
    public void addAttachments() {
        AllureAttachments.addScreenshotAs("Last screenshot");
        System.out.println("last screenshot....");
        AllureAttachments.addPageSource();
        System.out.println("addPageSource....");
        AllureAttachments.addBrowserConsoleLogs();
        System.out.println("addBrowserConsoleLogs....");

        Selenide.closeWebDriver();
        System.out.println("closeWebDriver....");
    }

    @AfterAll
    static void cleanUp(){
    }
}
