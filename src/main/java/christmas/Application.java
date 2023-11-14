package christmas;

import christmas.domain.Controller.EventController;
import christmas.domain.discount.Discount;
import christmas.domain.ReservationInfo;
import christmas.domain.discount.DiscountCategory;
import christmas.view.Input;
import christmas.view.Output;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        EventController eventController = new EventController();
        eventController.run();
    }
}
