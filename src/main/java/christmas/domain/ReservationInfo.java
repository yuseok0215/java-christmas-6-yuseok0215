package christmas.domain;

import java.util.Map;

public class ReservationInfo {

    private final int visitDate;
    private final Map<String, Integer> menuAndQuantity;

    public ReservationInfo(int visitDate, Map<String, Integer> menuAndQuantity) {
        this.visitDate = visitDate;
        this.menuAndQuantity = menuAndQuantity;
    }

    public int getVisitDate() {
        return visitDate;
    }

    public Map<String, Integer> getMenuAndQuantity() {
        return menuAndQuantity;
    }
}
