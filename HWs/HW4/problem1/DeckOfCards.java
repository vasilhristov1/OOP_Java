package problem1;

import java.util.Random;

public class DeckOfCards {
    private Card deck[]; // array of Card objects
    private int currentCard; // index of next Card to be dealt
    private final int NUMBER_OF_CARDS = 52; // constant number of Cards
    private Random randomNumbers; // random number generator

    // constructor fills deck of Cards
    public DeckOfCards()
    {
        String faces[] = { "Ace", "Deuce", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
        String suits[] = { "Hearts", "Diamonds", "Clubs", "Spades" };

        deck = new Card[ NUMBER_OF_CARDS ]; // create array of Card objects
        currentCard = 0; // set currentCard so first Card dealt is deck[ 0 ]
        randomNumbers = new Random(); // create random number generator

        // populate deck with Card objects
        for ( int count = 0; count < deck.length; count++ )
            deck[ count ] =
                    new Card( faces[ count % 13 ], suits[ count / 13 ] );
    } // end DeckOfCards constructor

    // shuffle deck of Cards with one-pass algorithm
    public void shuffle()
    {
        // after shuffling, dealing should start at deck[ 0 ] again
        currentCard = 0; // reinitialize currentCard

        // for each Card, pick another random Card and swap them
        for ( int first = 0; first < deck.length; first++ )
        {
            // select a random number between 0 and 51
            int second =  randomNumbers.nextInt( NUMBER_OF_CARDS );

            // swap current Card with randomly selected Card
            Card temp = deck[ first ];
            deck[ first ] = deck[ second ];
            deck[ second ] = temp;
        } // end for
    } // end method shuffle

    // deal one Card
    public Card dealCard()
    {
        // determine whether Cards remain to be dealt
        if ( currentCard < deck.length )
            return deck[ currentCard++ ]; // return current Card in array
        else
            return null; // return null to indicate that all Cards were dealt
    } // end method dealCard

    public void fourSuits() {
        int spades = 0; // stores the number of cards with suit spades
        int clubs = 0; // stores the number of cards with suit clubs
        int hearts = 0; // stores the number of cards with suit hearts
        int diamonds = 0; // stores the number of cards with suit diamonds
        int counter = 0; // counts the number of picks

        // pick cards while there is one of each suit
        while (true) {
            Card card = this.dealCard(); // deal card
            this.shuffle(); // shuffle to return the picked card
            counter++; // increase the number of picks
            String s = card.getSuit(); // stores the suit of the card

            // checks what is the suit of the dealt card
            if (s.equals("Spades")) {
                if (spades == 0) {
                    System.out.println(card); // prints the first card of this suit
                }
                spades++; // increase the number of this suit
            } else if (s.equals("Clubs")) {
                if (clubs == 0) {
                    System.out.println(card); // prints the first card of this suit
                }
                clubs++; // increase the number of this suit
            } else if (s.equals("Diamonds")) {
                if (diamonds == 0) {
                    System.out.println(card); // prints the first card of this suit
                }
                diamonds++; // increase the number of this suit
            } else if (s.equals("Hearts")) {
                if (hearts == 0) {
                    System.out.println(card); // prints the first card of this suit
                }
                hearts++; // increase the number of this suit
            }

            // if all suits are found we exit the loop
            if (spades > 0 && clubs > 0 && hearts > 0 && diamonds > 0) {
                break;
            }
        }

        System.out.println("Number of picks: " + counter); // prints the number of picks
    }
} // end class DeckOfCards
