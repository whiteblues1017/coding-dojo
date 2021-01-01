import Enum.Cards.Numbers;
import Enum.ResultType;
import Enum.HandsType;

import java.util.ArrayList;

public class Player {

    private String playerName;

    private ArrayList<Card> cards;

    private ResultType result;

    private HandsType hand;

    private Numbers strongestNumber;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }

    public ResultType getResult() {
        return result;
    }

    public void setHand(HandsType hand) {
        this.hand = hand;
    }

    public HandsType getHand() {
        return hand;
    }

    public void setStrongestNumber(Numbers strongestNumber) {
        this.strongestNumber = strongestNumber;
    }

    public Numbers getStrongestNumber() {
        return strongestNumber;
    }
}
