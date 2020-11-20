import java.util.Scanner;
import javax.swing.*;
public class Ex22 {
	public static void main (String[] args){
		Scanner input;
		input = new Scanner(System.in);
		
		String weight_string, height_string, name;
		
		System.out.print("Window name: ");
		name = input.next();
		System.out.print("Window width: ");
		weight_string = input.next();
		System.out.print("Window height: ");
		height_string = input.next();
		
		int w = Integer.parseInt(weight_string);
		int h = Integer.parseInt(height_string);
		
		JFrame window;
		window = new JFrame();

		window.setTitle(name);
		window.setSize(w, h);
		window.setVisible(true);
	}
}
