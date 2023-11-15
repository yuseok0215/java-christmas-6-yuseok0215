package christmas.domain.discount.Event;

import christmas.option.Menu;
import java.util.Map;

public class WeekdayDiscount {
    public int weekdayDiscount(Map<String, Integer> menuNameAndQuantity, String dayOfWeekKorean, Map<String, Integer> discountCategory) {
        int discountPrice = 0;
        if (dayOfWeekKorean.equals("일요일") || dayOfWeekKorean.equals("월요일") || dayOfWeekKorean.equals("화요일") || dayOfWeekKorean.equals("수요일")
                || dayOfWeekKorean.equals("목요일")) {
            discountPrice = checkDessertMenu(menuNameAndQuantity, discountPrice);
        }
        if (discountPrice != 0) {
            discountCategory.put("평일 할인", discountPrice);
        }
        return discountPrice;
    }

    private int checkDessertMenu(Map<String, Integer> menuNameAndQuantity, int discountPrice) {
        for (Map.Entry<String, Integer> entry : menuNameAndQuantity.entrySet()) {
            String menuName = entry.getKey();
            Integer menuQuantity = entry.getValue();

            String menuCategory = Menu.getMenuCategory(menuName);

            if (menuCategory.equals("Dessert")) {
                discountPrice += 2023 * menuQuantity;
            }
        }
        return discountPrice;
    }
}
