import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long[][][] dp;
	static int[][] map;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		dp = new long[n+1][n+1][3];
		
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=n; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
			}
		} // end input
		
		
		
		// 가로:0, 대각선:1, 세로:2
		dp[1][2][0] = 1; // 문제에서 주어진 초기값

		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (map[i][j]==1) continue;
				dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][1]; // 이전 가로, 대각선 경우의 수 합
				dp[i][j][2] += dp[i-1][j][1] + dp[i-1][j][2]; // 이전 세로, 대각선 경우의 수 합
				if (map[i-1][j]==1 || map[i][j-1]==1) continue; 
				dp[i][j][1] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2]; // 이전 대각선, 가로, 세로 경우의 수 합
			}
		}
		
		
		System.out.println(dp[n][n][0]+dp[n][n][1]+dp[n][n][2]);
	}
	
}