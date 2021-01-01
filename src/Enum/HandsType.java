package Enum;

public enum HandsType {
    STRAIGHT_FLUSH("8"),
    FOUR_OF_KINDS("7"),
    FULL_HOUSE("6"),
    FLUSH("5"),
    STRAIGHT("4"),
    THREE_OF_KIND("3"),
    TWO_PAIRS("2"),
    PAIR("1"),
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
