import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @BeforeEach
public void setUp() {
    open("http://localhost:9999");
    $("[data-test-id=date] .input__control").sendKeys(Keys.CONTROL + "A");
    $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
}

    @Test
    void shouldOrderCard() {
        $("[data-test-id=city] .input__control").setValue("Екатеринбург");
        String dateDelivery = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] .input__control").setValue(dateDelivery);
        $("[data-test-id=name] .input__control").setValue("Федотов Александр");
        $("[data-test-id=phone] .input__control").setValue("+79003824451");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=notification] .notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + dateDelivery), Duration.ofSeconds(15));
    }

}
