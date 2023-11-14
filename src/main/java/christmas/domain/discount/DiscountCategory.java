package christmas.domain.discount;

import christmas.Menu;
import christmas.domain.discount.discountCategory.ChristmasDdayDiscount;
import christmas.domain.discount.discountCategory.SpecialDiscount;
import christmas.domain.discount.discountCategory.WeekdayDiscount;
import christmas.domain.discount.discountCategory.WeekendDiscount;
import christmas.view.Output;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DiscountCategory {

    private final Map<String, Integer> discountCategory;
    private final ChristmasDdayDiscount christmasDdayDiscount;
    private final SpecialDiscount specialDiscount;
    private final WeekdayDiscount weekdayDiscount;
    private final WeekendDiscount weekendDiscount;

    public DiscountCategory() {
        this.discountCategory = new LinkedHashMap<>();
        this.christmasDdayDiscount = new ChristmasDdayDiscount();
        this.specialDiscount = new SpecialDiscount();
        this.weekdayDiscount = new WeekdayDiscount();
        this.weekendDiscount = new WeekendDiscount();
    }

    public int christmasDdayDiscount(int visitDate) {
        return christmasDdayDiscount.christmasDdayDiscount(visitDate, discountCategory);
    }

    public int specialDiscount(int visitDate) {
        return specialDiscount.specialDiscount(visitDate, discountCategory);
    }

    public int weekdayDiscount(Map<String, Integer> menuNameAndQuantity, String dayOfWeekKorean) {
        return weekdayDiscount.weekdayDiscount(menuNameAndQuantity, dayOfWeekKorean, discountCategory);
    }

    public int weekendDiscount(Map<String, Integer> menuNameAndQuantity, String dayOfWeekKorean) {
        return weekendDiscount.weekendDiscount(menuNameAndQuantity, dayOfWeekKorean, discountCategory);
    }

    public int presentationDiscount(int totalPriceBeforeDiscount) {
        if (totalPriceBeforeDiscount >= 120000) {
            discountCategory.put("증정 이벤트", 25000);
            Output.announcePresentChampagne();
            return 25000;
        }
        return 0;
    }

    public boolean hasPresentationEvent(String eventType) {
        return discountCategory.containsKey(eventType);
    }

    public Map<String, String> convertDiscountAmount() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        return discountCategory.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> decimalFormat.format(entry.getValue())
                ));
    }
}
