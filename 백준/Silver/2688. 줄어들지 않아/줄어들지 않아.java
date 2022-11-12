import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// i = 2 ->  dp[2][2] = dp[1][2] + dp[1][1] + dp[1][0]

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        long[][] dp = new long[65][10]; // 1<=n<=64 개의 자리 수, 시작하는 수
        
		for (int i = 0; i <= 9; i++) {
			dp[1][i] = 1;
		} // 기저조건 (한자리)
		
		for (int i = 2; i <= 64; i++) { // 자리수
			for (int j = 0; j <= 9; j++) { // 시작하는 수
				for (int k = j; k <= 9; k++) { // j번째부터 9까지 누적
					dp[i][j] += dp[i - 1][k];
				}
			}
		}
		
		int T = Integer.parseInt(br.readLine()); // test case
		for (int t=1; t<=T; t++) {
			int n = Integer.parseInt(br.readLine()); // 자리수
			long res = 0;
			for (int i = 0; i <= 9; i++) {
				res += dp[n][i];
			}
			sb.append(res + "\n");
		}
		
		System.out.println(sb);
	}
}