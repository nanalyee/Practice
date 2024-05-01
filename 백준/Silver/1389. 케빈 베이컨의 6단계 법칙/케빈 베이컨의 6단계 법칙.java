import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N, M, ans, min;
	public static int[][] arr; // 인접행렬
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수 - 친구 관계의 수 M (1 ≤ M ≤ 5,000)
		arr = new int[N+1][N+1]; // 1부터 시작하기 위함
		min = Integer.MAX_VALUE;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					arr[i][j] = 0;
				else
					arr[i][j] = 5001; // 최대
			}
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); 
			int v = Integer.parseInt(st.nextToken());
			arr[u][v] = 1; // 연결 상태
			arr[v][u] = 1; // 연결 상태
		}
		
		for(int i=1;i<=N;i++) {
			visited=new boolean[N+1]; // 초기화
			bfs(i);
		}
		System.out.println(ans);
	}
	
	public static void bfs(int num) {
		Queue<Item> que = new LinkedList<>();
		que.add(new Item(num ,0));
		visited[num] = true;
		int cnt=0;
		while (!que.isEmpty()) {
			Item item = que.poll();
			cnt += item.cnt;
			for (int j=1; j<=N; j++) {
				if (arr[j][item.idx]==1 && !visited[j]) {
					visited[j] = true;
					que.offer(new Item(j, item.cnt+1));
				}
			}
		}
		if (min>cnt) {
			min = cnt;
			ans = num;
		}
	}
	
	static class Item{
		int idx;
		int cnt;
		Item(int idx,int cnt){
			this.idx = idx;
			this.cnt = cnt;
		}
	}
}