package christmas.converter;

import christmas.option.Menu;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Converter {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public static Map<String, String> convertDiscountAmount(Map<String, Integer> discountCategory) {
        return discountCategory.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> decimalFormat.format(entry.getValue()),
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    public static Map<String, Integer> convertStringMenu(Map<String, Integer> menuNameAndQuantity, String stringMenu) {
        String[] splitMenu = stringMenu.split(",");
        for (String menu : splitMenu) {
            String[] nameAndQuantity = menu.split("-");
            String menuName = nameAndQuantity[0];

            if(!Menu.isMenuValid(menuName)){
                throw new IllegalArgumentException();
            }

            int menuCount = Integer.parseInt(nameAndQuantity[1]);
            if(menuCount < 1) {
                throw new IllegalArgumentException();
            }

            if (menuNameAndQuantity.containsKey(menuName)) {
                throw new IllegalArgumentException();
            }
            menuNameAndQuantity.put(menuName, menuCount);
        }
        return menuNameAndQuantity;
    }

    public static String convertFormattedAmount(int amount) {
        return decimalFormat.format(amount);
    }
}
