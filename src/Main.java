import javax.swing.JFrame;


public class Main extends JFrame{

	public Main(){
		add(new Gui());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,700);
		setTitle("Achtung Die Jaco");
		
		setVisible(true);
		
	}
	
	public static void main(String[] args){
		new Main();
	}
}
