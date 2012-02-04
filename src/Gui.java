import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Gui extends JPanel implements ActionListener{

	private final int refreshRate = 140;
	public Gui (){
		this.setBackground(Color.black);
		this.setVisible(true);
		initGame();
	}
	
	public void initGame() {
		Timer timer = new Timer(refreshRate,this);
		timer.start();
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.setBackground(Color.blue);
		this.repaint();
		
	}
	

}
