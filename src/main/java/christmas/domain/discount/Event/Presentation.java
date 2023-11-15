package christmas.domain.discount.Event;

import christmas.option.Event;
import christmas.view.Output;
import java.util.Map;

public class Presentation {

    public int applyPresentationEvent(int totalPriceBeforeDiscount, Map<String, Integer> discountCategory) {
        if (totalPriceBeforeDiscount >= 120000) {
            discountCategory.put(Event.PRESENTATION.getEventName(), 25000);
            Output.announcePresentChampagne();
            return 25000;
        }
        return 0;
    }
}
