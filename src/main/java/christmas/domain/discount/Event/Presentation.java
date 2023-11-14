package christmas.domain.discount.Event;

import christmas.view.Output;
import java.util.Map;

public class Presentation {

    public int presentationEvent(int totalPriceBeforeDiscount, Map<String, Integer> discountCategory) {
        if (totalPriceBeforeDiscount >= 120000) {
            discountCategory.put("증정 이벤트", 25000);
            Output.announcePresentChampagne();
            return 25000;
        }
        return 0;
    }
}
