package GUIExample;
import java.awt.*;
import javax.swing.*;
import Model.*;

public class ShufflePanel extends JPanel {

    private ImageIcon img1;
    private ImageIcon cardBackImage;
    private JLabel cbi;
    private JLabel img;
    private JLabel txt;
    public ShufflePanel(){
        setPreferredSize(new Dimension(1920, 1080));
        setBackground(Color.gray);
        setLayout(null);
        
        img1 = new ImageIcon("images/shuffle.gif");

        img = new JLabel(img1);
        img.setBounds(650, 200, img1.getIconWidth(), img1.getIconHeight());
		add(img);

        txt = new JLabel("shuffling the cards ......");
        txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
        txt.setBounds(700, 500, 500 ,100 );
		add(txt);
		
		cardBackImage = new ImageIcon("images/back.png");
		cbi = new JLabel(cardBackImage);
		cbi.setBounds(
		      100,
		      120,
		      cardBackImage.getIconWidth(),
		      cardBackImage.getIconHeight()
		    );

		

	
    }
	 public void imgBackChange() {
	    cardBackImage = new ImageIcon("images/back.png");
	    repaint();
	  }
	
	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	
	    
	    int x = 300;
	    int y = 650;
	    int a=6;
	    
	    for (int i=0; i< a; i++) {
	      cardBackImage.paintIcon(this, g, x, y);
	      x += 200;
	    }
	  }
}
