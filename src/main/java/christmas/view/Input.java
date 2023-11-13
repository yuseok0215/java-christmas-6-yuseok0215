package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.Map;

public class Input {

    public static Integer requestVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return Integer.parseInt(Console.readLine());
    }

    public static Map<String, Integer> requestOrderMenuAndQuantity() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1");

        Map<String, Integer> menuNameAndQuantity = new HashMap<>();
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

    public static void announceEventBenefitPreview() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }
}
