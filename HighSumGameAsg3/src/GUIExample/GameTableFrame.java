package GUIExample;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import View.*;
import Model.*;

public class GameTableFrame extends JFrame{
	 
    private GameTablePanel gameTablePanel;
    private ShufflePanel shufflePanel;
    private Dealer dealer;
    private Player player;
    private int count=0;
    
    public GameTableFrame(Dealer dealer, Player player)
    {
        this.dealer = dealer;
        this.player = player;
        gameTablePanel = new GameTablePanel(dealer,player);
        shufflePanel = new ShufflePanel();
        this.count=0;
        gameTablePanel.setLayout(null);
        // add(gameTablePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void updateGameTable()
    {
        gameTablePanel.repaint();
    }
    
    public GameTablePanel getGamePanel(){
        return this.gameTablePanel;
    }
    public void addLoading(){
        remove(gameTablePanel);
        add(shufflePanel);
        pack();
        revalidate();
        repaint();
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(shufflePanel);
                add(gameTablePanel);
                pack();
                revalidate();
                repaint();
            }
        });
        timer.setRepeats(false); 
        timer.start();
    }
   
    
}
