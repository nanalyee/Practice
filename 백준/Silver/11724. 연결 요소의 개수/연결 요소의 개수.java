import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N, M;
	public static int[][] graph; // 인접행렬그래프
	static boolean[] visited; // 방문여부
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수
		graph = new int[N][N];
		visited = new boolean[N];
		int answer = 0;
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1; // 0부터 시작하기 위함
			int v = Integer.parseInt(st.nextToken()) - 1;
			graph[u][v] = 1; // 연결 상태
			graph[v][u] = 1; // 연결 상태
		}
		
		for (int i=0; i<N; i++) {
			if(!visited[i]) {
				bfs(i);
				answer++;
			}
		}
		System.out.println(answer);
	}
	
	public static void bfs(int node) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(node);
		visited[node] = true;
		
		while(!que.isEmpty()) {
			int now = que.poll(); // 기준
			for (int i=0; i<N; i++) { // 연결되어있는 모든 노드 확인
				if (graph[now][i] == 1 && !visited[i]) { // 연결되었고 방문한 적이 없다면
					que.add(i); // 큐에 추가
					visited[i] = true; // 방문처리
				}
			}
		}
	}
}