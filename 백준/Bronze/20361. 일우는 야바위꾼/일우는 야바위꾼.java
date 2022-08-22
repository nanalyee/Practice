import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n=sc.nextInt(); // 컵 개수
		int x=sc.nextInt(); // 공 위치 (1~N)
		int k=sc.nextInt(); // 반복 횟수
		int[] arr = new int[n+1]; // 1번부터 ~
		arr[x]=1;
		
		for (int i=0 ; i<k ; i++ ) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int num=arr[a];
			arr[a]=arr[b];
			arr[b]=num;
		}
		
		for (int i=0 ; i<n+1; i++) {
			if (arr[i]==1) {
				System.out.println(i);
				break;
			}
		}
	}
}