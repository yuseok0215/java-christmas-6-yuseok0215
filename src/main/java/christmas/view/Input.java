package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.converter.TypeConverter;
import java.util.HashMap;
import java.util.Map;

public class Input {

    public static int visitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return Integer.parseInt(Console.readLine());
    }

    public static Map<String, Integer> orderMenuAndQuantity() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        Map<String, Integer> menuNameAndQuantity = new HashMap<>();

        String stringMenu = Console.readLine();
        return TypeConverter.convertStringMenu(menuNameAndQuantity, stringMenu);
    }
}
