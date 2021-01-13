import javax.swing.JFrame;
import java.util.Scanner;

class IterFrame{
	public static void main(String[] args){
	
	JFrame frame;
	int h, w;
	String title;
	Scanner H, W, Title;

	System.out.println("Quais as dimensoes do seu Frame?");
	H = new Scanner(System.in);
	h = H.nextInt();
	W = new Scanner(System.in);
	w = W.nextInt();
	System.out.println("Qual o title do seu Frame?");
	Title = new Scanner(System.in);
	title = Title.next();

	frame = new JFrame();
	frame.setVisible(true);
	frame.setTitle(title);
	frame.setSize(h,w);

	}
}

