package christmas.option;

public enum Event {

    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    PRESENTATION("증정 이벤트"),
    CHRISTMAS("크리스마스 디데이 할인");

    private final String eventName;

    Event(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
