import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, result;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		result = 0;
		visited = new boolean[n];
		
		graph = new ArrayList[n]; // 인접리스트
		for (int i=0; i<n; i++) {
			graph[i] = new ArrayList<>();
		}
        
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[v].add(e); // 양방향
			graph[e].add(v);
		}
		
		for (int i=0; i<n; i++) {
			if (result==0) friend(1,i);
		}
		
		System.out.println(result);
	}

	
	private static void friend(int dpt, int node) {
		if (dpt==5) { // 5개 이상 이어진 노드 조건 만족
			result=1;
			return;
		}
		visited[node] = true; // 방문완료
		for (int i=0; i<graph[node].size(); i++) {
			int next = graph[node].get(i); // 다음 노드 조사
			if (!visited[next]) friend(dpt+1, next); // 방문 안했으면 연결
		}
		visited[node] = false; // 초기화
	}
}