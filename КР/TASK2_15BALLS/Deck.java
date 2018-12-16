import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    Card[] deck;

    public Deck(int capacity) {
        deck = new Card[capacity];
    }

    public Deck(Card[] deck) {
        this.deck = deck;
    }

    public void fill() {
        for (int i = 0; i < deck.length; i++) {
            deck[i] = Card.next_card();
        }
    }

    static public Card[] valid_deck() {
        Card[] deck = new Card[52];
        for (Card.Suit s : Card.Suit.values()) {
            for (int i = 0; i < 14; i++) {
                deck[i * s.ordinal()] = new Card(s, i);
            }
        }
        return deck;
    }

    public Card[] cards() {
        return deck;
    }
}

class Card implements Comparable<Card> {

    enum Suit {spade, heart, club, diamond}

    ;
    Suit suit;
    int  val; //0-13 incl

    public Card(Suit suit, int val) {
        this.suit = suit;
        this.val = val;
    }

    static ThreadLocalRandom r = ThreadLocalRandom.current();

    static Card next_card() {
        return new Card(Suit.values()[r.nextInt(0, 4)], r.nextInt(0, 14));
    }

    @Override
    public int compareTo(Card that) {
        int suit_cmp = Integer.compare(this.suit.ordinal(), that.suit.ordinal());
        if (this.suit == that.suit) {
            return Integer.compare(this.val, that.val);
        }
        return suit_cmp;
    }

    public void show() {
        System.out.println("Card : suit: " + suit + "; val: " + val);
    }
}
