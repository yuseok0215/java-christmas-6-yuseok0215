package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.discount.Event.ChristmasDdayDiscount;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class ChristmasDdayDiscountTest {

    @Test
    void 크리스마스_디데이_할인을_받을_수_있는_경우() {
        ChristmasDdayDiscount discountCalculator = new ChristmasDdayDiscount();
        HashMap<String, Integer> discountCategory = new HashMap<>();

        int validVisitDate = 15;
        int discountAmount = discountCalculator.christmasDdayDiscount(validVisitDate, discountCategory);

        assertTrue(discountCategory.containsKey("크리스마스 디데이 할인"));
        assertEquals(discountAmount, discountCategory.get("크리스마스 디데이 할인"));
        assertTrue(discountAmount > 0);
    }

    @Test
    void 크리스마스_디데이_할인을_받을_수_없는_경우() {
        ChristmasDdayDiscount discountCalculator = new ChristmasDdayDiscount();
        HashMap<String, Integer> discountCategory = new HashMap<>();

        int validVisitDate = 30;
        int discountAmount = discountCalculator.christmasDdayDiscount(validVisitDate, discountCategory);

        assertFalse(discountCategory.containsKey("크리스마스 디데이 할인"));
        assertEquals(0, discountAmount);
    }
}
