import Enum.Cards.Numbers;
import Enum.Cards.Suits;

public class Card {
    private String cardName;

    private Suits suit;

    private Numbers number;

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    public Suits getSuit() {
        return suit;
    }

    public void setNumber(Numbers number) {
        this.number = number;
    }

    public Numbers getNumber() {
        return number;
    }
}
