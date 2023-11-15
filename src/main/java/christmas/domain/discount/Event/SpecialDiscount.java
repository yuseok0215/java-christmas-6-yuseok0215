package christmas.domain.discount.Event;

import christmas.option.Event;
import java.util.List;
import java.util.Map;

public class SpecialDiscount {
    public int applySpecialDiscount(int visitDate, Map<String, Integer> discountCategory) {
        List<Integer> starDays = List.of(3, 10, 17, 24, 25);
        if (starDays.contains(visitDate)) {
            discountCategory.put(Event.SPECIAL.getEventName(), 1000);
            return 1000;
        }
        return 0;
    }
}
