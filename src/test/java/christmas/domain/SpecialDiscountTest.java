package christmas.domain;

import christmas.domain.discount.DiscountCategory;
import christmas.domain.discount.Event.SpecialDiscount;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpecialDiscountTest {

    @Test
    void 특별_할인_테스트() {
        SpecialDiscount specialDiscount = new SpecialDiscount();

        Map<String, Integer> discountCategory = new HashMap<>();
        int validVisitDate = 3;
        int discountAmount = specialDiscount.specialDiscount(validVisitDate, discountCategory);

        Assertions.assertEquals(1000, discountAmount);
        Assertions.assertEquals(1000, discountCategory.get("특별 할인"));
        Assertions.assertTrue(discountCategory.containsKey("특별 할인"));

        discountCategory.clear();

        int invalidVisitDate = 11;
        discountAmount = specialDiscount.specialDiscount(invalidVisitDate, discountCategory);

        Assertions.assertEquals(0, discountAmount);
        Assertions.assertFalse(discountCategory.containsKey("특별 할인"));

    }
}
