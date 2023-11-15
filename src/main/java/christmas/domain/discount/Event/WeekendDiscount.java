package christmas.domain.discount.Event;

import christmas.Menu;
import java.util.Map;

public class WeekendDiscount {

    public int weekendDiscount(Map<String, Integer> menuNameAndQuantity, String dayOfWeekKorean, Map<String, Integer> discountCategory) { int discountPrice = 0;
        if (dayOfWeekKorean.equals("금요일") || dayOfWeekKorean.equals("토요일")) {
            discountPrice = checkMainMenu(menuNameAndQuantity, discountPrice);
        }
        if (discountPrice != 0) {
            discountCategory.put("주말 할인", discountPrice);
        }
        return discountPrice;
    }

    private int checkMainMenu(Map<String, Integer> menuNameAndQuantity, int discountPrice) {
        for (Map.Entry<String, Integer> entry : menuNameAndQuantity.entrySet()) {
            String menuName = entry.getKey();
            Integer menuQuantity = entry.getValue();

            String menuCategory = Menu.getMenuCategory(menuName);

            if (menuCategory.equals("Main")) {
                discountPrice += 2023 * menuQuantity;
            }
        }
        return discountPrice;
    }
}
