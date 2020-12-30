package Enum;

public enum HandsType {
    STRAIGHT_FLUSH("7"),
    FOUR_OF_KINDS("6"),
    FULL_HOUSE("5"),
    FLUSH("4"),
    STRAIGHT("3"),
    THREE_OF_KIND("2"),
    TWO_PAIRS("1"),
    HIGH_CARD("0");

    private String code;

    HandsType(String code) {
        this.code  = code;
    }

    public String getCode(){
        return this.code;
    }

    public static String toName(String code) {
        HandsType[] values = values();

        for (HandsType value: values) {
            if (value.getCode().equals(code)) {
                return value.toString();
            }
        }

        throw new IllegalArgumentException("Illegal code : " + code);
    }


}
