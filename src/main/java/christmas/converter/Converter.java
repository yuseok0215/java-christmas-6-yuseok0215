package christmas.converter;

import java.text.DecimalFormat;
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
                        entry -> decimalFormat.format(entry.getValue())
                ));
    }

    public static String convertFormattedAmount(int amount) {
        return decimalFormat.format(amount);
    }
}
