package christmas.domain.discount.discountCategory;

import java.util.Map;

public class ChristmasDdayDiscount {
    public int christmasDdayDiscount(int visitDate, Map<String, Integer> discountCategory) {
        if(visitDate >= 1 && visitDate <= 25) {
            int discountAmount = (1000 + (visitDate - 1) * 100);
            discountCategory.put("크리스마스 디데이 할인", discountAmount);
            return discountAmount;
        }
        return 0;
    }
}
