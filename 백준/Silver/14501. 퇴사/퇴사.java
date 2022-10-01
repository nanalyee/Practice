import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int ans = 0;
        
        int[] T = new int[n+1];
        int[] P = new int[n+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        } // end input

        int[] dp = new int[n+1]; // 1~n번까지
        
        dp[0] = 0; // 초기값 세팅

        
        for(int i = 1; i <= n; i++) {
            if(i + T[i] <= n+1) {  // 퇴사 전에 일을 끝낼 수 있으면
                for(int j = 0; j < i; j++) {
                    if(j + T[j] <= i) {  // j날에 상담한 후 i날에 상담할 수 있으면
                        dp[i] = Math.max(dp[i], dp[j] + P[i]);
                    }
                }
                ans = Math.max(ans, dp[i]); // 최대값으로 갱신
            }
        }
        System.out.println(ans);
    }
}