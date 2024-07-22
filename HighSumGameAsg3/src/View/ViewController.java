package View;
import Model.*;
import javax.swing.*;
import GUIExample.*;
import java.awt.*;
import java.awt.event.*;
import Helper.*;


public class ViewController {
	
	private GameTableFrame gFrame;
	private Dealer dealer;
	private Player player;
	
	
///////////////////////////////////GUI////////////////////////////////////////

	public ViewController(Dealer dealer, Player player) {
		this.player = player;
		this.dealer = dealer;
	}

	public boolean getValidLoginStatus(){

	

		System.out.println("Go");
		JPanel panel = new JPanel();
		

	panel.setPreferredSize(new Dimension(400, 100));

	
		JLabel label1 = new JLabel("Username");
        label1.setFont(new Font("Serif", Font.PLAIN, 16));

		label1.setBounds(100, 8, 70, 20);
		panel.add(label1);
		

		JTextField textField1 = new JTextField();
		textField1.setBounds(100, 27, 193, 28);
		panel.add(textField1);
		
		JLabel label2 = new JLabel("Password");
        label2.setFont(new Font("Serif", Font.PLAIN, 16));

		label2.setBounds(100, 55, 70, 20);
		panel.add(label2);

		JPasswordField textField2 = new JPasswordField();
		textField2.setBounds(100, 75, 193, 28);
		panel.add(textField2);
		
		


		panel.setLayout(null);
		String[] buttons = {"Login"};
	
		boolean validLogin=false;
		panel.add(label1);
		panel.add(label2);
		panel.add(textField1);    
		panel.add(textField2);
		

		int result = JOptionPane.showOptionDialog(
				null,
				panel,
				"HighSum Game",
				JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE,
				null,
				buttons,
				buttons[0]);

		if (result == JOptionPane.OK_OPTION) {
			
			String name = textField1.getText();
			String password = textField2.getText();
			

			if(name.equals(this.player.getLoginName()) && Utility.getHash(password).equals(this.player.gethash())){
				validLogin = true;
			   
			}else{
				validLogin = false;
			}
			
			
		}
		return validLogin;


	}

	


	public void constructGame(){
		gFrame = new GameTableFrame(this.dealer,this.player);
	}

	public void constructShuffle(){
		gFrame.addLoading();
	}


	public void refresh() {
		gFrame.updateGameTable();
	}

	public void DisplayPlayerCardValue(){
		gFrame.getGamePanel().setplayerCardValue();
	}

	public void DisplayPlayerChipsLeft(){
		gFrame.getGamePanel().setPlayerChips();
	}
	public void HideDealerCardValue(){
		gFrame.getGamePanel().setHidedealerCardValue();
	}
	public void DisplayDealerCardValue(){
		gFrame.getGamePanel().setShowdealerCardValue();
	}
	public void DisplayBetOnTable(int amount){
		gFrame.getGamePanel().setBetonTable(amount);
	}
	public void pause(int time) {
		try{
			Thread.sleep(time);
			
		 }catch(Exception e){}
   }

   public void showFinalCard(){
		gFrame.getGamePanel().imgChange();
	
   }
   public void displayCardBack(){
	gFrame.getGamePanel().imgBackChange();
   }
	public void displayDealerRoundWinner(){
		gFrame.getGamePanel().setWhoWin("Dealer win this round, "+ dealer.getLoginName()+" calls !!!");
	}
	public void displayPlayerRoundWinner(){
		gFrame.getGamePanel().setWhoWin("Player win this round, "+player.getLoginName()+" calls !!!");
	}

	public void  displayError(String meg){
	
        JOptionPane.showMessageDialog(null, meg, "Error", JOptionPane.INFORMATION_MESSAGE);
	}



	public String askBet(){
		JPanel panel = new JPanel(); 
        JLabel label = new JLabel("Enter your bet");
        label.setFont(new Font("Serif", Font.PLAIN, 16));

        JTextField textField = new JTextField(10);
      
		String[] buttons = {"OK"};
		String bet="";
		
        panel.add(label);
        panel.add(textField);
		

        int result = JOptionPane.showOptionDialog(
                null, 
                panel,
                "Bet",
                JOptionPane.OK_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                buttons, 
                buttons[0]);
				

		
        if (result == JOptionPane.OK_OPTION) {
			
             bet = textField.getText();
           
			
        }
		return bet;
	}
	
	public char askFollow(String option) {
		String[] buttons = { option, "Quit" };
		String message;
		if(option.equals("Follow")){
			 message = "Dealer Call, place 10 chips.";
		}else{
			 message = "Player Call, Choose an option.";
		}
     	int result = JOptionPane.showOptionDialog(null, 
                          message,
                          "Choose one option",
                          JOptionPane.YES_NO_OPTION,
                          JOptionPane.QUESTION_MESSAGE,
                          null,buttons, buttons[0]);

      	if(result == JOptionPane.YES_OPTION){
         return 'y';
		}else{
		return 'n';
	  }
    }

	public boolean askAnotherGame(String message) {
		boolean anotherGame = true;
		System.out.println("ask another game");
		int again = JOptionPane.showConfirmDialog(null, message, "Next Game?", JOptionPane.YES_NO_OPTION);
		if(again==JOptionPane.NO_OPTION) {
			anotherGame=false;
		}
		return anotherGame;
	}

	public void ends() {
		gFrame.dispose();
	}




///////////////////////Console/////////////////////////////////////////////////////////////
	public void ShowPlayerNameAndChips(Player player) {
		
		System.out.println(player.getLoginName()+", You have "+player.getChips()+" chips");
	}

	public void ShowPlayerTotalCardValue(Player player) {
		
		System.out.println("Value:"+player.getTotalCardsValue());
	}

	public void showPlayerCardsOnHand(Player player) {
		
		
		System.out.println(player.getLoginName());

		if(player instanceof Dealer) {
			
			for(int i=0;i<player.getCardsOnHand().size();i++) {
				if(i==0) {
					System.out.print("<HIDDEN CARD> ");
				}else {
					System.out.print(player.getCardsOnHand().get(i).toString()+" ");
				}
			}
		}else {
			for(Card card:player.getCardsOnHand()) {
				System.out.print(card+" ");
			}
		}
		System.out.println();
	}

	public void ShowExitGame(String meg) {
		System.out.println("Thanks For playing, Bye for now!");
        JOptionPane.showMessageDialog(null, meg, "See ya :)", JOptionPane.INFORMATION_MESSAGE);
        

	}

	public void ShowBetOntable(int bet) {
	
		System.out.println("Bet on table : "+bet);
	}
	
	public void ShowPlayerWin(Player player) {
		System.out.println(player.getLoginName()+" Wins!");
	}
	
	public void ShowDealerWin() {
		System.out.println("Dealer Wins!");
	}
	
	public void ShowTie() {
		System.out.println("It is a tie!.");
	}
	
	public void ShowPlayerQuit() {
		System.out.println("You Quit!");
	}
	
	public void ShowBlankLine() {
		System.out.println();
	}
	
	public void ShowDealerDealCardsAndGameRound(int round) {
		System.out.println("Dealer dealing cards - ROUND "+round);
	}
	
	public void ShowGameStart() {
		System.out.println("Game starts - Dealer shuffle deck");
	}
	
	public void ShowPlayerNameAndLeftOverChips(Player player) {
		System.out.println(player.getLoginName()+", You are left with "+player.getChips()+" chips");
	}
	
	public void ShowyGameTitle() {
		System.out.println("HighSum GAME");
	}
	
	public void ShowSingleLine() {
		for(int i=0;i<30;i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	public void ShowDoubleLine() {
		for(int i=0;i<30;i++) {
			System.out.print("=");
		}
		System.out.println();
	}
	

	public int getDealerCallBetChips() {
		System.out.println("Dealer call, state bet: 10");
		return 10;
	}


	
	
}
