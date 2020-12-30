import Enum.Cards.Numbers;
import Enum.Cards.Suits;

public class Card {
    private Suits suit;
    
    private Numbers number;

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
