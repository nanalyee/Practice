import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		boolean[] map = new boolean[n+1]; // 땅 배열 (1~n번까지)
		
		for (int i=0; i<q; i++) {
			int wish = Integer.parseInt(br.readLine());
			int other = 0; // 가는 경로에 가장 처음 만나는 점유된 땅 번호
			int now = wish;
			
			while(true) {
				if (now<=0) break; // 범위 밖이면 종료
				if (map[now]) other = now; // 이미 점유된 땅이면 갱신
				now = now/2; // 부모 층에 접근
			}
			if (other==0 ) map[wish] = true; // 경로에 점유된 땅 없었으면 점유
			sb.append(other+"\n");
		}
		
		System.out.println(sb);

	}
}