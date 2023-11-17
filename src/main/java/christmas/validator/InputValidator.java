package christmas.validator;

import christmas.option.Menu;
import java.util.HashMap;
import java.util.Map;

public class InputValidator {

    public static void validateVisitDate(int visitDate) {
        if (visitDate < 1 || visitDate > 31) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateOrderMenuName(Map<String, Integer> orderMenuAndQuantity) {
        Map<String, Integer> tempOrderMenuAndQuantity = new HashMap<>();

        for (Map.Entry<String, Integer> entry : orderMenuAndQuantity.entrySet()) {
            String menuName = entry.getKey();
            int menuCount = entry.getValue();

            validateMenu(menuName, menuCount, tempOrderMenuAndQuantity);
            tempOrderMenuAndQuantity.put(menuName, menuCount);

        }
    }

    private static void validateMenu(String menuName, int menuCount, Map<String, Integer> menuNameAndQuantity) {
        if (!Menu.isMenuValid(menuName)) {
            throw new IllegalArgumentException();
        }

        if (menuCount < 1) {
            throw new IllegalArgumentException();
        }

        if (menuNameAndQuantity.containsKey(menuName)) {
            throw new IllegalArgumentException();
        }
    }
}
