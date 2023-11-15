package christmas.domain.Event;

import christmas.domain.discount.Event.Presentation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PresentationTest {

    @Test
    void 증정_이벤트_테스트() {
        Presentation presentation = new Presentation();

        int validTotalPriceBeforeDiscount = 130000;
        Map<String, Integer> discountCategory = new HashMap<>();

        int discountAmount = presentation.presentationEvent(validTotalPriceBeforeDiscount, discountCategory);

        Assertions.assertEquals(25000, discountAmount);
        Assertions.assertEquals(25000, discountCategory.get("증정 이벤트"));
        Assertions.assertTrue(discountCategory.containsKey("증정 이벤트"));

        discountCategory.clear();

        int invalidTotalPriceBeforeDiscount = 110000;

        discountAmount = presentation.presentationEvent(invalidTotalPriceBeforeDiscount, discountCategory);

        Assertions.assertEquals(0, discountAmount);
        Assertions.assertFalse(discountCategory.containsKey("증정 이벤트"));
    }
}
