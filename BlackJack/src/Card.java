public class Card {
    private String value;
    private String suit;
    private int cardValue;

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
        switch (value) {
            case "Ace":
                this.cardValue = 11;
                break;
            case "Jack":
            case "Queen":
            case "King":
                this.cardValue = 10;
                break;
            default:
                this.cardValue = Integer.parseInt(value);
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getCardValue() {
        return cardValue;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
