import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 우선순위큐는 힙으로 구현할수있다
		for (int i=0; i<n; i++) {
			int x = Integer.parseInt(br.readLine());
			
			// 배열에서 가장 작은 값을 출력하고 그 값을 배열에서 제거
			if (x==0) {
				if (pq.isEmpty()) sb.append(0+"\n");
				else sb.append(pq.poll()+"\n");
			}
			else pq.add(x);
		}
		
		System.out.println(sb);
	}
}