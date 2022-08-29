import java.util.Scanner;
import java.io.FileInputStream;

class Main {
	static public void main(String []args) throws Exception {
        Scanner sc = new Scanner(System.in);
		//StringBuilder sb = new StringBuilder();
		int n = sc.nextInt();
		int[] arr = new int[3];
		
		if ( n<10 ) {
			System.out.println("-1");
		}
		else {
			while ( n>=300 ) {
				n-=300;
				arr[0]++;
			}
			while ( n>=60 ) {
				n-=60;
				arr[1]++;
			}
			while ( n>=10 ) {
				n-=10;
				arr[2]++;
			}
			if ( n!=0 ) System.out.println("-1");
			else {
				for ( int i=0 ; i<3 ; i++ ) {
					System.out.println(arr[i]);
				}
			}
		}
    }
}