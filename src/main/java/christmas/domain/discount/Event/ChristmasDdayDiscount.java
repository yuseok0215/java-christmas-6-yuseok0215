package christmas.domain.discount.Event;

import christmas.option.Event;
import java.util.Map;

public class ChristmasDdayDiscount {
    public int applyChristmasDdayDiscount(int visitDate, Map<String, Integer> discountCategory) {
        if(visitDate >= 1 && visitDate <= 25) {
            int discountAmount = (1000 + (visitDate - 1) * 100);
            discountCategory.put(Event.CHRISTMAS.getEventName(), discountAmount);
            return discountAmount;
        }
        return 0;
    }
}
