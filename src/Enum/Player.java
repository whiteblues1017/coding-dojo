package Enum;

import Card;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> cards;

    private Result result;


    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
