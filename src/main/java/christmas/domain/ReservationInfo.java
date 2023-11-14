package christmas.domain;

import java.util.Map;

public record ReservationInfo(int visitDate, Map<String, Integer> menuAndQuantity) {

    public int getVisitDate() {
        return visitDate;
    }

    public Map<String, Integer> getMenuAndQuantity() {
        return menuAndQuantity;
    }
}
