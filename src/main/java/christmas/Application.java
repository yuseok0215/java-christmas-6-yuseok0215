package christmas;

import camp.nextstep.edu.missionutils.Console;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Application {
    public static final int year = 2023;
    public static final int month = 12;

    public static void main(String[] args) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        int visitDate = requestVisitDate();

        Map<String,Integer> menuNameAndQuantity = getMenuNameAndQuantity(); // 메뉴 입력

        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        announceOrderMenu(menuNameAndQuantity);

        System.out.println("<할인 전 총주문 금액>");
        int totalPriceBeforeDiscount = announceTotalPriceBeforeDiscount(menuNameAndQuantity);

        // 할인 금액을 구해보자!
        int discountPrice = 0;
        discountPrice += christmasDdayDiscount(visitDate);

        DayOfWeek dayOfWeek = getDayOfWeek(visitDate);
        String dayOfWeekKorean = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN); // 요일 알려주기

        discountPrice +=  christmasGeneralDiscount(visitDate, menuNameAndQuantity, dayOfWeekKorean);

        System.out.println("<증정 메뉴>");
        List<String> presentationMenu = new ArrayList<>();
        if (totalPriceBeforeDiscount >= 120000) {
            presentationMenu.add("샴페인");
        }
        if (discountPrice >= 20000) {
            presentationMenu.add("산타");
        } else if (discountPrice >= 10000) {
            presentationMenu.add("트리");
        } else if (discountPrice >= 5000) {
            presentationMenu.add("별");
        }

        for (String item : presentationMenu) {
            System.out.println(item + " 1개");
        }
        // 삼페인, 배지에 대한 증정 메뉴 알려주기

    }

    private static int christmasGeneralDiscount(int visitDate, Map<String, Integer> menuNameAndQuantity, String dayOfWeekKorean) {
        int discountPrice = 0;
        for (Map.Entry<String, Integer> entry : menuNameAndQuantity.entrySet()) {
            String menuName = entry.getKey();
            Integer menuQuantity = entry.getValue();

            String menuCategory = Menu.getMenuCategory(menuName);
            if (dayOfWeekKorean.equals("월요일") || dayOfWeekKorean.equals("화요일") || dayOfWeekKorean.equals("수요일") || dayOfWeekKorean.equals("목요일")) {
                if (menuCategory.equals("Dessert")) {
                    discountPrice += 2023 * menuQuantity;
                }
            }

            if (dayOfWeekKorean.equals("금요일") || dayOfWeekKorean.equals("토요일")) {
                if (menuCategory.equals("Main")) {
                    discountPrice += 2023 * menuQuantity;
                }
            }

            List<Integer> starDays = List.of(3, 10, 17, 24, 25);
            if (starDays.contains(visitDate)) {
                discountPrice += 1000;
            }
        }
        return discountPrice;
    }

    private static DayOfWeek getDayOfWeek(int day) {
        return LocalDate.of(year, month, day).getDayOfWeek();
    }

    private static int christmasDdayDiscount(int visitDate) {
        if(visitDate >= 1 && visitDate <= 25) {
            return (1000 + (visitDate - 1) * 100);
        }
        return 0;
    }

    private static int announceTotalPriceBeforeDiscount(Map<String, Integer> menuNameAndQuantity) {
        int totalPriceBeforeDiscount = getTotalPriceBeforeDiscount(menuNameAndQuantity);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(totalPriceBeforeDiscount);
        System.out.println(formattedPrice + "원");
        return totalPriceBeforeDiscount;
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
