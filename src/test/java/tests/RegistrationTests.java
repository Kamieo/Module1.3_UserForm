package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

public class RegistrationTests extends TestBase {


    @Test
    @DisplayName("Успешная регистрация с заполнением всех полей формы")
    void allFieldsFilled() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=userEmail]").setValue(userEmail);
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue(phoneNumber);
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(3);
        $(".react-datepicker__year-select").selectOption(birthYear);
        $(".react-datepicker__day--014").click();
        $("[id=subjectsInput]").setValue(subject).pressEnter();
        $("[id=hobbies-checkbox-3]").click();
        $("[id=uploadPicture]").uploadFromClasspath("foto.jpg");
        $("[id=currentAddress]").setValue(currentAddress);
        $("[id=react-select-3-input]").setValue(state).pressEnter();
        $("[id=react-select-4-input]").setValue(city).pressEnter();
        executeJavaScript("document.querySelector('footer').remove();");
        $("[id=submit]").scrollTo().click();
        $(".modal-content").shouldBe(visible);

    }

    @Test
    @DisplayName("Успешная регистрация с заполнением только обязательных полей формы")
    void onlyRequiredFieldsTest() {
        open("/automation-practice-form");
        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue(phoneNumber);
        executeJavaScript("document.querySelector('footer').remove();");
        $("[id=submit]").scrollIntoView("{block: \"end\"}").click();
        $(".modal-content").shouldBe(visible);

    }
    @Test
    @DisplayName("Заполнение поля имейл цифрами")
    void emailContainsOnlyNumbersTest() {
        open("/automation-practice-form");
        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=userEmail]").setValue(wrongEmail);
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue(phoneNumber);
        executeJavaScript("document.querySelector('footer').remove();");
        $("[id=submit]").scrollTo().click();
        $(".modal-content").shouldNotBe(visible);
    }
    @Test
    @DisplayName("Заполнение поля номер телефона буквами")
    void mobileNumberContainsLettersTest() {
        open("/automation-practice-form");
        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue(wrongNumber);
        executeJavaScript("document.querySelector('footer').remove();");
        $("[id=submit]").scrollTo().click();
        $(".modal-content").shouldNotBe(visible);
    }

    @Test
    @DisplayName("Форма не заполнена")
    void allFieldsAreEmpty() {
        open("/automation-practice-form");
        executeJavaScript("document.querySelector('footer').remove();");
        $("[id=submit]").scrollTo().click();
        $(".modal-content").shouldNotBe(visible);
    }
}

