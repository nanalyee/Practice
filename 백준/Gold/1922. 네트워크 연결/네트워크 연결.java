import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {
	
	// 간선
	static class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	// Union Find
	static int[] parents; // 서로소 집합
	static int N,M; // 정점, 간선
	static ArrayList<Edge> edgeList; // 간선 리스트
	
	public static int find(int a) {
		if( a==parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if( aRoot==bRoot ) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int result = 0; // 최소 비용 결과
		
		edgeList = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			edgeList.add(new Edge (Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken())) );
		}
		
		// make
		parents = new int[N+1];
		for (int i=1; i<=N; i++) { // 자기 자신을 대표로 생성
			parents[i] = i;
		}
		
		// 정렬
		Collections.sort(edgeList);
		
		int count = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				result += edge.weight; // 가중치 누적
				if (++count == N-1) break;
			}
		}
		
		System.out.println(result);
	}
	
}