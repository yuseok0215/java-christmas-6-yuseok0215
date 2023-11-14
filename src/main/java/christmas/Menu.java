package christmas;

import java.util.Arrays;

public enum Menu {
    양송이수프("Appetizer", 6000),
    타파스("Appetizer", 5500),
    시저샐러드("Appetizer", 8000),
    티본스테이크("Main", 55000),
    바비큐립("Main", 54000),
    해산물파스타("Main", 35000),
    크리스마스파스타("Main", 25000),
    초코케이크("Dessert", 15000),
    아이스크림("Dessert", 5000),
    제로콜라("Drink", 3000),
    레드와인("Drink", 60000),
    삼페인("Drink", 25000);

    private final String category;
    private final int price;

    Menu(String category, int price) {
        this.category = category;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public static int getMenuByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name().equals(menuName))
                .findFirst()
                .map(Menu::getPrice)
                .orElse(0);
    }

    public static String getMenuCategory(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name().equals(menuName))
                .findFirst()
                .map(menu -> menu.category)
                .orElse(null);
    }

    public static boolean isMenuValid(String inputMenu) {
        return Arrays.stream(Menu.values())
                .anyMatch(menu -> menu.name().equalsIgnoreCase(inputMenu));
    }
}
