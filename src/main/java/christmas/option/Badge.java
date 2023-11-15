package christmas.option;

public enum Badge {

    SANTA("산타" ,20000),
    TREE("트리" ,10000),
    STAR("별", 5000),
    NOTHING("없음", 0);

    private final String badgeName;
    private final int priceStandard;

    Badge(String badgeName, int priceStandard) {
        this.badgeName = badgeName;
        this.priceStandard = priceStandard;
    }

    public static String getEventBadge(int totalDiscountPrice) {
        if (totalDiscountPrice >= SANTA.priceStandard) {
            return SANTA.badgeName;
        } else if (totalDiscountPrice >= TREE.priceStandard) {
            return TREE.badgeName;
        } else if (totalDiscountPrice >= STAR.priceStandard) {
            return STAR.badgeName;
        }
        return NOTHING.badgeName;
    }
}
