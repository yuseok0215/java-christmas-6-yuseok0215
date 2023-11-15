package christmas.view;

import christmas.message.ErrorMessage;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

public class Output {

    public static void announceStartEventCheck() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void announceEventBenefitPreview(int visitDate) {
        System.out.println("12월 " + visitDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
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
        System.out.println("\n<증정 메뉴>");
        System.out.println("샴페인 1개");
    }

    public static void announcePresentAbsence() {
        System.out.println("\n<증정 메뉴>");
        System.out.println("없음");
    }

    public static void announceBenefitDetails(Map<String, String> benefitDetails) {
        if(benefitDetails.size() == 0) {
            System.out.println("없음");
            return;
        }
        benefitDetails.forEach(Output::announceBenefits);
    }

    private static void announceBenefits(String benefitType, String formattedAmount) {
        System.out.println(benefitType + ": -" + formattedAmount + "원");
    }

    public static void announceTotalBenefitAmount(String totalBenefitAmount) {
        System.out.println("\n<총혜택 금액>");
        System.out.println(totalBenefitAmount + "원");
    }

    public static void announcePaymentAmountAfterDiscount(String paymentAmount) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(paymentAmount + "원");
    }

    public static void announceEventBadge(String badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge);
    }

    public static void announceStartBenefits() {
        System.out.println("\n<혜택 내역>");
    }

    public static void announceErrorMessage(ErrorMessage errorMessage) {
        System.out.println(errorMessage.getMessage());
    }

}
