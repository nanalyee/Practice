import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int[][] adjMatrix;
	static boolean[] visited;
	static int n;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt(); // 정점
		int m = sc.nextInt(); // 간선
		int v = sc.nextInt(); // 탐색 시작 번호
		
		adjMatrix = new int[n+1][n+1]; // 그래프 초기화
		
		
		for (int i=1; i<=m; i++) { // 간선 정보에 해당하는 부분만 덮어씀
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[to][from] = adjMatrix[from][to] = 1;
		}
		
		visited = new boolean[n+1]; // 방문 노드 확인
		dfs(v);
		sb.append("\n");
		visited = new boolean[n+1];
		bfs(v);
		
		System.out.println(sb);
	}

	private static void dfs(int cur) {
		visited[cur] = true;
		sb.append(cur+" ");
		
		// 현재 정점의 인접정점들에 큐에 넣어서 차후 탐색하도록 만들기
		for (int i=1; i<=n; i++) {
			if (!visited[i] && adjMatrix[cur][i] != 0) {
				dfs(i);
			}
		}
	}

	private static void bfs(int node) {
		Queue<Integer> queue = new ArrayDeque<>();		
		visited[node] = true;
		queue.offer(node);
		sb.append(node+" ");
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			// 현재 정점의 인접정점들에 큐에 넣어서 차후 탐색하도록 만들기
			for (int i=1; i<=n; i++) {
				if (!visited[i] && adjMatrix[cur][i] != 0) {
					visited[i] = true;
					queue.offer(i);
					sb.append(i+" ");
				}
			}
		}
	}
	
	
}