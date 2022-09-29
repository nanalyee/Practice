import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int[] DP;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		DP = new int[N+1]; // 0~N까지
		Arrays.fill(DP, -1); // -1로 초기화
		DP[0] = 0;
		DP[1] = 0;
		
		System.out.println(makeOne(N));
	}

	private static int makeOne(int n) {
		
		if (DP[n]==-1) {
			if (n%3==0 && n%2==0) {
				DP[n] = Math.min(Math.min(makeOne(n/3), makeOne(n/2)), makeOne(n-1))+1; 
			}
			
			else if (n%3==0) {
				DP[n] = Math.min(makeOne(n/3), makeOne(n-1))+1;
			}
			
			else if (n%2==0) {
				DP[n] = Math.min(makeOne(n/2), makeOne(n-1))+1;
			}
			
			else {
				DP[n] = makeOne(n-1)+1;
			}
		}

		
		return DP[n];
	}
}