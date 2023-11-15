package christmas.domain.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.discount.Event.WeekdayDiscount;
import christmas.domain.discount.Event.WeekendDiscount;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class WeekendDiscountTest {

    @Test
    void 주말_할인_이벤트_테스트1() {
        WeekendDiscount weekendDiscount = new WeekendDiscount();

        Map<String, Integer> menuNameAndQuantity = new HashMap<>();
        menuNameAndQuantity.put("해산물파스타", 2);
        menuNameAndQuantity.put("크리스마스파스타", 1);

        String dayOfWeekKorean = "토요일";
        Map<String, Integer> discountCategory = new HashMap<>();

        int discountAmount = weekendDiscount.weekendDiscount(menuNameAndQuantity, dayOfWeekKorean, discountCategory);

        assertEquals(2023 * 3, discountAmount);
        assertTrue(discountCategory.containsKey("주말 할인"));
        assertEquals(2023 * 3, discountCategory.get("주말 할인"));
    }

    @Test
    void 주말_할인_이벤트_테스트2() {
        WeekendDiscount weekendDiscount = new WeekendDiscount();

        Map<String, Integer> menuNameAndQuantity = new HashMap<>();
        menuNameAndQuantity.put("아이스크림", 2);
        menuNameAndQuantity.put("레드와인", 1);

        String dayOfWeekKorean = "토요일";
        Map<String, Integer> discountCategory = new HashMap<>();

        int discountAmount = weekendDiscount.weekendDiscount(menuNameAndQuantity, dayOfWeekKorean, discountCategory);

        assertEquals(0, discountAmount);
        assertFalse(discountCategory.containsKey("주말 할인"));
    }

    @Test
    void 주말_할인_이벤트_테스트3() {
        WeekendDiscount weekendDiscount = new WeekendDiscount();

        Map<String, Integer> menuNameAndQuantity = new HashMap<>();
        menuNameAndQuantity.put("해산물파스타", 2);
        menuNameAndQuantity.put("크리스마스파스타", 1);

        String dayOfWeekKorean = "화요일";
        Map<String, Integer> discountCategory = new HashMap<>();

        int discountAmount = weekendDiscount.weekendDiscount(menuNameAndQuantity, dayOfWeekKorean, discountCategory);

        assertEquals(0, discountAmount);
        assertFalse(discountCategory.containsKey("주말 할인"));
    }
}
