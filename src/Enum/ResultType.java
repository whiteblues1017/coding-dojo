package Enum;

public enum ResultType {
    WIN("1"),
    DRAW("0"),
    LOSE("-1")
    ;

    private String code;

    ResultType(String code) {
        this.code  = code;
    }

    public String getCode(){
        return this.code;
    }

    public static String toName(String code) {
        ResultType[] values = values();

        for (ResultType value: values) {
            if (value.getCode().equals(code)) {
                return value.toString();
            }
        }

        throw new IllegalArgumentException("Illegal code : " + code);
    }

}
