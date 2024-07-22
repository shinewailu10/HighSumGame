package Controller;
import Model.Dealer;
import Model.Player;
import View.ViewController;


public class GameController {

	private Dealer dealer;
	private Player player;
	private ViewController view;
	private static int chipsOnTable;
	private boolean nextGame;

	public GameController(Dealer dealer,Player player,ViewController view) {
		this.dealer = dealer;
		this.player = player;
		this.view = view;
		chipsOnTable = 0;

	}
	
	public static int getChipsOnTable() {
		return chipsOnTable;
	}
	


	public void run() {	
		
		boolean z = false;

		

		while(z == false) {
			boolean x = this.view.getValidLoginStatus();
			if(x==true) {
				
				this.view.constructGame();
				z=true;
			}else{
				
				this.view.displayError("Invalid Login!");;
			}
		}
		


		boolean carryOn= true;
		
		while(carryOn) {
			runOneRound();
			
			if(nextGame == false) {
				carryOn = false;
			}
		}
		this.view.ShowPlayerNameAndLeftOverChips(this.player);
		this.view.ends();
		this.view.ShowExitGame("Thanks For playing, Bye for now!");

		
	}
	
	public void runOneRound() {
		
		this.view.ShowyGameTitle();
		this.view.ShowDoubleLine();
		this.view.ShowPlayerNameAndChips(this.player);
		this.view.ShowSingleLine();
		this.view.ShowGameStart();
		this.view.ShowSingleLine();
		this.dealer.shuffleCards();
		this.view.constructShuffle();
		chipsOnTable = 0;
		this.view.ShowBetOntable(chipsOnTable);
		this.view.pause(3000);


		boolean playerQuit = false;
		
		for(int round = 1;round<=4;round++) {
			
			this.view.ShowSingleLine();
			this.view.ShowDealerDealCardsAndGameRound(round);
			this.view.ShowSingleLine();
			

			if(round==1) {//round 1 deal extra card
				this.dealer.dealCardTo(this.player);
				
				this.dealer.dealCardTo(this.dealer);
			
			}
			this.dealer.dealCardTo(this.player);
		
			this.dealer.dealCardTo(this.dealer);
		

			this.view.showPlayerCardsOnHand(this.dealer);
			this.view.ShowBlankLine();
			this.view.showPlayerCardsOnHand(player);
			this.view.ShowPlayerTotalCardValue(player);

			this.view.DisplayPlayerCardValue();
			this.view.HideDealerCardValue();
			this.view.DisplayPlayerChipsLeft();	
			this.view.DisplayBetOnTable(chipsOnTable);
			this.view.refresh();
			
			
			int whoCanCall = this.dealer.determineWhichCardRankHigher(dealer.getLastCard(), player.getLastCard());
			
			if(whoCanCall==1) {//dealer call
				
				this.view.displayDealerRoundWinner();
				
				int chipsToBet = this.view. getDealerCallBetChips();
				//ask player want to follow?
				char decision = this.view.askFollow("Follow");

				if(decision =='y') {
					
					this.player.deductChips(chipsToBet);
					chipsOnTable+=2*chipsToBet;

					this.view.ShowPlayerNameAndLeftOverChips(this.player);
					this.view.ShowBetOntable(chipsOnTable);

				}else {
					playerQuit = true;
				
					break;
				}
			}else {//player call

				this.view.displayPlayerRoundWinner();
				
				if(round==1) {//round 1 player cannot quit
					int input = 0;
					boolean valid = false;
					while (!valid) {
						String chipsToBet = this.view.askBet();
						try {
							input = Integer.parseInt(chipsToBet);
							if(input <= 0 || input >= this.player.getChips()) {
								throw new IllegalArgumentException();
							}
							valid = true;
						} catch (NumberFormatException e) {
							this.view.displayError("Must be an INTEGER!");
						}catch(IllegalArgumentException x){
							this.view.displayError("Enter a valid bet amount");
						}
					}

					this.player.deductChips(input);
					chipsOnTable+=2*input;
					this.view.ShowBetOntable(chipsOnTable);
				}else {
					char decision = this.view.askFollow("Call");
					if(decision=='y') {	
					int input = 0;
					boolean valid = false;

					while (!valid) {
						String chipsToBet = this.view.askBet();
						try {
							input = Integer.parseInt(chipsToBet);
							if(input <= 0 || input >= this.player.getChips()) {
								throw new IllegalArgumentException();
							}
							valid = true;
						} catch (NumberFormatException e) {
							this.view.displayError("Must be an INTEGER!");
						}catch(IllegalArgumentException x){
							this.view.displayError("Enter a valid bet amount");
						}
					}
						this.player.deductChips(input);
						chipsOnTable+=2*input;
						this.view.ShowPlayerNameAndLeftOverChips(this.player);
						this.view.ShowBetOntable(chipsOnTable);
					}else {
						playerQuit = true;
					
						break;
					}
				}
			}
			this.view.DisplayBetOnTable(chipsOnTable);
			this.view.DisplayPlayerChipsLeft();	
		}
		this.view.DisplayDealerCardValue();
		this.view.showFinalCard();
		
		
		//check who win
		if(playerQuit) {
			this.view.ShowPlayerNameAndLeftOverChips(this.player);
			this.view.ShowPlayerQuit();
			nextGame = this.view.askAnotherGame("You quit, Next Game?");
		}
		else if(this.player.getTotalCardsValue()>this.dealer.getTotalCardsValue()) {
			this.view.ShowPlayerWin(this.player);
			this.player.addChips(chipsOnTable);
			chipsOnTable=0;
			this.view.DisplayBetOnTable(chipsOnTable);
			this.view.DisplayPlayerChipsLeft();
			this.view.ShowPlayerNameAndLeftOverChips(this.player);
			 nextGame = this.view.askAnotherGame(this.player.getLoginName()+" Wins. Next Game?");
		}else if(this.player.getTotalCardsValue()<this.dealer.getTotalCardsValue()) {
			this.view.ShowDealerWin();
			this.view.ShowPlayerNameAndLeftOverChips(this.player);
			nextGame = this.view.askAnotherGame(this.dealer.getLoginName()+" Wins, Next Game?");
		}else {
			this.view.ShowTie();
			this.player.addChips(chipsOnTable/2);
			this.view.ShowPlayerNameAndLeftOverChips(this.player);
			nextGame = this.view.askAnotherGame( "The game is tie , Next Game?");
		}
		
		dealer.addCardsBackToDeck(dealer.getCardsOnHand());
		dealer.addCardsBackToDeck(player.getCardsOnHand());
		dealer.clearCardsOnHand();
		player.clearCardsOnHand();
		this.view.displayCardBack();
		
		
		

	}
	
	
}
