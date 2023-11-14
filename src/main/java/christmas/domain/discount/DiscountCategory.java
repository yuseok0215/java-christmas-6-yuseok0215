package christmas.domain.discount;

import christmas.Menu;
import christmas.view.Output;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DiscountCategory {

    private final Map<String, Integer> discountCategory;

    public DiscountCategory() {
        this.discountCategory = new LinkedHashMap<>();
    }

    public int christmasDdayDiscount(int visitDate) {
        if(visitDate >= 1 && visitDate <= 25) {
            int discountAmount = (1000 + (visitDate - 1) * 100);
            discountCategory.put("크리스마스 디데이 할인", discountAmount);
            return discountAmount;
        }
        return 0;
    }


    public int specialDiscount(int visitDate) {
        List<Integer> starDays = List.of(3, 10, 17, 24, 25);
        if (starDays.contains(visitDate)) {
            discountCategory.put("특별 할인", 1000);
            return 1000;
        }
        return 0;
    }

    public int weekdayDiscount(Map<String, Integer> menuNameAndQuantity, String dayOfWeekKorean) {
        int discountPrice = 0;
        if (dayOfWeekKorean.equals("일요일") || dayOfWeekKorean.equals("월요일") || dayOfWeekKorean.equals("화요일") || dayOfWeekKorean.equals("수요일")
                || dayOfWeekKorean.equals("목요일")) {
            for (Map.Entry<String, Integer> entry : menuNameAndQuantity.entrySet()) {
                String menuName = entry.getKey();
                Integer menuQuantity = entry.getValue();

                String menuCategory = Menu.getMenuCategory(menuName);

                if (menuCategory.equals("Dessert")) {
                    discountPrice += 2023 * menuQuantity;
                }
            }
        }
        if (discountPrice != 0) {
            discountCategory.put("평일 할인", discountPrice);
        }
        return discountPrice;
    }

    public int weekendDiscount(Map<String, Integer> menuNameAndQuantity, String dayOfWeekKorean) {
        int discountPrice = 0;
        if (dayOfWeekKorean.equals("금요일") || dayOfWeekKorean.equals("토요일")) {
            for (Map.Entry<String, Integer> entry : menuNameAndQuantity.entrySet()) {
                String menuName = entry.getKey();
                Integer menuQuantity = entry.getValue();

                String menuCategory = Menu.getMenuCategory(menuName);

                if (menuCategory.equals("Main")) {
                    discountPrice += 2023 * menuQuantity;
                }
            }
        }
        if (discountPrice != 0) {
            discountCategory.put("주말 할인", discountPrice);
        }
        return discountPrice;
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
