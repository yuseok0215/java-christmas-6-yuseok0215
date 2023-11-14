package christmas.Controller;

import christmas.Menu;
import christmas.domain.ReservationInfo;
import christmas.domain.discount.EventManager;
import christmas.domain.discount.DiscountCategory;
import christmas.view.Input;
import christmas.view.Output;
import java.text.DecimalFormat;
import java.util.Map;

public class EventController {

    public void run() {
        ReservationInfo reservationInfo = new ReservationInfo(requestVisitDate(), requestMenuNameAndQuantity());

        Output.announceEventBenefitPreview();
        Output.announceOrderMenu(reservationInfo.getMenuAndQuantity());

        int totalPriceBeforeDiscount = getTotalPriceBeforeDiscount(reservationInfo.getMenuAndQuantity());
        Output.announceOrderAmountBeforeDiscount(totalPriceBeforeDiscount);

        EventManager discount = new EventManager(new DiscountCategory(), reservationInfo);

        updateDiscount(totalPriceBeforeDiscount, discount);
        presentationEvent(discount);

        System.out.println("\n<혜택내역>");
        Output.announceBenefitDetails(discount.getBenefitDetails());
        Output.announceTotalBenefitAmount(discount.getTotalDiscountPrice());
        Output.announcePaymentAmountAfterDiscount(discount.getPaymentAmount(totalPriceBeforeDiscount));
        Output.announceEventBadge(discount.getEventBadge());
    }

    private void presentationEvent(EventManager discount) {
        if (discount.hasPresentationMenu("증정 이벤트")) {
            Output.announcePresentChampagne();
        } else if (!discount.hasPresentationMenu("증정 이벤트")) {
            Output.announcePresentAbsence();
        }
    }

    private void updateDiscount(int totalPriceBeforeDiscount, EventManager discount) {
        discount.updateChristmasDdayDiscount();
        discount.updateWeekdayDiscount();
        discount.updateWeekendDiscount();
        discount.updateSpecialDiscount();
        discount.updatePresentationDiscount(totalPriceBeforeDiscount);
    }

    private static int requestVisitDate() {
        Output.announceStartEventCheck();
        return Input.visitDate();
    }

    private static Map<String,Integer> requestMenuNameAndQuantity() {
        try {
            return Input.orderMenuAndQuantity();
        } catch (IllegalArgumentException e) {
            return requestMenuNameAndQuantity();
        }
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
