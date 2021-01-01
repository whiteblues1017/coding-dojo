import Errors.Error.*;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorChecker {

    public static void checkInputString(String row) throws Exception {
        Pattern pattern = Pattern.compile(".*:( (A|[2-9]|10|[JQK])[HDSC]){5}");
        Matcher matcher = pattern.matcher(row);

        if (!matcher.matches()) {
            throw new InputException("Illegal input player information : " + row);
        }
    }

    public static void checkFieldDuplicateCards(HashMap<String, Card> fieldCard, String playerCard) throws DuplicateException {
        if (fieldCard.containsKey(playerCard)) {
            throw new DuplicateException(playerCard + "is already on the field");
        }
    }
}
