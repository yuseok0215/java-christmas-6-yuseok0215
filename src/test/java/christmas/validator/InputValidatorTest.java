package christmas.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {

    @Test
    public void 올바른_날짜_입력에_대한_테스트() {
        assertDoesNotThrow(() -> InputValidator.validateVisitDate(15));
    }

    @Test
    public void 올바르지_않은_날짜_입력에_대한_테스트() {
        assertThrows(IllegalArgumentException.class, () -> InputValidator.validateVisitDate(0));

        assertThrows(IllegalArgumentException.class, () -> InputValidator.validateVisitDate(32));
    }

    @Test
    public void 올바른_주문_메뉴_입력에_대한_예외처리_테스트() {
        Map<String, Integer> orderMenuAndQuantity = new HashMap<>();
        orderMenuAndQuantity.put("해산물파스타", 2);
        orderMenuAndQuantity.put("아이스크림", 1);

        assertDoesNotThrow(() -> InputValidator.validateOrderMenuName(orderMenuAndQuantity));
    }

    @Test
    public void 올바르지_않은_주문_메뉴_입력에_대한_예외처리_테스트() {
        Map<String, Integer> orderMenuAndQuantity = new HashMap<>();
        orderMenuAndQuantity.put("해산물 파스타", 2);
        orderMenuAndQuantity.put("아이스크림", 1);
        orderMenuAndQuantity.put("해산물 파스타", 3);

        assertThrows(IllegalArgumentException.class, () -> InputValidator.validateOrderMenuName(orderMenuAndQuantity));
    }
}
