package christmas.option;

public enum AnnounceMessage {

    START_PLANNER("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EVENT_PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("\n<<주문 메뉴>"),
    PRESENTATION_MENU("\n<증정 메뉴>"),
    PRESENTATION_ITEM("샴페인 1개"),
    EMPTY("없음"),
    MENU_QUANTITY("%s %d개"),
    TOTAL_AMOUNT_BEFORE_DISCOUNT("\n<할인 전 총주문 금액>"),
    PRICE_FORMAT("#,###"),
    FORMATTED_AMOUNT("%s원"),
    DISCOUNT_AMOUNT("%s: -%s원"),
    TOTAL_BENEFIT_AMOUNT("\n<총혜택 금액>"),
    EXPECTED_AMOUNT_AFTER_DISCOUNT("\n<할인 후 예상 결제 금액>"),
    EVENT_BADGE("\n<12월 이벤트 배지>"),
    BENEFIT_DETAILS("\n<혜택 내역>");


    private final String message;

    AnnounceMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
