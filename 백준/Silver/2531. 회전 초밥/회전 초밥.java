import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		int[] arr = new int[N];
		int[] visited = new int[d+1]; // 쿠폰 쓰면 한자리 더 가능
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int total = 0; // 먹게 되는 총 가짓수
		int max = 0; // 최대 총 가짓수
		
		// 처음 k개 확인
		for (int i=0; i<k; i++) {
			if (visited[arr[i]]==0) total++; // 안먹었던 k개 추가
			visited[arr[i]]++; // 먹음
		}
		max = total;
		// 슬라이딩 (N개를 확인해야함에 유의)
		for (int i=1; i<=N; i++) {
			// 쿠폰 사용
			if (max <= total) {
				if (visited[c] == 0) max = total + 1;
				else max = total;
			}
			
			visited[arr[i-1]]--; // 방문 해제
			// 제거될 초밥이 안먹어본(중복없는) 초밥이라면 먹을 총 가짓수 -1
			if (visited[arr[i-1]]==0) total--; 
			// 새로온 초밥이 안먹어본(중복없는) 초밥이라면 먹을 총 가짓수 +1
			if (visited[arr[(i+k-1)%N]]==0) total++;
			visited[arr[(i+k-1)%N]]++; // 방문 처리
		}
		
		System.out.println(max);
	}
}