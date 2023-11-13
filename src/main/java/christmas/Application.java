package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.Input;
import christmas.view.Output;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class Application {
    public static final int year = 2023;
    public static final int month = 12;

    public static void main(String[] args) {
        int visitDate = requestVisitDate();
        Map<String,Integer> menuNameAndQuantity = getMenuNameAndQuantity();

        Input.announceEventBenefitPreview();
        Output.announceOrderMenu(menuNameAndQuantity);

        int totalPriceBeforeDiscount = getTotalPriceBeforeDiscount(menuNameAndQuantity);
        Output.announceOrderAmountBeforeDiscount(totalPriceBeforeDiscount);


        Map<String, Integer> discountAmount = new LinkedHashMap<>();

        // 할인 금액을 구해보자!
        int christmasDdayDiscountAmount = christmasDdayDiscount(visitDate);
        discountAmount.put("크리스마스 디데이 할인", christmasDdayDiscountAmount);

        DayOfWeek dayOfWeek = getDayOfWeek(visitDate);
        String dayOfWeekKorean = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN); // 요일 알려주기

        discountAmount.put("평일 할인", weekdayDiscount(menuNameAndQuantity, dayOfWeekKorean));
        discountAmount.put("주말 할인", weekendDiscount(menuNameAndQuantity, dayOfWeekKorean));
        discountAmount.put("특별 할인", specialDiscount(visitDate));

        // 삼페인, 배지에 대한 증정 메뉴 알려주기\
        System.out.println("\n<증정 메뉴>");
        if (totalPriceBeforeDiscount >= 120000) {
            discountAmount.put("증정 이벤트", 25000);
            Output.announcePresentChampagne();
        }
        if (totalPriceBeforeDiscount < 120000) {
            Output.announcePresentAbsence();
        }

        int totalPaymentDiscountPrice = discountAmount.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("증정 이벤트"))
                .mapToInt(Map.Entry::getValue)
                .sum();

        int totalDiscountPrice = discountAmount.values().stream()
                .mapToInt(Integer::intValue)
                .sum();


        System.out.println("<혜택내역>");
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        discountAmount.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .forEach(entry -> {
                    String formattedValue = decimalFormat.format(entry.getValue());
                    Output.announceBenefits(entry, formattedValue);
                });

        System.out.println("<총혜택 금액>");
        Output.announceTotalBenefitAmount(decimalFormat.format(totalDiscountPrice));

        System.out.println("<할인 후 예상 결제 금액>");
        Output.announcePaymentAmountAfterDiscount(decimalFormat.format(totalPriceBeforeDiscount - totalPaymentDiscountPrice));

        System.out.println("<12월 이벤트 배지>");
        Output.announceEventBadge(totalDiscountPrice);
    }

    private static int requestVisitDate() {
        Output.announceStartEventCheck();
        return Input.requestVisitDate();
    }

    private static Map<String,Integer> getMenuNameAndQuantity() {
        try {
            return Input.requestOrderMenuAndQuantity();
        } catch (IllegalArgumentException e) {
            return getMenuNameAndQuantity();
        }
    }

    private static int specialDiscount(int visitDate) {
        List<Integer> starDays = List.of(3, 10, 17, 24, 25);
        if (starDays.contains(visitDate)) {
            return 1000;
        }
        return 0;
    }

    private static int weekendDiscount(Map<String, Integer> menuNameAndQuantity, String dayOfWeekKorean) {
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
        return discountPrice;
    }

    private static int weekdayDiscount(Map<String, Integer> menuNameAndQuantity, String dayOfWeekKorean) {
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
        return discountPrice;
    }

    private static DayOfWeek getDayOfWeek(int day) {
        return LocalDate.of(year, month, day).getDayOfWeek();
    }

    private static int christmasDdayDiscount(int visitDate) {
        if(visitDate >= 1 && visitDate <= 25) {
            return (1000 + (visitDate - 1) * 100);
        }
        return 0;
    }

    private static int TotalPriceBeforeDiscount(Map<String, Integer> menuNameAndQuantity) {
        int totalPriceBeforeDiscount = getTotalPriceBeforeDiscount(menuNameAndQuantity);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(totalPriceBeforeDiscount);
        System.out.println(formattedPrice + "원");
        return totalPriceBeforeDiscount;
    }

    private static int getTotalPriceBeforeDiscount(Map<String, Integer> menuNameAndQuantity) {
        int totalPriceBeforeDiscount = 0;
        for (Map.Entry<String, Integer> entry : menuNameAndQuantity.entrySet()) {
            String menuName = entry.getKey();
            int menuPrice = Menu.getMenuByName(menuName) * entry.getValue();

            totalPriceBeforeDiscount += menuPrice;
        }
        return totalPriceBeforeDiscount;
    }
}
