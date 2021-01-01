package Enum.Cards;

public enum Suits {
    HEART("H"),
    DIA("D"),
    SPADE("S"),
    CLOVER("C")
    ;


    private String code;

    Suits(String code) {
        this.code  = code;
    }

    public String getCode(){
        return this.code;
    }

    public static String toName(String code) {
        Suits[] values = values();

        for (Suits value: values) {
            if (value.getCode().equals(code)) {
                return value.toString();
            }
        }

        throw new IllegalArgumentException("Illegal code : " + code);
    }
}
