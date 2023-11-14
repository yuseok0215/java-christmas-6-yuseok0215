package christmas.domain.discount;

import christmas.converter.Converter;
import christmas.domain.discount.Event.ChristmasDdayDiscount;
import christmas.domain.discount.Event.Presentation;
import christmas.domain.discount.Event.SpecialDiscount;
import christmas.domain.discount.Event.WeekdayDiscount;
import christmas.domain.discount.Event.WeekendDiscount;
import christmas.view.Output;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DiscountCategory {

    private final Map<String, Integer> discountCategory;
    private final ChristmasDdayDiscount christmasDdayDiscount;
    private final SpecialDiscount specialDiscount;
    private final WeekdayDiscount weekdayDiscount;
    private final WeekendDiscount weekendDiscount;
    private final Presentation presentation;

    public DiscountCategory() {
        this.discountCategory = new LinkedHashMap<>();
        this.christmasDdayDiscount = new ChristmasDdayDiscount();
        this.specialDiscount = new SpecialDiscount();
        this.weekdayDiscount = new WeekdayDiscount();
        this.weekendDiscount = new WeekendDiscount();
        this.presentation = new Presentation();
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
        return presentation.presentationEvent(totalPriceBeforeDiscount, discountCategory);
    }

    public boolean hasPresentationEvent(String eventType) {
        return discountCategory.containsKey(eventType);
    }

    public Map<String, String> getDiscountAmount() {
        return Converter.convertDiscountAmount(discountCategory);
    }
}
