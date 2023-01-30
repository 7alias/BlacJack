import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Card> dilerCards = new ArrayList<>();
    static ArrayList<Card> playerCards = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Добро пожаловать к нам в игру.");


        while (true) {
            dilerCards.clear();
            playerCards.clear();
            Deck deck = Deck.newGame();


            dilerCards.add(deck.getCard());
            Card dillerSecondCard = deck.getCard();
            System.out.println("Карты дилера:");
            printCards(dilerCards);


            if (getPoints(dilerCards) == 11) {
                System.out.println("Дилер смотрит вторую карту...");
                if (dillerSecondCard.getCardValue() == 10) {
                    System.out.println("Ха-Ха. ты лох");
                    dilerCards.add(dillerSecondCard);
                    printGameStatus();

                    if (startNewGame(false)) continue;
                    else break;
                }
            }


            Boolean startNewGame = null;
            while (true) {
                playerCards.add(deck.getCard());
                printGameStatus();
                if (getPoints(playerCards) > 21) {
                    if (!(getPoints(playerCards) == 22 && playerCards.size() == 2 && playerCards.get(0).getValue().equals("Ace") && playerCards.get(1).getValue().equals("Ace"))) {
                        startNewGame = startNewGame(false);
                        break;
                    }
                }

                System.out.println("Будете брать еще карту? (Да/Нет)");
                String playerAnswer = "";

                while (!(playerAnswer.equals("Да") || playerAnswer.equals("Нет"))) {
                    playerAnswer = scanner.nextLine();
                }

                if (playerAnswer.equals("Нет")) {
                    break;
                }
            }

            if (startNewGame != null){
                if (startNewGame) continue;
                else break;
            }

            while (getPoints(dilerCards) < 17) {
                printGameStatus();
                dilerCards.add(deck.getCard());

                if (getPoints(dilerCards) > 21) {
                    if (!(getPoints(dilerCards) == 22
                            && dilerCards.size() == 2
                            && dilerCards.get(0).getValue().equals("Ace")
                            && dilerCards.get(1).getValue().equals("Ace"))) {
                        printGameStatus();
                        startNewGame = startNewGame(true);
                        break;
                    }
                }
            }

            if (startNewGame != null){
                if (startNewGame) continue;
                else break;
            }

            printGameStatus();

            boolean playerAnswer;
            if (getPoints(dilerCards) > getPoints(playerCards)) {
                playerAnswer = startNewGame(false);
            } else if (getPoints(dilerCards) < getPoints(playerCards)) {
                playerAnswer = startNewGame(true);
            } else {
                playerAnswer = startNewGame();
            }

            if (playerAnswer) continue;
            else break;
        }
    }

    public static int getPoints(ArrayList<Card> cards) {
        int points = 0;
        int aceCount = 0;
        for (Card card : cards) {
            if (card.getValue().equals("Ace")) {
                points += 1;
                aceCount++;
            } else {
                points += card.getCardValue();
            }
        }
        while (points + 10 <= 21 && aceCount > 0) {
            points += 10;
            aceCount--;
        }
        return points;
    }

    public static void printCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public static boolean startNewGame(boolean win) {
        if (win) {
            System.out.println("Ты победил. Начать новую игру? (Да/Нет)");
        } else {
            System.out.println("Ты проиграл. Начать новую игру? (Да/Нет)");
        }

        String playerAnswer = scanner.nextLine();
        while (!(playerAnswer.equals("Да") || playerAnswer.equals("Нет"))) {
            System.out.println("Вы ввели некоректное значение. Нужно ответить или Да или Нет");
            playerAnswer = scanner.nextLine();
        }
        return playerAnswer.equals("Да");
    }

    public static boolean startNewGame() {
        System.out.println("Ничья. Начать новую игру? (Да/Нет)");

        String playerAnswer = scanner.nextLine();
        while (!(playerAnswer.equals("Да") || playerAnswer.equals("Нет"))) {
            System.out.println("Вы ввели некоректное значение. Нужно ответить или Да или Нет");
            playerAnswer = scanner.nextLine();
        }
        return playerAnswer.equals("Да");
    }

    public static void printGameStatus() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Карты диллера:");
        printCards(dilerCards);
        System.out.println("Очки диллера: " + getPoints(dilerCards) + "\n");
        System.out.println("Ваши карты:");
        printCards(playerCards);
        System.out.println("Ваши очки: " + getPoints(playerCards));
    }

}