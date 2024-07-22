import Controller.*;
import Model.*;
import View.*;

public class GUIExample {

  private Dealer dealer;
  private Player player;
  private ViewController view;
  private GameController gc;


  //testing of game table UI
  public GUIExample() {
    player = new Player("gg", "gg", 1000);
    dealer = new Dealer();
    this.view = new ViewController(this.dealer, this.player);

    this.gc = new GameController(this.dealer, this.player, this.view);
  }

  public void run() {
    gc.run();
  }

  public static void main(String[] args) {
    new GUIExample().run();
  }
}
