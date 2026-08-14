package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

public class TextBoxTest extends TestBase {
    @Test
    @DisplayName("Успешная регистрация с заполнением всех полей формы")
    void allFieldsFilled() {
        open("/text-box");
        $("[id=userName]").setValue(fullName);
        $("[id=userEmail]").setValue(userEmail);
        $("[id=currentAddress]").setValue(currentAddress);
        $("[id=permanentAddress]").setValue(permanentAddress);
        executeJavaScript("document.querySelector('footer').remove();");
        $("[id=submit]").scrollTo().click();
        $("[id=output]").shouldBe(visible);
        $("[id=name]").shouldHave(text("Name:" + fullName));
        $("[id=email]").shouldHave(text("Email:" + userEmail));
        $("[id=output]").find("[id=currentAddress]").shouldHave(text("Current Address :" + currentAddress));
        $("[id=output]").find("[id=permanentAddress]").shouldHave(text("Permananet Address :" + permanentAddress));


}

    @Test
    @DisplayName("Заполнение поля имейл цифрами")
    void wrongEmailFilled() {
        open("/text-box");
        $("[id=userName]").setValue(fullName);
        $("[id=userEmail]").setValue(wrongEmail);
        $("[id=currentAddress]").setValue(currentAddress);
        $("[id=permanentAddress]").setValue(permanentAddress);
        executeJavaScript("document.querySelector('footer').remove();");
        $("[id=submit]").scrollTo().click();
        $("[id=output]").shouldNotBe(visible);

    }

}