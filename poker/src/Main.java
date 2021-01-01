import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input players Information > ");

        String inputLine = scanner.nextLine();

        scanner.close();

        Field field = new Field();
        field.setPlayers(inputLine.split(" {2}"));
        field.battlePlayers();
        System.out.println(field.outputResultText());
    }
}
