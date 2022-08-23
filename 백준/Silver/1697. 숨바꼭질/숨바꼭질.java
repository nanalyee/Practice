import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, k; // 수빈, 동생의 위치, 횟수
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken()); // 수빈
		k = Integer.parseInt(st.nextToken()); // 동생
		visited = new int[100_001];
		
		find(n);
		
		System.out.println(visited[k]);
	}

	private static void find(int idx) {
		if (n==k) return;
		
		Queue<Integer> que = new LinkedList<>();
		que.add(idx);
		
		while(!que.isEmpty()) {
			int now = que.poll();
			for (int i=0; i<3; i++) {
				int x=0;
				if (i==0)  x = now+1;
				else if (i==1) x = now-1;
				else x = now*2;
				
				if (x<0 || x>100_000 || visited[x]!=0) continue;
				visited[x] = visited[now]+1;
				if (x==k) break;
				que.add(x);
			}
		}
	}
}