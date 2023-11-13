package christmas.view;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

public class Output {

    public static void announceStartEventCheck() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void announceOrderMenu(Map<String, Integer> menuNameAndQuantity) {
        System.out.println("\n<주문 메뉴>");

        for (Map.Entry<String, Integer> entry : menuNameAndQuantity.entrySet()) {
            String menuName = entry.getKey();
            Integer menuQuantity = entry.getValue();
            System.out.println(menuName + " " + menuQuantity + "개");
        }
    }

    public static void announceOrderAmountBeforeDiscount(int totalPriceBeforeDiscount) {
        System.out.println("\n<할인 전 총주문 금액>");

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(totalPriceBeforeDiscount);
        System.out.println(formattedPrice + "원");
    }

    public static void announcePresentChampagne() {
        System.out.println("샴페인 1개");
    }

    public static void announcePresentAbsence() {
        System.out.println("없음");
    }

    public static void announceBenefits(Entry<String, Integer> entry, String formattedValue) {
        System.out.println(entry.getKey() + ": -" + formattedValue + "원");
    }

    public static void announceTotalBenefitAmount(String totalBenefitAmount) {
        System.out.println(totalBenefitAmount + "원");
    }

    public static void announcePaymentAmountAfterDiscount(String paymentAmount) {
        System.out.println(paymentAmount + "원");
    }

    public static void announceEventBadge(int totalDiscountPrice) {
        if (totalDiscountPrice >= 20000) {
            System.out.println("산타");
        } else if (totalDiscountPrice >= 10000) {
            System.out.println("트리");
        } else if (totalDiscountPrice >= 5000) {
            System.out.println("별");
        }
    }
}
