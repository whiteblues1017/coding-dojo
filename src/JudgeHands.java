import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ポーカーの役クラス
 */
public class JudgeHands {
    /**
     * 5枚全てが同じスートかつ続き数字であるか
     *
     * @param cards 5枚のカード
     * @return ストレートフラッシュであるか
     */
    public static boolean straightFlush(ArrayList<Card> cards) {
        return straight(cards) && flush(cards);
    }

    /**
     * 同じ数字のカードが4枚揃っているか
     *
     * @param cards 5枚のカード
     * @return フォーオブアカインドであるか
     */
    public static boolean fourOfAKind(ArrayList<Card> cards) {
        return cards.stream().map(card -> card.getNumber()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).values().contains(4L);
    }

    /**
     * 同じ数字が3枚と2枚の組み合わせがあるか
     *
     * @param cards 5枚のカード
     * @return フルハウスであるか
     */
    public static boolean fullHouse(ArrayList<Card> cards) {
        return threeOfAKind(cards) && pair(cards);
    }

    /**
     * 数字に関係なく同じスートが5枚揃っているか
     *
     * @param cards 5枚のカード
     * @return フラッシュであるか
     */
    public static boolean flush(ArrayList<Card> cards) {
        return cards.stream().map(card -> card.getSuit()).distinct().collect(Collectors.toList()).size() == 1;
    }

    /**
     * 続き数字のカードが5枚あるか
     *
     * @param cards 5枚のカード
     * @return ストレートがあるか
     */
    public static boolean straight(ArrayList<Card> cards) {
        ArrayList<String> strengthNumbers = Field.getStrengthNumbers();
        List<Integer> numberCards = cards.stream().map(card -> strengthNumbers.indexOf(card.getNumber())).sorted().collect(Collectors.toList());
        for (int i = 0; i < numberCards.size(); i++) {
            if (numberCards.get(0) + i != numberCards.get(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 同じ数字のカードが3枚あるか
     *
     * @param cards 5枚のカード
     * @return スリーオブアカインドがあるか
     */
    public static boolean threeOfAKind(ArrayList<Card> cards) {
        return cards.stream().map(card -> card.getNumber()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).values().contains(3L);
    }

    /**
     * 数字のペアが2組あるか
     *
     * @param cards 5枚のカード
     * @return ツーペアがあるか
     */
    public static boolean twoPairs(ArrayList<Card> cards) {
        int pairCount = cards.stream().map(card -> card.getNumber()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream().filter(value -> value.equals(2L)).collect(Collectors.toList()).size();

        return pairCount == 2;
    }

    /**
     * 数字のペアが一つでもあるか
     *
     * @param cards 5枚のカード
     * @return ペアかどうか
     */
    public static boolean pair(ArrayList<Card> cards) {

        int pairCount = cards.stream().map(card -> card.getNumber()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream().filter(value -> value.equals(2L)).collect(Collectors.toList()).size();

        return pairCount == 1;
    }

}
