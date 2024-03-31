import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// dp
		int[] dp = new int[11]; // 0~10
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i=4; i<=10; i++) {
			dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
		}
		
		// print out
		int T = Integer.parseInt(br.readLine());
		for (int tc=0; tc<T; tc++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]+"\n");
		}
		System.out.println(sb);
	}
}