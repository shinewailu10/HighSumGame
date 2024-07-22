package Model;
import java.util.*;

public class Dealer extends Player{

	private Deck deck;
	
	public Dealer() {
		super("Dealer","",0);
		deck = new Deck();
	}
	
	public void shuffleCards() {
		System.out.println("Dealer shuffle deck");
		deck.shuffle();
	}
	
	public void dealCardTo(Player player) {
		Card card = deck.dealCard();
		player.addCard(card);
	}
	
	public void addCardsBackToDeck(ArrayList<Card> cards) {
		deck.appendCard(cards);
	}
	
	public int determineWhichCardRankHigher(Card card1, Card card2) {
		if(card1.getRank()>card2.getRank()) {
			return 1;
		}else {
			return 2;
		}
	}
	
	
	
}
