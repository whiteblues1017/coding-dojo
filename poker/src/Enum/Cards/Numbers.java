package Enum.Cards;

public enum Numbers {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN ("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private String code;

    Numbers(String code) {
        this.code  = code;
    }

    public String getCode(){
        return this.code;
    }

    public static String toName(String code) {
        Numbers[] values = values();

        for (Numbers value: values) {
            if (value.getCode().equals(code)) {
                return value.toString();
            }
        }

        throw new IllegalArgumentException("Illegal code : " + code);
    }
}
