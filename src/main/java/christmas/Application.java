package christmas;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        int visitDate = requestVisitDate();

        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1");
        String stringMenu = Console.readLine();
        String[] splitMenu = stringMenu.split(",");
        Map<String, Integer> menuNameAndQuantity = new HashMap<>();
        for (String menu : splitMenu) {
            String[] split = menu.split("-");
            String menuName = split[0];
            int menuCount = Integer.parseInt(split[1]);

            menuNameAndQuantity.put(menuName, menuCount);
        }
    }

    private static int requestVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String stringDate = Console.readLine();
        return Integer.parseInt(stringDate);
    }
}
