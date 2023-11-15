package christmas.Controller;

import christmas.option.Event;
import christmas.option.Menu;
import christmas.domain.ReservationInfo;
import christmas.domain.discount.EventManager;
import christmas.domain.discount.DiscountCategory;
import christmas.message.ErrorMessage;
import christmas.validator.Validator;
import christmas.view.Input;
import christmas.view.Output;
import java.util.Map;

public class EventController {

    public void run() {
        ReservationInfo reservationInfo = new ReservationInfo(requestVisitDate(), requestMenuNameAndQuantity());

        Output.announceEventBenefitPreview(reservationInfo.getVisitDate());
        Output.announceOrderMenu(reservationInfo.getMenuAndQuantity());

        int totalPriceBeforeDiscount = getTotalPriceBeforeDiscount(reservationInfo.getMenuAndQuantity());
        Output.announceOrderAmountBeforeDiscount(totalPriceBeforeDiscount);

        EventManager discount = new EventManager(new DiscountCategory(), reservationInfo);

        updateDiscount(totalPriceBeforeDiscount, discount);
        presentationEvent(discount);

        Output.announceStartBenefits();
        Output.announceBenefitDetails(discount.getBenefitDetails());
        Output.announceTotalBenefitAmount(discount.getTotalDiscountPrice());
        Output.announcePaymentAmountAfterDiscount(discount.getPaymentAmount(totalPriceBeforeDiscount));
        Output.announceEventBadge(discount.getEventBadge());
    }

    private static int requestVisitDate() {
        Output.announceStartEventCheck();
        while (true) {
            try {
                int visitDate = Input.visitDate();
                Validator.validateVisitDate(visitDate);
                return visitDate;
            } catch (IllegalArgumentException e) {
                Output.announceErrorMessage(ErrorMessage.INVALID_DATE);
            }
        }
    }

    private static Map<String,Integer> requestMenuNameAndQuantity() {
        while (true) {
            try {
                Map<String, Integer> orderMenuAndQuantity = Input.orderMenuAndQuantity();
                Validator.validateStringMenu(orderMenuAndQuantity);
                return orderMenuAndQuantity;
            } catch (IllegalArgumentException e) {
                Output.announceErrorMessage(ErrorMessage.INVALID_MENU);
            }
        }
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

    private void updateDiscount(int totalPriceBeforeDiscount, EventManager discount) {
        discount.updateChristmasDdayDiscount();
        discount.updateWeekdayDiscount();
        discount.updateWeekendDiscount();
        discount.updateSpecialDiscount();
        discount.updatePresentationDiscount(totalPriceBeforeDiscount);
    }

    private void presentationEvent(EventManager discount) {
        if (discount.hasPresentationMenu(Event.PRESENTATION.getEventName())) {
            Output.announcePresentChampagne();
            return;
        }
        Output.announcePresentAbsence();
    }
}
