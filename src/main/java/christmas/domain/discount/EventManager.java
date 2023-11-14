package christmas.domain.discount;

import christmas.converter.Converter;
import christmas.domain.ReservationInfo;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

public class EventManager {
    public static final int year = 2023;
    public static final int month = 12;

    private int totalDiscountPrice;
    private int totalPaymentDiscountPrice;
    private final DiscountCategory discountCategory;
    private final ReservationInfo reservationInfo;

    public EventManager(DiscountCategory discountCategory, ReservationInfo reservationInfo) {
        this.totalDiscountPrice = 0;
        this.totalPaymentDiscountPrice = 0;
        this.discountCategory = discountCategory;
        this.reservationInfo = reservationInfo;
    }

    private static DayOfWeek getDayOfWeek(int day) {
        return LocalDate.of(year, month, day).getDayOfWeek();
    }

    public void updateChristmasDdayDiscount() {
        totalDiscountPrice += discountCategory.christmasDdayDiscount(reservationInfo.getVisitDate());
        totalPaymentDiscountPrice += discountCategory.christmasDdayDiscount(reservationInfo.getVisitDate());
    }

    public void updateWeekdayDiscount() {
        DayOfWeek dayOfWeek = getDayOfWeek(reservationInfo.getVisitDate());
        String dayOfWeekKorean = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN); // 요일 알려주기
        totalDiscountPrice += discountCategory.weekdayDiscount(reservationInfo.getMenuAndQuantity(), dayOfWeekKorean);
        totalPaymentDiscountPrice += discountCategory.weekdayDiscount(reservationInfo.getMenuAndQuantity(), dayOfWeekKorean);
    }

    public void updateWeekendDiscount() {
        DayOfWeek dayOfWeek = getDayOfWeek(reservationInfo.getVisitDate());
        String dayOfWeekKorean = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN); // 요일 알려주기
        totalDiscountPrice += discountCategory.weekendDiscount(reservationInfo.getMenuAndQuantity(), dayOfWeekKorean);
        totalPaymentDiscountPrice += discountCategory.weekendDiscount(reservationInfo.getMenuAndQuantity(), dayOfWeekKorean);
    }

    public void updateSpecialDiscount() {
        totalDiscountPrice += discountCategory.specialDiscount(reservationInfo.getVisitDate());
        totalPaymentDiscountPrice += discountCategory.specialDiscount(reservationInfo.getVisitDate());
    }

    public void updatePresentationDiscount(int totalPriceBeforeDiscount) {
        totalDiscountPrice += discountCategory.presentationDiscount(totalPriceBeforeDiscount);
    }

    public boolean hasPresentationMenu(String eventType) {
        return discountCategory.hasPresentationEvent(eventType);
    }

    public Map<String, String> getBenefitDetails() {
        return discountCategory.getDiscountAmount();
    }

    public String getTotalDiscountPrice() {
        return Converter.convertFormattedAmount(totalDiscountPrice);
    }

    public String getPaymentAmount(int totalPriceBeforeDiscount) {
        return Converter.convertFormattedAmount(totalPriceBeforeDiscount - totalPaymentDiscountPrice);
    }

    public String getEventBadge() {
        if (totalDiscountPrice >= 20000) {
            return "산타";
        } else if (totalDiscountPrice >= 10000) {
            return "트리";
        } else if (totalDiscountPrice >= 5000) {
            return "별";
        }
        return "없음";
    }
}
