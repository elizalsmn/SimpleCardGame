import java.util.*;
/**
 * This class represents a deck of cards.
 * It has Deck, CardsPlayed and playermoney elements
 * notes : please put the "Images" files outside the src file
 * @author elizalesmana
 * @version 1.3
 * @since 2023-11-15
 */
public class card {
	private ArrayList<Integer> Deck;
	private ArrayList<String> CardsPlayed;
	private int playermoney = 100;

	
	/**
	 * Constructs a new deck of cards.
	 * Initialize the deck that has 52 cards and and empty list of cardsplayed
	 */
	public card() {
		Deck = new ArrayList<Integer>();
		CardsPlayed = new ArrayList<String>();
		this.playermoney=playermoney;
		
		for (int i=0;i<52;i++) {
			Deck.add(i);
		}
	}
	
	/**
	 * Shuffles the deck of cards 
	 */
	public void shuffle () {
		for (int i=0 ; i<52;i++) {
			int index = (int)(Math.random()*52);
			int temp = Deck.get(i);
			Deck.set(i,Deck.get(index));
			Deck.set(index,temp);
		}
	}
	
	/**
	 * Subtracts the bet amount from the player's money
	 * @param money the amount of money that the player lost 
	 */
	public void losebet(int money) {
		playermoney -= money;
	}
	
	/**
	 * Adds the bet amount to the player's money.
	 * @param money the amount of money that the player won
	 */
	public void winbet(int money) {
		playermoney += money;
	}
	
	/**
	 * This returns the player's current money
	 * @return int
	 */
	public int getmoney() {
		return playermoney;
	}
	
	/**
	 * Choose nine cards for one round for both the player and the dealer
	 * the first 3 cards are the player's, the next 3 cards are for replacements,
	 * and the last 3 cards are the dealer's
	 * and it'll return an array list of URLs to the images of the cards.
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getcard() {
		for (int i=0; i<9;i++) {
			String suit = String.valueOf(Deck.get(i)%4+1);
			String cardnum = String.valueOf(Deck.get(i)%13+1);
			CardsPlayed.add("Images/card_"+suit+cardnum+".gif");
		}
		
		return CardsPlayed;
	}
	
	/**
	 * Replaces a card in the player's hand with a card from the deck.
	 * It will also change the cards icon
	 * @param x the index of the card that want to be replaced
	 */
	public void replace(int x) {
		CardsPlayed.set(x, CardsPlayed.get(x+3));
		Deck.set(x, Deck.get(x+3));
	}
	
	/**
	 * Checks if the player has lost all of their money.
	 * @return boolean
	 */
	public boolean checklose() {
		if (playermoney<=0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Compare which playing hand is stronger based on the number of
	 * special cards (J,Q,K) and/or modulus 10 of the sum of 
	 * normal cards from each player.
	 * If the dealer's hand is weaker than it'll call winbet function,
	 * otherwise it will call losebet function with bet as the parameter. 
	 * 
	 * @param bet The amount of money the player is betting.
	 * @return boolean True if the player won, false if the dealer won.
	 */
	public boolean getresult(int bet) {
		boolean player = false;
		int special_dealer = 0;
		int special_player = 0;
		int normal_dealer = 0;
		int normal_player = 0;
		for (int i=0; i<3;i++) {
			if (Deck.get(i)%13+1>10) {
				special_player +=1;
			}
			else {
				normal_player+=Deck.get(i)%13+1;
			}
		}
		for (int i=6; i<9;i++) {
			if (Deck.get(i)%13+1>10) {
				special_dealer +=1;
			}
			else {
				normal_dealer+=Deck.get(i)%13+1;
			}
		}
		if (special_dealer<special_player) {
			player =  true;
		}
		else if(special_dealer==special_player) {
			if (normal_dealer%10<normal_player%10) {
				player =  true;
				
			}
		}
		if (player) {
			winbet(bet);
		}
		else {
			losebet(bet);
		}
		CardsPlayed.clear();
		return player;
		
	}
	
	
	
}
	