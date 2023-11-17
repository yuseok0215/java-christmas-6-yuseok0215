package christmas.domain.discount.Event;

import christmas.option.Event;
import christmas.option.Menu;
import java.util.Map;

public class WeekdayDiscount {

    public int applyWeekdayDiscount(Map<String, Integer> menuNameAndQuantity, String dayOfWeekKorean, Map<String, Integer> discountCategory) {
        int discountPrice = 0;
        if (isWeekday(dayOfWeekKorean)) {
            discountPrice = checkDessertMenu(menuNameAndQuantity, discountPrice);
        }
        if (discountPrice != 0) {
            discountCategory.put(Event.WEEKDAY.getEventName(), discountPrice);
        }
        return discountPrice;
    }

    private boolean isWeekday(String dayOfWeekKorean) {
        return dayOfWeekKorean.equals("일요일") || dayOfWeekKorean.equals("월요일") || dayOfWeekKorean.equals("화요일")
                || dayOfWeekKorean.equals("수요일")
                || dayOfWeekKorean.equals("목요일");
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
