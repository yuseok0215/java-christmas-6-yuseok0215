package christmas;

import camp.nextstep.edu.missionutils.Console;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        int visitDate = requestVisitDate();

        Map<String,Integer> menuNameAndQuantity = getMenuNameAndQuantity(); // 메뉴 입력

        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        announceOrderMenu(menuNameAndQuantity);

        System.out.println("<할인 전 총주문 금액>");

        announceTotalPriceBeforeDiscount(menuNameAndQuantity);


    }

    private static void announceTotalPriceBeforeDiscount(Map<String, Integer> menuNameAndQuantity) {
        int totalPriceBeforeDiscount = getTotalPriceBeforeDiscount(menuNameAndQuantity);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(totalPriceBeforeDiscount);
        System.out.println(formattedPrice + "원");
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

    private static void announceOrderMenu(Map<String, Integer> menuNameAndQuantity) {
        System.out.println("<주문 메뉴>");

        for (Map.Entry<String, Integer> entry : menuNameAndQuantity.entrySet()) {
            String menuName = entry.getKey();
            Integer menuQuantity = entry.getValue();
            System.out.println(menuName + " " + menuQuantity + "개");
        }
    }

    private static Map<String,Integer> getMenuNameAndQuantity() {
        try {
            return requestMenuNameAndQuantity();
        } catch (IllegalArgumentException e) {
            getMenuNameAndQuantity();
            return getMenuNameAndQuantity();
        }
    }

    private static Map<String,Integer> requestMenuNameAndQuantity() {
        Map<String, Integer> menuNameAndQuantity = new HashMap<>();

        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1");
        String stringMenu = Console.readLine();
        String[] splitMenu = stringMenu.split(",");
        for (String menu : splitMenu) {
            String[] split = menu.split("-");
            String menuName = split[0];
            int menuCount = Integer.parseInt(split[1]);

            if (menuNameAndQuantity.containsKey(menuName)) {
                throw new IllegalArgumentException("[Error] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            menuNameAndQuantity.put(menuName, menuCount);
        }
        return menuNameAndQuantity;
    }

    private static int requestVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String stringDate = Console.readLine();
        return Integer.parseInt(stringDate);
    }
}
