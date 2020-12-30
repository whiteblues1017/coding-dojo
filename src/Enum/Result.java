package Enum;

public enum Result {;

    private Integer WIN = 1;

    private Integer DRAW = 0;

    private Integer LOSE = -1;

    public void setWIN(Integer WIN) {
        this.WIN = WIN;
    }

    public Integer getWIN() {
        return WIN;
    }

    public void setDRAW(Integer DRAW) {
        this.DRAW = DRAW;
    }

    public Integer getDRAW() {
        return DRAW;
    }

    public void setLOSE(Integer LOSE) {
        this.LOSE = LOSE;
    }

    public Integer getLOSE() {
        return LOSE;
    }
}
