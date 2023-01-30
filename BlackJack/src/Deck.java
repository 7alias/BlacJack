import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static List<Card> deck;

    private Deck() {
        this.deck = new ArrayList<>();

    }

    public static Deck newGame (){
        Deck deckObject = new Deck();
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String suit : suits) {
            for (String value : values) {
                deck.add(new Card(value, suit));
            }
        }
        Collections.shuffle(deck);

        return deckObject;
    }
    public Card getCard (){
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    public Card draw() {
        return deck.remove(0);
    }

    public int getDeckSize() {
        return deck.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : deck) {
            sb.append(card.toString()).append("\n");
        }
        return sb.toString();
    }
}
