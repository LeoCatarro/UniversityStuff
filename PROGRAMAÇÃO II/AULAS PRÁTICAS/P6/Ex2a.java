class Ex2a {
	public static void main(String[] args){
		int num = 1;
		int sum = 0;

	/*	while(num <= 100){
			sum = sum + num;
			num++;
			System.out.println("Soma:" + sum);
		}
	*/
	/*	for( int num = 1 ; num<=100 ; num++){
			sum = sum + num;
			System.out.println( sum );
		}
	*/
		do {
			sum = sum + num;
			num++;
			System.out.println(sum);
		} while(num<=100);		
	}	
}