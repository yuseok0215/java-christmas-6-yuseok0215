package christmas.domain.discount.discountCategory;

import java.util.List;
import java.util.Map;

public class SpecialDiscount {
    public int specialDiscount(int visitDate, Map<String, Integer> discountCategory) {
        List<Integer> starDays = List.of(3, 10, 17, 24, 25);
        if (starDays.contains(visitDate)) {
            discountCategory.put("특별 할인", 1000);
            return 1000;
        }
        return 0;
    }
}
