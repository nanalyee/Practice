import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[3][n+1]; // 1~N번까지, R=0, G=1, B=2
 
		dp[0][0] = dp[0][1] = dp[0][2] = 0; // 0번일때는 존재하지 않음
		
		for (int j=1; j<n+1; j++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int i=0; i<3; i++) { // 비용을 모두 dp 배열에 넣어줌
				dp[i][j] = Integer.parseInt(st.nextToken());
			} 
		} // end input

		
		for (int i=2; i<=n; i++) { // 2층부터 n층까지
			dp[0][i] += Math.min(dp[1][i-1], dp[2][i-1]); // i층이 R -> i-1층의 G,B
			dp[1][i] += Math.min(dp[0][i-1], dp[2][i-1]); // i층이 G
			dp[2][i] += Math.min(dp[0][i-1], dp[1][i-1]); // i층이 B
		}
		
		System.out.println( Math.min(Math.min(dp[0][n], dp[1][n]), dp[2][n]) );
	}
}