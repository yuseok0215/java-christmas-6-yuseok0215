package christmas.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class TypeConverterTest {

    @Test
    void 금액의_포맷팅_테스트() {
        Map<String, Integer> discountCategory = new HashMap<>();
        discountCategory.put("크리스마스 디데이 할인", 1200);
        discountCategory.put("평일 할인", 4046);

        Map<String, String> result = TypeConverter.convertDiscountAmount(discountCategory);

        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("크리스마스 디데이 할인", "1,200");
        expected.put("평일 할인", "4,046");

        assertEquals(expected, result);
    }

    @Test
    void 문자열을_메뉴와_개수로_나누는_테스트() {
        Map<String, Integer> menuNameAndQuantity = new HashMap<>();
        String stringMenu = "티본스테이크-1,바비큐립-1,초코케이크-2";

        Map<String, Integer> result = TypeConverter.convertStringMenu(menuNameAndQuantity, stringMenu);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("티본스테이크", 1);
        expected.put("바비큐립", 1);
        expected.put("초코케이크", 2);

        assertEquals(expected, result);
    }
}
