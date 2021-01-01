import Enum.Cards.Numbers;
import Enum.Cards.Suits;
import Enum.HandsType;
import Enum.ResultType;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Field {

    private static final ArrayList<String> strengthNumbers
            = new ArrayList(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"));

    private static final ArrayList<String> suits = new ArrayList<>(Arrays.asList("H", "D", "S", "C"));

    private static final HashMap<String, Card> allCards = new HashMap();

    private static final HashMap<String, Card> fieldCards = new HashMap<>();

    private static ArrayList<Player> fieldPlayers;

    public Field() {
        initialSetCards();
    }

    public void setPlayers(String[] inputPlayerStrings) throws Exception {
        ArrayList<Player> players = new ArrayList<>();
        for (String playerString: inputPlayerStrings) {
            players.add(initPlayerStatus(playerString));
        }
        fieldPlayers = players;
    }


    // TODO: 3名以上になったらスコアでジャッジするように修正
    public void battlePlayers() {
        if (!fieldPlayers.isEmpty()) {
            if (fieldPlayers.get(0).getHand().equals(fieldPlayers.get(1).getHand())){
                if (strengthNumbers.indexOf(fieldPlayers.get(0).getStrongestNumber().getCode()) > strengthNumbers.indexOf(fieldPlayers.get(1).getStrongestNumber().getCode())) {
                    fieldPlayers.get(0).setResult(ResultType.WIN);
                    fieldPlayers.get(1).setResult(ResultType.LOSE);
                }
                else if (strengthNumbers.indexOf(fieldPlayers.get(0).getStrongestNumber().getCode()) < strengthNumbers.indexOf(fieldPlayers.get(1).getStrongestNumber().getCode())) {
                    fieldPlayers.get(0).setResult(ResultType.LOSE);
                    fieldPlayers.get(1).setResult(ResultType.WIN);
                }
                else {
                    fieldPlayers.get(0).setResult(ResultType.DRAW);
                    fieldPlayers.get(1).setResult(ResultType.DRAW);
                }
            }
            else if(Integer.parseInt(fieldPlayers.get(0).getHand().getCode()) > Integer.parseInt(fieldPlayers.get(1).getHand().getCode())) {
                fieldPlayers.get(0).setResult(ResultType.WIN);
                fieldPlayers.get(1).setResult(ResultType.LOSE);
            }
            else {
                fieldPlayers.get(0).setResult(ResultType.LOSE);
                fieldPlayers.get(1).setResult(ResultType.WIN);
            }
        }
    }

    public String outputResultText() throws Exception {
        for (Player player: fieldPlayers) {
            if(player.getResult().equals(ResultType.WIN)) {
                return player.getPlayerName() + " wins. - with " + HandsType.toName(player.getHand().getCode()) + ": " + Numbers.toName(player.getStrongestNumber().getCode());
            }
            else if (player.getResult().equals(ResultType.DRAW)) {
                return "Tie.";
            }
        }

        throw new Exception("can not judge game.");
    }

    private void initialSetCards() {
        for (String suit : suits) {
            for (String number : strengthNumbers){
                Card card = new Card();
                card.setNumber(Numbers.valueOf(Numbers.toName(number)));
                card.setSuit(Suits.valueOf(Suits.toName(suit)));

                allCards.put(number + suit, card);
            }
        }
    }

    private static Player initPlayerStatus(String inputPlayerString) throws Exception {
        ErrorChecker.checkInputString(inputPlayerString);

        Player player = new Player();
        ArrayList<Card> playerCards = new ArrayList<>();

        for (String inputCardString : inputPlayerString.split(": ")[1].split(" ")) {
            Card playerCard = allCards.get(inputCardString);

            ErrorChecker.checkFieldDuplicateCards(fieldCards, inputCardString);
            fieldCards.put(inputCardString, playerCard);
            playerCards.add(playerCard);
        }

        player.setPlayerName(inputPlayerString.split(": ")[0]);
        player.setCards(playerCards);
        player.setHand(judgePlayerHand(player.getCards()));

        // 黒魔術感がすごい
        player.setStrongestNumber(Numbers.valueOf(Numbers.toName(strengthNumbers.get(judgeStrongestPlayerCard(player)))));

        return player;
    }


    private static HandsType judgePlayerHand(ArrayList<Card> playerCards) {
        if (JudgeHands.straightFlush(playerCards)) return HandsType.STRAIGHT_FLUSH;
        if (JudgeHands.fourOfAKind(playerCards)) return HandsType.FOUR_OF_KINDS;
        if (JudgeHands.fullHouse(playerCards)) return HandsType.FULL_HOUSE;
        if (JudgeHands.flush(playerCards)) return HandsType.FLUSH;
        if (JudgeHands.straight(playerCards)) return HandsType.STRAIGHT;
        if (JudgeHands.threeOfAKind(playerCards)) return HandsType.THREE_OF_KIND;
        if (JudgeHands.twoPairs(playerCards)) return HandsType.TWO_PAIRS;

        return HandsType.HIGH_CARD;
    }

    private static Integer judgeStrongestPlayerCard(Player player) {
        if (player.getHand().equals(HandsType.FOUR_OF_KINDS)) {
            for (Map.Entry<Numbers, Long> cardLongEntry :
                    player.getCards().stream().map(card -> card.getNumber()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()) {
                if (cardLongEntry.getValue() == 4L) {
                    return strengthNumbers.indexOf(cardLongEntry.getKey().getCode());
                }
            }
        }
        else if (player.getHand().equals(HandsType.FULL_HOUSE) || player.getHand().equals(HandsType.THREE_OF_KIND)) {
            for (Map.Entry<Numbers, Long> cardLongEntry :
                    player.getCards().stream().map(card -> card.getNumber()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()) {
                if (cardLongEntry.getValue() == 3L) {
                    return strengthNumbers.indexOf(cardLongEntry.getKey().getCode());
                }
            }
        }
        else if (player.getHand().equals(HandsType.TWO_PAIRS) || player.getHand().equals(HandsType.PAIR)) {
            return  player.getCards().stream().map(card -> card.getNumber()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet().stream().filter(entry -> entry.getValue().equals(2L))
                    .map(entry -> strengthNumbers.indexOf(entry.getKey().getCode())).max(Integer::compareTo).get();
        }

        return player.getCards().stream().map(card -> strengthNumbers.indexOf(card.getNumber().getCode())).max(Integer::compareTo).get();
    }

    public static ArrayList<String> getStrengthNumbers() {
        return strengthNumbers;
    }
}
