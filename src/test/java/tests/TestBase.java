package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TestBase {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
    }

    @AfterEach
    void afterEach(){closeWebDriver();
    }
}