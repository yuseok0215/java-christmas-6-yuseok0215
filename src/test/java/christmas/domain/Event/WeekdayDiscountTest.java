package christmas.domain.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.discount.Event.WeekdayDiscount;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class WeekdayDiscountTest {

    @Test
    void 평일_할인_이벤트_테스트1() {
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();

        Map<String, Integer> menuNameAndQuantity = new HashMap<>();
        menuNameAndQuantity.put("초코케이크", 2);
        menuNameAndQuantity.put("아이스크림", 1);

        String dayOfWeekKorean = "월요일";
        Map<String, Integer> discountCategory = new HashMap<>();

        int discountAmount = weekdayDiscount.applyWeekdayDiscount(menuNameAndQuantity, dayOfWeekKorean, discountCategory);

        assertEquals(2023 * 3, discountAmount);
        assertTrue(discountCategory.containsKey("평일 할인"));
        assertEquals(2023 * 3, discountCategory.get("평일 할인"));
    }

    @Test
    void 평일_할인_이벤트_테스트2() {
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();

        Map<String, Integer> menuNameAndQuantity = new HashMap<>();
        menuNameAndQuantity.put("양송이수프", 2);
        menuNameAndQuantity.put("해산물파스타", 1);

        String dayOfWeekKorean = "월요일";
        Map<String, Integer> discountCategory = new HashMap<>();

        int discountAmount = weekdayDiscount.applyWeekdayDiscount(menuNameAndQuantity, dayOfWeekKorean, discountCategory);

        assertEquals(0, discountAmount);
        assertFalse(discountCategory.containsKey("평일 할인"));
    }

    @Test
    void 평일_할인_이벤트_테스트3() {
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();

        Map<String, Integer> menuNameAndQuantity = new HashMap<>();
        menuNameAndQuantity.put("초코케이크", 2);
        menuNameAndQuantity.put("아이스크림", 1);

        String dayOfWeekKorean = "토요일";
        Map<String, Integer> discountCategory = new HashMap<>();

        int discountAmount = weekdayDiscount.applyWeekdayDiscount(menuNameAndQuantity, dayOfWeekKorean, discountCategory);

        assertEquals(0, discountAmount);
        assertFalse(discountCategory.containsKey("평일 할인"));
    }
}
