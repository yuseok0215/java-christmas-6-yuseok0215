package christmas.view;

import static christmas.option.AnnounceMessage.*;

import christmas.message.ErrorMessage;
import java.text.DecimalFormat;
import java.util.Map;

public class Output {

    public static void announceStartEventCheck() {
        System.out.println(START_PLANNER.getMessage());
    }

    public static void announceEventBenefitPreview(int visitDate) {
        System.out.printf((EVENT_PREVIEW.getMessage()) + "%n", visitDate);
    }

    public static void announceOrderMenu(Map<String, Integer> menuNameAndQuantity) {
        System.out.println(ORDER_MENU.getMessage());

        for (Map.Entry<String, Integer> entry : menuNameAndQuantity.entrySet()) {
            String menuName = entry.getKey();
            Integer menuQuantity = entry.getValue();
            System.out.printf((MENU_QUANTITY.getMessage()) + "%n", menuName, menuQuantity);
        }
    }

    public static void announceOrderAmountBeforeDiscount(int totalPriceBeforeDiscount) {
        System.out.println(TOTAL_AMOUNT_BEFORE_DISCOUNT.getMessage());

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(totalPriceBeforeDiscount);
        System.out.printf((FORMATTED_AMOUNT.getMessage()) + "%n", formattedPrice);
    }

    public static void announcePresentChampagne() {
        System.out.println(PRESENTATION_MENU.getMessage());
        System.out.println(PRESENTATION_ITEM.getMessage());
    }

    public static void announcePresentAbsence() {
        System.out.println(PRESENTATION_MENU.getMessage());
        System.out.println(EMPTY.getMessage());
    }

    public static void announceBenefitDetails(Map<String, String> benefitDetails) {
        if(benefitDetails.size() == 0) {
            System.out.println(EMPTY.getMessage());
            return;
        }
        benefitDetails.forEach(Output::announceBenefits);
    }

    private static void announceBenefits(String benefitType, String formattedAmount) {
        System.out.printf((DISCOUNT_AMOUNT.getMessage()) + "%n", benefitType, formattedAmount);
    }

    public static void announceTotalBenefitAmount(String totalBenefitAmount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT.getMessage());
        System.out.printf((FORMATTED_AMOUNT.getMessage()) + "%n", totalBenefitAmount);
    }

    public static void announcePaymentAmountAfterDiscount(String paymentAmount) {
        System.out.println(EXPECTED_AMOUNT_AFTER_DISCOUNT.getMessage());
        System.out.printf((FORMATTED_AMOUNT.getMessage()) + "%n", paymentAmount);
    }

    public static void announceEventBadge(String badge) {
        System.out.println(EVENT_BADGE.getMessage());
        System.out.println(badge);
    }

    public static void announceStartBenefits() {
        System.out.println(BENEFIT_DETAILS.getMessage());
    }

    public static void announceErrorMessage(ErrorMessage errorMessage) {
        System.out.println(errorMessage.getMessage());
    }

}
