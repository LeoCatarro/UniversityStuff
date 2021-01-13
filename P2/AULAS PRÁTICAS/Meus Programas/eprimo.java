import java.util.Scanner;
class eprimo{
	
	static boolean primo (int x){
		int count=0;
		if(x==1){
			return false;
		}
		if(x==2){
			return true;
		}
		for(int i=2;i<=x/2;i++){
			if(x%i==0){
				count++;
				break;
			}
		}
		if(count==0){
			return true;
		}
		else{
			return false;
		}	
	}	
	public static void main(String[] args) {
		int x;
		Scanner s = new Scanner(System.in);
		x = s.nextInt();

		System.out.println(primo(x));
	}
}	